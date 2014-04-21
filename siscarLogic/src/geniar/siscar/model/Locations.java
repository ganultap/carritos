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
 * Locations entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOCATIONS", uniqueConstraints = {})
public class Locations implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lcnCodigo;
	private Cities cities;
	private LocationsTypes locationsTypes;
	private String lcnDescripcion;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);

	// Constructors

	/** default constructor */
	public Locations() {
	}

	/** minimal constructor */
	public Locations(Long lcnCodigo, Cities cities, LocationsTypes locationsTypes, String lcnDescripcion) {
		this.lcnCodigo = lcnCodigo;
		this.cities = cities;
		this.locationsTypes = locationsTypes;
		this.lcnDescripcion = lcnDescripcion;
	}

	/** full constructor */
	public Locations(Long lcnCodigo, Cities cities, LocationsTypes locationsTypes, String lcnDescripcion,
			Set<Vehicles> vehicleses, Set<Tariffs> tariffses) {
		this.lcnCodigo = lcnCodigo;
		this.cities = cities;
		this.locationsTypes = locationsTypes;
		this.lcnDescripcion = lcnDescripcion;
		this.vehicleses = vehicleses;
		this.tariffses = tariffses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="LOCATIONS_GEN", sequenceName="SQ_LOCATIONS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_GEN")
	@Column(name = "LCN_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getLcnCodigo() {
		return this.lcnCodigo;
	}

	public void setLcnCodigo(Long lcnCodigo) {
		this.lcnCodigo = lcnCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTS_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Cities getCities() {
		return this.cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LCT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public LocationsTypes getLocationsTypes() {
		return this.locationsTypes;
	}

	public void setLocationsTypes(LocationsTypes locationsTypes) {
		this.locationsTypes = locationsTypes;
	}

	@Column(name = "LCN_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getLcnDescripcion() {
		return this.lcnDescripcion;
	}

	public void setLcnDescripcion(String lcnDescripcion) {
		this.lcnDescripcion = lcnDescripcion;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locations")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locations")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

}