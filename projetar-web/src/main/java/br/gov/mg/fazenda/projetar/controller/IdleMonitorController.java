package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@Named
@SuppressWarnings("serial")
public class IdleMonitorController implements Serializable {

	private static final String OUTCOME = "ajax-session-timeout-outcome" ;
	private Logger logger = LoggerFactory.getLogger(IdleMonitorController.class);

	public void idleEventListener(){
		FacesContext context = FacesContext.getCurrentInstance();
		killHttpSession(context);
	}
	
	public void activeEventListener(){
		FacesContext context = FacesContext.getCurrentInstance();
		killHttpSession(context);
		doRedirectToLoginPage( context );
	}
	
	protected void killHttpSession( FacesContext context ){
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		if( session != null ){
			session.invalidate();
		}
	}
	
	protected void doRedirectToLoginPage( FacesContext context ){
		ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)
		context.getApplication().getNavigationHandler();
		handler.performNavigation( OUTCOME );	
	}
	
}
