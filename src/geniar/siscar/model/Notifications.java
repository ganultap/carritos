package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Notifications entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NOTIFICATIONS", schema = "", uniqueConstraints = {})
public class Notifications implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ntfCodigo;
	private Set<NotificationTypes> notificationTypeses = new HashSet<NotificationTypes>(0);

	// Constructors

	/** default constructor */
	public Notifications() {
	}

	/** minimal constructor */
	public Notifications(Long ntfCodigo) {
		this.ntfCodigo = ntfCodigo;
	}

	/** full constructor */
	public Notifications(Long ntfCodigo, Set<NotificationTypes> notificationTypeses) {
		this.ntfCodigo = ntfCodigo;
		this.notificationTypeses = notificationTypeses;
	}

	// Property accessors
	@Id
	@Column(name = "NTF_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getNtfCodigo() {
		return this.ntfCodigo;
	}

	public void setNtfCodigo(Long ntfCodigo) {
		this.ntfCodigo = ntfCodigo;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "notifications")
	public Set<NotificationTypes> getNotificationTypeses() {
		return this.notificationTypeses;
	}

	public void setNotificationTypeses(Set<NotificationTypes> notificationTypeses) {
		this.notificationTypeses = notificationTypeses;
	}

}