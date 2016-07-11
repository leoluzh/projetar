package br.gov.mg.fazenda.projetar.service;

import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Servico;
import br.gov.mg.fazenda.geral.service.WriteableService;
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

@Servico
public interface AcompanhamentoAtividadeService extends WriteableService<AcompanhamentoAtividade,Long> {

	public List<AcompanhamentoAtividade> findAllByAtividade( Atividade atividade );
	public List<AcompanhamentoAtividade> findAllByEtapaMarco( EtapaMarco etapaMarco );
	public List<AcompanhamentoAtividade> buscarPorResponsavel( Usuario usuario );
	public List<AcompanhamentoAtividade> buscarPorUnidade( Unidade unidade );
	
}
