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
 * CostsCentersVehicles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COSTS_CENTERS_VEHICLES", schema = "", uniqueConstraints = {})
public class CostsCentersVehicles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ccrCodigo;
	private VehiclesAssignation vehiclesAssignation;
	private Requests requests;
	private CostsCenters costsCenters;
	private Vehicles vehicles;
	private String ccrPorcentaje;
	private Long ccrValor;
	private Date ccrFechaInicio;
	private Date ccrFechaFin;
	private String ccrLogin;
	private Date ccrFechaActual;
	private String ccrEstado;

	// Constructors

	/** default constructor */
	public CostsCentersVehicles() {
	}

	/** minimal constructor */
	public CostsCentersVehicles(Long ccrCodigo,
			VehiclesAssignation vehiclesAssignation, Requests requests,
			CostsCenters costsCenters, Vehicles vehicles, String ccrPorcentaje,
			Date ccrFechaInicio, String ccrLogin, Date ccrFechaActual) {
		this.ccrCodigo = ccrCodigo;
		this.vehiclesAssignation = vehiclesAssignation;
		this.requests = requests;
		this.costsCenters = costsCenters;
		this.vehicles = vehicles;
		this.ccrPorcentaje = ccrPorcentaje;
		this.ccrFechaInicio = ccrFechaInicio;
		this.ccrLogin = ccrLogin;
		this.ccrFechaActual = ccrFechaActual;
	}

	/** full constructor */
	public CostsCentersVehicles(Long ccrCodigo,
			VehiclesAssignation vehiclesAssignation, Requests requests,
			CostsCenters costsCenters, Vehicles vehicles, String ccrPorcentaje,
			Long ccrValor, Date ccrFechaInicio, Date ccrFechaFin,
			String ccrLogin, Date ccrFechaActual, String ccrEstado) {
		this.ccrCodigo = ccrCodigo;
		this.vehiclesAssignation = vehiclesAssignation;
		this.requests = requests;
		this.costsCenters = costsCenters;
		this.vehicles = vehicles;
		this.ccrPorcentaje = ccrPorcentaje;
		this.ccrValor = ccrValor;
		this.ccrFechaInicio = ccrFechaInicio;
		this.ccrFechaFin = ccrFechaFin;
		this.ccrLogin = ccrLogin;
		this.ccrFechaActual = ccrFechaActual;
		this.ccrEstado = ccrEstado;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "COSTS_CENTERS_VEHICLES_GEN", sequenceName = "SQ_COSTS_CENTERS_VEHICLES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COSTS_CENTERS_VEHICLES_GEN")
	@Column(name = "CCR_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCcrCodigo() {
		return this.ccrCodigo;
	}

	public void setCcrCodigo(Long ccrCodigo) {
		this.ccrCodigo = ccrCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHA_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public VehiclesAssignation getVehiclesAssignation() {
		return this.vehiclesAssignation;
	}

	public void setVehiclesAssignation(VehiclesAssignation vehiclesAssignation) {
		this.vehiclesAssignation = vehiclesAssignation;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQS_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Requests getRequests() {
		return this.requests;
	}

	public void setRequests(Requests requests) {
		this.requests = requests;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "COC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public CostsCenters getCostsCenters() {
		return this.costsCenters;
	}

	public void setCostsCenters(CostsCenters costsCenters) {
		this.costsCenters = costsCenters;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
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
	@Column(name = "CCR_FECHA_INICIO", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
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

	@Column(name = "CCR_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getCcrLogin() {
		return this.ccrLogin;
	}

	public void setCcrLogin(String ccrLogin) {
		this.ccrLogin = ccrLogin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CCR_FECHA_ACTUAL", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCcrFechaActual() {
		return this.ccrFechaActual;
	}

	public void setCcrFechaActual(Date ccrFechaActual) {
		this.ccrFechaActual = ccrFechaActual;
	}

	@Column(name = "CCR_ESTADO", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getCcrEstado() {
		return this.ccrEstado;
	}

	public void setCcrEstado(String ccrEstado) {
		this.ccrEstado = ccrEstado;
	}

}