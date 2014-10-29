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
 * ServiceRegistry entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SERVICE_REGISTRY", uniqueConstraints = {})
public class ServiceRegistry implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long serCodigo;
	private LegateesTypes legateesTypes;
	private FuelTypeRequest fuelTypeRequest;
	private Prepaid prepaid;
	private Vehicles vehicles;
	private Tariffs tariffs;
	private HeaderProof headerProof;
	private Pumps pumps;
	private FuelsTypes fuelsTypes;
	private String serUsrLogin;
	private String serCorreoAsignatario;
	private Date serFecha;
	private String serHora;
	private String serCarneAsignatario;
	private String serNumRebPag;
	private String serNombreSolicitante;
	private String serCarneSolicitante;
	private String serKilometrajeAnterior;
	private Float serNumeroGalones;
	private Float serTotal;
	private String serKilometrajeActual;
	private String serObservaciones;
	private String serPlacaIntra;
	private Long aoaCodigo;
	private ChargeTo chargeTo;
	private FlatFile flatFile;

	// Constructors

	/** default constructor */
	public ServiceRegistry() {
	}

	/** minimal constructor */
	public ServiceRegistry(Long serCodigo, FuelTypeRequest fuelTypeRequest,
			Prepaid prepaid, Tariffs tariffs, HeaderProof headerProof,
			Pumps pumps, FuelsTypes fuelsTypes, Date serFecha, String serHora,
			String serNumRebPag, Float serNumeroGalones, Float serTotal,
			ChargeTo chargeTo) {
		this.serCodigo = serCodigo;
		this.fuelTypeRequest = fuelTypeRequest;
		this.prepaid = prepaid;
		this.tariffs = tariffs;
		this.headerProof = headerProof;
		this.pumps = pumps;
		this.fuelsTypes = fuelsTypes;
		this.serFecha = serFecha;
		this.serHora = serHora;
		this.serNumRebPag = serNumRebPag;
		this.serNumeroGalones = serNumeroGalones;
		this.serTotal = serTotal;
		this.chargeTo = chargeTo;
	}

	/** full constructor */
	public ServiceRegistry(Long serCodigo, LegateesTypes legateesTypes,
			FuelTypeRequest fuelTypeRequest, Prepaid prepaid,
			Vehicles vehicles, Tariffs tariffs, HeaderProof headerProof,
			Pumps pumps, FuelsTypes fuelsTypes, String serUsrLogin,
			String serCorreoAsignatario, Date serFecha, String serHora,
			String serCarneAsignatario, String serNumRebPag,
			String serNombreSolicitante, String serCarneSolicitante,
			String serKilometrajeAnterior, Float serNumeroGalones,
			Float serTotal, String serKilometrajeActual,
			String serObservaciones, String serPlacaIntra, Long aoaCodigo,
			ChargeTo chargeTo, FlatFile flatFile) {
		this.serCodigo = serCodigo;
		this.legateesTypes = legateesTypes;
		this.fuelTypeRequest = fuelTypeRequest;
		this.prepaid = prepaid;
		this.vehicles = vehicles;
		this.tariffs = tariffs;
		this.headerProof = headerProof;
		this.pumps = pumps;
		this.fuelsTypes = fuelsTypes;
		this.serUsrLogin = serUsrLogin;
		this.serCorreoAsignatario = serCorreoAsignatario;
		this.serFecha = serFecha;
		this.serHora = serHora;
		this.serCarneAsignatario = serCarneAsignatario;
		this.serNumRebPag = serNumRebPag;
		this.serNombreSolicitante = serNombreSolicitante;
		this.serCarneSolicitante = serCarneSolicitante;
		this.serKilometrajeAnterior = serKilometrajeAnterior;
		this.serNumeroGalones = serNumeroGalones;
		this.serTotal = serTotal;
		this.serKilometrajeActual = serKilometrajeActual;
		this.serObservaciones = serObservaciones;
		this.serPlacaIntra = serPlacaIntra;
		this.aoaCodigo = aoaCodigo;
		this.chargeTo = chargeTo;
		this.flatFile = flatFile;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "SERVICE_REGISTRY_GEN", sequenceName = "SQ_SERVICE_REGISTRY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICE_REGISTRY_GEN")
	@Column(name = "SER_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getSerCodigo() {
		return this.serCodigo;
	}

	public void setSerCodigo(Long serCodigo) {
		this.serCodigo = serCodigo;
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
	@JoinColumn(name = "FTR_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public FuelTypeRequest getFuelTypeRequest() {
		return this.fuelTypeRequest;
	}

	public void setFuelTypeRequest(FuelTypeRequest fuelTypeRequest) {
		this.fuelTypeRequest = fuelTypeRequest;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PRE_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Prepaid getPrepaid() {
		return this.prepaid;
	}

	public void setPrepaid(Prepaid prepaid) {
		this.prepaid = prepaid;
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
	@JoinColumn(name = "TRF_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Tariffs getTariffs() {
		return this.tariffs;
	}

	public void setTariffs(Tariffs tariffs) {
		this.tariffs = tariffs;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "HEP_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public HeaderProof getHeaderProof() {
		return this.headerProof;
	}

	public void setHeaderProof(HeaderProof headerProof) {
		this.headerProof = headerProof;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PUM_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Pumps getPumps() {
		return this.pumps;
	}

	public void setPumps(Pumps pumps) {
		this.pumps = pumps;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FUT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public FuelsTypes getFuelsTypes() {
		return this.fuelsTypes;
	}

	public void setFuelsTypes(FuelsTypes fuelsTypes) {
		this.fuelsTypes = fuelsTypes;
	}

	@Column(name = "SER_USR_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getSerUsrLogin() {
		return this.serUsrLogin;
	}

	public void setSerUsrLogin(String serUsrLogin) {
		this.serUsrLogin = serUsrLogin;
	}

	@Column(name = "SER_CORREO_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getSerCorreoAsignatario() {
		return this.serCorreoAsignatario;
	}

	public void setSerCorreoAsignatario(String serCorreoAsignatario) {
		this.serCorreoAsignatario = serCorreoAsignatario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SER_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getSerFecha() {
		return this.serFecha;
	}

	public void setSerFecha(Date serFecha) {
		this.serFecha = serFecha;
	}

	@Column(name = "SER_HORA", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getSerHora() {
		return this.serHora;
	}

	public void setSerHora(String serHora) {
		this.serHora = serHora;
	}

	@Column(name = "SER_CARNE_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getSerCarneAsignatario() {
		return this.serCarneAsignatario;
	}

	public void setSerCarneAsignatario(String serCarneAsignatario) {
		this.serCarneAsignatario = serCarneAsignatario;
	}

	@Column(name = "SER_NUM_REB_PAG", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getSerNumRebPag() {
		return this.serNumRebPag;
	}

	public void setSerNumRebPag(String serNumRebPag) {
		this.serNumRebPag = serNumRebPag;
	}

	@Column(name = "SER_NOMBRE_SOLICITANTE", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getSerNombreSolicitante() {
		return this.serNombreSolicitante;
	}

	public void setSerNombreSolicitante(String serNombreSolicitante) {
		this.serNombreSolicitante = serNombreSolicitante;
	}

	@Column(name = "SER_CARNE_SOLICITANTE", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getSerCarneSolicitante() {
		return this.serCarneSolicitante;
	}

	public void setSerCarneSolicitante(String serCarneSolicitante) {
		this.serCarneSolicitante = serCarneSolicitante;
	}

	@Column(name = "SER_KILOMETRAJE_ANTERIOR", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getSerKilometrajeAnterior() {
		return this.serKilometrajeAnterior;
	}

	public void setSerKilometrajeAnterior(String serKilometrajeAnterior) {
		this.serKilometrajeAnterior = serKilometrajeAnterior;
	}

	@Column(name = "SER_NUMERO_GALONES", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getSerNumeroGalones() {
		return this.serNumeroGalones;
	}

	public void setSerNumeroGalones(Float serNumeroGalones) {
		this.serNumeroGalones = serNumeroGalones;
	}

	@Column(name = "SER_TOTAL", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getSerTotal() {
		return this.serTotal;
	}

	public void setSerTotal(Float serTotal) {
		this.serTotal = serTotal;
	}

	@Column(name = "SER_KILOMETRAJE_ACTUAL", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getSerKilometrajeActual() {
		return this.serKilometrajeActual;
	}

	public void setSerKilometrajeActual(String serKilometrajeActual) {
		this.serKilometrajeActual = serKilometrajeActual;
	}

	@Column(name = "SER_OBSERVACIONES", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getSerObservaciones() {
		return this.serObservaciones;
	}

	public void setSerObservaciones(String serObservaciones) {
		this.serObservaciones = serObservaciones;
	}

	@Column(name = "SER_PLACA_INTRA", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getSerPlacaIntra() {
		return this.serPlacaIntra;
	}

	public void setSerPlacaIntra(String serPlacaIntra) {
		this.serPlacaIntra = serPlacaIntra;
	}

	@Column(name = "AOA_CODIGO", unique = false, nullable = true, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getAoaCodigo() {
		return this.aoaCodigo;
	}

	public void setAoaCodigo(Long aoaCodigo) {
		this.aoaCodigo = aoaCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public ChargeTo getChargeTo() {
		return this.chargeTo;
	}

	public void setChargeTo(ChargeTo chargeTo) {
		this.chargeTo = chargeTo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FF_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public FlatFile getFlatFile() {
		return this.flatFile;
	}

	public void setFlatFile(FlatFile flatFile) {
		this.flatFile = flatFile;
	}

}