package br.gov.mg.fazenda.projetar.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.ProgramaDao;
import br.gov.mg.fazenda.projetar.entity.Programa;
import br.gov.mg.fazenda.projetar.service.ProgramaService;

@Stateless
@ServicoPadrao
public class ProgramaServiceImpl 
	extends AbstractWriteableService<Programa, Long> 
	implements ProgramaService {

	@Inject
	@DaoPadrao
	private ProgramaDao dao;
	
	@Override
	public WritableDao<Programa, Long> getDao() {
		return dao;
	}

}
