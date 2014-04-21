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
 * InvolvedVehicles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "INVOLVED_VEHICLES", schema = "", uniqueConstraints = {})
public class InvolvedVehicles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long hnvCodigo;
	private Cities cities;
	private Accidents accidents;
	private String hnvTipoVehiculo;
	private String hnvPlaca;
	private String hnvMarca;
	private String hnvConductor;
	private String hnvModelo;
	private String hnvIdentifConduc;
	private String hnvTelefConduc;
	private String hnvIdentifProp;
	private String hnvPropietario;
	private String hnvDireccionconductor;
	private Set<InjuredPersons> injuredPersonses = new HashSet<InjuredPersons>(
			0);

	// Constructors

	/** default constructor */
	public InvolvedVehicles() {
	}

	/** minimal constructor */
	public InvolvedVehicles(Long hnvCodigo, Cities cities, Accidents accidents,
			String hnvPlaca, String hnvMarca, String hnvConductor,
			String hnvModelo, String hnvIdentifConduc, String hnvTelefConduc,
			String hnvIdentifProp, String hnvPropietario,
			String hnvDireccionconductor) {
		this.hnvCodigo = hnvCodigo;
		this.cities = cities;
		this.accidents = accidents;
		this.hnvPlaca = hnvPlaca;
		this.hnvMarca = hnvMarca;
		this.hnvConductor = hnvConductor;
		this.hnvModelo = hnvModelo;
		this.hnvIdentifConduc = hnvIdentifConduc;
		this.hnvTelefConduc = hnvTelefConduc;
		this.hnvIdentifProp = hnvIdentifProp;
		this.hnvPropietario = hnvPropietario;
		this.hnvDireccionconductor = hnvDireccionconductor;
	}

	/** full constructor */
	public InvolvedVehicles(Long hnvCodigo, Cities cities, Accidents accidents,
			String hnvTipoVehiculo, String hnvPlaca, String hnvMarca,
			String hnvConductor, String hnvModelo, String hnvIdentifConduc,
			String hnvTelefConduc, String hnvIdentifProp,
			String hnvPropietario, String hnvDireccionconductor,
			Set<InjuredPersons> injuredPersonses) {
		this.hnvCodigo = hnvCodigo;
		this.cities = cities;
		this.accidents = accidents;
		this.hnvTipoVehiculo = hnvTipoVehiculo;
		this.hnvPlaca = hnvPlaca;
		this.hnvMarca = hnvMarca;
		this.hnvConductor = hnvConductor;
		this.hnvModelo = hnvModelo;
		this.hnvIdentifConduc = hnvIdentifConduc;
		this.hnvTelefConduc = hnvTelefConduc;
		this.hnvIdentifProp = hnvIdentifProp;
		this.hnvPropietario = hnvPropietario;
		this.hnvDireccionconductor = hnvDireccionconductor;
		this.injuredPersonses = injuredPersonses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "INVOLVED_VEHICLES_GEN", sequenceName = "SQ_INVOLVED_VEHICLES", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVOLVED_VEHICLES_GEN")
	@Column(name = "HNV_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getHnvCodigo() {
		return this.hnvCodigo;
	}

	public void setHnvCodigo(Long hnvCodigo) {
		this.hnvCodigo = hnvCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CTS_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Cities getCities() {
		return this.cities;
	}

	public void setCities(Cities cities) {
		this.cities = cities;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Accidents getAccidents() {
		return this.accidents;
	}

	public void setAccidents(Accidents accidents) {
		this.accidents = accidents;
	}

	@Column(name = "HNV_TIPO_VEHICULO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getHnvTipoVehiculo() {
		return this.hnvTipoVehiculo;
	}

	public void setHnvTipoVehiculo(String hnvTipoVehiculo) {
		this.hnvTipoVehiculo = hnvTipoVehiculo;
	}

	@Column(name = "HNV_PLACA", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getHnvPlaca() {
		return this.hnvPlaca;
	}

	public void setHnvPlaca(String hnvPlaca) {
		this.hnvPlaca = hnvPlaca;
	}

	@Column(name = "HNV_MODELO", unique = false, nullable = false, insertable = true, updatable = true, length = 4)
	public String getHnvModelo() {
		return this.hnvModelo;
	}

	public void setHnvModelo(String hnvModelo) {
		this.hnvModelo = hnvModelo;
	}

	@Column(name = "HNV_MARCA", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getHnvMarca() {
		return this.hnvMarca;
	}

	public void setHnvMarca(String hnvMarca) {
		this.hnvMarca = hnvMarca;
	}

	@Column(name = "HNV_CONDUCTOR", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getHnvConductor() {
		return this.hnvConductor;
	}

	public void setHnvConductor(String hnvConductor) {
		this.hnvConductor = hnvConductor;
	}

	@Column(name = "HNV_IDENTIF_CONDUC", unique = false, nullable = false, insertable = true, updatable = true, length = 25)
	public String getHnvIdentifConduc() {
		return this.hnvIdentifConduc;
	}

	public void setHnvIdentifConduc(String hnvIdentifConduc) {
		this.hnvIdentifConduc = hnvIdentifConduc;
	}

	@Column(name = "HNV_TELEF_CONDUC", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getHnvTelefConduc() {
		return this.hnvTelefConduc;
	}

	public void setHnvTelefConduc(String hnvTelefConduc) {
		this.hnvTelefConduc = hnvTelefConduc;
	}

	@Column(name = "HNV_IDENTIF_PROP", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getHnvIdentifProp() {
		return this.hnvIdentifProp;
	}

	public void setHnvIdentifProp(String hnvIdentifProp) {
		this.hnvIdentifProp = hnvIdentifProp;
	}

	@Column(name = "HNV_PROPIETARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getHnvPropietario() {
		return this.hnvPropietario;
	}

	public void setHnvPropietario(String hnvPropietario) {
		this.hnvPropietario = hnvPropietario;
	}

	@Column(name = "HNV_DIRECCIONCONDUCTOR", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getHnvDireccionconductor() {
		return this.hnvDireccionconductor;
	}

	public void setHnvDireccionconductor(String hnvDireccionconductor) {
		this.hnvDireccionconductor = hnvDireccionconductor;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "involvedVehicles")
	public Set<InjuredPersons> getInjuredPersonses() {
		return this.injuredPersonses;
	}

	public void setInjuredPersonses(Set<InjuredPersons> injuredPersonses) {
		this.injuredPersonses = injuredPersonses;
	}

}