package br.gov.mg.fazenda.projetar.entity.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.AcaoOrcamentaria;
import br.gov.mg.fazenda.projetar.service.AcaoOrcamentariaService;

@FacesConverter(forClass=AcaoOrcamentaria.class)
public class AcaoOrcamentariaConverter implements Converter {

	@Inject
	@ServicoPadrao
	private AcaoOrcamentariaService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		AcaoOrcamentaria instance = null;
		
		if( StringUtils.isNotEmpty( value ) ){
			Long id = Long.parseLong( value );
			instance = service.load( id );
		}
		
		return instance;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if( !Objects.isNull( value ) ){
			AcaoOrcamentaria instance = (AcaoOrcamentaria)value;
			return instance != null ? instance.getId().toString() : "";
		}
		
		return "";
	}

}
