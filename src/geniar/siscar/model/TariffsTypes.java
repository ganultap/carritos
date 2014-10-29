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
 * TariffsTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TARIFFS_TYPES", uniqueConstraints = {})
public class TariffsTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long trtId;
	private String trtNombre;
	private Set<Collections> collectionses = new HashSet<Collections>(0);
	private Set<Tariffs> tariffses = new HashSet<Tariffs>(0);

	// Constructors

	/** default constructor */
	public TariffsTypes() {
	}

	/** minimal constructor */
	public TariffsTypes(Long trtId, String trtNombre) {
		this.trtId = trtId;
		this.trtNombre = trtNombre;
	}

	/** full constructor */
	public TariffsTypes(Long trtId, String trtNombre, Set<Collections> collectionses, Set<Tariffs> tariffses) {
		this.trtId = trtId;
		this.trtNombre = trtNombre;
		this.collectionses = collectionses;
		this.tariffses = tariffses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="TARIFFS_TYPES_GEN", sequenceName="SQ_TARIFF_TYPES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TARIFFS_TYPES_GEN")
	@Column(name = "TRT_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getTrtId() {
		return this.trtId;
	}

	public void setTrtId(Long trtId) {
		this.trtId = trtId;
	}

	@Column(name = "TRT_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getTrtNombre() {
		return this.trtNombre;
	}

	public void setTrtNombre(String trtNombre) {
		this.trtNombre = trtNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tariffsTypes")
	public Set<Collections> getCollectionses() {
		return this.collectionses;
	}

	public void setCollectionses(Set<Collections> collectionses) {
		this.collectionses = collectionses;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "tariffsTypes")
	public Set<Tariffs> getTariffses() {
		return this.tariffses;
	}

	public void setTariffses(Set<Tariffs> tariffses) {
		this.tariffses = tariffses;
	}

}