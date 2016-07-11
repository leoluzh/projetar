package br.gov.mg.fazenda.projetar.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.function.Predicate;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.collections4.comparators.NullComparator;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Range;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import br.gov.mg.fazenda.projetar.entity.security.Usuario;
import br.gov.mg.fazenda.projetar.xml.adapter.DateAdapter;

/**
 * 
 * @author leonardo luz fernandes
 * @version 0.1
 * @since 03/04/2016
 * @param <K>
 */

@XmlRootElement(name="AcompanhamentoAtividade",namespace="br.gov.mg.fazenda.projetar.entity")
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)

@SuppressWarnings("serial")
@Entity
@Table(name="TBACOMPANHAMENTO_ATIVIDADE",schema="PROJETAR")
@SequenceGenerator(
		name="SQACOMPANHAMENTO_ATIVIDADE",
		sequenceName="SQACOMPANHAMENTO_ATIVIDADE",
		schema="PROJETAR",
		allocationSize=1,initialValue=1)
@Audited
@AuditTable(value="TBACOMPANHAMENTO_ATIVIDADE_AUDIT",schema="PROJETAR")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class AcompanhamentoAtividade 
extends KeyableAuditableEntity<AcompanhamentoAtividade,Long> 
implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQACOMPANHAMENTO_ATIVIDADE")
	@Column(name="SQ_ACOMPAMENTO_ATIVIDADE")
	private Long id;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@NotNull(message="Uma data deve ser obrigatoriamente informada para acompanhamento da atividade.")
	@Temporal(TemporalType.DATE)
	@Column(name="DT_ACOMPANHAMENTO")
	private Date data;
	
	@NotNull(message="Uma situação deve ser obrigatoriamente informada para acompanhamento da atividade.")
	@Enumerated(EnumType.ORDINAL)
	@Column(name="TP_STATUS")
	private StatusAcompanhamentoAtividade status;
	
	@NotNull(message="Um percentual de execução deve ser obrigatoriamente informada.")
	@Range(min=0,max=100)
	@Column(name="NR_PERCENTUAL_EXECUCAO")
	private Integer percentualExecucao;
	
	@Column(name="DS_STATUS_QUALITATIVO")
	private String descricaoStatusQualitativo;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name="DT_TENDENCIA_TERMINO")
	private Date dataTendenciaTermino;
	
	@Column(name="DS_JUSTIFICATIVA_REPROGRAMACAO")
	private String justificativaReprogramacao;
	
	@XmlJavaTypeAdapter(value=DateAdapter.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DT_CANCELAMENTO")
	private Date dataCancelamento;

	@NotNull(message="Uma atividade deve ser obrigatoriamente informada para um acompanhamento de atividade.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_ATIVIDADE",referencedColumnName="SQ_ATIVIDADE")
	private Atividade atividade;
	
	@NotNull(message="Um usuário deve ser obrigatoriamente informado para um acompanhamento de atividade.")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SQ_USUARIO",referencedColumnName="SQ_USUARIO")
	private Usuario usuario;

		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusAcompanhamentoAtividade getStatus() {
		return status;
	}

	public void setStatus(StatusAcompanhamentoAtividade status) {
		this.status = status;
	}
	
	public AcompanhamentoAtividade withStatus( StatusAcompanhamentoAtividade status ){
		setStatus(status);
		return this;
	}

	public Integer getPercentualExecucao() {
		return percentualExecucao;
	}

	public void setPercentualExecucao(Integer percentualExecucao) {
		this.percentualExecucao = percentualExecucao;
	}

	public String getDescricaoStatusQualitativo() {
		return descricaoStatusQualitativo;
	}

	public void setDescricaoStatusQualitativo(String descricaoStatusQualitativo) {
		this.descricaoStatusQualitativo = descricaoStatusQualitativo;
	}

	public Date getDataTendenciaTermino() {
		return dataTendenciaTermino;
	}

	public void setDataTendenciaTermino(Date dataTendenciaTermino) {
		this.dataTendenciaTermino = dataTendenciaTermino;
	}

	public String getJustificativaReprogramacao() {
		return justificativaReprogramacao;
	}

	public void setJustificativaReprogramacao(String justificativaReprogramacao) {
		this.justificativaReprogramacao = justificativaReprogramacao;
	}

	public Date getDataCancelamento() {
		return dataCancelamento;
	}

	public void setDataCancelamento(Date dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}

	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public AcompanhamentoAtividade withAtividade( Atividade atividade ){
		setAtividade(atividade);
		return this;
	}
	
	public static Predicate<AcompanhamentoAtividade> filterByDataIsNull(){
		Predicate<AcompanhamentoAtividade> filter = new Predicate<AcompanhamentoAtividade>() {
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return t.getData() == null ;
			}
		};
		return filter;
	}

	public static Predicate<AcompanhamentoAtividade> filterByDataIsNotNull(){
		Predicate<AcompanhamentoAtividade> filter = new Predicate<AcompanhamentoAtividade>() {
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return t.getData() != null ;
			}
		};
		return filter;
	}

	public static Predicate<AcompanhamentoAtividade> filterByDataEquals( Date data ){
		Predicate<AcompanhamentoAtividade> filter = new Predicate<AcompanhamentoAtividade>() {
			private final LocalDate _data = new LocalDate( data ) ;
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return new LocalDate( t.getData() ).isEqual( _data ) ;
			}
		};
		return filterByDataIsNotNull().and( filter );
	}


	public static Predicate<AcompanhamentoAtividade> filterByBeforeData( Date data ){
		Predicate<AcompanhamentoAtividade> filter = new Predicate<AcompanhamentoAtividade>() {
			private final LocalDate _data = new LocalDate( data );
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return new LocalDate( t.getData() ).isBefore( _data )  ;
			}
		};
		return filterByDataIsNotNull().and( filter );
	}
	
	public static Predicate<AcompanhamentoAtividade> filterByBeforeOrEqualsData( Date data ){
		return filterByDataEquals(data).or( filterByBeforeData(data) );
	}
	
	public static Predicate<AcompanhamentoAtividade> filterByAfterData( Date data ){
		Predicate<AcompanhamentoAtividade> filter = new Predicate<AcompanhamentoAtividade>() {
			private final LocalDate _data = new LocalDate( data ) ;
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return new LocalDate( t.getData() ).isAfter( _data ) ;
			}
		};
		return filterByDataIsNotNull().and( filter );
	}

	public static Predicate<AcompanhamentoAtividade> filterByAfterOrEqualsData( Date data ){
		return filterByDataEquals(data).or( filterByAfterData(data) );
	}
	
	public static Predicate<AcompanhamentoAtividade> filterByStatus( final StatusAcompanhamentoAtividade status ){
		Predicate<AcompanhamentoAtividade> predicate = new Predicate<AcompanhamentoAtividade>() {
			private final StatusAcompanhamentoAtividade _status = status;
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return _status == t.getStatus() ;
			}
		};
		return predicate;
	}
	
	public static Predicate<AcompanhamentoAtividade> filterByAno( final int ano ){
		Predicate<AcompanhamentoAtividade> predicate = new Predicate<AcompanhamentoAtividade>() {
			private final int _ano = ano ;
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return _ano == t.getAno() ;
			}
		};
		return predicate;
	}
	
	public static Predicate<AcompanhamentoAtividade> filterByMesAno( final int mes , final int ano ){
		Predicate<AcompanhamentoAtividade> predicate = new Predicate<AcompanhamentoAtividade>() {
			private final int _mes = mes ;
			private final int _ano = ano ;
			@Override
			public boolean test(AcompanhamentoAtividade t) {
				return _mes == t.getMes() && _ano == t.getAno() ;
			}
		};
		return predicate;
	}
	
	
	public static Comparator<AcompanhamentoAtividade> orderByDataAsc(){
		return new Comparator<AcompanhamentoAtividade>() {
			private final NullComparator<Date> delegator = new NullComparator<Date>(true);
			@Override
			public int compare(AcompanhamentoAtividade o1, AcompanhamentoAtividade o2) {
				return delegator.compare(o1.getData(),o2.getData());
			}
		};
	}
	
	public static Comparator<AcompanhamentoAtividade> orderByDataDesc(){
		return new Comparator<AcompanhamentoAtividade>() {
			private final NullComparator<Date> delegator = new NullComparator<Date>(false);			
			@Override
			public int compare(AcompanhamentoAtividade o1, AcompanhamentoAtividade o2) {
				return delegator.compare(o2.getData(),o1.getData());
			}
		};
	}
	
	@Transient
	public Integer getDia(){
		if( this.data != null ){
			return new LocalDate( this.data ).getDayOfMonth();
		}
		return null;
	}
	
	@Transient
	public AcompanhamentoAtividade setDia( Integer dia ){
		if( this.data != null ){
			LocalDate dt = new LocalDate( this.data );
			this.data = dt.withDayOfMonth(dia).toDate();			
		}else{
			LocalDate dt = new LocalDate();
			this.data = dt.withDayOfMonth(dia).toDate();			
		}
		return this;		
	}
	
	public AcompanhamentoAtividade withDia( int dia ){
		setDia(dia);
		return this;
	}
	
	@Transient
	public Integer getMes() {
		if( this.data != null ){
			return new LocalDate( this.data ).getMonthOfYear();
		}
		return null;
	}

	@Transient
	public void setMes( int mes ){
		if( this.data != null ){
			LocalDate dt = new LocalDate( this.data );
			this.data = dt.withMonthOfYear( mes ).toDate();			
		}else{
			LocalDate dt = new LocalDate();
			this.data = dt.withMonthOfYear( mes ).toDate();
		}
	}
	
	public AcompanhamentoAtividade withMes( int mes ){
		setMes(mes);
		return this;
	}

	@Transient
	public Integer getAno(){
		if( this.data != null ){
			return new LocalDate( this.data ).getYear();
		}
		return null;
	}
	
	@Transient
	public void setAno( int ano ){
		if( this.data != null ){
			LocalDate dt = new LocalDate( this.data );
			this.data = dt.withYear( ano ).toDate();
		}else{
			LocalDate dt = new LocalDate();
			this.data = dt.withYear( ano ).toDate() ;
		}
	}
	
	public AcompanhamentoAtividade withAno( int ano ){
		setAno( ano );
		return this;
	}

	@Transient
	public String getDataFormatada( String formato ){
		if( this.data != null ){
			return new LocalDate(this.data).toString( formato );
		}
		return "";
	}
	
	@Transient
	public String getDataCancelamentoFormatada( String formato ){
		if( this.dataCancelamento != null ){
			return new LocalDateTime( this.dataCancelamento ).toString( formato );
		}
		return "";
	}

	@Transient
	public String getDataTendenciaTerminoFormatada( String formato ){
		if( this.dataTendenciaTermino != null ){
			return new LocalDateTime( this.dataTendenciaTermino ).toString( formato );
		}
		return "";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public AcompanhamentoAtividade withUsuario( Usuario usuario ){
		setUsuario(usuario);
		return this;
	}
	
}
