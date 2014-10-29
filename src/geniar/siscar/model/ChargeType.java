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
 * ChargeType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHARGE_TYPE", schema = "")
public class ChargeType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cgtId;
	private String cgtNombre;
	private String cgtDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public ChargeType() {
	}

	/** minimal constructor */
	public ChargeType(Long cgtId, String cgtNombre) {
		this.cgtId = cgtId;
		this.cgtNombre = cgtNombre;
	}

	/** full constructor */
	public ChargeType(Long cgtId, String cgtNombre, String cgtDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.cgtId = cgtId;
		this.cgtNombre = cgtNombre;
		this.cgtDescripcion = cgtDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "CGT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getCgtId() {
		return this.cgtId;
	}

	public void setCgtId(Long cgtId) {
		this.cgtId = cgtId;
	}

	@Column(name = "CGT_NOMBRE", nullable = false, length = 30)
	public String getCgtNombre() {
		return this.cgtNombre;
	}

	public void setCgtNombre(String cgtNombre) {
		this.cgtNombre = cgtNombre;
	}

	@Column(name = "CGT_DESCRIPCION", length = 200)
	public String getCgtDescripcion() {
		return this.cgtDescripcion;
	}

	public void setCgtDescripcion(String cgtDescripcion) {
		this.cgtDescripcion = cgtDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "chargeType")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}