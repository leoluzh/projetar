package br.gov.mg.fazenda.projetar.util;

import java.util.Comparator;

import org.apache.commons.collections4.comparators.NullComparator;

public class NumberAsDoubleComparator implements Comparator<Number> {

	private NullComparator<Double> delegate; 

	public NumberAsDoubleComparator( boolean nullAsHigh ){
		delegate = new NullComparator<>( nullAsHigh );
	}
	
	@Override
	public int compare( Number o1, Number o2 ) {
		return delegate.compare( 
				o1 != null ? new Double( o1.doubleValue() ) : null , 
				o2 != null ? new Double( o2.doubleValue() ) : null );
	}

	public static NumberAsDoubleComparator getComparator( boolean nullAsHigh ){
		return new NumberAsDoubleComparator(nullAsHigh);
	}

}
