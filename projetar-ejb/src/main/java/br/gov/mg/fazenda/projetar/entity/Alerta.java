package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Alerta extends KeyableAuditableEntity<Alerta,Long> implements Serializable {

	public enum TipoAlerta{
		GLOBAL ,
		UNIDADE ,
		GRUPO , 
		USUARIO
	};
	
	private Long id;
	private String mensagem;
	private TipoAlerta tipo;
	
	@Override
	public <T> Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
}
