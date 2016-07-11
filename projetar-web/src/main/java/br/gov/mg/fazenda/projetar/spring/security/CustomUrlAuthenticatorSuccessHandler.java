package br.gov.mg.fazenda.projetar.spring.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 05/07/2016
 *
 */

public class CustomUrlAuthenticatorSuccessHandler implements AuthenticationSuccessHandler {

	private Logger looger = LoggerFactory.getLogger( CustomUrlAuthenticatorSuccessHandler.class );
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response , 
			Authentication authentication )
			throws IOException, ServletException {
		
		handle( request , response , authentication );
		clearAuthenticationAttributes( request );
		
	}

	protected void handle(
			HttpServletRequest request, 
			HttpServletResponse response , 
			Authentication authentication )
			throws IOException, ServletException {
	
		String targetURL = determineTargetURL(authentication);
		
		if( response.isCommitted() ){
			looger.debug("Response has already been committed. Unable to redirect to " + targetURL );
			return;
		}else{
			redirectStrategy.sendRedirect( request , response , targetURL );
		}
	
	
	}
	
	protected void clearAuthenticationAttributes( HttpServletRequest request ){
		HttpSession session = request.getSession( false );
		if( session != null ){
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
	
	protected String determineTargetURL( Authentication authentication ){
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String target = "/pages/dashboard/index.xhtml" ;
		for( GrantedAuthority authority : authorities ){
			if( "ROLE_ADMIN".equals( authority.getAuthority() ) ){
				target = "/pages/admin/dashboard/index.xhtml" ;
			}
		}
		return target ;
	}
	
	public RedirectStrategy getRedirectStrategy(){
		return this.redirectStrategy;
	}
	
	public void setRedirectStrategy( RedirectStrategy redirectStrategy ){
		this.redirectStrategy = redirectStrategy;
	}
	
	
}
