package br.gov.mg.fazenda.projetar.service.impl;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.gov.mg.fazenda.geral.anotacao.DaoPadrao;
import br.gov.mg.fazenda.geral.anotacao.ServicoPadrao;
import br.gov.mg.fazenda.geral.service.impl.AbstractWriteableService;
import br.gov.mg.fazenda.projetar.dao.UsuarioDao;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.service.UsuarioService;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 14/04/2016
 *
 */

@Stateless
@ServicoPadrao
public class UsuarioServiceImpl 
	extends AbstractWriteableService<Usuario,Long> 
	implements UsuarioService {

	@Inject
	@DaoPadrao
	private UsuarioDao dao;

	@Override
	public UsuarioDao getDao() {
		return this.dao;
	}
	
	public Optional<Usuario> findByUsername( String username ){
		return this.getDao().findByUsername( username );
	}
	
	public Optional<Usuario> findByEmail( String email ){
		return this.getDao().findByEmail( email );
	}
	
	public List<Usuario> findAllOrderByNome(){
		return this.getDao().findAllOrderByNome();
	}
	
}
