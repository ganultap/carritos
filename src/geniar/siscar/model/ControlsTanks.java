package geniar.siscar.model;

import java.math.BigDecimal;
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
 * ControlsTanks entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CONTROLS_TANKS", schema = "", uniqueConstraints = {})
public class ControlsTanks implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cotCodigo;
	private FuelTanks fuelTanks;
	private Date cotFechaCarga;
	private BigDecimal cotGalonesActuales;

	// Constructors

	/** default constructor */
	public ControlsTanks() {
	}

	/** minimal constructor */
	public ControlsTanks(Long cotCodigo, FuelTanks fuelTanks) {
		this.cotCodigo = cotCodigo;
		this.fuelTanks = fuelTanks;
	}

	/** full constructor */
	public ControlsTanks(Long cotCodigo, FuelTanks fuelTanks, Date cotFechaCarga, BigDecimal cotGalonesActuales) {
		this.cotCodigo = cotCodigo;
		this.fuelTanks = fuelTanks;
		this.cotFechaCarga = cotFechaCarga;
		this.cotGalonesActuales = cotGalonesActuales;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="SQ_CONTROL_TANKS_GEN", sequenceName="SQ_CONTROL_TANKS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_CONTROL_TANKS_GEN")
	@Column(name = "COT_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCotCodigo() {
		return this.cotCodigo;
	}

	public void setCotCodigo(Long cotCodigo) {
		this.cotCodigo = cotCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FTA_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public FuelTanks getFuelTanks() {
		return this.fuelTanks;
	}

	public void setFuelTanks(FuelTanks fuelTanks) {
		this.fuelTanks = fuelTanks;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COT_FECHA_CARGA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCotFechaCarga() {
		return this.cotFechaCarga;
	}

	public void setCotFechaCarga(Date cotFechaCarga) {
		this.cotFechaCarga = cotFechaCarga;
	}

	@Column(name = "COT_GALONES_ACTUALES", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public BigDecimal getCotGalonesActuales() {
		return this.cotGalonesActuales;
	}

	public void setCotGalonesActuales(BigDecimal cotGalonesActuales) {
		this.cotGalonesActuales = cotGalonesActuales;
	}

}