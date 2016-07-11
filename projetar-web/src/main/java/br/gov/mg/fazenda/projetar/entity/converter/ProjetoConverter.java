package br.gov.mg.fazenda.projetar.entity.converter;

import java.util.Objects;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.projetar.entity.Programa;
import br.gov.mg.fazenda.projetar.entity.Projeto;
import br.gov.mg.fazenda.projetar.service.ProgramaService;

@FacesConverter(forClass=Projeto.class)
public class ProjetoConverter implements Converter {

	@Inject
	@ServicoPadrao
	private ProgramaService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		Programa instance = null;
		
		if( StringUtils.isNotEmpty( value ) ){
			Long id = Long.parseLong( value );
			instance = service.load( id ); 
		}
		
		return instance;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if( !Objects.isNull( value ) ){
			Projeto instance = (Projeto)value;
			return instance.getId() != null ? instance.getId().toString() : "";
		}
		
		return "";
	}

}
