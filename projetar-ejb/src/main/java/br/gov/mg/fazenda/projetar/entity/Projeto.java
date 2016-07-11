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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
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

import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.xml.adapter.DateAdapter;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 08/04/2016
 * 
 */

@XmlRootElement(name="Projeto" , namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBPROJETO", schema="PROJETAR" ,
uniqueConstraints={
		@UniqueConstraint(columnNames={"NM_PROJETO"}) ,
})
@SequenceGenerator(
		name="SQPROJETO",
		sequenceName="SQPROJETO",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBPROJETO_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class Projeto 
	extends KeyableAuditableEntity<Projeto,Long> 
	implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQPROJETO")
	@Column(name="SQ_PROJETO")
	private Long id;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@NotNull(message="Data do projeto é obrigatória.")
	@Temporal(TemporalType.DATE)
	@Column(name="DT_ANO_PROJETO")
	private Date ano;
	
	@NotNull(message="Nome do projeto não pode ser nulo.")
	@NotBlank(message="Nome do projeto não pode ser branco.")
	@NotEmpty(message="Nome do projeto não pode ser vazio.")
	@Column(name="NM_PROJETO")
	private String nome;
	
	@NotNull(message="Um usuário de gerência deve ser informado obrigatóriamente.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_GERENTE",referencedColumnName="SQ_USUARIO")
	private Usuario	gerente;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_INTERLOCUTOR",referencedColumnName="SQ_USUARIO")
	private Usuario interlocutor;
	
	@NotNull(message="Fase do projeto é obrigatoria.")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="NR_FASE")
	private Fase fase; 
	
	@XmlElementWrapper(name="etapas")
	@XmlElement(name="etapa")
	@OneToMany(mappedBy="projeto")
	private List<EtapaMarco> etapas = new ArrayList<EtapaMarco>();
	
	@XmlElementWrapper(name="etapasReferenciadas")
	@XmlElement(name="etapaMarco")
	@ManyToMany(mappedBy="projetosReferenciados")
	private List<EtapaMarco> etapasReferenciadas = new ArrayList<EtapaMarco>();
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Column(name="DT_INICIO_PLANEJADO")
	private Date inicioPlanejado;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Column(name="DT_TERMINO_PLANEJADO")
	private Date terminoPlanejado;
	
	@Column(name="DS_OBJETIVO")
	private String objetivo;
	
	@Column(name="DS_IMPACTOS_BENEFICIOS")
	private String impactosBeneficios;
	
	@Column(name="DS_PRODUTO")
	private String produto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_UNIDADE",referencedColumnName="SQ_UNIDADE")
	private Unidade unidade;
	
	@Column(name="DS_RISCOS")
	private String riscos;
	
	@NotNull(message="Tipo de projeto é obrigatório.")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="TP_PROJETO")
	private TipoProjeto tipoProjeto;
	
	@NotNull(message="Tipo de categoria de projeto é obrigatório.")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="TP_CATEGORIA")
	private TipoCategoriaProjeto tipoCategoria;
	
	@DecimalMin(value="0.00",message="O valor de um projeto deve ser maior ou igual zero.")
	@Column(name="VRL_PROJETO")
	private BigDecimal valor;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getGerente() {
		return gerente;
	}

	public void setGerente(Usuario gerente) {
		this.gerente = gerente;
	}

	public Usuario getInterlocutor() {
		return interlocutor;
	}

	public void setInterlocutor(Usuario interlocutor) {
		this.interlocutor = interlocutor;
	}

	public Fase getFase() {
		return fase;
	}

	public void setFase(Fase fase) {
		this.fase = fase;
	}

	public List<EtapaMarco> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<EtapaMarco> etapas) {
		this.etapas = etapas;
	}

	public List<EtapaMarco> getEtapasReferenciadas() {
		return etapasReferenciadas;
	}

	public void setEtapasReferenciadas(List<EtapaMarco> etapasReferenciadas) {
		this.etapasReferenciadas = etapasReferenciadas;
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

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getImpactosBeneficios() {
		return impactosBeneficios;
	}

	public void setImpactosBeneficios(String impactosBeneficios) {
		this.impactosBeneficios = impactosBeneficios;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public String getRiscos() {
		return riscos;
	}

	public void setRiscos(String riscos) {
		this.riscos = riscos;
	}

	public TipoProjeto getTipoProjeto() {
		return tipoProjeto;
	}

	public void setTipoProjeto(TipoProjeto tipoProjeto) {
		this.tipoProjeto = tipoProjeto;
	}
	
	public TipoCategoriaProjeto getTipoCategoria(){
		return this.tipoCategoria;
	}
	
	public void setTipoCategoria( TipoCategoriaProjeto tipoCategoria ){
		this.tipoCategoria = tipoCategoria ;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
			
}
