package geniar.siscar.model;

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
 * InvoiceHeader entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INVOICE_HEADER", schema = "", uniqueConstraints = {})
public class InvoiceHeader implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long inhCodigo;
	private InvoiceType invoiceType;
	private String PInvoiceNum;
	private String PTipoFactura;
	private Date PInvoiceDate;
	private String PNit;
	private Date PFechaRcvFactura;
	private String PMonedaFactura;
	private String PConvType;
	private Float PInvoiceAmount;
	private Date PConvDateDate;
	private Long PConvRate;
	private String PDescription;
	private String PUser;
	private String PSource;
	private PoliciesInvoice policiesInvoice;
	private Set<InvoiceDetail> invoiceDetails = new HashSet<InvoiceDetail>(0);

	// Constructors

	/** default constructor */
	public InvoiceHeader() {
	}

	/** minimal constructor */
	public InvoiceHeader(Long inhCodigo, InvoiceType invoiceType,
			String PInvoiceNum, String PTipoFactura, Date PInvoiceDate,
			String PNit, Date PFechaRcvFactura, String PMonedaFactura,
			String PConvType, Float PInvoiceAmount, Date PConvDateDate,
			Long PConvRate, String PDescription, String PUser, String PSource) {
		this.inhCodigo = inhCodigo;
		this.invoiceType = invoiceType;
		this.PInvoiceNum = PInvoiceNum;
		this.PTipoFactura = PTipoFactura;
		this.PInvoiceDate = PInvoiceDate;
		this.PNit = PNit;
		this.PFechaRcvFactura = PFechaRcvFactura;
		this.PMonedaFactura = PMonedaFactura;
		this.PConvType = PConvType;
		this.PInvoiceAmount = PInvoiceAmount;
		this.PConvDateDate = PConvDateDate;
		this.PConvRate = PConvRate;
		this.PDescription = PDescription;
		this.PUser = PUser;
		this.PSource = PSource;
	}

	/** full constructor */
	public InvoiceHeader(Long inhCodigo, InvoiceType invoiceType,
			String PInvoiceNum, String PTipoFactura, Date PInvoiceDate,
			String PNit, Date PFechaRcvFactura, String PMonedaFactura,
			String PConvType, Float PInvoiceAmount, Date PConvDateDate,
			Long PConvRate, String PDescription, String PUser, String PSource,
			Set<InvoiceDetail> invoiceDetails) {
		this.inhCodigo = inhCodigo;
		this.invoiceType = invoiceType;
		this.PInvoiceNum = PInvoiceNum;
		this.PTipoFactura = PTipoFactura;
		this.PInvoiceDate = PInvoiceDate;
		this.PNit = PNit;
		this.PFechaRcvFactura = PFechaRcvFactura;
		this.PMonedaFactura = PMonedaFactura;
		this.PConvType = PConvType;
		this.PInvoiceAmount = PInvoiceAmount;
		this.PConvDateDate = PConvDateDate;
		this.PConvRate = PConvRate;
		this.PDescription = PDescription;
		this.PUser = PUser;
		this.PSource = PSource;
		this.invoiceDetails = invoiceDetails;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "INVOICE_HEADER_GEN", sequenceName = "SQ_INVOICE_HEADER", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_HEADER_GEN")
	@Column(name = "INH_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getInhCodigo() {
		return this.inhCodigo;
	}

	public void setInhCodigo(Long inhCodigo) {
		this.inhCodigo = inhCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "INT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public InvoiceType getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(InvoiceType invoiceType) {
		this.invoiceType = invoiceType;
	}

	@Column(name = "P_INVOICE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPInvoiceNum() {
		return this.PInvoiceNum;
	}

	public void setPInvoiceNum(String PInvoiceNum) {
		this.PInvoiceNum = PInvoiceNum;
	}

	@Column(name = "P_TIPO_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPTipoFactura() {
		return this.PTipoFactura;
	}

	public void setPTipoFactura(String PTipoFactura) {
		this.PTipoFactura = PTipoFactura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "P_INVOICE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPInvoiceDate() {
		return this.PInvoiceDate;
	}

	public void setPInvoiceDate(Date PInvoiceDate) {
		this.PInvoiceDate = PInvoiceDate;
	}

	@Column(name = "P_NIT", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getPNit() {
		return this.PNit;
	}

	public void setPNit(String PNit) {
		this.PNit = PNit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "P_FECHA_RCV_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPFechaRcvFactura() {
		return this.PFechaRcvFactura;
	}

	public void setPFechaRcvFactura(Date PFechaRcvFactura) {
		this.PFechaRcvFactura = PFechaRcvFactura;
	}

	@Column(name = "P_MONEDA_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getPMonedaFactura() {
		return this.PMonedaFactura;
	}

	public void setPMonedaFactura(String PMonedaFactura) {
		this.PMonedaFactura = PMonedaFactura;
	}

	@Column(name = "P_CONV_TYPE", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getPConvType() {
		return this.PConvType;
	}

	public void setPConvType(String PConvType) {
		this.PConvType = PConvType;
	}

	@Column(name = "P_INVOICE_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Float getPInvoiceAmount() {
		return this.PInvoiceAmount;
	}

	public void setPInvoiceAmount(Float PInvoiceAmount) {
		this.PInvoiceAmount = PInvoiceAmount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "P_CONV_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPConvDateDate() {
		return this.PConvDateDate;
	}

	public void setPConvDateDate(Date PConvDateDate) {
		this.PConvDateDate = PConvDateDate;
	}

	@Column(name = "P_CONV_RATE", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPConvRate() {
		return this.PConvRate;
	}

	public void setPConvRate(Long PConvRate) {
		this.PConvRate = PConvRate;
	}

	@Column(name = "P_DESCRIPTION", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPDescription() {
		return this.PDescription;
	}

	public void setPDescription(String PDescription) {
		this.PDescription = PDescription;
	}

	@Column(name = "P_USER", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getPUser() {
		return this.PUser;
	}

	public void setPUser(String PUser) {
		this.PUser = PUser;
	}

	@Column(name = "P_SOURCE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPSource() {
		return this.PSource;
	}

	public void setPSource(String PSource) {
		this.PSource = PSource;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "invoiceHeader")
	public Set<InvoiceDetail> getInvoiceDetails() {
		return this.invoiceDetails;
	}

	public void setInvoiceDetails(Set<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PIN_ID")
	public PoliciesInvoice getPoliciesInvoice() {
		return this.policiesInvoice;
	}

	public void setPoliciesInvoice(PoliciesInvoice policiesInvoice) {
		this.policiesInvoice = policiesInvoice;
	}

}