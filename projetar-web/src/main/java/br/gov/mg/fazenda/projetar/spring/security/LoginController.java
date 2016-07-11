package br.gov.mg.fazenda.projetar.spring.security;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Custom Login Controller Authenticator.
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 17/04/2016
 *
 */

@Component
@ManagedBean(value="loginController")
@RequestScoped
@SuppressWarnings("serial")
public class LoginController implements Serializable {

	private static final String STATE_INCORRECT = "incorrect" ;
	private static final String STATE_CORRECT = "correct" ;
	private static final String STATE_LOGGEDOUT = "loggedout" ;
	
	private String username = null ;
	private String password = null ;
	
	@ManagedProperty(value="#{autheticationManager}")
	private AuthenticationManager authenticationManager = null ;
	
	public String login(){
		
		try{
			Authentication request = new UsernamePasswordAuthenticationToken( this.username , this.password );
			Authentication result = this.authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		}catch( Exception ex ){
			ex.printStackTrace();
			return STATE_INCORRECT;
		}
		
		return STATE_CORRECT;

	}
	
	public String cancel(){
		return null;
	}
	
	public String logout(){
		SecurityContextHolder.clearContext();
		return STATE_LOGGEDOUT;
	}
	
	public AuthenticationManager getAuthenticationManager(){
		return this.authenticationManager;
	}
	
	public void setAuthenticationManager( AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager ;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
