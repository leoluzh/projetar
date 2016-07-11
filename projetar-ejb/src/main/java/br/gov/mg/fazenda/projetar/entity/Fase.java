package br.gov.mg.fazenda.projetar.entity;

public enum Fase {

	INDEFINIDO( 0 , "Indefinido" , "plain" , "" ) ,
	PLEITO( 1 , "Pleito" , "primary" , "" ) ,
	ELABORACAO( 2 , "Elaboração" , "warning" , "" ) ,
	ACOMPANHAMENTO( 3 , "Acompanhamento" ,  "information" , "" ) , 
	CONCLUIDO( 4 , "Concluído" , "success" , "" ) ;
	
	Fase( int codigo , String descricao , String css , String cssIcon ){
		this.codigo = codigo ;
		this.descricao = descricao ;
		this.css = css ;
		this.cssIcon = cssIcon ;
	}
	
	private int codigo;
	private String descricao;
	private String css;
	private String cssIcon;
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public String getCss(){
		return ".label label-" + this.css;
	}
	
	public String getCssIcon(){
		return this.cssIcon;
	}
	
}
