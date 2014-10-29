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
 * Account entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNT", schema = "")
public class Account implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accId;
	private String accNumeroCuenta;
	private String accDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public Account() {
	}

	/** minimal constructor */
	public Account(Long accId, String accNumeroCuenta) {
		this.accId = accId;
		this.accNumeroCuenta = accNumeroCuenta;
	}

	/** full constructor */
	public Account(Long accId, String accNumeroCuenta, String accDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.accId = accId;
		this.accNumeroCuenta = accNumeroCuenta;
		this.accDescripcion = accDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "ACC_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getAccId() {
		return this.accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	@Column(name = "ACC_NUMERO_CUENTA", nullable = false, length = 30)
	public String getAccNumeroCuenta() {
		return this.accNumeroCuenta;
	}

	public void setAccNumeroCuenta(String accNumeroCuenta) {
		this.accNumeroCuenta = accNumeroCuenta;
	}

	@Column(name = "ACC_DESCRIPCION", length = 200)
	public String getAccDescripcion() {
		return this.accDescripcion;
	}

	public void setAccDescripcion(String accDescripcion) {
		this.accDescripcion = accDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "account")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}