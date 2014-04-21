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
 * DocumentOneType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOCUMENT_ONE_TYPE", schema = "")
public class DocumentOneType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dotId;
	private String dotName;
	private String dotDescripcion;
	private Set<DocumentOne> documentOnes = new HashSet<DocumentOne>(0);

	// Constructors

	/** default constructor */
	public DocumentOneType() {
	}

	/** minimal constructor */
	public DocumentOneType(Long dotId, String dotName) {
		this.dotId = dotId;
		this.dotName = dotName;
	}

	/** full constructor */
	public DocumentOneType(Long dotId, String dotName, String dotDescripcion,
			Set<DocumentOne> documentOnes) {
		this.dotId = dotId;
		this.dotName = dotName;
		this.dotDescripcion = dotDescripcion;
		this.documentOnes = documentOnes;
	}

	// Property accessors
	@Id
	@Column(name = "DOT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getDotId() {
		return this.dotId;
	}

	public void setDotId(Long dotId) {
		this.dotId = dotId;
	}

	@Column(name = "DOT_NAME", nullable = false, length = 12)
	public String getDotName() {
		return this.dotName;
	}

	public void setDotName(String dotName) {
		this.dotName = dotName;
	}

	@Column(name = "DOT_DESCRIPCION", length = 120)
	public String getDotDescripcion() {
		return this.dotDescripcion;
	}

	public void setDotDescripcion(String dotDescripcion) {
		this.dotDescripcion = dotDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentOneType")
	public Set<DocumentOne> getDocumentOnes() {
		return this.documentOnes;
	}

	public void setDocumentOnes(Set<DocumentOne> documentOnes) {
		this.documentOnes = documentOnes;
	}

}