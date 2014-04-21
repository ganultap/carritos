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
 * NoveltyTypes entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "NOVELTY_TYPES", schema = "", uniqueConstraints = {})
public class NoveltyTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ntId;
	private String ntNombre;
	private Set<PlainFileParameter> plainFileParameters = new HashSet<PlainFileParameter>(
			0);
	private Set<FlatFile> flatFiles = new HashSet<FlatFile>(0);

	// Constructors

	/** default constructor */
	public NoveltyTypes() {
	}

	/** minimal constructor */
	public NoveltyTypes(Long ntId) {
		this.ntId = ntId;
	}

	/** full constructor */
	public NoveltyTypes(Long ntId, String ntNombre,
			Set<PlainFileParameter> plainFileParameters, Set<FlatFile> flatFiles) {
		this.ntId = ntId;
		this.ntNombre = ntNombre;
		this.plainFileParameters = plainFileParameters;
		this.flatFiles = flatFiles;
	}

	// Property accessors
	@Id
	@Column(name = "NT_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getNtId() {
		return this.ntId;
	}

	public void setNtId(Long ntId) {
		this.ntId = ntId;
	}

	@Column(name = "NT_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 10)
	public String getNtNombre() {
		return this.ntNombre;
	}

	public void setNtNombre(String ntNombre) {
		this.ntNombre = ntNombre;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "noveltyTypes")
	public Set<PlainFileParameter> getPlainFileParameters() {
		return this.plainFileParameters;
	}

	public void setPlainFileParameters(
			Set<PlainFileParameter> plainFileParameters) {
		this.plainFileParameters = plainFileParameters;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "noveltyTypes")
	public Set<FlatFile> getFlatFiles() {
		return this.flatFiles;
	}

	public void setFlatFiles(Set<FlatFile> flatFiles) {
		this.flatFiles = flatFiles;
	}
}