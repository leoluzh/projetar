package br.gov.mg.fazenda.projetar.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.EtapaMarcoDao;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Stateless
@DaoPadrao
public class EtapaMarcoDaoImpl extends AbstractWritableDao<EtapaMarco,Long> implements EtapaMarcoDao {

	@Override
	public List<EtapaMarco> findAllByProjeto( Projeto projeto ) {
		
		String query = "SELECT e FROM EtapaMarco e WHERE e.projeto = :pProjeto " ;
		
		List<EtapaMarco> resultado = 
		this.getEntityManager().createQuery( query , EtapaMarco.class )
		.setParameter("pProjeto", projeto )
		.getResultList();
		
		return resultado;
	}

	public List<EtapaMarco> buscarPorResponsavel( Usuario usuario ){
		
		String query = "SELECT e FROM EtapaMarco e WHERE e.responsavel = :pResponsavel ";
		List<EtapaMarco> resultado = 
		this.getEntityManager().createQuery(query,EtapaMarco.class)
		.setParameter("pResponsavel", usuario )
		.getResultList();
				
		return resultado;
		
	}
	
	public List<EtapaMarco> buscarPorInterlocutor( Usuario usuario ){

		String query = "SELECT e FROM EtapaMarco e WHERE e.interlocutor = :pInterlocutor ";
		List<EtapaMarco> resultado = 
		this.getEntityManager().createQuery(query,EtapaMarco.class)
		.setParameter("pInterlocutor", usuario )
		.getResultList();
				
		return resultado;
		
	}
		
}
