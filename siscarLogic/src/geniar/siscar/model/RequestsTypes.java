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
 * RequestsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUESTS_TYPES", uniqueConstraints = {})
public class RequestsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rqyCodigo;
	private String rqyNombre;
	private Set<Requests> requestses = new HashSet<Requests>(0);

	// Constructors

	/** default constructor */
	public RequestsTypes() {
	}

	/** minimal constructor */
	public RequestsTypes(Long rqyCodigo, String rqyNombre) {
		this.rqyCodigo = rqyCodigo;
		this.rqyNombre = rqyNombre;
	}

	/** full constructor */
	public RequestsTypes(Long rqyCodigo, String rqyNombre, Set<Requests> requestses) {
		this.rqyCodigo = rqyCodigo;
		this.rqyNombre = rqyNombre;
		this.requestses = requestses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="REQUESTS_TYPES_GEN", sequenceName="SQ_REQUESTS_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUESTS_TYPES_GEN")
	@Column(name = "RQY_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRqyCodigo() {
		return this.rqyCodigo;
	}

	public void setRqyCodigo(Long rqyCodigo) {
		this.rqyCodigo = rqyCodigo;
	}

	@Column(name = "RQY_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getRqyNombre() {
		return this.rqyNombre;
	}

	public void setRqyNombre(String rqyNombre) {
		this.rqyNombre = rqyNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "requestsTypes")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

}