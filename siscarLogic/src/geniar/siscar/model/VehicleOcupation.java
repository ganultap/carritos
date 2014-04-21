package geniar.siscar.model;

import java.util.Date;

public class VehicleOcupation {
	
	private String codigo;
	private String placa;
	private String marca;
	private String linea;
	private Date fechaIni;
	private Date fechaFin;
	private String asignatario;
	private String estadoVehiculo;
	private String tipoVehiculo;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public Date getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getAsignatario() {
		return asignatario;
	}
	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}
	public String getEstadoVehiculo() {
		return estadoVehiculo;
	}
	public void setEstadoVehiculo(String estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
}
