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
 * ProofType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PROOF_TYPE", schema = "", uniqueConstraints = {})
public class ProofType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long prtCodigo;
	private String prtNombre;
	private Set<HeaderProof> headerProofs = new HashSet<HeaderProof>(0);

	// Constructors

	/** default constructor */
	public ProofType() {
	}

	/** minimal constructor */
	public ProofType(Long prtCodigo, String prtNombre) {
		this.prtCodigo = prtCodigo;
		this.prtNombre = prtNombre;
	}

	/** full constructor */
	public ProofType(Long prtCodigo, String prtNombre, Set<HeaderProof> headerProofs) {
		this.prtCodigo = prtCodigo;
		this.prtNombre = prtNombre;
		this.headerProofs = headerProofs;
	}

	// Property accessors
	@Id
	@Column(name = "PRT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPrtCodigo() {
		return this.prtCodigo;
	}

	public void setPrtCodigo(Long prtCodigo) {
		this.prtCodigo = prtCodigo;
	}

	@Column(name = "PRT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPrtNombre() {
		return this.prtNombre;
	}

	public void setPrtNombre(String prtNombre) {
		this.prtNombre = prtNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "proofType")
	public Set<HeaderProof> getHeaderProofs() {
		return this.headerProofs;
	}

	public void setHeaderProofs(Set<HeaderProof> headerProofs) {
		this.headerProofs = headerProofs;
	}

}