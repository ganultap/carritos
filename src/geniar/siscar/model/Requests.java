package geniar.siscar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Requests entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REQUESTS", uniqueConstraints = {})
public class Requests implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rqsCodigo;
	private RequestsTypes requestsTypes;
	private LegateesTypes legateesTypes;
	private RequestsStates requestsStates;
	private VehiclesTypes vehiclesTypes;
	private Zones zones;
	private Vehicles vehicles;
	private Users usersByRqsUser;
	private RequestsClasses requestsClasses;
	private Users usersByUsrCodigo;
	private String rqsMotivoCancelacion;
	private String rqsDevolucion;
	private Date rqsFechaCancelacion;
	private Date rqsFechaSolicitud;
	private String rqsDescripcion;
	private String rqsCarnetEmpleado;
	private String rqsCarnetAsignatario;
	private Date rqsFechaInicial;
	private Date rqsFechaFinal;
	private String rqsNit;
	private String rqsPlacaExtensionRemplazo;
	private String rqsCarneLogin;
	private Set<LegateesNewness> legateesNewnesses = new HashSet<LegateesNewness>(
			0);
	private Set<VehiclesAssignation> vehiclesAssignations = new HashSet<VehiclesAssignation>(
			0);
	private Set<CostsCentersVehicles> costsCentersVehicleses = new HashSet<CostsCentersVehicles>(
			0);

	// Constructors

	/** default constructor */
	public Requests() {
	}

	/** minimal constructor */
	public Requests(Long rqsCodigo, RequestsTypes requestsTypes,
			RequestsStates requestsStates, Users usersByRqsUser,
			RequestsClasses requestsClasses, Date rqsFechaSolicitud,
			String rqsDescripcion, Date rqsFechaInicial, Date rqsFechaFinal) {
		this.rqsCodigo = rqsCodigo;
		this.requestsTypes = requestsTypes;
		this.requestsStates = requestsStates;
		this.usersByRqsUser = usersByRqsUser;
		this.requestsClasses = requestsClasses;
		this.rqsFechaSolicitud = rqsFechaSolicitud;
		this.rqsDescripcion = rqsDescripcion;
		this.rqsFechaInicial = rqsFechaInicial;
		this.rqsFechaFinal = rqsFechaFinal;
	}

	/** full constructor */
	public Requests(Long rqsCodigo, RequestsTypes requestsTypes,
			LegateesTypes legateesTypes, RequestsStates requestsStates,
			VehiclesTypes vehiclesTypes, Zones zones, Vehicles vehicles,
			Users usersByRqsUser, RequestsClasses requestsClasses,
			Users usersByUsrCodigo, String rqsMotivoCancelacion,
			String rqsDevolucion, Date rqsFechaCancelacion,
			Date rqsFechaSolicitud, String rqsDescripcion,
			String rqsCarnetEmpleado, String rqsCarnetAsignatario,
			Date rqsFechaInicial, Date rqsFechaFinal, String rqsNit,
			String rqsPlacaExtensionRemplazo, String rqsCarneLogin,
			Set<LegateesNewness> legateesNewnesses,
			Set<VehiclesAssignation> vehiclesAssignations,
			Set<CostsCentersVehicles> costsCentersVehicleses) {
		this.rqsCodigo = rqsCodigo;
		this.requestsTypes = requestsTypes;
		this.legateesTypes = legateesTypes;
		this.requestsStates = requestsStates;
		this.vehiclesTypes = vehiclesTypes;
		this.zones = zones;
		this.vehicles = vehicles;
		this.usersByRqsUser = usersByRqsUser;
		this.requestsClasses = requestsClasses;
		this.usersByUsrCodigo = usersByUsrCodigo;
		this.rqsMotivoCancelacion = rqsMotivoCancelacion;
		this.rqsDevolucion = rqsDevolucion;
		this.rqsFechaCancelacion = rqsFechaCancelacion;
		this.rqsFechaSolicitud = rqsFechaSolicitud;
		this.rqsDescripcion = rqsDescripcion;
		this.rqsCarnetEmpleado = rqsCarnetEmpleado;
		this.rqsCarnetAsignatario = rqsCarnetAsignatario;
		this.rqsFechaInicial = rqsFechaInicial;
		this.rqsFechaFinal = rqsFechaFinal;
		this.rqsNit = rqsNit;
		this.rqsPlacaExtensionRemplazo = rqsPlacaExtensionRemplazo;
		this.legateesNewnesses = legateesNewnesses;
		this.vehiclesAssignations = vehiclesAssignations;
		this.costsCentersVehicleses = costsCentersVehicleses;
		this.rqsCarneLogin = rqsCarneLogin;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "REQUESTS_GEN", sequenceName = "SQ_REQUESTS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQUESTS_GEN")
	@Column(name = "RQS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRqsCodigo() {
		return this.rqsCodigo;
	}

	public void setRqsCodigo(Long rqsCodigo) {
		this.rqsCodigo = rqsCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQY_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public RequestsTypes getRequestsTypes() {
		return this.requestsTypes;
	}

	public void setRequestsTypes(RequestsTypes requestsTypes) {
		this.requestsTypes = requestsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LGT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public LegateesTypes getLegateesTypes() {
		return this.legateesTypes;
	}

	public void setLegateesTypes(LegateesTypes legateesTypes) {
		this.legateesTypes = legateesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public RequestsStates getRequestsStates() {
		return this.requestsStates;
	}

	public void setRequestsStates(RequestsStates requestsStates) {
		this.requestsStates = requestsStates;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public VehiclesTypes getVehiclesTypes() {
		return this.vehiclesTypes;
	}

	public void setVehiclesTypes(VehiclesTypes vehiclesTypes) {
		this.vehiclesTypes = vehiclesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ZNS_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Zones getZones() {
		return this.zones;
	}

	public void setZones(Zones zones) {
		this.zones = zones;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQS_USER", unique = false, nullable = false, insertable = true, updatable = true)
	public Users getUsersByRqsUser() {
		return this.usersByRqsUser;
	}

	public void setUsersByRqsUser(Users usersByRqsUser) {
		this.usersByRqsUser = usersByRqsUser;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public RequestsClasses getRequestsClasses() {
		return this.requestsClasses;
	}

	public void setRequestsClasses(RequestsClasses requestsClasses) {
		this.requestsClasses = requestsClasses;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "USR_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Users getUsersByUsrCodigo() {
		return this.usersByUsrCodigo;
	}

	public void setUsersByUsrCodigo(Users usersByUsrCodigo) {
		this.usersByUsrCodigo = usersByUsrCodigo;
	}

	@Column(name = "RQS_MOTIVO_CANCELACION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRqsMotivoCancelacion() {
		return this.rqsMotivoCancelacion;
	}

	public void setRqsMotivoCancelacion(String rqsMotivoCancelacion) {
		this.rqsMotivoCancelacion = rqsMotivoCancelacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RQS_FECHA_CANCELACION", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getRqsFechaCancelacion() {
		return this.rqsFechaCancelacion;
	}

	public void setRqsFechaCancelacion(Date rqsFechaCancelacion) {
		this.rqsFechaCancelacion = rqsFechaCancelacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RQS_FECHA_SOLICITUD", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getRqsFechaSolicitud() {
		return this.rqsFechaSolicitud;
	}

	public void setRqsFechaSolicitud(Date rqsFechaSolicitud) {
		this.rqsFechaSolicitud = rqsFechaSolicitud;
	}

	@Column(name = "RQS_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getRqsDescripcion() {
		return this.rqsDescripcion;
	}

	public void setRqsDescripcion(String rqsDescripcion) {
		this.rqsDescripcion = rqsDescripcion;
	}

	@Column(name = "RQS_CARNET_EMPLEADO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRqsCarnetEmpleado() {
		return this.rqsCarnetEmpleado;
	}

	public void setRqsCarnetEmpleado(String rqsCarnetEmpleado) {
		this.rqsCarnetEmpleado = rqsCarnetEmpleado;
	}

	@Column(name = "RQS_CARNET_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRqsCarnetAsignatario() {
		return this.rqsCarnetAsignatario;
	}

	public void setRqsCarnetAsignatario(String rqsCarnetAsignatario) {
		this.rqsCarnetAsignatario = rqsCarnetAsignatario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RQS_FECHA_INICIAL", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getRqsFechaInicial() {
		return this.rqsFechaInicial;
	}

	public void setRqsFechaInicial(Date rqsFechaInicial) {
		this.rqsFechaInicial = rqsFechaInicial;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "RQS_FECHA_FINAL", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getRqsFechaFinal() {
		return this.rqsFechaFinal;
	}

	public void setRqsFechaFinal(Date rqsFechaFinal) {
		this.rqsFechaFinal = rqsFechaFinal;
	}

	@Column(name = "RQS_NIT", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getRqsNit() {
		return this.rqsNit;
	}

	public void setRqsNit(String rqsNit) {
		this.rqsNit = rqsNit;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "requests")
	public Set<LegateesNewness> getLegateesNewnesses() {
		return this.legateesNewnesses;
	}

	public void setLegateesNewnesses(Set<LegateesNewness> legateesNewnesses) {
		this.legateesNewnesses = legateesNewnesses;
	}

	@OneToMany(cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "requests")
	public Set<VehiclesAssignation> getVehiclesAssignations() {
		return this.vehiclesAssignations;
	}

	public void setVehiclesAssignations(
			Set<VehiclesAssignation> vehiclesAssignations) {
		this.vehiclesAssignations = vehiclesAssignations;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "requests")
	public Set<CostsCentersVehicles> getCostsCentersVehicleses() {
		return this.costsCentersVehicleses;
	}

	public void setCostsCentersVehicleses(
			Set<CostsCentersVehicles> costsCentersVehicleses) {
		this.costsCentersVehicleses = costsCentersVehicleses;
	}

	@Column(name = "RQS_PLACA_EXTENSION_REMPLAZO", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getRqsPlacaExtensionRemplazo() {
		return rqsPlacaExtensionRemplazo;
	}

	public void setRqsPlacaExtensionRemplazo(String rqsPlacaExtensionRemplazo) {
		this.rqsPlacaExtensionRemplazo = rqsPlacaExtensionRemplazo;
	}

	@Column(name = "RQS_DEVOLUCION", unique = false, nullable = true, insertable = true, updatable = true, length = 4000)
	public String getRqsDevolucion() {
		return rqsDevolucion;
	}

	public void setRqsDevolucion(String rqsDevolucion) {
		this.rqsDevolucion = rqsDevolucion;
	}

	@Column(name = "RQS_CARNE_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getRqsCarneLogin() {
		return rqsCarneLogin;
	}

	public void setRqsCarneLogin(String rqsCarneLogin) {
		this.rqsCarneLogin = rqsCarneLogin;
	}

}