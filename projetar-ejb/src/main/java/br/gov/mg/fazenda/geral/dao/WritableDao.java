package br.gov.mg.fazenda.geral.dao;

import java.io.Serializable;
import java.util.Optional;


/**
 *
 * @author leonardo luz fernandes <a href="mailto:leonardo.luz@fazenda.gov.br">leonardo.luz@fazenda.mg.gov.br</a> 
 * @version 0.1
 */

public interface WritableDao<Entity,Key extends Serializable> extends ReadableDao<Entity, Key>{
    public Entity update(Entity entity);
    public Entity save(Entity entity);
    public Entity saveOrUpdate(Entity entity);
    public void delete(Key id);
    public void delete(Entity entity);
    public Entity merge(Entity entity);
    public Entity persist( Entity entity );
}//end interface
