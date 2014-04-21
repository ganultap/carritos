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
 * TransmissionsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANSMISSIONS_TYPES", uniqueConstraints = {})
public class TransmissionsTypes implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long tntCodigo;
	private String tntNombre;
	private Set<Vehicles> vehicleses = new HashSet<Vehicles>(0);
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);

	// Constructors

	/** default constructor */
	public TransmissionsTypes() {
	}

	/** minimal constructor */
	public TransmissionsTypes(Long tntCodigo, String tntNombre) {
		this.tntCodigo = tntCodigo;
		this.tntNombre = tntNombre;
	}

	/** full constructor */
	public TransmissionsTypes(Long tntCodigo, String tntNombre, Set<Vehicles> vehicleses, Set<Tariffs> tariffses) {
		this.tntCodigo = tntCodigo;
		this.tntNombre = tntNombre;
		this.vehicleses = vehicleses;
		this.tariffses = tariffses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="TRANSMISSIONS_TYPES_GEN", sequenceName="SQ_TRANSMISSIONS_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSMISSIONS_TYPES_GEN")
	@Column(name = "TNT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getTntCodigo() {
		return this.tntCodigo;
	}

	public void setTntCodigo(Long tntCodigo) {
		this.tntCodigo = tntCodigo;
	}

	@Column(name = "TNT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getTntNombre() {
		return this.tntNombre;
	}

	public void setTntNombre(String tntNombre) {
		this.tntNombre = tntNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "transmissionsTypes")
	public Set<Vehicles> getVehicleses() {
		return this.vehicleses;
	}

	public void setVehicleses(Set<Vehicles> vehicleses) {
		this.vehicleses = vehicleses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "transmissionsTypes")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

}