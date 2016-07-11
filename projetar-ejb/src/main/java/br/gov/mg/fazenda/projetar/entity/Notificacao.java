package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Notificacao extends KeyableAuditableEntity<Notificacao,Long> implements Serializable {

	private Long id;
	private String mensagem;
	
	@Override
	public <T> Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

}
