package br.gov.mg.fazenda.projetar.spring.security;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
//import javax.enterprise.inject.Produces;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@ApplicationScoped
@SuppressWarnings("serial")
public class SpringBeanIntegrator implements Serializable {

	private AnnotationConfigApplicationContext context;
	
	public SpringBeanIntegrator(){
		
	}
	
	@PostConstruct
	private void init(){
		//this.context = new AnnotationConfigApplicationContext();
		//this.context.register( SpringBeanIntegrator.class );
		//this.context.refresh();
	}
	
	//@Produces
	//public Xtpo produceXpto(){
	//	return this.context.getBean(Xpto.class);
	//}
	
}
