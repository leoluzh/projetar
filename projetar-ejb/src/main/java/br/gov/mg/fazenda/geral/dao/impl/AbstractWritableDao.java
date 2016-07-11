package br.gov.mg.fazenda.geral.dao.impl;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.event.EntityEvent;
import br.gov.mg.fazenda.projetar.event.EntityEvent.EventType;

/**
 *
 * @author leonardo luz fernandes <a href="mailto:leonardo.luz@fazenda.gov.br">leonardo.luz@fazenda.mg.gov.br</a> 
 * @version 0.1
 */

public abstract class AbstractWritableDao<Entity,Key extends Serializable> extends AbstractReadableDao<Entity, Key> implements WritableDao<Entity, Key>{

	@Inject
	private Event<EntityEvent<Entity>> eventSource;
	
	@Interceptors({WritableDaoInterceptor.class})
    @Override
    public Entity merge(Entity entity) {
        entity = getEntityManager().merge(entity);
        eventSource.fire( createEvent( entity , EventType.SAVE , null ) );
        return entity;
    }

	@Interceptors({WritableDaoInterceptor.class})
    @Override
    public Entity update(Entity entity) {
        entity = getEntityManager().merge(entity);
        eventSource.fire( createEvent( entity , EventType.UPDATE , null ) );
        return entity;
    }

	@Interceptors({WritableDaoInterceptor.class})
    @Override
    public Entity save(Entity entity) {
        getEntityManager().persist(entity);
        eventSource.fire( createEvent( entity , EventType.SAVE , null ) );
        return entity;
    }
    
	@Interceptors({WritableDaoInterceptor.class})
    @Override
    public Entity persist( Entity entity ){
    	getEntityManager().persist(entity);
    	eventSource.fire( createEvent( entity , EventType.CREATE , null ) );
    	return entity;
    }
        

	@Interceptors({WritableDaoInterceptor.class})
    @Override
    public Entity saveOrUpdate(Entity entity) {
    	
    	Key value = null ;
    	
    	try{
    		value = (Key) getEntityKeyIdValue( entity ) ;
    	}catch( Exception ex ){
    		//IGNORE: when id is null api throws exception ...
    	}

    	//case for database generated key
    	if( value == null ){
    		entity = save( entity );
    	}else{
    		if( getEntityManager().contains( entity ) ){
    			entity = update( entity );
    		}else{
        		if( exists( value ) ){
        			entity = update( entity );
        		}else{
        			entity = save( entity );
        		}
    		}
    	}
    	
    	return entity;
    }

	@Interceptors({WritableDaoInterceptor.class})
    @SuppressWarnings("unchecked")
    @Override
    public void delete(Key id) {
        Query query = 
        		getEntityManager().createQuery("SELECT e FROM " + getEntityClassName() + " e WHERE e." + getNameOfEntityKeyId() + " = :pId ")
        		.setParameter("pId", id);
        Entity entity = (Entity)query.getSingleResult();
        delete(entity);
        eventSource.fire( createEvent( entity , EntityEvent.EventType.DELETE , null ) );
    }

	@Interceptors({WritableDaoInterceptor.class})
    @Override
    public void delete(Entity entity) {
        getEntityManager().remove(entity);
    }

	private EntityEvent<Entity> createEvent( Entity entity , EntityEvent.EventType type  , String source ){
		return new EntityEvent<Entity>(entity, type, source);
	}
	
}
