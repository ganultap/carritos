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
 * Line entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LINE", schema = "")
public class Line implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long linId;
	private String linValor;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public Line() {
	}

	/** minimal constructor */
	public Line(Long linId, String linValor) {
		this.linId = linId;
		this.linValor = linValor;
	}

	/** full constructor */
	public Line(Long linId, String linValor,
			Set<AccountingParameters> accountingParameterses) {
		this.linId = linId;
		this.linValor = linValor;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "LIN_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getLinId() {
		return this.linId;
	}

	public void setLinId(Long linId) {
		this.linId = linId;
	}

	@Column(name = "LIN_VALOR", nullable = false, length = 10)
	public String getLinValor() {
		return this.linValor;
	}

	public void setLinValor(String linValor) {
		this.linValor = linValor;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "line")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}