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
 * RetirementsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "RETIREMENTS_TYPES", uniqueConstraints = {})
public class RetirementsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long retCodigo;
	private String retNombre;
	private Set<RetirementsReasons> retirementsReasonses = new HashSet<RetirementsReasons>(0);

	// Constructors

	/** default constructor */
	public RetirementsTypes() {
	}

	/** minimal constructor */
	public RetirementsTypes(Long retCodigo, String retNombre) {
		this.retCodigo = retCodigo;
		this.retNombre = retNombre;
	}

	/** full constructor */
	public RetirementsTypes(Long retCodigo, String retNombre, Set<RetirementsReasons> retirementsReasonses) {
		this.retCodigo = retCodigo;
		this.retNombre = retNombre;
		this.retirementsReasonses = retirementsReasonses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="RETIREMENTS_TYPES_GEN", sequenceName="SQ_RETIREMENTS_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RETIREMENTS_TYPES_GEN")
	@Column(name = "RET_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRetCodigo() {
		return this.retCodigo;
	}

	public void setRetCodigo(Long retCodigo) {
		this.retCodigo = retCodigo;
	}

	@Column(name = "RET_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getRetNombre() {
		return this.retNombre;
	}

	public void setRetNombre(String retNombre) {
		this.retNombre = retNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "retirementsTypes")
	public Set<RetirementsReasons> getRetirementsReasonses() {
		return this.retirementsReasonses;
	}

	public void setRetirementsReasonses(Set<RetirementsReasons> retirementsReasonses) {
		this.retirementsReasonses = retirementsReasonses;
	}

}