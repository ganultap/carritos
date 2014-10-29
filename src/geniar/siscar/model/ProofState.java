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
 * ProofState entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROOF_STATE", schema = "", uniqueConstraints = {})
public class ProofState implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long prsCodigo;
	private String prsNombre;
	private Set<HeaderProof> headerProofs = new HashSet<HeaderProof>(0);

	// Constructors

	/** default constructor */
	public ProofState() {
	}

	/** minimal constructor */
	public ProofState(Long prsCodigo, String prsNombre) {
		this.prsCodigo = prsCodigo;
		this.prsNombre = prsNombre;
	}

	/** full constructor */
	public ProofState(Long prsCodigo, String prsNombre,
			Set<HeaderProof> headerProofs) {
		this.prsCodigo = prsCodigo;
		this.prsNombre = prsNombre;
		this.headerProofs = headerProofs;
	}

	// Property accessors
	@Id
	@Column(name = "PRS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPrsCodigo() {
		return this.prsCodigo;
	}

	public void setPrsCodigo(Long prsCodigo) {
		this.prsCodigo = prsCodigo;
	}

	@Column(name = "PRS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPrsNombre() {
		return this.prsNombre;
	}

	public void setPrsNombre(String prsNombre) {
		this.prsNombre = prsNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "proofType")
	public Set<HeaderProof> getHeaderProofs() {
		return this.headerProofs;
	}

	public void setHeaderProofs(Set<HeaderProof> headerProofs) {
		this.headerProofs = headerProofs;
	}

}