package geniar.siscar.model;

public class FlatFileVO {

	private Long ffCodigo;
	private String ffAsignatario;
	private String ffCarne;
	private Long ffConcepto;
	private String ffDescripcion;
	private String ffDocumento;
	private Long ffFecha;
	private Long ffMoneda;
	private Long ffUnidades;
	private String ffValor;

	public FlatFileVO() {
	}

	// public FlatFileVO(String Carne, String Asignatario, Long Concepto,
	// Long Unidades, String Valor, Long Fecha, String Documento,
	// Long Moneda, String Descripcion) {
	//
	// ffAsignatario = Asignatario;
	// ffCarne = Carne;
	// ffConcepto = Concepto;
	// ffDescripcion = Descripcion;
	// ffDocumento = Documento;
	// ffFecha = Fecha;
	// ffMoneda = Moneda;
	// ffUnidades = Unidades;
	// ffValor = Valor;
	//	}

	public String getFfAsignatario() {
		return ffAsignatario;
	}

	public String getFfCarne() {
		return ffCarne;
	}

	public Long getFfConcepto() {
		return ffConcepto;
	}

	public String getFfDescripcion() {
		return ffDescripcion;
	}

	public String getFfDocumento() {
		return ffDocumento;
	}

	public Long getFfFecha() {
		return ffFecha;
	}

	public Long getFfMoneda() {
		return ffMoneda;
	}

	public Long getFfUnidades() {
		return ffUnidades;
	}

	public String getFfValor() {
		return ffValor;
	}

	public void setFfAsignatario(String ffAsignatario) {
		this.ffAsignatario = ffAsignatario;
	}

	public void setFfCarne(String ffCarne) {
		this.ffCarne = ffCarne;
	}

	public void setFfConcepto(Long ffConcepto) {
		this.ffConcepto = ffConcepto;
	}

	public void setFfDescripcion(String ffDescripcion) {
		this.ffDescripcion = ffDescripcion;
	}

	public void setFfDocumento(String ffDocumento) {
		this.ffDocumento = ffDocumento;
	}

	public void setFfFecha(Long ffFecha) {
		this.ffFecha = ffFecha;
	}

	public void setFfMoneda(Long ffMoneda) {
		this.ffMoneda = ffMoneda;
	}

	public void setFfUnidades(Long ffUnidades) {
		this.ffUnidades = ffUnidades;
	}

	public void setFfValor(String ffValor) {
		this.ffValor = ffValor;
	}

	public Long getFfCodigo() {
		return ffCodigo;
	}

	public void setFfCodigo(Long ffCodigo) {
		this.ffCodigo = ffCodigo;
	}

}
