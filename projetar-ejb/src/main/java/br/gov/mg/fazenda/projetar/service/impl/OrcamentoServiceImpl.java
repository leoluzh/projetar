package br.gov.mg.fazenda.projetar.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.OrcamentoDao;
import br.gov.mg.fazenda.projetar.entity.Orcamento;
import br.gov.mg.fazenda.projetar.service.OrcamentoService;

@Stateless
@ServicoPadrao
public class OrcamentoServiceImpl 
	extends AbstractWriteableService<Orcamento,Long> 
	implements OrcamentoService{

	@Inject
	@DaoPadrao
	private OrcamentoDao dao;

	@Override
	public OrcamentoDao getDao() {
		return dao;
	}
	
}
