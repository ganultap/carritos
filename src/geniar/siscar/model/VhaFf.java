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
 * VhaFf entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VHA_FF", schema = "", uniqueConstraints = {})
public class VhaFf implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long vhfCodigo;
	private VehiclesAssignation vehiclesAssignation;
	private FlatFile flatFile;
	private String vhfObservacion;

	// Constructors

	/** default constructor */
	public VhaFf() {
	}

	/** minimal constructor */
	public VhaFf(Long vhfCodigo, VehiclesAssignation vehiclesAssignation,
			FlatFile flatFile) {
		this.vhfCodigo = vhfCodigo;
		this.vehiclesAssignation = vehiclesAssignation;
		this.flatFile = flatFile;
	}

	/** full constructor */
	public VhaFf(Long vhfCodigo, VehiclesAssignation vehiclesAssignation,
			FlatFile flatFile, String vhfObservacion) {
		this.vhfCodigo = vhfCodigo;
		this.vehiclesAssignation = vehiclesAssignation;
		this.flatFile = flatFile;
		this.vhfObservacion = vhfObservacion;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "VHA_FF_GEN", sequenceName = "SQ_VHA_FF", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VHA_FF_GEN")
	@Column(name = "VHF_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getVhfCodigo() {
		return this.vhfCodigo;
	}

	public void setVhfCodigo(Long vhfCodigo) {
		this.vhfCodigo = vhfCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHA_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public VehiclesAssignation getVehiclesAssignation() {
		return this.vehiclesAssignation;
	}

	public void setVehiclesAssignation(VehiclesAssignation vehiclesAssignation) {
		this.vehiclesAssignation = vehiclesAssignation;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FF_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public FlatFile getFlatFile() {
		return this.flatFile;
	}

	public void setFlatFile(FlatFile flatFile) {
		this.flatFile = flatFile;
	}

	@Column(name = "VHF_OBSERVACION", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getVhfObservacion() {
		return this.vhfObservacion;
	}

	public void setVhfObservacion(String vhfObservacion) {
		this.vhfObservacion = vhfObservacion;
	}

}