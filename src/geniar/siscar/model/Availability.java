package geniar.siscar.model;

import java.util.Date;

public class Availability {

	
   private Long    codigoVehiculo;
   private String  placaVehiculo;
   private String  marca;
   private String  linea;
   private Date    fechaInicial;
   private Date    fechaFinal;
   private String  nombreAsignatario;
   private String  estadoVehiculo;
   private String  estadoSolicitud;
   private String  tipoVehiculo;
   private String  tipoAsignacion;
   private String  tipoCombustible;

   public Availability(){}
   
	public Availability(Long codigoVehiculo, String placaVehiculo,String marca,String linea,
		Date fechaInicial, Date fechaFinal, String tipoCombustible) {
	super();
	this.codigoVehiculo = codigoVehiculo;
	this.placaVehiculo  = placaVehiculo;
	this.marca          = marca;
	this.linea          = linea;
	this.fechaInicial   = fechaInicial;
	this.fechaFinal     = fechaFinal;
	this.tipoCombustible     = tipoCombustible;
  }

	public Long getCodigoVehiculo() {
		return codigoVehiculo;
	}

	public void setCodigoVehiculo(Long codigoVehiculo) {
		this.codigoVehiculo = codigoVehiculo;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}

	public void setPlacaVehiculo(String placaVehiculo) {
		this.placaVehiculo = placaVehiculo;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
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

	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
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

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}
}
