package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 * 
 * @author leonardo luz fernandes
 * @verion 0.1
 * 
 * ThemeConfigController utilizado para configurar o tamanha da fonte da aplicacao.
 * Sistema utiliza um managed bean com escopo de sessao bem como pontos escuta (listener)
 * para controlar a selecao do tamanho da fonte.
 * De acordo com a recomendacao de projetos esse o ideal para requisitos de tela.
 * 
 * Use os listener para selecionar a fonte e para configurar a propriedade de o getter.
 * 
 * <div class="#{fontSizeConfigController.css}">
 * 
 * <h:commandButton id="increase" value="+" title="Increase Font Size" actionListener="#{fontSizeConfigController.increaseFontSize}"/>
 * <h:commandButton id="decrease" value="-" title="Decrease Font Size" actionListener="#{fontSizeConfigController.decreaseFontSize}"/>
 * 
 */

@Named
@SessionScoped
@SuppressWarnings("serial")
public class ThemeConfigController implements Serializable {

	private boolean coollapseMenu = false;
	private boolean fixedSidebar = false;
	private boolean topNavbar = false;
	private boolean primaryLayout = false;
	private boolean boxedLayout = false;
	private boolean fixedfooter = false;

	protected enum Skins {
		DEFAULT(),
		BLUE_LIGHT(),
		YELLOW(),
		RED,
	}
	
	protected enum FontSize {
		
		TINY("font-tiny","font-size: 8px !important;") ,
		NORMAL("font-normal","font-size: 10px !important;") ,
		LARGE("font-large","font-size: 12px !important;");
		
		FontSize( String css , String style){
			this.style = style;
		}
		
		private String css;
		private String style;
		
		public String getCss(){
			return this.css;
		}
		
		public String getStyle(){
			return this.style;
		}
		
	};
	
	
	private FontSize fontSize = FontSize.NORMAL;
	
	public FontSize getFontSize(){
		return this.fontSize;
	}
	
	public void setFontSize( FontSize fontSize ){
		this.fontSize = fontSize;
	}
	
	public void setFontSizeTinyEventListener( ActionEvent event ){
		this.fontSize = FontSize.TINY;
	}
	
	public void setFontSizeNormalEventListener( ActionEvent event ){
		this.fontSize = FontSize.NORMAL;
	}
	
	public void setFontSizeLargeEventListener( ActionEvent event ){
		this.fontSize = FontSize.LARGE;
	}
	
	public void setIncreaseFontSize( ActionEvent event ){
		if( this.fontSize != null ){
			if( fontSize.ordinal() < FontSize.values().length  ){
				this.fontSize = FontSize.values()[ this.fontSize.ordinal() + 1 ];
			}
		}else{
			this.fontSize = FontSize.NORMAL;
		}
	}
	
	public void setDecreaseFontSize( ActionEvent event ){
		if( this.fontSize !=  null ){
			if( this.fontSize.ordinal() > 0 ){
				this.fontSize = FontSize.values()[ this.fontSize.ordinal() - 1 ];
			}
		}else{
			this.fontSize = FontSize.NORMAL;
		}
	}

	public boolean isCoollapseMenu() {
		return coollapseMenu;
	}

	public void setCoollapseMenu(boolean coollapseMenu) {
		this.coollapseMenu = coollapseMenu;
	}

	public boolean isFixedSidebar() {
		return fixedSidebar;
	}

	public void setFixedSidebar(boolean fixedSidebar) {
		this.fixedSidebar = fixedSidebar;
	}

	public boolean isTopNavbar() {
		return topNavbar;
	}

	public void setTopNavbar(boolean topNavbar) {
		this.topNavbar = topNavbar;
	}

	public boolean isPrimaryLayout() {
		return primaryLayout;
	}

	public void setPrimaryLayout(boolean primaryLayout) {
		this.primaryLayout = primaryLayout;
	}

	public boolean isBoxedLayout() {
		return boxedLayout;
	}

	public void setBoxedLayout(boolean boxedLayout) {
		this.boxedLayout = boxedLayout;
	}

	public boolean isFixedfooter() {
		return fixedfooter;
	}

	public void setFixedfooter(boolean fixedfooter) {
		this.fixedfooter = fixedfooter;
	}
	
}
