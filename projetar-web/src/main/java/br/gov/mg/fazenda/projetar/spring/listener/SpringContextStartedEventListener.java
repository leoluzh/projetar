package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextStartedEventListener extends SpringAbstractContextEventListener<ContextStartedEvent> {

}
