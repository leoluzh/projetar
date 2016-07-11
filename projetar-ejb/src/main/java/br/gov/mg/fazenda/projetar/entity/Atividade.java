package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.joda.time.LocalDate;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.xml.adapter.DateAdapter;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@XmlRootElement(name="Atividade",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@Entity
@Table(name="TBATIVIDADE")
@SequenceGenerator(name="SQATIVIDADE",sequenceName="SQATIVIDADE",allocationSize=1)
@Audited
@AuditTable(value="TBATIVIDADE_AUDIT")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@SuppressWarnings("serial")
public class Atividade 
extends KeyableAuditableEntity<Atividade,Long> 
implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQATIVIDADE")
	@Column(name="SQ_ATIVIDADE")
	private Long id;
	
	@NotNull(message="Descrição da atividade não pode ser nula.")
	@NotEmpty(message="Descricão da atividade não pode ser vazia.")
	@NotBlank(message="Descrição da atividade não pode ser preenchida com espaços brancos.")
	@Column(name="DS_ATIVIDADE")
	private String descricaoAtividade;
	
	@NotNull(message="Uma etapa deve estar associada a uma atividade.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_ETAPA_MARCO",referencedColumnName="SQ_ETAPA_MARCO")
	private EtapaMarco etapa;
	
	@NotNull(message="Uma data de inicio de planejamento para atividade deve ser informada.")
	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_PLANEJADO")
	private Date dataInicioPlanejado;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@NotNull(message="Uma data de término de planejamento para atividade de ser informada.")
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TERMINO_PLANEJADO")
	private Date dataTerminoPlanejado;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_INICIO_REAL")
	private Date dataInicioReal;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TERMINO_REAL")
	private Date dataTerminoReal;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TENDENCIA_TERMINO")
	private Date dataTendenciaTermino;
	
	@Range(min=0,max=100)
	@Column(name="NR_PERCENTUAL_EXECUTADO")
	private Integer percentualExecutado;
	
	@NotNull(message="Uma situação deve ser informada para atividade.")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="TP_STATUS")
	private Status status;
	
	@NotNull(message="Um usuário responsável deve ser informado para atividade.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO_RESPONSAVEL",referencedColumnName="SQ_USUARIO")
	private Usuario responsavel;
	
	@XmlElementWrapper(name="acompanhamentos")
	@XmlElement(name="AcompanhamentAtividade")
	@Cascade(value=CascadeType.ALL)
	@OrderBy(value="data ASC")
	@OneToMany(mappedBy="atividade")
	private List<AcompanhamentoAtividade> acompanhamentos;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(String descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public EtapaMarco getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaMarco etapa) {
		this.etapa = etapa;
	}

	public Date getDataInicioPlanejado() {
		return dataInicioPlanejado;
	}

	public void setDataInicioPlanejado(Date dataInicioPlanejado) {
		this.dataInicioPlanejado = dataInicioPlanejado;
	}

	public Date getDataTerminoPlanejado() {
		return dataTerminoPlanejado;
	}

	public void setDataTerminoPlanejado(Date dataTerminoPlanejado) {
		this.dataTerminoPlanejado = dataTerminoPlanejado;
	}

	public Date getDataInicioReal() {
		return dataInicioReal;
	}

	public void setDataInicioReal(Date dataInicioReal) {
		this.dataInicioReal = dataInicioReal;
	}

	public Date getDataTerminoReal() {
		return dataTerminoReal;
	}

	public void setDataTerminoReal(Date dataTerminoReal) {
		this.dataTerminoReal = dataTerminoReal;
	}

	public Date getDataTendenciaTermino() {
		return dataTendenciaTermino;
	}

	public void setDataTendenciaTermino(Date dataTendenciaTermino) {
		this.dataTendenciaTermino = dataTendenciaTermino;
	}

	public Integer getPercentualExecutado() {
		return percentualExecutado;
	}

	public void setPercentualExecutado(Integer percentualExecutado) {
		this.percentualExecutado = percentualExecutado;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Atividade withStatus( Status status ){
		setStatus(status);
		return this;
	}

	public Usuario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Usuario responsavel) {
		this.responsavel = responsavel;
	}

	public List<AcompanhamentoAtividade> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void setAcompanhamentos(List<AcompanhamentoAtividade> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	
	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesPorData( final Date data ){
		
		List<AcompanhamentoAtividade> periodos = getAcompanhamentos()
		.stream().filter( AcompanhamentoAtividade.filterByDataEquals( data ) )
		.collect( Collectors.toList() );
		
		return periodos;
	}
	
	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesByFilter( Predicate<AcompanhamentoAtividade> filter ){
		return getAcompanhamentos().stream().filter( filter ).collect( Collectors.toList() );
	}
	
	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesPorMesAno( int mes , int ano ){
		if( CollectionUtils.isNotEmpty( getAcompanhamentos() ) ){
			return getAcompanhamentos().stream().filter(
					AcompanhamentoAtividade.filterByMesAno( mes , ano ) ).collect( Collectors.toList() );
		}
		return Collections.emptyList();
	}
	

	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesPorAno( int ano ){
		List<AcompanhamentoAtividade> periodos = new ArrayList<AcompanhamentoAtividade>();
		for( AcompanhamentoAtividade corrente : getAcompanhamentos() ){
			if( corrente.getAno() == ano ){
				periodos.add( corrente );
			}
		}
		return periodos;
	}
	
	
	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesAnoCorrente(){
		LocalDate ldt = new LocalDate();
		return getAcompanhamentoAtividadesPorAno( ldt.getYear() );
	}
	
	@Transient
	public AcompanhamentoAtividade getAcompanhamentoAtividadesAnoCorrentePorMes( int mes ){
		
		LocalDate ldt = new LocalDate();		
		List<AcompanhamentoAtividade> lista = getAcompanhamentoAtividadesPorAno( ldt.getYear() );	
		
		for( AcompanhamentoAtividade corrente : lista ){
			if( corrente.getMes() == mes ){
				return corrente;
			}
		}
		
		AcompanhamentoAtividade p = 
				new AcompanhamentoAtividade()
			.withAno( ldt.getYear() )
			.withDia( 1 )
			.withMes( mes )
			.withStatus( StatusAcompanhamentoAtividade.NAO_AVALIDADO );
		
		return p;
		//return null;
	}
	
	@Transient
	public Integer getAnoInicioPlanejado(){
		if( this.dataInicioPlanejado != null ){
			return new LocalDate( this.dataInicioPlanejado ).getYear();
		}
		return null;
	}
	
	@Transient
	public Integer getAnoTerminoPeriodoPlanejado(){
		if( this.dataTerminoPlanejado != null ){
			return new LocalDate( this.dataTerminoPlanejado ).getYear();
		}
		return null;
	}
	
	@Transient
	public Integer getAnoInicioReal(){
		if( this.dataInicioReal != null ){
			return new LocalDate( this.dataInicioReal ).getYear();
		}
		return null;
	}
	
	@Transient
	public Integer getAnoTerminoReal(){
		if( this.dataTerminoReal != null ){
			return new LocalDate( this.dataTerminoReal ).getYear();
		}
		return null;
	}
	
	@Transient
	public Integer getAnoTendenciaTermino(){
		if( this.dataTendenciaTermino != null ){
			return new LocalDate( this.dataTendenciaTermino ).getYear();
		}
		return null;
	}
	
	//inicio: planejado ...
	//fim: real ...
	public static List<AcompanhamentoAtividade> fatory( Date inicio , Date fim , Atividade atividade , Usuario usuario ){
		
		List<AcompanhamentoAtividade> periodos = new ArrayList<AcompanhamentoAtividade>();
		LocalDate dtInicio = new LocalDate(inicio);
		LocalDate dtFim = new LocalDate(fim);
		
		while( dtInicio.isBefore( dtFim ) || dtInicio.isEqual( dtFim )  ){
			AcompanhamentoAtividade periodo = new AcompanhamentoAtividade()
			.withAtividade( atividade )
			.withStatus( StatusAcompanhamentoAtividade.NAO_AVALIDADO )
			.withDia( 1 )
			.withAno( dtInicio.getYear() )
			.withMes( dtInicio.getMonthOfYear() );
			periodos.add( periodo );
			dtInicio = dtInicio.plusMonths(1);
		}
		
		return periodos;
	}

	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesOrderDataAsc(){
		Collections.sort( this.acompanhamentos , AcompanhamentoAtividade.orderByDataAsc() );
		return this.acompanhamentos;
	}
	
	@Transient
	public List<AcompanhamentoAtividade> getAcompanhamentoAtividadesOrderDataDesc(){
		Collections.sort( this.acompanhamentos , AcompanhamentoAtividade.orderByDataDesc() );
		return this.acompanhamentos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	
}
