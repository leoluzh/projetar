package br.gov.mg.fazenda.projetar.spring.listener;

import org.springframework.stereotype.Component;

import br.gov.mg.fazenda.projetar.spring.event.LoginEvent;

@Component
public class LoginEventListener extends SpringAbstractContextEventListener<LoginEvent> {

}
