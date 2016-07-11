package br.gov.mg.fazenda.projetar.service;

import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Servico;
import br.gov.mg.fazenda.geral.service.WriteableService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Servico
public interface AtividadeService extends WriteableService<Atividade,Long> {

	public List<Atividade> findAllByEtapaMarco(EtapaMarco etapaMarco);
	public List<Atividade> buscarPorResponsavel( Usuario usuario );
	public List<Atividade> buscarPorUnidade( Unidade unidade );
	public List<Atividade> buscarPorUnidades( List<Unidade> unidades );

}
