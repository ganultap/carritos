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
 * ControlType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTROL_TYPE", schema = "")
public class ControlType implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cttId;
	private String cttValor;
	private String cttDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public ControlType() {
	}

	/** minimal constructor */
	public ControlType(Long cttId, String cttValor) {
		this.cttId = cttId;
		this.cttValor = cttValor;
	}

	/** full constructor */
	public ControlType(Long cttId, String cttValor, String cttDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.cttId = cttId;
		this.cttValor = cttValor;
		this.cttDescripcion = cttDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "CTT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getCttId() {
		return this.cttId;
	}

	public void setCttId(Long cttId) {
		this.cttId = cttId;
	}

	@Column(name = "CTT_VALOR", nullable = false, length = 30)
	public String getCttValor() {
		return this.cttValor;
	}

	public void setCttValor(String cttValor) {
		this.cttValor = cttValor;
	}

	@Column(name = "CTT_DESCRIPCION", length = 200)
	public String getCttDescripcion() {
		return this.cttDescripcion;
	}

	public void setCttDescripcion(String cttDescripcion) {
		this.cttDescripcion = cttDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "controlType")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}