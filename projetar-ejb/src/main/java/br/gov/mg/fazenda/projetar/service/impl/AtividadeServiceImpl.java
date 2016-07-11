package br.gov.mg.fazenda.projetar.service.impl;

import br.gov.mg.fazenda.projetar.dao.AtividadeDao;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.AtividadeService;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Stateless
@ServicoPadrao
public class AtividadeServiceImpl 
	extends AbstractWriteableService<Atividade,Long>  
	implements AtividadeService {

	@Inject
	@DaoPadrao
	private AtividadeDao dao;

	@Override
	public AtividadeDao getDao() {
		return dao;
	}

	@Override
	public List<Atividade> findAllByEtapaMarco(EtapaMarco etapaMarco) {
		return getDao().findAllByEtapaMarco( etapaMarco );
	}

	@Override
	public List<Atividade> buscarPorResponsavel(Usuario usuario) {
		return getDao().buscarPorResponsavel( usuario );
	}

	@Override
	public List<Atividade> buscarPorUnidade(Unidade unidade) {
		return getDao().buscarPorUnidade( unidade );
	}
	
	@Override
	public List<Atividade> buscarPorUnidades( List<Unidade> unidades) {
		return getDao().buscarPorUnidades( unidades );
	}
	
}
