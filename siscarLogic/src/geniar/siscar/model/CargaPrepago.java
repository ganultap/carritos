package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CargaPrepago entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CARGA_PREPAGO", schema = "")
public class CargaPrepago implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idCargaPrepago;
	private String placa;
	private Long consumoPromedio;
	private String centroCosto;
	private Long kmAnual;
	private Long galonesAno;
	private String tipoCombustible;
	private Long valorPrepago;
	private String tipoCargo;

	// Constructors

	/** default constructor */
	public CargaPrepago() {
	}

	/** minimal constructor */
	public CargaPrepago(Long idCargaPrepago, String placa,
			Long consumoPromedio, String centroCosto, Long kmAnual,
			Long galonesAno, String tipoCombustible, Long valorPrepago) {
		this.idCargaPrepago = idCargaPrepago;
		this.placa = placa;
		this.consumoPromedio = consumoPromedio;
		this.centroCosto = centroCosto;
		this.kmAnual = kmAnual;
		this.galonesAno = galonesAno;
		this.tipoCombustible = tipoCombustible;
		this.valorPrepago = valorPrepago;
	}

	/** full constructor */
	public CargaPrepago(Long idCargaPrepago, String placa,
			Long consumoPromedio, String centroCosto, Long kmAnual,
			Long galonesAno, String tipoCombustible, Long valorPrepago,
			String tipoCargo) {
		this.idCargaPrepago = idCargaPrepago;
		this.placa = placa;
		this.consumoPromedio = consumoPromedio;
		this.centroCosto = centroCosto;
		this.kmAnual = kmAnual;
		this.galonesAno = galonesAno;
		this.tipoCombustible = tipoCombustible;
		this.valorPrepago = valorPrepago;
		this.tipoCargo = tipoCargo;
	}

	// Property accessors
	@Id
	@Column(name = "ID_CARGA_PREPAGO", unique = true, nullable = false, precision = 38, scale = 0)
	public Long getIdCargaPrepago() {
		return this.idCargaPrepago;
	}

	public void setIdCargaPrepago(Long idCargaPrepago) {
		this.idCargaPrepago = idCargaPrepago;
	}

	@Column(name = "PLACA", nullable = false, length = 7)
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(name = "CONSUMO_PROMEDIO", nullable = false, precision = 22, scale = 0)
	public Long getConsumoPromedio() {
		return this.consumoPromedio;
	}

	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}

	@Column(name = "CENTRO_COSTO", nullable = false, length = 10)
	public String getCentroCosto() {
		return this.centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	@Column(name = "KM_ANUAL", nullable = false, precision = 22, scale = 0)
	public Long getKmAnual() {
		return this.kmAnual;
	}

	public void setKmAnual(Long kmAnual) {
		this.kmAnual = kmAnual;
	}

	@Column(name = "GALONES_ANO", nullable = false, precision = 22, scale = 0)
	public Long getGalonesAno() {
		return this.galonesAno;
	}

	public void setGalonesAno(Long galonesAno) {
		this.galonesAno = galonesAno;
	}

	@Column(name = "TIPO_COMBUSTIBLE", nullable = false, length = 1)
	public String getTipoCombustible() {
		return this.tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	@Column(name = "VALOR_PREPAGO", nullable = false, precision = 22, scale = 0)
	public Long getValorPrepago() {
		return this.valorPrepago;
	}

	public void setValorPrepago(Long valorPrepago) {
		this.valorPrepago = valorPrepago;
	}

	@Column(name = "TIPO_CARGO", length = 3)
	public String getTipoCargo() {
		return this.tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

}