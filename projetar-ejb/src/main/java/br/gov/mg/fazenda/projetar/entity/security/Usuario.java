package br.gov.mg.fazenda.projetar.entity.security;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.gov.mg.fazenda.projetar.entity.KeyableAuditableEntity;
import br.gov.mg.fazenda.projetar.util.PatternsUtils;
import br.gov.mg.fazenda.projetar.xml.adapter.DateTimeAdapter;


/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 08/04/2016
 *
 */

@XmlRootElement(name="Usuario",namespace="br.gov.mg.fazenda.projetar.entity.security")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBUSUARIO", schema="PROJETAR" ,
	uniqueConstraints={
			@UniqueConstraint(columnNames={"NM_USERNAME"}) ,
			@UniqueConstraint(columnNames={"NM_EMAIL"})
})
@SequenceGenerator(
		name="SQUSUARIO",
		sequenceName="SQUSUARIO",
		schema="PROJETAR",
		allocationSize=1,initialValue=1)
@AuditTable(value="TBUSUARIO_AUDIT",schema="PROJETAR")
@Audited
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class Usuario 
	extends KeyableAuditableEntity<Usuario,Long> 
	implements Serializable {

	@PrePersist
	@PreUpdate
	public void doNormalizeBeforeSafeOrUpdate(){
		this.username = StringUtils.trim( this.username );
		this.username = StringUtils.lowerCase( this.username );
		this.nome = StringUtils.trim( this.nome );
		this.email = StringUtils.trim( this.email );
		this.email = StringUtils.lowerCase( this.email );
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQUSUARIO")
	@Column(name="SQ_USUARIO")
	private Long id;
	
	@NotNull(message="Username não pode ser um valor nulo.")
	@NotBlank(message="Username não pode ser um valor em branco.")
	@NotEmpty(message="Username não pode ser um valor vazio.")
	@Column(name="NM_USERNAME")
	private String username;

	@NotNull(message="Nome de usuário não pode ser um valor nulo.")
	@NotBlank(message="Nome de usuário não pode ser um valor em branco.")
	@NotEmpty(message="Nome de usuário não pode ser um valor vazio.")
	@Column(name="NM_NOME")
	private String nome;

	@Email(message="Email inválido.",regexp=PatternsUtils.EMAIL)
	@NotNull(message="Email não pode ser um valor nulo.")
	@NotBlank(message="Email não pode ser um valor em branco.")
	@NotEmpty(message="Email não pode ser um valor vazio.")	
	@Column(name="NM_EMAIL")
	private String email;

	@XmlTransient
	@NotNull(message="Senha não pode ser um valor nulo.")
	@NotBlank(message="Senha não pode ser um valor em branco.")
	@NotEmpty(message="Senha não pode ser um valor vazio.")	
	@Column(name="NM_SENHA")
	private String senha;
	
	@Column(name="FL_HABILITADO")
	private Boolean habilitado = true;

	@XmlJavaTypeAdapter(value=DateTimeAdapter.class)
	@Column(name="DT_BLOQUEIO_CONTA")
	private Date dataBloqueioConta;
	
	@XmlJavaTypeAdapter(value=DateTimeAdapter.class)
	@Column(name="DT_EXPIRACAO_CONTA")
	private Date dataExpiracaoConta;
	
	@XmlJavaTypeAdapter(value=DateTimeAdapter.class)
	@Column(name="DT_EXPIRACAO_CREDENCIAL")
	private Date dataExpiracaoCredencial;
	
	@XmlElementWrapper(name="perfis")
	@XmlElement(name="perfil")
	@ManyToMany
	@JoinTable(
		name="TBUSUARIO_PERFIL",
		joinColumns={
			@JoinColumn(name="SQ_USUARIO",referencedColumnName="SQ_USUARIO")
		},
		inverseJoinColumns={
			@JoinColumn(name="SQ_PERFIL",referencedColumnName="SQ_PERFIL")
	})
	private List<Perfil> perfis;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Date getDataBloqueioConta() {
		return dataBloqueioConta;
	}

	public void setDataBloqueioConta(Date dataBloqueioConta) {
		this.dataBloqueioConta = dataBloqueioConta;
	}

	public Date getDataExpiracaoConta() {
		return dataExpiracaoConta;
	}

	public void setDataExpiracaoConta(Date dataExpiracaoConta) {
		this.dataExpiracaoConta = dataExpiracaoConta;
	}

	public Date getDataExpiracaoCredencial() {
		return dataExpiracaoCredencial;
	}

	public void setDataExpiracaoCredencial(Date dataExpiracaoCredencial) {
		this.dataExpiracaoCredencial = dataExpiracaoCredencial;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
		
}
