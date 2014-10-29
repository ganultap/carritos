package geniar.siscar.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Collections entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COLLECTIONS", uniqueConstraints = {})
public class Collections implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cllCodigo;
	private TariffsTypes tariffsTypes;
	private Vehicles vehicles;
	private LocationsTypes locationsTypes;
	private Float cllValor;
	private Date cllFecha;

	// Constructors

	/** default constructor */
	public Collections() {
	}

	/** full constructor */
	public Collections(Long cllCodigo, TariffsTypes tariffsTypes, Vehicles vehicles, LocationsTypes locationsTypes,
			Float cllValor, Date cllFecha) {
		this.cllCodigo = cllCodigo;
		this.tariffsTypes = tariffsTypes;
		this.vehicles = vehicles;
		this.locationsTypes = locationsTypes;
		this.cllValor = cllValor;
		this.cllFecha = cllFecha;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="COLLECTIONS_GEN", sequenceName="SQ_COLLECTIONS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COLLECTIONS_GEN")
	@Column(name = "CLL_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCllCodigo() {
		return this.cllCodigo;
	}

	public void setCllCodigo(Long cllCodigo) {
		this.cllCodigo = cllCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TRT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public TariffsTypes getTariffsTypes() {
		return this.tariffsTypes;
	}

	public void setTariffsTypes(TariffsTypes tariffsTypes) {
		this.tariffsTypes = tariffsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LCT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public LocationsTypes getLocationsTypes() {
		return this.locationsTypes;
	}

	public void setLocationsTypes(LocationsTypes locationsTypes) {
		this.locationsTypes = locationsTypes;
	}

	@Column(name = "CLL_VALOR", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getCllValor() {
		return this.cllValor;
	}

	public void setCllValor(Float cllValor) {
		this.cllValor = cllValor;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CLL_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getCllFecha() {
		return this.cllFecha;
	}

	public void setCllFecha(Date cllFecha) {
		this.cllFecha = cllFecha;
	}

}