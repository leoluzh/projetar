package br.gov.mg.fazenda.projetar.entity;

import javax.persistence.MappedSuperclass;

import org.hibernate.envers.Audited;

/**
 * 
 * @author leonardo luz fernandes
 * @since 03/04/2016
 * @version 0.1
 * 
 * @param <K>
 */

@Audited
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class KeyableEntity<K> implements IKeyableEntity<K> {
	
	public abstract <T> K getId();
	public abstract void setId(K id); 
	
	public <T> KeyableEntity<K> withId( K id ){
		setId(id);
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeyableEntity<K> other = (KeyableEntity<K>) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}
		
}
