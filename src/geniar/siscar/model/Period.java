package geniar.siscar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Period entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERIOD", schema = "", uniqueConstraints = {})
public class Period implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long perId;
	private String perNombre;
	private Date perFechaIni;
	private Date perFechaFin;
	private String perObservacion;
	private NoveltyTypes noveltyTypes;
	private Set<HeaderProof> headerProofs = new HashSet<HeaderProof>(0);
	private Set<FlatFile> flatFiles = new HashSet<FlatFile>(0);

	// Constructors

	/** default constructor */
	public Period() {
	}

	/** minimal constructor */
	public Period(Long perId, String perNombre, Date perFechaIni,
			Date perFechaFin, NoveltyTypes noveltyTypes) {
		this.perId = perId;
		this.perNombre = perNombre;
		this.perFechaIni = perFechaIni;
		this.perFechaFin = perFechaFin;
		this.noveltyTypes = noveltyTypes;
	}

	/** full constructor */
	public Period(Long perId, String perNombre, Date perFechaIni,
			Date perFechaFin, String perObservacion,
			Set<HeaderProof> headerProofs, Set<FlatFile> flatFiles) {
		this.perId = perId;
		this.perNombre = perNombre;
		this.perFechaIni = perFechaIni;
		this.perFechaFin = perFechaFin;
		this.perObservacion = perObservacion;
		this.headerProofs = headerProofs;
		this.flatFiles = flatFiles;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PERIOD_GEN", sequenceName = "SQ_PERIODS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERIOD_GEN")
	@Column(name = "PER_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPerId() {
		return this.perId;
	}

	public void setPerId(Long perId) {
		this.perId = perId;
	}

	@Column(name = "PER_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPerNombre() {
		return this.perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PER_FECHA_INI", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPerFechaIni() {
		return this.perFechaIni;
	}

	public void setPerFechaIni(Date perFechaIni) {
		this.perFechaIni = perFechaIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PER_FECHA_FIN", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPerFechaFin() {
		return this.perFechaFin;
	}

	public void setPerFechaFin(Date perFechaFin) {
		this.perFechaFin = perFechaFin;
	}

	@Column(name = "PER_OBSERVACION", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getPerObservacion() {
		return this.perObservacion;
	}

	public void setPerObservacion(String perObservacion) {
		this.perObservacion = perObservacion;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "period")
	public Set<HeaderProof> getHeaderProofs() {
		return this.headerProofs;
	}

	public void setHeaderProofs(Set<HeaderProof> headerProofs) {
		this.headerProofs = headerProofs;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "period")
	public Set<FlatFile> getFlatFiles() {
		return this.flatFiles;
	}

	public void setFlatFiles(Set<FlatFile> flatFiles) {
		this.flatFiles = flatFiles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NT_ID", nullable = false)
	public NoveltyTypes getNoveltyTypes() {
		return this.noveltyTypes;
	}

	public void setNoveltyTypes(NoveltyTypes noveltyTypes) {
		this.noveltyTypes = noveltyTypes;
	}

}