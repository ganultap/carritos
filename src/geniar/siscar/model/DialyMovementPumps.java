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
 * DialyMovementPumps entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DIALY_MOVEMENT_PUMPS", schema = "", uniqueConstraints = {})
public class DialyMovementPumps implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dmpCodigo;
	private RevisionHour revisionHour;
	private Pumps pumps;
	private Date dmpFecha;
	private BigDecimal dmpLectura;
	private String dmpRecibosDia;
	private String dmpRecibosNoche;

	// Constructors

	/** default constructor */
	public DialyMovementPumps() {
	}

	/** minimal constructor */
	public DialyMovementPumps(Long dmpCodigo, RevisionHour revisionHour,
			Pumps pumps, Date dmpFecha, BigDecimal dmpLectura,
			String dmpRecibosDia) {
		this.dmpCodigo = dmpCodigo;
		this.revisionHour = revisionHour;
		this.pumps = pumps;
		this.dmpFecha = dmpFecha;
		this.dmpLectura = dmpLectura;
		this.dmpRecibosDia = dmpRecibosDia;
	}

	/** full constructor */
	public DialyMovementPumps(Long dmpCodigo, RevisionHour revisionHour,
			Pumps pumps, Date dmpFecha, BigDecimal dmpLectura,
			String dmpRecibosDia, String dmpRecibosNoche) {
		this.dmpCodigo = dmpCodigo;
		this.revisionHour = revisionHour;
		this.pumps = pumps;
		this.dmpFecha = dmpFecha;
		this.dmpLectura = dmpLectura;
		this.dmpRecibosDia = dmpRecibosDia;
		this.dmpRecibosNoche = dmpRecibosNoche;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "SQ_DIALY_MOVEMENT_PUMPS_GEN", sequenceName = "SQ_DIALY_MOVEMENT_PUMPS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_DIALY_MOVEMENT_PUMPS_GEN")
	@Column(name = "DMP_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getDmpCodigo() {
		return this.dmpCodigo;
	}

	public void setDmpCodigo(Long dmpCodigo) {
		this.dmpCodigo = dmpCodigo;
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
	@JoinColumn(name = "PUM_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Pumps getPumps() {
		return this.pumps;
	}

	public void setPumps(Pumps pumps) {
		this.pumps = pumps;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DMP_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getDmpFecha() {
		return this.dmpFecha;
	}

	public void setDmpFecha(Date dmpFecha) {
		this.dmpFecha = dmpFecha;
	}

	@Column(name = "DMP_LECTURA", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public BigDecimal getDmpLectura() {
		return this.dmpLectura;
	}

	public void setDmpLectura(BigDecimal dmpLectura) {
		this.dmpLectura = dmpLectura;
	}

	@Column(name = "DMP_RECIBOS_DIA", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getDmpRecibosDia() {
		return this.dmpRecibosDia;
	}

	public void setDmpRecibosDia(String dmpRecibosDia) {
		this.dmpRecibosDia = dmpRecibosDia;
	}

	@Column(name = "DMP_RECIBOS_NOCHE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getDmpRecibosNoche() {
		return this.dmpRecibosNoche;
	}

	public void setDmpRecibosNoche(String dmpRecibosNoche) {
		this.dmpRecibosNoche = dmpRecibosNoche;
	}

}