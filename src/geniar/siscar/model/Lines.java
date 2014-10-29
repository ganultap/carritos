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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Lines entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "LINES", uniqueConstraints = {})
public class Lines implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lnsId;
	private Brands brands;
	private String lnsNombre;
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);
	private Set<SupplyingCatalogs> supplyingCatalogses = new HashSet<SupplyingCatalogs>(0);
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);

	// Constructors

	/** default constructor */
	public Lines() {
	}

	/** minimal constructor */
	public Lines(Long lnsId, Brands brands, String lnsNombre) {
		this.lnsId = lnsId;
		this.brands = brands;
		this.lnsNombre = lnsNombre;
	}

	/** full constructor */
	public Lines(Long lnsId, Brands brands, String lnsNombre, Set<Tariffs> tariffses,
			Set<SupplyingCatalogs> supplyingCatalogses, Set<Vehicles> vehicleses) {
		this.lnsId = lnsId;
		this.brands = brands;
		this.lnsNombre = lnsNombre;
		this.tariffses = tariffses;
		this.supplyingCatalogses = supplyingCatalogses;
		this.vehicleses = vehicleses;
	}

	// Property accessors
	@Id
	
	@SequenceGenerator(name="LINES_GEN", sequenceName="SQ_LINES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LINES_GEN")
	@Column(name = "LNS_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getLnsId() {
		return this.lnsId;
	}

	public void setLnsId(Long lnsId) {
		this.lnsId = lnsId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "BRN_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Brands getBrands() {
		return this.brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}

	@Column(name = "LNS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getLnsNombre() {
		return this.lnsNombre;
	}

	public void setLnsNombre(String lnsNombre) {
		this.lnsNombre = lnsNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "lines")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "lines")
	public Set<SupplyingCatalogs> getSupplyingCatalogses() {
		return this.supplyingCatalogses;
	}

	public void setSupplyingCatalogses(Set<SupplyingCatalogs> supplyingCatalogses) {
		this.supplyingCatalogses = supplyingCatalogses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "lines")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

}