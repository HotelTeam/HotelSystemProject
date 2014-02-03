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
public class ReservationJpaController implements Serializable {

    public ReservationJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reservation reservation) {
        if (reservation.getClientCollection() == null) {
            reservation.setClientCollection(new ArrayList<Client>());
        }
        if (reservation.getSpecificResCollection() == null) {
            reservation.setSpecificResCollection(new ArrayList<SpecificRes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Client> attachedClientCollection = new ArrayList<Client>();
            for (Client clientCollectionClientToAttach : reservation.getClientCollection()) {
                clientCollectionClientToAttach = em.getReference(clientCollectionClientToAttach.getClass(), clientCollectionClientToAttach.getIdClient());
                attachedClientCollection.add(clientCollectionClientToAttach);
            }
            reservation.setClientCollection(attachedClientCollection);
            Collection<SpecificRes> attachedSpecificResCollection = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionSpecificResToAttach : reservation.getSpecificResCollection()) {
                specificResCollectionSpecificResToAttach = em.getReference(specificResCollectionSpecificResToAttach.getClass(), specificResCollectionSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollection.add(specificResCollectionSpecificResToAttach);
            }
            reservation.setSpecificResCollection(attachedSpecificResCollection);
            em.persist(reservation);
            for (Client clientCollectionClient : reservation.getClientCollection()) {
                clientCollectionClient.getReservationCollection().add(reservation);
                clientCollectionClient = em.merge(clientCollectionClient);
            }
            for (SpecificRes specificResCollectionSpecificRes : reservation.getSpecificResCollection()) {
                Reservation oldReservationOfSpecificResCollectionSpecificRes = specificResCollectionSpecificRes.getReservation();
                specificResCollectionSpecificRes.setReservation(reservation);
                specificResCollectionSpecificRes = em.merge(specificResCollectionSpecificRes);
                if (oldReservationOfSpecificResCollectionSpecificRes != null) {
                    oldReservationOfSpecificResCollectionSpecificRes.getSpecificResCollection().remove(specificResCollectionSpecificRes);
                    oldReservationOfSpecificResCollectionSpecificRes = em.merge(oldReservationOfSpecificResCollectionSpecificRes);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reservation reservation) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reservation persistentReservation = em.find(Reservation.class, reservation.getIdRes());
            Collection<Client> clientCollectionOld = persistentReservation.getClientCollection();
            Collection<Client> clientCollectionNew = reservation.getClientCollection();
            Collection<SpecificRes> specificResCollectionOld = persistentReservation.getSpecificResCollection();
            Collection<SpecificRes> specificResCollectionNew = reservation.getSpecificResCollection();
            List<String> illegalOrphanMessages = null;
            for (SpecificRes specificResCollectionOldSpecificRes : specificResCollectionOld) {
                if (!specificResCollectionNew.contains(specificResCollectionOldSpecificRes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SpecificRes " + specificResCollectionOldSpecificRes + " since its reservation field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Client> attachedClientCollectionNew = new ArrayList<Client>();
            for (Client clientCollectionNewClientToAttach : clientCollectionNew) {
                clientCollectionNewClientToAttach = em.getReference(clientCollectionNewClientToAttach.getClass(), clientCollectionNewClientToAttach.getIdClient());
                attachedClientCollectionNew.add(clientCollectionNewClientToAttach);
            }
            clientCollectionNew = attachedClientCollectionNew;
            reservation.setClientCollection(clientCollectionNew);
            Collection<SpecificRes> attachedSpecificResCollectionNew = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionNewSpecificResToAttach : specificResCollectionNew) {
                specificResCollectionNewSpecificResToAttach = em.getReference(specificResCollectionNewSpecificResToAttach.getClass(), specificResCollectionNewSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollectionNew.add(specificResCollectionNewSpecificResToAttach);
            }
            specificResCollectionNew = attachedSpecificResCollectionNew;
            reservation.setSpecificResCollection(specificResCollectionNew);
            reservation = em.merge(reservation);
            for (Client clientCollectionOldClient : clientCollectionOld) {
                if (!clientCollectionNew.contains(clientCollectionOldClient)) {
                    clientCollectionOldClient.getReservationCollection().remove(reservation);
                    clientCollectionOldClient = em.merge(clientCollectionOldClient);
                }
            }
            for (Client clientCollectionNewClient : clientCollectionNew) {
                if (!clientCollectionOld.contains(clientCollectionNewClient)) {
                    clientCollectionNewClient.getReservationCollection().add(reservation);
                    clientCollectionNewClient = em.merge(clientCollectionNewClient);
                }
            }
            for (SpecificRes specificResCollectionNewSpecificRes : specificResCollectionNew) {
                if (!specificResCollectionOld.contains(specificResCollectionNewSpecificRes)) {
                    Reservation oldReservationOfSpecificResCollectionNewSpecificRes = specificResCollectionNewSpecificRes.getReservation();
                    specificResCollectionNewSpecificRes.setReservation(reservation);
                    specificResCollectionNewSpecificRes = em.merge(specificResCollectionNewSpecificRes);
                    if (oldReservationOfSpecificResCollectionNewSpecificRes != null && !oldReservationOfSpecificResCollectionNewSpecificRes.equals(reservation)) {
                        oldReservationOfSpecificResCollectionNewSpecificRes.getSpecificResCollection().remove(specificResCollectionNewSpecificRes);
                        oldReservationOfSpecificResCollectionNewSpecificRes = em.merge(oldReservationOfSpecificResCollectionNewSpecificRes);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reservation.getIdRes();
                if (findReservation(id) == null) {
                    throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.");
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
            Reservation reservation;
            try {
                reservation = em.getReference(Reservation.class, id);
                reservation.getIdRes();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reservation with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SpecificRes> specificResCollectionOrphanCheck = reservation.getSpecificResCollection();
            for (SpecificRes specificResCollectionOrphanCheckSpecificRes : specificResCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reservation (" + reservation + ") cannot be destroyed since the SpecificRes " + specificResCollectionOrphanCheckSpecificRes + " in its specificResCollection field has a non-nullable reservation field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Client> clientCollection = reservation.getClientCollection();
            for (Client clientCollectionClient : clientCollection) {
                clientCollectionClient.getReservationCollection().remove(reservation);
                clientCollectionClient = em.merge(clientCollectionClient);
            }
            em.remove(reservation);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reservation> findReservationEntities() {
        return findReservationEntities(true, -1, -1);
    }

    public List<Reservation> findReservationEntities(int maxResults, int firstResult) {
        return findReservationEntities(false, maxResults, firstResult);
    }

    private List<Reservation> findReservationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reservation.class));
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

    public Reservation findReservation(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reservation.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reservation> rt = cq.from(Reservation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
