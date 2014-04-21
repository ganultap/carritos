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
 * TapestriesTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TAPESTRIES_TYPES", uniqueConstraints = {})
public class TapestriesTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long tptpcCodigo;
	private String tptpcNombre;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

	// Constructors

	/** default constructor */
	public TapestriesTypes() {
	}

	/** minimal constructor */
	public TapestriesTypes(Long tptpcCodigo, String tptpcNombre) {
		this.tptpcCodigo = tptpcCodigo;
		this.tptpcNombre = tptpcNombre;
	}

	/** full constructor */
	public TapestriesTypes(Long tptpcCodigo, String tptpcNombre, Set<Vehicles> vehicleses) {
		this.tptpcCodigo = tptpcCodigo;
		this.tptpcNombre = tptpcNombre;
		this.vehicleses = vehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="TAPESTRIES_TYPES_GEN", sequenceName="SQ_TAPESTRIES_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TAPESTRIES_TYPES_GEN")
	@Column(name = "TPTPC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getTptpcCodigo() {
		return this.tptpcCodigo;
	}

	public void setTptpcCodigo(Long tptpcCodigo) {
		this.tptpcCodigo = tptpcCodigo;
	}

	@Column(name = "TPTPC_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getTptpcNombre() {
		return this.tptpcNombre;
	}

	public void setTptpcNombre(String tptpcNombre) {
		this.tptpcNombre = tptpcNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tapestriesTypes")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

}