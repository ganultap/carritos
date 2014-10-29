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
 * AccidentsStates entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCIDENTS_STATES", schema = "", uniqueConstraints = {})
public class AccidentsStates implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long acsCode;
	private String acsNombre;
	private Set<Accidents> accidentses = new HashSet<Accidents>(0);

	// Constructors

	/** default constructor */
	public AccidentsStates() {
	}

	/** minimal constructor */
	public AccidentsStates(Long acsCode, String acsNombre) {
		this.acsCode = acsCode;
		this.acsNombre = acsNombre;
	}

	/** full constructor */
	public AccidentsStates(Long acsCode, String acsNombre, Set<Accidents> accidentses) {
		this.acsCode = acsCode;
		this.acsNombre = acsNombre;
		this.accidentses = accidentses;
	}

	// Property accessors
	@Id
	@Column(name = "ACS_CODE", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAcsCode() {
		return this.acsCode;
	}

	public void setAcsCode(Long acsCode) {
		this.acsCode = acsCode;
	}

	@Column(name = "ACS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getAcsNombre() {
		return this.acsNombre;
	}

	public void setAcsNombre(String acsNombre) {
		this.acsNombre = acsNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "accidentsStates")
	public Set<Accidents> getAccidentses() {
		return this.accidentses;
	}

	public void setAccidentses(Set<Accidents> accidentses) {
		this.accidentses = accidentses;
	}

}