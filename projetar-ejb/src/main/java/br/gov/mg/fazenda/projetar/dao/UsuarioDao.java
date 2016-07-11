package br.gov.mg.fazenda.projetar.dao;

import java.util.List;
import java.util.Optional;

import br.gov.mg.fazenda.geral.anotacao.Dao;
import br.gov.mg.fazenda.geral.dao.WritableDao;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

@Dao
public interface UsuarioDao extends WritableDao<Usuario,Long> {

	public Optional<Usuario> findByUsername( String username );
	public Optional<Usuario> findByEmail( String email );
	public List<Usuario> findAllOrderByNome();
	
}
