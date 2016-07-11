package br.gov.mg.fazenda.projetar.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

public enum TipoCategoriaProjeto {

	ACAO_PRIORITARIA("Ação Prioritária",""),
	ESTRATEGICO("Estratégico",""),
	OUTROS("Outros","") ;
	
	private String descricao;
	private String cor;
	private boolean doColorCss;
	
	TipoCategoriaProjeto( String descricao , String cor ){
		this.descricao = descricao ;
		this.cor = cor ;
		this.doColorCss = StringUtils.isNoneBlank( cor );
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public String getCor(){
		return this.cor;
	}
	
	public String getCss(){
		if( doColorCss ){
			return "label label-categoria-projeto-" + cor;
		}
		return "";		
	}
	
}
