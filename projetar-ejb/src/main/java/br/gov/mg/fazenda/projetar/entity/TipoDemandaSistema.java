package br.gov.mg.fazenda.projetar.entity;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public enum TipoDemandaSistema {
	
	NAO_SISTEMA ("Não se aplica"),
	MANUTENCAO_CORRETIVA("Manutenção Corretiva"),
	MANUTENCAO_EVOLUTIVA("Manutenção Evolutiva"),
	MANUTENCAO_ADAPTATIVA("Manutenção Adaptativa"),
	PROJETO("Projeto"),
	PACOTE_ATENDIMENTO_OPERACIONAL_CONTINUADO("Pacote de Atendimento Operacional Continuado"),
	PROJETO_INTERNO_STI("Projeto Interno STI"),
	CONSULTA_AO_BANCO_DE_DADOS("Consulta ao Banco de Dados"),
	ACAO_DATA_WAREHOUSE("Ação Data Warehouse");

	private String descricao;

	TipoDemandaSistema(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}


}
