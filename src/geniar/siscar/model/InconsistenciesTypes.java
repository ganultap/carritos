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
 * InconsistenciesTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INCONSISTENCIES_TYPES", schema = "")
public class InconsistenciesTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ictId;
	private String ictNombre;
	private Set<Inconsistencies> inconsistencieses = new HashSet<Inconsistencies>(
			0);

	// Constructors

	/** default constructor */
	public InconsistenciesTypes() {
	}

	/** minimal constructor */
	public InconsistenciesTypes(Long ictId, String ictNombre) {
		this.ictId = ictId;
		this.ictNombre = ictNombre;
	}

	/** full constructor */
	public InconsistenciesTypes(Long ictId, String ictNombre,
			Set<Inconsistencies> inconsistencieses) {
		this.ictId = ictId;
		this.ictNombre = ictNombre;
		this.inconsistencieses = inconsistencieses;
	}

	// Property accessors
	@Id
	@Column(name = "ICT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getIctId() {
		return this.ictId;
	}

	public void setIctId(Long ictId) {
		this.ictId = ictId;
	}

	@Column(name = "ICT_NOMBRE", nullable = false, length = 100)
	public String getIctNombre() {
		return this.ictNombre;
	}

	public void setIctNombre(String ictNombre) {
		this.ictNombre = ictNombre;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "inconsistenciesTypes")
	public Set<Inconsistencies> getInconsistencieses() {
		return this.inconsistencieses;
	}

	public void setInconsistencieses(Set<Inconsistencies> inconsistencieses) {
		this.inconsistencieses = inconsistencieses;
	}

}