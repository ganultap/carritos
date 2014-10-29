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
 * DocumentTwoType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOCUMENT_TWO_TYPE", schema = "")
public class DocumentTwoType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dttId;
	private String dttName;
	private String dttDescripcion;
	private Set<DocumentTwo> documentTwos = new HashSet<DocumentTwo>(0);

	// Constructors

	/** default constructor */
	public DocumentTwoType() {
	}

	/** minimal constructor */
	public DocumentTwoType(Long dttId, String dttName) {
		this.dttId = dttId;
		this.dttName = dttName;
	}

	/** full constructor */
	public DocumentTwoType(Long dttId, String dttName, String dttDescripcion,
			Set<DocumentTwo> documentTwos) {
		this.dttId = dttId;
		this.dttName = dttName;
		this.dttDescripcion = dttDescripcion;
		this.documentTwos = documentTwos;
	}

	// Property accessors
	@Id
	@Column(name = "DTT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getDttId() {
		return this.dttId;
	}

	public void setDttId(Long dttId) {
		this.dttId = dttId;
	}

	@Column(name = "DTT_NAME", nullable = false, length = 12)
	public String getDttName() {
		return this.dttName;
	}

	public void setDttName(String dttName) {
		this.dttName = dttName;
	}

	@Column(name = "DTT_DESCRIPCION", length = 120)
	public String getDttDescripcion() {
		return this.dttDescripcion;
	}

	public void setDttDescripcion(String dttDescripcion) {
		this.dttDescripcion = dttDescripcion;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "documentTwoType")
	public Set<DocumentTwo> getDocumentTwos() {
		return this.documentTwos;
	}

	public void setDocumentTwos(Set<DocumentTwo> documentTwos) {
		this.documentTwos = documentTwos;
	}

}