package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@XmlRootElement(name="Unidade",namespace="br.gov.mg.fazenda.projetar.entity.")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBUNIDADE", schema="PROJETAR" ,
uniqueConstraints={
		@UniqueConstraint(columnNames={"NM_UNIDADE"})
})
@SequenceGenerator(
		name="SQUNIDADE",
		sequenceName="SQUNIDADE",
		schema="PROJETAR",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBUNIDADE_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class Unidade 
	extends KeyableAuditableEntity<Unidade,Long> 
	implements Serializable {

	
	@PrePersist
	@PreUpdate
	public void doNormalizeBeforeSaveOrUpdate(){
		this.nome = StringUtils.trim( this.nome );
		this.nome = StringUtils.upperCase( this.nome );
	}
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQUNIDADE")
	@Column(name="SQ_UNIDADE")
	private Long id;
	
	@NotNull()
	@NotBlank
	@NotEmpty
	@Column(name="NM_UNIDADE")
	private String nome;
	
	@XmlElementWrapper(name="projetos")
	@XmlElement(name="projeto")
	@OneToMany(mappedBy="unidade")
	private List<Projeto> projetos = new ArrayList<Projeto>();
	
	@Cascade(value=CascadeType.ALL)
	@OneToMany(mappedBy="unidadePai")
	private List<Unidade> unidadeFilhas = new ArrayList<Unidade>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_UNIDADE_PAI",referencedColumnName="SQ_UNIDADE")
	private Unidade unidadePai;
	
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

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

	public List<Unidade> getUnidadeFilhas() {
		return unidadeFilhas;
	}

	public void setUnidadeFilhas(List<Unidade> unidadeFilhas) {
		this.unidadeFilhas = unidadeFilhas;
	}

	public Unidade getUnidadePai() {
		return unidadePai;
	}

	public void setUnidadePai(Unidade unidadePai) {
		this.unidadePai = unidadePai;
	}
	
}
