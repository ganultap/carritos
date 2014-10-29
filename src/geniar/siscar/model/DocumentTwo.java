package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DocumentTwo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOCUMENT_TWO", schema = "")
public class DocumentTwo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dctId;
	private DocumentTwoType documentTwoType;
	private String dctNumero;

	// Constructors

	/** default constructor */
	public DocumentTwo() {
	}

	/** full constructor */
	public DocumentTwo(Long dctId, DocumentTwoType documentTwoType,
			String dctNumero) {
		this.dctId = dctId;
		this.documentTwoType = documentTwoType;
		this.dctNumero = dctNumero;
	}

	// Property accessors
	@Id
	@Column(name = "DCT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getDctId() {
		return this.dctId;
	}

	public void setDctId(Long dctId) {
		this.dctId = dctId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DTT_ID", nullable = false)
	public DocumentTwoType getDocumentTwoType() {
		return this.documentTwoType;
	}

	public void setDocumentTwoType(DocumentTwoType documentTwoType) {
		this.documentTwoType = documentTwoType;
	}

	@Column(name = "DCT_NUMERO", nullable = false, length = 20)
	public String getDctNumero() {
		return this.dctNumero;
	}

	public void setDctNumero(String dctNumero) {
		this.dctNumero = dctNumero;
	}

}