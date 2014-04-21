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
 * Modules entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MODULES", schema = "", uniqueConstraints = {})
public class Modules implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mdlCodigo;
	private String mdlNombre;
	private Set<Options> optionses = new HashSet<Options>(0);

	// Constructors

	/** default constructor */
	public Modules() {
	}

	/** minimal constructor */
	public Modules(Long mdlCodigo, String mdlNombre) {
		this.mdlCodigo = mdlCodigo;
		this.mdlNombre = mdlNombre;
	}

	/** full constructor */
	public Modules(Long mdlCodigo, String mdlNombre, Set<Options> optionses) {
		this.mdlCodigo = mdlCodigo;
		this.mdlNombre = mdlNombre;
		this.optionses = optionses;
	}

	// Property accessors
	@Id
	@Column(name = "MDL_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getMdlCodigo() {
		return this.mdlCodigo;
	}

	public void setMdlCodigo(Long mdlCodigo) {
		this.mdlCodigo = mdlCodigo;
	}

	@Column(name = "MDL_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getMdlNombre() {
		return this.mdlNombre;
	}

	public void setMdlNombre(String mdlNombre) {
		this.mdlNombre = mdlNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "modules")
	public Set<Options> getOptionses() {
		return this.optionses;
	}

	public void setOptionses(Set<Options> optionses) {
		this.optionses = optionses;
	}

}