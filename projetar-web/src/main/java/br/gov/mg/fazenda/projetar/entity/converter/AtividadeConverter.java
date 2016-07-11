package br.gov.mg.fazenda.projetar.entity.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.Atividade;
import br.gov.mg.fazenda.projetar.service.AtividadeService;

@FacesConverter(forClass=Atividade.class)
public class AtividadeConverter implements Converter {

	@Inject
	@ServicoPadrao
	private AtividadeService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Atividade instance = null ;
		
		if( StringUtils.isNotEmpty( value ) ){
			Long id = Long.parseLong( value );
			instance = service.load( id );
		}
		
		return instance;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if( !Objects.isNull( value ) ){
			Atividade instance = (Atividade)value;
			return instance.getId() != null ? instance.getId().toString() : "";
		}
		
		return "";
	}

}
