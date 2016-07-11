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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RevisionEntity;

import br.gov.mg.fazenda.projetar.entity.listener.KeyableAuditableEntityListener;
import br.gov.mg.fazenda.projetar.entity.security.Usuario;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 0.1
 * @param <K>
 */

@RevisionEntity(KeyableAuditableEntityListener.class)
@Audited
@MappedSuperclass
@SuppressWarnings("serial")
public abstract class KeyableAuditableEntity<K,T> extends KeyableEntity<T> implements IKeyableAuditableEntity<K,T> {


	@PrePersist
	@PreUpdate
	public void doBeforeSaveOrUpdate(){
		this.dataAtualizacao = new Date();
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_ATUALIZACAO")
	private Date dataAtualizacao;
	
	//@NotNull
	@Cascade(value=CascadeType.ALL)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_ATUALIZACAO",referencedColumnName="SQ_USUARIO")
	private Usuario usuarioAtualizacao;
	
	@Override
	public Date getDataAtualizacao() {
		return this.dataAtualizacao;
	}
	
	@Override
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao ;
	}

	@Override
	public <Z> KeyableAuditableEntity<K,T> withDataAtualizacao(Date dataAtualizacao) {
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
	
	@Override
	public <Z> KeyableAuditableEntity<K, T> withUsuarioAtualizacao( Usuario usuarioAtualizacao ){
		setUsuarioAtualizacao(usuarioAtualizacao);
		return this;
	}
	
}
