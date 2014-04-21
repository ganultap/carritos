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
 * MovementType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MOVEMENT_TYPE", schema = "")
public class MovementType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mvmId;
	private String mvmNombre;
	private String mvmDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public MovementType() {
	}

	/** minimal constructor */
	public MovementType(Long mvmId, String mvmNombre) {
		this.mvmId = mvmId;
		this.mvmNombre = mvmNombre;
	}

	/** full constructor */
	public MovementType(Long mvmId, String mvmNombre, String mvmDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.mvmId = mvmId;
		this.mvmNombre = mvmNombre;
		this.mvmDescripcion = mvmDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "MVM_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getMvmId() {
		return this.mvmId;
	}

	public void setMvmId(Long mvmId) {
		this.mvmId = mvmId;
	}

	@Column(name = "MVM_NOMBRE", nullable = false, length = 10)
	public String getMvmNombre() {
		return this.mvmNombre;
	}

	public void setMvmNombre(String mvmNombre) {
		this.mvmNombre = mvmNombre;
	}

	@Column(name = "MVM_DESCRIPCION", length = 200)
	public String getMvmDescripcion() {
		return this.mvmDescripcion;
	}

	public void setMvmDescripcion(String mvmDescripcion) {
		this.mvmDescripcion = mvmDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "movementType")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}