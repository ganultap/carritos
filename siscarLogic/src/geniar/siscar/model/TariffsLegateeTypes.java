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
 * TariffsLegateeTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TARIFFS_LEGATEE_TYPES", uniqueConstraints = {})
public class TariffsLegateeTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TariffsLegateeTypesId id;
	private LegateesTypes legateesTypes;
	private Tariffs tariffs;

	// Constructors

	/** default constructor */
	public TariffsLegateeTypes() {
	}

	/** full constructor */
	public TariffsLegateeTypes(TariffsLegateeTypesId id, LegateesTypes legateesTypes, Tariffs tariffs) {
		this.id = id;
		this.legateesTypes = legateesTypes;
		this.tariffs = tariffs;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "lgtCodigo", column = @Column(name = "LGT_CODIGO", unique = false, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)),
			@AttributeOverride(name = "trfId", column = @Column(name = "TRF_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)) })
	public TariffsLegateeTypesId getId() {
		return this.id;
	}

	public void setId(TariffsLegateeTypesId id) {
		this.id = id;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LGT_CODIGO", unique = false, nullable = false, insertable = false, updatable = false)
	public LegateesTypes getLegateesTypes() {
		return this.legateesTypes;
	}

	public void setLegateesTypes(LegateesTypes legateesTypes) {
		this.legateesTypes = legateesTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TRF_ID", unique = false, nullable = false, insertable = false, updatable = false)
	public Tariffs getTariffs() {
		return this.tariffs;
	}

	public void setTariffs(Tariffs tariffs) {
		this.tariffs = tariffs;
	}

}