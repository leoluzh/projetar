package br.gov.mg.fazenda.projetar.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Unidade;
import br.gov.mg.fazenda.projetar.service.AtividadeService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class AtividadeListProducer extends AbstractListProducer<Atividade,Long> {

	protected String getLoggerName() {
		return AtividadeListProducer.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	private AtividadeService atividadeService;
	
	@Override
	protected AtividadeService getService() {
		return this.atividadeService;
	}
	
	public void findAllByEtapaMarco( EtapaMarco etapaMarco ){
		this.searcher = () -> {
			final EtapaMarco _etapaMarco = etapaMarco;
			this.searchedAt = new Date();
			return this.atividadeService.findAllByEtapaMarco( _etapaMarco );
		};
		search();
	}
	
	
	public void findAllByResponsavel(){
		this.searcher = () ->{
			this.searchedAt = new Date();
			return this.atividadeService.buscarPorResponsavel( this.usuario );
			
		};
		search();
	}
	
	public void findAllByUnidade( Unidade unidade ){
		this.searcher = () ->{
			final Unidade _unidade = unidade ;
			this.searchedAt = new Date();
			return this.atividadeService.buscarPorUnidade( _unidade );
		};
		search();
	}
	
	@Produces
	@Named(value="atividades")
	public List<Atividade> getAtividades(){
		return this.elements;
	}
	
	
}
