package br.gov.mg.fazenda.projetar.dao.impl;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.ProgramaDao;
import br.gov.mg.fazenda.projetar.entity.Programa;

@Stateless
@DaoPadrao
public class ProgramaDaoImpl extends AbstractWritableDao<Programa,Long> implements ProgramaDao {

}
