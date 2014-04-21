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
 * LegateesNewness entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LEGATEES_NEWNESS", uniqueConstraints = {})
public class LegateesNewness implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lgnCodigo;
	private Requests requests;
	private Date lgnFechaActual;
	private String lgnDescripcion;
	private String usrLogin;
	private String lgnCarneAsignatario;
	private String lgnCarneAsistente;

	// Constructors

	/** default constructor */
	public LegateesNewness() {
	}

	/** minimal constructor */
	public LegateesNewness(Long lgnCodigo, Requests requests, Date lgnFechaActual, String usrLogin,
			String lgnCarneAsignatario, String lgnCarneAsistente) {
		this.lgnCodigo = lgnCodigo;
		this.requests = requests;
		this.lgnFechaActual = lgnFechaActual;
		this.usrLogin = usrLogin;
		this.lgnCarneAsignatario = lgnCarneAsignatario;
		this.lgnCarneAsistente = lgnCarneAsistente;
	}

	/** full constructor */
	public LegateesNewness(Long lgnCodigo, Requests requests, Date lgnFechaActual, String lgnDescripcion,
			String usrLogin, String lgnCarneAsignatario, String lgnCarneAsistente) {
		this.lgnCodigo = lgnCodigo;
		this.requests = requests;
		this.lgnFechaActual = lgnFechaActual;
		this.lgnDescripcion = lgnDescripcion;
		this.usrLogin = usrLogin;
		this.lgnCarneAsignatario = lgnCarneAsignatario;
		this.lgnCarneAsistente = lgnCarneAsistente;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="LEGATEES_NEWNESS_GEN", sequenceName="SQ_LEGATEES_NEWNESS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LEGATEES_NEWNESS_GEN")
	@Column(name = "LGN_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getLgnCodigo() {
		return this.lgnCodigo;
	}

	public void setLgnCodigo(Long lgnCodigo) {
		this.lgnCodigo = lgnCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RQS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Requests getRequests() {
		return this.requests;
	}

	public void setRequests(Requests requests) {
		this.requests = requests;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LGN_FECHA_ACTUAL", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getLgnFechaActual() {
		return this.lgnFechaActual;
	}

	public void setLgnFechaActual(Date lgnFechaActual) {
		this.lgnFechaActual = lgnFechaActual;
	}

	@Column(name = "LGN_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getLgnDescripcion() {
		return this.lgnDescripcion;
	}

	public void setLgnDescripcion(String lgnDescripcion) {
		this.lgnDescripcion = lgnDescripcion;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Column(name = "LGN_CARNE_ASIGNATARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getLgnCarneAsignatario() {
		return this.lgnCarneAsignatario;
	}

	public void setLgnCarneAsignatario(String lgnCarneAsignatario) {
		this.lgnCarneAsignatario = lgnCarneAsignatario;
	}

	@Column(name = "LGN_CARNE_ASISTENTE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getLgnCarneAsistente() {
		return this.lgnCarneAsistente;
	}

	public void setLgnCarneAsistente(String lgnCarneAsistente) {
		this.lgnCarneAsistente = lgnCarneAsistente;
	}

}