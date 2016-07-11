package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.gov.mg.fazenda.projetar.spring.security.UserDetailsWrapper;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 24/04/2016
 *
 */

@Model
@SuppressWarnings("serial") 
public class UsuarioSessionController implements Serializable {

	private UserDetailsWrapper user;
	
	@PostConstruct
	public void init(){
		this.user = producesUsuario();
	}
	
	@Named("username")
	@Produces
	public String getUsername(){
		return this.user.getUsername();
	}
	
	@Named("fullname")
	@Produces
	public String getNome(){
		return this.user.getUsuario().getNome();
	}
	
	@Named("email")
	@Produces
	public String getEmail(){
		return this.user.getUsuario().getEmail();
	}
	
    protected UsernamePasswordAuthenticationToken produceUsernamePasswordAuthenticationToken(){
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		return authentication;
    }
    
    protected UserDetailsWrapper producesUsuario(){
    	UsernamePasswordAuthenticationToken authentication = produceUsernamePasswordAuthenticationToken();
    	if( authentication != null && authentication.getPrincipal() != null ){
    		return (UserDetailsWrapper) authentication.getPrincipal();
    	}
    	return null;
    }
	
		
}
