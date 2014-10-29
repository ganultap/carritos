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
 * InjuredPersons entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INJURED_PERSONS", schema = "", uniqueConstraints = {})
public class InjuredPersons implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pivIdentificacion;
	private Cities cities;
	private InvolvedVehicles involvedVehicles;
	private String pivId;
	private String pivNombre;
	private String pivDireccion;
	private String pnvTelefono;
	private Long pivEdad;
	private String pivSitioatencion;

	// Constructors

	/** default constructor */
	public InjuredPersons() {
	}

	/** minimal constructor */
	public InjuredPersons(Long pivIdentificacion, String pivNombre,
			String pivDireccion, String pnvTelefono, Long pivEdad,
			String pivSitioatencion) {
		this.pivIdentificacion = pivIdentificacion;
		this.pivNombre = pivNombre;
		this.pivDireccion = pivDireccion;
		this.pnvTelefono = pnvTelefono;
		this.pivEdad = pivEdad;
		this.pivSitioatencion = pivSitioatencion;
	}

	/** full constructor */
	public InjuredPersons(Long pivIdentificacion, Cities cities,
			InvolvedVehicles involvedVehicles, String pivId, String pivNombre,
			String pivDireccion, String pnvTelefono, Long pivEdad,
			String pivSitioatencion) {
		this.pivIdentificacion = pivIdentificacion;
		this.cities = cities;
		this.involvedVehicles = involvedVehicles;
		this.pivId = pivId;
		this.pivNombre = pivNombre;
		this.pivDireccion = pivDireccion;
		this.pnvTelefono = pnvTelefono;
		this.pivEdad = pivEdad;
		this.pivSitioatencion = pivSitioatencion;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "INJURED_VEHICLES_GEN", sequenceName = "SQ_INJURED_VEHICLES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INJURED_VEHICLES_GEN")
	@Column(name = "PIV_IDENTIFICACION", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPivIdentificacion() {
		return this.pivIdentificacion;
	}

	public void setPivIdentificacion(Long pivIdentificacion) {
		this.pivIdentificacion = pivIdentificacion;
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
	@JoinColumn(name = "HNV_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public InvolvedVehicles getInvolvedVehicles() {
		return this.involvedVehicles;
	}

	public void setInvolvedVehicles(InvolvedVehicles involvedVehicles) {
		this.involvedVehicles = involvedVehicles;
	}

	@Column(name = "PIV_ID", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getPivId() {
		return this.pivId;
	}

	public void setPivId(String pivId) {
		this.pivId = pivId;
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