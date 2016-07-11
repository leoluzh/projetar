package br.gov.mg.fazenda.geral.anotacao;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;

/**
*
* @author leonardo luz fernandes <a href="mailto:leonardo.luz@fazenda.gov.br">leonardo.luz@fazenda.mg.gov.br</a> 
* @version 0.1
*/

@Stereotype
@Retention(RUNTIME)
@Target({METHOD, FIELD, TYPE})
public @interface ServicoRest {
	
}