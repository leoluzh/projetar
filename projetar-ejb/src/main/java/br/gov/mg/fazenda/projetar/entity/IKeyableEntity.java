package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K> tipo utilizado para chave primary (tipo simples ou composto)
 * 
 */

public interface IKeyableEntity<K> extends Serializable {

	public <Z> K getId();
	public void setId( K id );
	public <Z> IKeyableEntity<K> withId( K id );
	
}
