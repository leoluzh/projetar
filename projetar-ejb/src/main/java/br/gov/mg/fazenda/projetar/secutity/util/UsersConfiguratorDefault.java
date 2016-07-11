package br.gov.mg.fazenda.projetar.secutity.util;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.UsuarioService;


@Named
@Startup
@Singleton
@SuppressWarnings("serial")
public class UsersConfiguratorDefault implements Serializable {

	private Logger logger = LoggerFactory.getLogger(UsersConfiguratorDefault.class);
	
	public enum States {BEFORESTARTED, STARTED, PAUSED, SHUTTINGDOWN};
	private States state = null;

	@Inject
	@ServicoPadrao
	private UsuarioService usuarioService;
	
	public UsersConfiguratorDefault(){
		logger.info("Calling default contructor of: %s" , UsersConfiguratorDefault.class.getName() );
	}
	
	public States getState(){
		return this.state ;
	}
	
	@PostConstruct
	public void configure(){
		state = States.BEFORESTARTED;
		logger.info("Configure default users ...");
		createUsers();
		state = States.STARTED;
	}
	
	@PreDestroy
	public void destroy(){
		state = States.SHUTTINGDOWN;
		logger.info("Release object UserConfiguratorDefault ...");
	}
	
	
	//@Lock(LockType.WRITE)
	private void createUsers(){
		
		Optional<Usuario> result = null ;
		
		result =
		usuarioService.findByEmail("projetar@fazenda.mg.gov.br");
		
		if( !result.isPresent() ){
			
			logger.info("Creating user ...");
			
			Usuario usuario = new Usuario();
			usuario.setEmail( "projetar@fazenda.mg.gov.br" );
			usuario.setNome("Projetar SEF-MG");
			usuario.setSenha( PasswordUtils.encode("12345") );
			usuario.setUsername("projetar");
			usuario.setDataBloqueioConta( new Date() );
			usuario.setDataAtualizacao( new Date() );
			usuario.setDataExpiracaoConta( new Date() );
			usuario.setDataExpiracaoCredencial( new Date() );
			usuario.setHabilitado(true);		
		
			usuario = 
			usuarioService.saveOrUpdate( usuario );
			
		}

		result =
		usuarioService.findByEmail("admin@fazenda.mg.gov.br");
		
		if( !result.isPresent() ){
			
			logger.info("Creating user ...");
			
			Usuario usuario = new Usuario();
			usuario.setEmail( "admin@fazenda.mg.gov.br" );
			usuario.setNome("Administrador Projetar SEF-MG");
			usuario.setSenha( PasswordUtils.encode("12345") );
			usuario.setUsername("admin");
			usuario.setDataBloqueioConta( new Date() );
			usuario.setDataAtualizacao( new Date() );
			usuario.setDataExpiracaoConta( new Date() );
			usuario.setDataExpiracaoCredencial( new Date() );
			usuario.setHabilitado(true);		
		
			usuario = 
			usuarioService.saveOrUpdate( usuario );
			
		}
		
		
		
		result =
		usuarioService.findByEmail("leonardo.luz@fazenda.mg.gov.br");
		
		if( !result.isPresent() ){
			
			logger.info("Creating user ...");
			
			Usuario usuario = new Usuario();
			usuario = new Usuario();
			usuario.setEmail( "leonardo.luz@fazenda.mg.gov.br" );
			usuario.setNome("Leonardo Luz Fernandes");
			usuario.setSenha( PasswordUtils.encode("12345") );
			usuario.setUsername("leonardo.luz");
			usuario.setDataBloqueioConta( new Date() );
			usuario.setDataAtualizacao( new Date() );
			usuario.setDataExpiracaoConta( new Date() );
			usuario.setDataExpiracaoCredencial( new Date() );
			usuario.setHabilitado(true);		
		
			usuario = 
			usuarioService.saveOrUpdate( usuario );
			
			
		}
		
	}
		
}
