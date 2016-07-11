package br.gov.mg.fazenda.projetar.spring.security;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @since 02/04/2016
 * @version 0.1
 */

@Repository
@Service("userDetailsService")
@SuppressWarnings("serial")
public class SystemUserDetailsService implements UserDetailsService , Serializable {

	private Logger logger = LoggerFactory.getLogger( SystemUserDetailsService.class );
	
	private EntityManager entityManager;
	
	
	@PersistenceContext
	public void setEntityManager( EntityManager entityManager ){
		this.entityManager = entityManager;
	}
	
    
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try{
			
			logger.info("System User Details Service: ");
			logger.info("Is Entity Manager wired? %s " , ( entityManager != null ? "true" : "false" ) );
			logger.info("Username: %s" , username );
		
			String query = "SELECT u FROM Usuario u WHERE u.username = :pUsername " ; 
			List<Usuario> usuarios = (List<Usuario>) 
					entityManager.createQuery( query , Usuario.class )
					.setParameter( "pUsername", StringUtils.trim(username) ).getResultList();
			
			if( CollectionUtils.isEmpty( usuarios ) ){
				logger.info("Usuário %s não encontrado." , username );
				throw new UsernameNotFoundException("Usuário " + username + " não encontrado." );
			}
			
			return new UserDetailsWrapper().withUsuario(usuarios.get(0));
		
		}catch(Exception ex){
			ex.printStackTrace();
			throw new UsernameNotFoundException(ex.getMessage(),ex);
		}
		
	}
	
	/**
	@Bean
	public SystemUserDetailsService springDataUserDetailsService(){
		return new SystemUserDetailsService();
	}
	**/
	
	@Bean()
	public BCryptPasswordEncoder passwordEncoder() throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder( 7 , SecureRandom.getInstance("SHA1PRNG") );
		return encoder;
	}
	
	
	
}
