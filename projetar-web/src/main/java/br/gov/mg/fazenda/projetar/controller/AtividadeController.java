package br.gov.mg.fazenda.projetar.controller;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.service.AtividadeService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class AtividadeController extends AbstractController<Atividade,Long> {

	protected String getLoggerName(){
		return AtividadeController.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	private AtividadeService service;
	
	@Override
	protected AtividadeService getService() {
		return this.service;
	}

	@Produces
	@Named("newAtividade")
	public Atividade newInstance() {
		return new Atividade();
	}

}
