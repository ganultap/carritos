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
 * VhaAoaApp entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "VHA_AOA_APP", schema = "", uniqueConstraints = {})
public class VhaAoaApp implements java.io.Serializable {

	// Fields
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long vhaAoaAppId;
	private VehiclesAssignation vehiclesAssignation;
	private HeaderProof headerProof;
	private String aoaObservacion;
	private Date aoaFechaIni;
	private Date aoaFechaFin;

	// Constructors

	/** default constructor */
	public VhaAoaApp() {
	}

	/** minimal constructor */
	public VhaAoaApp(Long vhaAoaAppId, VehiclesAssignation vehiclesAssignation,
			HeaderProof headerProof) {
		this.vhaAoaAppId = vhaAoaAppId;
		this.vehiclesAssignation = vehiclesAssignation;
		this.headerProof = headerProof;
	}

	/** full constructor */
	public VhaAoaApp(Long vhaAoaAppId, VehiclesAssignation vehiclesAssignation,
			HeaderProof headerProof, String aoaObservacion, Date aoaFechaIni,
			Date aoaFechaFin) {
		this.vhaAoaAppId = vhaAoaAppId;
		this.vehiclesAssignation = vehiclesAssignation;
		this.headerProof = headerProof;
		this.aoaObservacion = aoaObservacion;
		this.aoaFechaIni = aoaFechaIni;
		this.aoaFechaFin = aoaFechaFin;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="VHA_AOA_APP_GEN", sequenceName="SQ_VHA_AOA_APP", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="VHA_AOA_APP_GEN")
	@Column(name = "VHA_AOA_APP_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhaAoaAppId() {
		return this.vhaAoaAppId;
	}

	public void setVhaAoaAppId(Long vhaAoaAppId) {
		this.vhaAoaAppId = vhaAoaAppId;
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
	@JoinColumn(name = "HEP_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public HeaderProof getHeaderProof() {
		return this.headerProof;
	}

	public void setHeaderProof(HeaderProof headerProof) {
		this.headerProof = headerProof;
	}

	@Column(name = "AOA_OBSERVACION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getAoaObservacion() {
		return this.aoaObservacion;
	}

	public void setAoaObservacion(String aoaObservacion) {
		this.aoaObservacion = aoaObservacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AOA_FECHA_INI", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getAoaFechaIni() {
		return this.aoaFechaIni;
	}

	public void setAoaFechaIni(Date aoaFechaIni) {
		this.aoaFechaIni = aoaFechaIni;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AOA_FECHA_FIN", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getAoaFechaFin() {
		return this.aoaFechaFin;
	}

	public void setAoaFechaFin(Date aoaFechaFin) {
		this.aoaFechaFin = aoaFechaFin;
	}

}