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
 * ActsAccidents entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACTS_ACCIDENTS", schema = "SISCAR")
public class ActsAccidents implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idActsAccidents;
	private Accidents accidents;
	private Acts acts;
	private Date fechaActaRegistro;

	// Constructors

	/** default constructor */
	public ActsAccidents() {
	}

	/** minimal constructor */
	public ActsAccidents(Long idActsAccidents, Accidents accidents, Acts acts) {
		this.idActsAccidents = idActsAccidents;
		this.accidents = accidents;
		this.acts = acts;
	}

	/** full constructor */
	public ActsAccidents(Long idActsAccidents, Accidents accidents, Acts acts,
			Date fechaActaRegistro) {
		this.idActsAccidents = idActsAccidents;
		this.accidents = accidents;
		this.acts = acts;
		this.fechaActaRegistro = fechaActaRegistro;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "SQ_ACTS_ACCIDENTS_GEN", sequenceName = "SQ_ACTS_ACCIDENTS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACTS_ACCIDENTS_GEN")
	@Column(name = "ID_ACTS_ACCIDENTS", unique = true, nullable = false, precision = 38, scale = 0)
	public Long getIdActsAccidents() {
		return this.idActsAccidents;
	}

	public void setIdActsAccidents(Long idActsAccidents) {
		this.idActsAccidents = idActsAccidents;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_CODIGO", nullable = false)
	public Accidents getAccidents() {
		return this.accidents;
	}

	public void setAccidents(Accidents accidents) {
		this.accidents = accidents;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACT_CODIGO", nullable = false)
	public Acts getActs() {
		return this.acts;
	}

	public void setActs(Acts acts) {
		this.acts = acts;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHA_ACTA_REGISTRO", length = 7)
	public Date getFechaActaRegistro() {
		return this.fechaActaRegistro;
	}

	public void setFechaActaRegistro(Date fechaActaRegistro) {
		this.fechaActaRegistro = fechaActaRegistro;
	}

}