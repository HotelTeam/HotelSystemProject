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
public class SuiteroomJpaController implements Serializable {

    public SuiteroomJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Suiteroom suiteroom) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Simpleroom simpleroomOrphanCheck = suiteroom.getSimpleroom();
        if (simpleroomOrphanCheck != null) {
            Suiteroom oldSuiteroomOfSimpleroom = simpleroomOrphanCheck.getSuiteroom();
            if (oldSuiteroomOfSimpleroom != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Simpleroom " + simpleroomOrphanCheck + " already has an item of type Suiteroom whose simpleroom column cannot be null. Please make another selection for the simpleroom field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Simpleroom simpleroom = suiteroom.getSimpleroom();
            if (simpleroom != null) {
                simpleroom = em.getReference(simpleroom.getClass(), simpleroom.getIdRoom());
                suiteroom.setSimpleroom(simpleroom);
            }
            em.persist(suiteroom);
            if (simpleroom != null) {
                simpleroom.setSuiteroom(suiteroom);
                simpleroom = em.merge(simpleroom);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSuiteroom(suiteroom.getIdRoom()) != null) {
                throw new PreexistingEntityException("Suiteroom " + suiteroom + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Suiteroom suiteroom) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Suiteroom persistentSuiteroom = em.find(Suiteroom.class, suiteroom.getIdRoom());
            Simpleroom simpleroomOld = persistentSuiteroom.getSimpleroom();
            Simpleroom simpleroomNew = suiteroom.getSimpleroom();
            List<String> illegalOrphanMessages = null;
            if (simpleroomNew != null && !simpleroomNew.equals(simpleroomOld)) {
                Suiteroom oldSuiteroomOfSimpleroom = simpleroomNew.getSuiteroom();
                if (oldSuiteroomOfSimpleroom != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Simpleroom " + simpleroomNew + " already has an item of type Suiteroom whose simpleroom column cannot be null. Please make another selection for the simpleroom field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (simpleroomNew != null) {
                simpleroomNew = em.getReference(simpleroomNew.getClass(), simpleroomNew.getIdRoom());
                suiteroom.setSimpleroom(simpleroomNew);
            }
            suiteroom = em.merge(suiteroom);
            if (simpleroomOld != null && !simpleroomOld.equals(simpleroomNew)) {
                simpleroomOld.setSuiteroom(null);
                simpleroomOld = em.merge(simpleroomOld);
            }
            if (simpleroomNew != null && !simpleroomNew.equals(simpleroomOld)) {
                simpleroomNew.setSuiteroom(suiteroom);
                simpleroomNew = em.merge(simpleroomNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = suiteroom.getIdRoom();
                if (findSuiteroom(id) == null) {
                    throw new NonexistentEntityException("The suiteroom with id " + id + " no longer exists.");
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
            Suiteroom suiteroom;
            try {
                suiteroom = em.getReference(Suiteroom.class, id);
                suiteroom.getIdRoom();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The suiteroom with id " + id + " no longer exists.", enfe);
            }
            Simpleroom simpleroom = suiteroom.getSimpleroom();
            if (simpleroom != null) {
                simpleroom.setSuiteroom(null);
                simpleroom = em.merge(simpleroom);
            }
            em.remove(suiteroom);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Suiteroom> findSuiteroomEntities() {
        return findSuiteroomEntities(true, -1, -1);
    }

    public List<Suiteroom> findSuiteroomEntities(int maxResults, int firstResult) {
        return findSuiteroomEntities(false, maxResults, firstResult);
    }

    private List<Suiteroom> findSuiteroomEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Suiteroom.class));
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

    public Suiteroom findSuiteroom(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Suiteroom.class, id);
        } finally {
            em.close();
        }
    }

    public int getSuiteroomCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Suiteroom> rt = cq.from(Suiteroom.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
