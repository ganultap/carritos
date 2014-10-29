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
 * Responsibility entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RESPONSIBILITY", schema = "", uniqueConstraints = {})
public class Responsibility implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long resCodigo;
	private String resNombre;
	private Set<Accidents> accidentses = new HashSet<Accidents>(0);

	// Constructors

	/** default constructor */
	public Responsibility() {
	}

	/** minimal constructor */
	public Responsibility(Long resCodigo) {
		this.resCodigo = resCodigo;
	}

	/** full constructor */
	public Responsibility(Long resCodigo, String resNombre, Set<Accidents> accidentses) {
		this.resCodigo = resCodigo;
		this.resNombre = resNombre;
		this.accidentses = accidentses;
	}

	// Property accessors
	@Id
	@Column(name = "RES_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getResCodigo() {
		return this.resCodigo;
	}

	public void setResCodigo(Long resCodigo) {
		this.resCodigo = resCodigo;
	}

	@Column(name = "RES_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getResNombre() {
		return this.resNombre;
	}

	public void setResNombre(String resNombre) {
		this.resNombre = resNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "responsibility")
	public Set<Accidents> getAccidentses() {
		return this.accidentses;
	}

	public void setAccidentses(Set<Accidents> accidentses) {
		this.accidentses = accidentses;
	}

}