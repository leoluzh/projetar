package br.gov.mg.fazenda.projetar.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.UnidadeDao;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.service.UnidadeService;

@Stateless
@ServicoPadrao
public class UnidadeServiceImpl 
	extends AbstractWriteableService<Unidade, Long> 
	implements UnidadeService  {

	@Inject
	@DaoPadrao
	private UnidadeDao dao;

	@Override
	public UnidadeDao getDao() {
		return dao;
	}
	
}


