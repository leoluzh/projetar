package br.gov.mg.fazenda.projetar.dao;

import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Dao
public interface AtividadeDao extends WritableDao<Atividade,Long> {

	public List<Atividade> findAllByEtapaMarco(EtapaMarco etapaMarco);
	public List<Atividade> buscarPorResponsavel( Usuario usuario );
	public List<Atividade> buscarPorUnidade( Unidade unidade );
	public List<Atividade> buscarPorUnidades( List<Unidade> unidades );
	
}
