/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package internal.db;

import internal.db.exceptions.NonexistentEntityException;
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
public class AnalysisJpaController implements Serializable {

    public AnalysisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Analysis analysis) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Year idYear = analysis.getIdYear();
            if (idYear != null) {
                idYear = em.getReference(idYear.getClass(), idYear.getIdYear());
                analysis.setIdYear(idYear);
            }
            Month idMonth = analysis.getIdMonth();
            if (idMonth != null) {
                idMonth = em.getReference(idMonth.getClass(), idMonth.getIdMonth());
                analysis.setIdMonth(idMonth);
            }
            em.persist(analysis);
            if (idYear != null) {
                idYear.getAnalysisCollection().add(analysis);
                idYear = em.merge(idYear);
            }
            if (idMonth != null) {
                idMonth.getAnalysisCollection().add(analysis);
                idMonth = em.merge(idMonth);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Analysis analysis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Analysis persistentAnalysis = em.find(Analysis.class, analysis.getIdAn());
            Year idYearOld = persistentAnalysis.getIdYear();
            Year idYearNew = analysis.getIdYear();
            Month idMonthOld = persistentAnalysis.getIdMonth();
            Month idMonthNew = analysis.getIdMonth();
            if (idYearNew != null) {
                idYearNew = em.getReference(idYearNew.getClass(), idYearNew.getIdYear());
                analysis.setIdYear(idYearNew);
            }
            if (idMonthNew != null) {
                idMonthNew = em.getReference(idMonthNew.getClass(), idMonthNew.getIdMonth());
                analysis.setIdMonth(idMonthNew);
            }
            analysis = em.merge(analysis);
            if (idYearOld != null && !idYearOld.equals(idYearNew)) {
                idYearOld.getAnalysisCollection().remove(analysis);
                idYearOld = em.merge(idYearOld);
            }
            if (idYearNew != null && !idYearNew.equals(idYearOld)) {
                idYearNew.getAnalysisCollection().add(analysis);
                idYearNew = em.merge(idYearNew);
            }
            if (idMonthOld != null && !idMonthOld.equals(idMonthNew)) {
                idMonthOld.getAnalysisCollection().remove(analysis);
                idMonthOld = em.merge(idMonthOld);
            }
            if (idMonthNew != null && !idMonthNew.equals(idMonthOld)) {
                idMonthNew.getAnalysisCollection().add(analysis);
                idMonthNew = em.merge(idMonthNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = analysis.getIdAn();
                if (findAnalysis(id) == null) {
                    throw new NonexistentEntityException("The analysis with id " + id + " no longer exists.");
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
            Analysis analysis;
            try {
                analysis = em.getReference(Analysis.class, id);
                analysis.getIdAn();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The analysis with id " + id + " no longer exists.", enfe);
            }
            Year idYear = analysis.getIdYear();
            if (idYear != null) {
                idYear.getAnalysisCollection().remove(analysis);
                idYear = em.merge(idYear);
            }
            Month idMonth = analysis.getIdMonth();
            if (idMonth != null) {
                idMonth.getAnalysisCollection().remove(analysis);
                idMonth = em.merge(idMonth);
            }
            em.remove(analysis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Analysis> findAnalysisEntities() {
        return findAnalysisEntities(true, -1, -1);
    }

    public List<Analysis> findAnalysisEntities(int maxResults, int firstResult) {
        return findAnalysisEntities(false, maxResults, firstResult);
    }

    private List<Analysis> findAnalysisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Analysis.class));
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

    public Analysis findAnalysis(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Analysis.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnalysisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Analysis> rt = cq.from(Analysis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<internal.db.Analysis> findAnalysisByYearMonth(String year, String month) {
        
        Query query = getEntityManager().createNamedQuery("Analysis.findByYearAndMonth");
        query.setParameter("year", year);
        query.setParameter("month", month);
        
        return query.<Analysis>getResultList();
    }
}
