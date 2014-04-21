package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * DocumentOne entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DOCUMENT_ONE", schema = "")
public class DocumentOne implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dcoId;
	private DocumentOneType documentOneType;
	private String dcoNumero;

	// Constructors

	/** default constructor */
	public DocumentOne() {
	}

	/** full constructor */
	public DocumentOne(Long dcoId, DocumentOneType documentOneType,
			String dcoNumero) {
		this.dcoId = dcoId;
		this.documentOneType = documentOneType;
		this.dcoNumero = dcoNumero;
	}

	// Property accessors
	@Id
	@Column(name = "DCO_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getDcoId() {
		return this.dcoId;
	}

	public void setDcoId(Long dcoId) {
		this.dcoId = dcoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DOT_ID", nullable = false)
	public DocumentOneType getDocumentOneType() {
		return this.documentOneType;
	}

	public void setDocumentOneType(DocumentOneType documentOneType) {
		this.documentOneType = documentOneType;
	}

	@Column(name = "DCO_NUMERO", nullable = false, length = 20)
	public String getDcoNumero() {
		return this.dcoNumero;
	}

	public void setDcoNumero(String dcoNumero) {
		this.dcoNumero = dcoNumero;
	}

}