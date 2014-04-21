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
 * Accidents entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACCIDENTS", schema = "", uniqueConstraints = {})
public class Accidents implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long accCodigo;
	private Cities cities;
	private AccidentsResults accidentsResults;
	private Acts acts;
	private LegateesTypes legateesTypes;
	private AccidentsStates accidentsStates;
	private Responsibility responsibility;
	private Vehicles vehicles;
	private Severity severity;
	private String accSancInterActa;
	private String accAprobadoActa;
	private Date accFecAprobActa;
	private String accOrdenTrabajoActa;
	private String accReclamo;
	private Long accTotalTestigos;
	private String accDescripcion;
	private String accObservaciones;
	private String accRecomendaciones;
	private Date accFechaAccidente;
	private String accSitioAccidente;
	private String accUso;
	private Long accNumeroSiniestro;
	private String accTotalMuertos;
	private String accTotalHeridos;
	private Long accVehiculosInvolucrados;
	private String accIntervinoTransito;
	private String accCedulaConduc;
	private String accNombreConduct;
	private String accCargoConduct;
	private String accCodCargoAcc;
	private Float accValorDano;
	private Float accDeduciblesPesos;
	private Float accDeduciblesDolar;
	private String accDeducible;
	private String accCargoDeducible;
	private String accNombreAsignatario;
	private String accEmailConductor;
	private String accHora;
	private Set<Witnesses> witnesseses = new HashSet<Witnesses>(0);
	private Set<InvolvedVehicles> involvedVehicleses = new HashSet<InvolvedVehicles>(
			0);
	private Set<AccidentsFiles> accidentsFileses = new HashSet<AccidentsFiles>(
			0);
	private Set<ActsAccidents> actsAccidentses = new HashSet<ActsAccidents>(0);

	// Constructors

	/** default constructor */
	public Accidents() {
	}

	/** minimal constructor */
	public Accidents(Long accCodigo, Cities cities,
			AccidentsResults accidentsResults, AccidentsStates accidentsStates,
			Vehicles vehicles, Date accFechaAccidente,
			String accSitioAccidente, String accNombreConduct,
			String accDeducible, String accCargoDeducible,
			String accEmailConductor, String accHora) {
		this.accCodigo = accCodigo;
		this.cities = cities;
		this.accidentsResults = accidentsResults;
		this.accidentsStates = accidentsStates;
		this.vehicles = vehicles;
		this.accFechaAccidente = accFechaAccidente;
		this.accSitioAccidente = accSitioAccidente;
		this.accNombreConduct = accNombreConduct;
		this.accDeducible = accDeducible;
		this.accCargoDeducible = accCargoDeducible;
		this.accEmailConductor = accEmailConductor;
		this.accHora = accHora;
	}

	/** full constructor */
	public Accidents(Long accCodigo, Cities cities,
			AccidentsResults accidentsResults, Acts acts,
			LegateesTypes legateesTypes, AccidentsStates accidentsStates,
			Responsibility responsibility, Vehicles vehicles,
			Severity severity, String accSancInterActa, String accAprobadoActa,
			Date accFecAprobActa, String accOrdenTrabajoActa,
			String accReclamo, Long accTotalTestigos, String accDescripcion,
			String accObservaciones, String accRecomendaciones,
			Date accFechaAccidente, String accSitioAccidente, String accUso,
			Long accNumeroSiniestro, String accTotalMuertos,
			String accTotalHeridos, Long accVehiculosInvolucrados,
			String accIntervinoTransito, String accCedulaConduc,
			String accNombreConduct, String accCargoConduct,
			String accCodCargoAcc, Float accValorDano,
			Float accDeduciblesPesos, Float accDeduciblesDolar,
			String accDeducible, String accCargoDeducible,
			String accNombreAsignatario, String accEmailConductor,
			String accHora, Set<Witnesses> witnesseses,
			Set<InvolvedVehicles> involvedVehicleses,
			Set<AccidentsFiles> accidentsFileses) {
		this.accCodigo = accCodigo;
		this.cities = cities;
		this.accidentsResults = accidentsResults;
		this.acts = acts;
		this.legateesTypes = legateesTypes;
		this.accidentsStates = accidentsStates;
		this.responsibility = responsibility;
		this.vehicles = vehicles;
		this.severity = severity;
		this.accSancInterActa = accSancInterActa;
		this.accAprobadoActa = accAprobadoActa;
		this.accFecAprobActa = accFecAprobActa;
		this.accOrdenTrabajoActa = accOrdenTrabajoActa;
		this.accReclamo = accReclamo;
		this.accTotalTestigos = accTotalTestigos;
		this.accDescripcion = accDescripcion;
		this.accObservaciones = accObservaciones;
		this.accRecomendaciones = accRecomendaciones;
		this.accFechaAccidente = accFechaAccidente;
		this.accSitioAccidente = accSitioAccidente;
		this.accUso = accUso;
		this.accNumeroSiniestro = accNumeroSiniestro;
		this.accTotalMuertos = accTotalMuertos;
		this.accTotalHeridos = accTotalHeridos;
		this.accVehiculosInvolucrados = accVehiculosInvolucrados;
		this.accIntervinoTransito = accIntervinoTransito;
		this.accCedulaConduc = accCedulaConduc;
		this.accNombreConduct = accNombreConduct;
		this.accCargoConduct = accCargoConduct;
		this.accCodCargoAcc = accCodCargoAcc;
		this.accValorDano = accValorDano;
		this.accDeduciblesPesos = accDeduciblesPesos;
		this.accDeduciblesDolar = accDeduciblesDolar;
		this.accDeducible = accDeducible;
		this.accCargoDeducible = accCargoDeducible;
		this.accNombreAsignatario = accNombreAsignatario;
		this.witnesseses = witnesseses;
		this.involvedVehicleses = involvedVehicleses;
		this.accidentsFileses = accidentsFileses;
		this.accEmailConductor = accEmailConductor;
		this.accHora = accHora;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "ACCIDENTS_GEN", sequenceName = "SQ_ACCIDENTS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCIDENTS_GEN")
	@Column(name = "ACC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAccCodigo() {
		return this.accCodigo;
	}

	public void setAccCodigo(Long accCodigo) {
		this.accCodigo = accCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTS_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Cities getCities() {
		return this.cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACL_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public AccidentsResults getAccidentsResults() {
		return this.accidentsResults;
	}

	public void setAccidentsResults(AccidentsResults accidentsResults) {
		this.accidentsResults = accidentsResults;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Acts getActs() {
		return this.acts;
	}

	public void setActs(Acts acts) {
		this.acts = acts;
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
	@JoinColumn(name = "ACS_CODE", unique = false, nullable = false, insertable = true, updatable = true)
	public AccidentsStates getAccidentsStates() {
		return this.accidentsStates;
	}

	public void setAccidentsStates(AccidentsStates accidentsStates) {
		this.accidentsStates = accidentsStates;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RES_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Responsibility getResponsibility() {
		return this.responsibility;
	}

	public void setResponsibility(Responsibility responsibility) {
		this.responsibility = responsibility;
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
	@JoinColumn(name = "SEV_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Severity getSeverity() {
		return this.severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity;
	}

	@Column(name = "ACC_SANC_INTER_ACTA", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public String getAccSancInterActa() {
		return this.accSancInterActa;
	}

	public void setAccSancInterActa(String accSancInterActa) {
		this.accSancInterActa = accSancInterActa;
	}

	@Column(name = "ACC_APROBADO_ACTA", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccAprobadoActa() {
		return this.accAprobadoActa;
	}

	public void setAccAprobadoActa(String accAprobadoActa) {
		this.accAprobadoActa = accAprobadoActa;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACC_FEC_APROB_ACTA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getAccFecAprobActa() {
		return this.accFecAprobActa;
	}

	public void setAccFecAprobActa(Date accFecAprobActa) {
		this.accFecAprobActa = accFecAprobActa;
	}

	@Column(name = "ACC_ORDEN_TRABAJO_ACTA", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getAccOrdenTrabajoActa() {
		return this.accOrdenTrabajoActa;
	}

	public void setAccOrdenTrabajoActa(String accOrdenTrabajoActa) {
		this.accOrdenTrabajoActa = accOrdenTrabajoActa;
	}

	@Column(name = "ACC_RECLAMO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccReclamo() {
		return this.accReclamo;
	}

	public void setAccReclamo(String accReclamo) {
		this.accReclamo = accReclamo;
	}

	@Column(name = "ACC_TOTAL_TESTIGOS", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAccTotalTestigos() {
		return this.accTotalTestigos;
	}

	public void setAccTotalTestigos(Long accTotalTestigos) {
		this.accTotalTestigos = accTotalTestigos;
	}

	@Column(name = "ACC_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public String getAccDescripcion() {
		return this.accDescripcion;
	}

	public void setAccDescripcion(String accDescripcion) {
		this.accDescripcion = accDescripcion;
	}

	@Column(name = "ACC_OBSERVACIONES", unique = false, nullable = true, insertable = true, updatable = true, length = 2000)
	public String getAccObservaciones() {
		return this.accObservaciones;
	}

	public void setAccObservaciones(String accObservaciones) {
		this.accObservaciones = accObservaciones;
	}

	@Column(name = "ACC_RECOMENDACIONES", unique = false, nullable = true, insertable = true, updatable = true, length = 250)
	public String getAccRecomendaciones() {
		return this.accRecomendaciones;
	}

	public void setAccRecomendaciones(String accRecomendaciones) {
		this.accRecomendaciones = accRecomendaciones;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACC_FECHA_ACCIDENTE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getAccFechaAccidente() {
		return this.accFechaAccidente;
	}

	public void setAccFechaAccidente(Date accFechaAccidente) {
		this.accFechaAccidente = accFechaAccidente;
	}

	@Column(name = "ACC_SITIO_ACCIDENTE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getAccSitioAccidente() {
		return this.accSitioAccidente;
	}

	public void setAccSitioAccidente(String accSitioAccidente) {
		this.accSitioAccidente = accSitioAccidente;
	}

	@Column(name = "ACC_USO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccUso() {
		return this.accUso;
	}

	public void setAccUso(String accUso) {
		this.accUso = accUso;
	}

	@Column(name = "ACC_NUMERO_SINIESTRO", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAccNumeroSiniestro() {
		return this.accNumeroSiniestro;
	}

	public void setAccNumeroSiniestro(Long accNumeroSiniestro) {
		this.accNumeroSiniestro = accNumeroSiniestro;
	}

	@Column(name = "ACC_TOTAL_MUERTOS", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getAccTotalMuertos() {
		return this.accTotalMuertos;
	}

	public void setAccTotalMuertos(String accTotalMuertos) {
		this.accTotalMuertos = accTotalMuertos;
	}

	@Column(name = "ACC_TOTAL_HERIDOS", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccTotalHeridos() {
		return this.accTotalHeridos;
	}

	public void setAccTotalHeridos(String accTotalHeridos) {
		this.accTotalHeridos = accTotalHeridos;
	}

	@Column(name = "ACC_VEHICULOS_INVOLUCRADOS", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAccVehiculosInvolucrados() {
		return this.accVehiculosInvolucrados;
	}

	public void setAccVehiculosInvolucrados(Long accVehiculosInvolucrados) {
		this.accVehiculosInvolucrados = accVehiculosInvolucrados;
	}

	@Column(name = "ACC_INTERVINO_TRANSITO", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getAccIntervinoTransito() {
		return this.accIntervinoTransito;
	}

	public void setAccIntervinoTransito(String accIntervinoTransito) {
		this.accIntervinoTransito = accIntervinoTransito;
	}

	@Column(name = "ACC_CEDULA_CONDUC", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccCedulaConduc() {
		return this.accCedulaConduc;
	}

	public void setAccCedulaConduc(String accCedulaConduc) {
		this.accCedulaConduc = accCedulaConduc;
	}

	@Column(name = "ACC_NOMBRE_CONDUCT", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getAccNombreConduct() {
		return this.accNombreConduct;
	}

	public void setAccNombreConduct(String accNombreConduct) {
		this.accNombreConduct = accNombreConduct;
	}

	@Column(name = "ACC_CARGO_CONDUCT", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccCargoConduct() {
		return this.accCargoConduct;
	}

	public void setAccCargoConduct(String accCargoConduct) {
		this.accCargoConduct = accCargoConduct;
	}

	@Column(name = "ACC_COD_CARGO_ACC", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAccCodCargoAcc() {
		return this.accCodCargoAcc;
	}

	public void setAccCodCargoAcc(String accCodCargoAcc) {
		this.accCodCargoAcc = accCodCargoAcc;
	}

	@Column(name = "ACC_VALOR_DANO", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getAccValorDano() {
		return this.accValorDano;
	}

	public void setAccValorDano(Float accValorDano) {
		this.accValorDano = accValorDano;
	}

	@Column(name = "ACC_DEDUCIBLES_PESOS", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getAccDeduciblesPesos() {
		return this.accDeduciblesPesos;
	}

	public void setAccDeduciblesPesos(Float accDeduciblesPesos) {
		this.accDeduciblesPesos = accDeduciblesPesos;
	}

	@Column(name = "ACC_DEDUCIBLES_DOLAR", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getAccDeduciblesDolar() {
		return this.accDeduciblesDolar;
	}

	public void setAccDeduciblesDolar(Float accDeduciblesDolar) {
		this.accDeduciblesDolar = accDeduciblesDolar;
	}

	@Column(name = "ACC_DEDUCIBLE", unique = false, nullable = true, insertable = true, updatable = true, length = 38)
	public String getAccDeducible() {
		return this.accDeducible;
	}

	public void setAccDeducible(String accDeducible) {
		this.accDeducible = accDeducible;
	}

	@Column(name = "ACC_CARGO_DEDUCIBLE", unique = false, nullable = false, insertable = true, updatable = true, length = 38)
	public String getAccCargoDeducible() {
		return this.accCargoDeducible;
	}

	public void setAccCargoDeducible(String accCargoDeducible) {
		this.accCargoDeducible = accCargoDeducible;
	}

	@Column(name = "ACC_NOMBRE_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAccNombreAsignatario() {
		return this.accNombreAsignatario;
	}

	public void setAccNombreAsignatario(String accNombreAsignatario) {
		this.accNombreAsignatario = accNombreAsignatario;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "accidents")
	public Set<Witnesses> getWitnesseses() {
		return this.witnesseses;
	}

	public void setWitnesseses(Set<Witnesses> witnesseses) {
		this.witnesseses = witnesseses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "accidents")
	public Set<InvolvedVehicles> getInvolvedVehicleses() {
		return this.involvedVehicleses;
	}

	public void setInvolvedVehicleses(Set<InvolvedVehicles> involvedVehicleses) {
		this.involvedVehicleses = involvedVehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "accidents")
	public Set<AccidentsFiles> getAccidentsFileses() {
		return this.accidentsFileses;
	}

	public void setAccidentsFileses(Set<AccidentsFiles> accidentsFileses) {
		this.accidentsFileses = accidentsFileses;
	}

	@Column(name = "ACC_EMAIL_CONDUCTOR", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getAccEmailConductor() {
		return accEmailConductor;
	}

	public void setAccEmailConductor(String accEmailConductor) {
		this.accEmailConductor = accEmailConductor;
	}

	@Column(name = "ACC_HORA", unique = false, nullable = true, insertable = true, updatable = true, length = 5)
	public String getAccHora() {
		return accHora;
	}

	public void setAccHora(String accHora) {
		this.accHora = accHora;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accidents")
	public Set<ActsAccidents> getActsAccidentses() {
		return this.actsAccidentses;
	}

	public void setActsAccidentses(Set<ActsAccidents> actsAccidentses) {
		this.actsAccidentses = actsAccidentses;
	}

}