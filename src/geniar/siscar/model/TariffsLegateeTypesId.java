package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TariffsLegateeTypesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class TariffsLegateeTypesId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long lgtCodigo;
	private Long trfId;

	// Constructors

	/** default constructor */
	public TariffsLegateeTypesId() {
	}

	/** full constructor */
	public TariffsLegateeTypesId(Long lgtCodigo, Long trfId) {
		this.lgtCodigo = lgtCodigo;
		this.trfId = trfId;
	}

	// Property accessors

	@Column(name = "LGT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getLgtCodigo() {
		return this.lgtCodigo;
	}

	public void setLgtCodigo(Long lgtCodigo) {
		this.lgtCodigo = lgtCodigo;
	}

	@Column(name = "TRF_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getTrfId() {
		return this.trfId;
	}

	public void setTrfId(Long trfId) {
		this.trfId = trfId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TariffsLegateeTypesId))
			return false;
		TariffsLegateeTypesId castOther = (TariffsLegateeTypesId) other;

		return ((this.getLgtCodigo() == castOther.getLgtCodigo()) || (this.getLgtCodigo() != null
				&& castOther.getLgtCodigo() != null && this.getLgtCodigo().equals(castOther.getLgtCodigo())))
				&& ((this.getTrfId() == castOther.getTrfId()) || (this.getTrfId() != null
						&& castOther.getTrfId() != null && this.getTrfId().equals(castOther.getTrfId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getLgtCodigo() == null ? 0 : this.getLgtCodigo().hashCode());
		result = 37 * result + (getTrfId() == null ? 0 : this.getTrfId().hashCode());
		return result;
	}

}