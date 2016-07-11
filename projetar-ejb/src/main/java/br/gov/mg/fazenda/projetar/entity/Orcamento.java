package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 08/04/2016
 * 
 */
@XmlRootElement(name="Orcamento",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@SuppressWarnings("serial")
@Entity
@Table(name="TBORCAMENTO",schema="PROJETAR")
@SequenceGenerator(
		name="SQORCAMENTO",
		sequenceName="SQORCAMENTO",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBORCAMENTO_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Orcamento 
	extends KeyableAuditableEntity<Orcamento,Long> 
	implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQORCAMENTO")
	@Column(name="SQ_ORCAMENTO")
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_ETAPA_MARCO",referencedColumnName="SQ_ETAPA_MARCO")
	private EtapaMarco etapa;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_ACAO_ORCAMENTARIA",referencedColumnName="SQ_ACAO_ORCAMENTARIA")
	private AcaoOrcamentaria acao;
	
	@DecimalMin("0.00")
	@Column(name="VLR_INICIAL")
	private BigDecimal valorInicial;

	@DecimalMin("0.00")
	@Column(name="VLR_AJUSTADO")
	private BigDecimal valorAjustado;

	@DecimalMin("0.00")
	@Column(name="VLR_AUTORIZADO")
	private BigDecimal valorAutorizado;

	@DecimalMin("0.00")	
	@Column(name="VLR_EMPENHADO")
	private BigDecimal valorEmpenhado;
	
	@DecimalMin("0.00")	
	@Column(name="VLR_LIQUIDADO")
	private BigDecimal valorLiquidado;

	/**
	@NotNull(message="Uma data de atualização deve ser obrigatoriamente preenchida para um orçamento.")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_ATUALIZACAO")
	private Date dataAtualizacao;
	
	@NotNull(message="Um usuário de atualização deve ser obrigatoriamente preenchido para um orçamento.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_ATUALIZACAO")
	private Usuario usuarioAtualizacao;
	**/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EtapaMarco getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaMarco etapa) {
		this.etapa = etapa;
	}

	public AcaoOrcamentaria getAcao() {
		return acao;
	}

	public void setAcao(AcaoOrcamentaria acao) {
		this.acao = acao;
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
	
}
