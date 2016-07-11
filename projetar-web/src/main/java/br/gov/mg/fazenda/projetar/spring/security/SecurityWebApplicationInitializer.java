package br.gov.mg.fazenda.projetar.spring.security;

import java.io.Serializable;

import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.FilterType;
//import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 02/04/2016
 */

@ComponentScan(	
		basePackages={"br.gov.mg.fazenda.projetar.spring.security"},
		lazyInit=true,
		useDefaultFilters=true 
		//,
		//excludeFilters={
		//		@Filter(type=FilterType.ANNOTATION,value=javax.inject.Named.class) , 
		//		@Filter(type=FilterType.ANNOTATION,value=javax.inject.Inject.class),
	    //		@Filter(type=FilterType.ANNOTATION,value=javax.inject.Singleton.class)}
)
@Configuration
@SuppressWarnings("serial")
public class SecurityWebApplicationInitializer 
	extends AbstractSecurityWebApplicationInitializer 
	implements Serializable {

	public SecurityWebApplicationInitializer(){
		super( SecurityConfiguration.class,
			   SystemUserDetailsService.class,
			   JpaConfiguration.class);
		//org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
	}
	
}
