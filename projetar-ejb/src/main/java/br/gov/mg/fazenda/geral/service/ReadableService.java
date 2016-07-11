package br.gov.mg.fazenda.geral.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Servico;

@Servico
public interface ReadableService<Entity,Key extends Serializable>  
	extends AuditadableService<Entity,Key>{
	
    public List<Entity> findAll();
    public int countAll();
    public List<Entity> findAll( int firstResult, int maxResults, String orderByProperty , boolean ascending);
    public List<Entity> paginate( int pageIndex, int pageSize, String orderByProperty , boolean ascending);
    public Entity load(Key ids);
    public List<Entity> findManyById(Key[] ids);
    public List<Entity> findManyById(Collection<? extends Key> ids);
    public void detach(Entity entity);
    public void refresh(Entity entity);
    public boolean contains( Entity entity );
    public boolean exists( Key key );
    
}
