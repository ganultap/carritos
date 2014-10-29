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
 * CostsCentersNewness entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COSTS_CENTERS_NEWNESS", uniqueConstraints = {})
public class CostsCentersNewness implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cocCodigo;
	private Vehicles vehicles;
	private String ccnDescripcion;
	private String usrLogin;
	private Date ccnFechaActual;
	private String ccrPorcentaje;
	private Long ccrValor;
	private Date ccrFechaInicio;
	private Date ccrFechaFin;

	// Constructors

	/** default constructor */
	public CostsCentersNewness() {
	}

	/** minimal constructor */
	public CostsCentersNewness(Long cocCodigo, Vehicles vehicles, String usrLogin, Date ccnFechaActual,
			String ccrPorcentaje) {
		this.cocCodigo = cocCodigo;
		this.vehicles = vehicles;
		this.usrLogin = usrLogin;
		this.ccnFechaActual = ccnFechaActual;
		this.ccrPorcentaje = ccrPorcentaje;
	}

	/** full constructor */
	public CostsCentersNewness(Long cocCodigo, Vehicles vehicles, String ccnDescripcion, String usrLogin,
			Date ccnFechaActual, String ccrPorcentaje, Long ccrValor, Date ccrFechaInicio, Date ccrFechaFin) {
		this.cocCodigo = cocCodigo;
		this.vehicles = vehicles;
		this.ccnDescripcion = ccnDescripcion;
		this.usrLogin = usrLogin;
		this.ccnFechaActual = ccnFechaActual;
		this.ccrPorcentaje = ccrPorcentaje;
		this.ccrValor = ccrValor;
		this.ccrFechaInicio = ccrFechaInicio;
		this.ccrFechaFin = ccrFechaFin;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="COSTS_CENTERS_NEWNESS_GEN", sequenceName="SQ_COSTS_CENTERS_NEWNESS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COSTS_CENTERS_NEWNESS_GEN")
	@Column(name = "COC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCocCodigo() {
		return this.cocCodigo;
	}

	public void setCocCodigo(Long cocCodigo) {
		this.cocCodigo = cocCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "CCN_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getCcnDescripcion() {
		return this.ccnDescripcion;
	}

	public void setCcnDescripcion(String ccnDescripcion) {
		this.ccnDescripcion = ccnDescripcion;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CCN_FECHA_ACTUAL", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getCcnFechaActual() {
		return this.ccnFechaActual;
	}

	public void setCcnFechaActual(Date ccnFechaActual) {
		this.ccnFechaActual = ccnFechaActual;
	}

	@Column(name = "CCR_PORCENTAJE", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getCcrPorcentaje() {
		return this.ccrPorcentaje;
	}

	public void setCcrPorcentaje(String ccrPorcentaje) {
		this.ccrPorcentaje = ccrPorcentaje;
	}

	@Column(name = "CCR_VALOR", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCcrValor() {
		return this.ccrValor;
	}

	public void setCcrValor(Long ccrValor) {
		this.ccrValor = ccrValor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CCR_FECHA_INICIO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCcrFechaInicio() {
		return this.ccrFechaInicio;
	}

	public void setCcrFechaInicio(Date ccrFechaInicio) {
		this.ccrFechaInicio = ccrFechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CCR_FECHA_FIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCcrFechaFin() {
		return this.ccrFechaFin;
	}

	public void setCcrFechaFin(Date ccrFechaFin) {
		this.ccrFechaFin = ccrFechaFin;
	}

}