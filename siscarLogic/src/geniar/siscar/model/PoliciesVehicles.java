package geniar.siscar.model;

// default package

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PoliciesVehicles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "POLICIES_VEHICLES", schema = "", uniqueConstraints = {})
public class PoliciesVehicles implements java.io.Serializable {

	// Fields

	private PoliciesVehiclesId id;
	private LegateesTypes legateesTypes;
	private Vehicles vehicles;
	private Policies policies;
	private MonthTransacType monthTransacType;
	private Long pvsEstado;
	private Date pvsFechaini;
	private Date pvsFechafin;
	private Float pvsValorPrima;
	private Float pvsValorIva;
	private Float pvsValorTotal;
	private Float pvsValorAsegurado;
	private String pvsCarnetAsignatario;
	private String pvsNumeroFactura;
	private PoliciesInvoice policiesInvoice;

	// Constructors

	/** default constructor */
	public PoliciesVehicles() {
	}

	/** minimal constructor */
	public PoliciesVehicles(PoliciesVehiclesId id, Vehicles vehicles,
			Policies policies, MonthTransacType monthTransacType,
			Long pvsEstado, Date pvsFechaini, Date pvsFechafin,
			Float pvsValorPrima, Float pvsValorIva, Float pvsValorTotal,
			Float pvsValorAsegurado) {
		this.id = id;
		this.vehicles = vehicles;
		this.policies = policies;
		this.monthTransacType = monthTransacType;
		this.pvsEstado = pvsEstado;
		this.pvsFechaini = pvsFechaini;
		this.pvsFechafin = pvsFechafin;
		this.pvsValorPrima = pvsValorPrima;
		this.pvsValorIva = pvsValorIva;
		this.pvsValorTotal = pvsValorTotal;
		this.pvsValorAsegurado = pvsValorAsegurado;
	}

	/** full constructor */
	public PoliciesVehicles(PoliciesVehiclesId id, LegateesTypes legateesTypes,
			Vehicles vehicles, Policies policies,
			MonthTransacType monthTransacType, Long pvsEstado,
			Date pvsFechaini, Date pvsFechafin, Float pvsValorPrima,
			Float pvsValorIva, Float pvsValorTotal, Float pvsValorAsegurado,
			String pvsCarnetAsignatario, String pvsNumeroFactura,
			PoliciesInvoice policiesInvoice) {
		this.id = id;
		this.legateesTypes = legateesTypes;
		this.vehicles = vehicles;
		this.policies = policies;
		this.monthTransacType = monthTransacType;
		this.pvsEstado = pvsEstado;
		this.pvsFechaini = pvsFechaini;
		this.pvsFechafin = pvsFechafin;
		this.pvsValorPrima = pvsValorPrima;
		this.pvsValorIva = pvsValorIva;
		this.pvsValorTotal = pvsValorTotal;
		this.pvsValorAsegurado = pvsValorAsegurado;
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
		this.pvsNumeroFactura = pvsNumeroFactura;
		this.policiesInvoice = policiesInvoice;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "vhcCodigo", column = @Column(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)),
			@AttributeOverride(name = "plsCodigo", column = @Column(name = "PLS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)) })
	public PoliciesVehiclesId getId() {
		return this.id;
	}

	public void setId(PoliciesVehiclesId id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "LGT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public LegateesTypes getLegateesTypes() {
		return this.legateesTypes;
	}

	public void setLegateesTypes(LegateesTypes legateesTypes) {
		this.legateesTypes = legateesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = false, insertable = false, updatable = false)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLS_CODIGO", unique = false, nullable = false, insertable = false, updatable = false)
	public Policies getPolicies() {
		return this.policies;
	}

	public void setPolicies(Policies policies) {
		this.policies = policies;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MTT_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public MonthTransacType getMonthTransacType() {
		return this.monthTransacType;
	}

	public void setMonthTransacType(MonthTransacType monthTransacType) {
		this.monthTransacType = monthTransacType;
	}

	@Column(name = "PVS_ESTADO", unique = false, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getPvsEstado() {
		return this.pvsEstado;
	}

	public void setPvsEstado(Long pvsEstado) {
		this.pvsEstado = pvsEstado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PVS_FECHAINI", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPvsFechaini() {
		return this.pvsFechaini;
	}

	public void setPvsFechaini(Date pvsFechaini) {
		this.pvsFechaini = pvsFechaini;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PVS_FECHAFIN", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPvsFechafin() {
		return this.pvsFechafin;
	}

	public void setPvsFechafin(Date pvsFechafin) {
		this.pvsFechafin = pvsFechafin;
	}

	@Column(name = "PVS_VALOR_PRIMA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPvsValorPrima() {
		return this.pvsValorPrima;
	}

	public void setPvsValorPrima(Float pvsValorPrima) {
		this.pvsValorPrima = pvsValorPrima;
	}

	@Column(name = "PVS_VALOR_IVA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPvsValorIva() {
		return this.pvsValorIva;
	}

	public void setPvsValorIva(Float pvsValorIva) {
		this.pvsValorIva = pvsValorIva;
	}

	@Column(name = "PVS_VALOR_TOTAL", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPvsValorTotal() {
		return this.pvsValorTotal;
	}

	public void setPvsValorTotal(Float pvsValorTotal) {
		this.pvsValorTotal = pvsValorTotal;
	}

	@Column(name = "PVS_VALOR_ASEGURADO", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPvsValorAsegurado() {
		return this.pvsValorAsegurado;
	}

	public void setPvsValorAsegurado(Float pvsValorAsegurado) {
		this.pvsValorAsegurado = pvsValorAsegurado;
	}

	@Column(name = "PVS_CARNET_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPvsCarnetAsignatario() {
		return this.pvsCarnetAsignatario;
	}

	public void setPvsCarnetAsignatario(String pvsCarnetAsignatario) {
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
	}

	/**
	 * @return the pvsNumeroFactura
	 */
	@Column(name = "PVS_NUMERO_FACTURA", unique = false, nullable = true, insertable = true, updatable = true, length = 38)
	public String getPvsNumeroFactura() {
		return pvsNumeroFactura;
	}

	/**
	 * @param pvsNumeroFactura
	 *            the pvsNumeroFactura to set
	 */
	public void setPvsNumeroFactura(String pvsNumeroFactura) {
		this.pvsNumeroFactura = pvsNumeroFactura;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PIN_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public PoliciesInvoice getPoliciesInvoice() {
		return policiesInvoice;
	}

	public void setPoliciesInvoice(PoliciesInvoice policiesInvoice) {
		this.policiesInvoice = policiesInvoice;
	}

}