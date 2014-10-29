package geniar.siscar.model;

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

/**
 * Pumps entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUMPS", schema = "", uniqueConstraints = {})
public class Pumps implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pumCodigo;
	private FuelTanks fuelTanks;
	private String pumNombre;
	private Long pumVecesUtilizado;
	private Set<ServiceRegistry> serviceRegistries = new HashSet<ServiceRegistry>(
			0);
	private Set<DialyMovementPumps> dialyMovementPumpses = new HashSet<DialyMovementPumps>(
			0);

	// Constructors

	/** default constructor */
	public Pumps() {
	}

	/** minimal constructor */
	public Pumps(Long pumCodigo, String pumNombre) {
		this.pumCodigo = pumCodigo;
		this.pumNombre = pumNombre;
	}

	/** full constructor */
	public Pumps(Long pumCodigo, FuelTanks fuelTanks, String pumNombre,
			Long pumVecesUtilizado, Set<ServiceRegistry> serviceRegistries,
			Set<DialyMovementPumps> dialyMovementPumpses) {
		this.pumCodigo = pumCodigo;
		this.fuelTanks = fuelTanks;
		this.pumNombre = pumNombre;
		this.pumVecesUtilizado = pumVecesUtilizado;
		this.serviceRegistries = serviceRegistries;
		this.dialyMovementPumpses = dialyMovementPumpses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PUMP_GEN", sequenceName = "SQ_PUMP", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PUMP_GEN")
	@Column(name = "PUM_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getPumCodigo() {
		return this.pumCodigo;
	}

	public void setPumCodigo(Long pumCodigo) {
		this.pumCodigo = pumCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FTA_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public FuelTanks getFuelTanks() {
		return this.fuelTanks;
	}

	public void setFuelTanks(FuelTanks fuelTanks) {
		this.fuelTanks = fuelTanks;
	}

	@Column(name = "PUM_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPumNombre() {
		return this.pumNombre;
	}

	public void setPumNombre(String pumNombre) {
		this.pumNombre = pumNombre;
	}

	@Column(name = "PUM_VECES_UTILIZADO", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPumVecesUtilizado() {
		return this.pumVecesUtilizado;
	}

	public void setPumVecesUtilizado(Long pumVecesUtilizado) {
		this.pumVecesUtilizado = pumVecesUtilizado;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pumps")
	public Set<ServiceRegistry> getServiceRegistries() {
		return this.serviceRegistries;
	}

	public void setServiceRegistries(Set<ServiceRegistry> serviceRegistries) {
		this.serviceRegistries = serviceRegistries;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "pumps")
	public Set<DialyMovementPumps> getDialyMovementPumpses() {
		return this.dialyMovementPumpses;
	}

	public void setDialyMovementPumpses(
			Set<DialyMovementPumps> dialyMovementPumpses) {
		this.dialyMovementPumpses = dialyMovementPumpses;
	}

}