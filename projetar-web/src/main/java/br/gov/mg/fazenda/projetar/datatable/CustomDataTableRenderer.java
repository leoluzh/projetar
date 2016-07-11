package br.gov.mg.fazenda.projetar.datatable;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.el.ValueExpression;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.model.SelectItem;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableRenderer;

@SuppressWarnings("serial")
public class CustomDataTableRenderer extends DataTableRenderer implements Serializable {


	@Override
	protected void encodeFilter( FacesContext context , DataTable table , UIColumn column ) throws IOException{
		
		Map<String,String> params = context.getExternalContext().getRequestParameterMap();
		ResponseWriter writer = context.getResponseWriter();
		String separator = String.valueOf(UINamingContainer.getSeparatorChar(context));
		
		ValueExpression filterBy = column.getValueExpression("filterBy");
		
		String filterField = null ;
		
		if( filterBy != null ){
			filterField = table.resolveStaticField(filterBy);
		}
		
		String filterId = column.getContainerClientId(context) + separator + "filter" ;
		String filterValue = params.containsKey( filterId ) && !table.isReset() 
				? params.get(filterId)
				: (String)table.getFilters().get( filterField );
				
		String filterStyleClass = column.getFilterStyleClass();
		
		if( column.getValueExpression("filterOptions") == null ){

			filterStyleClass = filterStyleClass == null 
					? DataTable.COLUMN_INPUT_FILTER_CLASS
					: DataTable.COLUMN_INPUT_FILTER_CLASS + " " + filterStyleClass ;
		
			writer.startElement("input", null );
			writer.writeAttribute("id", filterId , null );
			writer.writeAttribute("name", filterId, null);
			writer.writeAttribute("class", filterStyleClass, null);
			writer.writeAttribute("value", filterValue, null);
			writer.writeAttribute("autocomplete","off",  null);
			
			if( column.getFilterStyle() != null ){
				writer.writeAttribute("style", column.getFilterStyle(), null);
			}
			
			if( column.getFilterMaxLength() != Integer.MAX_VALUE ){
				writer.writeAttribute("maxLength", column.getFilterMaxLength() , null);
			}
			
			writer.endElement("input");
			
		}else{
			
			filterStyleClass = filterStyleClass == null 
					? DataTable.COLUMN_FILTER_CLASS
					: DataTable.COLUMN_FILTER_CLASS + " " + filterStyleClass ;
			
			writer.startElement("select",null);
			writer.writeAttribute("id", filterId, null);
			writer.writeAttribute("name", filterId, null);
			writer.writeAttribute("class", filterStyleClass, null);
			
			SelectItem[] items = getFilterOptions(column);
			
			for( SelectItem item : items ){
				Object itemValue = item.getValue();
				writer.startElement("option", null);
				writer.writeAttribute("value", item.getValue() , null);
				if( itemValue != null && String.valueOf(itemValue).equals(filterValue)){
					writer.writeAttribute("selected","selected", null);
				}
				writer.writeText(item.getLabel(), null);
				writer.endElement("option");
			}
			
			writer.endElement("select");
			
			
		}	
		
	}
	
}
