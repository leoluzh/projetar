package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Component
public class SpringContextStoppedEventListener extends SpringAbstractContextEventListener<ContextStoppedEvent> {

}
