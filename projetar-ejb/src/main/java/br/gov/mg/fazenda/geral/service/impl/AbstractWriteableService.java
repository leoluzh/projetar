package br.gov.mg.fazenda.geral.service.impl;

import java.io.Serializable;

import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.geral.service.WriteableService;


public abstract class AbstractWriteableService<Entity,Key extends Serializable> extends AbstractReadableService<Entity,Key> implements WriteableService<Entity,Key> {


	public abstract WritableDao<Entity,Key> getDao();
	
	@Override
	public Entity update(Entity entity) {
		return this.getDao().update(entity);
	}

	@Override
	public Entity save(Entity entity) {
		return this.getDao().save(entity);
	}

	@Override
	public Entity saveOrUpdate(Entity entity) {
		return this.getDao().saveOrUpdate(entity);
	}

	@Override
	public void delete(Key id) {
		this.delete(id);
	}

	@Override
	public void delete(Entity entity) {
		this.delete(entity);
	}

	@Override
	public Entity merge(Entity entity) {
		return this.merge(entity);
	}

	@Override
	public Entity persist(Entity entity) {
		return this.persist(entity);
	}


}
