package geniar.siscar.model;
// default package

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PoliciesInvoiceHistoric entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES_INVOICE_HISTORIC", schema = "", uniqueConstraints = {})
public class PoliciesInvoiceHistoric implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pihId;
	private Date pihFecha;
	private String usrLogin;
	private String pinNumeroFactura;
	private Date pinFechaFactura;
	private String pinConcepto;
	private Long plsCodigo;
	private Long pinCargado;

	// Constructors

	/** default constructor */
	public PoliciesInvoiceHistoric() {
	}

	/** full constructor */
	public PoliciesInvoiceHistoric(Long pihId, Date pihFecha, String usrLogin,
			String pinNumeroFactura, Date pinFechaFactura, String pinConcepto,
			Long plsCodigo, Long pinCargado) {
		this.pihId = pihId;
		this.pihFecha = pihFecha;
		this.usrLogin = usrLogin;
		this.pinNumeroFactura = pinNumeroFactura;
		this.pinFechaFactura = pinFechaFactura;
		this.pinConcepto = pinConcepto;
		this.plsCodigo = plsCodigo;
		this.pinCargado = pinCargado;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="PI_HIS_GEN", sequenceName="SQ_POLICES_INVHIS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PI_HIS_GEN")
	@Column(name = "PIH_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPihId() {
		return this.pihId;
	}

	public void setPihId(Long pihId) {
		this.pihId = pihId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PIH_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPihFecha() {
		return this.pihFecha;
	}

	public void setPihFecha(Date pihFecha) {
		this.pihFecha = pihFecha;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Column(name = "PIN_NUMERO_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPinNumeroFactura() {
		return this.pinNumeroFactura;
	}

	public void setPinNumeroFactura(String pinNumeroFactura) {
		this.pinNumeroFactura = pinNumeroFactura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PIN_FECHA_FACTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPinFechaFactura() {
		return this.pinFechaFactura;
	}

	public void setPinFechaFactura(Date pinFechaFactura) {
		this.pinFechaFactura = pinFechaFactura;
	}

	@Column(name = "PIN_CONCEPTO", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPinConcepto() {
		return this.pinConcepto;
	}

	public void setPinConcepto(String pinConcepto) {
		this.pinConcepto = pinConcepto;
	}

	@Column(name = "PLS_CODIGO", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPlsCodigo() {
		return this.plsCodigo;
	}

	public void setPlsCodigo(Long plsCodigo) {
		this.plsCodigo = plsCodigo;
	}

	@Column(name = "PIN_CARGADO", unique = false, nullable = false, insertable = true, updatable = true, length = 4)
	public Long getPinCargado() {
		return pinCargado;
	}

	public void setPinCargado(Long pinCargado) {
		this.pinCargado = pinCargado;
	}

}