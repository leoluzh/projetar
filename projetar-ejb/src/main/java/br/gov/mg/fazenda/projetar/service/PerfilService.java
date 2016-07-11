package br.gov.mg.fazenda.projetar.service;

import br.gov.mg.fazenda.geral.anotacao.Servico;
import br.gov.mg.fazenda.geral.service.WriteableService;
import br.gov.mg.fazenda.projetar.entity.security.Perfil;

@Servico
public interface PerfilService extends WriteableService<Perfil,Long> {

}
