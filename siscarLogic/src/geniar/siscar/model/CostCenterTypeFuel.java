package geniar.siscar.model;
// default package

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
 * CostCenterTypeFuel entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COST_CENTER_TYPE_FUEL", schema = "", uniqueConstraints = {})
public class CostCenterTypeFuel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cctCodigo;
	private String cctNombre;
	private Set<CostCentersFuel> costCentersFuels = new HashSet<CostCentersFuel>(
			0);

	// Constructors

	/** default constructor */
	public CostCenterTypeFuel() {
	}

	/** minimal constructor */
	public CostCenterTypeFuel(Long cctCodigo, String cctNombre) {
		this.cctCodigo = cctCodigo;
		this.cctNombre = cctNombre;
	}

	/** full constructor */
	public CostCenterTypeFuel(Long cctCodigo, String cctNombre,
			Set<CostCentersFuel> costCentersFuels) {
		this.cctCodigo = cctCodigo;
		this.cctNombre = cctNombre;
		this.costCentersFuels = costCentersFuels;
	}

	// Property accessors
	@Id
	@Column(name = "CCT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getCctCodigo() {
		return this.cctCodigo;
	}

	public void setCctCodigo(Long cctCodigo) {
		this.cctCodigo = cctCodigo;
	}

	@Column(name = "CCT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getCctNombre() {
		return this.cctNombre;
	}

	public void setCctNombre(String cctNombre) {
		this.cctNombre = cctNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "costCenterTypeFuel")
	public Set<CostCentersFuel> getCostCentersFuels() {
		return this.costCentersFuels;
	}

	public void setCostCentersFuels(Set<CostCentersFuel> costCentersFuels) {
		this.costCentersFuels = costCentersFuels;
	}

}