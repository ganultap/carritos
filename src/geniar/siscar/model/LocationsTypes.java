package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * LocationsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOCATIONS_TYPES", uniqueConstraints = {})
public class LocationsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lctCodigo;
	private String lctNombre;
	private Set<Collections> collectionses = new HashSet<Collections>(0);
	private Set<PoliciesAplications> policiesAplicationses = new HashSet<PoliciesAplications>(0);
	private Set<Locations> locationses = new HashSet<Locations>(0);
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(0);
	private Set<ControlAssignationPolicies> controlAssignationPolicieses = new HashSet<ControlAssignationPolicies>(0);

	// Constructors

	/** default constructor */
	public LocationsTypes() {
	}

	/** minimal constructor */
	public LocationsTypes(Long lctCodigo, String lctNombre) {
		this.lctCodigo = lctCodigo;
		this.lctNombre = lctNombre;
	}

	/** full constructor */
	public LocationsTypes(Long lctCodigo, String lctNombre, Set<Collections> collectionses,
			Set<PoliciesAplications> policiesAplicationses, Set<Locations> locationses,
			Set<ControlAssignationPolicies> controlAssignationPolicieses,
			Set<AccountingParameters> accountingParameterses) {
		this.lctCodigo = lctCodigo;
		this.lctNombre = lctNombre;
		this.collectionses = collectionses;
		this.policiesAplicationses = policiesAplicationses;
		this.locationses = locationses;
		this.controlAssignationPolicieses = controlAssignationPolicieses;
		this.accountingParameterses = accountingParameterses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="LOCATIONS_TYPES_GEN", sequenceName="SQ_LOCATIONS_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_TYPES_GEN")
	@Column(name = "LCT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getLctCodigo() {
		return this.lctCodigo;
	}

	public void setLctCodigo(Long lctCodigo) {
		this.lctCodigo = lctCodigo;
	}

	@Column(name = "LCT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getLctNombre() {
		return this.lctNombre;
	}

	public void setLctNombre(String lctNombre) {
		this.lctNombre = lctNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locationsTypes")
	public Set<Collections> getCollectionses() {
		return this.collectionses;
	}

	public void setCollectionses(Set<Collections> collectionses) {
		this.collectionses = collectionses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locationsTypes")
	public Set<PoliciesAplications> getPoliciesAplicationses() {
		return this.policiesAplicationses;
	}

	public void setPoliciesAplicationses(Set<PoliciesAplications> policiesAplicationses) {
		this.policiesAplicationses = policiesAplicationses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locationsTypes")
	public Set<Locations> getLocationses() {
		return this.locationses;
	}

	public void setLocationses(Set<Locations> locationses) {
		this.locationses = locationses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locationsTypes")
	public Set<ControlAssignationPolicies> getControlAssignationPolicieses() {
		return this.controlAssignationPolicieses;
	}

	public void setControlAssignationPolicieses(Set<ControlAssignationPolicies> controlAssignationPolicieses) {
		this.controlAssignationPolicieses = controlAssignationPolicieses;
	}
	
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "locationsTypes")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}
}