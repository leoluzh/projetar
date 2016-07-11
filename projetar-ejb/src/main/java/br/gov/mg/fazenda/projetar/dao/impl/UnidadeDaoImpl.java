package br.gov.mg.fazenda.projetar.dao.impl;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.UnidadeDao;
import br.gov.mg.fazenda.projetar.entity.Unidade;

@Stateless
@DaoPadrao
public class UnidadeDaoImpl extends AbstractWritableDao<Unidade,Long> implements UnidadeDao {

}
