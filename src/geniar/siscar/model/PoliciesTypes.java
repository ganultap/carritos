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
 * PoliciesTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES_TYPES", schema = "", uniqueConstraints = {})
public class PoliciesTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pltCodigo;
	private String pltNombre;
	private Set<PoliciesAplications> policiesAplicationses = new HashSet<PoliciesAplications>(0);
	private Set<ControlAssignationPolicies> controlAssignationPolicieses = new HashSet<ControlAssignationPolicies>(0);
	private Set<Policies> policieses = new HashSet<Policies>(0);

	// Constructors

	/** default constructor */
	public PoliciesTypes() {
	}

	/** minimal constructor */
	public PoliciesTypes(Long pltCodigo, String pltNombre) {
		this.pltCodigo = pltCodigo;
		this.pltNombre = pltNombre;
	}

	/** full constructor */
	public PoliciesTypes(Long pltCodigo, String pltNombre, Set<PoliciesAplications> policiesAplicationses,
			Set<ControlAssignationPolicies> controlAssignationPolicieses, Set<Policies> policieses) {
		this.pltCodigo = pltCodigo;
		this.pltNombre = pltNombre;
		this.policiesAplicationses = policiesAplicationses;
		this.controlAssignationPolicieses = controlAssignationPolicieses;
		this.policieses = policieses;
	}

	// Property accessors
	@Id
	@Column(name = "PLT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPltCodigo() {
		return this.pltCodigo;
	}

	public void setPltCodigo(Long pltCodigo) {
		this.pltCodigo = pltCodigo;
	}

	@Column(name = "PLT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPltNombre() {
		return this.pltNombre;
	}

	public void setPltNombre(String pltNombre) {
		this.pltNombre = pltNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "policiesTypes")
	public Set<PoliciesAplications> getPoliciesAplicationses() {
		return this.policiesAplicationses;
	}

	public void setPoliciesAplicationses(Set<PoliciesAplications> policiesAplicationses) {
		this.policiesAplicationses = policiesAplicationses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "policiesTypes")
	public Set<ControlAssignationPolicies> getControlAssignationPolicieses() {
		return this.controlAssignationPolicieses;
	}

	public void setControlAssignationPolicieses(Set<ControlAssignationPolicies> controlAssignationPolicieses) {
		this.controlAssignationPolicieses = controlAssignationPolicieses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "policiesTypes")
	public Set<Policies> getPolicieses() {
		return this.policieses;
	}

	public void setPolicieses(Set<Policies> policieses) {
		this.policieses = policieses;
	}

}