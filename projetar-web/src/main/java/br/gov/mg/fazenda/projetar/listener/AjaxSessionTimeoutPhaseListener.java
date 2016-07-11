package br.gov.mg.fazenda.projetar.listener;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 *
 */

@SuppressWarnings("serial")
public class AjaxSessionTimeoutPhaseListener implements PhaseListener {

	private static final String AJAX_FACES_REQUEST_HEADER = "Faces-Request" ;
	private static final String AJAX_FACES_REQUEST_CONTENT = "partial/ajax" ;
	private static final String OUTCOME = "ajax-session-timeout-outcome" ;
	
	
	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		if( isSessionInvalid( request ) ){
			if( isAjaxRequest( context ) ){
				ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler)
				context.getApplication().getNavigationHandler();
				handler.performNavigation( OUTCOME );
			}
		}
	}
	
	protected boolean isAjaxRequest( FacesContext context ){
		PartialViewContext pvc = context.getPartialViewContext();
		boolean isAjax = pvc != null && pvc.isAjaxRequest();
		return isAjax;
	}
	
	protected boolean isSessionInvalid( HttpServletRequest request ){
		return StringUtils.isNotEmpty( request.getRequestedSessionId() ) && !request.isRequestedSessionIdValid();
	}
	
	protected boolean isAjaxRequest( HttpServletRequest request ){
		String content = request.getHeader( AJAX_FACES_REQUEST_HEADER );
		return AJAX_FACES_REQUEST_CONTENT.equals( content );
	}
	
	@Override
	public void beforePhase(PhaseEvent event) {
		//TODO: Do nothing here.		
	}
	
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	} 
	
}
