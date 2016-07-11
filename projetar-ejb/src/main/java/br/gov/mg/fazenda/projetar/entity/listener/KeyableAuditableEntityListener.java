package br.gov.mg.fazenda.projetar.entity.listener;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.hibernate.envers.RevisionListener;

import br.gov.mg.fazenda.geral.anotacao.EntidadePadrao;
import br.gov.mg.fazenda.projetar.entity.KeyableAuditableEntity;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 26/04/2016
 * 
 */ 

public class KeyableAuditableEntityListener implements RevisionListener {

	private static final Logger logger = Logger.getLogger(KeyableAuditableEntityListener.class.getName());
	
	@Inject
	@EntidadePadrao(value="principal")
	private Usuario usuario;
	
	@Override
	public void newRevision(Object revisionEntity) {
		
		if (revisionEntity instanceof KeyableAuditableEntity<?,?> ) {
			
			Date date = new Date();
			
			KeyableAuditableEntity<?,?> entity = (KeyableAuditableEntity<?,?>) revisionEntity;
			entity.setDataAtualizacao( date );
			entity.setUsuarioAtualizacao( usuario );
			
			if( Objects.isNull( usuario ) ){
				logger.log(
						Level.WARNING, 
						"Um objeto do tipo %s com id %s foi inserido sem usuario de atualizacao na data %s",
						new Object[]{
								revisionEntity.getClass(), 
								entity.getId() ,
								date });
			}
		}
		
	}
	
	/**
	public Usuario getUsuario(){
		try {
			java.beans.Expression expression1 = new Expression( this.principal,"principal", new Object[]{} );
			expression1.execute();
			Object wrapper = expression1.getValue();
			java.beans.Expression expression2 = new Expression( wrapper , "principal" , new Object[]{} );
			Usuario usuario = (Usuario)expression2.getValue();
			return usuario;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	**/

}
