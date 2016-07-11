package br.gov.mg.fazenda.projetar.controller;


import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.service.EtapaMarcoService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class EtapaMarcoController extends AbstractController<EtapaMarco,Long> {

	protected String getLoggerName() {
		return EtapaMarcoController.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	protected EtapaMarcoService service;
	
	@Override
	protected EtapaMarcoService getService() {
		return this.service;
	}

	@Produces
	@Named("newEtapaMarco")
	@Override
	public EtapaMarco newInstance() {
		return new EtapaMarco();
	}

}
