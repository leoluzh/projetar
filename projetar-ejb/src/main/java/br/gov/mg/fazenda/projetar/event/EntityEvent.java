package br.gov.mg.fazenda.projetar.event;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EntityEvent<Entity> implements Serializable {

	public enum EventType{
		CREATE ,
		SAVE ,
		UPDATE ,
		DELETE ,
		REFRESH,
	}
	
	private Entity entity;
	private EventType type;
	private String source;
	
	public EntityEvent( Entity entity , EventType type ){
		this.entity = entity ;
		this.type = type;
	}

	public EntityEvent( Entity entity , EventType type , String source ){
		this( entity , type );
		this.source = source ;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public EventType getType() {
		return type;
	}
	
	public void setType(EventType type) {
		this.type = type;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
}
