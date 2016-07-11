package br.gov.mg.fazenda.projetar.dao;

import java.util.List;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 * 
 */

@Dao
public interface EtapaMarcoDao extends WritableDao<EtapaMarco, Long> {

	public List<EtapaMarco> findAllByProjeto( Projeto projeto );
	public List<EtapaMarco> buscarPorResponsavel( Usuario usuario );
	public List<EtapaMarco> buscarPorInterlocutor( Usuario usuario );
	
}
