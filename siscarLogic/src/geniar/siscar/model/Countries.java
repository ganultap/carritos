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
 * Countries entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COUNTRIES", schema = "", uniqueConstraints = {})
public class Countries implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cntId;
	private String cntNombre;
	private Set<Cities> citieses = new HashSet<Cities>(0);

	// Constructors

	/** default constructor */
	public Countries() {
	}

	/** minimal constructor */
	public Countries(Long cntId, String cntNombre) {
		this.cntId = cntId;
		this.cntNombre = cntNombre;
	}

	/** full constructor */
	public Countries(Long cntId, String cntNombre, Set<Cities> citieses) {
		this.cntId = cntId;
		this.cntNombre = cntNombre;
		this.citieses = citieses;
	}

	// Property accessors
	@Id
	@Column(name = "CNT_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCntId() {
		return this.cntId;
	}

	public void setCntId(Long cntId) {
		this.cntId = cntId;
	}

	@Column(name = "CNT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getCntNombre() {
		return this.cntNombre;
	}

	public void setCntNombre(String cntNombre) {
		this.cntNombre = cntNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "countries")
	public Set<Cities> getCitieses() {
		return this.citieses;
	}

	public void setCitieses(Set<Cities> citieses) {
		this.citieses = citieses;
	}

}