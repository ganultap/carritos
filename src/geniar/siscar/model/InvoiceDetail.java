package geniar.siscar.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * InvoiceDetail entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INVOICE_DETAIL", schema = "", uniqueConstraints = {})
public class InvoiceDetail implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long indCodigo;
	private InvoiceHeader invoiceHeader;
	private Long PInvoiceId;
	private Long PLineNum;
	private String PInvoiceNum;
	private Date PInvoiceDate;
	private Long PInvoiceAmount;
	private String PDescription;
	private Long PUserId;
	private String PPlacaVeh;
	private String PCompany;
	private String PAccount;
	private String PCcenter;
	private String PAuxiliary;
	private Long POrgId;
	private Long PLocation;
	private String PNit;

	// Constructors

	/** default constructor */
	public InvoiceDetail() {
	}

	/** full constructor */
	public InvoiceDetail(Long indCodigo, InvoiceHeader invoiceHeader,
			Long PInvoiceId, Long PLineNum, String PInvoiceNum,
			Date PInvoiceDate, Long PInvoiceAmount, String PDescription,
			Long PUserId, String PPlacaVeh, String PCompany, String PAccount,
			String PCcenter, String PAuxiliary, Long POrgId, Long PLocation, String PNit) {
		this.indCodigo = indCodigo;
		this.invoiceHeader = invoiceHeader;
		this.PInvoiceId = PInvoiceId;
		this.PLineNum = PLineNum;
		this.PInvoiceNum = PInvoiceNum;
		this.PInvoiceDate = PInvoiceDate;
		this.PInvoiceAmount = PInvoiceAmount;
		this.PDescription = PDescription;
		this.PUserId = PUserId;
		this.PPlacaVeh = PPlacaVeh;
		this.PCompany = PCompany;
		this.PAccount = PAccount;
		this.PCcenter = PCcenter;
		this.PAuxiliary = PAuxiliary;
		this.POrgId = POrgId;
		this.PLocation = PLocation;
		this.PNit = PNit;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "INVOICE_DETAIL_GEN", sequenceName = "SQ_INVOICE_DETAIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_DETAIL_GEN")
	@Column(name = "IND_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getIndCodigo() {
		return this.indCodigo;
	}

	public void setIndCodigo(Long indCodigo) {
		this.indCodigo = indCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "INH_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public InvoiceHeader getInvoiceHeader() {
		return this.invoiceHeader;
	}

	public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
		this.invoiceHeader = invoiceHeader;
	}

	@Column(name = "P_INVOICE_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPInvoiceId() {
		return this.PInvoiceId;
	}

	public void setPInvoiceId(Long PInvoiceId) {
		this.PInvoiceId = PInvoiceId;
	}

	@Column(name = "P_LINE_NUM", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPLineNum() {
		return this.PLineNum;
	}

	public void setPLineNum(Long PLineNum) {
		this.PLineNum = PLineNum;
	}

	@Column(name = "P_INVOICE_NUM", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPInvoiceNum() {
		return this.PInvoiceNum;
	}

	public void setPInvoiceNum(String PInvoiceNum) {
		this.PInvoiceNum = PInvoiceNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "P_INVOICE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPInvoiceDate() {
		return this.PInvoiceDate;
	}

	public void setPInvoiceDate(Date PInvoiceDate) {
		this.PInvoiceDate = PInvoiceDate;
	}

	@Column(name = "P_INVOICE_AMOUNT", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPInvoiceAmount() {
		return this.PInvoiceAmount;
	}

	public void setPInvoiceAmount(Long PInvoiceAmount) {
		this.PInvoiceAmount = PInvoiceAmount;
	}

	@Column(name = "P_DESCRIPTION", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPDescription() {
		return this.PDescription;
	}

	public void setPDescription(String PDescription) {
		this.PDescription = PDescription;
	}

	@Column(name = "P_USER_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPUserId() {
		return this.PUserId;
	}

	public void setPUserId(Long PUserId) {
		this.PUserId = PUserId;
	}

	@Column(name = "P_PLACA_VEH", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPPlacaVeh() {
		return this.PPlacaVeh;
	}

	public void setPPlacaVeh(String PPlacaVeh) {
		this.PPlacaVeh = PPlacaVeh;
	}

	@Column(name = "P_COMPANY", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getPCompany() {
		return this.PCompany;
	}

	public void setPCompany(String PCompany) {
		this.PCompany = PCompany;
	}

	@Column(name = "P_ACCOUNT", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getPAccount() {
		return this.PAccount;
	}

	public void setPAccount(String PAccount) {
		this.PAccount = PAccount;
	}

	@Column(name = "P_CCENTER", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getPCcenter() {
		return this.PCcenter;
	}

	public void setPCcenter(String PCcenter) {
		this.PCcenter = PCcenter;
	}

	@Column(name = "P_AUXILIARY", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getPAuxiliary() {
		return this.PAuxiliary;
	}

	public void setPAuxiliary(String PAuxiliary) {
		this.PAuxiliary = PAuxiliary;
	}

	@Column(name = "P_ORG_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPOrgId() {
		return this.POrgId;
	}

	public void setPOrgId(Long POrgId) {
		this.POrgId = POrgId;
	}

	@Column(name = "P_LOCATION", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPLocation() {
		return this.PLocation;
	}

	public void setPLocation(Long PLocation) {
		this.PLocation = PLocation;
	}

	@Column(name = "P_NIT", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPNit() {
		return PNit;
	}

	public void setPNit(String nit) {
		PNit = nit;
	}
}