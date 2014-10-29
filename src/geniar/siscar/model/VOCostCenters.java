package geniar.siscar.model;

public class VOCostCenters {

	private Long cocCodigo;
	private String cocNumero;
	private Float cocValorPrepago;
	private String observacion;
	private boolean seleccion;
	private boolean visible;

	public VOCostCenters() {
		super();
	}

	public Long getCocCodigo() {
		return cocCodigo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public void setCocCodigo(Long cocCodigo) {
		this.cocCodigo = cocCodigo;
	}

	public String getCocNumero() {
		return cocNumero;
	}

	public void setCocNumero(String cocNumero) {
		this.cocNumero = cocNumero;
	}

	public Float getCocValorPrepago() {
		return cocValorPrepago;
	}

	public void setCocValorPrepago(Float cocValorPrepago) {
		this.cocValorPrepago = cocValorPrepago;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
