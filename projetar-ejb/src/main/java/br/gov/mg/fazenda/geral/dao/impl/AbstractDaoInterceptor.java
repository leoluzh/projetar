package br.gov.mg.fazenda.geral.dao.impl;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 30/04/2016
 *
 */

@SuppressWarnings("serial")
public abstract class AbstractDaoInterceptor implements Serializable {

	@AroundInvoke
	public Object intercept( InvocationContext context ) throws Exception {
		doBeforeContextProceed( context );
		Object result = context.proceed();
		doAfterContextProceed( context , result );
		return result;
	}
	
	public abstract void doBeforeContextProceed( InvocationContext context );
	public abstract void doAfterContextProceed( InvocationContext context , Object resultProceed );
	
}
