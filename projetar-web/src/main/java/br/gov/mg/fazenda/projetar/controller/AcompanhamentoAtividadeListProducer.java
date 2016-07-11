package br.gov.mg.fazenda.projetar.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.AcompanhamentoAtividade;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.service.AcompanhamentoAtividadeService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class AcompanhamentoAtividadeListProducer extends AbstractListProducer<AcompanhamentoAtividade,Long> {

	protected String getLoggerName(){
		return AcompanhamentoAtividadeListProducer.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	private AcompanhamentoAtividadeService acompanhamentoAtividadeService;
	
	@Override
	protected AcompanhamentoAtividadeService getService(){
		return this.acompanhamentoAtividadeService;
	}
	
	public void findAllByAtividade( final Atividade atividade ){
		//maneira elegante de se guardar uma consulta usando functional style.
		this.searcher = () -> {
			final Atividade _atividade = atividade ;
			this.searchedAt = new Date();
			return acompanhamentoAtividadeService.findAllByAtividade( _atividade ); 
		};
		search();
	}
	
	public void findAllByEtapaMarco( final EtapaMarco etapaMarco ){
		this.searcher = () -> {
			final EtapaMarco _etapaMarco = etapaMarco ;
			this.searchedAt = new Date();
			return acompanhamentoAtividadeService.findAllByEtapaMarco( _etapaMarco ) ; 
		};
		search();
	}
	
	public void findAllByResponsavel(){
		this.searcher = () -> {
			this.searchedAt = new Date();
			return acompanhamentoAtividadeService.buscarPorResponsavel( this.usuario );
		};
		search();
	}
	
	@Produces
	@Named("acompanhamentos")
	public List<AcompanhamentoAtividade> getAcompanhamentos(){
		return this.elements;
	}
	
}
