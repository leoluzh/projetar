package br.gov.mg.fazenda.geral.service;

import java.io.Serializable;

import br.gov.mg.fazenda.geral.anotacao.Servico;

@Servico
public interface WriteableService<Entity,Key extends Serializable> extends ReadableService<Entity, Key> {
    public Entity update(Entity entity);
    public Entity save(Entity entity);
    public Entity saveOrUpdate(Entity entity);
    public void delete(Key id);
    public void delete(Entity entity);
    public Entity merge(Entity entity);
    public Entity persist( Entity entity );
}
