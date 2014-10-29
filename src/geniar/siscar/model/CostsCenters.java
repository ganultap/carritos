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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * CostsCenters entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COSTS_CENTERS", schema = "", uniqueConstraints = {})
public class CostsCenters implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cocCodigo;
	private String cocNumero;
	private Float valorPrepago;
	private Set<CostCentersFuel> costCentersFuels = new HashSet<CostCentersFuel>(
			0);
	private Set<AccountingParameters> accountingParameterses = new HashSet<AccountingParameters>(
			0);
	private Set<CostsCentersVehicles> costsCentersVehicleses = new HashSet<CostsCentersVehicles>(
			0);

	// Constructors

	/** default constructor */
	public CostsCenters() {
	}

	/** minimal constructor */
	public CostsCenters(Long cocCodigo, String cocNumero) {
		this.cocCodigo = cocCodigo;
		this.cocNumero = cocNumero;
	}

	/** full constructor */
	public CostsCenters(Long cocCodigo, String cocNumero,
			Set<CostCentersFuel> costCentersFuels,
			Set<AccountingParameters> accountingParameterses,
			Set<CostsCentersVehicles> costsCentersVehicleses) {
		this.cocCodigo = cocCodigo;
		this.cocNumero = cocNumero;
		this.costCentersFuels = costCentersFuels;
		this.accountingParameterses = accountingParameterses;
		this.costsCentersVehicleses = costsCentersVehicleses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "COST_CENTERS_GEN", sequenceName = "SQ_COST_CENTERS", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COST_CENTERS_GEN")
	@Column(name = "COC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCocCodigo() {
		return this.cocCodigo;
	}

	public void setCocCodigo(Long cocCodigo) {
		this.cocCodigo = cocCodigo;
	}

	@Column(name = "COC_NUMERO", unique = false, nullable = false, insertable = true, updatable = true, length = 60)
	public String getCocNumero() {
		return this.cocNumero;
	}

	public void setCocNumero(String cocNumero) {
		this.cocNumero = cocNumero;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "costsCenters")
	public Set<CostCentersFuel> getCostCentersFuels() {
		return this.costCentersFuels;
	}

	public void setCostCentersFuels(Set<CostCentersFuel> costCentersFuels) {
		this.costCentersFuels = costCentersFuels;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "costsCenters")
	public Set<AccountingParameters> getAccountingParameterses() {
		return this.accountingParameterses;
	}

	public void setAccountingParameterses(
			Set<AccountingParameters> accountingParameterses) {
		this.accountingParameterses = accountingParameterses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "costsCenters")
	public Set<CostsCentersVehicles> getCostsCentersVehicleses() {
		return this.costsCentersVehicleses;
	}

	public void setCostsCentersVehicleses(
			Set<CostsCentersVehicles> costsCentersVehicleses) {
		this.costsCentersVehicleses = costsCentersVehicleses;
	}

	@Column(name = "VALOR_PREPAGO", unique = false, nullable = true, insertable = true, updatable = true, precision = 126, scale = 2)
	public Float getValorPrepago() {
		return valorPrepago;
	}

	public void setValorPrepago(Float valorPrepago) {
		this.valorPrepago = valorPrepago;
	}

}