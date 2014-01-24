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
public class MonthJpaController implements Serializable {

    public MonthJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Month month) {
        if (month.getSpecificResCollection() == null) {
            month.setSpecificResCollection(new ArrayList<SpecificRes>());
        }
        if (month.getAnalysisCollection() == null) {
            month.setAnalysisCollection(new ArrayList<Analysis>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SpecificRes> attachedSpecificResCollection = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionSpecificResToAttach : month.getSpecificResCollection()) {
                specificResCollectionSpecificResToAttach = em.getReference(specificResCollectionSpecificResToAttach.getClass(), specificResCollectionSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollection.add(specificResCollectionSpecificResToAttach);
            }
            month.setSpecificResCollection(attachedSpecificResCollection);
            Collection<Analysis> attachedAnalysisCollection = new ArrayList<Analysis>();
            for (Analysis analysisCollectionAnalysisToAttach : month.getAnalysisCollection()) {
                analysisCollectionAnalysisToAttach = em.getReference(analysisCollectionAnalysisToAttach.getClass(), analysisCollectionAnalysisToAttach.getIdAn());
                attachedAnalysisCollection.add(analysisCollectionAnalysisToAttach);
            }
            month.setAnalysisCollection(attachedAnalysisCollection);
            em.persist(month);
            for (SpecificRes specificResCollectionSpecificRes : month.getSpecificResCollection()) {
                Month oldIdMonthOfSpecificResCollectionSpecificRes = specificResCollectionSpecificRes.getIdMonth();
                specificResCollectionSpecificRes.setIdMonth(month);
                specificResCollectionSpecificRes = em.merge(specificResCollectionSpecificRes);
                if (oldIdMonthOfSpecificResCollectionSpecificRes != null) {
                    oldIdMonthOfSpecificResCollectionSpecificRes.getSpecificResCollection().remove(specificResCollectionSpecificRes);
                    oldIdMonthOfSpecificResCollectionSpecificRes = em.merge(oldIdMonthOfSpecificResCollectionSpecificRes);
                }
            }
            for (Analysis analysisCollectionAnalysis : month.getAnalysisCollection()) {
                Month oldIdMonthOfAnalysisCollectionAnalysis = analysisCollectionAnalysis.getIdMonth();
                analysisCollectionAnalysis.setIdMonth(month);
                analysisCollectionAnalysis = em.merge(analysisCollectionAnalysis);
                if (oldIdMonthOfAnalysisCollectionAnalysis != null) {
                    oldIdMonthOfAnalysisCollectionAnalysis.getAnalysisCollection().remove(analysisCollectionAnalysis);
                    oldIdMonthOfAnalysisCollectionAnalysis = em.merge(oldIdMonthOfAnalysisCollectionAnalysis);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Month month) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Month persistentMonth = em.find(Month.class, month.getIdMonth());
            Collection<SpecificRes> specificResCollectionOld = persistentMonth.getSpecificResCollection();
            Collection<SpecificRes> specificResCollectionNew = month.getSpecificResCollection();
            Collection<Analysis> analysisCollectionOld = persistentMonth.getAnalysisCollection();
            Collection<Analysis> analysisCollectionNew = month.getAnalysisCollection();
            Collection<SpecificRes> attachedSpecificResCollectionNew = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionNewSpecificResToAttach : specificResCollectionNew) {
                specificResCollectionNewSpecificResToAttach = em.getReference(specificResCollectionNewSpecificResToAttach.getClass(), specificResCollectionNewSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollectionNew.add(specificResCollectionNewSpecificResToAttach);
            }
            specificResCollectionNew = attachedSpecificResCollectionNew;
            month.setSpecificResCollection(specificResCollectionNew);
            Collection<Analysis> attachedAnalysisCollectionNew = new ArrayList<Analysis>();
            for (Analysis analysisCollectionNewAnalysisToAttach : analysisCollectionNew) {
                analysisCollectionNewAnalysisToAttach = em.getReference(analysisCollectionNewAnalysisToAttach.getClass(), analysisCollectionNewAnalysisToAttach.getIdAn());
                attachedAnalysisCollectionNew.add(analysisCollectionNewAnalysisToAttach);
            }
            analysisCollectionNew = attachedAnalysisCollectionNew;
            month.setAnalysisCollection(analysisCollectionNew);
            month = em.merge(month);
            for (SpecificRes specificResCollectionOldSpecificRes : specificResCollectionOld) {
                if (!specificResCollectionNew.contains(specificResCollectionOldSpecificRes)) {
                    specificResCollectionOldSpecificRes.setIdMonth(null);
                    specificResCollectionOldSpecificRes = em.merge(specificResCollectionOldSpecificRes);
                }
            }
            for (SpecificRes specificResCollectionNewSpecificRes : specificResCollectionNew) {
                if (!specificResCollectionOld.contains(specificResCollectionNewSpecificRes)) {
                    Month oldIdMonthOfSpecificResCollectionNewSpecificRes = specificResCollectionNewSpecificRes.getIdMonth();
                    specificResCollectionNewSpecificRes.setIdMonth(month);
                    specificResCollectionNewSpecificRes = em.merge(specificResCollectionNewSpecificRes);
                    if (oldIdMonthOfSpecificResCollectionNewSpecificRes != null && !oldIdMonthOfSpecificResCollectionNewSpecificRes.equals(month)) {
                        oldIdMonthOfSpecificResCollectionNewSpecificRes.getSpecificResCollection().remove(specificResCollectionNewSpecificRes);
                        oldIdMonthOfSpecificResCollectionNewSpecificRes = em.merge(oldIdMonthOfSpecificResCollectionNewSpecificRes);
                    }
                }
            }
            for (Analysis analysisCollectionOldAnalysis : analysisCollectionOld) {
                if (!analysisCollectionNew.contains(analysisCollectionOldAnalysis)) {
                    analysisCollectionOldAnalysis.setIdMonth(null);
                    analysisCollectionOldAnalysis = em.merge(analysisCollectionOldAnalysis);
                }
            }
            for (Analysis analysisCollectionNewAnalysis : analysisCollectionNew) {
                if (!analysisCollectionOld.contains(analysisCollectionNewAnalysis)) {
                    Month oldIdMonthOfAnalysisCollectionNewAnalysis = analysisCollectionNewAnalysis.getIdMonth();
                    analysisCollectionNewAnalysis.setIdMonth(month);
                    analysisCollectionNewAnalysis = em.merge(analysisCollectionNewAnalysis);
                    if (oldIdMonthOfAnalysisCollectionNewAnalysis != null && !oldIdMonthOfAnalysisCollectionNewAnalysis.equals(month)) {
                        oldIdMonthOfAnalysisCollectionNewAnalysis.getAnalysisCollection().remove(analysisCollectionNewAnalysis);
                        oldIdMonthOfAnalysisCollectionNewAnalysis = em.merge(oldIdMonthOfAnalysisCollectionNewAnalysis);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = month.getIdMonth();
                if (findMonth(id) == null) {
                    throw new NonexistentEntityException("The month with id " + id + " no longer exists.");
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
            Month month;
            try {
                month = em.getReference(Month.class, id);
                month.getIdMonth();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The month with id " + id + " no longer exists.", enfe);
            }
            Collection<SpecificRes> specificResCollection = month.getSpecificResCollection();
            for (SpecificRes specificResCollectionSpecificRes : specificResCollection) {
                specificResCollectionSpecificRes.setIdMonth(null);
                specificResCollectionSpecificRes = em.merge(specificResCollectionSpecificRes);
            }
            Collection<Analysis> analysisCollection = month.getAnalysisCollection();
            for (Analysis analysisCollectionAnalysis : analysisCollection) {
                analysisCollectionAnalysis.setIdMonth(null);
                analysisCollectionAnalysis = em.merge(analysisCollectionAnalysis);
            }
            em.remove(month);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Month> findMonthEntities() {
        return findMonthEntities(true, -1, -1);
    }

    public List<Month> findMonthEntities(int maxResults, int firstResult) {
        return findMonthEntities(false, maxResults, firstResult);
    }

    private List<Month> findMonthEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Month.class));
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

    public Month findMonth(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Month.class, id);
        } finally {
            em.close();
        }
    }

    public int getMonthCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Month> rt = cq.from(Month.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
