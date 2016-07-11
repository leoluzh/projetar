package br.gov.mg.fazenda.geral.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author leonardo luz fernandes <a href="mailto:leonardo.luz@fazenda.gov.br">leonardo.luz@fazenda.mg.gov.br</a> 
 * @version 0.1
 */

public interface ReadableDao<Entity,Key extends Serializable> extends AuditadableDao<Entity,Key> {
    public List<Entity> findAll();
    public int countAll();
    public List<Entity> findAll( int firstResult, int maxResults, String orderByProperty , boolean ascending);
    public List<Entity> paginate( int pageIndex, int pageSize, String orderByProperty , boolean ascending);
    public Entity load(Key id);
    public List<Entity> findManyById(Key[] ids);
    public List<Entity> findManyById(Collection<? extends Key> ids);
    public void detach(Entity entity);
    public void refresh(Entity entity);
    public boolean contains( Entity entity );
    public boolean exists( Key key );
}//end interface
