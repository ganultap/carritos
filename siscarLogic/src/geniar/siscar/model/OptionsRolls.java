package geniar.siscar.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * OptionsRolls entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "OPTIONS_ROLLS",uniqueConstraints = {})
public class OptionsRolls implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OptionsRollsId id;
	private Rolls rolls;
	private Options options;

	// Constructors

	/** default constructor */
	public OptionsRolls() {
	}

	/** full constructor */
	public OptionsRolls(OptionsRollsId id, Rolls rolls, Options options) {
		this.id = id;
		this.rolls = rolls;
		this.options = options;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "rlsCodigo", column = @Column(name = "RLS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)),
			@AttributeOverride(name = "optCodigo", column = @Column(name = "OPT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)) })
	public OptionsRollsId getId() {
		return this.id;
	}

	public void setId(OptionsRollsId id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "RLS_CODIGO", unique = false, nullable = false, insertable = false, updatable = false)
	public Rolls getRolls() {
		return this.rolls;
	}

	public void setRolls(Rolls rolls) {
		this.rolls = rolls;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "OPT_CODIGO", unique = false, nullable = false, insertable = false, updatable = false)
	public Options getOptions() {
		return this.options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

}