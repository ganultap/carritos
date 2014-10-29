package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * AccidentsFiles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCIDENTS_FILES", schema = "", uniqueConstraints = {})
public class AccidentsFiles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long acfId;
	private Accidents accidents;
	private String acfRuta;
	private String acfDescripcion;
	private String acfNombre;

	// Constructors

	/** default constructor */
	public AccidentsFiles() {
	}

	/** minimal constructor */
	public AccidentsFiles(Long acfId) {
		this.acfId = acfId;
	}

	/** full constructor */
	public AccidentsFiles(Long acfId, Accidents accidents, String acfRuta,
			String acfDescripcion, String acfNombre) {
		this.acfId = acfId;
		this.accidents = accidents;
		this.acfRuta = acfRuta;
		this.acfDescripcion = acfDescripcion;
		this.acfNombre = acfNombre;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "ACCIDENTS_FILES_GEN", sequenceName = "SQ_ACCIDENTS_FILES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCIDENTS_FILES_GEN")
	@Column(name = "ACF_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAcfId() {
		return this.acfId;
	}

	public void setAcfId(Long acfId) {
		this.acfId = acfId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Accidents getAccidents() {
		return this.accidents;
	}

	public void setAccidents(Accidents accidents) {
		this.accidents = accidents;
	}

	@Column(name = "ACF_RUTA", unique = false, nullable = true, insertable = true, updatable = true, length = 300)
	public String getAcfRuta() {
		return this.acfRuta;
	}

	public void setAcfRuta(String acfRuta) {
		this.acfRuta = acfRuta;
	}

	@Column(name = "ACF_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getAcfDescripcion() {
		return this.acfDescripcion;
	}

	public void setAcfDescripcion(String acfDescripcion) {
		this.acfDescripcion = acfDescripcion;
	}

	@Column(name = "ACF_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getAcfNombre() {
		return this.acfNombre;
	}

	public void setAcfNombre(String acfNombre) {
		this.acfNombre = acfNombre;
	}

}