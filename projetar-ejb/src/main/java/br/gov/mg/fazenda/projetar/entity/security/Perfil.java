package br.gov.mg.fazenda.projetar.entity.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.AuditTable;

import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.gov.mg.fazenda.projetar.entity.KeyableAuditableEntity;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@XmlRootElement(name="Perfil",namespace="br.gov.mg.fazenda.projetar.entity.security")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBPERFIL",schema="PROJETAR",
uniqueConstraints={
		@UniqueConstraint(columnNames={"NM_PERFIL"})
})
@SequenceGenerator(
		name="SQPERFIL",
		sequenceName="SQPERFIL",
		schema="PROJETAR",
		allocationSize=1,initialValue=1)
@AuditTable(value="TBPERFIL_AUDIT",schema="PROJETAR")
@Audited
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class Perfil 
extends KeyableAuditableEntity<Perfil,Long> 
implements Serializable {

	public void doTrimBeforeSaveOrUpdate(){
		this.nome = StringUtils.trim( this.nome );
		this.nome = StringUtils.upperCase( this.nome );
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQPERFIL")
	@Column(name="SQ_PERFIL")
	private Long id;
	
	@NotBlank(message="Nome de um perfil não pode ser um valor em branco.")
	@NotEmpty(message="Nome de um perfil não pode ser vazio.")
	@NotNull(message="Nome de um perfil é obrigatório.")
	@Column(name="NM_PERFIL")
	private String nome;

	
	@NotNull(message="Uma descrição para um perfil deve ser obrigatoriamente informado.")
	@NotBlank(message="Uma descrição para um perfil não pode ser vazio.")
	@NotEmpty(message="Uma descrição para um perfil não pode ser vazio.")
	@Column(name="DS_PERFIL")
	private String descricao;
	
	@XmlElementWrapper(name="usuarios")
	@XmlElement(name="usuario")
	@ManyToMany(mappedBy="perfis")
	private List<Usuario> usuarios;
	
	@XmlElementWrapper(name="permissoes")
	@XmlElement(name="permissao")
	@ManyToMany
	@JoinTable(name="TBPERFIL_PERMISSAO",
		joinColumns={
			@JoinColumn(name="SQ_PERFIL",referencedColumnName="SQ_PERFIL")
		},
		inverseJoinColumns={
			@JoinColumn(name="SQ_PERMISSAO",referencedColumnName="SQ_PERMISSAO")
		}
	)
	private List<Permissao> permissoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
}
