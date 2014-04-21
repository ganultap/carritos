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
 * PoliciesHistoric entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "POLICIES_HISTORIC", schema = "", uniqueConstraints = {})
public class PoliciesHistoric implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long plhId;
	private PoliciesTypes policiesTypes;
	private String usrLogin;
	private Date plhFecha;
	private Long plsNumero;
	private Date plsFechainicioCobertura;
	private Date plsFechafinCobertura;
	private String plsDocUno;
	private String plsDocDos;
	private Long plsEstado;

	// Constructors

	/** default constructor */
	public PoliciesHistoric() {
	}

	/** minimal constructor */
	public PoliciesHistoric(Long plhId, PoliciesTypes policiesTypes,
			String usrLogin, Date plhFecha, Long plsNumero,
			Date plsFechainicioCobertura, Date plsFechafinCobertura,
			Long plsEstado) {
		this.plhId = plhId;
		this.policiesTypes = policiesTypes;
		this.usrLogin = usrLogin;
		this.plhFecha = plhFecha;
		this.plsNumero = plsNumero;
		this.plsFechainicioCobertura = plsFechainicioCobertura;
		this.plsFechafinCobertura = plsFechafinCobertura;
		this.plsEstado = plsEstado;
	}

	/** full constructor */
	public PoliciesHistoric(Long plhId, PoliciesTypes policiesTypes,
			String usrLogin, Date plhFecha, Long plsNumero,
			Date plsFechainicioCobertura, Date plsFechafinCobertura,
			String plsDocUno, String plsDocDos, Long plsEstado) {
		this.plhId = plhId;
		this.policiesTypes = policiesTypes;
		this.usrLogin = usrLogin;
		this.plhFecha = plhFecha;
		this.plsNumero = plsNumero;
		this.plsFechainicioCobertura = plsFechainicioCobertura;
		this.plsFechafinCobertura = plsFechafinCobertura;
		this.plsDocUno = plsDocUno;
		this.plsDocDos = plsDocDos;
		this.plsEstado = plsEstado;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="POLICES_HISTO_GEN", sequenceName="SQ_POLICES_HISTO", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POLICES_HISTO_GEN")
	@Column(name = "PLH_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getPlhId() {
		return this.plhId;
	}

	public void setPlhId(Long plhId) {
		this.plhId = plhId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PLT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public PoliciesTypes getPoliciesTypes() {
		return this.policiesTypes;
	}

	public void setPoliciesTypes(PoliciesTypes policiesTypes) {
		this.policiesTypes = policiesTypes;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLH_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPlhFecha() {
		return this.plhFecha;
	}

	public void setPlhFecha(Date plhFecha) {
		this.plhFecha = plhFecha;
	}

	@Column(name = "PLS_NUMERO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPlsNumero() {
		return this.plsNumero;
	}

	public void setPlsNumero(Long plsNumero) {
		this.plsNumero = plsNumero;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLS_FECHAINICIO_COBERTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPlsFechainicioCobertura() {
		return this.plsFechainicioCobertura;
	}

	public void setPlsFechainicioCobertura(Date plsFechainicioCobertura) {
		this.plsFechainicioCobertura = plsFechainicioCobertura;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PLS_FECHAFIN_COBERTURA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPlsFechafinCobertura() {
		return this.plsFechafinCobertura;
	}

	public void setPlsFechafinCobertura(Date plsFechafinCobertura) {
		this.plsFechafinCobertura = plsFechafinCobertura;
	}

	@Column(name = "PLS_DOC_UNO", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPlsDocUno() {
		return this.plsDocUno;
	}

	public void setPlsDocUno(String plsDocUno) {
		this.plsDocUno = plsDocUno;
	}

	@Column(name = "PLS_DOC_DOS", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getPlsDocDos() {
		return this.plsDocDos;
	}

	public void setPlsDocDos(String plsDocDos) {
		this.plsDocDos = plsDocDos;
	}

	@Column(name = "PLS_ESTADO", unique = false, nullable = false, insertable = true, updatable = true, length = 2)
	public Long getPlsEstado() {
		return this.plsEstado;
	}

	public void setPlsEstado(Long plsEstado) {
		this.plsEstado = plsEstado;
	}

}