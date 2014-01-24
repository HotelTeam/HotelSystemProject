/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

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
public class BedTypeJpaController implements Serializable {

    public BedTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BedType bedType) {
        if (bedType.getSimpleroomCollection() == null) {
            bedType.setSimpleroomCollection(new ArrayList<Simpleroom>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Simpleroom> attachedSimpleroomCollection = new ArrayList<Simpleroom>();
            for (Simpleroom simpleroomCollectionSimpleroomToAttach : bedType.getSimpleroomCollection()) {
                simpleroomCollectionSimpleroomToAttach = em.getReference(simpleroomCollectionSimpleroomToAttach.getClass(), simpleroomCollectionSimpleroomToAttach.getIdRoom());
                attachedSimpleroomCollection.add(simpleroomCollectionSimpleroomToAttach);
            }
            bedType.setSimpleroomCollection(attachedSimpleroomCollection);
            em.persist(bedType);
            for (Simpleroom simpleroomCollectionSimpleroom : bedType.getSimpleroomCollection()) {
                BedType oldIdBedOfSimpleroomCollectionSimpleroom = simpleroomCollectionSimpleroom.getIdBed();
                simpleroomCollectionSimpleroom.setIdBed(bedType);
                simpleroomCollectionSimpleroom = em.merge(simpleroomCollectionSimpleroom);
                if (oldIdBedOfSimpleroomCollectionSimpleroom != null) {
                    oldIdBedOfSimpleroomCollectionSimpleroom.getSimpleroomCollection().remove(simpleroomCollectionSimpleroom);
                    oldIdBedOfSimpleroomCollectionSimpleroom = em.merge(oldIdBedOfSimpleroomCollectionSimpleroom);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BedType bedType) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BedType persistentBedType = em.find(BedType.class, bedType.getIdBed());
            Collection<Simpleroom> simpleroomCollectionOld = persistentBedType.getSimpleroomCollection();
            Collection<Simpleroom> simpleroomCollectionNew = bedType.getSimpleroomCollection();
            Collection<Simpleroom> attachedSimpleroomCollectionNew = new ArrayList<Simpleroom>();
            for (Simpleroom simpleroomCollectionNewSimpleroomToAttach : simpleroomCollectionNew) {
                simpleroomCollectionNewSimpleroomToAttach = em.getReference(simpleroomCollectionNewSimpleroomToAttach.getClass(), simpleroomCollectionNewSimpleroomToAttach.getIdRoom());
                attachedSimpleroomCollectionNew.add(simpleroomCollectionNewSimpleroomToAttach);
            }
            simpleroomCollectionNew = attachedSimpleroomCollectionNew;
            bedType.setSimpleroomCollection(simpleroomCollectionNew);
            bedType = em.merge(bedType);
            for (Simpleroom simpleroomCollectionOldSimpleroom : simpleroomCollectionOld) {
                if (!simpleroomCollectionNew.contains(simpleroomCollectionOldSimpleroom)) {
                    simpleroomCollectionOldSimpleroom.setIdBed(null);
                    simpleroomCollectionOldSimpleroom = em.merge(simpleroomCollectionOldSimpleroom);
                }
            }
            for (Simpleroom simpleroomCollectionNewSimpleroom : simpleroomCollectionNew) {
                if (!simpleroomCollectionOld.contains(simpleroomCollectionNewSimpleroom)) {
                    BedType oldIdBedOfSimpleroomCollectionNewSimpleroom = simpleroomCollectionNewSimpleroom.getIdBed();
                    simpleroomCollectionNewSimpleroom.setIdBed(bedType);
                    simpleroomCollectionNewSimpleroom = em.merge(simpleroomCollectionNewSimpleroom);
                    if (oldIdBedOfSimpleroomCollectionNewSimpleroom != null && !oldIdBedOfSimpleroomCollectionNewSimpleroom.equals(bedType)) {
                        oldIdBedOfSimpleroomCollectionNewSimpleroom.getSimpleroomCollection().remove(simpleroomCollectionNewSimpleroom);
                        oldIdBedOfSimpleroomCollectionNewSimpleroom = em.merge(oldIdBedOfSimpleroomCollectionNewSimpleroom);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bedType.getIdBed();
                if (findBedType(id) == null) {
                    throw new NonexistentEntityException("The bedType with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BedType bedType;
            try {
                bedType = em.getReference(BedType.class, id);
                bedType.getIdBed();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bedType with id " + id + " no longer exists.", enfe);
            }
            Collection<Simpleroom> simpleroomCollection = bedType.getSimpleroomCollection();
            for (Simpleroom simpleroomCollectionSimpleroom : simpleroomCollection) {
                simpleroomCollectionSimpleroom.setIdBed(null);
                simpleroomCollectionSimpleroom = em.merge(simpleroomCollectionSimpleroom);
            }
            em.remove(bedType);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BedType> findBedTypeEntities() {
        return findBedTypeEntities(true, -1, -1);
    }

    public List<BedType> findBedTypeEntities(int maxResults, int firstResult) {
        return findBedTypeEntities(false, maxResults, firstResult);
    }

    private List<BedType> findBedTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BedType.class));
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

    public BedType findBedType(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BedType.class, id);
        } finally {
            em.close();
        }
    }

    public int getBedTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BedType> rt = cq.from(BedType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
