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
 * RevisionHour entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "REVISION_HOUR", schema = "", uniqueConstraints = {})
public class RevisionHour implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rhoCodigo;
	private String rhoHora;
	private Set<DialyMovementPumps> dialyMovementPumpses = new HashSet<DialyMovementPumps>(0);

	// Constructors

	/** default constructor */
	public RevisionHour() {
	}

	/** minimal constructor */
	public RevisionHour(Long rhoCodigo, String rhoHora) {
		this.rhoCodigo = rhoCodigo;
		this.rhoHora = rhoHora;
	}

	/** full constructor */
	public RevisionHour(Long rhoCodigo, String rhoHora, Set<DialyMovementPumps> dialyMovementPumpses) {
		this.rhoCodigo = rhoCodigo;
		this.rhoHora = rhoHora;
		this.dialyMovementPumpses = dialyMovementPumpses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="SQ_REVISION_HOUR_GEN", sequenceName="SQ_REVISION_HOUR", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SQ_REVISION_HOUR_GEN")
	@Column(name = "RHO_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getRhoCodigo() {
		return this.rhoCodigo;
	}

	public void setRhoCodigo(Long rhoCodigo) {
		this.rhoCodigo = rhoCodigo;
	}

	@Column(name = "RHO_HORA", unique = false, nullable = false, insertable = true, updatable = true, length = 18)
	public String getRhoHora() {
		return this.rhoHora;
	}

	public void setRhoHora(String rhoHora) {
		this.rhoHora = rhoHora;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "revisionHour")
	public Set<DialyMovementPumps> getDialyMovementPumpses() {
		return this.dialyMovementPumpses;
	}

	public void setDialyMovementPumpses(Set<DialyMovementPumps> dialyMovementPumpses) {
		this.dialyMovementPumpses = dialyMovementPumpses;
	}

}