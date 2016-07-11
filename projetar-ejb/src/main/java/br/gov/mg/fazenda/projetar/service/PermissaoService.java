package br.gov.mg.fazenda.projetar.service;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.service.WriteableService;
import br.gov.mg.fazenda.projetar.entity.security.Permissao;

@Dao
public interface PermissaoService extends WriteableService<Permissao,Long>{

}
