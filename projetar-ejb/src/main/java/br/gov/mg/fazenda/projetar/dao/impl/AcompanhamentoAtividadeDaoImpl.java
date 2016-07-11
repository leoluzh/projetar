package br.gov.mg.fazenda.projetar.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.AcompanhamentoAtividadeDao;
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


@Stateless
@DaoPadrao
public class AcompanhamentoAtividadeDaoImpl extends AbstractWritableDao<AcompanhamentoAtividade,Long> implements AcompanhamentoAtividadeDao {

	@Override
	public List<AcompanhamentoAtividade> findAllByAtividade(Atividade atividade) {
		
		String query = "SELECT a FROM AcompanhamentoAtividade a WHERE a.atividade = :pAtividade ORDER BY a.data ASC" ;
		
		List<AcompanhamentoAtividade> resultado = 
		this.getEntityManager().createQuery( query , AcompanhamentoAtividade.class )
		.setParameter("pAtividade", atividade )
		.getResultList();
		
		return resultado;
	}

	@Override
	public List<AcompanhamentoAtividade> findAllByEtapaMarco( EtapaMarco etapaMarco ){
		
		String query = "SELECT a FROM AcompanhamentoAtividade a WHERE a.atividade.etapa = :pEtapaMarco " ;
		
		List<AcompanhamentoAtividade> resultado = 
		this.getEntityManager().createQuery( query , AcompanhamentoAtividade.class )
		.setParameter( "pEtapaMarco" , etapaMarco )
		.getResultList();
		
		return resultado;
		
	}

	@Override
	public List<AcompanhamentoAtividade> buscarPorResponsavel(Usuario usuario) {
		
		String query = "SELECT a FROM AcompanhamentoAtividade a WHERE a.responsavel = :pResponsavel ";
		
		List<AcompanhamentoAtividade> resultado =
		this.getEntityManager().createQuery(query,AcompanhamentoAtividade.class)
		.setParameter("pResponsavel", usuario)
		.getResultList();
		
		return resultado;
	}

	@Override
	public List<AcompanhamentoAtividade> buscarPorUnidade(Unidade unidade) {
		
		String query = "SELECT a FROM AcompanhamentoAtividade a WHERE a.atividade.etapa.projeto.unidade = :pUnidade ";
		
		List<AcompanhamentoAtividade> resultado =
		this.getEntityManager().createQuery(query,AcompanhamentoAtividade.class)
		.setParameter("pResponsavel", unidade)
		.getResultList();
		
		return resultado;
	}
	
	
	
}
