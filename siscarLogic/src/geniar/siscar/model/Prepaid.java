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
 * Prepaid entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PREPAID", schema = "", uniqueConstraints = {})
public class Prepaid implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long preCodigo;
	private HeaderProof headerProof;
	private String preAsignatario;
	private String prePlaca;
	private Date preFechaini;
	private Date preFechaFin;
	private Float preTotal;
	private CostCentersFuel costCentersFuel;
	private Set<ServiceRegistry> serviceRegistries = new HashSet<ServiceRegistry>(
			0);

	// Nueva columna - devolucion prepago combustible
	private HeaderProof headerProof_dev;
	private ChargeTo chargeTo;

	// Constructors
	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public ChargeTo getChargeTo() {
		return chargeTo;
	}

	public void setChargeTo(ChargeTo chargeTo) {
		this.chargeTo = chargeTo;
	}

	/** default constructor */
	public Prepaid() {

	}

	/** minimal constructor */
	public Prepaid(Long preCodigo, String preAsignatario,
			HeaderProof headerProof, Date preFechaini, Date preFechaFin,
			Float preTotal, String prePlaca, CostCentersFuel costCentersFuel) {
		this.preCodigo = preCodigo;
		this.preAsignatario = preAsignatario;
		this.preFechaini = preFechaini;
		this.preFechaFin = preFechaFin;
		this.preTotal = preTotal;
		this.prePlaca = prePlaca;
		this.headerProof = headerProof;
		this.costCentersFuel = costCentersFuel;
	}

	/** full constructor */
	public Prepaid(Long preCodigo, String preAsignatario, Date preFechaini,
			Date preFechaFin, Float preTotal, CostCentersFuel costCentersFuel,
			Set<ServiceRegistry> serviceRegistries,
			Set<CostCentersFuel> costCentersFuels, String prePlaca) {
		this.preCodigo = preCodigo;
		this.preAsignatario = preAsignatario;
		this.preFechaini = preFechaini;
		this.preFechaFin = preFechaFin;
		this.preTotal = preTotal;
		this.serviceRegistries = serviceRegistries;
		this.prePlaca = prePlaca;
		this.costCentersFuel = costCentersFuel;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PREPAID_GEN", sequenceName = "SQ_PREPAID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PREPAID_GEN")
	@Column(name = "PRE_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPreCodigo() {
		return this.preCodigo;
	}

	public void setPreCodigo(Long preCodigo) {
		this.preCodigo = preCodigo;
	}

	@Column(name = "PRE_ASIGNATARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPreAsignatario() {
		return this.preAsignatario;
	}

	public void setPreAsignatario(String preAsignatario) {
		this.preAsignatario = preAsignatario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRE_FECHAINI", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPreFechaini() {
		return this.preFechaini;
	}

	public void setPreFechaini(Date preFechaini) {
		this.preFechaini = preFechaini;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRE_FECHA_FIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPreFechaFin() {
		return this.preFechaFin;
	}

	public void setPreFechaFin(Date preFechaFin) {
		this.preFechaFin = preFechaFin;
	}

	@Column(name = "PRE_TOTAL", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 2)
	public Float getPreTotal() {
		return this.preTotal;
	}

	public void setPreTotal(Float preTotal) {
		this.preTotal = preTotal;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "prepaid")
	public Set<ServiceRegistry> getServiceRegistries() {
		return this.serviceRegistries;
	}

	public void setServiceRegistries(Set<ServiceRegistry> serviceRegistries) {
		this.serviceRegistries = serviceRegistries;
	}

	@Column(name = "PRE_PLACA", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getPrePlaca() {
		return prePlaca;
	}

	public void setPrePlaca(String prePlaca) {
		this.prePlaca = prePlaca;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "HEP_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public HeaderProof getHeaderProof() {
		return this.headerProof;
	}

	public void setHeaderProof(HeaderProof headerProof) {
		this.headerProof = headerProof;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "HEP_DEV_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public HeaderProof getHeaderProof_dev() {
		return headerProof_dev;
	}

	public void setHeaderProof_dev(HeaderProof headerProof_dev) {
		this.headerProof_dev = headerProof_dev;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CCF_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public CostCentersFuel getCostCentersFuel() {
		return costCentersFuel;
	}

	public void setCostCentersFuel(CostCentersFuel costCentersFuel) {
		this.costCentersFuel = costCentersFuel;
	}

}