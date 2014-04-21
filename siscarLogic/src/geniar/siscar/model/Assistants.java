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
 * Assistants entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ASSISTANTS", schema = "", uniqueConstraints = {})
public class Assistants implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long asiCodigo;
	private Acts acts;
	private String asiNombre;
	private String asiEmail;
	private String asiCodigoCiat;

	// Constructors

	/** default constructor */
	public Assistants() {
	}

	/** minimal constructor */
	public Assistants(Long asiCodigo, Acts acts, String asiCodigoCiat) {
		this.asiCodigo = asiCodigo;
		this.acts = acts;
		this.asiCodigoCiat = asiCodigoCiat;
	}

	/** full constructor */
	public Assistants(Long asiCodigo, Acts acts, String asiNombre,
			String asiEmail, String asiCodigoCiat) {
		this.asiCodigo = asiCodigo;
		this.acts = acts;
		this.asiNombre = asiNombre;
		this.asiEmail = asiEmail;
		this.asiCodigoCiat = asiCodigoCiat;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="ASSISTANTS_GEN",sequenceName="SQ_ASSISTANTS",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ASSISTANTS_GEN")
	@Column(name = "ASI_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getAsiCodigo() {
		return this.asiCodigo;
	}

	public void setAsiCodigo(Long asiCodigo) {
		this.asiCodigo = asiCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Acts getActs() {
		return this.acts;
	}

	public void setActs(Acts acts) {
		this.acts = acts;
	}

	@Column(name = "ASI_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAsiNombre() {
		return this.asiNombre;
	}

	public void setAsiNombre(String asiNombre) {
		this.asiNombre = asiNombre;
	}

	@Column(name = "ASI_EMAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getAsiEmail() {
		return this.asiEmail;
	}

	public void setAsiEmail(String asiEmail) {
		this.asiEmail = asiEmail;
	}

	@Column(name = "ASI_CODIGO_CIAT", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getAsiCodigoCiat() {
		return this.asiCodigoCiat;
	}

	public void setAsiCodigoCiat(String asiCodigoCiat) {
		this.asiCodigoCiat = asiCodigoCiat;
	}

}