package br.gov.mg.fazenda.projetar.dao.impl;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.ProjetoDao;
import br.gov.mg.fazenda.projetar.entity.Fase;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.TipoProjeto;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

@Stateless
@DaoPadrao
public class ProjetoDaoImpl extends AbstractWritableDao<Projeto,Long> implements ProjetoDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorAno(Date ano) {
		
		String query = "SELECT p FROM Projeto p WHERE p.ano = :pAno ORDER BY p.unidade.nome , p.ano , p.nome  ASC" ;
		
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pAno", ano )
		.getResultList();
		
		return projetos;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorNome( String nome ){

		String query = "SELECT p FROM Projeto p WHERE p.nome = :pNome ORDER BY p.unidade.nome , p.ano , p.nome  ASC" ;
		
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pNome", nome )
		.getResultList();
		
		return projetos;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorParteNome( String nome ){

		String query = "SELECT p FROM Projeto p WHERE p.nome LIKE :pNome ORDER BY p.unidade.nome , p.ano , p.nome  ASC" ;
		
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pNome", "%" + nome + "%" )
		.getResultList();
		
		return projetos;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorGerente(Usuario usuario) {
		
		String query = "SELECT p FROM Projeto p WHERE p.gerente = :pGerente ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pGerente", usuario )
		.getResultList();
		
		return projetos;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorInterlocutor(Usuario usuario) {

		String query = "SELECT p FROM Projeto p WHERE p.interlocutor = :pInterlocutor ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pInterlocutor", usuario )
		.getResultList();
		
		return projetos;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorUnidade( Unidade unidade ){
		
		String query = "SELECT p FROM Projeto p WHERE p.unidade = :pUnidade ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pUnidade", unidade )
		.getResultList();
		
		return projetos;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorUnidades( List<Unidade> unidades ){
		
		String query = "SELECT p FROM Projeto p WHERE p.unidade IN :pUnidades ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pUnidades", unidades )
		.getResultList();
		
		return projetos;
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorTipoProjeto(TipoProjeto tipoProjeto) {

		String query = "SELECT p FROM Projeto p WHERE p.unidade = :pUnidade ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pTipoProjeto", tipoProjeto )
		.getResultList();
		
		return projetos;
		
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorTipoProjetoUnidade(TipoProjeto tipoProjeto, Unidade unidade) {

		String query = "SELECT p FROM Projeto p WHERE p.tipoProjeto = :pTipoProjeto AND p.unidade = :pUnidade ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pTipoProjeto", tipoProjeto )
		.setParameter("pUnidade", unidade )
		.getResultList();
		
		return projetos;
	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorTiposProjetosUnidade(List<TipoProjeto> tiposProjetos, Unidade unidade) {

		String query = "SELECT p FROM Projeto p WHERE p.tipoProjeto IN :pTiposProjetos AND p.unidade = :pUnidade ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pTipoProjetos", tiposProjetos )
		.setParameter("pUnidade", unidade )
		.getResultList();
		
		return projetos;
	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorGerenteFase(Usuario usuario, Fase fase) {

		String query = "SELECT p FROM Projeto p WHERE p.gerente = :pGerente AND p.fase = :pFase ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pGerente", usuario )
		.setParameter("pFase", fase )
		.getResultList();
		
		return projetos;
	
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Projeto> buscarPorInterlocutorFase(Usuario usuario, Fase fase) {

		String query = "SELECT p FROM Projeto p WHERE p.interlocutor = :pInterlocutor AND p.fase = :pFase ORDER BY p.unidade.nome , p.ano , p.nome  ASC";
		List<Projeto> projetos = this.getEntityManager()
		.createQuery( query )
		.setParameter("pInterlocutor", usuario )
		.setParameter("pFase", fase )
		.getResultList();
		
		return projetos;
		
	}
	
}
