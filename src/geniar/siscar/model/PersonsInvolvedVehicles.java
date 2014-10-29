package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * PersonsInvolvedVehicles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PERSONS_INVOLVED_VEHICLES", schema = "", uniqueConstraints = {})
public class PersonsInvolvedVehicles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pivIdentificacion;
	private Cities cities;
	private InvolvedVehicles involvedVehicles;
	private String pivNombre;
	private String pivDireccion;
	private String pnvTelefono;
	private Long pivEdad;
	private String pivSitioatencion;

	// Constructors

	/** default constructor */
	public PersonsInvolvedVehicles() {
	}

	/** full constructor */
	public PersonsInvolvedVehicles(Long pivIdentificacion, Cities cities, InvolvedVehicles involvedVehicles,
			String pivNombre, String pivDireccion, String pnvTelefono, Long pivEdad, String pivSitioatencion) {
		this.pivIdentificacion = pivIdentificacion;
		this.cities = cities;
		this.involvedVehicles = involvedVehicles;
		this.pivNombre = pivNombre;
		this.pivDireccion = pivDireccion;
		this.pnvTelefono = pnvTelefono;
		this.pivEdad = pivEdad;
		this.pivSitioatencion = pivSitioatencion;
	}

	// Property accessors
	@Id
	@Column(name = "PIV_IDENTIFICACION", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPivIdentificacion() {
		return this.pivIdentificacion;
	}

	public void setPivIdentificacion(Long pivIdentificacion) {
		this.pivIdentificacion = pivIdentificacion;
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
	@JoinColumn(name = "HNV_PLACA", unique = false, nullable = false, insertable = true, updatable = true)
	public InvolvedVehicles getInvolvedVehicles() {
		return this.involvedVehicles;
	}

	public void setInvolvedVehicles(InvolvedVehicles involvedVehicles) {
		this.involvedVehicles = involvedVehicles;
	}

	@Column(name = "PIV_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPivNombre() {
		return this.pivNombre;
	}

	public void setPivNombre(String pivNombre) {
		this.pivNombre = pivNombre;
	}

	@Column(name = "PIV_DIRECCION", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPivDireccion() {
		return this.pivDireccion;
	}

	public void setPivDireccion(String pivDireccion) {
		this.pivDireccion = pivDireccion;
	}

	@Column(name = "PNV_TELEFONO", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getPnvTelefono() {
		return this.pnvTelefono;
	}

	public void setPnvTelefono(String pnvTelefono) {
		this.pnvTelefono = pnvTelefono;
	}

	@Column(name = "PIV_EDAD", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPivEdad() {
		return this.pivEdad;
	}

	public void setPivEdad(Long pivEdad) {
		this.pivEdad = pivEdad;
	}

	@Column(name = "PIV_SITIOATENCION", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getPivSitioatencion() {
		return this.pivSitioatencion;
	}

	public void setPivSitioatencion(String pivSitioatencion) {
		this.pivSitioatencion = pivSitioatencion;
	}

}