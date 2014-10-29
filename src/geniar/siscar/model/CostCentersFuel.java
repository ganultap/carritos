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
 * CostCentersFuel entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_CENTERS_FUEL", schema = "", uniqueConstraints = {})
public class CostCentersFuel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ccfId;
	private CostsCenters costsCenters;
	private CostCenterTypeFuel costCenterTypeFuel;
	private Float ccfValor;
	private Date ccfFechaInicio;
	private Date ccfFechaFin;
	private String ccfEstado;
	private String ccfPorcentaje;
	private VehiclesAssignation vehiclesAssignation;
	private Set<Prepaid> prepaids = new HashSet<Prepaid>(0);
	private Set<PrepaidConsumption> prepaidConsumptions = new HashSet<PrepaidConsumption>(
			0);

	// Constructors

	/** default constructor */
	public CostCentersFuel() {
	}

	/** minimal constructor */
	public CostCentersFuel(Long ccfId, CostsCenters costsCenters,
			CostCenterTypeFuel costCenterTypeFuel,
			VehiclesAssignation vehiclesAssignation, Float ccfValor,
			String ccfPorcentaje) {
		this.ccfId = ccfId;
		this.costsCenters = costsCenters;
		this.costCenterTypeFuel = costCenterTypeFuel;
		this.ccfValor = ccfValor;
		this.ccfPorcentaje = ccfPorcentaje;
		this.vehiclesAssignation = vehiclesAssignation;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "costCentersFuel")
	public Set<Prepaid> getPrepaids() {
		return this.prepaids;
	}

	public void setPrepaids(Set<Prepaid> prepaids) {
		this.prepaids = prepaids;
	}

	/** full constructor */
	public CostCentersFuel(Long ccfId, Prepaid prepaid,
			CostsCenters costsCenters, CostCenterTypeFuel costCenterTypeFuel,
			Float ccfValor, Date ccfFechaInicio, Date ccfFechaFin,
			String ccfPorcentaje, VehiclesAssignation vehiclesAssignation,
			Set<PrepaidConsumption> prepaidConsumptions, String ccfEstado,
			Set<Prepaid> prepaids) {
		this.ccfId = ccfId;
		this.costsCenters = costsCenters;
		this.costCenterTypeFuel = costCenterTypeFuel;
		this.ccfValor = ccfValor;
		this.ccfFechaInicio = ccfFechaInicio;
		this.ccfFechaFin = ccfFechaFin;
		this.ccfPorcentaje = ccfPorcentaje;
		this.prepaidConsumptions = prepaidConsumptions;
		this.vehiclesAssignation = vehiclesAssignation;
		this.ccfEstado = ccfEstado;
		this.prepaids = prepaids;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PREPAID_GEN", sequenceName = "SQ_COST_CENTER_FUEL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PREPAID_GEN")
	@Column(name = "CCF_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCcfId() {
		return this.ccfId;
	}

	public void setCcfId(Long ccfId) {
		this.ccfId = ccfId;
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
	@JoinColumn(name = "CCT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public CostCenterTypeFuel getCostCenterTypeFuel() {
		return this.costCenterTypeFuel;
	}

	public void setCostCenterTypeFuel(CostCenterTypeFuel costCenterTypeFuel) {
		this.costCenterTypeFuel = costCenterTypeFuel;
	}

	@Column(name = "CCF_VALOR", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getCcfValor() {
		return this.ccfValor;
	}

	public void setCcfValor(Float ccfValor) {
		this.ccfValor = ccfValor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CCF_FECHA_INICIO", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCcfFechaInicio() {
		return this.ccfFechaInicio;
	}

	public void setCcfFechaInicio(Date ccfFechaInicio) {
		this.ccfFechaInicio = ccfFechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CCF_FECHA_FIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCcfFechaFin() {
		return this.ccfFechaFin;
	}

	public void setCcfFechaFin(Date ccfFechaFin) {
		this.ccfFechaFin = ccfFechaFin;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "costCentersFuel")
	public Set<PrepaidConsumption> getPrepaidConsumptions() {
		return this.prepaidConsumptions;
	}

	public void setPrepaidConsumptions(
			Set<PrepaidConsumption> prepaidConsumptions) {
		this.prepaidConsumptions = prepaidConsumptions;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VHA_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public VehiclesAssignation getVehiclesAssignation() {
		return this.vehiclesAssignation;
	}

	public void setVehiclesAssignation(VehiclesAssignation vehiclesAssignation) {
		this.vehiclesAssignation = vehiclesAssignation;
	}

	@Column(name = "CCF_ESTADO", length = 30)
	public String getCcfEstado() {
		return this.ccfEstado;
	}

	public void setCcfEstado(String ccfEstado) {
		this.ccfEstado = ccfEstado;
	}

	@Column(name = "CCF_PORCENTAJE", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getCcfPorcentaje() {
		return ccfPorcentaje;
	}

	public void setCcfPorcentaje(String ccfPorcentaje) {
		this.ccfPorcentaje = ccfPorcentaje;
	}

}