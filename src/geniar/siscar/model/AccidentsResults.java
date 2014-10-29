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
 * AccidentsResults entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCIDENTS_RESULTS", schema = "")
public class AccidentsResults implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long aclCodigo;
	private String aclNombre;
	private Set<Accidents> accidentses = new HashSet<Accidents>(0);

	// Constructors

	/** default constructor */
	public AccidentsResults() {
	}

	/** minimal constructor */
	public AccidentsResults(Long aclCodigo, String aclNombre) {
		this.aclCodigo = aclCodigo;
		this.aclNombre = aclNombre;
	}

	/** full constructor */
	public AccidentsResults(Long aclCodigo, String aclNombre,
			Set<Accidents> accidentses) {
		this.aclCodigo = aclCodigo;
		this.aclNombre = aclNombre;
		this.accidentses = accidentses;
	}

	// Property accessors
	@Id
	@Column(name = "ACL_CODIGO", unique = true, nullable = false, precision = 38, scale = 0)
	public Long getAclCodigo() {
		return this.aclCodigo;
	}

	public void setAclCodigo(Long aclCodigo) {
		this.aclCodigo = aclCodigo;
	}

	@Column(name = "ACL_NOMBRE", nullable = false, length = 30)
	public String getAclNombre() {
		return this.aclNombre;
	}

	public void setAclNombre(String aclNombre) {
		this.aclNombre = aclNombre;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accidentsResults")
	public Set<Accidents> getAccidentses() {
		return this.accidentses;
	}

	public void setAccidentses(Set<Accidents> accidentses) {
		this.accidentses = accidentses;
	}

}