package br.gov.mg.fazenda.projetar.spring.publisher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import br.gov.mg.fazenda.projetar.spring.event.LoginEvent;

@Component
public class LoginEventPublisher implements ApplicationEventPublisherAware {

	private ApplicationEventPublisher publisher;
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	public void publish(){
		LoginEvent event = new LoginEvent(this);
		publisher.publishEvent(event);
	}
	
}
