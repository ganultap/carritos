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
 * Vehicles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VEHICLES", uniqueConstraints = {})
public class Vehicles implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long vhcCodigo;
	private RetirementsReasons retirementsReasons;
	private Lines lines;
	private InsurancesCar insurancesCar;
	private Locations locations;
	private VehiclesStates vehiclesStates;
	private TapestriesTypes tapestriesTypes;
	private VehiclesTypes vehiclesTypes;
	private TransmissionsTypes transmissionsTypes;
	private TractionsTypes tractionsTypes;
	private FuelsTypes fuelsTypes;
	private String vhcCiuAduan;
	private String vhcUnidadTaquim;
	private Long vhcValorAsignacion;
	private String vhcCatalogado;
	private String vhcPlacaDiplomatica;
	private String vhcPlacaActivoFijo;
	private String vhcEspecificTec;
	private Date vhcFechaLevante;
	private String vhcDocumentTrans;
	private String vhcNumeroFactura;
	private String vhcNumeroChasis;
	private String vhcOrderCompra;
	private String vhcProveedor;
	private Long vhcCapacidad;
	private Float vhcCapMaxTanq;
	private String vhcValorComercial;
	private Long vhcAnoValCom;
	private Date vhcFechaProtocolo;
	private String vhcNumeroManifiesto;
	private Date vhcFechaManifiesto;
	private String vhcNumDeclImpor;
	private String vhcNumeroTl;
	private String vhcRemplazaA;
	private String vhcPlRemVehi;
	private String vhcAtInicial;
	private String vhcValorCif;
	private String vhcVidaUtil;
	private String vhcValorFob;
	private String vhcCargosImport;
	private String vhcObservaciones;
	private String vhcDescripcion;
	private Long vhcAno;
	private String vhcNumeroLevante;
	private String vhcNumReferCat;
	private String vhcOdometro;
	private String vhcCilindraje;
	private String vhcNumeroMotor;
	private String vhcAtfinal;
	private String vhcNumeroSerie;
	private String vhcModelo;
	private String vhcColor;
	private Long vhcPromedioTanqueo;
	private Long vhcKilometrajeActual;
	private Long vhcAnofabricacion;
	private Long vhcMesfabricacion;
	private Set<VehiclesAssignation> vehiclesAssignations = new HashSet<VehiclesAssignation>(0);
	private Set<PoliciesVehicles> policiesVehicleses = new HashSet<PoliciesVehicles>(0);
	private Set<Accidents> accidentses = new HashSet<Accidents>(0);
	private Set<CostsCentersNewness> costsCentersNewnesses = new HashSet<CostsCentersNewness>(0);
	private Set<LocationsNewness> locationsNewnesses = new HashSet<LocationsNewness>(0);
	private Set<Requests> requestses = new HashSet<Requests>(0);
	private Set<Devolutions> devolutionses = new HashSet<Devolutions>(0);
	private Set<CostsCentersVehicles> costsCentersVehicleses = new HashSet<CostsCentersVehicles>(0);
	private Set<Collections> collectionses = new HashSet<Collections>(0);
	private Set<ClientsSalesVehicles> clientsSalesVehicleses = new HashSet<ClientsSalesVehicles>(0);

	// Constructors

	/** default constructor */
	public Vehicles() {
	}

	/** minimal constructor */
	public Vehicles(Long vhcCodigo, Lines lines, Locations locations, VehiclesStates vehiclesStates,
			TapestriesTypes tapestriesTypes, VehiclesTypes vehiclesTypes, TransmissionsTypes transmissionsTypes,
			TractionsTypes tractionsTypes, FuelsTypes fuelsTypes, Long vhcCapacidad, String vhcNumeroTl,
			String vhcNumReferCat, String vhcCilindraje, String vhcNumeroMotor, String vhcNumeroSerie, String vhcModelo,
			String vhcColor, Long vhcAnofabricacion, Long vhcMesfabricacion) {
		this.vhcCodigo = vhcCodigo;
		this.lines = lines;
		this.locations = locations;
		this.vehiclesStates = vehiclesStates;
		this.tapestriesTypes = tapestriesTypes;
		this.vehiclesTypes = vehiclesTypes;
		this.transmissionsTypes = transmissionsTypes;
		this.tractionsTypes = tractionsTypes;
		this.fuelsTypes = fuelsTypes;
		this.vhcCapacidad = vhcCapacidad;
		this.vhcNumeroTl = vhcNumeroTl;
		this.vhcNumReferCat = vhcNumReferCat;
		this.vhcCilindraje = vhcCilindraje;
		this.vhcNumeroMotor = vhcNumeroMotor;
		this.vhcNumeroSerie = vhcNumeroSerie;
		this.vhcModelo = vhcModelo;
		this.vhcColor = vhcColor;
		this.vhcAnofabricacion = vhcAnofabricacion;
		this.vhcMesfabricacion = vhcMesfabricacion;
	}

	/** full constructor */
	public Vehicles(Long vhcCodigo, RetirementsReasons retirementsReasons, Lines lines, InsurancesCar insurancesCar,
			Locations locations, VehiclesStates vehiclesStates, TapestriesTypes tapestriesTypes,
			VehiclesTypes vehiclesTypes, TransmissionsTypes transmissionsTypes, TractionsTypes tractionsTypes,
			FuelsTypes fuelsTypes, String vhcCiuAduan, String vhcUnidadTaquim, Long vhcValorAsignacion,
			String vhcCatalogado, String vhcPlacaDiplomatica, String vhcPlacaActivoFijo, String vhcEspecificTec,
			Date vhcFechaLevante, String vhcDocumentTrans, String vhcNumeroFactura, String vhcNumeroChasis,
			String vhcOrderCompra, String vhcProveedor, Long vhcCapacidad, Float vhcCapMaxTanq,
			String vhcValorComercial, Long vhcAnoValCom, Date vhcFechaProtocolo, String vhcNumeroManifiesto,
			Date vhcFechaManifiesto, String vhcNumDeclImpor, String vhcNumeroTl, String vhcRemplazaA,
			String vhcPlRemVehi, String vhcAtInicial, String vhcValorCif, String vhcVidaUtil, String vhcValorFob,
			String vhcCargosImport, String vhcObservaciones, String vhcDescripcion, Long vhcAno,
			String vhcNumeroLevante, String vhcNumReferCat, String vhcOdometro, String vhcCilindraje,
			String vhcNumeroMotor, String vhcAtfinal, String vhcNumeroSerie, String vhcModelo, String vhcColor,
			Long vhcPromedioTanqueo, Long vhcKilometrajeActual, Long vhcAnofabricacion, Long vhcMesfabricacion,
			Set<VehiclesAssignation> vehiclesAssignations, Set<PoliciesVehicles> policiesVehicleses,
			Set<Accidents> accidentses, Set<CostsCentersNewness> costsCentersNewnesses,
			Set<LocationsNewness> locationsNewnesses, Set<Requests> requestses, Set<Devolutions> devolutionses,
			Set<CostsCentersVehicles> costsCentersVehicleses, Set<Collections> collectionses,
			Set<ClientsSalesVehicles> clientsSalesVehicleses) {
		this.vhcCodigo = vhcCodigo;
		this.retirementsReasons = retirementsReasons;
		this.lines = lines;
		this.insurancesCar = insurancesCar;
		this.locations = locations;
		this.vehiclesStates = vehiclesStates;
		this.tapestriesTypes = tapestriesTypes;
		this.vehiclesTypes = vehiclesTypes;
		this.transmissionsTypes = transmissionsTypes;
		this.tractionsTypes = tractionsTypes;
		this.fuelsTypes = fuelsTypes;
		this.vhcCiuAduan = vhcCiuAduan;
		this.vhcUnidadTaquim = vhcUnidadTaquim;
		this.vhcValorAsignacion = vhcValorAsignacion;
		this.vhcCatalogado = vhcCatalogado;
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
		this.vhcPlacaActivoFijo = vhcPlacaActivoFijo;
		this.vhcEspecificTec = vhcEspecificTec;
		this.vhcFechaLevante = vhcFechaLevante;
		this.vhcDocumentTrans = vhcDocumentTrans;
		this.vhcNumeroFactura = vhcNumeroFactura;
		this.vhcNumeroChasis = vhcNumeroChasis;
		this.vhcOrderCompra = vhcOrderCompra;
		this.vhcProveedor = vhcProveedor;
		this.vhcCapacidad = vhcCapacidad;
		this.vhcCapMaxTanq = vhcCapMaxTanq;
		this.vhcValorComercial = vhcValorComercial;
		this.vhcAnoValCom = vhcAnoValCom;
		this.vhcFechaProtocolo = vhcFechaProtocolo;
		this.vhcNumeroManifiesto = vhcNumeroManifiesto;
		this.vhcFechaManifiesto = vhcFechaManifiesto;
		this.vhcNumDeclImpor = vhcNumDeclImpor;
		this.vhcNumeroTl = vhcNumeroTl;
		this.vhcRemplazaA = vhcRemplazaA;
		this.vhcPlRemVehi = vhcPlRemVehi;
		this.vhcAtInicial = vhcAtInicial;
		this.vhcValorCif = vhcValorCif;
		this.vhcVidaUtil = vhcVidaUtil;
		this.vhcValorFob = vhcValorFob;
		this.vhcCargosImport = vhcCargosImport;
		this.vhcObservaciones = vhcObservaciones;
		this.vhcDescripcion = vhcDescripcion;
		this.vhcAno = vhcAno;
		this.vhcNumeroLevante = vhcNumeroLevante;
		this.vhcNumReferCat = vhcNumReferCat;
		this.vhcOdometro = vhcOdometro;
		this.vhcCilindraje = vhcCilindraje;
		this.vhcNumeroMotor = vhcNumeroMotor;
		this.vhcAtfinal = vhcAtfinal;
		this.vhcNumeroSerie = vhcNumeroSerie;
		this.vhcModelo = vhcModelo;
		this.vhcColor = vhcColor;
		this.vhcPromedioTanqueo = vhcPromedioTanqueo;
		this.vhcKilometrajeActual = vhcKilometrajeActual;
		this.vhcAnofabricacion = vhcAnofabricacion;
		this.vhcMesfabricacion = vhcMesfabricacion;
		this.vehiclesAssignations = vehiclesAssignations;
		this.policiesVehicleses = policiesVehicleses;
		this.accidentses = accidentses;
		this.costsCentersNewnesses = costsCentersNewnesses;
		this.locationsNewnesses = locationsNewnesses;
		this.requestses = requestses;
		this.devolutionses = devolutionses;
		this.costsCentersVehicleses = costsCentersVehicleses;
		this.collectionses = collectionses;
		this.clientsSalesVehicleses = clientsSalesVehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="VEHICLES_GEN", sequenceName="SQ_VEHICLES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VEHICLES_GEN")
	@Column(name = "VHC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcCodigo() {
		return this.vhcCodigo;
	}

	public void setVhcCodigo(Long vhcCodigo) {
		this.vhcCodigo = vhcCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RER_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public RetirementsReasons getRetirementsReasons() {
		return this.retirementsReasons;
	}

	public void setRetirementsReasons(RetirementsReasons retirementsReasons) {
		this.retirementsReasons = retirementsReasons;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LNS_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Lines getLines() {
		return this.lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "INS_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public InsurancesCar getInsurancesCar() {
		return this.insurancesCar;
	}

	public void setInsurancesCar(InsurancesCar insurancesCar) {
		this.insurancesCar = insurancesCar;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LCN_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Locations getLocations() {
		return this.locations;
	}

	public void setLocations(Locations locations) {
		this.locations = locations;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public VehiclesStates getVehiclesStates() {
		return this.vehiclesStates;
	}

	public void setVehiclesStates(VehiclesStates vehiclesStates) {
		this.vehiclesStates = vehiclesStates;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TPTPC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public TapestriesTypes getTapestriesTypes() {
		return this.tapestriesTypes;
	}

	public void setTapestriesTypes(TapestriesTypes tapestriesTypes) {
		this.tapestriesTypes = tapestriesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public VehiclesTypes getVehiclesTypes() {
		return this.vehiclesTypes;
	}

	public void setVehiclesTypes(VehiclesTypes vehiclesTypes) {
		this.vehiclesTypes = vehiclesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TNT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public TransmissionsTypes getTransmissionsTypes() {
		return this.transmissionsTypes;
	}

	public void setTransmissionsTypes(TransmissionsTypes transmissionsTypes) {
		this.transmissionsTypes = transmissionsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TCT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public TractionsTypes getTractionsTypes() {
		return this.tractionsTypes;
	}

	public void setTractionsTypes(TractionsTypes tractionsTypes) {
		this.tractionsTypes = tractionsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FUT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public FuelsTypes getFuelsTypes() {
		return this.fuelsTypes;
	}

	public void setFuelsTypes(FuelsTypes fuelsTypes) {
		this.fuelsTypes = fuelsTypes;
	}

	@Column(name = "VHC_CIU_ADUAN", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcCiuAduan() {
		return this.vhcCiuAduan;
	}

	public void setVhcCiuAduan(String vhcCiuAduan) {
		this.vhcCiuAduan = vhcCiuAduan;
	}

	@Column(name = "VHC_UNIDAD_TAQUIM", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getVhcUnidadTaquim() {
		return this.vhcUnidadTaquim;
	}

	public void setVhcUnidadTaquim(String vhcUnidadTaquim) {
		this.vhcUnidadTaquim = vhcUnidadTaquim;
	}

	@Column(name = "VHC_VALOR_ASIGNACION", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcValorAsignacion() {
		return this.vhcValorAsignacion;
	}

	public void setVhcValorAsignacion(Long vhcValorAsignacion) {
		this.vhcValorAsignacion = vhcValorAsignacion;
	}

	@Column(name = "VHC_CATALOGADO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcCatalogado() {
		return this.vhcCatalogado;
	}

	public void setVhcCatalogado(String vhcCatalogado) {
		this.vhcCatalogado = vhcCatalogado;
	}

	@Column(name = "VHC_PLACA_DIPLOMATICA", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getVhcPlacaDiplomatica() {
		return this.vhcPlacaDiplomatica;
	}

	public void setVhcPlacaDiplomatica(String vhcPlacaDiplomatica) {
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
	}

	@Column(name = "VHC_PLACA_ACTIVO_FIJO", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getVhcPlacaActivoFijo() {
		return this.vhcPlacaActivoFijo;
	}

	public void setVhcPlacaActivoFijo(String vhcPlacaActivoFijo) {
		this.vhcPlacaActivoFijo = vhcPlacaActivoFijo;
	}

	@Column(name = "VHC_ESPECIFIC_TEC", unique = false, nullable = true, insertable = true, updatable = true)
	public String getVhcEspecificTec() {
		return this.vhcEspecificTec;
	}

	public void setVhcEspecificTec(String vhcEspecificTec) {
		this.vhcEspecificTec = vhcEspecificTec;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHC_FECHA_LEVANTE", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhcFechaLevante() {
		return this.vhcFechaLevante;
	}

	public void setVhcFechaLevante(Date vhcFechaLevante) {
		this.vhcFechaLevante = vhcFechaLevante;
	}

	@Column(name = "VHC_DOCUMENT_TRANS", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getVhcDocumentTrans() {
		return this.vhcDocumentTrans;
	}

	public void setVhcDocumentTrans(String vhcDocumentTrans) {
		this.vhcDocumentTrans = vhcDocumentTrans;
	}

	@Column(name = "VHC_NUMERO_FACTURA", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getVhcNumeroFactura() {
		return this.vhcNumeroFactura;
	}

	public void setVhcNumeroFactura(String vhcNumeroFactura) {
		this.vhcNumeroFactura = vhcNumeroFactura;
	}

	@Column(name = "VHC_NUMERO_CHASIS", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getVhcNumeroChasis() {
		return this.vhcNumeroChasis;
	}

	public void setVhcNumeroChasis(String vhcNumeroChasis) {
		this.vhcNumeroChasis = vhcNumeroChasis;
	}

	@Column(name = "VHC_ORDER_COMPRA", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getVhcOrderCompra() {
		return this.vhcOrderCompra;
	}

	public void setVhcOrderCompra(String vhcOrderCompra) {
		this.vhcOrderCompra = vhcOrderCompra;
	}

	@Column(name = "VHC_PROVEEDOR", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getVhcProveedor() {
		return this.vhcProveedor;
	}

	public void setVhcProveedor(String vhcProveedor) {
		this.vhcProveedor = vhcProveedor;
	}

	@Column(name = "VHC_CAPACIDAD", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcCapacidad() {
		return this.vhcCapacidad;
	}

	public void setVhcCapacidad(Long vhcCapacidad) {
		this.vhcCapacidad = vhcCapacidad;
	}

	@Column(name = "VHC_CAP_MAX_TANQ", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getVhcCapMaxTanq() {
		return this.vhcCapMaxTanq;
	}

	public void setVhcCapMaxTanq(Float vhcCapMaxTanq) {
		this.vhcCapMaxTanq = vhcCapMaxTanq;
	}

	@Column(name = "VHC_VALOR_COMERCIAL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcValorComercial() {
		return this.vhcValorComercial;
	}

	public void setVhcValorComercial(String vhcValorComercial) {
		this.vhcValorComercial = vhcValorComercial;
	}

	@Column(name = "VHC_ANO_VAL_COM", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcAnoValCom() {
		return this.vhcAnoValCom;
	}

	public void setVhcAnoValCom(Long vhcAnoValCom) {
		this.vhcAnoValCom = vhcAnoValCom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHC_FECHA_PROTOCOLO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhcFechaProtocolo() {
		return this.vhcFechaProtocolo;
	}

	public void setVhcFechaProtocolo(Date vhcFechaProtocolo) {
		this.vhcFechaProtocolo = vhcFechaProtocolo;
	}

	@Column(name = "VHC_NUMERO_MANIFIESTO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcNumeroManifiesto() {
		return this.vhcNumeroManifiesto;
	}

	public void setVhcNumeroManifiesto(String vhcNumeroManifiesto) {
		this.vhcNumeroManifiesto = vhcNumeroManifiesto;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VHC_FECHA_MANIFIESTO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getVhcFechaManifiesto() {
		return this.vhcFechaManifiesto;
	}

	public void setVhcFechaManifiesto(Date vhcFechaManifiesto) {
		this.vhcFechaManifiesto = vhcFechaManifiesto;
	}

	@Column(name = "VHC_NUM_DECL_IMPOR", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getVhcNumDeclImpor() {
		return this.vhcNumDeclImpor;
	}

	public void setVhcNumDeclImpor(String vhcNumDeclImpor) {
		this.vhcNumDeclImpor = vhcNumDeclImpor;
	}

	@Column(name = "VHC_NUMERO_TL", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getVhcNumeroTl() {
		return this.vhcNumeroTl;
	}

	public void setVhcNumeroTl(String vhcNumeroTl) {
		this.vhcNumeroTl = vhcNumeroTl;
	}

	@Column(name = "VHC_REMPLAZA_A", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcRemplazaA() {
		return this.vhcRemplazaA;
	}

	public void setVhcRemplazaA(String vhcRemplazaA) {
		this.vhcRemplazaA = vhcRemplazaA;
	}

	@Column(name = "VHC_PL_REM_VEHI", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getVhcPlRemVehi() {
		return this.vhcPlRemVehi;
	}

	public void setVhcPlRemVehi(String vhcPlRemVehi) {
		this.vhcPlRemVehi = vhcPlRemVehi;
	}

	@Column(name = "VHC_AT_INICIAL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcAtInicial() {
		return this.vhcAtInicial;
	}

	public void setVhcAtInicial(String vhcAtInicial) {
		this.vhcAtInicial = vhcAtInicial;
	}

	@Column(name = "VHC_VALOR_CIF", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcValorCif() {
		return this.vhcValorCif;
	}

	public void setVhcValorCif(String vhcValorCif) {
		this.vhcValorCif = vhcValorCif;
	}

	@Column(name = "VHC_VIDA_UTIL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcVidaUtil() {
		return this.vhcVidaUtil;
	}

	public void setVhcVidaUtil(String vhcVidaUtil) {
		this.vhcVidaUtil = vhcVidaUtil;
	}

	@Column(name = "VHC_VALOR_FOB", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcValorFob() {
		return this.vhcValorFob;
	}

	public void setVhcValorFob(String vhcValorFob) {
		this.vhcValorFob = vhcValorFob;
	}

	@Column(name = "VHC_CARGOS_IMPORT", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcCargosImport() {
		return this.vhcCargosImport;
	}

	public void setVhcCargosImport(String vhcCargosImport) {
		this.vhcCargosImport = vhcCargosImport;
	}

	@Column(name = "VHC_OBSERVACIONES", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getVhcObservaciones() {
		return this.vhcObservaciones;
	}

	public void setVhcObservaciones(String vhcObservaciones) {
		this.vhcObservaciones = vhcObservaciones;
	}

	@Column(name = "VHC_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getVhcDescripcion() {
		return this.vhcDescripcion;
	}

	public void setVhcDescripcion(String vhcDescripcion) {
		this.vhcDescripcion = vhcDescripcion;
	}

	@Column(name = "VHC_ANO", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcAno() {
		return this.vhcAno;
	}

	public void setVhcAno(Long vhcAno) {
		this.vhcAno = vhcAno;
	}

	@Column(name = "VHC_NUMERO_LEVANTE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getVhcNumeroLevante() {
		return this.vhcNumeroLevante;
	}

	public void setVhcNumeroLevante(String vhcNumeroLevante) {
		this.vhcNumeroLevante = vhcNumeroLevante;
	}

	@Column(name = "VHC_NUM_REFER_CAT", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getVhcNumReferCat() {
		return this.vhcNumReferCat;
	}

	public void setVhcNumReferCat(String vhcNumReferCat) {
		this.vhcNumReferCat = vhcNumReferCat;
	}

	@Column(name = "VHC_ODOMETRO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getVhcOdometro() {
		return this.vhcOdometro;
	}

	public void setVhcOdometro(String vhcOdometro) {
		this.vhcOdometro = vhcOdometro;
	}

	@Column(name = "VHC_CILINDRAJE", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getVhcCilindraje() {
		return this.vhcCilindraje;
	}

	public void setVhcCilindraje(String vhcCilindraje) {
		this.vhcCilindraje = vhcCilindraje;
	}

	@Column(name = "VHC_NUMERO_MOTOR", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getVhcNumeroMotor() {
		return this.vhcNumeroMotor;
	}

	public void setVhcNumeroMotor(String vhcNumeroMotor) {
		this.vhcNumeroMotor = vhcNumeroMotor;
	}

	@Column(name = "VHC_ATFINAL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getVhcAtfinal() {
		return this.vhcAtfinal;
	}

	public void setVhcAtfinal(String vhcAtfinal) {
		this.vhcAtfinal = vhcAtfinal;
	}

	@Column(name = "VHC_NUMERO_SERIE", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getVhcNumeroSerie() {
		return this.vhcNumeroSerie;
	}

	public void setVhcNumeroSerie(String vhcNumeroSerie) {
		this.vhcNumeroSerie = vhcNumeroSerie;
	}

	@Column(name = "VHC_MODELO", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getVhcModelo() {
		return this.vhcModelo;
	}

	public void setVhcModelo(String vhcModelo) {
		this.vhcModelo = vhcModelo;
	}

	@Column(name = "VHC_COLOR", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getVhcColor() {
		return this.vhcColor;
	}

	public void setVhcColor(String vhcColor) {
		this.vhcColor = vhcColor;
	}

	@Column(name = "VHC_PROMEDIO_TANQUEO", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcPromedioTanqueo() {
		return this.vhcPromedioTanqueo;
	}

	public void setVhcPromedioTanqueo(Long vhcPromedioTanqueo) {
		this.vhcPromedioTanqueo = vhcPromedioTanqueo;
	}

	@Column(name = "VHC_KILOMETRAJE_ACTUAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcKilometrajeActual() {
		return this.vhcKilometrajeActual;
	}

	public void setVhcKilometrajeActual(Long vhcKilometrajeActual) {
		this.vhcKilometrajeActual = vhcKilometrajeActual;
	}

	@Column(name = "VHC_ANOFABRICACION", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcAnofabricacion() {
		return this.vhcAnofabricacion;
	}

	public void setVhcAnofabricacion(Long vhcAnofabricacion) {
		this.vhcAnofabricacion = vhcAnofabricacion;
	}

	@Column(name = "VHC_MESFABRICACION", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcMesfabricacion() {
		return this.vhcMesfabricacion;
	}

	public void setVhcMesfabricacion(Long vhcMesfabricacion) {
		this.vhcMesfabricacion = vhcMesfabricacion;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<VehiclesAssignation> getVehiclesAssignations() {
		return this.vehiclesAssignations;
	}

	public void setVehiclesAssignations(Set<VehiclesAssignation> vehiclesAssignations) {
		this.vehiclesAssignations = vehiclesAssignations;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<PoliciesVehicles> getPoliciesVehicleses() {
		return this.policiesVehicleses;
	}

	public void setPoliciesVehicleses(Set<PoliciesVehicles> policiesVehicleses) {
		this.policiesVehicleses = policiesVehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<Accidents> getAccidentses() {
		return this.accidentses;
	}

	public void setAccidentses(Set<Accidents> accidentses) {
		this.accidentses = accidentses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<CostsCentersNewness> getCostsCentersNewnesses() {
		return this.costsCentersNewnesses;
	}

	public void setCostsCentersNewnesses(Set<CostsCentersNewness> costsCentersNewnesses) {
		this.costsCentersNewnesses = costsCentersNewnesses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<LocationsNewness> getLocationsNewnesses() {
		return this.locationsNewnesses;
	}

	public void setLocationsNewnesses(Set<LocationsNewness> locationsNewnesses) {
		this.locationsNewnesses = locationsNewnesses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<Requests> getRequestses() {
		return this.requestses;
	}

	public void setRequestses(Set<Requests> requestses) {
		this.requestses = requestses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<Devolutions> getDevolutionses() {
		return this.devolutionses;
	}

	public void setDevolutionses(Set<Devolutions> devolutionses) {
		this.devolutionses = devolutionses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<CostsCentersVehicles> getCostsCentersVehicleses() {
		return this.costsCentersVehicleses;
	}

	public void setCostsCentersVehicleses(Set<CostsCentersVehicles> costsCentersVehicleses) {
		this.costsCentersVehicleses = costsCentersVehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<Collections> getCollectionses() {
		return this.collectionses;
	}

	public void setCollectionses(Set<Collections> collectionses) {
		this.collectionses = collectionses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "vehicles")
	public Set<ClientsSalesVehicles> getClientsSalesVehicleses() {
		return this.clientsSalesVehicleses;
	}

	public void setClientsSalesVehicleses(Set<ClientsSalesVehicles> clientsSalesVehicleses) {
		this.clientsSalesVehicleses = clientsSalesVehicleses;
	}

}