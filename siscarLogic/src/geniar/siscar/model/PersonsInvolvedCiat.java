package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonsInvolvedCiat entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERSONS_INVOLVED_CIAT", schema = "", uniqueConstraints = {})
public class PersonsInvolvedCiat implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long picIdentificacion;
	private Cities cities;
	private Vehicles vehicles;
	private String picNombre;
	private String picDireccion;
	private String picTelefono;
	private Long picEdad;
	private String picSitioAtencion;

	// Constructors

	/** default constructor */
	public PersonsInvolvedCiat() {
	}

	/** minimal constructor */
	public PersonsInvolvedCiat(Long picIdentificacion, Cities cities, String picNombre, String picDireccion,
			String picTelefono, Long picEdad, String picSitioAtencion) {
		this.picIdentificacion = picIdentificacion;
		this.cities = cities;
		this.picNombre = picNombre;
		this.picDireccion = picDireccion;
		this.picTelefono = picTelefono;
		this.picEdad = picEdad;
		this.picSitioAtencion = picSitioAtencion;
	}

	/** full constructor */
	public PersonsInvolvedCiat(Long picIdentificacion, Cities cities, Vehicles vehicles, String picNombre,
			String picDireccion, String picTelefono, Long picEdad, String picSitioAtencion) {
		this.picIdentificacion = picIdentificacion;
		this.cities = cities;
		this.vehicles = vehicles;
		this.picNombre = picNombre;
		this.picDireccion = picDireccion;
		this.picTelefono = picTelefono;
		this.picEdad = picEdad;
		this.picSitioAtencion = picSitioAtencion;
	}

	// Property accessors
	@Id
	@Column(name = "PIC_IDENTIFICACION", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPicIdentificacion() {
		return this.picIdentificacion;
	}

	public void setPicIdentificacion(Long picIdentificacion) {
		this.picIdentificacion = picIdentificacion;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTS_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Cities getCities() {
		return this.cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "PIC_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPicNombre() {
		return this.picNombre;
	}

	public void setPicNombre(String picNombre) {
		this.picNombre = picNombre;
	}

	@Column(name = "PIC_DIRECCION", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPicDireccion() {
		return this.picDireccion;
	}

	public void setPicDireccion(String picDireccion) {
		this.picDireccion = picDireccion;
	}

	@Column(name = "PIC_TELEFONO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPicTelefono() {
		return this.picTelefono;
	}

	public void setPicTelefono(String picTelefono) {
		this.picTelefono = picTelefono;
	}

	@Column(name = "PIC_EDAD", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPicEdad() {
		return this.picEdad;
	}

	public void setPicEdad(Long picEdad) {
		this.picEdad = picEdad;
	}

	@Column(name = "PIC_SITIO_ATENCION", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getPicSitioAtencion() {
		return this.picSitioAtencion;
	}

	public void setPicSitioAtencion(String picSitioAtencion) {
		this.picSitioAtencion = picSitioAtencion;
	}

}