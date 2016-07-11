package br.gov.mg.fazenda.projetar.controller;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.AcompanhamentoAtividade;
import br.gov.mg.fazenda.projetar.service.AcompanhamentoAtividadeService;


@ViewScoped
@Named
@SuppressWarnings("serial")
public class AcompanhamentoAtividadeController extends AbstractController<AcompanhamentoAtividade,Long> {

	protected String getLoggerName() {
		return AcompanhamentoAtividadeController.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	private AcompanhamentoAtividadeService service;

	@Override
	protected AcompanhamentoAtividadeService getService() {
		return this.service;
	}

	@Produces
	@Named("newAcompanhamentoAtividade")
	public AcompanhamentoAtividade newInstance() {
		return new AcompanhamentoAtividade();
	}
		
	
}
