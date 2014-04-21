package geniar.siscar.model;

public class VOCargaPrepago {

	private String placa;
	private Long consumoPromedio;
	private String centroCosto;
	private Long kmAnual;
	private Long galonesAno;
	private String tipoCombustible;
	private Long valorPrepago;
	private String tipoCargo;
	private String observacion;
	private boolean visible;
	private boolean seleccion;

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getConsumoPromedio() {
		return consumoPromedio;
	}

	public void setConsumoPromedio(Long consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public Long getKmAnual() {
		return kmAnual;
	}

	public void setKmAnual(Long kmAnual) {
		this.kmAnual = kmAnual;
	}

	public Long getGalonesAno() {
		return galonesAno;
	}

	public void setGalonesAno(Long galonesAno) {
		this.galonesAno = galonesAno;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public Long getValorPrepago() {
		return valorPrepago;
	}

	public void setValorPrepago(Long valorPrepago) {
		this.valorPrepago = valorPrepago;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
