package br.gov.mg.fazenda.geral.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.hibernate.envers.AuditReader;

/**
 * 
 * @author leonardo luz fernandes
 * @since 27/04/2016
 * @version 0.1
 * 
 * @param <Entity>
 * @param <Key>
 */

public interface AuditadableDao<Entity,Key extends Serializable> {

	public Entity find( Key primaryKey, Number revision) throws Exception ;
	public List<Number> getRevisions(Key primaryKey) throws Exception ;	
	public Date getRevisionDate(Number revision) throws Exception ;
	public Number getRevisionNumberForDate(Date date) throws Exception ;
	public Entity findRevision(Number revision) throws Exception ;
	public Map<Number, Entity> findRevisions(Set<Number> revisions) throws Exception ;
	public Entity getCurrentRevision(boolean persist);
	public boolean isEntityClassAudited();
	
}
