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
 * RequestsClasses entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUESTS_CLASSES", uniqueConstraints = {})
public class RequestsClasses implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rqcCodigo;
	private String rqcNombre;
	private Set<Requests> requestses = new HashSet<Requests>(0);

	// Constructors

	/** default constructor */
	public RequestsClasses() {
	}

	/** minimal constructor */
	public RequestsClasses(Long rqcCodigo, String rqcNombre) {
		this.rqcCodigo = rqcCodigo;
		this.rqcNombre = rqcNombre;
	}

	/** full constructor */
	public RequestsClasses(Long rqcCodigo, String rqcNombre, Set<Requests> requestses) {
		this.rqcCodigo = rqcCodigo;
		this.rqcNombre = rqcNombre;
		this.requestses = requestses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="REQUESTS_CLASSES_GEN", sequenceName="SQ_REQUESTS_CLASSES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUESTS_CLASSES_GEN")
	@Column(name = "RQC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRqcCodigo() {
		return this.rqcCodigo;
	}

	public void setRqcCodigo(Long rqcCodigo) {
		this.rqcCodigo = rqcCodigo;
	}

	@Column(name = "RQC_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getRqcNombre() {
		return this.rqcNombre;
	}

	public void setRqcNombre(String rqcNombre) {
		this.rqcNombre = rqcNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "requestsClasses")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

}