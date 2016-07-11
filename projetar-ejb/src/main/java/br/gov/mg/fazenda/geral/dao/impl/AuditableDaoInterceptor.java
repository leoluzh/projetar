package br.gov.mg.fazenda.geral.dao.impl;

import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@SuppressWarnings({"serial", "cdi-missing-interceptor-binding"})
public class AuditableDaoInterceptor extends AbstractDaoInterceptor {

	@Override
	public void doBeforeContextProceed(InvocationContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void doAfterContextProceed(InvocationContext context , Object resultProceed ) {
		// TODO Auto-generated method stub
	}

}
