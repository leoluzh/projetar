package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.mg.fazenda.geral.anotacao.EntidadePadrao;
import br.gov.mg.fazenda.geral.service.WriteableService;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.util.NumberAsDoubleComparator;

@SuppressWarnings("serial")
public abstract class AbstractController<T,K extends Serializable> implements Serializable {

	protected Logger logger;
	
	public AbstractController(){
		logger = LoggerFactory.getLogger( getLoggerName()  );
	}	
	
	@Inject
	@EntidadePadrao(value="principal")
	protected Usuario usuario;
	
	private T entity;
	
	protected abstract WriteableService<T,K> getService();
	public abstract T newInstance();
	protected abstract String getLoggerName();

	public Logger getLogger(){
		return this.logger;
	}
	
	public T getEntity(){
		return this.entity;
	}
	
	public void setElement( T entity ){
		this.entity = entity;
	}
	
	
	public void saveOrUpdate( T entity ){
		try{
			if( entity != null && isValid( entity ) ){
				this.getService().saveOrUpdate(entity);
	            FacesContext.getCurrentInstance().addMessage(null,
	                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registered!", "Registration successful."));
			}else{
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration Invalid!", "Registration Unsuccessful.");
	            FacesContext.getCurrentInstance().addMessage(null, m);				
			}
		}catch(Exception ex){
	           	String errorMessage = getRootErrorMessage(ex);
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Registration Unsuccessful.");
	            FacesContext.getCurrentInstance().addMessage(null, m);
	 		}
	}
	
	public void saveOrUpdate(){
		saveOrUpdate(this.entity);
	}
	
	public void delete( T entity ){
		try{
			if( entity != null && canDelete( entity ) ){
				this.getService().delete(entity);
			}else{
	            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Delete Invalid!", "Delete operation unsuccessful.");
	            FacesContext.getCurrentInstance().addMessage(null, m);								
			}
		}catch(Exception ex){
            String errorMessage = getRootErrorMessage(ex);
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Delete operation unsuccessful.");
            FacesContext.getCurrentInstance().addMessage(null, m);			
		}
	}
	
	public void delete(){
		delete(this.entity);
	}
	
	public boolean canDelete( T entity ){
		return true;
	}
	
	public boolean isValid( T entity ){
		return true;
	}
	
    protected String getRootErrorMessage(Exception e) {
        // Default to general error message that registration failed.
        String errorMessage = "Operação de registro falhou.";
        if (e == null) {
            // This shouldn't happen, but return the default messages
            return errorMessage;
        }

        // Start with the exception and recurse to find the root cause
        Throwable t = e;
        while (t != null) {
            // Get the message from the Throwable class instance
            errorMessage = t.getLocalizedMessage();
            t = t.getCause();
        }
        // This is the root cause message
        return errorMessage;
    }
	
    /**
     * Buscar revisoes pela chave primaria de um elemento
     * 
     * @param id
     * @return
     */
    
	public List<Number> getRevisoes( K id ) {
		try{
			List<Number> revisoes = getService().getRevisions( id );
			return revisoes.stream().sorted( NumberAsDoubleComparator.getComparator(true) ).collect(Collectors.toList());
		}catch( Exception ex ){
			return Collections.emptyList();
		}
	}
	
	/**
	 * Buscar primeira revisao de um elemento pela chave primaria
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Optional<T> getPrimeiraRevisao( K id ) throws Exception {
		List<Number> revisoes = getRevisoes( id );
		if( CollectionUtils.isNotEmpty( revisoes ) ){
			T t =  getPorNumeroRevisao( revisoes.get(0) );
			return Optional.ofNullable( t );
		}
		return Optional.ofNullable(null) ;
	}

	/**
	 * Buscar ultima revisao de um elemento pela chave primaria
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	public Optional<T> getUltimaRevisao( K id ) throws Exception {
		List<Number> revisoes = getRevisoes( id );
		if( CollectionUtils.isNotEmpty( revisoes ) ){
			T t = getPorNumeroRevisao( revisoes.get( revisoes.size() - 1 ) );
			return Optional.of(t);
		}
		return Optional.ofNullable(null) ;
	}
	
	
	/**
	 * Buscar revisao de um elemento pela chave primaria
	 * 
	 * @param revisao
	 * @return
	 * @throws Exception
	 */	
	public T getPorNumeroRevisao( Number revisao ) throws Exception {
		return getService().findRevision( revisao );
	}
	
	public Map<Number,T> getMapaRevisoes( K id ) throws Exception {
		Set<Number> revisions = new HashSet<Number>();
		revisions.addAll( getService().getRevisions( id ) );
		return getService().findRevisions( revisions );
	}
    
	
}
