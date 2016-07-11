package br.gov.mg.fazenda.projetar.dao;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.entity.Orcamento;

@Dao
public interface OrcamentoDao extends WritableDao<Orcamento,Long> {

}
