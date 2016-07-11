package br.gov.mg.fazenda.projetar.entity;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public enum Status {

	SEM_PLANEJAMENTO("Sem Planejamento"),
	PLANEJADO("Planejado"),
	DENTRO_DO_PREVISTO("Dentro do Previsto"),
	FORA_DO_PREVISTO("Reprogramado"),
	CONCLUIDA("Concluida"),
	CANCELADA("Cancelada");

	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
