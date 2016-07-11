package br.gov.mg.fazenda.projetar.dao.impl;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.OrcamentoDao;
import br.gov.mg.fazenda.projetar.entity.Orcamento;

@Stateless
@DaoPadrao
public class OrcamentoDaoImpl extends AbstractWritableDao<Orcamento,Long> implements OrcamentoDao {

}
