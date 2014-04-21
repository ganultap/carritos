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
 * Zones entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ZONES", uniqueConstraints = {})
public class Zones implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long znsId;
	private String znsNombre;
	private String znsDescripcion;
	private String znsKilometraje;
	private Set<Requests> requestses = new HashSet<Requests>(0);
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);

	// Constructors

	/** default constructor */
	public Zones() {
	}

	/** minimal constructor */
	public Zones(Long znsId, String znsDescripcion, String znsKilometraje) {
		this.znsId = znsId;
		this.znsDescripcion = znsDescripcion;
		this.znsKilometraje = znsKilometraje;
	}

	/** full constructor */
	public Zones(Long znsId, String znsNombre, String znsDescripcion, String znsKilometraje, Set<Requests> requestses,
			Set<Tariffs> tariffses) {
		this.znsId = znsId;
		this.znsNombre = znsNombre;
		this.znsDescripcion = znsDescripcion;
		this.znsKilometraje = znsKilometraje;
		this.requestses = requestses;
		this.tariffses = tariffses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="ZONES_GEN", sequenceName="SQ_ZONES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ZONES_GEN")
	@Column(name = "ZNS_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getZnsId() {
		return this.znsId;
	}

	public void setZnsId(Long znsId) {
		this.znsId = znsId;
	}

	@Column(name = "ZNS_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getZnsNombre() {
		return this.znsNombre;
	}

	public void setZnsNombre(String znsNombre) {
		this.znsNombre = znsNombre;
	}

	@Column(name = "ZNS_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getZnsDescripcion() {
		return this.znsDescripcion;
	}

	public void setZnsDescripcion(String znsDescripcion) {
		this.znsDescripcion = znsDescripcion;
	}

	@Column(name = "ZNS_KILOMETRAJE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getZnsKilometraje() {
		return this.znsKilometraje;
	}

	public void setZnsKilometraje(String znsKilometraje) {
		this.znsKilometraje = znsKilometraje;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "zones")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "zones")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

}