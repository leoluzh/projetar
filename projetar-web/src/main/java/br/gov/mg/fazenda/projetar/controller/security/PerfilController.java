package br.gov.mg.fazenda.projetar.controller.security;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.controller.AbstractController;
import br.gov.mg.fazenda.projetar.entity.security.Perfil;
import br.gov.mg.fazenda.projetar.service.PerfilService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PerfilController extends AbstractController<Perfil,Long> {

	@Inject
	@ServicoPadrao
	private PerfilService service;

	@Override
	protected PerfilService getService() {
		return service;
	}

	@Override
	public Perfil newInstance() {
		return new Perfil();
	}

	@Override
	protected String getLoggerName() {
		return PerfilController.class.getName();
	}
	
}
