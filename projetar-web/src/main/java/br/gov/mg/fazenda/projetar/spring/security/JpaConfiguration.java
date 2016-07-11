package br.gov.mg.fazenda.projetar.spring.security;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author leonardo luz fernandes
 * @verion 0.1
 * @since 02/04/2016
 * 
 */

@Repository
@SuppressWarnings("serial")
public class JpaConfiguration implements Serializable {

	private Logger logger = LoggerFactory.getLogger( JpaConfiguration.class );
	
	private static final String LOOKUP_NAME = "java:comp/env/persistence/projetar-emf";
	
	public JpaConfiguration() {
		logger.info( "Starting Jpa Configuration ...");
		logger.info( "Configure entity manager factory from: %s" , LOOKUP_NAME );
	}
	
	
	@Bean
	public EntityManagerFactory emf() throws NamingException {
		
		try{		
			logger.info( "Creating entity manager factoring from Jpa Configuration Repository.");
			Context context = new InitialContext();
			EntityManagerFactory lookup = (EntityManagerFactory)context.lookup(LOOKUP_NAME);
			return lookup;
		}catch( NamingException ex ){
			ex.printStackTrace();
			throw ex;
		}
		
	}
			
	
}
