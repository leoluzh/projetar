package br.gov.mg.fazenda.projetar.controller.security;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.controller.AbstractListProducer;
import br.gov.mg.fazenda.projetar.entity.security.Perfil;
import br.gov.mg.fazenda.projetar.service.PerfilService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PerfilListProducer extends AbstractListProducer<Perfil,Long>  {

	@Inject
	@ServicoPadrao
	private PerfilService service;

	@Override
	protected PerfilService getService() {
		return service;
	}

	@Override
	protected String getLoggerName() {
		return PerfilListProducer.class.getName();
	}
	
}
