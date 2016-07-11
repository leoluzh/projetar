package br.gov.mg.fazenda.projetar.spring.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class LoginEvent extends ApplicationEvent {

	public LoginEvent(Object source) {
		super(source);
	}

	@Override
	public String toString(){
		return "Login Event: " + super.toString();
	}
	
}
