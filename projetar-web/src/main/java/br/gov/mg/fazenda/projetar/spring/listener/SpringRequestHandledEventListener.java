package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Component
public class SpringRequestHandledEventListener extends SpringAbstractContextEventListener<RequestHandledEvent> {

}
