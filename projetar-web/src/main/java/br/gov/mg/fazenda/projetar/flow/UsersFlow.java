package br.gov.mg.fazenda.projetar.flow;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.functional.QuerySearch;
import br.gov.mg.fazenda.projetar.service.UsuarioService;


@Named
@FlowScoped("users")
@SuppressWarnings("serial")
public class UsersFlow implements Serializable {

	@Inject
	@ServicoPadrao
	private UsuarioService service;
	
	private List<Usuario> usuarios;
	private List<Usuario> filtrados;
	private Usuario selecionado;
	private Usuario usuario;
	
	private QuerySearch<Usuario> query;
	
	@PostConstruct
	public void init(){
		query = () -> { return service.findAll(); };
		usuarios = query.fetch();
	}
	
	@Produces
	public Usuario getNewUsuario(){
		return new Usuario();
	}
	
	
	
}
