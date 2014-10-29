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
 * Attribute entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ATTRIBUTE", schema = "")
public class Attribute implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long atbId;
	private String atbValor;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public Attribute() {
	}

	/** minimal constructor */
	public Attribute(Long atbId) {
		this.atbId = atbId;
	}

	/** full constructor */
	public Attribute(Long atbId, String atbValor,
			Set<AccountingParameters> accountingParameterses) {
		this.atbId = atbId;
		this.atbValor = atbValor;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "ATB_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getAtbId() {
		return this.atbId;
	}

	public void setAtbId(Long atbId) {
		this.atbId = atbId;
	}

	@Column(name = "ATB_VALOR", length = 10)
	public String getAtbValor() {
		return this.atbValor;
	}

	public void setAtbValor(String atbValor) {
		this.atbValor = atbValor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "attribute")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}