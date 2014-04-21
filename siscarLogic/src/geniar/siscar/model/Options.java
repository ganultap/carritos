package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Options entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OPTIONS", uniqueConstraints = {})
public class Options implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long optCodigo;
	private Modules modules;
	private String optNombre;
	private String optLink;
	private Set<OptionsRolls> optionsRollses = new HashSet<OptionsRolls>(0);

	// Constructors

	/** default constructor */
	public Options() {
	}

	/** minimal constructor */
	public Options(Long optCodigo, Modules modules, String optNombre, String optLink) {
		this.optCodigo = optCodigo;
		this.modules = modules;
		this.optNombre = optNombre;
		this.optLink = optLink;
	}

	/** full constructor */
	public Options(Long optCodigo, Modules modules, String optNombre, String optLink, Set<OptionsRolls> optionsRollses) {
		this.optCodigo = optCodigo;
		this.modules = modules;
		this.optNombre = optNombre;
		this.optLink = optLink;
		this.optionsRollses = optionsRollses;
	}

	// Property accessors
	@Id
	@Column(name = "OPT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getOptCodigo() {
		return this.optCodigo;
	}

	public void setOptCodigo(Long optCodigo) {
		this.optCodigo = optCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MDL_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Modules getModules() {
		return this.modules;
	}

	public void setModules(Modules modules) {
		this.modules = modules;
	}

	@Column(name = "OPT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getOptNombre() {
		return this.optNombre;
	}

	public void setOptNombre(String optNombre) {
		this.optNombre = optNombre;
	}

	@Column(name = "OPT_LINK", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getOptLink() {
		return this.optLink;
	}

	public void setOptLink(String optLink) {
		this.optLink = optLink;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "options")
	public Set<OptionsRolls> getOptionsRollses() {
		return this.optionsRollses;
	}

	public void setOptionsRollses(Set<OptionsRolls> optionsRollses) {
		this.optionsRollses = optionsRollses;
	}

}