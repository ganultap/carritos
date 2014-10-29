package geniar.siscar.view.billing;

import java.util.Date;

public class VOPeriodPage {
	private Long idPeriodo;
	private String nombre;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	private boolean checkState;
	
	public boolean isCheckState() {
		return checkState;
	}
	public void setCheckState(boolean checkState) {
		this.checkState = checkState;
	}
	public Long getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
