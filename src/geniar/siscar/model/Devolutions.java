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
 * Devolutions entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DEVOLUTIONS", uniqueConstraints = {})
public class Devolutions implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dvlCodigo;
	private Vehicles vehicles;
	private String dvlTipoAsignatario;
	private Date dvlFechaEntrega;
	private String dvlKilometraje;
	private String dvlEstadoVeh;
	private Float dvlDevTarifAsig;
	private Float dvlCobroTarifAsig;

	// Constructors

	/** default constructor */
	public Devolutions() {
	}

	/** minimal constructor */
	public Devolutions(Long dvlCodigo, Date dvlFechaEntrega, String dvlKilometraje, String dvlEstadoVeh) {
		this.dvlCodigo = dvlCodigo;
		this.dvlFechaEntrega = dvlFechaEntrega;
		this.dvlKilometraje = dvlKilometraje;
		this.dvlEstadoVeh = dvlEstadoVeh;
	}

	/** full constructor */
	public Devolutions(Long dvlCodigo, Vehicles vehicles, String dvlTipoAsignatario, Date dvlFechaEntrega,
			String dvlKilometraje, String dvlEstadoVeh, Float dvlDevTarifAsig, Float dvlCobroTarifAsig) {
		this.dvlCodigo = dvlCodigo;
		this.vehicles = vehicles;
		this.dvlTipoAsignatario = dvlTipoAsignatario;
		this.dvlFechaEntrega = dvlFechaEntrega;
		this.dvlKilometraje = dvlKilometraje;
		this.dvlEstadoVeh = dvlEstadoVeh;
		this.dvlDevTarifAsig = dvlDevTarifAsig;
		this.dvlCobroTarifAsig = dvlCobroTarifAsig;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="DEVOLUTIONS_GEN", sequenceName="SQ_DEVOLUTIONS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEVOLUTIONS_GEN")
	@Column(name = "DVL_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getDvlCodigo() {
		return this.dvlCodigo;
	}

	public void setDvlCodigo(Long dvlCodigo) {
		this.dvlCodigo = dvlCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "DVL_TIPO_ASIGNATARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getDvlTipoAsignatario() {
		return this.dvlTipoAsignatario;
	}

	public void setDvlTipoAsignatario(String dvlTipoAsignatario) {
		this.dvlTipoAsignatario = dvlTipoAsignatario;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DVL_FECHA_ENTREGA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getDvlFechaEntrega() {
		return this.dvlFechaEntrega;
	}

	public void setDvlFechaEntrega(Date dvlFechaEntrega) {
		this.dvlFechaEntrega = dvlFechaEntrega;
	}

	@Column(name = "DVL_KILOMETRAJE", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getDvlKilometraje() {
		return this.dvlKilometraje;
	}

	public void setDvlKilometraje(String dvlKilometraje) {
		this.dvlKilometraje = dvlKilometraje;
	}

	@Column(name = "DVL_ESTADO_VEH", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getDvlEstadoVeh() {
		return this.dvlEstadoVeh;
	}

	public void setDvlEstadoVeh(String dvlEstadoVeh) {
		this.dvlEstadoVeh = dvlEstadoVeh;
	}

	@Column(name = "DVL_DEV_TARIF_ASIG", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getDvlDevTarifAsig() {
		return this.dvlDevTarifAsig;
	}

	public void setDvlDevTarifAsig(Float dvlDevTarifAsig) {
		this.dvlDevTarifAsig = dvlDevTarifAsig;
	}

	@Column(name = "DVL_COBRO_TARIF_ASIG", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getDvlCobroTarifAsig() {
		return this.dvlCobroTarifAsig;
	}

	public void setDvlCobroTarifAsig(Float dvlCobroTarifAsig) {
		this.dvlCobroTarifAsig = dvlCobroTarifAsig;
	}

}