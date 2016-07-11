package br.gov.mg.fazenda.projetar.dao;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.entity.security.Permissao;

@Dao
public interface PermissaoDao extends WritableDao<Permissao,Long> {

}
