package br.gov.mg.fazenda.projetar.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.EtapaMarcoDao;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.EtapaMarcoService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 * 
 */

@Stateless
@ServicoPadrao
public class EtapaMarcoServiceImpl 
	extends AbstractWriteableService<EtapaMarco, Long> 
	implements EtapaMarcoService {

	@Inject
	@DaoPadrao
	private EtapaMarcoDao dao;

	@Override
	public EtapaMarcoDao getDao() {
		return dao;
	}
	
	public List<EtapaMarco> findAllByProjeto( Projeto projeto ){
		return getDao().findAllByProjeto( projeto );
	}
	
	public List<EtapaMarco> buscarPorResponsavel( Usuario usuario ){
		return getDao().buscarPorResponsavel( usuario );
	}
	
	public List<EtapaMarco> buscarPorInterlocutor( Usuario usuario ){
		return getDao().buscarPorInterlocutor( usuario );
	}
	
}
