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
 * Tariffs entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TARIFFS", uniqueConstraints = {})
public class Tariffs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long trfId;
	private Lines lines;
	private Locations locations;
	private VehiclesTypes vehiclesTypes;
	private TariffsTypes tariffsTypes;
	private MoneyType moneyType;
	private Zones zones;
	private TransmissionsTypes transmissionsTypes;
	private TractionsTypes tractionsTypes;
	private FuelsTypes fuelsTypes;
	private Float trfValor;
	private Float trfKmIncluido;
	private Float trfKmValorAdicional;
	private Date trfAñoVigencia;
	private Date trfFechaInicio;
	private Date trfFechaFin;
	private Set<TariffsLegateeTypes> tariffsLegateeTypeses = new HashSet<TariffsLegateeTypes>(0);

	// Constructors

	/** default constructor */
	public Tariffs() {
	}

	/** minimal constructor */
	public Tariffs(Long trfId, TariffsTypes tariffsTypes, Float trfValor) {
		this.trfId = trfId;
		this.tariffsTypes = tariffsTypes;
		this.trfValor = trfValor;
	}

	/** full constructor */
	public Tariffs(Long trfId, Lines lines, Locations locations, VehiclesTypes vehiclesTypes,
			TariffsTypes tariffsTypes, MoneyType moneyType, Zones zones, TransmissionsTypes transmissionsTypes,
			TractionsTypes tractionsTypes, FuelsTypes fuelsTypes, Float trfValor, Float trfKmIncluido,
			Float trfKmValorAdicional, Date trfAñoVigencia, Date trfFechaInicio, Date trfFechaFin,
			Set<TariffsLegateeTypes> tariffsLegateeTypeses) {
		this.trfId = trfId;
		this.lines = lines;
		this.locations = locations;
		this.vehiclesTypes = vehiclesTypes;
		this.tariffsTypes = tariffsTypes;
		this.moneyType = moneyType;
		this.zones = zones;
		this.transmissionsTypes = transmissionsTypes;
		this.tractionsTypes = tractionsTypes;
		this.fuelsTypes = fuelsTypes;
		this.trfValor = trfValor;
		this.trfKmIncluido = trfKmIncluido;
		this.trfKmValorAdicional = trfKmValorAdicional;
		this.trfAñoVigencia = trfAñoVigencia;
		this.trfFechaInicio = trfFechaInicio;
		this.trfFechaFin = trfFechaFin;
		this.tariffsLegateeTypeses = tariffsLegateeTypeses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="TARIFFS_GEN", sequenceName="SQ_TARIFF", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TARIFFS_GEN")
	@Column(name = "TRF_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getTrfId() {
		return this.trfId;
	}

	public void setTrfId(Long trfId) {
		this.trfId = trfId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LNS_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Lines getLines() {
		return this.lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LCN_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Locations getLocations() {
		return this.locations;
	}

	public void setLocations(Locations locations) {
		this.locations = locations;
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
	@JoinColumn(name = "TRT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public TariffsTypes getTariffsTypes() {
		return this.tariffsTypes;
	}

	public void setTariffsTypes(TariffsTypes tariffsTypes) {
		this.tariffsTypes = tariffsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MNEY_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public MoneyType getMoneyType() {
		return this.moneyType;
	}

	public void setMoneyType(MoneyType moneyType) {
		this.moneyType = moneyType;
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
	@JoinColumn(name = "TNT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public TransmissionsTypes getTransmissionsTypes() {
		return this.transmissionsTypes;
	}

	public void setTransmissionsTypes(TransmissionsTypes transmissionsTypes) {
		this.transmissionsTypes = transmissionsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TCT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public TractionsTypes getTractionsTypes() {
		return this.tractionsTypes;
	}

	public void setTractionsTypes(TractionsTypes tractionsTypes) {
		this.tractionsTypes = tractionsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FUT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public FuelsTypes getFuelsTypes() {
		return this.fuelsTypes;
	}

	public void setFuelsTypes(FuelsTypes fuelsTypes) {
		this.fuelsTypes = fuelsTypes;
	}

	@Column(name = "TRF_VALOR", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getTrfValor() {
		return this.trfValor;
	}

	public void setTrfValor(Float trfValor) {
		this.trfValor = trfValor;
	}

	@Column(name = "TRF_KM_INCLUIDO", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getTrfKmIncluido() {
		return this.trfKmIncluido;
	}

	public void setTrfKmIncluido(Float trfKmIncluido) {
		this.trfKmIncluido = trfKmIncluido;
	}

	@Column(name = "TRF_KM_VALOR_ADICIONAL", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getTrfKmValorAdicional() {
		return this.trfKmValorAdicional;
	}

	public void setTrfKmValorAdicional(Float trfKmValorAdicional) {
		this.trfKmValorAdicional = trfKmValorAdicional;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRF_AÑO_VIGENCIA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getTrfAñoVigencia() {
		return this.trfAñoVigencia;
	}

	public void setTrfAñoVigencia(Date trfAñoVigencia) {
		this.trfAñoVigencia = trfAñoVigencia;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRF_FECHA_INICIO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getTrfFechaInicio() {
		return this.trfFechaInicio;
	}

	public void setTrfFechaInicio(Date trfFechaInicio) {
		this.trfFechaInicio = trfFechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRF_FECHA_FIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getTrfFechaFin() {
		return this.trfFechaFin;
	}

	public void setTrfFechaFin(Date trfFechaFin) {
		this.trfFechaFin = trfFechaFin;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tariffs")
	public Set<TariffsLegateeTypes> getTariffsLegateeTypeses() {
		return this.tariffsLegateeTypeses;
	}

	public void setTariffsLegateeTypeses(Set<TariffsLegateeTypes> tariffsLegateeTypeses) {
		this.tariffsLegateeTypeses = tariffsLegateeTypeses;
	}

}