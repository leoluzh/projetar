package br.gov.mg.fazenda.geral.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import br.gov.mg.fazenda.geral.dao.AuditadableDao;

/**
 * 
 * @author leonardo luz fernandes
 * @since 27/04/2016
 * @version 0.1
 * 
 * Usado pra tentar esconder um pouco da dependencia do framework hibernate.
 * 
 * @param <Entity>
 * @param <Key>
 */

public abstract class AbstractAuditadableDao<Entity,Key extends Serializable> 
	extends AbstractDao<Entity,Key>
	implements AuditadableDao<Entity,Key> {

	private AuditReader auditReader;
	protected abstract EntityManager getEntityManager();
	
	@PostConstruct
	protected void configureAuditReader(){
		this.auditReader = AuditReaderFactory.get( getEntityManager() );
	}
	
	protected AuditReader getAuditReader() {
		return this.auditReader;
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public Entity find(Key primaryKey, Number revision) throws Exception {
		return getAuditReader().find(getEntityClass(), primaryKey, revision);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public List<Number> getRevisions(Key primaryKey) throws Exception {
		return getAuditReader().getRevisions(getEntityClass(), primaryKey);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public Date getRevisionDate(Number revision) throws Exception {
		return getAuditReader().getRevisionDate(revision);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public Number getRevisionNumberForDate(Date date) throws Exception {
		return getAuditReader().getRevisionNumberForDate(date);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public Entity findRevision(Number revision) throws Exception {
		return getAuditReader().findRevision(getEntityClass(), revision);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public Map<Number, Entity> findRevisions(Set<Number> revisions) throws Exception {
		return getAuditReader().findRevisions(getEntityClass(), revisions);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public Entity getCurrentRevision(boolean persist) {
		return getAuditReader().getCurrentRevision(getEntityClass(), persist);
	}

	@Interceptors({AuditableDaoInterceptor.class})
	@Override
	public boolean isEntityClassAudited() {
		return getAuditReader().isEntityClassAudited(getEntityClass());
	}

}
