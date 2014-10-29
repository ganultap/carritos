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
 * Rolls entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ROLLS", uniqueConstraints = {})
public class Rolls implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rlsCodigo;
	private String rlsNombre;
	private String rlsDescripcion;
	private String rlsMail;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<OptionsRolls> optionsRollses = new HashSet<OptionsRolls>(0);

	// Constructors

	/** default constructor */
	public Rolls() {
	}

	/** minimal constructor */
	public Rolls(Long rlsCodigo, String rlsNombre, String rlsMail) {
		this.rlsCodigo = rlsCodigo;
		this.rlsNombre = rlsNombre;
		this.rlsMail = rlsMail;
	}

	/** full constructor */
	public Rolls(Long rlsCodigo, String rlsNombre, String rlsDescripcion, String rlsMail, Set<Users> userses,
			Set<OptionsRolls> optionsRollses) {
		this.rlsCodigo = rlsCodigo;
		this.rlsNombre = rlsNombre;
		this.rlsDescripcion = rlsDescripcion;
		this.rlsMail = rlsMail;
		this.userses = userses;
		this.optionsRollses = optionsRollses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="ROLLS_GEN", sequenceName="SQ_ROLLS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLLS_GEN")
	@Column(name = "RLS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRlsCodigo() {
		return this.rlsCodigo;
	}

	public void setRlsCodigo(Long rlsCodigo) {
		this.rlsCodigo = rlsCodigo;
	}

	@Column(name = "RLS_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getRlsNombre() {
		return this.rlsNombre;
	}

	public void setRlsNombre(String rlsNombre) {
		this.rlsNombre = rlsNombre;
	}

	@Column(name = "RLS_DESCRIPCION", unique = false, nullable = true, insertable = true, updatable = true)
	public String getRlsDescripcion() {
		return this.rlsDescripcion;
	}

	public void setRlsDescripcion(String rlsDescripcion) {
		this.rlsDescripcion = rlsDescripcion;
	}

	@Column(name = "RLS_MAIL", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getRlsMail() {
		return this.rlsMail;
	}

	public void setRlsMail(String rlsMail) {
		this.rlsMail = rlsMail;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "rolls")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "rolls")
	public Set<OptionsRolls> getOptionsRollses() {
		return this.optionsRollses;
	}

	public void setOptionsRollses(Set<OptionsRolls> optionsRollses) {
		this.optionsRollses = optionsRollses;
	}

}