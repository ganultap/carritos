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
 * VehiclesStates entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VEHICLES_STATES", uniqueConstraints = {})
public class VehiclesStates implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long vhsCodigo;
	private String vhsNombre;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

	// Constructors

	/** default constructor */
	public VehiclesStates() {
	}

	/** minimal constructor */
	public VehiclesStates(Long vhsCodigo, String vhsNombre) {
		this.vhsCodigo = vhsCodigo;
		this.vhsNombre = vhsNombre;
	}

	/** full constructor */
	public VehiclesStates(Long vhsCodigo, String vhsNombre, Set<Vehicles> vehicleses) {
		this.vhsCodigo = vhsCodigo;
		this.vhsNombre = vhsNombre;
		this.vehicleses = vehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="VEHICLES_STATES_GEN", sequenceName="SQ_VEHICLES_STATES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VEHICLES_STATES_GEN")
	@Column(name = "VHS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhsCodigo() {
		return this.vhsCodigo;
	}

	public void setVhsCodigo(Long vhsCodigo) {
		this.vhsCodigo = vhsCodigo;
	}

	@Column(name = "VHS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getVhsNombre() {
		return this.vhsNombre;
	}

	public void setVhsNombre(String vhsNombre) {
		this.vhsNombre = vhsNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesStates")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

}