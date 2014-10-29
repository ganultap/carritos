package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OptionsRollsId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class OptionsRollsId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rlsCodigo;
	private Long optCodigo;

	// Constructors

	/** default constructor */
	public OptionsRollsId() {
	}

	/** full constructor */
	public OptionsRollsId(Long rlsCodigo, Long optCodigo) {
		this.rlsCodigo = rlsCodigo;
		this.optCodigo = optCodigo;
	}

	// Property accessors

	@Column(name = "RLS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getRlsCodigo() {
		return this.rlsCodigo;
	}

	public void setRlsCodigo(Long rlsCodigo) {
		this.rlsCodigo = rlsCodigo;
	}

	@Column(name = "OPT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getOptCodigo() {
		return this.optCodigo;
	}

	public void setOptCodigo(Long optCodigo) {
		this.optCodigo = optCodigo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OptionsRollsId))
			return false;
		OptionsRollsId castOther = (OptionsRollsId) other;

		return ((this.getRlsCodigo() == castOther.getRlsCodigo()) || (this.getRlsCodigo() != null
				&& castOther.getRlsCodigo() != null && this.getRlsCodigo().equals(castOther.getRlsCodigo())))
				&& ((this.getOptCodigo() == castOther.getOptCodigo()) || (this.getOptCodigo() != null
						&& castOther.getOptCodigo() != null && this.getOptCodigo().equals(castOther.getOptCodigo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRlsCodigo() == null ? 0 : this.getRlsCodigo().hashCode());
		result = 37 * result + (getOptCodigo() == null ? 0 : this.getOptCodigo().hashCode());
		return result;
	}

}