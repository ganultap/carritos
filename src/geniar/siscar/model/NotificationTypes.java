package geniar.siscar.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NotificationTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NOTIFICATION_TYPES", schema = "", uniqueConstraints = {})
public class NotificationTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long nttCodigo;
	private Notifications notifications;
	private Date nttFecha;
	private String nttTexto;
	private String nttDestinatario;
	private String nttSolicitante;
	private String ntfNombre;

	// Constructors

	/** default constructor */
	public NotificationTypes() {
	}

	/** full constructor */
	public NotificationTypes(Long nttCodigo, Notifications notifications, Date nttFecha, String nttTexto,
			String nttDestinatario, String nttSolicitante, String ntfNombre) {
		this.nttCodigo = nttCodigo;
		this.notifications = notifications;
		this.nttFecha = nttFecha;
		this.nttTexto = nttTexto;
		this.nttDestinatario = nttDestinatario;
		this.nttSolicitante = nttSolicitante;
		this.ntfNombre = ntfNombre;
	}

	// Property accessors
	@Id
	@Column(name = "NTT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getNttCodigo() {
		return this.nttCodigo;
	}

	public void setNttCodigo(Long nttCodigo) {
		this.nttCodigo = nttCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "NTF_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Notifications getNotifications() {
		return this.notifications;
	}

	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "NTT_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getNttFecha() {
		return this.nttFecha;
	}

	public void setNttFecha(Date nttFecha) {
		this.nttFecha = nttFecha;
	}

	@Column(name = "NTT_TEXTO", unique = false, nullable = false, insertable = true, updatable = true)
	public String getNttTexto() {
		return this.nttTexto;
	}

	public void setNttTexto(String nttTexto) {
		this.nttTexto = nttTexto;
	}

	@Column(name = "NTT_DESTINATARIO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getNttDestinatario() {
		return this.nttDestinatario;
	}

	public void setNttDestinatario(String nttDestinatario) {
		this.nttDestinatario = nttDestinatario;
	}

	@Column(name = "NTT_SOLICITANTE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getNttSolicitante() {
		return this.nttSolicitante;
	}

	public void setNttSolicitante(String nttSolicitante) {
		this.nttSolicitante = nttSolicitante;
	}

	@Column(name = "NTF_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getNtfNombre() {
		return this.ntfNombre;
	}

	public void setNtfNombre(String ntfNombre) {
		this.ntfNombre = ntfNombre;
	}

}