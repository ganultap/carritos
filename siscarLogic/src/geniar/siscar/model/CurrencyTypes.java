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
 * CurrencyTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CURRENCY_TYPES", schema = "", uniqueConstraints = {})
public class CurrencyTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ctId;
	private String ctNombre;
	private Set<PlainFileParameter> plainFileParameters = new HashSet<PlainFileParameter>(
			0);
	private Set<HeaderProof> headerProofs = new HashSet<HeaderProof>(0);

	// Constructors

	/** default constructor */
	public CurrencyTypes() {
	}

	/** minimal constructor */
	public CurrencyTypes(Long ctId, String ctNombre) {
		this.ctId = ctId;
		this.ctNombre = ctNombre;
	}

	/** full constructor */
	public CurrencyTypes(Long ctId, String ctNombre,
			Set<PlainFileParameter> plainFileParameters,
			Set<HeaderProof> headerProofs) {
		this.ctId = ctId;
		this.ctNombre = ctNombre;
		this.plainFileParameters = plainFileParameters;
		this.headerProofs = headerProofs;
	}

	// Property accessors
	@Id
	@Column(name = "CT_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCtId() {
		return this.ctId;
	}

	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	@Column(name = "CT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getCtNombre() {
		return this.ctNombre;
	}

	public void setCtNombre(String ctNombre) {
		this.ctNombre = ctNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "currencyTypes")
	public Set<PlainFileParameter> getPlainFileParameters() {
		return this.plainFileParameters;
	}

	public void setPlainFileParameters(
			Set<PlainFileParameter> plainFileParameters) {
		this.plainFileParameters = plainFileParameters;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "currencyTypes")
	public Set<HeaderProof> getHeaderProofs() {
		return this.headerProofs;
	}

	public void setHeaderProofs(Set<HeaderProof> headerProofs) {
		this.headerProofs = headerProofs;
	}
}