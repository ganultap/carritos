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
 * PrepaidConsumption entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PREPAID_CONSUMPTION", schema = "", uniqueConstraints = {})
public class PrepaidConsumption implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long prcCodigo;
	private ServiceRegistry serviceRegistry;
	private CostCentersFuel costCentersFuel;
	private Date prcFecha;
	private String prcHora;
	private Float prcValorConsumo;

	// Constructors

	/** default constructor */
	public PrepaidConsumption() {
	}

	/** minimal constructor */
	public PrepaidConsumption(Long prcCodigo, ServiceRegistry serviceRegistry,
			CostCentersFuel costCentersFuel, Date prcFecha, String prcHora) {
		this.prcCodigo = prcCodigo;
		this.serviceRegistry = serviceRegistry;
		this.costCentersFuel = costCentersFuel;
		this.prcFecha = prcFecha;
		this.prcHora = prcHora;
	}

	/** full constructor */
	public PrepaidConsumption(Long prcCodigo, ServiceRegistry serviceRegistry,
			CostCentersFuel costCentersFuel, Date prcFecha, String prcHora,
			Float prcValorConsumo) {
		this.prcCodigo = prcCodigo;
		this.serviceRegistry = serviceRegistry;
		this.costCentersFuel = costCentersFuel;
		this.prcFecha = prcFecha;
		this.prcHora = prcHora;
		this.prcValorConsumo = prcValorConsumo;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="PREPAID_CONSUMPTION_GEN", sequenceName="SQ_PREPAID_CONSUMPTION", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PREPAID_CONSUMPTION_GEN")
	@Column(name = "PRC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getPrcCodigo() {
		return this.prcCodigo;
	}

	public void setPrcCodigo(Long prcCodigo) {
		this.prcCodigo = prcCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "SER_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public ServiceRegistry getServiceRegistry() {
		return this.serviceRegistry;
	}

	public void setServiceRegistry(ServiceRegistry serviceRegistry) {
		this.serviceRegistry = serviceRegistry;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CCF_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public CostCentersFuel getCostCentersFuel() {
		return this.costCentersFuel;
	}

	public void setCostCentersFuel(CostCentersFuel costCentersFuel) {
		this.costCentersFuel = costCentersFuel;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRC_FECHA", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPrcFecha() {
		return this.prcFecha;
	}

	public void setPrcFecha(Date prcFecha) {
		this.prcFecha = prcFecha;
	}

	@Column(name = "PRC_HORA", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getPrcHora() {
		return this.prcHora;
	}

	public void setPrcHora(String prcHora) {
		this.prcHora = prcHora;
	}

	@Column(name = "PRC_VALOR_CONSUMO", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getPrcValorConsumo() {
		return this.prcValorConsumo;
	}

	public void setPrcValorConsumo(Float prcValorConsumo) {
		this.prcValorConsumo = prcValorConsumo;
	}

}