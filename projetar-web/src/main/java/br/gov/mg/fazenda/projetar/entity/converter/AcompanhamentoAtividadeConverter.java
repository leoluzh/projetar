package br.gov.mg.fazenda.projetar.entity.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.AcompanhamentoAtividade;
import br.gov.mg.fazenda.projetar.service.AcompanhamentoAtividadeService;

@FacesConverter(forClass=AcompanhamentoAtividade.class)
public class AcompanhamentoAtividadeConverter implements Converter {

	@Inject
	@ServicoPadrao
	private AcompanhamentoAtividadeService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		AcompanhamentoAtividade instance = null;
		
		if( StringUtils.isNotEmpty( value ) ){
			Long id = Long.parseLong( value );
			instance = service.load( id );
		}
		
		return instance;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
	
		if( !Objects.isNull( value ) ){
			AcompanhamentoAtividade instance = (AcompanhamentoAtividade)value;
			return instance != null ? instance.getId().toString() : "" ;
		}
		
		return "";
	}

}
