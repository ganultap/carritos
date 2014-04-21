package geniar.siscar.model;
// default package

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
 * LegateesTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LEGATEES_TYPES", schema = "")
public class LegateesTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lgtCodigo;
	private String lgtNombre;
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);
	private Set<Requests> requestses = new HashSet<Requests>(0);
	private Set<PoliciesAplications> policiesAplicationses = new HashSet<PoliciesAplications>(
			0);
	private Set<TariffsLegateeTypes> tariffsLegateeTypeses = new HashSet<TariffsLegateeTypes>(
			0);
	private Set<ControlAssignationPolicies> controlAssignationPolicieses = new HashSet<ControlAssignationPolicies>(
			0);

	// Constructors

	/** default constructor */
	public LegateesTypes() {
	}

	/** minimal constructor */
	public LegateesTypes(Long lgtCodigo) {
		this.lgtCodigo = lgtCodigo;
	}

	/** full constructor */
	public LegateesTypes(Long lgtCodigo, String lgtNombre,
			Set<AccountingParameters> accountingParameterses,
			Set<Requests> requestses,
			Set<PoliciesAplications> policiesAplicationses,
			Set<TariffsLegateeTypes> tariffsLegateeTypeses,
			Set<ControlAssignationPolicies> controlAssignationPolicieses) {
		this.lgtCodigo = lgtCodigo;
		this.lgtNombre = lgtNombre;
		this.accountingParameterses = accountingParameterses;
		this.requestses = requestses;
		this.policiesAplicationses = policiesAplicationses;
		this.tariffsLegateeTypeses = tariffsLegateeTypeses;
		this.controlAssignationPolicieses = controlAssignationPolicieses;
	}

	// Property accessors
	@Id
	@Column(name = "LGT_CODIGO", unique = true, nullable = false, precision = 38, scale = 0)
	public Long getLgtCodigo() {
		return this.lgtCodigo;
	}

	public void setLgtCodigo(Long lgtCodigo) {
		this.lgtCodigo = lgtCodigo;
	}

	@Column(name = "LGT_NOMBRE",unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLgtNombre() {
		return this.lgtNombre;
	}

	public void setLgtNombre(String lgtNombre) {
		this.lgtNombre = lgtNombre;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "legateesTypes")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "legateesTypes")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "legateesTypes")
	public Set<PoliciesAplications> getPoliciesAplicationses() {
		return this.policiesAplicationses;
	}

	public void setPoliciesAplicationses(
			Set<PoliciesAplications> policiesAplicationses) {
		this.policiesAplicationses = policiesAplicationses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "legateesTypes")
	public Set<TariffsLegateeTypes> getTariffsLegateeTypeses() {
		return this.tariffsLegateeTypeses;
	}

	public void setTariffsLegateeTypeses(
			Set<TariffsLegateeTypes> tariffsLegateeTypeses) {
		this.tariffsLegateeTypeses = tariffsLegateeTypeses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "legateesTypes")
	public Set<ControlAssignationPolicies> getControlAssignationPolicieses() {
		return this.controlAssignationPolicieses;
	}

	public void setControlAssignationPolicieses(
			Set<ControlAssignationPolicies> controlAssignationPolicieses) {
		this.controlAssignationPolicieses = controlAssignationPolicieses;
	}

}