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
public class YearJpaController implements Serializable {

    public YearJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Year year) {
        if (year.getSpecificResCollection() == null) {
            year.setSpecificResCollection(new ArrayList<SpecificRes>());
        }
        if (year.getAnalysisCollection() == null) {
            year.setAnalysisCollection(new ArrayList<Analysis>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<SpecificRes> attachedSpecificResCollection = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionSpecificResToAttach : year.getSpecificResCollection()) {
                specificResCollectionSpecificResToAttach = em.getReference(specificResCollectionSpecificResToAttach.getClass(), specificResCollectionSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollection.add(specificResCollectionSpecificResToAttach);
            }
            year.setSpecificResCollection(attachedSpecificResCollection);
            Collection<Analysis> attachedAnalysisCollection = new ArrayList<Analysis>();
            for (Analysis analysisCollectionAnalysisToAttach : year.getAnalysisCollection()) {
                analysisCollectionAnalysisToAttach = em.getReference(analysisCollectionAnalysisToAttach.getClass(), analysisCollectionAnalysisToAttach.getIdAn());
                attachedAnalysisCollection.add(analysisCollectionAnalysisToAttach);
            }
            year.setAnalysisCollection(attachedAnalysisCollection);
            em.persist(year);
            for (SpecificRes specificResCollectionSpecificRes : year.getSpecificResCollection()) {
                Year oldIdYearOfSpecificResCollectionSpecificRes = specificResCollectionSpecificRes.getIdYear();
                specificResCollectionSpecificRes.setIdYear(year);
                specificResCollectionSpecificRes = em.merge(specificResCollectionSpecificRes);
                if (oldIdYearOfSpecificResCollectionSpecificRes != null) {
                    oldIdYearOfSpecificResCollectionSpecificRes.getSpecificResCollection().remove(specificResCollectionSpecificRes);
                    oldIdYearOfSpecificResCollectionSpecificRes = em.merge(oldIdYearOfSpecificResCollectionSpecificRes);
                }
            }
            for (Analysis analysisCollectionAnalysis : year.getAnalysisCollection()) {
                Year oldIdYearOfAnalysisCollectionAnalysis = analysisCollectionAnalysis.getIdYear();
                analysisCollectionAnalysis.setIdYear(year);
                analysisCollectionAnalysis = em.merge(analysisCollectionAnalysis);
                if (oldIdYearOfAnalysisCollectionAnalysis != null) {
                    oldIdYearOfAnalysisCollectionAnalysis.getAnalysisCollection().remove(analysisCollectionAnalysis);
                    oldIdYearOfAnalysisCollectionAnalysis = em.merge(oldIdYearOfAnalysisCollectionAnalysis);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Year year) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Year persistentYear = em.find(Year.class, year.getIdYear());
            Collection<SpecificRes> specificResCollectionOld = persistentYear.getSpecificResCollection();
            Collection<SpecificRes> specificResCollectionNew = year.getSpecificResCollection();
            Collection<Analysis> analysisCollectionOld = persistentYear.getAnalysisCollection();
            Collection<Analysis> analysisCollectionNew = year.getAnalysisCollection();
            Collection<SpecificRes> attachedSpecificResCollectionNew = new ArrayList<SpecificRes>();
            for (SpecificRes specificResCollectionNewSpecificResToAttach : specificResCollectionNew) {
                specificResCollectionNewSpecificResToAttach = em.getReference(specificResCollectionNewSpecificResToAttach.getClass(), specificResCollectionNewSpecificResToAttach.getSpecificResPK());
                attachedSpecificResCollectionNew.add(specificResCollectionNewSpecificResToAttach);
            }
            specificResCollectionNew = attachedSpecificResCollectionNew;
            year.setSpecificResCollection(specificResCollectionNew);
            Collection<Analysis> attachedAnalysisCollectionNew = new ArrayList<Analysis>();
            for (Analysis analysisCollectionNewAnalysisToAttach : analysisCollectionNew) {
                analysisCollectionNewAnalysisToAttach = em.getReference(analysisCollectionNewAnalysisToAttach.getClass(), analysisCollectionNewAnalysisToAttach.getIdAn());
                attachedAnalysisCollectionNew.add(analysisCollectionNewAnalysisToAttach);
            }
            analysisCollectionNew = attachedAnalysisCollectionNew;
            year.setAnalysisCollection(analysisCollectionNew);
            year = em.merge(year);
            for (SpecificRes specificResCollectionOldSpecificRes : specificResCollectionOld) {
                if (!specificResCollectionNew.contains(specificResCollectionOldSpecificRes)) {
                    specificResCollectionOldSpecificRes.setIdYear(null);
                    specificResCollectionOldSpecificRes = em.merge(specificResCollectionOldSpecificRes);
                }
            }
            for (SpecificRes specificResCollectionNewSpecificRes : specificResCollectionNew) {
                if (!specificResCollectionOld.contains(specificResCollectionNewSpecificRes)) {
                    Year oldIdYearOfSpecificResCollectionNewSpecificRes = specificResCollectionNewSpecificRes.getIdYear();
                    specificResCollectionNewSpecificRes.setIdYear(year);
                    specificResCollectionNewSpecificRes = em.merge(specificResCollectionNewSpecificRes);
                    if (oldIdYearOfSpecificResCollectionNewSpecificRes != null && !oldIdYearOfSpecificResCollectionNewSpecificRes.equals(year)) {
                        oldIdYearOfSpecificResCollectionNewSpecificRes.getSpecificResCollection().remove(specificResCollectionNewSpecificRes);
                        oldIdYearOfSpecificResCollectionNewSpecificRes = em.merge(oldIdYearOfSpecificResCollectionNewSpecificRes);
                    }
                }
            }
            for (Analysis analysisCollectionOldAnalysis : analysisCollectionOld) {
                if (!analysisCollectionNew.contains(analysisCollectionOldAnalysis)) {
                    analysisCollectionOldAnalysis.setIdYear(null);
                    analysisCollectionOldAnalysis = em.merge(analysisCollectionOldAnalysis);
                }
            }
            for (Analysis analysisCollectionNewAnalysis : analysisCollectionNew) {
                if (!analysisCollectionOld.contains(analysisCollectionNewAnalysis)) {
                    Year oldIdYearOfAnalysisCollectionNewAnalysis = analysisCollectionNewAnalysis.getIdYear();
                    analysisCollectionNewAnalysis.setIdYear(year);
                    analysisCollectionNewAnalysis = em.merge(analysisCollectionNewAnalysis);
                    if (oldIdYearOfAnalysisCollectionNewAnalysis != null && !oldIdYearOfAnalysisCollectionNewAnalysis.equals(year)) {
                        oldIdYearOfAnalysisCollectionNewAnalysis.getAnalysisCollection().remove(analysisCollectionNewAnalysis);
                        oldIdYearOfAnalysisCollectionNewAnalysis = em.merge(oldIdYearOfAnalysisCollectionNewAnalysis);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = year.getIdYear();
                if (findYear(id) == null) {
                    throw new NonexistentEntityException("The year with id " + id + " no longer exists.");
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
            Year year;
            try {
                year = em.getReference(Year.class, id);
                year.getIdYear();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The year with id " + id + " no longer exists.", enfe);
            }
            Collection<SpecificRes> specificResCollection = year.getSpecificResCollection();
            for (SpecificRes specificResCollectionSpecificRes : specificResCollection) {
                specificResCollectionSpecificRes.setIdYear(null);
                specificResCollectionSpecificRes = em.merge(specificResCollectionSpecificRes);
            }
            Collection<Analysis> analysisCollection = year.getAnalysisCollection();
            for (Analysis analysisCollectionAnalysis : analysisCollection) {
                analysisCollectionAnalysis.setIdYear(null);
                analysisCollectionAnalysis = em.merge(analysisCollectionAnalysis);
            }
            em.remove(year);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Year> findYearEntities() {
        return findYearEntities(true, -1, -1);
    }

    public List<Year> findYearEntities(int maxResults, int firstResult) {
        return findYearEntities(false, maxResults, firstResult);
    }

    private List<Year> findYearEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Year.class));
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

    public Year findYear(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Year.class, id);
        } finally {
            em.close();
        }
    }

    public int getYearCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Year> rt = cq.from(Year.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
