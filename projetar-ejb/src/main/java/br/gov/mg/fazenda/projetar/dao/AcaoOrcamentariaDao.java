package br.gov.mg.fazenda.projetar.dao;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.entity.AcaoOrcamentaria;

/**
 * 
 * @author leonardo luz fernandes
 * @since 27/04/2016
 * @version 0.1
 * 
 * @param <Entity>
 * @param <Key>
 */


@Dao
public interface AcaoOrcamentariaDao extends WritableDao<AcaoOrcamentaria, Long>{

}
