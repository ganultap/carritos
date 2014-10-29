package geniar.siscar.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ParValoresparametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAR_VALORESPARAMETROS", uniqueConstraints = {})
public class ParValoresparametros implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idvalorparametro;
	private ParParametros parParametros;
	private Date fechainicio;
	private Date fechafin;
	private Float valorinicial;
	private Float valorfinal;

	// Constructors

	/** default constructor */
	public ParValoresparametros() {
	}

	/** minimal constructor */
	public ParValoresparametros(Long idvalorparametro, ParParametros parParametros) {
		this.idvalorparametro = idvalorparametro;
		this.parParametros = parParametros;
	}

	/** full constructor */
	public ParValoresparametros(Long idvalorparametro, ParParametros parParametros, Date fechainicio, Date fechafin,
			Float valorinicial, Float valorfinal) {
		this.idvalorparametro = idvalorparametro;
		this.parParametros = parParametros;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.valorinicial = valorinicial;
		this.valorfinal = valorfinal;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PAR_VALORESPARAMETROS_GEN", sequenceName = "SQ_VALOR_PARAMETROS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAR_VALORESPARAMETROS_GEN")
	@Column(name = "IDVALORPARAMETRO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getIdvalorparametro() {
		return this.idvalorparametro;
	}

	public void setIdvalorparametro(Long idvalorparametro) {
		this.idvalorparametro = idvalorparametro;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPARAMETRO", unique = false, nullable = false, insertable = true, updatable = true)
	public ParParametros getParParametros() {
		return this.parParametros;
	}

	public void setParParametros(ParParametros parParametros) {
		this.parParametros = parParametros;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHAINICIO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getFechainicio() {
		return this.fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHAFIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	@Column(name = "VALORINICIAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getValorinicial() {
		return this.valorinicial;
	}

	public void setValorinicial(Float valorinicial) {
		this.valorinicial = valorinicial;
	}

	@Column(name = "VALORFINAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getValorfinal() {
		return this.valorfinal;
	}

	public void setValorfinal(Float valorfinal) {
		this.valorfinal = valorfinal;
	}

}