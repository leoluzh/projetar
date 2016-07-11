package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextClosedEventListener extends SpringAbstractContextEventListener<ContextClosedEvent> {

}
