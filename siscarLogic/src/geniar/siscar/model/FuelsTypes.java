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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * FuelsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FUELS_TYPES", schema = "", uniqueConstraints = {})
public class FuelsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long futCodigo;
	private String futNombre;
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);
	private Set<FuelTanks> fuelTankses = new HashSet<FuelTanks>(0);
	private Set<ServiceRegistry> serviceRegistries = new HashSet<ServiceRegistry>(
			0);
	private Set<FuelPerformance> fuelPerformances = new HashSet<FuelPerformance>(
			0);

	// Constructors

	/** default constructor */
	public FuelsTypes() {
	}

	/** minimal constructor */
	public FuelsTypes(Long futCodigo, String futNombre) {
		this.futCodigo = futCodigo;
		this.futNombre = futNombre;
	}

	/** full constructor */
	public FuelsTypes(Long futCodigo, String futNombre, Set<Tariffs> tariffses,
			Set<Vehicles> vehicleses, Set<FuelTanks> fuelTankses,
			Set<ServiceRegistry> serviceRegistries,
			Set<FuelPerformance> fuelPerformances) {
		this.futCodigo = futCodigo;
		this.futNombre = futNombre;
		this.tariffses = tariffses;
		this.vehicleses = vehicleses;
		this.fuelTankses = fuelTankses;
		this.serviceRegistries = serviceRegistries;
		this.fuelPerformances = fuelPerformances;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "FUELS_TYPES_GEN", sequenceName = "SQ_FUELS_TYPES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUELS_TYPES_GEN")
	@Column(name = "FUT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getFutCodigo() {
		return this.futCodigo;
	}

	public void setFutCodigo(Long futCodigo) {
		this.futCodigo = futCodigo;
	}

	@Column(name = "FUT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getFutNombre() {
		return this.futNombre;
	}

	public void setFutNombre(String futNombre) {
		this.futNombre = futNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelsTypes")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelsTypes")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelsTypes")
	public Set<FuelTanks> getFuelTankses() {
		return this.fuelTankses;
	}

	public void setFuelTankses(Set<FuelTanks> fuelTankses) {
		this.fuelTankses = fuelTankses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelsTypes")
	public Set<ServiceRegistry> getServiceRegistries() {
		return this.serviceRegistries;
	}

	public void setServiceRegistries(Set<ServiceRegistry> serviceRegistries) {
		this.serviceRegistries = serviceRegistries;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelsTypes")
	public Set<FuelPerformance> getFuelPerformances() {
		return this.fuelPerformances;
	}

	public void setFuelPerformances(Set<FuelPerformance> fuelPerformances) {
		this.fuelPerformances = fuelPerformances;
	}

}