package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Cities entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CITIES", uniqueConstraints = {})
public class Cities implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ctsId;
	private Countries countries;
	private String ctsNombre;
	private Set<InvolvedVehicles> involvedVehicleses = new HashSet<InvolvedVehicles>(0);
	private Set<Witnesses> witnesseses = new HashSet<Witnesses>(0);
	private Set<Locations> locationses = new HashSet<Locations>(0);
	private Set<Accidents> accidentses = new HashSet<Accidents>(0);
	private Set<InjuredPersons> injuredPersonses = new HashSet<InjuredPersons>(0);

	// Constructors

	/** default constructor */
	public Cities() {
	}

	/** minimal constructor */
	public Cities(Long ctsId, Countries countries, String ctsNombre) {
		this.ctsId = ctsId;
		this.countries = countries;
		this.ctsNombre = ctsNombre;
	}

	/** full constructor */
	public Cities(Long ctsId, Countries countries, String ctsNombre, Set<InvolvedVehicles> involvedVehicleses,
			Set<Witnesses> witnesseses, Set<Locations> locationses, Set<Accidents> accidentses,
			Set<InjuredPersons> injuredPersonses) {
		this.ctsId = ctsId;
		this.countries = countries;
		this.ctsNombre = ctsNombre;
		this.involvedVehicleses = involvedVehicleses;
		this.witnesseses = witnesseses;
		this.locationses = locationses;
		this.accidentses = accidentses;
		this.injuredPersonses = injuredPersonses;
	}

	// Property accessors
	@Id
	@Column(name = "CTS_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCtsId() {
		return this.ctsId;
	}

	public void setCtsId(Long ctsId) {
		this.ctsId = ctsId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CNT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Countries getCountries() {
		return this.countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	@Column(name = "CTS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getCtsNombre() {
		return this.ctsNombre;
	}

	public void setCtsNombre(String ctsNombre) {
		this.ctsNombre = ctsNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "cities")
	public Set<InvolvedVehicles> getInvolvedVehicleses() {
		return this.involvedVehicleses;
	}

	public void setInvolvedVehicleses(Set<InvolvedVehicles> involvedVehicleses) {
		this.involvedVehicleses = involvedVehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "cities")
	public Set<Witnesses> getWitnesseses() {
		return this.witnesseses;
	}

	public void setWitnesseses(Set<Witnesses> witnesseses) {
		this.witnesseses = witnesseses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "cities")
	public Set<Locations> getLocationses() {
		return this.locationses;
	}

	public void setLocationses(Set<Locations> locationses) {
		this.locationses = locationses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "cities")
	public Set<Accidents> getAccidentses() {
		return this.accidentses;
	}

	public void setAccidentses(Set<Accidents> accidentses) {
		this.accidentses = accidentses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "cities")
	public Set<InjuredPersons> getInjuredPersonses() {
		return this.injuredPersonses;
	}

	public void setInjuredPersonses(Set<InjuredPersons> injuredPersonses) {
		this.injuredPersonses = injuredPersonses;
	}

}