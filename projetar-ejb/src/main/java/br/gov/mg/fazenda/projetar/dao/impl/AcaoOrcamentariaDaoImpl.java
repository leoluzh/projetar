package br.gov.mg.fazenda.projetar.dao.impl;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.AcaoOrcamentariaDao;
import br.gov.mg.fazenda.projetar.entity.AcaoOrcamentaria;

@Stateless
@DaoPadrao
public class AcaoOrcamentariaDaoImpl extends AbstractWritableDao<AcaoOrcamentaria,Long> implements AcaoOrcamentariaDao {

}
