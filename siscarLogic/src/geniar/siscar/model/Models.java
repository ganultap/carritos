package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Models entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MODELS", uniqueConstraints = {})
public class Models implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mdlId;
	private String mdlNombre;
	private Set<SupplyingCatalogs> supplyingCatalogses = new HashSet<SupplyingCatalogs>(0);

	// Constructors

	/** default constructor */
	public Models() {
	}

	/** minimal constructor */
	public Models(Long mdlId, String mdlNombre) {
		this.mdlId = mdlId;
		this.mdlNombre = mdlNombre;
	}

	/** full constructor */
	public Models(Long mdlId, String mdlNombre, Set<SupplyingCatalogs> supplyingCatalogses) {
		this.mdlId = mdlId;
		this.mdlNombre = mdlNombre;
		this.supplyingCatalogses = supplyingCatalogses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="MODELS_GEN", sequenceName="SQ_MODELS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MODELS_GEN")
	@Column(name = "MDL_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getMdlId() {
		return this.mdlId;
	}

	public void setMdlId(Long mdlId) {
		this.mdlId = mdlId;
	}

	@Column(name = "MDL_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getMdlNombre() {
		return this.mdlNombre;
	}

	public void setMdlNombre(String mdlNombre) {
		this.mdlNombre = mdlNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "models")
	public Set<SupplyingCatalogs> getSupplyingCatalogses() {
		return this.supplyingCatalogses;
	}

	public void setSupplyingCatalogses(Set<SupplyingCatalogs> supplyingCatalogses) {
		this.supplyingCatalogses = supplyingCatalogses;
	}

}