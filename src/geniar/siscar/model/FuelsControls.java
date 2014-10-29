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
 * FuelsControls entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FUELS_CONTROLS", uniqueConstraints = {})
public class FuelsControls implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long fucCodigo;
	private Pumps pumps;
	private Long fucNumeroGalones;
	private String fucNumeroCarne;
	private String fucNombre;
	private String fucPlaca;
	private String fucNumeroTl;

	// Constructors

	/** default constructor */
	public FuelsControls() {
	}

	/** minimal constructor */
	public FuelsControls(Long fucCodigo, Pumps pumps, Long fucNumeroGalones) {
		this.fucCodigo = fucCodigo;
		this.pumps = pumps;
		this.fucNumeroGalones = fucNumeroGalones;
	}

	/** full constructor */
	public FuelsControls(Long fucCodigo, Pumps pumps, Long fucNumeroGalones, String fucNumeroCarne, String fucNombre,
			String fucPlaca, String fucNumeroTl) {
		this.fucCodigo = fucCodigo;
		this.pumps = pumps;
		this.fucNumeroGalones = fucNumeroGalones;
		this.fucNumeroCarne = fucNumeroCarne;
		this.fucNombre = fucNombre;
		this.fucPlaca = fucPlaca;
		this.fucNumeroTl = fucNumeroTl;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="FUELS_CONTROLS_GEN", sequenceName="SQ_FUELS_CONTROLS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FUELS_CONTROLS_GEN")
	@Column(name = "FUC_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getFucCodigo() {
		return this.fucCodigo;
	}

	public void setFucCodigo(Long fucCodigo) {
		this.fucCodigo = fucCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PUM_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Pumps getPumps() {
		return this.pumps;
	}

	public void setPumps(Pumps pumps) {
		this.pumps = pumps;
	}

	@Column(name = "FUC_NUMERO_GALONES", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getFucNumeroGalones() {
		return this.fucNumeroGalones;
	}

	public void setFucNumeroGalones(Long fucNumeroGalones) {
		this.fucNumeroGalones = fucNumeroGalones;
	}

	@Column(name = "FUC_NUMERO_CARNE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFucNumeroCarne() {
		return this.fucNumeroCarne;
	}

	public void setFucNumeroCarne(String fucNumeroCarne) {
		this.fucNumeroCarne = fucNumeroCarne;
	}

	@Column(name = "FUC_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFucNombre() {
		return this.fucNombre;
	}

	public void setFucNombre(String fucNombre) {
		this.fucNombre = fucNombre;
	}

	@Column(name = "FUC_PLACA", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFucPlaca() {
		return this.fucPlaca;
	}

	public void setFucPlaca(String fucPlaca) {
		this.fucPlaca = fucPlaca;
	}

	@Column(name = "FUC_NUMERO_TL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFucNumeroTl() {
		return this.fucNumeroTl;
	}

	public void setFucNumeroTl(String fucNumeroTl) {
		this.fucNumeroTl = fucNumeroTl;
	}

}