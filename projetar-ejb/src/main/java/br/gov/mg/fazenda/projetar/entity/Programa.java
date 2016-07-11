package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.gov.mg.fazenda.projetar.xml.adapter.DateAdapter;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@XmlRootElement(name="Programa",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@SuppressWarnings("serial")
@Entity
@Table(name ="TBPROGRAMA",schema="PROJETAR")
@SequenceGenerator(
		name="SQPROGRAMA",
		sequenceName="SQPROGRAMA",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBPROGRAMA_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Programa 
	extends KeyableAuditableEntity<Programa,Long> 
	implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQPROGRAMA")
	@Column(name="SQ_PROGRAMA")
	private Long id;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_ANO_PROGRAMA")
	private Date ano;
	
	@NotNull()
	@NotBlank()
	@NotEmpty()
	@Column(name="DS_PROGRAMA")
	private String descricao;
	
	@DecimalMin("0.00")
	private BigDecimal valorInicial;
	
	@DecimalMin("0.00")
	private BigDecimal valorAjustado;

	@DecimalMin("0.00")
	private BigDecimal valorAutorizado;

	@DecimalMin("0.00")
	private BigDecimal valorEmpenhado;
	
	@DecimalMin("0.00")
	private BigDecimal valorLiquidado;
	
	@XmlElementWrapper(name="acoes")
	@XmlElement(name="acao")
	@OneToMany(mappedBy="programa")
	private List<AcaoOrcamentaria> acoes = new ArrayList<AcaoOrcamentaria>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAno() {
		return ano;
	}

	public void setAno(Date ano) {
		this.ano = ano;
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

	public List<AcaoOrcamentaria> getAcoes() {
		return acoes;
	}

	public void setAcoes(List<AcaoOrcamentaria> acoes) {
		this.acoes = acoes;
	}
	
}
