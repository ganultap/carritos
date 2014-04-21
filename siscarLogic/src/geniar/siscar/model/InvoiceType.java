package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * InvoiceType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INVOICE_TYPE", schema = "", uniqueConstraints = {})
public class InvoiceType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long intCodigo;
	private String intNombre;
	private Set<InvoiceHeader> invoiceHeaders = new HashSet<InvoiceHeader>(0);

	// Constructors

	/** default constructor */
	public InvoiceType() {
	}

	/** minimal constructor */
	public InvoiceType(Long intCodigo, String intNombre) {
		this.intCodigo = intCodigo;
		this.intNombre = intNombre;
	}

	/** full constructor */
	public InvoiceType(Long intCodigo, String intNombre,
			Set<InvoiceHeader> invoiceHeaders) {
		this.intCodigo = intCodigo;
		this.intNombre = intNombre;
		this.invoiceHeaders = invoiceHeaders;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "INVOICE_TYPE_GEN", sequenceName = "SQ_INVOICE_TYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOICE_TYPE_GEN")
	@Column(name = "INT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getIntCodigo() {
		return this.intCodigo;
	}

	public void setIntCodigo(Long intCodigo) {
		this.intCodigo = intCodigo;
	}

	@Column(name = "INT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getIntNombre() {
		return this.intNombre;
	}

	public void setIntNombre(String intNombre) {
		this.intNombre = intNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "invoiceType")
	public Set<InvoiceHeader> getInvoiceHeaders() {
		return this.invoiceHeaders;
	}

	public void setInvoiceHeaders(Set<InvoiceHeader> invoiceHeaders) {
		this.invoiceHeaders = invoiceHeaders;
	}

}