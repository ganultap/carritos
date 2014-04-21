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

/**
 * HeaderProof entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HEADER_PROOF", uniqueConstraints = {})
public class HeaderProof implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long hepId;
	private CurrencyTypes currencyTypes;
	private ProofType proofType;
	private TransactionType transactionType;
	private ProofState proofState;
	private Period period;
	private String hepGroupId;
	private Date fechaAnul;
	private String userAnul;
	private Set<Prepaid> prepaids = new HashSet<Prepaid>(0);
	private Set<ServiceRegistry> serviceRegistries = new HashSet<ServiceRegistry>(
			0);
	private Set<ActualOthersApplications> actualOthersApplicationses = new HashSet<ActualOthersApplications>(
			0);
	private Set<VhaAoaApp> vhaAoaApps = new HashSet<VhaAoaApp>(0);

	// Constructors

	/** default constructor */
	public HeaderProof() {
	}

	/** minimal constructor */
	public HeaderProof(Long hepId, CurrencyTypes currencyTypes,
			ProofType proofType, TransactionType transactionType,
			ProofState proofState, Period period, String hepGroupId) {
		this.hepId = hepId;
		this.currencyTypes = currencyTypes;
		this.proofType = proofType;
		this.transactionType = transactionType;
		this.proofState = proofState;
		this.period = period;
		this.hepGroupId = hepGroupId;
	}

	/** full constructor */
	public HeaderProof(Long hepId, CurrencyTypes currencyTypes,
			ProofType proofType, TransactionType transactionType,
			ProofState proofState, Period period, String hepGroupId,
			Set<Prepaid> prepaids, Set<ServiceRegistry> serviceRegistries,
			Set<ActualOthersApplications> actualOthersApplicationses,
			Set<VhaAoaApp> vhaAoaApps) {
		this.hepId = hepId;
		this.currencyTypes = currencyTypes;
		this.proofType = proofType;
		this.transactionType = transactionType;
		this.proofState = proofState;
		this.period = period;
		this.hepGroupId = hepGroupId;
		this.prepaids = prepaids;
		this.serviceRegistries = serviceRegistries;
		this.actualOthersApplicationses = actualOthersApplicationses;
		this.vhaAoaApps = vhaAoaApps;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "HEADER_PROF_GEN", sequenceName = "SQ_HEADER_PROF", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HEADER_PROF_GEN")
	@Column(name = "HEP_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getHepId() {
		return this.hepId;
	}

	public void setHepId(Long hepId) {
		this.hepId = hepId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public CurrencyTypes getCurrencyTypes() {
		return this.currencyTypes;
	}

	public void setCurrencyTypes(CurrencyTypes currencyTypes) {
		this.currencyTypes = currencyTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public ProofType getProofType() {
		return this.proofType;
	}

	public void setProofType(ProofType proofType) {
		this.proofType = proofType;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TST_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public TransactionType getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public ProofState getProofState() {
		return this.proofState;
	}

	public void setProofState(ProofState proofState) {
		this.proofState = proofState;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PER_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	@Column(name = "HEP_GROUP_ID", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getHepGroupId() {
		return this.hepGroupId;
	}

	public void setHepGroupId(String hepGroupId) {
		this.hepGroupId = hepGroupId;
	}

	@Column(name = "FECHA_ANUL", unique = false, nullable = true, insertable = true, updatable = true)
	public Date getFechaAnul() {
		return fechaAnul;
	}

	public void setFechaAnul(Date fechaAnul) {
		this.fechaAnul = fechaAnul;
	}

	@Column(name = "USER_ANUL", unique = false, nullable = true, insertable = true, updatable = true)
	public String getUserAnul() {
		return userAnul;
	}

	public void setUserAnul(String userAnul) {
		this.userAnul = userAnul;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "headerProof")
	public Set<Prepaid> getPrepaids() {
		return this.prepaids;
	}

	public void setPrepaids(Set<Prepaid> prepaids) {
		this.prepaids = prepaids;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "headerProof")
	public Set<ServiceRegistry> getServiceRegistries() {
		return this.serviceRegistries;
	}

	public void setServiceRegistries(Set<ServiceRegistry> serviceRegistries) {
		this.serviceRegistries = serviceRegistries;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "headerProof")
	public Set<ActualOthersApplications> getActualOthersApplicationses() {
		return this.actualOthersApplicationses;
	}

	public void setActualOthersApplicationses(
			Set<ActualOthersApplications> actualOthersApplicationses) {
		this.actualOthersApplicationses = actualOthersApplicationses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "headerProof")
	public Set<VhaAoaApp> getVhaAoaApps() {
		return this.vhaAoaApps;
	}

	public void setVhaAoaApps(Set<VhaAoaApp> vhaAoaApps) {
		this.vhaAoaApps = vhaAoaApps;
	}

}