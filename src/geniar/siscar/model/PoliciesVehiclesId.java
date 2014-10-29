package geniar.siscar.model;

// default package

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * PoliciesVehiclesId entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Embeddable
public class PoliciesVehiclesId implements java.io.Serializable {

	// Fields

	private Long vhcCodigo;
	private Long plsCodigo;

	// Constructors

	/** default constructor */
	public PoliciesVehiclesId() {
	}

	/** full constructor */
	public PoliciesVehiclesId(Long vhcCodigo, Long plsCodigo) {
		this.vhcCodigo = vhcCodigo;
		this.plsCodigo = plsCodigo;
	}

	// Property accessors

	@Column(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getVhcCodigo() {
		return this.vhcCodigo;
	}

	public void setVhcCodigo(Long vhcCodigo) {
		this.vhcCodigo = vhcCodigo;
	}

	@Column(name = "PLS_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getPlsCodigo() {
		return this.plsCodigo;
	}

	public void setPlsCodigo(Long plsCodigo) {
		this.plsCodigo = plsCodigo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PoliciesVehiclesId))
			return false;
		PoliciesVehiclesId castOther = (PoliciesVehiclesId) other;

		return ((this.getVhcCodigo() == castOther.getVhcCodigo()) || (this
				.getVhcCodigo() != null
				&& castOther.getVhcCodigo() != null && this.getVhcCodigo()
				.equals(castOther.getVhcCodigo())))
				&& ((this.getPlsCodigo() == castOther.getPlsCodigo()) || (this
						.getPlsCodigo() != null
						&& castOther.getPlsCodigo() != null && this
						.getPlsCodigo().equals(castOther.getPlsCodigo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getVhcCodigo() == null ? 0 : this.getVhcCodigo().hashCode());
		result = 37 * result
				+ (getPlsCodigo() == null ? 0 : this.getPlsCodigo().hashCode());
		return result;
	}

}