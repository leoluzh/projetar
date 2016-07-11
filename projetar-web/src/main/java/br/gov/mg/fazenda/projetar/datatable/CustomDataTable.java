package br.gov.mg.fazenda.projetar.datatable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

@SuppressWarnings("serial")
public class CustomDataTable extends DataTable implements Serializable {

	protected ValueExpression getFilterFacetValueExpression(){
		ValueExpression ve = getValueExpression("filterMap");
		return ve;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getFilters(){
		
		ValueExpression ve = getFilterFacetValueExpression();
		
		if( ve == null ){
			return super.getFilters();
		}
		
		Map<String,Object> map = (Map<String,Object>)
				ve.getValue(FacesContext.getCurrentInstance().getELContext());
		
		if( map == null ){
			return new HashMap<String,Object>();
		}else{
			return map;
		}
		
	}
	
	public void setFilters( Map<String,Object> filters ){
		
		ValueExpression ve = getFilterFacetValueExpression();
		
		if( ve == null ){
			super.setFilters(filters);
			return;
		}
		
		ve.setValue(FacesContext.getCurrentInstance().getELContext(), filters);
		
	}
	
}
