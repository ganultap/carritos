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
 * Severity entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SEVERITY", schema = "", uniqueConstraints = {})
public class Severity implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sevCodigo;
	private String sevNombre;
	private Set<Accidents> accidentses = new HashSet<Accidents>(0);

	// Constructors

	/** default constructor */
	public Severity() {
	}

	/** minimal constructor */
	public Severity(Long sevCodigo) {
		this.sevCodigo = sevCodigo;
	}

	/** full constructor */
	public Severity(Long sevCodigo, String sevNombre, Set<Accidents> accidentses) {
		this.sevCodigo = sevCodigo;
		this.sevNombre = sevNombre;
		this.accidentses = accidentses;
	}

	// Property accessors
	@Id
	@Column(name = "SEV_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getSevCodigo() {
		return this.sevCodigo;
	}

	public void setSevCodigo(Long sevCodigo) {
		this.sevCodigo = sevCodigo;
	}

	@Column(name = "SEV_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getSevNombre() {
		return this.sevNombre;
	}

	public void setSevNombre(String sevNombre) {
		this.sevNombre = sevNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "severity")
	public Set<Accidents> getAccidentses() {
		return this.accidentses;
	}

	public void setAccidentses(Set<Accidents> accidentses) {
		this.accidentses = accidentses;
	}

}