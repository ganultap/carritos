package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * ControlAssignationPolicies entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTROL_ASSIGNATION_POLICIES", schema = "")
public class ControlAssignationPolicies implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long capId;
	private LegateesTypes legateesTypes;
	private PoliciesTypes policiesTypes;
	private LocationsTypes locationsTypes;

	// Constructors

	/** default constructor */
	public ControlAssignationPolicies() {
	}

	/** minimal constructor */
	public ControlAssignationPolicies(Long capId) {
		this.capId = capId;
	}

	/** full constructor */
	public ControlAssignationPolicies(Long capId, LegateesTypes legateesTypes,
			PoliciesTypes policiesTypes, LocationsTypes locationsTypes) {
		this.capId = capId;
		this.legateesTypes = legateesTypes;
		this.policiesTypes = policiesTypes;
		this.locationsTypes = locationsTypes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="CAP_GEN", sequenceName="SQ_CTRL_ASSIGN_POLICY", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CAP_GEN")
	@Column(name = "CAP_ID", unique = true, nullable = false, precision = 38, scale = 0)
	public Long getCapId() {
		return this.capId;
	}

	public void setCapId(Long capId) {
		this.capId = capId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LGT_CODIGO")
	public LegateesTypes getLegateesTypes() {
		return this.legateesTypes;
	}

	public void setLegateesTypes(LegateesTypes legateesTypes) {
		this.legateesTypes = legateesTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLT_CODIGO")
	public PoliciesTypes getPoliciesTypes() {
		return this.policiesTypes;
	}

	public void setPoliciesTypes(PoliciesTypes policiesTypes) {
		this.policiesTypes = policiesTypes;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LCT_CODIGO")
	public LocationsTypes getLocationsTypes() {
		return this.locationsTypes;
	}

	public void setLocationsTypes(LocationsTypes locationsTypes) {
		this.locationsTypes = locationsTypes;
	}

}