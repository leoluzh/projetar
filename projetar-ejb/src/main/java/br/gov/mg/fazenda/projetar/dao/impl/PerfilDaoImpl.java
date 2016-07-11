package br.gov.mg.fazenda.projetar.dao.impl;

import javax.ejb.Stateless;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.dao.impl.AbstractWritableDao;
import br.gov.mg.fazenda.projetar.dao.PerfilDao;
import br.gov.mg.fazenda.projetar.entity.security.Perfil;

@Stateless
@DaoPadrao
public class PerfilDaoImpl extends AbstractWritableDao<Perfil,Long> implements PerfilDao {

}
