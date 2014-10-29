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
 * Users entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "USERS", schema = "", uniqueConstraints = {})
public class Users implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long usrCodigo;
	private Rolls rolls;
	private String usrIdentificacion;
	private String usrNombre;
	private String usrApellido;
	private String usrTelefono;
	private String usrDireccion;
	private String usrEmail;
	private String usrLogin;
	private String descripcion;
	private String usrEstado;
	private Date usrFecha;
	private Set<Requests> requestsesForRqsUser = new HashSet<Requests>(0);
	private Set<Requests> requestsesForUsrCodigo = new HashSet<Requests>(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(Long usrCodigo) {
		this.usrCodigo = usrCodigo;
	}

	/** full constructor */
	public Users(Long usrCodigo, Rolls rolls, String usrIdentificacion,
			String usrNombre, String usrApellido, String usrTelefono,
			String usrDireccion, String usrEmail, String usrLogin,
			String descripcion, String usrEstado, Date usrFecha,
			Set<Requests> requestsesForRqsUser,
			Set<Requests> requestsesForUsrCodigo) {
		this.usrCodigo = usrCodigo;
		this.rolls = rolls;
		this.usrIdentificacion = usrIdentificacion;
		this.usrNombre = usrNombre;
		this.usrApellido = usrApellido;
		this.usrTelefono = usrTelefono;
		this.usrDireccion = usrDireccion;
		this.usrEmail = usrEmail;
		this.usrLogin = usrLogin;
		this.descripcion = descripcion;
		this.usrEstado = usrEstado;
		this.usrFecha = usrFecha;
		this.requestsesForRqsUser = requestsesForRqsUser;
		this.requestsesForUsrCodigo = requestsesForUsrCodigo;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "USERS_GEN", sequenceName = "SQ_USERS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_GEN")
	@Column(name = "USR_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getUsrCodigo() {
		return this.usrCodigo;
	}

	public void setUsrCodigo(Long usrCodigo) {
		this.usrCodigo = usrCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RLS_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Rolls getRolls() {
		return this.rolls;
	}

	public void setRolls(Rolls rolls) {
		this.rolls = rolls;
	}

	@Column(name = "USR_IDENTIFICACION", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getUsrIdentificacion() {
		return this.usrIdentificacion;
	}

	public void setUsrIdentificacion(String usrIdentificacion) {
		this.usrIdentificacion = usrIdentificacion;
	}

	@Column(name = "USR_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUsrNombre() {
		return this.usrNombre;
	}

	public void setUsrNombre(String usrNombre) {
		this.usrNombre = usrNombre;
	}

	@Column(name = "USR_APELLIDO", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUsrApellido() {
		return this.usrApellido;
	}

	public void setUsrApellido(String usrApellido) {
		this.usrApellido = usrApellido;
	}

	@Column(name = "USR_TELEFONO", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUsrTelefono() {
		return this.usrTelefono;
	}

	public void setUsrTelefono(String usrTelefono) {
		this.usrTelefono = usrTelefono;
	}

	@Column(name = "USR_DIRECCION", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUsrDireccion() {
		return this.usrDireccion;
	}

	public void setUsrDireccion(String usrDireccion) {
		this.usrDireccion = usrDireccion;
	}

	@Column(name = "USR_EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getUsrEmail() {
		return this.usrEmail;
	}

	public void setUsrEmail(String usrEmail) {
		this.usrEmail = usrEmail;
	}

	@Column(name = "USR_LOGIN", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getUsrLogin() {
		return this.usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	@Column(name = "DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true, length = 200)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "USR_ESTADO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getUsrEstado() {
		return this.usrEstado;
	}

	public void setUsrEstado(String usrEstado) {
		this.usrEstado = usrEstado;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "USR_FECHA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getUsrFecha() {
		return this.usrFecha;
	}

	public void setUsrFecha(Date usrFecha) {
		this.usrFecha = usrFecha;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "usersByRqsUser")
	public Set<Requests> getRequestsesForRqsUser() {
		return this.requestsesForRqsUser;
	}

	public void setRequestsesForRqsUser(Set<Requests> requestsesForRqsUser) {
		this.requestsesForRqsUser = requestsesForRqsUser;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "usersByUsrCodigo")
	public Set<Requests> getRequestsesForUsrCodigo() {
		return this.requestsesForUsrCodigo;
	}

	public void setRequestsesForUsrCodigo(Set<Requests> requestsesForUsrCodigo) {
		this.requestsesForUsrCodigo = requestsesForUsrCodigo;
	}
}