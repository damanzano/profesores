/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.icesi.profesores.controllers;

import co.edu.icesi.profesores.entities.M4scoHHrPos;
import co.edu.icesi.profesores.entities.M4scoPosition;
import co.edu.icesi.profesores.entities.M4scoPositionPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author 1130619373
 */
public class M4scoPositionJpaController implements Serializable {

    public M4scoPositionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   public List<M4scoPosition> findM4scoPositionEntities() {
        return findM4scoPositionEntities(true, -1, -1);
    }

    public List<M4scoPosition> findM4scoPositionEntities(int maxResults, int firstResult) {
        return findM4scoPositionEntities(false, maxResults, firstResult);
    }

    private List<M4scoPosition> findM4scoPositionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(M4scoPosition.class));
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

    public M4scoPosition findM4scoPosition(M4scoPositionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(M4scoPosition.class, id);
        } finally {
            em.close();
        }
    }

    public int getM4scoPositionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<M4scoPosition> rt = cq.from(M4scoPosition.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public M4scoPosition findPositionById(String idPosition) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<M4scoPosition> q = em.createNamedQuery("M4scoPosition.findByScoIdPosition", M4scoPosition.class);
            q.setParameter("scoIdPosition", idPosition);
            M4scoPosition position = (M4scoPosition)q.getSingleResult();
            return position;
        } catch (NoResultException ex) {
            throw ex;
        }catch (NonUniqueResultException ex){
            throw ex;
    }finally {
            em.close();
        }
    }
    
    /** 
     * Return the array of descriptions for the given positions' array
     */
    public List<M4scoPosition> findPositionByM4scoHHrPos(List<M4scoHHrPos> positions){
        List<M4scoPosition> descriptions=new ArrayList<M4scoPosition>();
        for(int i=0;i<positions.size();i++){
            M4scoPosition description=findPositionById(positions.get(i).getScoIdPosition());
            descriptions.add(description);
        }
        return descriptions;
    }
    
}
