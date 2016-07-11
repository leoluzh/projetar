package br.gov.mg.fazenda.projetar.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.AcompanhamentoAtividadeDao;
import br.gov.mg.fazenda.projetar.entity.AcompanhamentoAtividade;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.AcompanhamentoAtividadeService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Stateless
@ServicoPadrao
public class AcompanhamentoAtividadeServiceImpl 
	extends AbstractWriteableService<AcompanhamentoAtividade,Long> 
	implements AcompanhamentoAtividadeService {

	@Inject
	@DaoPadrao
	private AcompanhamentoAtividadeDao dao;

	@Override
	public AcompanhamentoAtividadeDao getDao() {
		return dao;
	}
	
	@Override
	public List<AcompanhamentoAtividade> findAllByAtividade( Atividade atividade ){
		return getDao().findAllByAtividade( atividade );
	}
	
	@Override
	public List<AcompanhamentoAtividade> findAllByEtapaMarco( EtapaMarco etapaMarco ){
		return getDao().findAllByEtapaMarco( etapaMarco );
	}
	
	@Override
	public List<AcompanhamentoAtividade> buscarPorResponsavel( Usuario usuario ){
		return getDao().buscarPorResponsavel(usuario);
	}

	@Override
	public List<AcompanhamentoAtividade> buscarPorUnidade(Unidade unidade) {
		return getDao().buscarPorUnidade(unidade);
	}
	
}
