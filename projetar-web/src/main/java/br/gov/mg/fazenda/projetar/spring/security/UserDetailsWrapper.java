package br.gov.mg.fazenda.projetar.spring.security;

import java.util.Collection;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.2
 * @since 24/04/2016
 *
 */

@SuppressWarnings("serial")
public class UserDetailsWrapper implements UserDetails  , CredentialsContainer {

	private Usuario usuario;
	
	public UserDetailsWrapper(){

	}
	
	public static UserDetailsWrapper getInstance( Usuario usuario ){
		return new UserDetailsWrapper().withUsuario(usuario);
	}
	
	public UserDetailsWrapper( Usuario usuario ){
		this.usuario = usuario ;
	}

	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setUsuario( Usuario usuario ){
		this.usuario = usuario ;
	}
	
	public UserDetailsWrapper withUsuario( Usuario usuario ){
		setUsuario(usuario);
		return this;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public void eraseCredentials(){
		this.usuario.setSenha(null);
	}	
	
}
