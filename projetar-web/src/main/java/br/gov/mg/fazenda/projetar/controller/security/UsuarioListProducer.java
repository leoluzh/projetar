package br.gov.mg.fazenda.projetar.controller.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
//import javax.faces.bean.ManagedBean; //jsf
//import javax.faces.bean.ViewScoped;  //jsf
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped; //cdi
import javax.inject.Inject; 
import javax.inject.Named; //cdi


import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.controller.AbstractListProducer;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.UsuarioService;

@ViewScoped
@Named
@SuppressWarnings("serial")
public class UsuarioListProducer extends AbstractListProducer<Usuario,Long> {

	protected String getLoggerName() {
		return UsuarioListProducer.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	private UsuarioService usuarioService;
	
	
	protected UsuarioService getService(){
		return this.usuarioService;
	}
	
	
	@PostConstruct
	public void init(){
		retrieveAllOrderedByNome();
	}
	
	public void retrieveAllOrderedByNome(){
		this.searcher = () -> { 
			this.searchedAt = new Date();
			return usuarioService.findAllOrderByNome(); 
		};
		search();
	}
		
	@Named("usuarioHabilitadoFilterOptions")
	@Produces
	public List<SelectItem> getUsuarioHabilitadoFilterOptions(){
		
		List<SelectItem> itens = new ArrayList<>();
		
		itens.add( new SelectItem( "" , "Todos" ) );
		itens.add( new SelectItem( "true" , "Sim" ) );
		itens.add( new SelectItem( "false" , "Não" ) );
		
		return itens;
	}
	
	
}
