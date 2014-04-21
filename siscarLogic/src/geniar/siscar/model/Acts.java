package geniar.siscar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Acts entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACTS", schema = "", uniqueConstraints = {})
public class Acts implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long actCodigo;
	private ActsTypes actsTypes;
	private Date actFechaReunion;
	private String actDescripcion;
	private String actObservaciones;
	private String actAprobacion;
	private Date actFechaAprobacion;
	private Set<Assistants> assistantses = new HashSet<Assistants>(0);
	private Set<ActsAccidents> actsAccidentses = new HashSet<ActsAccidents>(0);

	// Constructors

	/** default constructor */
	public Acts() {
	}

	/** minimal constructor */
	public Acts(Long actCodigo, ActsTypes actsTypes, Date actFechaReunion,
			String actDescripcion, String actAprobacion) {
		this.actCodigo = actCodigo;
		this.actsTypes = actsTypes;
		this.actFechaReunion = actFechaReunion;
		this.actDescripcion = actDescripcion;
		this.actAprobacion = actAprobacion;
	}

	/** full constructor */
	public Acts(Long actCodigo, ActsTypes actsTypes, Date actFechaReunion,
			String actDescripcion, String actObservaciones,
			String actAprobacion, Date actFechaAprobacion,
			Set<Assistants> assistantses) {
		this.actCodigo = actCodigo;
		this.actsTypes = actsTypes;
		this.actFechaReunion = actFechaReunion;
		this.actDescripcion = actDescripcion;
		this.actObservaciones = actObservaciones;
		this.actAprobacion = actAprobacion;
		this.actFechaAprobacion = actFechaAprobacion;
		this.assistantses = assistantses;
		

	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "ACTS_GEN", sequenceName = "SQ_ACTS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACTS_GEN")
	@Column(name = "ACT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getActCodigo() {
		return this.actCodigo;
	}

	public void setActCodigo(Long actCodigo) {
		this.actCodigo = actCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ATY_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public ActsTypes getActsTypes() {
		return this.actsTypes;
	}

	public void setActsTypes(ActsTypes actsTypes) {
		this.actsTypes = actsTypes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACT_FECHA_REUNION", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getActFechaReunion() {
		return this.actFechaReunion;
	}

	public void setActFechaReunion(Date actFechaReunion) {
		this.actFechaReunion = actFechaReunion;
	}

	@Column(name = "ACT_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 2000)
	public String getActDescripcion() {
		return this.actDescripcion;
	}

	public void setActDescripcion(String actDescripcion) {
		this.actDescripcion = actDescripcion;
	}

	@Column(name = "ACT_OBSERVACIONES", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public String getActObservaciones() {
		return this.actObservaciones;
	}

	public void setActObservaciones(String actObservaciones) {
		this.actObservaciones = actObservaciones;
	}

	@Column(name = "ACT_APROBACION", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getActAprobacion() {
		return this.actAprobacion;
	}

	public void setActAprobacion(String actAprobacion) {
		this.actAprobacion = actAprobacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACT_FECHA_APROBACION", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getActFechaAprobacion() {
		return this.actFechaAprobacion;
	}

	public void setActFechaAprobacion(Date actFechaAprobacion) {
		this.actFechaAprobacion = actFechaAprobacion;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "acts")
	public Set<Assistants> getAssistantses() {
		return this.assistantses;
	}

	public void setAssistantses(Set<Assistants> assistantses) {
		this.assistantses = assistantses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "acts")
	public Set<ActsAccidents> getActsAccidentses() {
		return this.actsAccidentses;
	}

	public void setActsAccidentses(Set<ActsAccidents> actsAccidentses) {
		this.actsAccidentses = actsAccidentses;
	}

}