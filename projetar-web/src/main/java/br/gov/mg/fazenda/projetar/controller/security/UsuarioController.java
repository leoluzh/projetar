package br.gov.mg.fazenda.projetar.controller.security;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.controller.AbstractController;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.UsuarioService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class UsuarioController extends AbstractController<Usuario,Long> {

	@Inject
	@ServicoPadrao
	private UsuarioService service;
		
	@Named(value="newUsuario")
	@Produces
	public Usuario getNewInstance(){
		return new Usuario();
	}
		 	
    public List<SelectItem> getHabilitadoFilterOptions(){
    	List<SelectItem> opcoes = new ArrayList<>();
    	opcoes.add( new SelectItem( "", "Todos" ) );
    	opcoes.add( new SelectItem( "true" , "Sim" ) );
    	opcoes.add( new SelectItem( "false" , "Não" ) );
    	return opcoes;
    }
    
	@Override
	protected UsuarioService getService() {
		return service;
	}


	@Override
	protected String getLoggerName() {
		return UsuarioController.class.getName();
	}

	@Override
	public Usuario newInstance() {
		// TODO Auto-generated method stub
		return null;
	}
    	
}
