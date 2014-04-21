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
 * PoliciesInvoice entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES_INVOICE", schema = "", uniqueConstraints = {})
public class PoliciesInvoice implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pinId;
	private Policies policies;
	private String pinNumeroFactura;
	private Long pinCargado;
	private Date pinFechaFactura;
	private String pinConcepto;
	private Set<InvoiceHeader> invoiceHeaders = new HashSet<InvoiceHeader>(0);
	private Set<Inconsistencies> inconsistencieses = new HashSet<Inconsistencies>(
			0);
	private Set<PoliciesVehicles> policiesVehicleses = new HashSet<PoliciesVehicles>(
			0);

	// Constructors

	/** default constructor */
	public PoliciesInvoice() {
	}

	/** minimal constructor */
	public PoliciesInvoice(Long pinId, Policies policies,
			String pinNumeroFactura, Date pinFechaFactura, String pinConcepto,
			Long pinCargado) {
		this.pinId = pinId;
		this.policies = policies;
		this.pinNumeroFactura = pinNumeroFactura;
		this.pinFechaFactura = pinFechaFactura;
		this.pinConcepto = pinConcepto;
		this.pinCargado = pinCargado;
	}

	/** full constructor */
	public PoliciesInvoice(Long pinId, Policies policies,
			String pinNumeroFactura, Date pinFechaFactura, String pinConcepto,
			Set<Inconsistencies> inconsistencieses, Long pinCargado,
			Set<PoliciesVehicles> policiesVehicleses) {
		this.pinId = pinId;
		this.policies = policies;
		this.pinNumeroFactura = pinNumeroFactura;
		this.pinFechaFactura = pinFechaFactura;
		this.pinConcepto = pinConcepto;
		this.inconsistencieses = inconsistencieses;
		this.pinCargado = pinCargado;
		this.policiesVehicleses = policiesVehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "POLINV_GEN", sequenceName = "SQ_POLICES_INVOICE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLINV_GEN")
	@Column(name = "PIN_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPinId() {
		return this.pinId;
	}

	public void setPinId(Long pinId) {
		this.pinId = pinId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLS_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Policies getPolicies() {
		return this.policies;
	}

	public void setPolicies(Policies policies) {
		this.policies = policies;
	}

	@Column(name = "PIN_NUMERO_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPinNumeroFactura() {
		return this.pinNumeroFactura;
	}

	public void setPinNumeroFactura(String pinNumeroFactura) {
		this.pinNumeroFactura = pinNumeroFactura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PIN_FECHA_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPinFechaFactura() {
		return this.pinFechaFactura;
	}

	public void setPinFechaFactura(Date pinFechaFactura) {
		this.pinFechaFactura = pinFechaFactura;
	}

	@Column(name = "PIN_CONCEPTO", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPinConcepto() {
		return this.pinConcepto;
	}

	public void setPinConcepto(String pinConcepto) {
		this.pinConcepto = pinConcepto;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "policiesInvoice")
	public Set<Inconsistencies> getInconsistencieses() {
		return this.inconsistencieses;
	}

	public void setInconsistencieses(Set<Inconsistencies> inconsistencieses) {
		this.inconsistencieses = inconsistencieses;
	}

	@Column(name = "PIN_CARGADO", unique = false, nullable = false, insertable = true, updatable = true, length = 4)
	public Long getPinCargado() {
		return pinCargado;
	}

	public void setPinCargado(Long pinCargado) {
		this.pinCargado = pinCargado;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policiesInvoice")
	public Set<InvoiceHeader> getInvoiceHeaders() {
		return this.invoiceHeaders;
	}

	public void setInvoiceHeaders(Set<InvoiceHeader> invoiceHeaders) {
		this.invoiceHeaders = invoiceHeaders;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "policiesInvoice")
	public Set<PoliciesVehicles> getPoliciesVehicleses() {
		return policiesVehicleses;
	}

	public void setPoliciesVehicleses(Set<PoliciesVehicles> policiesVehicleses) {
		this.policiesVehicleses = policiesVehicleses;
	}

}