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
 * ChargeTo entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHARGE_TO", schema = "", uniqueConstraints = {})
public class ChargeTo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long chtCodigo;
	private String chtNombre;
	private Set<ServiceRegistry> serviceRegistry = new HashSet<ServiceRegistry>(
			0);

	// Constructors

	/** default constructor */
	public ChargeTo() {
	}

	/** minimal constructor */
	public ChargeTo(Long chtCodigo, String chtNombre) {
		this.chtCodigo = chtCodigo;
		this.chtNombre = chtNombre;
	}

	/** full constructor */
	public ChargeTo(Long chtCodigo, String chtNombre,
			Set<ServiceRegistry> serviceRegistry) {
		this.chtCodigo = chtCodigo;
		this.chtNombre = chtNombre;
		this.serviceRegistry = serviceRegistry;
	}

	// Property accessors
	@Id
	@Column(name = "CHT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 18, scale = 0)
	public Long getChtCodigo() {
		return this.chtCodigo;
	}

	public void setChtCodigo(Long chtCodigo) {
		this.chtCodigo = chtCodigo;
	}

	@Column(name = "CHT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getChtNombre() {
		return this.chtNombre;
	}

	public void setChtNombre(String chtNombre) {
		this.chtNombre = chtNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "chargeTo")
	public Set<ServiceRegistry> getPrepaids() {
		return this.serviceRegistry;
	}

	public void setPrepaids(Set<ServiceRegistry> serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

}