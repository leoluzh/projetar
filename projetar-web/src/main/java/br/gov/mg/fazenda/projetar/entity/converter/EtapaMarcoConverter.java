package br.gov.mg.fazenda.projetar.entity.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.EtapaMarco;
import br.gov.mg.fazenda.projetar.service.EtapaMarcoService;

@FacesConverter(forClass=EtapaMarco.class)
public class EtapaMarcoConverter implements Converter {

	@Inject
	@ServicoPadrao
	private EtapaMarcoService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		EtapaMarco instance = null;
		
		if( StringUtils.isNotEmpty( value ) ){
			Long id = Long.parseLong( value );
			instance = service.load( id );
		}
		
		return instance;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if( !Objects.isNull( value ) ){
			EtapaMarco instance = (EtapaMarco)value;
			return instance.getId() != null ? instance.getId().toString() : "" ;
		}
		
		return "";
	}

}
