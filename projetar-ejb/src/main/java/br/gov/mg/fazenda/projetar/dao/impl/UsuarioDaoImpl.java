package br.gov.mg.fazenda.projetar.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.UsuarioDao;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * 
 */

@Stateless
@DaoPadrao
public class UsuarioDaoImpl extends AbstractWritableDao<Usuario,Long> implements UsuarioDao{

	public Optional<Usuario> findByUsername( String username ){
		
		String query = "SELECT u FROM Usuario u WHERE u.username = :pUsername " ;
		
		List<Usuario> usuarios = (List<Usuario>)this.getEntityManager()
		.createQuery( query , Usuario.class )
		.setParameter("pUsername", StringUtils.trim( username ) )
		.getResultList();

		if( CollectionUtils.isNotEmpty( usuarios ) ){
			return Optional.ofNullable( usuarios.get(0) );
		}else{
			return Optional.ofNullable( null );
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public Optional<Usuario> findByEmail( String email ){
		
		String query = "SELECT u FROM Usuario u WHERE u.email = :pEmail " ;
		
		List<Usuario> usuarios = 
		(List<Usuario>)this.getEntityManager()
		.createQuery( query )
		.setParameter("pEmail", StringUtils.trim( email ) )
		.getResultList();
		
		if( CollectionUtils.isNotEmpty( usuarios ) ){
			return Optional.ofNullable( usuarios.get( 0 ) );
		}else{
			return Optional.ofNullable(null);
		}
		
		
	}
	
	public List<Usuario> findAllOrderByNome(){
		
		String query = "SELECT u FROM Usuario u ORDER BY u.nome ASC" ;
		List<Usuario> usuario = 
				(List<Usuario>) this.getEntityManager()
				.createQuery( query , Usuario.class )
				.getResultList();
		
		return usuario;
		
	}
	
}
