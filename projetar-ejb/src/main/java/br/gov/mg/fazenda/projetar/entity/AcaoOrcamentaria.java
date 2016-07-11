package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@XmlRootElement(name="AcaoOrcamentaria",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@SuppressWarnings("serial")
@Entity
@Table(name="TBACAO_ORCAMENTARIA",schema="PROJETAR")
@SequenceGenerator(
		name="SQACAO_ORCAMENTARIA",
		schema="PROJETAR",
		sequenceName="SQACAO_ORCAMENTARIA",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBACAO_ORCAMENTARIA_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class AcaoOrcamentaria 
extends KeyableAuditableEntity<AcaoOrcamentaria,Long> 
implements Serializable {
		
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQACAO_ORCAMENTARIA")
	@Column(name="SQ_ACAO_ORCAMENTARIA")
	private Long id;
	
	@NotNull(message="Uma descrição da ação orçamentária deve ser obrigatoriamente preenchida.")
	@Column(name="DS_ACAO_ORCAMENTARIA")
	private String descricao;
	
	@DecimalMin(value="0.00",message="Um valor inicial para uma ação orçamentária dever ser maior ou igual a zero.")
	@NotNull(message="Um valor inicial para uma ação orçamentária deve ser obrigatoriamente preenchida")
	@Column(name="VLR_INICIAL")
	private BigDecimal valorInicial;

	@Column(name="VRL_AJUSTADO")
	private BigDecimal valorAjustado;
	
	@Column(name="VLR_AUTORIZADO")
	private BigDecimal valorAutorizado;
	
	@Column(name="VLR_EMPENHANDO")
	private BigDecimal valorEmpenhado;
	
	@Column(name="VLR_LIQUIDADO")
	private BigDecimal valorLiquidado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_PROGRAMA",referencedColumnName="SQ_PROGRAMA")
	private Programa programa;
	
	@XmlElementWrapper(name="orcamentos")
	@XmlElement(name="orcamento")
	@Cascade(value=CascadeType.ALL)
	@OneToMany(mappedBy ="acao")	
	private List<Orcamento> orcamentos = new ArrayList<Orcamento>();

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorAjustado() {
		return valorAjustado;
	}

	public void setValorAjustado(BigDecimal valorAjustado) {
		this.valorAjustado = valorAjustado;
	}

	public BigDecimal getValorAutorizado() {
		return valorAutorizado;
	}

	public void setValorAutorizado(BigDecimal valorAutorizado) {
		this.valorAutorizado = valorAutorizado;
	}

	public BigDecimal getValorEmpenhado() {
		return valorEmpenhado;
	}

	public void setValorEmpenhado(BigDecimal valorEmpenhado) {
		this.valorEmpenhado = valorEmpenhado;
	}

	public BigDecimal getValorLiquidado() {
		return valorLiquidado;
	}

	public void setValorLiquidado(BigDecimal valorLiquidado) {
		this.valorLiquidado = valorLiquidado;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public List<Orcamento> getOrcamentos() {
		return orcamentos;
	}

	public void setOrcamentos(List<Orcamento> orcamentos) {
		this.orcamentos = orcamentos;
	}	
	
}
