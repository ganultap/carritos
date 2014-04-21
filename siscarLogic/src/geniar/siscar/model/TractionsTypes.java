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
 * TractionsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRACTIONS_TYPES", uniqueConstraints = {})
public class TractionsTypes implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long tctCodigo;
	private String tctNombre;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);

	// Constructors

	/** default constructor */
	public TractionsTypes() {
	}

	/** minimal constructor */
	public TractionsTypes(Long tctCodigo, String tctNombre) {
		this.tctCodigo = tctCodigo;
		this.tctNombre = tctNombre;
	}

	/** full constructor */
	public TractionsTypes(Long tctCodigo, String tctNombre, Set<Vehicles> vehicleses, Set<Tariffs> tariffses) {
		this.tctCodigo = tctCodigo;
		this.tctNombre = tctNombre;
		this.vehicleses = vehicleses;
		this.tariffses = tariffses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="TRACTIONS_TYPES_GEN", sequenceName="SQ_TRACTIONS_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRACTIONS_TYPES_GEN")
	@Column(name = "TCT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getTctCodigo() {
		return this.tctCodigo;
	}

	public void setTctCodigo(Long tctCodigo) {
		this.tctCodigo = tctCodigo;
	}

	@Column(name = "TCT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getTctNombre() {
		return this.tctNombre;
	}

	public void setTctNombre(String tctNombre) {
		this.tctNombre = tctNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tractionsTypes")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tractionsTypes")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

}