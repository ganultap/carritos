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
 * VehiclesTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VEHICLES_TYPES", uniqueConstraints = {})
public class VehiclesTypes implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long vhtCodigo;
	private String vhtNombre;
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);
	private Set<Requests> requestses = new HashSet<Requests>(0);

	// Constructors

	/** default constructor */
	public VehiclesTypes() {
	}

	/** minimal constructor */
	public VehiclesTypes(Long vhtCodigo, String vhtNombre) {
		this.vhtCodigo = vhtCodigo;
		this.vhtNombre = vhtNombre;
	}

	/** full constructor */
	public VehiclesTypes(Long vhtCodigo, String vhtNombre, Set<Tariffs> tariffses, Set<Vehicles> vehicleses,
			Set<Requests> requestses) {
		this.vhtCodigo = vhtCodigo;
		this.vhtNombre = vhtNombre;
		this.tariffses = tariffses;
		this.vehicleses = vehicleses;
		this.requestses = requestses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="VEHICLES_TYPES_GEN", sequenceName="SQ_VEHICLES_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VEHICLES_TYPES_GEN")
	@Column(name = "VHT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhtCodigo() {
		return this.vhtCodigo;
	}

	public void setVhtCodigo(Long vhtCodigo) {
		this.vhtCodigo = vhtCodigo;
	}

	@Column(name = "VHT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getVhtNombre() {
		return this.vhtNombre;
	}

	public void setVhtNombre(String vhtNombre) {
		this.vhtNombre = vhtNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesTypes")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesTypes")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesTypes")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

}