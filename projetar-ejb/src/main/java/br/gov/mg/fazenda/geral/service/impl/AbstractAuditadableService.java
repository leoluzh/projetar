package br.gov.mg.fazenda.geral.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.gov.mg.fazenda.geral.dao.ReadableDao;
import br.gov.mg.fazenda.geral.service.AuditadableService;

public abstract class AbstractAuditadableService<Entity,Key extends Serializable> implements AuditadableService<Entity,Key> {

	protected abstract ReadableDao<Entity,Key> getDao();

	@Override
	public Entity find(Key primaryKey, Number revision) throws Exception {
		return getDao().find(primaryKey, revision);
	}

	@Override
	public List<Number> getRevisions(Key primaryKey) throws Exception {
		return getDao().getRevisions(primaryKey);
	}

	@Override
	public Date getRevisionDate(Number revision) throws Exception {
		return getDao().getRevisionDate(revision);
	}

	@Override
	public Number getRevisionNumberForDate(Date date) throws Exception {
		return getDao().getRevisionNumberForDate(date);
	}

	@Override
	public Entity findRevision(Number revision) throws Exception {
		return getDao().findRevision(revision);
	}

	@Override
	public Map<Number, Entity> findRevisions(Set<Number> revisions)
			throws Exception {
		return getDao().findRevisions(revisions);
	}

	@Override
	public Entity getCurrentRevision(boolean persist) {
		return getDao().getCurrentRevision(persist);
	}

	@Override
	public boolean isEntityClassAudited() {
		return getDao().isEntityClassAudited();
	}
	
	
}
