package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.util.Locale;

import org.joda.time.DateTime;
/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@SuppressWarnings("serial")
public class Mes implements Serializable {

	private int mes;
	
	public Mes( int mes ){
		this.mes = mes;
	}
	
	public String getAbreviacao(){
		String mes = getAbreviacaoCompleta();
		return mes.toUpperCase().substring(0,1);
	}

	public String getAbreviacaoCompleta(){
		String mes = (new DateTime())
				.withDayOfMonth(1)
				.withMonthOfYear( this.mes )
				.toString("MMM",new Locale("pt","BR"));
		return mes.toUpperCase();
	}
	
	public String getTexto(){
		String mes = (new DateTime())
				.withDayOfMonth(1)
				.withMonthOfYear( this.mes )
				.toString("MMMM",new Locale("pt","BR"));
		return mes;
	}
	
	public int getMes(){
		return this.mes;
	}
	
	public void setMes( int mes ){
		this.mes = mes ;
	}
	
}
