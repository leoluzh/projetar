package br.gov.mg.fazenda.projetar.service;

import java.util.List;
import java.util.Optional;

import br.gov.mg.fazenda.geral.anotacao.Servico;
import br.gov.mg.fazenda.geral.service.WriteableService;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

@Servico
public interface UsuarioService extends WriteableService<Usuario,Long> {
	
	public Optional<Usuario> findByUsername( String username );
	public Optional<Usuario> findByEmail( String email );
	public List<Usuario> findAllOrderByNome();
	
}
