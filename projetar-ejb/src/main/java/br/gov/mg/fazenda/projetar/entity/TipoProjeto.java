package br.gov.mg.fazenda.projetar.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public enum TipoProjeto {

	PROCESSO("Processo","processo"),
	DEMANDA("Demanda","demanda");
	
	private String descricao;
	private String cor;
	private boolean doColorCss = false;

	TipoProjeto(String descricao, String cor) {
		this.descricao = descricao;
		this.cor = cor;
		this.doColorCss = StringUtils.isNotBlank(cor);
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCor(){
		return cor;
	}
	
	public String getCss(){
		if( doColorCss ){
			return "label label-projeto-" + cor;
		}
		return "";
	}
	
}

