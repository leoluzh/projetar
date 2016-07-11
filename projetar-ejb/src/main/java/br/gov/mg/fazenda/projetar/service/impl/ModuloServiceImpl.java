package br.gov.mg.fazenda.projetar.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.ModuloDao;
import br.gov.mg.fazenda.projetar.entity.Modulo;
import br.gov.mg.fazenda.projetar.service.ModuloService;

@Stateless
@ServicoPadrao
public class ModuloServiceImpl 
	extends AbstractWriteableService<Modulo,Long> 
	implements ModuloService  {

	@Inject
	@DaoPadrao
	private ModuloDao dao;

	@Override
	public ModuloDao getDao() {
		return dao;
	}
	
}
