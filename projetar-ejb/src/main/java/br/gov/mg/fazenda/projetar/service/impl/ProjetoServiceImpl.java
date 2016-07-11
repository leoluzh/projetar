package br.gov.mg.fazenda.projetar.service.impl;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.ProjetoDao;
import br.gov.mg.fazenda.projetar.entity.Fase;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.TipoProjeto;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.ProjetoService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 10/04/2016
 */

@Stateless
@ServicoPadrao
public class ProjetoServiceImpl 
	extends AbstractWriteableService<Projeto,Long> 
	implements  ProjetoService {

	@Inject
	@DaoPadrao
	private ProjetoDao dao;

	@Override
	public ProjetoDao getDao() {
		return dao;
	}
	
	
	@Override
	public List<Projeto> buscarPorAno(Date ano) {
		return getDao().buscarPorAno(ano);
	}

	@Override
	public List<Projeto> buscarPorNome(String nome) {
		return getDao().buscarPorNome(nome);
	}

	@Override
	public List<Projeto> buscarPorParteNome(String nome) {
		return getDao().buscarPorParteNome(nome);
	}

	@Override
	public List<Projeto> buscarPorGerente(Usuario usuario) {
		return getDao().buscarPorGerente(usuario);
	}

	@Override
	public List<Projeto> buscarPorInterlocutor(Usuario usuario) {
		return getDao().buscarPorInterlocutor(usuario);
	}

	@Override
	public List<Projeto> buscarPorUnidade(Unidade unidade) {
		return getDao().buscarPorUnidade(unidade);
	}

	@Override
	public List<Projeto> buscarPorUnidades( List<Unidade> unidades) {
		return getDao().buscarPorUnidades(unidades);
	}
	
	
	@Override
	public List<Projeto> buscarPorTipoProjeto(TipoProjeto tipoProjeto) {
		return getDao().buscarPorTipoProjeto(tipoProjeto);
	}

	@Override
	public List<Projeto> buscarPorTipoProjetoUnidade(TipoProjeto tipoProjeto, Unidade unidade) {
		return getDao().buscarPorTipoProjetoUnidade(tipoProjeto, unidade);
	}

	@Override
	public List<Projeto> buscarPorTiposProjetosUnidade(List<TipoProjeto> tiposProjetos, Unidade unidade) {
		return getDao().buscarPorTiposProjetosUnidade(tiposProjetos, unidade) ;
	}

	@Override
	public List<Projeto> buscarPorGerenteFase(Usuario usuario, Fase fase) {
		return getDao().buscarPorGerenteFase(usuario, fase);
	}

	@Override
	public List<Projeto> buscarPorInterlocutorFase(Usuario usuario, Fase fase) {
		return getDao().buscarPorInterlocutorFase(usuario, fase);
	}


}
