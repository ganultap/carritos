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
 * MoneyType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MONEY_TYPE", schema = "", uniqueConstraints = {})
public class MoneyType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mneyId;
	private String mneyNombre;
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);

	// Constructors

	/** default constructor */
	public MoneyType() {
	}

	/** minimal constructor */
	public MoneyType(Long mneyId, String mneyNombre) {
		this.mneyId = mneyId;
		this.mneyNombre = mneyNombre;
	}

	/** full constructor */
	public MoneyType(Long mneyId, String mneyNombre, Set<Tariffs> tariffses) {
		this.mneyId = mneyId;
		this.mneyNombre = mneyNombre;
		this.tariffses = tariffses;
	}

	// Property accessors
	@Id
	@Column(name = "MNEY_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getMneyId() {
		return this.mneyId;
	}

	public void setMneyId(Long mneyId) {
		this.mneyId = mneyId;
	}

	@Column(name = "MNEY_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getMneyNombre() {
		return this.mneyNombre;
	}

	public void setMneyNombre(String mneyNombre) {
		this.mneyNombre = mneyNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "moneyType")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

}