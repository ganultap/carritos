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
 * DescriptionType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DESCRIPTION_TYPE", schema = "")
public class DescriptionType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dstId;
	private String dstValor;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public DescriptionType() {
	}

	/** minimal constructor */
	public DescriptionType(Long dstId, String dstValor) {
		this.dstId = dstId;
		this.dstValor = dstValor;
	}

	/** full constructor */
	public DescriptionType(Long dstId, String dstValor,
			Set<AccountingParameters> accountingParameterses) {
		this.dstId = dstId;
		this.dstValor = dstValor;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "DST_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getDstId() {
		return this.dstId;
	}

	public void setDstId(Long dstId) {
		this.dstId = dstId;
	}

	@Column(name = "DST_VALOR", nullable = false, length = 120)
	public String getDstValor() {
		return this.dstValor;
	}

	public void setDstValor(String dstValor) {
		this.dstValor = dstValor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "descriptionType")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}