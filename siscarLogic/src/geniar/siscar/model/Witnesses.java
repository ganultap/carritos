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
 * Witnesses entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "WITNESSES", schema = "", uniqueConstraints = {})
public class Witnesses implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long wtnCodigo;
	private Cities cities;
	private Accidents accidents;
	private String wtnIdentificacion;
	private String wtnNombre;
	private String wtnDireccion;
	private String wtnTelefono;

	// Constructors

	/** default constructor */
	public Witnesses() {
	}

	/** full constructor */
	public Witnesses(Long wtnCodigo, Cities cities, Accidents accidents,
			String wtnIdentificacion, String wtnNombre, String wtnDireccion,
			String wtnTelefono) {
		this.wtnCodigo = wtnCodigo;
		this.cities = cities;
		this.accidents = accidents;
		this.wtnIdentificacion = wtnIdentificacion;
		this.wtnNombre = wtnNombre;
		this.wtnDireccion = wtnDireccion;
		this.wtnTelefono = wtnTelefono;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "SQ_WITNESSES_GEN", sequenceName = "SQ_WITNESSES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_WITNESSES_GEN")
	@Column(name = "WTN_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getWtnCodigo() {
		return this.wtnCodigo;
	}

	public void setWtnCodigo(Long wtnCodigo) {
		this.wtnCodigo = wtnCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTS_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Cities getCities() {
		return this.cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Accidents getAccidents() {
		return this.accidents;
	}

	public void setAccidents(Accidents accidents) {
		this.accidents = accidents;
	}

	@Column(name = "WTN_IDENTIFICACION", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getWtnIdentificacion() {
		return this.wtnIdentificacion;
	}

	public void setWtnIdentificacion(String wtnIdentificacion) {
		this.wtnIdentificacion = wtnIdentificacion;
	}

	@Column(name = "WTN_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getWtnNombre() {
		return this.wtnNombre;
	}

	public void setWtnNombre(String wtnNombre) {
		this.wtnNombre = wtnNombre;
	}

	@Column(name = "WTN_DIRECCION", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getWtnDireccion() {
		return this.wtnDireccion;
	}

	public void setWtnDireccion(String wtnDireccion) {
		this.wtnDireccion = wtnDireccion;
	}

	@Column(name = "WTN_TELEFONO", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getWtnTelefono() {
		return this.wtnTelefono;
	}

	public void setWtnTelefono(String wtnTelefono) {
		this.wtnTelefono = wtnTelefono;
	}

}