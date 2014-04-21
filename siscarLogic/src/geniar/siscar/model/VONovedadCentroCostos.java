package geniar.siscar.model;

import java.util.Date;

public class VONovedadCentroCostos {

	private Long cocCodigo;
	private String cocNumero;
	private String cocPorcentaje;
	private Date fechaInicio;
	private Date fechaFin;
	
	public VONovedadCentroCostos() {
		super();
	}
	public Long getCocCodigo() {
		return cocCodigo;
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
	public String getCocPorcentaje() {
		return cocPorcentaje;
	}
	public void setCocPorcentaje(String cocPorcentaje) {
		this.cocPorcentaje = cocPorcentaje;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


}
