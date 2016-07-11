package br.gov.mg.fazenda.projetar.dao.impl;

import java.util.List;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.AtividadeDao;
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
public class AtividadeDaoImpl extends AbstractWritableDao<Atividade,Long> implements AtividadeDao {

	@Override
	public List<Atividade> findAllByEtapaMarco(EtapaMarco etapaMarco) {
		
		String query = "SELECT a FROM Atividade a WHERE a.etapa = :pEtapaMarco " ;
		
		List<Atividade> resultado = 
		this.getEntityManager().createQuery( query , Atividade.class )
		.setParameter("pEtapaMarco", etapaMarco )
		.getResultList();
		
		return resultado;
	}

	@Override
	public List<Atividade> buscarPorResponsavel(Usuario usuario) {
		String query = "SELECT a FROM Atividade a WHERE a.responsavel = :pResponsavel " ;
		
		List<Atividade> resultado = 
		this.getEntityManager().createQuery( query , Atividade.class )
		.setParameter("pResponsavel", usuario )
		.getResultList();
		
		return resultado;
	}

	@Override
	public List<Atividade> buscarPorUnidade(Unidade unidade) {
		
		String query = "SELECT a FROM Atividade a WHERE a.etapa.projeto.unidade = :pUnidade " ;
		
		List<Atividade> resultado = 
		this.getEntityManager().createQuery( query , Atividade.class )
		.setParameter("pUnidade", unidade )
		.getResultList();
		
		return resultado;
	}

	@Override
	public List<Atividade> buscarPorUnidades( List<Unidade> unidades ) {
		
		String query = "SELECT a FROM Atividade a WHERE a.etapa.projeto.unidade IN :pUnidades " ;
		
		List<Atividade> resultado = 
		this.getEntityManager().createQuery( query , Atividade.class )
		.setParameter("pUnidades", unidades )
		.getResultList();
		
		return resultado;
	}
	
	
}
