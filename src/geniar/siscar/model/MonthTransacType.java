package geniar.siscar.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MonthTransacType entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "MONTH_TRANSAC_TYPE", schema = "")
public class MonthTransacType implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mttId;
	private String mttNombre;
	private Set<PoliciesVehicles> policiesVehicleses = new HashSet<PoliciesVehicles>(
			0);

	// Constructors

	/** default constructor */
	public MonthTransacType() {
	}

	/** minimal constructor */
	public MonthTransacType(Long mttId, String mttNombre) {
		this.mttId = mttId;
		this.mttNombre = mttNombre;
	}

	/** full constructor */
	public MonthTransacType(Long mttId, String mttNombre,
			Set<PoliciesVehicles> policiesVehicleses) {
		this.mttId = mttId;
		this.mttNombre = mttNombre;
		this.policiesVehicleses = policiesVehicleses;
	}

	// Property accessors
	@Id
	@Column(name = "MTT_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getMttId() {
		return this.mttId;
	}

	public void setMttId(Long mttId) {
		this.mttId = mttId;
	}

	@Column(name = "MTT_NOMBRE", nullable = false, length = 100)
	public String getMttNombre() {
		return this.mttNombre;
	}

	public void setMttNombre(String mttNombre) {
		this.mttNombre = mttNombre;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "monthTransacType")
	public Set<PoliciesVehicles> getPoliciesVehicleses() {
		return this.policiesVehicleses;
	}

	public void setPoliciesVehicleses(Set<PoliciesVehicles> policiesVehicleses) {
		this.policiesVehicleses = policiesVehicleses;
	}

}