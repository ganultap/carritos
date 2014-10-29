package geniar.siscar.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ClientsSalesVehicles entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CLIENTS_SALES_VEHICLES", uniqueConstraints = {})
public class ClientsSalesVehicles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long cvsCodigo;
	private Vehicles vehicles;
	private String csvIdentificacacion;
	private Date csvFechaEntrega;
	private Date csvFechaLicitacion;
	private String csvNombre;
	private String csvTelefono;
	private String csvDireccion;
	private String csvMail;
	private String csvValorVenta;
	private String csvAtFinal;
	private Long csvNumeroLicitacion;
	private String csvPlacaIntra;
	private String csvObservaciones;

	// Constructors

	/** default constructor */
	public ClientsSalesVehicles() {
	}

	/** minimal constructor */
	public ClientsSalesVehicles(Long cvsCodigo, Vehicles vehicles) {
		this.cvsCodigo = cvsCodigo;
		this.vehicles = vehicles;
	}

	/** full constructor */
	public ClientsSalesVehicles(Long cvsCodigo, Vehicles vehicles, String csvIdentificacacion, Date csvFechaEntrega,
			Date csvFechaLicitacion, String csvNombre, String csvTelefono, String csvDireccion, String csvMail,
			String csvValorVenta, String csvAtFinal, Long csvNumeroLicitacion, String csvPlacaIntra,
			String csvObservaciones) {
		this.cvsCodigo = cvsCodigo;
		this.vehicles = vehicles;
		this.csvIdentificacacion = csvIdentificacacion;
		this.csvFechaEntrega = csvFechaEntrega;
		this.csvFechaLicitacion = csvFechaLicitacion;
		this.csvNombre = csvNombre;
		this.csvTelefono = csvTelefono;
		this.csvDireccion = csvDireccion;
		this.csvMail = csvMail;
		this.csvValorVenta = csvValorVenta;
		this.csvAtFinal = csvAtFinal;
		this.csvNumeroLicitacion = csvNumeroLicitacion;
		this.csvPlacaIntra = csvPlacaIntra;
		this.csvObservaciones = csvObservaciones;
	}

	// Property accessors
	@Id
	@SequenceGenerator(name="CLIENTS_SALES_GEN", sequenceName="SQ_CLIENTS_SALES_VEHICLES", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTS_SALES_GEN")
	@Column(name = "CVS_CODIGO", unique = true, nullable = false, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCvsCodigo() {
		return this.cvsCodigo;
	}

	public void setCvsCodigo(Long cvsCodigo) {
		this.cvsCodigo = cvsCodigo;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.LAZY)
	@JoinColumn(name = "VHC_CODIGO", unique = false, nullable = false, insertable = true, updatable = true)
	public Vehicles getVehicles() {
		return this.vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	@Column(name = "CSV_IDENTIFICACACION", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCsvIdentificacacion() {
		return this.csvIdentificacacion;
	}

	public void setCsvIdentificacacion(String csvIdentificacacion) {
		this.csvIdentificacacion = csvIdentificacacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CSV_FECHA_ENTREGA", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCsvFechaEntrega() {
		return this.csvFechaEntrega;
	}

	public void setCsvFechaEntrega(Date csvFechaEntrega) {
		this.csvFechaEntrega = csvFechaEntrega;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CSV_FECHA_LICITACION", unique = false, nullable = true, insertable = true, updatable = true, length = 7)
	public Date getCsvFechaLicitacion() {
		return this.csvFechaLicitacion;
	}

	public void setCsvFechaLicitacion(Date csvFechaLicitacion) {
		this.csvFechaLicitacion = csvFechaLicitacion;
	}

	@Column(name = "CSV_NOMBRE", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCsvNombre() {
		return this.csvNombre;
	}

	public void setCsvNombre(String csvNombre) {
		this.csvNombre = csvNombre;
	}

	@Column(name = "CSV_TELEFONO", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getCsvTelefono() {
		return this.csvTelefono;
	}

	public void setCsvTelefono(String csvTelefono) {
		this.csvTelefono = csvTelefono;
	}

	@Column(name = "CSV_DIRECCION", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCsvDireccion() {
		return this.csvDireccion;
	}

	public void setCsvDireccion(String csvDireccion) {
		this.csvDireccion = csvDireccion;
	}

	@Column(name = "CSV_MAIL", unique = false, nullable = true, insertable = true, updatable = true, length = 50)
	public String getCsvMail() {
		return this.csvMail;
	}

	public void setCsvMail(String csvMail) {
		this.csvMail = csvMail;
	}

	@Column(name = "CSV_VALOR_VENTA", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getCsvValorVenta() {
		return this.csvValorVenta;
	}

	public void setCsvValorVenta(String csvValorVenta) {
		this.csvValorVenta = csvValorVenta;
	}

	@Column(name = "CSV_AT_FINAL", unique = false, nullable = true, insertable = true, updatable = true, length = 30)
	public String getCsvAtFinal() {
		return this.csvAtFinal;
	}

	public void setCsvAtFinal(String csvAtFinal) {
		this.csvAtFinal = csvAtFinal;
	}

	@Column(name = "CSV_NUMERO_LICITACION", unique = false, nullable = true, insertable = true, updatable = true, precision = 38, scale = 0)
	public Long getCsvNumeroLicitacion() {
		return this.csvNumeroLicitacion;
	}

	public void setCsvNumeroLicitacion(Long csvNumeroLicitacion) {
		this.csvNumeroLicitacion = csvNumeroLicitacion;
	}

	@Column(name = "CSV_PLACA_INTRA", unique = false, nullable = true, insertable = true, updatable = true, length = 18)
	public String getCsvPlacaIntra() {
		return this.csvPlacaIntra;
	}

	public void setCsvPlacaIntra(String csvPlacaIntra) {
		this.csvPlacaIntra = csvPlacaIntra;
	}

	@Column(name = "CSV_OBSERVACIONES", unique = false, nullable = true, insertable = true, updatable = true, length = 100)
	public String getCsvObservaciones() {
		return this.csvObservaciones;
	}

	public void setCsvObservaciones(String csvObservaciones) {
		this.csvObservaciones = csvObservaciones;
	}

}