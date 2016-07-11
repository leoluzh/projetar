package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 08/04/2016
 *
 */

@XmlRootElement(name="Modulo",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@SuppressWarnings("serial")
@Entity
@Table(name="TBMODULO",schema="PROJETAR",
uniqueConstraints={
		@UniqueConstraint(columnNames={"NM_MODULO"})
})
@SequenceGenerator(
			name="SQMODULO",
			sequenceName="SQMODULO",
			allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBMODULO_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Modulo 
extends KeyableAuditableEntity<Modulo,Long> 
implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQMODULO")
	@Column(name="SQ_MODULO")
	private Long id;
	
	@NotBlank(message="Nome do módulo não pode ser preenchido com espaços em branco.")
	@NotEmpty(message="Nome do módulo não pode ser vazio.")
	@NotNull(message="Nome do módulo não pode ser nulo.")
	@Column(name="NM_MODULO")
	private String nome;

	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy="modulo")
	private List<EtapaMarco> etapas	= new ArrayList<EtapaMarco>();

	
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

	public List<EtapaMarco> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<EtapaMarco> etapas) {
		this.etapas = etapas;
	}
		
}
