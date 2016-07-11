package br.gov.mg.fazenda.geral.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.gov.mg.fazenda.geral.dao.ReadableDao;
import br.gov.mg.fazenda.geral.service.ReadableService;


public abstract class AbstractReadableService<Entity,Key extends Serializable> 
	extends AbstractAuditadableService<Entity,Key>
	implements ReadableService<Entity, Key> {

	protected abstract ReadableDao<Entity,Key> getDao();

	public AbstractReadableService() {
		
	}
	
	@Override
	public List<Entity> findAll() {
		return this.getDao().findAll();
	}

	@Override
	public int countAll() {
		return this.getDao().countAll();
	}

	@Override
	public List<Entity> findAll(int firstResult, int maxResults, String orderByProperty, boolean ascending) {
		return this.getDao().findAll(firstResult, maxResults, orderByProperty, ascending);
	}

	@Override
	public List<Entity> paginate(int pageIndex, int pageSize, String orderByProperty, boolean ascending) {
		return this.getDao().paginate(pageIndex, pageSize, orderByProperty, ascending);
	}

	@Override
	public Entity load(Key ids) {
		return this.getDao().load(ids);
	}

	@Override
	public List<Entity> findManyById(Key[] ids) {
		return this.getDao().findManyById(ids);
	}

	@Override
	public List<Entity> findManyById(Collection<? extends Key> ids) {
		return this.getDao().findManyById(ids);
	}

	@Override
	public void detach(Entity entity) {
		this.getDao().detach(entity);
	}

	@Override
	public void refresh(Entity entity) {
		this.getDao().refresh(entity);
	}

	@Override
	public boolean contains(Entity entity) {
		return this.getDao().contains(entity);
	}
	
	public boolean exists( Key key ){
		return this.getDao().exists(key);
	}
	
}
