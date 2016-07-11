package br.gov.mg.fazenda.projetar.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;

/**
 *
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 05/07/2016
 */

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	@Override
	public void handle(
			HttpServletRequest request, 
			HttpServletResponse response, 
			AccessDeniedException accessDeniedException ) throws IOException, ServletException {		
	
		if( accessDeniedException instanceof InvalidCsrfTokenException ){
			accessDeniedException.printStackTrace();
		}
		
		super.handle(request, response, accessDeniedException);
		
	}

}
