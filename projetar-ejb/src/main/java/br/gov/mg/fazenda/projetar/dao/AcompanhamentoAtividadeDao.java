package br.gov.mg.fazenda.projetar.dao;

import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.entity.AcompanhamentoAtividade;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Dao
public interface AcompanhamentoAtividadeDao extends WritableDao<AcompanhamentoAtividade,Long> {

	public List<AcompanhamentoAtividade> findAllByAtividade( Atividade atividade );
	public List<AcompanhamentoAtividade> findAllByEtapaMarco( EtapaMarco etapaMarco );
	public List<AcompanhamentoAtividade> buscarPorResponsavel( Usuario usuario );
	public List<AcompanhamentoAtividade> buscarPorUnidade( Unidade unidade );
	
}
