package br.gov.mg.fazenda.projetar.functional;

import java.util.List;

@FunctionalInterface
public interface QuerySearch<T> {

	List<T> fetch();
	
}
