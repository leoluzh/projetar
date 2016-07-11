package br.gov.mg.fazenda.projetar.controller.security;


import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.gov.mg.fazenda.projetar.controller.AbstractListProducer;
import br.gov.mg.fazenda.projetar.entity.security.Permissao;
import br.gov.mg.fazenda.projetar.service.PermissaoService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class PermissaoListProducer extends AbstractListProducer<Permissao,Long> {

	protected PermissaoService service;
	
	@Override
	protected PermissaoService getService() {
		return service;
	}

	@Override
	protected String getLoggerName() {
		return PermissaoListProducer.class.getName();
	}

}
