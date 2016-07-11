package br.gov.mg.fazenda.projetar.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.Fase;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.entity.TipoProjeto;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.service.ProjetoService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/06/2015
 *
 */

@Named
@ViewScoped
@SuppressWarnings("serial")
public class ProjetoListProducer extends AbstractListProducer<Projeto,Long> {

	protected String getLoggerName() {
		return ProjetoListProducer.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	protected ProjetoService projetoService;
	
	protected ProjetoService getService(){
		return this.projetoService;
	}
	
	/**
	 * Projetos onde usuario esta como interlocutor.
	 */
	
	public void findAllByInterlocutor(){
		this.searcher = () -> { 
			this.searchedAt = new Date();
			return this.projetoService.buscarPorInterlocutor( this.usuario ); 
		};
		search();
	}
	
	public void findAllByIntelocutor( Fase fase ){
		this.searcher = () -> { 
			final Fase _fase = fase ;
			this.searchedAt = new Date();
			return this.projetoService.buscarPorInterlocutorFase( this.usuario, _fase ); 
		};
		this.elements = searcher.fetch();
	}

	/**
	 * Projetos onde usuario esta gerente.
	 */
	public void findAllByGerente(){
		this.searcher = () -> {
			this.searchedAt = new Date();
			return this.projetoService.buscarPorGerente( usuario );
		};
		search();
	}
	
	public void findAllByGerente( Fase fase ){
		this.searcher = () -> {
			final Fase _fase = fase;
			this.searchedAt = new Date();
			return this.projetoService.buscarPorGerenteFase( usuario , _fase );
		};
		search();
	}
	
	public void findAllByUnidade( Unidade unidade ){
		this.searcher = () -> {
			final Unidade _unidade = unidade;
			this.searchedAt = new Date();
			return this.projetoService.buscarPorUnidade( _unidade );
		};
		search();
	}

	public void findAllByUnidades( List<Unidade> unidades ){
		this.searcher = () -> {
			final List<Unidade> _unidades = unidades ;
			this.searchedAt = new Date();
			return this.projetoService.buscarPorUnidades( _unidades );
		};
		search();
	}

	public void findAllByTipoProjeto( TipoProjeto tipoProjeto ){
		this.searcher = () ->{ 
			final TipoProjeto _tipoProjeto = tipoProjeto;
			this.searchedAt = new Date();
			return this.projetoService.buscarPorTipoProjeto(_tipoProjeto);
		};
		search();
	}
	
	@Produces
	@Named("projetos")
	public List<Projeto> getProjetos(){
		return this.elements;
	}
	
	
}
