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
 * PlainFileParameter entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PLAIN_FILE_PARAMETER", schema = "", uniqueConstraints = {})
public class PlainFileParameter implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pfpId;
	private CurrencyTypes currencyTypes;
	private NoveltyTypes noveltyTypes;
	private Long pfpConceptonomina;
	private String pfpDescripcion;

	// Constructors

	/** default constructor */
	public PlainFileParameter() {
	}

	/** minimal constructor */
	public PlainFileParameter(Long pfpId, Long pfpConceptonomina, String pfpDescripcion) {
		this.pfpId = pfpId;
		this.pfpConceptonomina = pfpConceptonomina;
		this.pfpDescripcion = pfpDescripcion;
	}

	/** full constructor */
	public PlainFileParameter(Long pfpId, CurrencyTypes currencyTypes, NoveltyTypes noveltyTypes,
			Long pfpConceptonomina, String pfpDescripcion) {
		this.pfpId = pfpId;
		this.currencyTypes = currencyTypes;
		this.noveltyTypes = noveltyTypes;
		this.pfpConceptonomina = pfpConceptonomina;
		this.pfpDescripcion = pfpDescripcion;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="PFP_GEN", sequenceName="SQ_PLAIN_FILE_PARAM", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PFP_GEN")
	@Column(name = "PFP_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 18, scale = 0)
	public Long getPfpId() {
		return this.pfpId;
	}

	public void setPfpId(Long pfpId) {
		this.pfpId = pfpId;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "CT_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public CurrencyTypes getCurrencyTypes() {
		return this.currencyTypes;
	}

	public void setCurrencyTypes(CurrencyTypes currencyTypes) {
		this.currencyTypes = currencyTypes;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "NT_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public NoveltyTypes getNoveltyTypes() {
		return this.noveltyTypes;
	}

	public void setNoveltyTypes(NoveltyTypes noveltyTypes) {
		this.noveltyTypes = noveltyTypes;
	}

	@Column(name = "PFP_CONCEPTONOMINA", unique = false, nullable = false, insertable = true, updatable = true, precision = 18, scale = 0)
	public Long getPfpConceptonomina() {
		return this.pfpConceptonomina;
	}

	public void setPfpConceptonomina(Long pfpConceptonomina) {
		this.pfpConceptonomina = pfpConceptonomina;
	}

	@Column(name = "PFP_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 100)
	public String getPfpDescripcion() {
		return this.pfpDescripcion;
	}

	public void setPfpDescripcion(String pfpDescripcion) {
		this.pfpDescripcion = pfpDescripcion;
	}

}