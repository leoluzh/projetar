package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.mg.fazenda.geral.anotacao.EntidadePadrao;
import br.gov.mg.fazenda.geral.service.ReadableService;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.event.EntityEvent;
import br.gov.mg.fazenda.projetar.functional.QuerySearch;

/**
 * 
 * @author Leonardo Luz Fernandes
 * @version 0.1
 * @since 09/07/2016
 * 
 * @param <T>
 * @param <K>
 */

@SuppressWarnings("serial")
public abstract class AbstractListProducer<T,K extends Serializable> implements Serializable {

	protected Logger logger = null ;
	
	@Inject
	@EntidadePadrao(value="principal")
	protected Usuario usuario;

	protected List<T> elements;
	protected List<T> filtered;
	protected T selected;
	protected Date searchedAt;
	protected QuerySearch<T> searcher;
	
	protected abstract ReadableService<T,K> getService();
	protected abstract String getLoggerName();

	public AbstractListProducer() {
		this.logger = LoggerFactory.getLogger( getLoggerName() );
	}
	
    @SuppressWarnings("cdi-observer")
    public void onListChanged( @Observes( notifyObserver = Reception.IF_EXISTS) final EntityEvent<T> element ) {
    	search();
    }	
    
    public void search() {
    	this.elements = this.searcher.fetch();
    }
 
    public List<T> getElements(){
    	return this.elements;
    }
    
    protected void setElements( List<T> elements ){
    	this.elements = elements ;
    }
    
    public List<T> getFiltered(){
    	return this.filtered;
    }
    
    public void setFiltered( List<T> filtered ){
    	this.filtered = filtered ;
    }
   
    protected QuerySearch<T> getSearcher(){
    	return this.searcher;
    }
    
    protected void setSearcher( QuerySearch<T> searcher ){
    	this.searcher = searcher;
    }
    
    public Date getSearchedAt(){
    	return this.searchedAt;
    }
    
    protected void setSearchedAt( Date searchedAt ){
    	this.searchedAt = searchedAt;
    }
    
    public T getSelected(){
    	return this.selected;
    }
    
    public void setSelected( T selected ){
    	this.selected = selected ;
    }
    
    @SuppressWarnings("unchecked")
    public void onRowSelected( SelectEvent event ){
    	handlerFromOnRowSelected( (T) event.getObject() );
    }
    
    @SuppressWarnings("unchecked")
    public void onRowUnselected( SelectEvent event ){
    	handlerFromOnRowUnselected( (T) event.getObject() );
    }
     
    public void handlerFromOnRowSelected( T selected ){
    	
    }
    
    public void handlerFromOnRowUnselected( T unselected ){
    	
    }
    
    
}
