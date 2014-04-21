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
 * Inconsistencies entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INCONSISTENCIES", schema = "", uniqueConstraints = {})
public class Inconsistencies implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long incId;
	private LegateesTypes legateesTypes;
	private InconsistenciesTypes inconsistenciesTypes;
	private PoliciesInvoice policiesInvoice;
	private MonthTransacType monthTransacType;
	private Long incEstado;
	private String incObservacion;
	private Date incFechaCargue;
	private String usrLogin;
	private String vhcPlaca;
	private Float incValorIva;
	private Float incValorPrima;
	private Float incValorTotal;
	private Float incValorAsegurado;
	private String pvsCarnetAsignatario;
	private Policies policies;

	// Constructors

	/** default constructor */
	public Inconsistencies() {
	}

	/** minimal constructor */
	public Inconsistencies(Long incId,
			InconsistenciesTypes inconsistenciesTypes,
			PoliciesInvoice policiesInvoice, MonthTransacType monthTransacType,
			Long incEstado, Date incFechaCargue, String usrLogin,
			String vhcPlaca, Float incValorIva, Float incValorPrima,
			Float incValorTotal, Float incValorAsegurado) {
		this.incId = incId;
		this.inconsistenciesTypes = inconsistenciesTypes;
		this.policiesInvoice = policiesInvoice;
		this.monthTransacType = monthTransacType;
		this.incEstado = incEstado;
		this.incFechaCargue = incFechaCargue;
		this.usrLogin = usrLogin;
		this.vhcPlaca = vhcPlaca;
		this.incValorIva = incValorIva;
		this.incValorPrima = incValorPrima;
		this.incValorTotal = incValorTotal;
		this.incValorAsegurado = incValorAsegurado;
	}

	/** full constructor */
	public Inconsistencies(Long incId, LegateesTypes legateesTypes,
			InconsistenciesTypes inconsistenciesTypes,
			PoliciesInvoice policiesInvoice, MonthTransacType monthTransacType,
			Long incEstado, String incObservacion, Date incFechaCargue,
			String usrLogin, String vhcPlaca, Float incValorIva,
			Float incValorPrima, Float incValorTotal, Float incValorAsegurado,
			String pvsCarnetAsignatario) {
		this.incId = incId;
		this.legateesTypes = legateesTypes;
		this.inconsistenciesTypes = inconsistenciesTypes;
		this.policiesInvoice = policiesInvoice;
		this.monthTransacType = monthTransacType;
		this.incEstado = incEstado;
		this.incObservacion = incObservacion;
		this.incFechaCargue = incFechaCargue;
		this.usrLogin = usrLogin;
		this.vhcPlaca = vhcPlaca;
		this.incValorIva = incValorIva;
		this.incValorPrima = incValorPrima;
		this.incValorTotal = incValorTotal;
		this.incValorAsegurado = incValorAsegurado;
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "INCONS_GEN", sequenceName = "SQ_INCONSISTENCIES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INCONS_GEN")
	@Column(name = "INC_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 18, scale = 0)
	public Long getIncId() {
		return this.incId;
	}

	public void setIncId(Long incId) {
		this.incId = incId;
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
	@JoinColumn(name = "ICT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public InconsistenciesTypes getInconsistenciesTypes() {
		return this.inconsistenciesTypes;
	}

	public void setInconsistenciesTypes(
			InconsistenciesTypes inconsistenciesTypes) {
		this.inconsistenciesTypes = inconsistenciesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PIN_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public PoliciesInvoice getPoliciesInvoice() {
		return this.policiesInvoice;
	}

	public void setPoliciesInvoice(PoliciesInvoice policiesInvoice) {
		this.policiesInvoice = policiesInvoice;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MTT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public MonthTransacType getMonthTransacType() {
		return this.monthTransacType;
	}

	public void setMonthTransacType(MonthTransacType monthTransacType) {
		this.monthTransacType = monthTransacType;
	}

	@Column(name = "INC_ESTADO", unique = false, nullable = false, insertable = true, updatable = true, precision = 2, scale = 0)
	public Long getIncEstado() {
		return this.incEstado;
	}

	public void setIncEstado(Long incEstado) {
		this.incEstado = incEstado;
	}

	@Column(name = "INC_OBSERVACION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getIncObservacion() {
		return this.incObservacion;
	}

	public void setIncObservacion(String incObservacion) {
		this.incObservacion = incObservacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "INC_FECHA_CARGUE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getIncFechaCargue() {
		return this.incFechaCargue;
	}

	public void setIncFechaCargue(Date incFechaCargue) {
		this.incFechaCargue = incFechaCargue;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Column(name = "VHC_PLACA", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getVhcPlaca() {
		return this.vhcPlaca;
	}

	public void setVhcPlaca(String vhcPlaca) {
		this.vhcPlaca = vhcPlaca;
	}

	@Column(name = "INC_VALOR_IVA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getIncValorIva() {
		return this.incValorIva;
	}

	public void setIncValorIva(Float incValorIva) {
		this.incValorIva = incValorIva;
	}

	@Column(name = "INC_VALOR_PRIMA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getIncValorPrima() {
		return this.incValorPrima;
	}

	public void setIncValorPrima(Float incValorPrima) {
		this.incValorPrima = incValorPrima;
	}

	@Column(name = "INC_VALOR_TOTAL", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getIncValorTotal() {
		return this.incValorTotal;
	}

	public void setIncValorTotal(Float incValorTotal) {
		this.incValorTotal = incValorTotal;
	}

	@Column(name = "INC_VALOR_ASEGURADO", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getIncValorAsegurado() {
		return this.incValorAsegurado;
	}

	public void setIncValorAsegurado(Float incValorAsegurado) {
		this.incValorAsegurado = incValorAsegurado;
	}

	@Column(name = "PVS_CARNET_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPvsCarnetAsignatario() {
		return this.pvsCarnetAsignatario;
	}

	public void setPvsCarnetAsignatario(String pvsCarnetAsignatario) {
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PLS_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Policies getPolicies() {
		return policies;
	}

	public void setPolicies(Policies policies) {
		this.policies = policies;
	}

}