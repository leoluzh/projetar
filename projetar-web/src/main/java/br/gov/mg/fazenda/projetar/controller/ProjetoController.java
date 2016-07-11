package br.gov.mg.fazenda.projetar.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.service.ProjetoService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class ProjetoController extends AbstractController<Projeto,Long> {

	protected String getLoggerName() {
		return ProjetoController.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	protected ProjetoService service;

	@PostConstruct
	public void init(){
		
	}
	
	@Override
	protected ProjetoService getService() {
		return this.service;
	}

	@Produces
	@Named("newProjeto")
	@Override
	public Projeto newInstance() {
		return new Projeto();
	}
	
}
