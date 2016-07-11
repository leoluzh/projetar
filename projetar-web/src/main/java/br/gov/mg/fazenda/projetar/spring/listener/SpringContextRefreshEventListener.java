package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextRefreshEventListener extends SpringAbstractContextEventListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		super.onApplicationEvent(event);
	}

}
