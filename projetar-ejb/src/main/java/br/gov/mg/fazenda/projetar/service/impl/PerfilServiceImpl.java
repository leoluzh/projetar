package br.gov.mg.fazenda.projetar.service.impl;

import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.PerfilDao;
import br.gov.mg.fazenda.projetar.entity.security.Perfil;
import br.gov.mg.fazenda.projetar.service.PerfilService;


@ServicoPadrao
public class PerfilServiceImpl extends AbstractWriteableService<Perfil,Long> implements PerfilService {

	@Inject
	@DaoPadrao
	private PerfilDao dao;
	
	@Override
	public PerfilDao getDao() {
		return dao;
	}

}
