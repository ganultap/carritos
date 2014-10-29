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
 * FuelTanks entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FUEL_TANKS", schema = "", uniqueConstraints = {})
public class FuelTanks implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ftaCodigo;
	private FuelsTypes fuelsTypes;
	private String ftaNombre;
	private Float ftaCapacidadMaxima;
	private Float ftaGalonesActuales;
	private Set<DailyMovementTank> dailyMovementTanks = new HashSet<DailyMovementTank>(
			0);
	private Set<Pumps> pumpses = new HashSet<Pumps>(0);
	private Set<ControlsTanks> controlsTankses = new HashSet<ControlsTanks>(0);

	// Constructors

	/** default constructor */
	public FuelTanks() {
	}

	/** minimal constructor */
	public FuelTanks(Long ftaCodigo, FuelsTypes fuelsTypes, String ftaNombre,
			Float ftaCapacidadMaxima) {
		this.ftaCodigo = ftaCodigo;
		this.fuelsTypes = fuelsTypes;
		this.ftaNombre = ftaNombre;
		this.ftaCapacidadMaxima = ftaCapacidadMaxima;
	}

	/** full constructor */
	public FuelTanks(Long ftaCodigo, FuelsTypes fuelsTypes, String ftaNombre,
			Float ftaCapacidadMaxima, Float ftaGalonesActuales,
			Set<DailyMovementTank> dailyMovementTanks, Set<Pumps> pumpses,
			Set<ControlsTanks> controlsTankses) {
		this.ftaCodigo = ftaCodigo;
		this.fuelsTypes = fuelsTypes;
		this.ftaNombre = ftaNombre;
		this.ftaCapacidadMaxima = ftaCapacidadMaxima;
		this.ftaGalonesActuales = ftaGalonesActuales;
		this.dailyMovementTanks = dailyMovementTanks;
		this.pumpses = pumpses;
		this.controlsTankses = controlsTankses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "TANKS_GEN", sequenceName = "SQ_TANKS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TANKS_GEN")
	@Column(name = "FTA_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getFtaCodigo() {
		return this.ftaCodigo;
	}

	public void setFtaCodigo(Long ftaCodigo) {
		this.ftaCodigo = ftaCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FUT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public FuelsTypes getFuelsTypes() {
		return this.fuelsTypes;
	}

	public void setFuelsTypes(FuelsTypes fuelsTypes) {
		this.fuelsTypes = fuelsTypes;
	}

	@Column(name = "FTA_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getFtaNombre() {
		return this.ftaNombre;
	}

	public void setFtaNombre(String ftaNombre) {
		this.ftaNombre = ftaNombre;
	}

	@Column(name = "FTA_CAPACIDAD_MAXIMA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getFtaCapacidadMaxima() {
		return this.ftaCapacidadMaxima;
	}

	public void setFtaCapacidadMaxima(Float ftaCapacidadMaxima) {
		this.ftaCapacidadMaxima = ftaCapacidadMaxima;
	}

	@Column(name = "FTA_GALONES_ACTUALES", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getFtaGalonesActuales() {
		return this.ftaGalonesActuales;
	}

	public void setFtaGalonesActuales(Float ftaGalonesActuales) {
		this.ftaGalonesActuales = ftaGalonesActuales;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelTanks")
	public Set<DailyMovementTank> getDailyMovementTanks() {
		return this.dailyMovementTanks;
	}

	public void setDailyMovementTanks(Set<DailyMovementTank> dailyMovementTanks) {
		this.dailyMovementTanks = dailyMovementTanks;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelTanks")
	public Set<Pumps> getPumpses() {
		return this.pumpses;
	}

	public void setPumpses(Set<Pumps> pumpses) {
		this.pumpses = pumpses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelTanks")
	public Set<ControlsTanks> getControlsTankses() {
		return this.controlsTankses;
	}

	public void setControlsTankses(Set<ControlsTanks> controlsTankses) {
		this.controlsTankses = controlsTankses;
	}

}