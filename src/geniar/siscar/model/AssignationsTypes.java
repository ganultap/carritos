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
 * AssignationsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ASSIGNATIONS_TYPES", uniqueConstraints = {})
public class AssignationsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long astCodigo;
	private String astNombre;
	private Set<VehiclesAssignation> vehiclesAssignations = new HashSet<VehiclesAssignation>(0);

	// Constructors

	/** default constructor */
	public AssignationsTypes() {
	}

	/** minimal constructor */
	public AssignationsTypes(Long astCodigo, String astNombre) {
		this.astCodigo = astCodigo;
		this.astNombre = astNombre;
	}

	/** full constructor */
	public AssignationsTypes(Long astCodigo, String astNombre, Set<VehiclesAssignation> vehiclesAssignations) {
		this.astCodigo = astCodigo;
		this.astNombre = astNombre;
		this.vehiclesAssignations = vehiclesAssignations;
	}

	// Property accessors
	@Id
	@Column(name = "AST_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAstCodigo() {
		return this.astCodigo;
	}

	public void setAstCodigo(Long astCodigo) {
		this.astCodigo = astCodigo;
	}

	@Column(name = "AST_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getAstNombre() {
		return this.astNombre;
	}

	public void setAstNombre(String astNombre) {
		this.astNombre = astNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "assignationsTypes")
	public Set<VehiclesAssignation> getVehiclesAssignations() {
		return this.vehiclesAssignations;
	}

	public void setVehiclesAssignations(Set<VehiclesAssignation> vehiclesAssignations) {
		this.vehiclesAssignations = vehiclesAssignations;
	}

}