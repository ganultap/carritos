package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Colors entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "COLORS", uniqueConstraints = {})
public class Colors implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long clCodigo;
	private String clNombre;

	// Constructors

	/** default constructor */
	public Colors() {
	}

	/** minimal constructor */
	public Colors(Long clCodigo) {
		this.clCodigo = clCodigo;
	}

	/** full constructor */
	public Colors(Long clCodigo, String clNombre) {
		this.clCodigo = clCodigo;
		this.clNombre = clNombre;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="COLORS_GEN", sequenceName="SQ_COLORS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COLORS_GEN")
	@Column(name = "CL_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getClCodigo() {
		return this.clCodigo;
	}

	public void setClCodigo(Long clCodigo) {
		this.clCodigo = clCodigo;
	}

	@Column(name = "CL_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getClNombre() {
		return this.clNombre;
	}

	public void setClNombre(String clNombre) {
		this.clNombre = clNombre;
	}

}