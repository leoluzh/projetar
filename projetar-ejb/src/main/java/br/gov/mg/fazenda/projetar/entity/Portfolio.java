package br.gov.mg.fazenda.projetar.entity;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public enum Portfolio {

	
	SEM_PORTFOLIO("Sem Portfólio"),
	COMPRAS("Compras"),
	SISTEMAS("Sistemas - DIST"),
	OBRAS("Obras"),
	RH("RH"),
	SISTEMAS_DAN("Sistemas - DAN");

	private String descricao;

	Portfolio(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
