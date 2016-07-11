package br.gov.mg.fazenda.projetar.entity;

import java.util.Date;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @since 03/04/2016
 * @version 0.1
 *
 * @param <T>
 */

public interface IAuditableEntity<T> {

	public Date getDataAtualizacao();
	public void setDataAtualizacao( Date dataAtualizacao );
	public <Z> IAuditableEntity<T> withDataAtualizacao( Date dataAtualizacao );
	public Usuario getUsuarioAtualizacao();
	public void setUsuarioAtualizacao( Usuario usuarioAtualizacao );
	public <Z> IAuditableEntity<T> withUsuarioAtualizacao( Usuario usuarioAtualizacao );
	
}
