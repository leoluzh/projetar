package br.gov.mg.fazenda.projetar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@Audited
@MappedSuperclass
public abstract class AuditableEntity<K> implements IAuditableEntity<K> {

	@PrePersist
	@PreUpdate
	public void doBeforeSaveOrUpdate(){
		this.dataAtualizacao = new Date();
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_ATUALIZACAO")
	private Date dataAtualizacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_ATUALIZACAO")
	private Usuario usuarioAtualizacao;
	
	@Override
	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}

	@Override
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	@Override
	public <T> IAuditableEntity<K> withDataAtualizacao( Date dataAtualizacao ){
		setDataAtualizacao(dataAtualizacao);
		return this;
	}

	@Override
	public Usuario getUsuarioAtualizacao() {
		return this.usuarioAtualizacao;
	}

	@Override
	public void setUsuarioAtualizacao(Usuario usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}

	public <T> IAuditableEntity<K> withUsuarioAtualizacao( Usuario usuarioAtualizacao ){
		setUsuarioAtualizacao(usuarioAtualizacao);
		return this;
	} 
	
}
