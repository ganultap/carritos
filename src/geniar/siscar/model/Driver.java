package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Driver entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DRIVER", schema = "")
public class Driver implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String drvCedula;
	private String drvNombre;
	private String drvNumeroCarne;
	private String drvCargo;
	private String drvDireccion;
	private String drvTelefono;

	// Constructors

	/** default constructor */
	public Driver() {
	}

	/** minimal constructor */
	public Driver(String drvCedula, String drvNombre, String drvCargo,
			String drvDireccion, String drvTelefono) {
		this.drvCedula = drvCedula;
		this.drvNombre = drvNombre;
		this.drvCargo = drvCargo;
		this.drvDireccion = drvDireccion;
		this.drvTelefono = drvTelefono;
	}

	/** full constructor */
	public Driver(String drvCedula, String drvNombre, String drvNumeroCarne,
			String drvCargo, String drvDireccion, String drvTelefono) {
		this.drvCedula = drvCedula;
		this.drvNombre = drvNombre;
		this.drvNumeroCarne = drvNumeroCarne;
		this.drvCargo = drvCargo;
		this.drvDireccion = drvDireccion;
		this.drvTelefono = drvTelefono;
	}

	// Property accessors
	@Id
	@Column(name = "DRV_CEDULA", unique = true, nullable = false, length = 30)
	public String getDrvCedula() {
		return this.drvCedula;
	}

	public void setDrvCedula(String drvCedula) {
		this.drvCedula = drvCedula;
	}

	@Column(name = "DRV_NOMBRE", nullable = false, length = 50)
	public String getDrvNombre() {
		return this.drvNombre;
	}

	public void setDrvNombre(String drvNombre) {
		this.drvNombre = drvNombre;
	}

	@Column(name = "DRV_NUMERO_CARNE", precision = 38, scale = 0)
	public String getDrvNumeroCarne() {
		return this.drvNumeroCarne;
	}

	public void setDrvNumeroCarne(String drvNumeroCarne) {
		this.drvNumeroCarne = drvNumeroCarne;
	}

	@Column(name = "DRV_CARGO", nullable = false, length = 50)
	public String getDrvCargo() {
		return this.drvCargo;
	}

	public void setDrvCargo(String drvCargo) {
		this.drvCargo = drvCargo;
	}

	@Column(name = "DRV_DIRECCION", nullable = false, length = 50)
	public String getDrvDireccion() {
		return this.drvDireccion;
	}

	public void setDrvDireccion(String drvDireccion) {
		this.drvDireccion = drvDireccion;
	}

	@Column(name = "DRV_TELEFONO", nullable = false, length = 20)
	public String getDrvTelefono() {
		return this.drvTelefono;
	}

	public void setDrvTelefono(String drvTelefono) {
		this.drvTelefono = drvTelefono;
	}

}