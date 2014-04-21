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
 * RequestsStates entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUESTS_STATES", uniqueConstraints = {})
public class RequestsStates implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rqtCodigo;
	private String rqtNombre;
	private Set<Requests> requestses = new HashSet<Requests>(0);

	// Constructors

	/** default constructor */
	public RequestsStates() {
	}

	/** minimal constructor */
	public RequestsStates(Long rqtCodigo, String rqtNombre) {
		this.rqtCodigo = rqtCodigo;
		this.rqtNombre = rqtNombre;
	}

	/** full constructor */
	public RequestsStates(Long rqtCodigo, String rqtNombre, Set<Requests> requestses) {
		this.rqtCodigo = rqtCodigo;
		this.rqtNombre = rqtNombre;
		this.requestses = requestses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="REQUESTS_STATES_GEN", sequenceName="SQ_REQUESTS_STATES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REQUESTS_STATES_GEN")
	@Column(name = "RQT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRqtCodigo() {
		return this.rqtCodigo;
	}

	public void setRqtCodigo(Long rqtCodigo) {
		this.rqtCodigo = rqtCodigo;
	}

	@Column(name = "RQT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getRqtNombre() {
		return this.rqtNombre;
	}

	public void setRqtNombre(String rqtNombre) {
		this.rqtNombre = rqtNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "requestsStates")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

}