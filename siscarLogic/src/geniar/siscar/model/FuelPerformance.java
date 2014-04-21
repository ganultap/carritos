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
 * FuelPerformance entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FUEL_PERFORMANCE", schema = "", uniqueConstraints = {})
public class FuelPerformance implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long fpmCodigo;
	private Lines lines;
	private TractionsTypes tractionsTypes;
	private FuelsTypes fuelsTypes;
	private Float valorRendimiento;

	// Constructors

	/** default constructor */
	public FuelPerformance() {
	}

	/** minimal constructor */
	public FuelPerformance(Long fpmCodigo, Float valorRendimiento) {
		this.fpmCodigo = fpmCodigo;
		this.valorRendimiento = valorRendimiento;
	}

	/** full constructor */
	public FuelPerformance(Long fpmCodigo, Lines lines, TractionsTypes tractionsTypes, FuelsTypes fuelsTypes,
			Float valorRendimiento) {
		this.fpmCodigo = fpmCodigo;
		this.lines = lines;
		this.tractionsTypes = tractionsTypes;
		this.fuelsTypes = fuelsTypes;
		this.valorRendimiento = valorRendimiento;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="FP_GEN", sequenceName="SQ_FUEL_PERFORMANCE", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FP_GEN")
	@Column(name = "FPM_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getFpmCodigo() {
		return this.fpmCodigo;
	}

	public void setFpmCodigo(Long fpmCodigo) {
		this.fpmCodigo = fpmCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LNS_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Lines getLines() {
		return this.lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TCT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public TractionsTypes getTractionsTypes() {
		return this.tractionsTypes;
	}

	public void setTractionsTypes(TractionsTypes tractionsTypes) {
		this.tractionsTypes = tractionsTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "FUT_CODIGO", unique = false, nullable = true, insertable = true, updatable = true)
	public FuelsTypes getFuelsTypes() {
		return this.fuelsTypes;
	}

	public void setFuelsTypes(FuelsTypes fuelsTypes) {
		this.fuelsTypes = fuelsTypes;
	}

	@Column(name = "VALOR_RENDIMIENTO", unique = false, nullable = false, insertable = true, updatable = true, precision = 126, scale = 0)
	public Float getValorRendimiento() {
		return this.valorRendimiento;
	}

	public void setValorRendimiento(Float valorRendimiento) {
		this.valorRendimiento = valorRendimiento;
	}

}