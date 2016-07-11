package br.gov.mg.fazenda.projetar.util;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import br.gov.mg.fazenda.geral.anotacao.EntidadePadrao;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 27/04/2016
 * 
 */

@SuppressWarnings("serial")
public class Resources implements Serializable {

    @PersistenceContext(unitName="PROJETAR_PERSISTENCE_UNIT")
    private EntityManager em;
	
	//@PersistenceUnit(name="PROJETAR_PERSISTENCE_UNIT",unitName="PROJETAR_PERSISTENCE_UNIT")
	//private EntityManagerFactory emf;
        
    @Produces
    public EntityManager getEntityManager(){
    	return this.em;
    }
    
    public void disposeEntityManager( @Disposes EntityManager em ){
    	if( em != null ){
    		em.close();
    	}
    }
    
    @Produces
    @EntidadePadrao(value="default")
    public AuditReader getAuditReader(){
    	AuditReader reader =
    	AuditReaderFactory.get(getEntityManager());
    	return reader;
    }
    
    public void disposeAuditReader( 
    		@Disposes     
    		@EntidadePadrao(value="default")
    		AuditReader reader ){
    	
    }
    
    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
        
}
