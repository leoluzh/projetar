package br.gov.mg.fazenda.projetar.entity;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public enum StatusAcompanhamentoAtividade {

	NAO_AVALIDADO("Não Avaliado","default") ,
	DENTRO_PREVISTO("Dentro do Previsto","success") ,
	FORA_PREVISTO("Fora do Previsto","danger") ;
	
	StatusAcompanhamentoAtividade( String descricao , String cor ) {
		this.descricao = descricao ;
		this.cor = cor ;
	}

	private String descricao;
	private String cor;
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public String getCor(){
		return cor;
	}
	
	public String getCss(){
		return "label label-" + getCor();
	}
	
}
