package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public abstract class SpringAbstractContextEventListener<E extends ApplicationEvent> implements ApplicationListener<E> {

	@Override
	public void onApplicationEvent(E event) {
		System.out.println("Source:" + event.getSource() );
		System.out.println("Timestamp: " + event.getTimestamp() );
	}

}
