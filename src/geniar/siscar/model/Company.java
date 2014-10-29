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
 * Company entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COMPANY", schema = "")
public class Company implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cmpId;
	private String cmpNombre;
	private String cmpDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(Long cmpId, String cmpNombre) {
		this.cmpId = cmpId;
		this.cmpNombre = cmpNombre;
	}

	/** full constructor */
	public Company(Long cmpId, String cmpNombre, String cmpDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.cmpId = cmpId;
		this.cmpNombre = cmpNombre;
		this.cmpDescripcion = cmpDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "CMP_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getCmpId() {
		return this.cmpId;
	}

	public void setCmpId(Long cmpId) {
		this.cmpId = cmpId;
	}

	@Column(name = "CMP_NOMBRE", nullable = false, length = 30)
	public String getCmpNombre() {
		return this.cmpNombre;
	}

	public void setCmpNombre(String cmpNombre) {
		this.cmpNombre = cmpNombre;
	}

	@Column(name = "CMP_DESCRIPCION", length = 200)
	public String getCmpDescripcion() {
		return this.cmpDescripcion;
	}

	public void setCmpDescripcion(String cmpDescripcion) {
		this.cmpDescripcion = cmpDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}