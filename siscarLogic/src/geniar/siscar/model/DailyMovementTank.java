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
 * DailyMovementTank entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DAILY_MOVEMENT_TANK", schema = "", uniqueConstraints = {})
public class DailyMovementTank implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long damId;
	private RevisionHour revisionHour;
	private FuelTanks fuelTanks;
	private Date damFecha;
	private String damEntrada;
	private Float damLectura;

	// Constructors

	/** default constructor */
	public DailyMovementTank() {
	}

	/** full constructor */
	public DailyMovementTank(Long damId, RevisionHour revisionHour,
			FuelTanks fuelTanks, Date damFecha, String damEntrada,
			Float damLectura) {
		this.damId = damId;
		this.revisionHour = revisionHour;
		this.fuelTanks = fuelTanks;
		this.damFecha = damFecha;
		this.damEntrada = damEntrada;
		this.damLectura = damLectura;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "DIALY_MOVEMENT_GEN", sequenceName = "SQ_DIALY_MOVEMENT", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIALY_MOVEMENT_GEN")
	@Column(name = "DAM_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getDamId() {
		return this.damId;
	}

	public void setDamId(Long damId) {
		this.damId = damId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RHO_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public RevisionHour getRevisionHour() {
		return this.revisionHour;
	}

	public void setRevisionHour(RevisionHour revisionHour) {
		this.revisionHour = revisionHour;
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
	@Column(name = "DAM_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getDamFecha() {
		return this.damFecha;
	}

	public void setDamFecha(Date damFecha) {
		this.damFecha = damFecha;
	}

	@Column(name = "DAM_ENTRADA", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getDamEntrada() {
		return this.damEntrada;
	}

	public void setDamEntrada(String damEntrada) {
		this.damEntrada = damEntrada;
	}

	@Column(name = "DAM_LECTURA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 2)
	public Float getDamLectura() {
		return this.damLectura;
	}

	public void setDamLectura(Float damLectura) {
		this.damLectura = damLectura;
	}

}