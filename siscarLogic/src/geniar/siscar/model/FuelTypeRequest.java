package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FuelTypeRequest entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FUEL_TYPE_REQUEST", schema = "", uniqueConstraints = {})
public class FuelTypeRequest implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ftrCodigo;
	private String ftrNombre;
	private Set<ServiceRegistry> serviceRegistries = new HashSet<ServiceRegistry>(0);

	// Constructors

	/** default constructor */
	public FuelTypeRequest() {
	}

	/** minimal constructor */
	public FuelTypeRequest(Long ftrCodigo, String ftrNombre) {
		this.ftrCodigo = ftrCodigo;
		this.ftrNombre = ftrNombre;
	}

	/** full constructor */
	public FuelTypeRequest(Long ftrCodigo, String ftrNombre, Set<ServiceRegistry> serviceRegistries) {
		this.ftrCodigo = ftrCodigo;
		this.ftrNombre = ftrNombre;
		this.serviceRegistries = serviceRegistries;
	}

	// Property accessors
	@Id
	@Column(name = "FTR_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getFtrCodigo() {
		return this.ftrCodigo;
	}

	public void setFtrCodigo(Long ftrCodigo) {
		this.ftrCodigo = ftrCodigo;
	}

	@Column(name = "FTR_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getFtrNombre() {
		return this.ftrNombre;
	}

	public void setFtrNombre(String ftrNombre) {
		this.ftrNombre = ftrNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "fuelTypeRequest")
	public Set<ServiceRegistry> getServiceRegistries() {
		return this.serviceRegistries;
	}

	public void setServiceRegistries(Set<ServiceRegistry> serviceRegistries) {
		this.serviceRegistries = serviceRegistries;
	}

}