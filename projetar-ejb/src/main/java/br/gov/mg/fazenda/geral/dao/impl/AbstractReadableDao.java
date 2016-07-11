package br.gov.mg.fazenda.geral.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.gov.mg.fazenda.geral.dao.ReadableDao;

/**
 *
 * @author leonardo luz fernandes <a href="mailto:leonardo.luz@fazenda.gov.br">leonardo.luz@fazenda.mg.gov.br</a> 
 * @version 0.1
 */

public abstract class AbstractReadableDao<Entity,Key extends Serializable> 
	extends AbstractAuditadableDao<Entity,Key> 
    implements ReadableDao<Entity,Key>{

	@Inject
	private EntityManager entityManager;

    public AbstractReadableDao(){
        super();
    }//end method

    protected EntityManager getEntityManager(){
    	return this.entityManager;
    }


    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> findAll() {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e ");
        return (List<Entity>)query.getResultList();
    }//end method

    @Interceptors({ReadableDaoInterceptor.class})
    @Override
    public int countAll() {
        Query query = getEntityManager().createQuery("SELECT COUNT(e) FROM " + getEntityClassName() + " e ");
        Number num = (Number)query.getSingleResult();
        return num != null ? num.intValue() : 0 ;
    }//end method

    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> findAll(int firstResult, int maxResults, String orderByProperty, boolean ascending) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e ORDER BY :pOrderBy " + (ascending?" ASC":" DESC"));
        //TODO: Create pagination code ...
        query.setParameter("pOrderBy",orderByProperty);
        //int pageStartIndex = (firstResult*maxResults);
        query.setFirstResult(firstResult);
        query.setMaxResults(maxResults);
        //TODO:
        return (List<Entity>)query.getResultList();
    }

    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> paginate(int pageNumber, int pageSize, String orderByProperty, boolean ascending) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e ORDER BY :pOrderBy " + (ascending?" ASC":" DESC"));
        query.setParameter("pOrderBy",orderByProperty);
        query.setFirstResult((pageNumber-1)*pageSize);
        query.setMaxResults(pageSize);
        return (List<Entity>)query.getResultList();
    }

    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public Entity load(Key id) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " = :pId ");
        query.setParameter("pId",id);
       //return (Entity)query.getSingleResult();
       List<Entity> resultado = (List<Entity>) query.getResultList();
       return ( resultado != null && resultado.size() > 0 ) ? resultado.get(0) : null ;
    }

    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> findManyById(Key[] ids) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e."+ getNameOfEntityKeyId() + " IN :pIds ");
        query.setParameter("pIds", ids);
        return (List<Entity>)query.getResultList();
    }


    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public List<Entity> findManyById(Collection<? extends Key> ids) {
        Query query = getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " IN :pIds ");
        query.setParameter("pIds", ids);
        return (List<Entity>)query.getResultList();
    }
    

    @Interceptors({ReadableDaoInterceptor.class})
    @Override
    public void detach(Entity entity) {
        getEntityManager().detach(entity);
    }

    @Interceptors({ReadableDaoInterceptor.class})
    @Override
    public void refresh(Entity entity) {
        getEntityManager().refresh(entity);
    }

    @Interceptors({ReadableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public boolean exists( Key key ){
    	Query query = getEntityManager().createQuery("SELECT COUNT(e) FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " = :pId ");
    	query.setParameter("pId", key );
    	List<Number> result = (List<Number>)query.getResultList();
    	return result != null && result.size() > 0 ? result.get(0).intValue() > 0 : false ;
    }

    @Interceptors({ReadableDaoInterceptor.class})
    @Override
    public boolean contains( Entity entity ){
    	return getEntityManager().contains(entity);
    }

}
