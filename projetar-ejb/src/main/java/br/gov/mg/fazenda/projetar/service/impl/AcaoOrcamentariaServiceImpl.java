package br.gov.mg.fazenda.projetar.service.impl;


import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.AcaoOrcamentariaDao;
import br.gov.mg.fazenda.projetar.entity.AcaoOrcamentaria;
import br.gov.mg.fazenda.projetar.service.AcaoOrcamentariaService;

@Stateless
@ServicoPadrao
public class AcaoOrcamentariaServiceImpl  
	extends AbstractWriteableService<AcaoOrcamentaria,Long> 
	implements AcaoOrcamentariaService {

	@Inject
	@DaoPadrao
	private AcaoOrcamentariaDao dao;

	@Override
	public AcaoOrcamentariaDao getDao() {
		return dao;
	}
	
}
