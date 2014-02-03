/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import internal.db.exceptions.IllegalOrphanException;
import internal.db.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author elit
 */
public class RoomsJpaController implements Serializable {

    public RoomsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rooms rooms) {
        if (rooms.getSpecificResCollection() == null) {
            rooms.setSpecificResCollection(new ArrayList<SpecificRes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Simpleroom simpleroom = rooms.getSimpleroom();
            if (simpleroom != null) {
                simpleroom = em.getReference(simpleroom.getClass(), simpleroom.getIdRoom());
                rooms.setSimpleroom(simpleroom);
            }
            Collection<SpecificRes> attachedSpecificResCollection = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionSpecificResToAttach : rooms.getSpecificResCollection()) {
                specificResCollectionSpecificResToAttach = em.getReference(specificResCollectionSpecificResToAttach.getClass(), specificResCollectionSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollection.add(specificResCollectionSpecificResToAttach);
            }
            rooms.setSpecificResCollection(attachedSpecificResCollection);
            em.persist(rooms);
            if (simpleroom != null) {
                Rooms oldRoomsOfSimpleroom = simpleroom.getRooms();
                if (oldRoomsOfSimpleroom != null) {
                    oldRoomsOfSimpleroom.setSimpleroom(null);
                    oldRoomsOfSimpleroom = em.merge(oldRoomsOfSimpleroom);
                }
                simpleroom.setRooms(rooms);
                simpleroom = em.merge(simpleroom);
            }
            for (SpecificRes specificResCollectionSpecificRes : rooms.getSpecificResCollection()) {
                Rooms oldRoomsOfSpecificResCollectionSpecificRes = specificResCollectionSpecificRes.getRooms();
                specificResCollectionSpecificRes.setRooms(rooms);
                specificResCollectionSpecificRes = em.merge(specificResCollectionSpecificRes);
                if (oldRoomsOfSpecificResCollectionSpecificRes != null) {
                    oldRoomsOfSpecificResCollectionSpecificRes.getSpecificResCollection().remove(specificResCollectionSpecificRes);
                    oldRoomsOfSpecificResCollectionSpecificRes = em.merge(oldRoomsOfSpecificResCollectionSpecificRes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rooms rooms) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rooms persistentRooms = em.find(Rooms.class, rooms.getIdRoom());
            Simpleroom simpleroomOld = persistentRooms.getSimpleroom();
            Simpleroom simpleroomNew = rooms.getSimpleroom();
            Collection<SpecificRes> specificResCollectionOld = persistentRooms.getSpecificResCollection();
            Collection<SpecificRes> specificResCollectionNew = rooms.getSpecificResCollection();
            List<String> illegalOrphanMessages = null;
            if (simpleroomOld != null && !simpleroomOld.equals(simpleroomNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Simpleroom " + simpleroomOld + " since its rooms field is not nullable.");
            }
            for (SpecificRes specificResCollectionOldSpecificRes : specificResCollectionOld) {
                if (!specificResCollectionNew.contains(specificResCollectionOldSpecificRes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SpecificRes " + specificResCollectionOldSpecificRes + " since its rooms field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (simpleroomNew != null) {
                simpleroomNew = em.getReference(simpleroomNew.getClass(), simpleroomNew.getIdRoom());
                rooms.setSimpleroom(simpleroomNew);
            }
            Collection<SpecificRes> attachedSpecificResCollectionNew = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionNewSpecificResToAttach : specificResCollectionNew) {
                specificResCollectionNewSpecificResToAttach = em.getReference(specificResCollectionNewSpecificResToAttach.getClass(), specificResCollectionNewSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollectionNew.add(specificResCollectionNewSpecificResToAttach);
            }
            specificResCollectionNew = attachedSpecificResCollectionNew;
            rooms.setSpecificResCollection(specificResCollectionNew);
            rooms = em.merge(rooms);
            if (simpleroomNew != null && !simpleroomNew.equals(simpleroomOld)) {
                Rooms oldRoomsOfSimpleroom = simpleroomNew.getRooms();
                if (oldRoomsOfSimpleroom != null) {
                    oldRoomsOfSimpleroom.setSimpleroom(null);
                    oldRoomsOfSimpleroom = em.merge(oldRoomsOfSimpleroom);
                }
                simpleroomNew.setRooms(rooms);
                simpleroomNew = em.merge(simpleroomNew);
            }
            for (SpecificRes specificResCollectionNewSpecificRes : specificResCollectionNew) {
                if (!specificResCollectionOld.contains(specificResCollectionNewSpecificRes)) {
                    Rooms oldRoomsOfSpecificResCollectionNewSpecificRes = specificResCollectionNewSpecificRes.getRooms();
                    specificResCollectionNewSpecificRes.setRooms(rooms);
                    specificResCollectionNewSpecificRes = em.merge(specificResCollectionNewSpecificRes);
                    if (oldRoomsOfSpecificResCollectionNewSpecificRes != null && !oldRoomsOfSpecificResCollectionNewSpecificRes.equals(rooms)) {
                        oldRoomsOfSpecificResCollectionNewSpecificRes.getSpecificResCollection().remove(specificResCollectionNewSpecificRes);
                        oldRoomsOfSpecificResCollectionNewSpecificRes = em.merge(oldRoomsOfSpecificResCollectionNewSpecificRes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rooms.getIdRoom();
                if (findRooms(id) == null) {
                    throw new NonexistentEntityException("The rooms with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rooms rooms;
            try {
                rooms = em.getReference(Rooms.class, id);
                rooms.getIdRoom();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rooms with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Simpleroom simpleroomOrphanCheck = rooms.getSimpleroom();
            if (simpleroomOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rooms (" + rooms + ") cannot be destroyed since the Simpleroom " + simpleroomOrphanCheck + " in its simpleroom field has a non-nullable rooms field.");
            }
            Collection<SpecificRes> specificResCollectionOrphanCheck = rooms.getSpecificResCollection();
            for (SpecificRes specificResCollectionOrphanCheckSpecificRes : specificResCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rooms (" + rooms + ") cannot be destroyed since the SpecificRes " + specificResCollectionOrphanCheckSpecificRes + " in its specificResCollection field has a non-nullable rooms field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rooms);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rooms> findRoomsEntities() {
        return findRoomsEntities(true, -1, -1);
    }

    public List<Rooms> findRoomsEntities(int maxResults, int firstResult) {
        return findRoomsEntities(false, maxResults, firstResult);
    }

    private List<Rooms> findRoomsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rooms.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Rooms findRooms(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rooms.class, id);
        } finally {
            em.close();
        }
    }

    public int getRoomsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rooms> rt = cq.from(Rooms.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
