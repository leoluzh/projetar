package br.gov.mg.fazenda.projetar.util;

import java.io.Serializable;
import java.security.Principal;
import java.util.Optional;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.EntidadePadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.UsuarioService;

@SuppressWarnings("serial")
public class UsuarioResources implements Serializable {

	@Inject
	@ServicoPadrao
	private UsuarioService usuarioService;
	
	@Inject
	private Principal principal;
	
	@Produces
	@EntidadePadrao(value="principal")
	public Usuario getUsuarioPrincipal(){
		Optional<Usuario> usuario = 
		this.usuarioService.findByUsername( principal.getName() );
		return usuario.orElse(null);
	}
	
}
