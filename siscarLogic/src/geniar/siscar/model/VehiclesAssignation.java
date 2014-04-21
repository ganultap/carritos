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
 * VehiclesAssignation entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VEHICLES_ASSIGNATION", schema = "", uniqueConstraints = {})
public class VehiclesAssignation implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long vhaCodigo;
	private Requests requests;
	private Vehicles vehicles;
	private AssignationsTypes assignationsTypes;
	private AssignationsStates assignationsStates;
	private String vhaNumeroSolicitud;
	private String vhaNumeroCarne;
	private Long vhaKilomeActual;
	private Date vhaFechaInicio;
	private Date vhaFechaTermina;
	private String vhaObservacion;
	private Date vhaFechaEntrega;
	private Date vhaFechaDev;
	private Long vhaKilomDev;
	private String vhaCobroCasaciat;
	private String vhaCobro;
	private String vhaObservacionDev;
	private Long vhaDiasAlquiler;
	private Float vhaValorAlquiler;
	private Long vhaKmAdicional;
	private Float vhaValorKmAdicional;
	private Set<CostsCentersVehicles> costsCentersVehicleses = new HashSet<CostsCentersVehicles>(
			0);
	private Set<CostCentersFuel> costCentersFuels = new HashSet<CostCentersFuel>(
			0);
	private Set<VhaAoaApp> vhaAoaApps = new HashSet<VhaAoaApp>(0);
	private Set<VhaFf> vhaFfs = new HashSet<VhaFf>(0);

	/** default constructor */
	public VehiclesAssignation() {
	}

	/** minimal constructor */
	public VehiclesAssignation(Long vhaCodigo, Requests requests,
			Vehicles vehicles, AssignationsTypes assignationsTypes,
			AssignationsStates assignationsStates) {
		this.vhaCodigo = vhaCodigo;
		this.requests = requests;
		this.vehicles = vehicles;
		this.assignationsTypes = assignationsTypes;
		this.assignationsStates = assignationsStates;
	}

	/** full constructor */
	public VehiclesAssignation(Long vhaCodigo, Requests requests,
			Vehicles vehicles, AssignationsTypes assignationsTypes,
			AssignationsStates assignationsStates, String vhaNumeroSolicitud,
			String vhaNumeroCarne, Long vhaKilomeActual, Date vhaFechaInicio,
			Date vhaFechaTermina, String vhaObservacion, Date vhaFechaEntrega,
			Date vhaFechaDev, Long vhaKilomDev, String vhaCobroCasaciat,
			String vhaCobro, String vhaObservacionDev,
			Set<CostsCentersVehicles> costsCentersVehicleses,Set<CostCentersFuel> costCentersFuels,
			Set<VhaAoaApp> vhaAoaApps, Set<VhaFf> vhaFfs) {
		this.vhaCodigo = vhaCodigo;
		this.requests = requests;
		this.vehicles = vehicles;
		this.assignationsTypes = assignationsTypes;
		this.assignationsStates = assignationsStates;
		this.vhaNumeroSolicitud = vhaNumeroSolicitud;
		this.vhaNumeroCarne = vhaNumeroCarne;
		this.vhaKilomeActual = vhaKilomeActual;
		this.vhaFechaInicio = vhaFechaInicio;
		this.vhaFechaTermina = vhaFechaTermina;
		this.vhaObservacion = vhaObservacion;
		this.vhaFechaEntrega = vhaFechaEntrega;
		this.vhaFechaDev = vhaFechaDev;
		this.vhaKilomDev = vhaKilomDev;
		this.vhaCobroCasaciat = vhaCobroCasaciat;
		this.vhaCobro = vhaCobro;
		this.vhaObservacionDev = vhaObservacionDev;
		this.costsCentersVehicleses = costsCentersVehicleses;
		this.costCentersFuels=costCentersFuels;
		this.vhaAoaApps = vhaAoaApps;
		this.vhaFfs = vhaFfs;
			}

	// Property accessors
	@Id
	@SequenceGenerator(name = "VEHICLES_ASSINATION_GEN", sequenceName = "SQ_VEHICLES_ASSINATION", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VEHICLES_ASSINATION_GEN")
	@Column(name = "VHA_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhaCodigo() {
		return this.vhaCodigo;
	}

	public void setVhaCodigo(Long vhaCodigo) {
		this.vhaCodigo = vhaCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Requests getRequests() {
		return this.requests;
	}

	public void setRequests(Requests requests) {
		this.requests = requests;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "AST_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public AssignationsTypes getAssignationsTypes() {
		return this.assignationsTypes;
	}

	public void setAssignationsTypes(AssignationsTypes assignationsTypes) {
		this.assignationsTypes = assignationsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ASS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public AssignationsStates getAssignationsStates() {
		return this.assignationsStates;
	}

	public void setAssignationsStates(AssignationsStates assignationsStates) {
		this.assignationsStates = assignationsStates;
	}

	@Column(name = "VHA_NUMERO_SOLICITUD", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhaNumeroSolicitud() {
		return this.vhaNumeroSolicitud;
	}

	public void setVhaNumeroSolicitud(String vhaNumeroSolicitud) {
		this.vhaNumeroSolicitud = vhaNumeroSolicitud;
	}

	@Column(name = "VHA_NUMERO_CARNE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhaNumeroCarne() {
		return this.vhaNumeroCarne;
	}

	public void setVhaNumeroCarne(String vhaNumeroCarne) {
		this.vhaNumeroCarne = vhaNumeroCarne;
	}

	@Column(name = "VHA_KILOME_ACTUAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhaKilomeActual() {
		return this.vhaKilomeActual;
	}

	public void setVhaKilomeActual(Long vhaKilomeActual) {
		this.vhaKilomeActual = vhaKilomeActual;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHA_FECHA_INICIO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhaFechaInicio() {
		return this.vhaFechaInicio;
	}

	public void setVhaFechaInicio(Date vhaFechaInicio) {
		this.vhaFechaInicio = vhaFechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHA_FECHA_TERMINA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhaFechaTermina() {
		return this.vhaFechaTermina;
	}

	public void setVhaFechaTermina(Date vhaFechaTermina) {
		this.vhaFechaTermina = vhaFechaTermina;
	}

	@Column(name = "VHA_OBSERVACION", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getVhaObservacion() {
		return this.vhaObservacion;
	}

	public void setVhaObservacion(String vhaObservacion) {
		this.vhaObservacion = vhaObservacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHA_FECHA_ENTREGA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhaFechaEntrega() {
		return this.vhaFechaEntrega;
	}

	public void setVhaFechaEntrega(Date vhaFechaEntrega) {
		this.vhaFechaEntrega = vhaFechaEntrega;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHA_FECHA_DEV", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhaFechaDev() {
		return this.vhaFechaDev;
	}

	public void setVhaFechaDev(Date vhaFechaDev) {
		this.vhaFechaDev = vhaFechaDev;
	}

	@Column(name = "VHA_KILOM_DEV", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhaKilomDev() {
		return this.vhaKilomDev;
	}

	public void setVhaKilomDev(Long vhaKilomDev) {
		this.vhaKilomDev = vhaKilomDev;
	}

	@Column(name = "VHA_COBRO_CASACIAT", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getVhaCobroCasaciat() {
		return this.vhaCobroCasaciat;
	}

	public void setVhaCobroCasaciat(String vhaCobroCasaciat) {
		this.vhaCobroCasaciat = vhaCobroCasaciat;
	}

	@Column(name = "VHA_COBRO", unique = false, nullable = true, insertable = true, updatable = true, length = 1)
	public String getVhaCobro() {
		return this.vhaCobro;
	}

	public void setVhaCobro(String vhaCobro) {
		this.vhaCobro = vhaCobro;
	}

	@Column(name = "VHA_OBSERVACION_DEV", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getVhaObservacionDev() {
		return this.vhaObservacionDev;
	}

	public void setVhaObservacionDev(String vhaObservacionDev) {
		this.vhaObservacionDev = vhaObservacionDev;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesAssignation")
	public Set<CostsCentersVehicles> getCostsCentersVehicleses() {
		return this.costsCentersVehicleses;
	}

	public void setCostsCentersVehicleses(
			Set<CostsCentersVehicles> costsCentersVehicleses) {
		this.costsCentersVehicleses = costsCentersVehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesAssignation")
	public Set<VhaAoaApp> getVhaAoaApps() {
		return this.vhaAoaApps;
	}

	public void setVhaAoaApps(Set<VhaAoaApp> vhaAoaApps) {
		this.vhaAoaApps = vhaAoaApps;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehiclesAssignation")
	public Set<VhaFf> getVhaFfs() {
		return this.vhaFfs;
	}

	public void setVhaFfs(Set<VhaFf> vhaFfs) {
		this.vhaFfs = vhaFfs;
	}
	
	@Column(name = "VHA_DIAS_ALQUILER", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhaDiasAlquiler() {
		return this.vhaDiasAlquiler;
	}

	public void setVhaDiasAlquiler(Long vhaDiasAlquiler) {
		this.vhaDiasAlquiler = vhaDiasAlquiler;
	}

	@Column(name = "VHA_VALOR_ALQUILER", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getVhaValorAlquiler() {
		return this.vhaValorAlquiler;
	}

	public void setVhaValorAlquiler(Float vhaValorAlquiler) {
		this.vhaValorAlquiler = vhaValorAlquiler;
	}

	@Column(name = "VHA_KM_ADICIONAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhaKmAdicional() {
		return this.vhaKmAdicional;
	}

	public void setVhaKmAdicional(Long vhaKmAdicional) {
		this.vhaKmAdicional = vhaKmAdicional;
	}

	@Column(name = "VHA_VALOR_KM_ADICIONAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getVhaValorKmAdicional() {
		return this.vhaValorKmAdicional;
	}

	public void setVhaValorKmAdicional(Float vhaValorKmAdicional) {
		this.vhaValorKmAdicional = vhaValorKmAdicional;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vehiclesAssignation")
	public Set<CostCentersFuel> getCostCentersFuels() {
		return this.costCentersFuels;
	}

	public void setCostCentersFuels(Set<CostCentersFuel> costCentersFuels) {
		this.costCentersFuels = costCentersFuels;
	}

}