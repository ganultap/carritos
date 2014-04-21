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
 * ActualOthersApplications entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACTUAL_OTHERS_APPLICATIONS", schema = "", uniqueConstraints = {})
public class ActualOthersApplications implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long aoaCodigo;
	private HeaderProof headerProof;
	private Date aoaFechaAnul;
	private String aoaState;
	private Long PSob;
	private Date PAccdate;
	private String PCurr;
	private String PUser;
	private String PCategory;
	private String PSource;
	private Date PConvDate;
	private String PConvType;
	private Long PConvRate;
	private String PCompany;
	private String PAccount;
	private String PCcenter;
	private String PAuxiliary;
	private Float PEntDr;
	private Float PEntCr;
	private String PBname;
	private String PDescription;
	private String PNit;
	private String PAttribute2;
	private String PAttribute5;
	private String PAttribute6;
	private String PAttribute7;
	private String PAttribute8;
	private String PAttribute9;
	private String PAttribute10;

	// Constructors

	/** default constructor */
	public ActualOthersApplications() {
	}

	/** minimal constructor */
	public ActualOthersApplications(Long aoaCodigo, HeaderProof headerProof,
			Long PSob, Date PAccdate, String PCurr, String PUser,
			String PCategory, String PSource, Date PConvDate, String PConvType,
			Long PConvRate, String PCompany, String PAccount, String PCcenter,
			String PAuxiliary, String PDescription, String PAttribute5,
			String PAttribute6) {
		this.aoaCodigo = aoaCodigo;
		this.headerProof = headerProof;
		this.PSob = PSob;
		this.PAccdate = PAccdate;
		this.PCurr = PCurr;
		this.PUser = PUser;
		this.PCategory = PCategory;
		this.PSource = PSource;
		this.PConvDate = PConvDate;
		this.PConvType = PConvType;
		this.PConvRate = PConvRate;
		this.PCompany = PCompany;
		this.PAccount = PAccount;
		this.PCcenter = PCcenter;
		this.PAuxiliary = PAuxiliary;
		this.PDescription = PDescription;
		this.PAttribute5 = PAttribute5;
		this.PAttribute6 = PAttribute6;
	}

	/** full constructor */
	public ActualOthersApplications(Long aoaCodigo, HeaderProof headerProof,
			Date aoaFechaAnul, String aoaState, Long PSob, Date PAccdate,
			String PCurr, String PUser, String PCategory, String PSource,
			Date PConvDate, String PConvType, Long PConvRate, String PCompany,
			String PAccount, String PCcenter, String PAuxiliary, Float PEntDr,
			Float PEntCr, String PBname, String PDescription, String PNit,
			String PAttribute2, String PAttribute5, String PAttribute6,
			String PAttribute7, String PAttribute8, String PAttribute9,
			String PAttribute10) {
		this.aoaCodigo = aoaCodigo;
		this.headerProof = headerProof;
		this.aoaFechaAnul = aoaFechaAnul;
		this.aoaState = aoaState;
		this.PSob = PSob;
		this.PAccdate = PAccdate;
		this.PCurr = PCurr;
		this.PUser = PUser;
		this.PCategory = PCategory;
		this.PSource = PSource;
		this.PConvDate = PConvDate;
		this.PConvType = PConvType;
		this.PConvRate = PConvRate;
		this.PCompany = PCompany;
		this.PAccount = PAccount;
		this.PCcenter = PCcenter;
		this.PAuxiliary = PAuxiliary;
		this.PEntDr = PEntDr;
		this.PEntCr = PEntCr;
		this.PBname = PBname;
		this.PDescription = PDescription;
		this.PNit = PNit;
		this.PAttribute2 = PAttribute2;
		this.PAttribute5 = PAttribute5;
		this.PAttribute6 = PAttribute6;
		this.PAttribute7 = PAttribute7;
		this.PAttribute8 = PAttribute8;
		this.PAttribute9 = PAttribute9;
		this.PAttribute10 = PAttribute10;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "ACTUAL_OTHERS_APPLICATIONS_GEN", sequenceName = "SQ_ACTUAL_OTHERS_APPLICATIONS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACTUAL_OTHERS_APPLICATIONS_GEN")
	@Column(name = "AOA_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getAoaCodigo() {
		return this.aoaCodigo;
	}

	public void setAoaCodigo(Long aoaCodigo) {
		this.aoaCodigo = aoaCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "HEP_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public HeaderProof getHeaderProof() {
		return this.headerProof;
	}

	public void setHeaderProof(HeaderProof headerProof) {
		this.headerProof = headerProof;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AOA_FECHA_ANUL", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getAoaFechaAnul() {
		return this.aoaFechaAnul;
	}

	public void setAoaFechaAnul(Date aoaFechaAnul) {
		this.aoaFechaAnul = aoaFechaAnul;
	}

	@Column(name = "AOA_STATE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getAoaState() {
		return this.aoaState;
	}

	public void setAoaState(String aoaState) {
		this.aoaState = aoaState;
	}

	@Column(name = "P_SOB", unique = false, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getPSob() {
		return this.PSob;
	}

	public void setPSob(Long PSob) {
		this.PSob = PSob;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "P_ACCDATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPAccdate() {
		return this.PAccdate;
	}

	public void setPAccdate(Date PAccdate) {
		this.PAccdate = PAccdate;
	}

	@Column(name = "P_CURR", unique = false, nullable = false, insertable = true, updatable = true, length = 15)
	public String getPCurr() {
		return this.PCurr;
	}

	public void setPCurr(String PCurr) {
		this.PCurr = PCurr;
	}

	@Column(name = "P_USER", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPUser() {
		return this.PUser;
	}

	public void setPUser(String PUser) {
		this.PUser = PUser;
	}

	@Column(name = "P_CATEGORY", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getPCategory() {
		return this.PCategory;
	}

	public void setPCategory(String PCategory) {
		this.PCategory = PCategory;
	}

	@Column(name = "P_SOURCE", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getPSource() {
		return this.PSource;
	}

	public void setPSource(String PSource) {
		this.PSource = PSource;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "P_CONV_DATE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getPConvDate() {
		return this.PConvDate;
	}

	public void setPConvDate(Date PConvDate) {
		this.PConvDate = PConvDate;
	}

	@Column(name = "P_CONV_TYPE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getPConvType() {
		return this.PConvType;
	}

	public void setPConvType(String PConvType) {
		this.PConvType = PConvType;
	}

	@Column(name = "P_CONV_RATE", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPConvRate() {
		return this.PConvRate;
	}

	public void setPConvRate(Long PConvRate) {
		this.PConvRate = PConvRate;
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

	@Column(name = "P_CCENTER", unique = false, nullable = true, insertable = true, updatable = true, length = 25)
	public String getPCcenter() {
		return this.PCcenter;
	}

	public void setPCcenter(String PCcenter) {
		this.PCcenter = PCcenter;
	}

	@Column(name = "P_AUXILIARY", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getPAuxiliary() {
		return this.PAuxiliary;
	}

	public void setPAuxiliary(String PAuxiliary) {
		this.PAuxiliary = PAuxiliary;
	}

	@Column(name = "P_ENT_DR", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPEntDr() {
		return this.PEntDr;
	}

	public void setPEntDr(Float PEntDr) {
		this.PEntDr = PEntDr;
	}

	@Column(name = "P_ENT_CR", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPEntCr() {
		return this.PEntCr;
	}

	public void setPEntCr(Float PEntCr) {
		this.PEntCr = PEntCr;
	}

	@Column(name = "P_BNAME", unique = false, nullable = true, insertable = true, updatable = true, length = 255)
	public String getPBname() {
		return this.PBname;
	}

	public void setPBname(String PBname) {
		this.PBname = PBname;
	}

	@Column(name = "P_DESCRIPTION", unique = false, nullable = false, insertable = true, updatable = true, length = 240)
	public String getPDescription() {
		return this.PDescription;
	}

	public void setPDescription(String PDescription) {
		this.PDescription = PDescription;
	}

	@Column(name = "P_NIT", unique = false, nullable = true, insertable = true, updatable = true, length = 14)
	public String getPNit() {
		return this.PNit;
	}

	public void setPNit(String PNit) {
		this.PNit = PNit;
	}

	@Column(name = "P_ATTRIBUTE2", unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	public String getPAttribute2() {
		return this.PAttribute2;
	}

	public void setPAttribute2(String PAttribute2) {
		this.PAttribute2 = PAttribute2;
	}

	@Column(name = "P_ATTRIBUTE5", unique = false, nullable = false, insertable = true, updatable = true, length = 150)
	public String getPAttribute5() {
		return this.PAttribute5;
	}

	public void setPAttribute5(String PAttribute5) {
		this.PAttribute5 = PAttribute5;
	}

	@Column(name = "P_ATTRIBUTE6", unique = false, nullable = false, insertable = true, updatable = true, length = 150)
	public String getPAttribute6() {
		return this.PAttribute6;
	}

	public void setPAttribute6(String PAttribute6) {
		this.PAttribute6 = PAttribute6;
	}

	@Column(name = "P_ATTRIBUTE7", unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	public String getPAttribute7() {
		return this.PAttribute7;
	}

	public void setPAttribute7(String PAttribute7) {
		this.PAttribute7 = PAttribute7;
	}

	@Column(name = "P_ATTRIBUTE8", unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	public String getPAttribute8() {
		return this.PAttribute8;
	}

	public void setPAttribute8(String PAttribute8) {
		this.PAttribute8 = PAttribute8;
	}

	@Column(name = "P_ATTRIBUTE9", unique = false, nullable = true, insertable = true, updatable = true, length = 150)
	public String getPAttribute9() {
		return this.PAttribute9;
	}

	public void setPAttribute9(String PAttribute9) {
		this.PAttribute9 = PAttribute9;
	}

	@Column(name = "P_ATTRIBUTE10", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getPAttribute10() {
		return this.PAttribute10;
	}

	public void setPAttribute10(String PAttribute10) {
		this.PAttribute10 = PAttribute10;
	}

}