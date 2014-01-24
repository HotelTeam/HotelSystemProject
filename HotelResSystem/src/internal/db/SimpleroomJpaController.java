/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import internal.db.exceptions.IllegalOrphanException;
import internal.db.exceptions.NonexistentEntityException;
import internal.db.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author elit
 */
public class SimpleroomJpaController implements Serializable {

    public SimpleroomJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Simpleroom simpleroom) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Rooms roomsOrphanCheck = simpleroom.getRooms();
        if (roomsOrphanCheck != null) {
            Simpleroom oldSimpleroomOfRooms = roomsOrphanCheck.getSimpleroom();
            if (oldSimpleroomOfRooms != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Rooms " + roomsOrphanCheck + " already has an item of type Simpleroom whose rooms column cannot be null. Please make another selection for the rooms field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BedType idBed = simpleroom.getIdBed();
            if (idBed != null) {
                idBed = em.getReference(idBed.getClass(), idBed.getIdBed());
                simpleroom.setIdBed(idBed);
            }
            Rooms rooms = simpleroom.getRooms();
            if (rooms != null) {
                rooms = em.getReference(rooms.getClass(), rooms.getIdRoom());
                simpleroom.setRooms(rooms);
            }
            Suiteroom suiteroom = simpleroom.getSuiteroom();
            if (suiteroom != null) {
                suiteroom = em.getReference(suiteroom.getClass(), suiteroom.getIdRoom());
                simpleroom.setSuiteroom(suiteroom);
            }
            em.persist(simpleroom);
            if (idBed != null) {
                idBed.getSimpleroomCollection().add(simpleroom);
                idBed = em.merge(idBed);
            }
            if (rooms != null) {
                rooms.setSimpleroom(simpleroom);
                rooms = em.merge(rooms);
            }
            if (suiteroom != null) {
                Simpleroom oldSimpleroomOfSuiteroom = suiteroom.getSimpleroom();
                if (oldSimpleroomOfSuiteroom != null) {
                    oldSimpleroomOfSuiteroom.setSuiteroom(null);
                    oldSimpleroomOfSuiteroom = em.merge(oldSimpleroomOfSuiteroom);
                }
                suiteroom.setSimpleroom(simpleroom);
                suiteroom = em.merge(suiteroom);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSimpleroom(simpleroom.getIdRoom()) != null) {
                throw new PreexistingEntityException("Simpleroom " + simpleroom + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Simpleroom simpleroom) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Simpleroom persistentSimpleroom = em.find(Simpleroom.class, simpleroom.getIdRoom());
            BedType idBedOld = persistentSimpleroom.getIdBed();
            BedType idBedNew = simpleroom.getIdBed();
            Rooms roomsOld = persistentSimpleroom.getRooms();
            Rooms roomsNew = simpleroom.getRooms();
            Suiteroom suiteroomOld = persistentSimpleroom.getSuiteroom();
            Suiteroom suiteroomNew = simpleroom.getSuiteroom();
            List<String> illegalOrphanMessages = null;
            if (roomsNew != null && !roomsNew.equals(roomsOld)) {
                Simpleroom oldSimpleroomOfRooms = roomsNew.getSimpleroom();
                if (oldSimpleroomOfRooms != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Rooms " + roomsNew + " already has an item of type Simpleroom whose rooms column cannot be null. Please make another selection for the rooms field.");
                }
            }
            if (suiteroomOld != null && !suiteroomOld.equals(suiteroomNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Suiteroom " + suiteroomOld + " since its simpleroom field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idBedNew != null) {
                idBedNew = em.getReference(idBedNew.getClass(), idBedNew.getIdBed());
                simpleroom.setIdBed(idBedNew);
            }
            if (roomsNew != null) {
                roomsNew = em.getReference(roomsNew.getClass(), roomsNew.getIdRoom());
                simpleroom.setRooms(roomsNew);
            }
            if (suiteroomNew != null) {
                suiteroomNew = em.getReference(suiteroomNew.getClass(), suiteroomNew.getIdRoom());
                simpleroom.setSuiteroom(suiteroomNew);
            }
            simpleroom = em.merge(simpleroom);
            if (idBedOld != null && !idBedOld.equals(idBedNew)) {
                idBedOld.getSimpleroomCollection().remove(simpleroom);
                idBedOld = em.merge(idBedOld);
            }
            if (idBedNew != null && !idBedNew.equals(idBedOld)) {
                idBedNew.getSimpleroomCollection().add(simpleroom);
                idBedNew = em.merge(idBedNew);
            }
            if (roomsOld != null && !roomsOld.equals(roomsNew)) {
                roomsOld.setSimpleroom(null);
                roomsOld = em.merge(roomsOld);
            }
            if (roomsNew != null && !roomsNew.equals(roomsOld)) {
                roomsNew.setSimpleroom(simpleroom);
                roomsNew = em.merge(roomsNew);
            }
            if (suiteroomNew != null && !suiteroomNew.equals(suiteroomOld)) {
                Simpleroom oldSimpleroomOfSuiteroom = suiteroomNew.getSimpleroom();
                if (oldSimpleroomOfSuiteroom != null) {
                    oldSimpleroomOfSuiteroom.setSuiteroom(null);
                    oldSimpleroomOfSuiteroom = em.merge(oldSimpleroomOfSuiteroom);
                }
                suiteroomNew.setSimpleroom(simpleroom);
                suiteroomNew = em.merge(suiteroomNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = simpleroom.getIdRoom();
                if (findSimpleroom(id) == null) {
                    throw new NonexistentEntityException("The simpleroom with id " + id + " no longer exists.");
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
            Simpleroom simpleroom;
            try {
                simpleroom = em.getReference(Simpleroom.class, id);
                simpleroom.getIdRoom();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The simpleroom with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Suiteroom suiteroomOrphanCheck = simpleroom.getSuiteroom();
            if (suiteroomOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Simpleroom (" + simpleroom + ") cannot be destroyed since the Suiteroom " + suiteroomOrphanCheck + " in its suiteroom field has a non-nullable simpleroom field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BedType idBed = simpleroom.getIdBed();
            if (idBed != null) {
                idBed.getSimpleroomCollection().remove(simpleroom);
                idBed = em.merge(idBed);
            }
            Rooms rooms = simpleroom.getRooms();
            if (rooms != null) {
                rooms.setSimpleroom(null);
                rooms = em.merge(rooms);
            }
            em.remove(simpleroom);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Simpleroom> findSimpleroomEntities() {
        return findSimpleroomEntities(true, -1, -1);
    }

    public List<Simpleroom> findSimpleroomEntities(int maxResults, int firstResult) {
        return findSimpleroomEntities(false, maxResults, firstResult);
    }

    private List<Simpleroom> findSimpleroomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Simpleroom.class));
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

    public Simpleroom findSimpleroom(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Simpleroom.class, id);
        } finally {
            em.close();
        }
    }

    public int getSimpleroomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Simpleroom> rt = cq.from(Simpleroom.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
