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
 * Brands entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BRANDS", uniqueConstraints = {})
public class Brands implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long brnId;
	private String brnNombre;
	private Set<Lines> lineses = new HashSet<Lines>(0);

	// Constructors

	/** default constructor */
	public Brands() {
	}

	/** minimal constructor */
	public Brands(Long brnId, String brnNombre) {
		this.brnId = brnId;
		this.brnNombre = brnNombre;
	}

	/** full constructor */
	public Brands(Long brnId, String brnNombre, Set<Lines> lineses) {
		this.brnId = brnId;
		this.brnNombre = brnNombre;
		this.lineses = lineses;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="BRANDS_GEN", sequenceName="SQ_BRANDS", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="BRANDS_GEN")
	@Column(name = "BRN_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getBrnId() {
		return this.brnId;
	}

	public void setBrnId(Long brnId) {
		this.brnId = brnId;
	}

	@Column(name = "BRN_NOMBRE", unique = false, nullable = false, insertable = true, updatable = true, length = 50)
	public String getBrnNombre() {
		return this.brnNombre;
	}

	public void setBrnNombre(String brnNombre) {
		this.brnNombre = brnNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "brands")
	public Set<Lines> getLineses() {
		return this.lineses;
	}

	public void setLineses(Set<Lines> lineses) {
		this.lineses = lineses;
	}

}