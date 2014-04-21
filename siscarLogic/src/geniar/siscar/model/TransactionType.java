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
 * TransactionType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANSACTION_TYPE", schema = "")
public class TransactionType implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long tstId;
	private String tstNombre;
	private String tstDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);
	private Set<HeaderProof> headerProofs = new HashSet<HeaderProof>(0);

	// Constructors

	/** default constructor */
	public TransactionType() {
	}

	/** minimal constructor */
	public TransactionType(Long tstId, String tstNombre) {
		this.tstId = tstId;
		this.tstNombre = tstNombre;
	}

	/** full constructor */
	public TransactionType(Long tstId, String tstNombre, String tstDescripcion,
			Set<AccountingParameters> accountingParameterses,
			Set<HeaderProof> headerProofs) {
		this.tstId = tstId;
		this.tstNombre = tstNombre;
		this.tstDescripcion = tstDescripcion;
		this.accountingParameterses = accountingParameterses;
		this.headerProofs = headerProofs;
	}

	// Property accessors
	@Id
	@Column(name = "TST_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getTstId() {
		return this.tstId;
	}

	public void setTstId(Long tstId) {
		this.tstId = tstId;
	}

	@Column(name = "TST_NOMBRE", nullable = false, length = 100)
	public String getTstNombre() {
		return this.tstNombre;
	}

	public void setTstNombre(String tstNombre) {
		this.tstNombre = tstNombre;
	}

	@Column(name = "TST_DESCRIPCION", length = 200)
	public String getTstDescripcion() {
		return this.tstDescripcion;
	}

	public void setTstDescripcion(String tstDescripcion) {
		this.tstDescripcion = tstDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "transactionType")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "transactionType")
	public Set<HeaderProof> getHeaderProofs() {
		return this.headerProofs;
	}

	public void setHeaderProofs(Set<HeaderProof> headerProofs) {
		this.headerProofs = headerProofs;
	}

}