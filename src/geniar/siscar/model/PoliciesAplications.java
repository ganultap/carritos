package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PoliciesAplications entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES_APLICATIONS", schema = "", uniqueConstraints = {})
public class PoliciesAplications implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long polCodigo;
	private LegateesTypes legateesTypes;
	private PoliciesTypes policiesTypes;
	private LocationsTypes locationsTypes;

	// Constructors

	/** default constructor */
	public PoliciesAplications() {
	}

	/** full constructor */
	public PoliciesAplications(Long polCodigo, LegateesTypes legateesTypes, PoliciesTypes policiesTypes,
			LocationsTypes locationsTypes) {
		this.polCodigo = polCodigo;
		this.legateesTypes = legateesTypes;
		this.policiesTypes = policiesTypes;
		this.locationsTypes = locationsTypes;
	}

	// Property accessors
	@Id
	@Column(name = "POL_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPolCodigo() {
		return this.polCodigo;
	}

	public void setPolCodigo(Long polCodigo) {
		this.polCodigo = polCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LGT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public LegateesTypes getLegateesTypes() {
		return this.legateesTypes;
	}

	public void setLegateesTypes(LegateesTypes legateesTypes) {
		this.legateesTypes = legateesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public PoliciesTypes getPoliciesTypes() {
		return this.policiesTypes;
	}

	public void setPoliciesTypes(PoliciesTypes policiesTypes) {
		this.policiesTypes = policiesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LCT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public LocationsTypes getLocationsTypes() {
		return this.locationsTypes;
	}

	public void setLocationsTypes(LocationsTypes locationsTypes) {
		this.locationsTypes = locationsTypes;
	}

}