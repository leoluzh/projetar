package br.gov.mg.fazenda.projetar.xml.adapter;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 14/06/2016
 */

public class DateTimeAdapter extends XmlAdapter<String,Date> {

	private static Logger logger = Logger.getLogger(DateTimeAdapter.class.getName());
	private static final String ISO_8601_PATTERN = "YYYY-MM-DDThh:mm:ssTZD";

	private DateTimeFormatter formatter;
	
	public DateTimeAdapter() {
		formatter = DateTimeFormat.forPattern(ISO_8601_PATTERN);
	}
	
	@Override
	public Date unmarshal( String value ) throws Exception {
		try{
			LocalDateTime dt = formatter.parseLocalDateTime( value );
			return dt.toDate();
		}catch(Exception ex){
			logger.log(Level.WARNING, String.format("Fail to parse date %s", value), ex);
		}
		return null;
	}

	@Override
	public String marshal(Date value) throws Exception {
		if( !Objects.isNull( value ) ){
			try{
				return new LocalDateTime( value ).toString(ISO_8601_PATTERN);
			}catch(Exception ex){
				logger.log(Level.WARNING, String.format("Fail to format %s", value.toString() ), ex);
			}
		}
		return null;
	}
	
}
