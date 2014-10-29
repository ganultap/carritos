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
 * Future entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FUTURE", schema = "")
public class Future implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long freId;
	private String freValor;
	private String freDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public Future() {
	}

	/** minimal constructor */
	public Future(Long freId, String freValor) {
		this.freId = freId;
		this.freValor = freValor;
	}

	/** full constructor */
	public Future(Long freId, String freValor, String freDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.freId = freId;
		this.freValor = freValor;
		this.freDescripcion = freDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "FRE_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getFreId() {
		return this.freId;
	}

	public void setFreId(Long freId) {
		this.freId = freId;
	}

	@Column(name = "FRE_VALOR", nullable = false, length = 30)
	public String getFreValor() {
		return this.freValor;
	}

	public void setFreValor(String freValor) {
		this.freValor = freValor;
	}

	@Column(name = "FRE_DESCRIPCION", length = 200)
	public String getFreDescripcion() {
		return this.freDescripcion;
	}

	public void setFreDescripcion(String freDescripcion) {
		this.freDescripcion = freDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "future")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}