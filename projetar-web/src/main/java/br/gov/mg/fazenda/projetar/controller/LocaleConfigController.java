package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 28/05/2016
 *
 * <f:view locale="#{localeConfigController.locale}">
 * 
 * <h:selectOneMenu ...
 * 	<f:selectItens value="#{localeConfigController.selectedOption}"/>
 * 	<f:ajax event="onChange" actionListener="#{localeConfigController.localeValueChangeListener}"/>
 */

@Named
@SessionScoped
@SuppressWarnings("serial")
public class LocaleConfigController implements Serializable {

	private Locale locale;
	private String option;
	
	public Locale getLocale(){
		return this.locale;
	}
	
	public void setLocale( Locale locale ){
		this.locale = locale;
		//fixed: do work properly with jsf 2.0
		FacesContext.getCurrentInstance().getViewRoot().setLocale( this.locale );
	}
	
	public String getSelectedOption(){
		return this.option;
	}
	
	public void setSelectedOption( String option ){
		this.option = option;
	}
	
	public List<SelectItem> getOptions(){
		List<SelectItem> options = new ArrayList<>();
		options.add( new SelectItem("pt-BR","Português (Brasil)"));
		options.add( new SelectItem("es-ES","Español (España)"));
		options.add( new SelectItem("en-US","English (United States)"));
		return options;
	} 
	
	public void setLocaleValueChangeListener( ActionEvent event ){
		 String value = (String)((EditableValueHolder)event.getComponent().getParent()).getValue();
		 setLocale( decode( value ) );
	}

	public void setLocaleValueChangeListener( ValueChangeEvent event ){
		 String value = (String)event.getNewValue();
		 setLocale( decode( value ) );
	}

	private Locale decode( String option ){
		 String[] tokens = option.split("-");
		 return new Locale( tokens[0],  tokens[1] );	
	}
	
}
