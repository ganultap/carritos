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
 * AssignationsStates entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ASSIGNATIONS_STATES", schema = "", uniqueConstraints = {})
public class AssignationsStates implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long assCodigo;
	private String assNombre;
	private Set<VehiclesAssignation> vehiclesAssignations = new HashSet<VehiclesAssignation>(0);

	// Constructors

	/** default constructor */
	public AssignationsStates() {
	}

	/** minimal constructor */
	public AssignationsStates(Long assCodigo, String assNombre) {
		this.assCodigo = assCodigo;
		this.assNombre = assNombre;
	}

	/** full constructor */
	public AssignationsStates(Long assCodigo, String assNombre, Set<VehiclesAssignation> vehiclesAssignations) {
		this.assCodigo = assCodigo;
		this.assNombre = assNombre;
		this.vehiclesAssignations = vehiclesAssignations;
	}

	// Property accessors
	@Id
	@Column(name = "ASS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAssCodigo() {
		return this.assCodigo;
	}

	public void setAssCodigo(Long assCodigo) {
		this.assCodigo = assCodigo;
	}

	@Column(name = "ASS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getAssNombre() {
		return this.assNombre;
	}

	public void setAssNombre(String assNombre) {
		this.assNombre = assNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "assignationsStates")
	public Set<VehiclesAssignation> getVehiclesAssignations() {
		return this.vehiclesAssignations;
	}

	public void setVehiclesAssignations(Set<VehiclesAssignation> vehiclesAssignations) {
		this.vehiclesAssignations = vehiclesAssignations;
	}

}