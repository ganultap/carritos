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
 * ActsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ACTS_TYPES", schema = "", uniqueConstraints = {})
public class ActsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long atyCodigo;
	private String atyNombre;
	private Set<Acts> actses = new HashSet<Acts>(0);

	// Constructors

	/** default constructor */
	public ActsTypes() {
	}

	/** minimal constructor */
	public ActsTypes(Long atyCodigo, String atyNombre) {
		this.atyCodigo = atyCodigo;
		this.atyNombre = atyNombre;
	}

	/** full constructor */
	public ActsTypes(Long atyCodigo, String atyNombre, Set<Acts> actses) {
		this.atyCodigo = atyCodigo;
		this.atyNombre = atyNombre;
		this.actses = actses;
	}

	// Property accessors
	@Id
	@Column(name = "ATY_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAtyCodigo() {
		return this.atyCodigo;
	}

	public void setAtyCodigo(Long atyCodigo) {
		this.atyCodigo = atyCodigo;
	}

	@Column(name = "ATY_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getAtyNombre() {
		return this.atyNombre;
	}

	public void setAtyNombre(String atyNombre) {
		this.atyNombre = atyNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "actsTypes")
	public Set<Acts> getActses() {
		return this.actses;
	}

	public void setActses(Set<Acts> actses) {
		this.actses = actses;
	}

}