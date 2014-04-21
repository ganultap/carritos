package geniar.siscar.model;

// default package

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
 * PoliciesVehiclesHistoric entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES_VEHICLES_HISTORIC", schema = "", uniqueConstraints = {})
public class PoliciesVehiclesHistoric implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pvhId;
	private LegateesTypes legateesTypes;
	private MonthTransacType monthTransacType;
	private Date pvhFecha;
	private String pinNumeroFactura;
	private String usrLogin;
	private Long vhcCodigo;
	private Long plsCodigo;
	private Long pvsEstado;
	private Date pvsFechaini;
	private Date pvsFechafin;
	private Float pvsValorIva;
	private Float pvsValorPrima;
	private Float pvsValorTotal;
	private Float pvsValorAsegurado;
	private String pvsCarnetAsignatario;

	// Constructors

	/** default constructor */
	public PoliciesVehiclesHistoric() {
	}

	/** minimal constructor */
	public PoliciesVehiclesHistoric(Long pvhId,
			MonthTransacType monthTransacType, Date pvhFecha,
			String pinNumeroFactura, String usrLogin, Long vhcCodigo,
			Long plsCodigo, Long pvsEstado, Date pvsFechaini, Date pvsFechafin,
			Float pvsValorIva, Float pvsValorPrima, Float pvsValorTotal,
			Float pvsValorAsegurado, String pvsCarnetAsignatario) {
		this.pvhId = pvhId;
		this.monthTransacType = monthTransacType;
		this.pvhFecha = pvhFecha;
		this.pinNumeroFactura = pinNumeroFactura;
		this.usrLogin = usrLogin;
		this.vhcCodigo = vhcCodigo;
		this.plsCodigo = plsCodigo;
		this.pvsEstado = pvsEstado;
		this.pvsFechaini = pvsFechaini;
		this.pvsFechafin = pvsFechafin;
		this.pvsValorIva = pvsValorIva;
		this.pvsValorPrima = pvsValorPrima;
		this.pvsValorTotal = pvsValorTotal;
		this.pvsValorAsegurado = pvsValorAsegurado;
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
	}

	/** full constructor */
	public PoliciesVehiclesHistoric(Long pvhId, LegateesTypes legateesTypes,
			MonthTransacType monthTransacType, Date pvhFecha,
			String pinNumeroFactura, String usrLogin, Long vhcCodigo,
			Long plsCodigo, Long pvsEstado, Date pvsFechaini, Date pvsFechafin,
			Float pvsValorIva, Float pvsValorPrima, Float pvsValorTotal,
			Float pvsValorAsegurado, String pvsCarnetAsignatario) {
		this.pvhId = pvhId;
		this.legateesTypes = legateesTypes;
		this.monthTransacType = monthTransacType;
		this.pvhFecha = pvhFecha;
		this.pinNumeroFactura = pinNumeroFactura;
		this.usrLogin = usrLogin;
		this.vhcCodigo = vhcCodigo;
		this.plsCodigo = plsCodigo;
		this.pvsEstado = pvsEstado;
		this.pvsFechaini = pvsFechaini;
		this.pvsFechafin = pvsFechafin;
		this.pvsValorIva = pvsValorIva;
		this.pvsValorPrima = pvsValorPrima;
		this.pvsValorTotal = pvsValorTotal;
		this.pvsValorAsegurado = pvsValorAsegurado;
		this.pvsCarnetAsignatario = pvsCarnetAsignatario;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "PVHIS_GEN", sequenceName = "SQ_POLICES_VHIS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PVHIS_GEN")
	@Column(name = "PVH_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPvhId() {
		return this.pvhId;
	}

	public void setPvhId(Long pvhId) {
		this.pvhId = pvhId;
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
	@JoinColumn(name = "MTT_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public MonthTransacType getMonthTransacType() {
		return this.monthTransacType;
	}

	public void setMonthTransacType(MonthTransacType monthTransacType) {
		this.monthTransacType = monthTransacType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PVH_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPvhFecha() {
		return this.pvhFecha;
	}

	public void setPvhFecha(Date pvhFecha) {
		this.pvhFecha = pvhFecha;
	}

	@Column(name = "PIN_NUMERO_FACTURA", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public String getPinNumeroFactura() {
		return this.pinNumeroFactura;
	}

	public void setPinNumeroFactura(String pinNumeroFactura) {
		this.pinNumeroFactura = pinNumeroFactura;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Column(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcCodigo() {
		return this.vhcCodigo;
	}

	public void setVhcCodigo(Long vhcCodigo) {
		this.vhcCodigo = vhcCodigo;
	}

	@Column(name = "PLS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPlsCodigo() {
		return this.plsCodigo;
	}

	public void setPlsCodigo(Long plsCodigo) {
		this.plsCodigo = plsCodigo;
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

	@Column(name = "PVS_VALOR_IVA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPvsValorIva() {
		return this.pvsValorIva;
	}

	public void setPvsValorIva(Float pvsValorIva) {
		this.pvsValorIva = pvsValorIva;
	}

	@Column(name = "PVS_VALOR_PRIMA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPvsValorPrima() {
		return this.pvsValorPrima;
	}

	public void setPvsValorPrima(Float pvsValorPrima) {
		this.pvsValorPrima = pvsValorPrima;
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

}