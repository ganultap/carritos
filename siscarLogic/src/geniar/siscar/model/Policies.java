package geniar.siscar.model;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Policies entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES", schema = "", uniqueConstraints = {})
public class Policies implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long plsCodigo;
	private PoliciesTypes policiesTypes;
	private Long plsNumero;
	private Date plsFechainicioCobertura;
	private Date plsFechafinCobertura;
	private String plsDocUno;
	private String plsDocDos;
	private Long plsEstado;
	private Set<PoliciesInvoice> policiesInvoices = new HashSet<PoliciesInvoice>(
			0);
	private Set<PoliciesVehicles> policiesVehicleses = new HashSet<PoliciesVehicles>(
			0);
	private Set<Inconsistencies> inconsistencieses = new HashSet<Inconsistencies>(
			0);

	// Constructors

	/** default constructor */
	public Policies() {
	}

	/** minimal constructor */
	public Policies(Long plsCodigo, PoliciesTypes policiesTypes,
			Long plsNumero, Date plsFechainicioCobertura,
			Date plsFechafinCobertura, Long plsEstado) {
		this.plsCodigo = plsCodigo;
		this.policiesTypes = policiesTypes;
		this.plsNumero = plsNumero;
		this.plsFechainicioCobertura = plsFechainicioCobertura;
		this.plsFechafinCobertura = plsFechafinCobertura;
		this.plsEstado = plsEstado;
	}

	/** full constructor */
	public Policies(Long plsCodigo, PoliciesTypes policiesTypes,
			Long plsNumero, Date plsFechainicioCobertura,
			Date plsFechafinCobertura, String plsDocUno, String plsDocDos,
			Long plsEstado, Set<PoliciesInvoice> policiesInvoices,
			Set<PoliciesVehicles> policiesVehicleses) {
		this.plsCodigo = plsCodigo;
		this.policiesTypes = policiesTypes;
		this.plsNumero = plsNumero;
		this.plsFechainicioCobertura = plsFechainicioCobertura;
		this.plsFechafinCobertura = plsFechafinCobertura;
		this.plsDocUno = plsDocUno;
		this.plsDocDos = plsDocDos;
		this.plsEstado = plsEstado;
		this.policiesInvoices = policiesInvoices;
		this.policiesVehicleses = policiesVehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "POLICES_GEN", sequenceName = "SQ_POLICES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLICES_GEN")
	@Column(name = "PLS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPlsCodigo() {
		return this.plsCodigo;
	}

	public void setPlsCodigo(Long plsCodigo) {
		this.plsCodigo = plsCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public PoliciesTypes getPoliciesTypes() {
		return this.policiesTypes;
	}

	public void setPoliciesTypes(PoliciesTypes policiesTypes) {
		this.policiesTypes = policiesTypes;
	}

	@Column(name = "PLS_NUMERO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPlsNumero() {
		return this.plsNumero;
	}

	public void setPlsNumero(Long plsNumero) {
		this.plsNumero = plsNumero;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLS_FECHAINICIO_COBERTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPlsFechainicioCobertura() {
		return this.plsFechainicioCobertura;
	}

	public void setPlsFechainicioCobertura(Date plsFechainicioCobertura) {
		this.plsFechainicioCobertura = plsFechainicioCobertura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLS_FECHAFIN_COBERTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPlsFechafinCobertura() {
		return this.plsFechafinCobertura;
	}

	public void setPlsFechafinCobertura(Date plsFechafinCobertura) {
		this.plsFechafinCobertura = plsFechafinCobertura;
	}

	@Column(name = "PLS_DOC_UNO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPlsDocUno() {
		return this.plsDocUno;
	}

	public void setPlsDocUno(String plsDocUno) {
		this.plsDocUno = plsDocUno;
	}

	@Column(name = "PLS_DOC_DOS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPlsDocDos() {
		return this.plsDocDos;
	}

	public void setPlsDocDos(String plsDocDos) {
		this.plsDocDos = plsDocDos;
	}

	@Column(name = "PLS_ESTADO", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
	public Long getPlsEstado() {
		return this.plsEstado;
	}

	public void setPlsEstado(Long plsEstado) {
		this.plsEstado = plsEstado;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "policies")
	public Set<PoliciesInvoice> getPoliciesInvoices() {
		return this.policiesInvoices;
	}

	public void setPoliciesInvoices(Set<PoliciesInvoice> policiesInvoices) {
		this.policiesInvoices = policiesInvoices;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "policies")
	public Set<PoliciesVehicles> getPoliciesVehicleses() {
		return this.policiesVehicleses;
	}

	public void setPoliciesVehicleses(Set<PoliciesVehicles> policiesVehicleses) {
		this.policiesVehicleses = policiesVehicleses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policies")
	public Set<Inconsistencies> getInconsistencieses() {
		return this.inconsistencieses;
	}

	public void setInconsistencieses(Set<Inconsistencies> inconsistencieses) {
		this.inconsistencieses = inconsistencieses;
	}

}