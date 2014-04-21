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
 * AccountingParameters entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCOUNTING_PARAMETERS", schema = "")
public class AccountingParameters implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long acpId;
	private Account account;
	private Attribute attribute;
	private Auxiliar auxiliar;
	private CostsCenters costsCenters;
	private MovementType movementType;
	private DocumentOne documentOne;
	private LegateesTypes legateesTypes;
	private TransactionType transactionType;
	private ChargeType chargeType;
	private ControlType controlType;
	private Line line;
	private Company company;
	private DocumentTwo documentTwo;
	private DescriptionType descriptionType;
	private Future future;
	private String accDescripcion;
	private Boolean acpState;
	private LocationsTypes locationsTypes;
	
	// Constructors

	/** default constructor */
	public AccountingParameters() {
	}

	/** minimal constructor */
	public AccountingParameters(Long acpId, Boolean acpState) {
		this.acpId = acpId;
		this.acpState = acpState;
	}

	/** full constructor */
	public AccountingParameters(Long acpId, Account account,
			Attribute attribute, Auxiliar auxiliar, CostsCenters costsCenters,
			MovementType movementType, DocumentOne documentOne,
			LegateesTypes legateesTypes, TransactionType transactionType,
			ChargeType chargeType, ControlType controlType, Line line,
			Company company, DocumentTwo documentTwo,
			DescriptionType descriptionType, Future future,
			String accDescripcion, Boolean acpState, LocationsTypes locationsTypes) {
		this.acpId = acpId;
		this.account = account;
		this.attribute = attribute;
		this.auxiliar = auxiliar;
		this.costsCenters = costsCenters;
		this.movementType = movementType;
		this.documentOne = documentOne;
		this.legateesTypes = legateesTypes;
		this.transactionType = transactionType;
		this.chargeType = chargeType;
		this.controlType = controlType;
		this.line = line;
		this.company = company;
		this.documentTwo = documentTwo;
		this.descriptionType = descriptionType;
		this.future = future;
		this.accDescripcion = accDescripcion;
		this.acpState = acpState;
		this.locationsTypes = locationsTypes;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="ACCOUNT_PAR_GEN", sequenceName="SQ_ACCOUNTING_PARAM", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_PAR_GEN")
	@Column(name = "ACP_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getAcpId() {
		return this.acpId;
	}

	public void setAcpId(Long acpId) {
		this.acpId = acpId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_ID")
	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATB_ID")
	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUX_ID")
	public Auxiliar getAuxiliar() {
		return this.auxiliar;
	}

	public void setAuxiliar(Auxiliar auxiliar) {
		this.auxiliar = auxiliar;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COC_CODIGO")
	public CostsCenters getCostsCenters() {
		return this.costsCenters;
	}

	public void setCostsCenters(CostsCenters costsCenters) {
		this.costsCenters = costsCenters;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MVM_ID")
	public MovementType getMovementType() {
		return this.movementType;
	}

	public void setMovementType(MovementType movementType) {
		this.movementType = movementType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DCO_ID")
	public DocumentOne getDocumentOne() {
		return this.documentOne;
	}

	public void setDocumentOne(DocumentOne documentOne) {
		this.documentOne = documentOne;
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
	@JoinColumn(name = "TST_ID")
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CGT_ID")
	public ChargeType getChargeType() {
		return this.chargeType;
	}
	
	public void setChargeType(ChargeType chargeType) {
		this.chargeType = chargeType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CTT_ID")
	public ControlType getControlType() {
		return this.controlType;
	}

	public void setControlType(ControlType controlType) {
		this.controlType = controlType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LIN_ID")
	public Line getLine() {
		return this.line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CMP_ID")
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DCT_ID")
	public DocumentTwo getDocumentTwo() {
		return this.documentTwo;
	}

	public void setDocumentTwo(DocumentTwo documentTwo) {
		this.documentTwo = documentTwo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DST_ID")
	public DescriptionType getDescriptionType() {
		return this.descriptionType;
	}

	public void setDescriptionType(DescriptionType descriptionType) {
		this.descriptionType = descriptionType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FRE_ID")
	public Future getFuture() {
		return this.future;
	}

	public void setFuture(Future future) {
		this.future = future;
	}

	@Column(name = "ACC_DESCRIPCION", length = 200)
	public String getAccDescripcion() {
		return this.accDescripcion;
	}

	public void setAccDescripcion(String accDescripcion) {
		this.accDescripcion = accDescripcion;
	}
	
	@Column(name = "ACP_STATE", nullable = false, precision = 1, scale = 0)
	public Boolean getAcpState() {
		return acpState;
	}

	public void setAcpState(Boolean acpState) {
		this.acpState = acpState;
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