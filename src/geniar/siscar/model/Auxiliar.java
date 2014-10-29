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
 * Auxiliar entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "AUXILIAR", schema = "")
public class Auxiliar implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long auxId;
	private String auxValor;
	private String auxDescripcion;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);

	// Constructors

	/** default constructor */
	public Auxiliar() {
	}

	/** minimal constructor */
	public Auxiliar(Long auxId, String auxValor) {
		this.auxId = auxId;
		this.auxValor = auxValor;
	}

	/** full constructor */
	public Auxiliar(Long auxId, String auxValor, String auxDescripcion,
			Set<AccountingParameters> accountingParameterses) {
		this.auxId = auxId;
		this.auxValor = auxValor;
		this.auxDescripcion = auxDescripcion;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@Column(name = "AUX_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getAuxId() {
		return this.auxId;
	}

	public void setAuxId(Long auxId) {
		this.auxId = auxId;
	}

	@Column(name = "AUX_VALOR", nullable = false, length = 40)
	public String getAuxValor() {
		return this.auxValor;
	}

	public void setAuxValor(String auxValor) {
		this.auxValor = auxValor;
	}

	@Column(name = "AUX_DESCRIPCION", length = 200)
	public String getAuxDescripcion() {
		return this.auxDescripcion;
	}

	public void setAuxDescripcion(String auxDescripcion) {
		this.auxDescripcion = auxDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "auxiliar")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

}