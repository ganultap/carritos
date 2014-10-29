package geniar.siscar.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * LocationsNewness entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LOCATIONS_NEWNESS", uniqueConstraints = {})
public class LocationsNewness implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lcnCodigo;
	private Vehicles vehicles;
	private String usrLogin;
	private String lcnUbicacion;
	private String lcnDescripcion;
	private Date lcnFechaActual;

	// Constructors

	/** default constructor */
	public LocationsNewness() {
	}

	/** minimal constructor */
	public LocationsNewness(Long lcnCodigo, Vehicles vehicles, Date lcnFechaActual) {
		this.lcnCodigo = lcnCodigo;
		this.vehicles = vehicles;
		this.lcnFechaActual = lcnFechaActual;
	}

	/** full constructor */
	public LocationsNewness(Long lcnCodigo, Vehicles vehicles, String usrLogin, String lcnUbicacion,
			String lcnDescripcion, Date lcnFechaActual) {
		this.lcnCodigo = lcnCodigo;
		this.vehicles = vehicles;
		this.usrLogin = usrLogin;
		this.lcnUbicacion = lcnUbicacion;
		this.lcnDescripcion = lcnDescripcion;
		this.lcnFechaActual = lcnFechaActual;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="LOCATIONS_NEWNESS_GEN", sequenceName="SQ_LOCATIONS_NEWNESS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LOCATIONS_NEWNESS_GEN")
	@Column(name = "LCN_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getLcnCodigo() {
		return this.lcnCodigo;
	}

	public void setLcnCodigo(Long lcnCodigo) {
		this.lcnCodigo = lcnCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Column(name = "LCN_UBICACION", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getLcnUbicacion() {
		return this.lcnUbicacion;
	}

	public void setLcnUbicacion(String lcnUbicacion) {
		this.lcnUbicacion = lcnUbicacion;
	}

	@Column(name = "LCN_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getLcnDescripcion() {
		return this.lcnDescripcion;
	}

	public void setLcnDescripcion(String lcnDescripcion) {
		this.lcnDescripcion = lcnDescripcion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LCN_FECHA_ACTUAL", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getLcnFechaActual() {
		return this.lcnFechaActual;
	}

	public void setLcnFechaActual(Date lcnFechaActual) {
		this.lcnFechaActual = lcnFechaActual;
	}

}