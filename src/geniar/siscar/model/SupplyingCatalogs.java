package geniar.siscar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SupplyingCatalogs entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SUPPLYING_CATALOGS", uniqueConstraints = {})
public class SupplyingCatalogs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long supCodigo;
	private Lines lines;
	private Models models;
	private String supNumCatalogo;

	// Constructors

	/** default constructor */
	public SupplyingCatalogs() {
	}

	/** full constructor */
	public SupplyingCatalogs(Long supCodigo, Lines lines, Models models, String supNumCatalogo) {
		this.supCodigo = supCodigo;
		this.lines = lines;
		this.models = models;
		this.supNumCatalogo = supNumCatalogo;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="SUPPLYING_CATALOGS_GEN", sequenceName="SQ_SUPPLYING_CATALOGS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SUPPLYING_CATALOGS_GEN")
	@Column(name = "SUP_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getSupCodigo() {
		return this.supCodigo;
	}

	public void setSupCodigo(Long supCodigo) {
		this.supCodigo = supCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "LNS_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Lines getLines() {
		return this.lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "MDL_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Models getModels() {
		return this.models;
	}

	public void setModels(Models models) {
		this.models = models;
	}

	@Column(name = "SUP_NUM_CATALOGO", unique = false, nullable = false, insertable = true, updatable = true, length = 30)
	public String getSupNumCatalogo() {
		return this.supNumCatalogo;
	}

	public void setSupNumCatalogo(String supNumCatalogo) {
		this.supNumCatalogo = supNumCatalogo;
	}

}