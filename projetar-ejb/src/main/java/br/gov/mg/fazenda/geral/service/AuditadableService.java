package br.gov.mg.fazenda.geral.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AuditadableService<Entity,Key extends Serializable> {

	public Entity find(Key primaryKey, Number revision) throws Exception ;
	public List<Number> getRevisions(Key primaryKey) throws Exception ;	
	public Date getRevisionDate(Number revision) throws Exception ;
	public Number getRevisionNumberForDate(Date date) throws Exception ;
	public Entity findRevision(Number revision) throws Exception ;
	public Map<Number, Entity> findRevisions(Set<Number> revisions) throws Exception ;
	public Entity getCurrentRevision(boolean persist);
	public boolean isEntityClassAudited();
		
}
