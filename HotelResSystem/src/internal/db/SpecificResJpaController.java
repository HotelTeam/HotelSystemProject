/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import internal.db.exceptions.NonexistentEntityException;
import internal.db.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author elit
 */
public class SpecificResJpaController implements Serializable {

    public SpecificResJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SpecificRes specificRes) throws PreexistingEntityException, Exception {
        if (specificRes.getSpecificResPK() == null) {
            specificRes.setSpecificResPK(new SpecificResPK());
        }
        specificRes.getSpecificResPK().setIdRoom(specificRes.getRooms().getIdRoom());
        specificRes.getSpecificResPK().setIdRes(specificRes.getReservation().getIdRes());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Year idYear = specificRes.getIdYear();
            if (idYear != null) {
                idYear = em.getReference(idYear.getClass(), idYear.getIdYear());
                specificRes.setIdYear(idYear);
            }
            Month idMonth = specificRes.getIdMonth();
            if (idMonth != null) {
                idMonth = em.getReference(idMonth.getClass(), idMonth.getIdMonth());
                specificRes.setIdMonth(idMonth);
            }
            Reservation reservation = specificRes.getReservation();
            if (reservation != null) {
                reservation = em.getReference(reservation.getClass(), reservation.getIdRes());
                specificRes.setReservation(reservation);
            }
            Rooms rooms = specificRes.getRooms();
            if (rooms != null) {
                rooms = em.getReference(rooms.getClass(), rooms.getIdRoom());
                specificRes.setRooms(rooms);
            }
            em.persist(specificRes);
            if (idYear != null) {
                idYear.getSpecificResCollection().add(specificRes);
                idYear = em.merge(idYear);
            }
            if (idMonth != null) {
                idMonth.getSpecificResCollection().add(specificRes);
                idMonth = em.merge(idMonth);
            }
            if (reservation != null) {
                reservation.getSpecificResCollection().add(specificRes);
                reservation = em.merge(reservation);
            }
            if (rooms != null) {
                rooms.getSpecificResCollection().add(specificRes);
                rooms = em.merge(rooms);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSpecificRes(specificRes.getSpecificResPK()) != null) {
                throw new PreexistingEntityException("SpecificRes " + specificRes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SpecificRes specificRes) throws NonexistentEntityException, Exception {
        specificRes.getSpecificResPK().setIdRoom(specificRes.getRooms().getIdRoom());
        specificRes.getSpecificResPK().setIdRes(specificRes.getReservation().getIdRes());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SpecificRes persistentSpecificRes = em.find(SpecificRes.class, specificRes.getSpecificResPK());
            Year idYearOld = persistentSpecificRes.getIdYear();
            Year idYearNew = specificRes.getIdYear();
            Month idMonthOld = persistentSpecificRes.getIdMonth();
            Month idMonthNew = specificRes.getIdMonth();
            Reservation reservationOld = persistentSpecificRes.getReservation();
            Reservation reservationNew = specificRes.getReservation();
            Rooms roomsOld = persistentSpecificRes.getRooms();
            Rooms roomsNew = specificRes.getRooms();
            if (idYearNew != null) {
                idYearNew = em.getReference(idYearNew.getClass(), idYearNew.getIdYear());
                specificRes.setIdYear(idYearNew);
            }
            if (idMonthNew != null) {
                idMonthNew = em.getReference(idMonthNew.getClass(), idMonthNew.getIdMonth());
                specificRes.setIdMonth(idMonthNew);
            }
            if (reservationNew != null) {
                reservationNew = em.getReference(reservationNew.getClass(), reservationNew.getIdRes());
                specificRes.setReservation(reservationNew);
            }
            if (roomsNew != null) {
                roomsNew = em.getReference(roomsNew.getClass(), roomsNew.getIdRoom());
                specificRes.setRooms(roomsNew);
            }
            specificRes = em.merge(specificRes);
            if (idYearOld != null && !idYearOld.equals(idYearNew)) {
                idYearOld.getSpecificResCollection().remove(specificRes);
                idYearOld = em.merge(idYearOld);
            }
            if (idYearNew != null && !idYearNew.equals(idYearOld)) {
                idYearNew.getSpecificResCollection().add(specificRes);
                idYearNew = em.merge(idYearNew);
            }
            if (idMonthOld != null && !idMonthOld.equals(idMonthNew)) {
                idMonthOld.getSpecificResCollection().remove(specificRes);
                idMonthOld = em.merge(idMonthOld);
            }
            if (idMonthNew != null && !idMonthNew.equals(idMonthOld)) {
                idMonthNew.getSpecificResCollection().add(specificRes);
                idMonthNew = em.merge(idMonthNew);
            }
            if (reservationOld != null && !reservationOld.equals(reservationNew)) {
                reservationOld.getSpecificResCollection().remove(specificRes);
                reservationOld = em.merge(reservationOld);
            }
            if (reservationNew != null && !reservationNew.equals(reservationOld)) {
                reservationNew.getSpecificResCollection().add(specificRes);
                reservationNew = em.merge(reservationNew);
            }
            if (roomsOld != null && !roomsOld.equals(roomsNew)) {
                roomsOld.getSpecificResCollection().remove(specificRes);
                roomsOld = em.merge(roomsOld);
            }
            if (roomsNew != null && !roomsNew.equals(roomsOld)) {
                roomsNew.getSpecificResCollection().add(specificRes);
                roomsNew = em.merge(roomsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SpecificResPK id = specificRes.getSpecificResPK();
                if (findSpecificRes(id) == null) {
                    throw new NonexistentEntityException("The specificRes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SpecificResPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SpecificRes specificRes;
            try {
                specificRes = em.getReference(SpecificRes.class, id);
                specificRes.getSpecificResPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The specificRes with id " + id + " no longer exists.", enfe);
            }
            Year idYear = specificRes.getIdYear();
            if (idYear != null) {
                idYear.getSpecificResCollection().remove(specificRes);
                idYear = em.merge(idYear);
            }
            Month idMonth = specificRes.getIdMonth();
            if (idMonth != null) {
                idMonth.getSpecificResCollection().remove(specificRes);
                idMonth = em.merge(idMonth);
            }
            Reservation reservation = specificRes.getReservation();
            if (reservation != null) {
                reservation.getSpecificResCollection().remove(specificRes);
                reservation = em.merge(reservation);
            }
            Rooms rooms = specificRes.getRooms();
            if (rooms != null) {
                rooms.getSpecificResCollection().remove(specificRes);
                rooms = em.merge(rooms);
            }
            em.remove(specificRes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SpecificRes> findSpecificResEntities() {
        return findSpecificResEntities(true, -1, -1);
    }

    public List<SpecificRes> findSpecificResEntities(int maxResults, int firstResult) {
        return findSpecificResEntities(false, maxResults, firstResult);
    }

    private List<SpecificRes> findSpecificResEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SpecificRes.class));
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

    public SpecificRes findSpecificRes(SpecificResPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SpecificRes.class, id);
        } finally {
            em.close();
        }
    }

    public int getSpecificResCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SpecificRes> rt = cq.from(SpecificRes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
