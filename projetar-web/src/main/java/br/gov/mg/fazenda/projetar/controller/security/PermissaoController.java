package br.gov.mg.fazenda.projetar.controller.security;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.controller.AbstractController;
import br.gov.mg.fazenda.projetar.entity.security.Permissao;
import br.gov.mg.fazenda.projetar.service.PermissaoService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PermissaoController extends AbstractController<Permissao,Long> {

	@Inject
	@ServicoPadrao
	protected PermissaoService service;
	
	@Override
	protected PermissaoService getService() {
		return service;
	}

	@Override
	public Permissao newInstance() {
		return new Permissao();
	}

	@Override
	protected String getLoggerName() {
		return PermissaoController.class.getName();
	}

}
