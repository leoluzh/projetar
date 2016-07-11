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
 * @since 08/04/2016
 *
 */

@XmlRootElement(name="Permissao",namespace="br.gov.mg.fazenda.projetar.security")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBPERMISSAO", schema="PROJETAR" ,
uniqueConstraints={
		@UniqueConstraint(columnNames={"NM_PERMISSAO"})
})
@SequenceGenerator(
		name="SQPERMISSAO",
		sequenceName="SQPERMISSAO",
		schema="PROJETAR",
		allocationSize=1,initialValue=1)
@AuditTable(value="TBPERMISSAO_AUDIT",schema="PROJETAR")
@Audited
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class Permissao 
	extends KeyableAuditableEntity<Permissao,Long> 
	implements Serializable {

	@PrePersist
	@PreUpdate
	public void doTrimBeforeSaveOrUpdate() {
		this.nome = StringUtils.trim( this.nome );
		this.nome = StringUtils.upperCase( this.nome );
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQPERMISSAO")
	@Column(name="SQ_PERMISSAO")
	private Long id;
	
	@NotNull(message="Nome da permissãoo não pode ser nulo.")
	@NotEmpty(message="Nome da permissão não pode ser vazio.")
	@NotBlank(message="Nome da permissão não pode ser espaços em branco.")
	@Column(name="NM_PERMISSAO")
	private String nome;
	
	@NotNull(message="Uma descrição para uma permissão deve ser obrigatoriamente informada.")
	@NotBlank(message="Uma descrição para uma permissão não pode ser espaços em branco.")
	@NotEmpty(message="Uma descrição para uma permissão não pode ser vazio.")	
	@Column(name="NM_DESCRICAO")
	private String descricao;

	@XmlElementWrapper(name="perfis")
	@XmlElement(name="perfil")
	@ManyToMany(mappedBy="permissoes")
	private List<Perfil> perfis;
	
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

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
			
}
