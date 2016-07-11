package br.gov.mg.fazenda.projetar.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;

import br.gov.mg.fazenda.projetar.entity.Notificacao;

@Model
@SuppressWarnings("serial")
public class NotificacoesController implements Serializable {

	public List<Notificacao> getNotificacoes(){
		return null;
	}
	
}
