package geniar.siscar.model;

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

/**
 * AssignationsKilometrages entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ASSIGNATIONS_KILOMETRAGES", uniqueConstraints = {})
public class AssignationsKilometrages implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long askCodigo;
	private VehiclesAssignation vehiclesAssignation;
	private Long askKilomEntrega;
	private Long askKilomDev;

	// Constructors

	/** default constructor */
	public AssignationsKilometrages() {
	}

	/** full constructor */
	public AssignationsKilometrages(Long askCodigo, VehiclesAssignation vehiclesAssignation, Long askKilomEntrega,
			Long askKilomDev) {
		this.askCodigo = askCodigo;
		this.vehiclesAssignation = vehiclesAssignation;
		this.askKilomEntrega = askKilomEntrega;
		this.askKilomDev = askKilomDev;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="ASSIGNATIONS_KILOMETRAGES_GEN", sequenceName="SQ_ASSIGNATIONS_KILOMETRAGES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASSIGNATIONS_KILOMETRAGES_GEN")
	@Column(name = "ASK_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAskCodigo() {
		return this.askCodigo;
	}

	public void setAskCodigo(Long askCodigo) {
		this.askCodigo = askCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHA_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public VehiclesAssignation getVehiclesAssignation() {
		return this.vehiclesAssignation;
	}

	public void setVehiclesAssignation(VehiclesAssignation vehiclesAssignation) {
		this.vehiclesAssignation = vehiclesAssignation;
	}

	@Column(name = "ASK_KILOM_ENTREGA", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAskKilomEntrega() {
		return this.askKilomEntrega;
	}

	public void setAskKilomEntrega(Long askKilomEntrega) {
		this.askKilomEntrega = askKilomEntrega;
	}

	@Column(name = "ASK_KILOM_DEV", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAskKilomDev() {
		return this.askKilomDev;
	}

	public void setAskKilomDev(Long askKilomDev) {
		this.askKilomDev = askKilomDev;
	}

}