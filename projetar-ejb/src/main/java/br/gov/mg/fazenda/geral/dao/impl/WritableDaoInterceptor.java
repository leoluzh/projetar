package br.gov.mg.fazenda.geral.dao.impl;

import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@SuppressWarnings({"serial","cdi-missing-interceptor-binding"})
public class WritableDaoInterceptor extends AbstractDaoInterceptor {

	@Override
	public void doBeforeContextProceed(InvocationContext context) {
	
	}

	@Override
	public void doAfterContextProceed(InvocationContext context , Object resultProceed ) {

	}

}
