package br.gov.mg.fazenda.projetar.controller;

import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.service.EtapaMarcoService;

@Named
@ViewScoped
@SuppressWarnings("serial")
public class EtapaMarcoListProducer extends AbstractListProducer<EtapaMarco,Long> {

	protected String getLoggerName() {
		return EtapaMarcoListProducer.class.getName();
	}
	
	@Inject
	@ServicoPadrao
	private EtapaMarcoService etapaMarcoService;

	@Override
	protected EtapaMarcoService getService() {
		return this.etapaMarcoService;
	}
	
	public void findAllByProjeto( Projeto projeto ){
		this.searcher = () -> {
			final Projeto _projeto = projeto ;
			this.searchedAt = new Date();
			return this.etapaMarcoService.findAllByProjeto( _projeto );
		};
		search();
	}

	public void findAllByInterlocutor(){
		this.searcher = () -> {
			this.searchedAt = new Date();
			return this.etapaMarcoService.buscarPorInterlocutor( this.usuario );
		};
		search();
	}

	public void findAllByResponsavel(){
		this.searcher = () -> {
			this.searchedAt = new Date();
			return this.etapaMarcoService.buscarPorResponsavel( this.usuario );
		};
		search();
	}

	@Produces
	@Named(value="etapasMarco")
	public List<EtapaMarco> getEtapasMarco(){
		return getElements();
	}
	
}
