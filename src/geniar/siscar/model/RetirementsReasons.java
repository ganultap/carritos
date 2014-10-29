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
 * RetirementsReasons entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RETIREMENTS_REASONS", uniqueConstraints = {})
public class RetirementsReasons implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rerCodigo;
	private RetirementsTypes retirementsTypes;
	private Date rerFechaRetiro;
	private String rerLoginUsr;
	private String rerDescripcion;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

	// Constructors

	/** default constructor */
	public RetirementsReasons() {
	}

	/** minimal constructor */
	public RetirementsReasons(Long rerCodigo,
			RetirementsTypes retirementsTypes, String rerDescripcion) {
		this.rerCodigo = rerCodigo;
		this.retirementsTypes = retirementsTypes;
		this.rerDescripcion = rerDescripcion;
	}

	/** full constructor */
	public RetirementsReasons(Long rerCodigo,
			RetirementsTypes retirementsTypes, Date rerFechaRetiro,
			String rerLoginUsr, String rerDescripcion, Set<Vehicles> vehicleses) {
		this.rerCodigo = rerCodigo;
		this.retirementsTypes = retirementsTypes;
		this.rerFechaRetiro = rerFechaRetiro;
		this.rerLoginUsr = rerLoginUsr;
		this.rerDescripcion = rerDescripcion;
		this.vehicleses = vehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "RETIREMENTS_REASONS_GEN", sequenceName = "SQ_RETIREMENTS_REASONS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RETIREMENTS_REASONS_GEN")
	@Column(name = "RER_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRerCodigo() {
		return this.rerCodigo;
	}

	public void setRerCodigo(Long rerCodigo) {
		this.rerCodigo = rerCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RET_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public RetirementsTypes getRetirementsTypes() {
		return this.retirementsTypes;
	}

	public void setRetirementsTypes(RetirementsTypes retirementsTypes) {
		this.retirementsTypes = retirementsTypes;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RER_FECHA_RETIRO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getRerFechaRetiro() {
		return this.rerFechaRetiro;
	}

	public void setRerFechaRetiro(Date rerFechaRetiro) {
		this.rerFechaRetiro = rerFechaRetiro;
	}

	@Column(name = "RER_LOGIN_USR", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getRerLoginUsr() {
		return this.rerLoginUsr;
	}

	public void setRerLoginUsr(String rerLoginUsr) {
		this.rerLoginUsr = rerLoginUsr;
	}

	@Column(name = "RER_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 2000)
	public String getRerDescripcion() {
		return this.rerDescripcion;
	}

	public void setRerDescripcion(String rerDescripcion) {
		this.rerDescripcion = rerDescripcion;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "retirementsReasons")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

}