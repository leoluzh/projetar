package br.gov.mg.fazenda.projetar.dao.impl;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.ModuloDao;
import br.gov.mg.fazenda.projetar.entity.Modulo;

@Stateless
@DaoPadrao
public class ModuloDaoImpl extends AbstractWritableDao<Modulo,Long> implements ModuloDao {

}
