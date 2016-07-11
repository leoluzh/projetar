package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.xml.adapter.DateAdapter;
import br.gov.mg.fazenda.projetar.xml.adapter.DateTimeAdapter;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 08/04/2016
 *
 */
@XmlRootElement(name="EtapaMarco",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBETAPA_MARCO",schema="PROJETAR")
@SequenceGenerator(
		name="SQETAPA_MARCO",
		sequenceName="SQETAPA_MARCO",
		schema="PROJETAR",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBETAPA_MARCO_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class EtapaMarco 
	extends KeyableAuditableEntity<EtapaMarco,Long> 
	implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQETAPA_MARCO")
	@Column(name="SQ_ETAPA_MARCO")
	private Long id;
	
	@NotNull
	@NotBlank
	@NotEmpty
	@Column(name="DS_ETAPA")
	private String descricaoEtapa;
	
	@NotNull(message="Etapa marco deve estar associada a um projeto obrigatóriamente.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_PROJETO",referencedColumnName="SQ_PROJETO")
	private Projeto projeto;

	@XmlElementWrapper(name="projetosReferenciados")
	@XmlElement(name="projeto")
	@Cascade(value=CascadeType.ALL)
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="TBETAPAS_MARCO_PROJETOS_REFERENCIADOS",
		joinColumns={
			@JoinColumn(
				name="SQ_ETAPA_MARCO",
				referencedColumnName="SQ_ETAPA_MARCO",
				nullable=false,
				updatable=false),
		},
		inverseJoinColumns={
			@JoinColumn(
				name="SQ_PROJETO_REFERENCIADO",
				referencedColumnName="SQ_PROJETO",
				nullable=false,
				updatable=false)
		}
	)
	private List<Projeto> projetosReferenciados;

	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_PLANEJADO")
	private Date inicioPlanejado;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TERMINO_PLANEJADO")
	private Date terminoPlanejado;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_REAL")
	private Date inicioReal;

	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TERMINO_REAL")
	private Date terminoReal;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TENDENCIA_TERMINO")
	private Date tendenciaDeTermino;
	
	@XmlJavaTypeAdapter(value=DateTimeAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_REGISTRO")
	private Date dataDeRegistro;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="TP_STATUS")
	private Status status;
	
	@XmlElementWrapper(name="atividades")
	@XmlElement(name="atividade")
	@Cascade(value=org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy="etapa")
	private List<Atividade> atividades = new ArrayList<Atividade>();
	
	@XmlElementWrapper(name="orcamentos")
	@XmlElement(name="orcamento")
	@OneToMany(mappedBy="etapa")
	private List<Orcamento> orcamento= new ArrayList<Orcamento>();
	
	@NotNull(message="Etapa marco deve ter uma usuario responsável associado obrigatóriamente.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_RESPONSAVEL",referencedColumnName="SQ_USUARIO")
	private Usuario responsavel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_INTERLOCUTOR",referencedColumnName="SQ_USUARIO")
	private Usuario interlocutor;
	
	@Column(name="DS_STATUS_QUALITATIVO")
	private String statusQualitativo;
	
	@Column(name="DS_JUSTIFICATIVA_REPROGRAMACAO")
	private String justificativaDaReprogramacao;
	
	@Column(name="VRL_PLANEJADO")
	private BigDecimal valorPlanejado;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="SQ_PORTFOLIO")
	private Portfolio portfolio;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="TP_DEMANDA_SISTEMA")
	private TipoDemandaSistema tipoDemandaSistema;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_MODULO",referencedColumnName="SQ_MODULO")
	private Modulo modulo;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoEtapa() {
		return descricaoEtapa;
	}

	public void setDescricaoEtapa(String descricaoEtapa) {
		this.descricaoEtapa = descricaoEtapa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Date getInicioPlanejado() {
		return inicioPlanejado;
	}

	public void setInicioPlanejado(Date inicioPlanejado) {
		this.inicioPlanejado = inicioPlanejado;
	}

	public Date getTerminoPlanejado() {
		return terminoPlanejado;
	}

	public void setTerminoPlanejado(Date terminoPlanejado) {
		this.terminoPlanejado = terminoPlanejado;
	}

	public Date getInicioReal() {
		return inicioReal;
	}

	public void setInicioReal(Date inicioReal) {
		this.inicioReal = inicioReal;
	}

	public Date getTerminoReal() {
		return terminoReal;
	}

	public void setTerminoReal(Date terminoReal) {
		this.terminoReal = terminoReal;
	}

	public Date getTendenciaDeTermino() {
		return tendenciaDeTermino;
	}

	public void setTendenciaDeTermino(Date tendenciaDeTermino) {
		this.tendenciaDeTermino = tendenciaDeTermino;
	}

	public Date getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(Date dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Orcamento> getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(List<Orcamento> orcamento) {
		this.orcamento = orcamento;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public Usuario getInterlocutor() {
		return interlocutor;
	}

	public void setInterlocutor(Usuario interlocutor) {
		this.interlocutor = interlocutor;
	}

	public String getStatusQualitativo() {
		return statusQualitativo;
	}

	public void setStatusQualitativo(String statusQualitativo) {
		this.statusQualitativo = statusQualitativo;
	}

	public String getJustificativaDaReprogramacao() {
		return justificativaDaReprogramacao;
	}

	public void setJustificativaDaReprogramacao(String justificativaDaReprogramacao) {
		this.justificativaDaReprogramacao = justificativaDaReprogramacao;
	}

	public BigDecimal getValorPlanejado() {
		return valorPlanejado;
	}

	public void setValorPlanejado(BigDecimal valorPlanejado) {
		this.valorPlanejado = valorPlanejado;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public TipoDemandaSistema getTipoDemandaSistema() {
		return tipoDemandaSistema;
	}

	public void setTipoDemandaSistema(TipoDemandaSistema tipoDemandaSistema) {
		this.tipoDemandaSistema = tipoDemandaSistema;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}

	public List<Projeto> getProjetosReferenciados() {
		return projetosReferenciados;
	}

	public void setProjetosReferenciados( List<Projeto> projetosReferenciados) {
		this.projetosReferenciados = projetosReferenciados;
	}
			
}
