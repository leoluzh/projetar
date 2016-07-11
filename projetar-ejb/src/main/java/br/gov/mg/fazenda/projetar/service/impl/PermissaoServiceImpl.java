package br.gov.mg.fazenda.projetar.service.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.PermissaoDao;
import br.gov.mg.fazenda.projetar.entity.security.Permissao;
import br.gov.mg.fazenda.projetar.service.PermissaoService;

@Stateless
@ServicoPadrao
public class PermissaoServiceImpl extends AbstractWriteableService<Permissao,Long> implements PermissaoService {

	@Inject
	@DaoPadrao
	private PermissaoDao dao;
	
	@Override
	public WritableDao<Permissao, Long> getDao() {
		return dao;
	}

}
