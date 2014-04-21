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
 * ParParametros entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAR_PARAMETROS", uniqueConstraints = {})
public class ParParametros implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idparametro;
	private String nombre;
	private String descripcion;
	private Set<ParValoresparametros> parValoresparametroses = new HashSet<ParValoresparametros>(0);

	// Constructors

	/** default constructor */
	public ParParametros() {
	}

	/** minimal constructor */
	public ParParametros(Long idparametro) {
		this.idparametro = idparametro;
	}

	/** full constructor */
	public ParParametros(Long idparametro, String nombre, String descripcion,
			Set<ParValoresparametros> parValoresparametroses) {
		this.idparametro = idparametro;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.parValoresparametroses = parValoresparametroses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="PAR_PARAMETROS_GEN", sequenceName="SQ_PARAMETROS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PAR_PARAMETROS_GEN")
	@Column(name = "IDPARAMETRO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getIdparametro() {
		return this.idparametro;
	}

	public void setIdparametro(Long idparametro) {
		this.idparametro = idparametro;
	}

	@Column(name = "NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true, length = 256)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "parParametros")
	public Set<ParValoresparametros> getParValoresparametroses() {
		return this.parValoresparametroses;
	}

	public void setParValoresparametroses(Set<ParValoresparametros> parValoresparametroses) {
		this.parValoresparametroses = parValoresparametroses;
	}

}