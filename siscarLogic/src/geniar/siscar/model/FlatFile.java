package geniar.siscar.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FlatFile entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FLAT_FILE", schema = "", uniqueConstraints = {})
public class FlatFile implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long ffCodigo;
	private Period period;
	private NoveltyTypes noveltyTypes;
	private String ffUsuario;
	private String ffCarne;
	private Long ffConcepto;
	private Long ffUnidades;
	private String ffValor;
	private Long ffFecha;
	private String ffDocumento;
	private Long ffMoneda;
	private String ffDescripcion;
	private String ffCentroCosto;
	private Long ffEstado;
	private Tariffs tariff;
	private Date ffFechaNomina;
	private Set<VhaFf> vhaFfs = new HashSet<VhaFf>(0);
	private Set<ServiceRegistry> serviceRegistries = new HashSet<ServiceRegistry>(
			0);

	// Constructors

	/** default constructor */
	public FlatFile() {
	}

	/** minimal constructor */
	public FlatFile(Long ffCodigo, Period period, NoveltyTypes noveltyTypes,
			String ffCarne, Long ffConcepto, Long ffUnidades, String ffValor,
			Long ffFecha, String ffDocumento, Long ffMoneda,
			String ffDescripcion, Long ffEstado, Tariffs tariff,
			Date FechaNomina) {
		this.ffCodigo = ffCodigo;
		this.period = period;
		this.noveltyTypes = noveltyTypes;
		this.ffCarne = ffCarne;
		this.ffConcepto = ffConcepto;
		this.ffUnidades = ffUnidades;
		this.ffValor = ffValor;
		this.ffFecha = ffFecha;
		this.ffDocumento = ffDocumento;
		this.ffMoneda = ffMoneda;
		this.ffDescripcion = ffDescripcion;
		this.ffEstado = ffEstado;
		this.tariff = tariff;
		this.ffFechaNomina = FechaNomina;
	}

	/** full constructor */
	public FlatFile(Long ffCodigo, Period period, NoveltyTypes noveltyTypes,
			String ffUsuario, String ffCarne, Long ffConcepto, Long ffUnidades,
			String ffValor, Long ffFecha, String ffDocumento, Long ffMoneda,
			String ffDescripcion, String ffCentroCosto, Set<VhaFf> vhaFfs,
			Long ffEstado, Tariffs tariff, Date FechaNomina,
			Set<ServiceRegistry> serviceRegistries) {
		this.ffCodigo = ffCodigo;
		this.period = period;
		this.noveltyTypes = noveltyTypes;
		this.ffUsuario = ffUsuario;
		this.ffCarne = ffCarne;
		this.ffConcepto = ffConcepto;
		this.ffUnidades = ffUnidades;
		this.ffValor = ffValor;
		this.ffFecha = ffFecha;
		this.ffDocumento = ffDocumento;
		this.ffMoneda = ffMoneda;
		this.ffDescripcion = ffDescripcion;
		this.ffCentroCosto = ffCentroCosto;
		this.ffEstado = ffEstado;
		this.vhaFfs = vhaFfs;
		this.tariff = tariff;
		this.ffFechaNomina = FechaNomina;
		this.serviceRegistries = serviceRegistries;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name = "FLAT_FILE_GEN", sequenceName = "SQ_FLAT_FILE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FLAT_FILE_GEN")
	@Column(name = "FF_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	public Long getFfCodigo() {
		return this.ffCodigo;
	}

	public void setFfCodigo(Long ffCodigo) {
		this.ffCodigo = ffCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "PER_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public Period getPeriod() {
		return this.period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "NT_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public NoveltyTypes getNoveltyTypes() {
		return this.noveltyTypes;
	}

	public void setNoveltyTypes(NoveltyTypes noveltyTypes) {
		this.noveltyTypes = noveltyTypes;
	}

	@Column(name = "FF_USUARIO", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getFfUsuario() {
		return this.ffUsuario;
	}

	public void setFfUsuario(String ffUsuario) {
		this.ffUsuario = ffUsuario;
	}

	@Column(name = "FF_CARNE", unique = false, nullable = false, insertable = true, updatable = true, length = 11)
	public String getFfCarne() {
		return this.ffCarne;
	}

	public void setFfCarne(String ffCarne) {
		this.ffCarne = ffCarne;
	}

	@Column(name = "FF_CONCEPTO", unique = false, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)
	public Long getFfConcepto() {
		return this.ffConcepto;
	}

	public void setFfConcepto(Long ffConcepto) {
		this.ffConcepto = ffConcepto;
	}

	@Column(name = "FF_UNIDADES", unique = false, nullable = false, insertable = true, updatable = true, precision = 8, scale = 0)
	public Long getFfUnidades() {
		return this.ffUnidades;
	}

	public void setFfUnidades(Long ffUnidades) {
		this.ffUnidades = ffUnidades;
	}

	@Column(name = "FF_VALOR", unique = false, nullable = false, insertable = true, updatable = true, length = 12)
	public String getFfValor() {
		return this.ffValor;
	}

	public void setFfValor(String ffValor) {
		this.ffValor = ffValor;
	}

	@Column(name = "FF_FECHA", unique = false, nullable = false, insertable = true, updatable = true, precision = 8, scale = 0)
	public Long getFfFecha() {
		return this.ffFecha;
	}

	public void setFfFecha(Long ffFecha) {
		this.ffFecha = ffFecha;
	}

	@Column(name = "FF_DOCUMENTO", unique = false, nullable = false, insertable = true, updatable = true, length = 10)
	public String getFfDocumento() {
		return this.ffDocumento;
	}

	public void setFfDocumento(String ffDocumento) {
		this.ffDocumento = ffDocumento;
	}

	@Column(name = "FF_MONEDA", unique = false, nullable = false, insertable = true, updatable = true, precision = 2, scale = 0)
	public Long getFfMoneda() {
		return this.ffMoneda;
	}

	public void setFfMoneda(Long ffMoneda) {
		this.ffMoneda = ffMoneda;
	}

	@Column(name = "FF_DESCRIPCION", unique = false, nullable = false, insertable = true, updatable = true, length = 35)
	public String getFfDescripcion() {
		return this.ffDescripcion;
	}

	public void setFfDescripcion(String ffDescripcion) {
		this.ffDescripcion = ffDescripcion;
	}

	@Column(name = "FF_CENTRO_COSTO", unique = false, nullable = true, insertable = true, updatable = true, length = 15)
	public String getFfCentroCosto() {
		return this.ffCentroCosto;
	}

	public void setFfCentroCosto(String ffCentroCosto) {
		this.ffCentroCosto = ffCentroCosto;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "flatFile")
	public Set<VhaFf> getVhaFfs() {
		return this.vhaFfs;
	}

	public void setVhaFfs(Set<VhaFf> vhaFfs) {
		this.vhaFfs = vhaFfs;
	}

	@Column(name = "FF_ESTADO", unique = false, nullable = true, insertable = true, updatable = true, precision = 4, scale = 0)
	public Long getFfEstado() {
		return this.ffEstado;
	}

	public void setFfEstado(Long ffEstado) {
		this.ffEstado = ffEstado;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "TRF_ID", unique = false, nullable = true, insertable = true, updatable = true)
	public Tariffs getTariff() {
		return this.tariff;
	}

	public void setTariff(Tariffs tariff) {
		this.tariff = tariff;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FECHANOMINA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getFfFechaNomina() {
		return this.ffFechaNomina;
	}

	public void setFfFechaNomina(Date ffFechaNomina) {
		this.ffFechaNomina = ffFechaNomina;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "flatFile")
	public Set<ServiceRegistry> getServiceRegistries() {
		return this.serviceRegistries;
	}

	public void setServiceRegistries(Set<ServiceRegistry> serviceRegistries) {
		this.serviceRegistries = serviceRegistries;
	}
}