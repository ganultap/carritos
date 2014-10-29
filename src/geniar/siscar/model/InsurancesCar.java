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
 * InsurancesCar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INSURANCES_CAR", schema = "", uniqueConstraints = {})
public class InsurancesCar implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long insCodigo;
	private String insNombre;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

	// Constructors

	/** default constructor */
	public InsurancesCar() {
	}

	/** minimal constructor */
	public InsurancesCar(Long insCodigo) {
		this.insCodigo = insCodigo;
	}

	/** full constructor */
	public InsurancesCar(Long insCodigo, String insNombre, Set<Vehicles> vehicleses) {
		this.insCodigo = insCodigo;
		this.insNombre = insNombre;
		this.vehicleses = vehicleses;
	}

	// Property accessors
	@Id
	@Column(name = "INS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getInsCodigo() {
		return this.insCodigo;
	}

	public void setInsCodigo(Long insCodigo) {
		this.insCodigo = insCodigo;
	}

	@Column(name = "INS_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getInsNombre() {
		return this.insNombre;
	}

	public void setInsNombre(String insNombre) {
		this.insNombre = insNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "insurancesCar")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

}