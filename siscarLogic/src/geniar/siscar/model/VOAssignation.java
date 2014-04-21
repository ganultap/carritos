package geniar.siscar.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VOAssignation {

	private String placa;
	private Long vhcCodigo;
	private String carneAsignatario;
	private Float Tarifa;
	private String tipoAsignacion;
	private String nombreAsignatario;
	private List<String> cencos;
	private String futNombre;
	private Float vhcCapMaxTanq;
	private String ultimoKilometrajeRegistrado;	
	private Long tipoCombustible;
		
	private Long vhaCodigo;
	private Date fechaInicio;
	private Date fechaTerminacion;
	private Date fechaEntrega;
	private Date fechaDevolucion;
	private String observacionAsignacion;
	private Long kilometrajeEntrega;
	private Long kilometrajeDevolucion;
	private String observacionDevolucion;
	private String cencosAsignaciones;
	
	/*valores nuevos a calcular*/
	private Long kilometrajeRecorrido;
	private Long diasAlquilados;

	public Long getKilometrajeRecorrido() {
		return kilometrajeRecorrido;
	}

	public void setKilometrajeRecorrido(Long kilometrajeRecorrido) {
		this.kilometrajeRecorrido = kilometrajeRecorrido;
	}

	public Long getDiasAlquilados() {
		return diasAlquilados;
	}

	public void setDiasAlquilados(Long diasAlquilados) {
		this.diasAlquilados = diasAlquilados;
	}

	public VOAssignation() {
	}

	public VOAssignation(String placa, Long vhcCodigo, String carneAsignatario, String tipoAsignacion,
			String nombreAsignatario, String futNombre, String ultimoKilometrajeRegistrado) {
		super();
		this.placa = placa;
		this.vhcCodigo = vhcCodigo;
		this.carneAsignatario = carneAsignatario;
		this.tipoAsignacion = tipoAsignacion;
		this.nombreAsignatario = nombreAsignatario;
		this.futNombre = futNombre;
		this.ultimoKilometrajeRegistrado = ultimoKilometrajeRegistrado;
	}
	
	/*Métodos Nuevos*/
	public Long calcKmRecorridos(Long kmMayor, Long kmMenor){
		//System.out.println("valores km: Ent: " + getKilometrajeEntrega() + "/Dev: " + getKilometrajeDevolucion());
		Long kmRecorrido;
		
		if(kmMenor!= null && kmMenor>0){
			if(kmMayor != null && kmMayor>0)
				kmRecorrido = kmMayor - kmMenor;
			else
				kmRecorrido = 0l;
		}
		else
			kmRecorrido = 0l;
		
		return (kmRecorrido);
	}
	
	public Long calcDiasAlquilados(Date fechaMayor, Date fechaMenor){
		Long dias_miliseg;
		Long addDay=0L;
		
		if(fechaMenor!=null && fechaMenor.getTime()>0){
			if(fechaMayor!=null && fechaMayor.getTime()>0){
		
				Calendar calendario_fechaMenor = new java.util.GregorianCalendar();
				calendario_fechaMenor.setTime(fechaMenor);
				
				Calendar calendario_fechaMayor = new java.util.GregorianCalendar();
				calendario_fechaMayor.setTime(fechaMayor);
		
				dias_miliseg = calendario_fechaMayor.getTimeInMillis() - calendario_fechaMenor.getTimeInMillis();
				addDay=1L;
			}
			else
				dias_miliseg = 0l;
		}
		else
			dias_miliseg = 0l;
		
		//Muestro el resultado en días
		return ((dias_miliseg/(3600*24*1000))+addDay);
	}
	/*Fin Métodos Nuevos*/
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Long getVhcCodigo() {
		return vhcCodigo;
	}

	public void setVhcCodigo(Long vhcCodigo) {
		this.vhcCodigo = vhcCodigo;
	}

	public String getCarneAsignatario() {
		return carneAsignatario;
	}

	public void setCarneAsignatario(String carneAsignatario) {
		this.carneAsignatario = carneAsignatario;
	}

	public Float getTarifa() {
		return Tarifa;
	}

	public void setTarifa(Float tarifa) {
		Tarifa = tarifa;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}

	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	public List<String> getCencos() {
		return cencos;
	}

	public void setCencos(List<String> cencos) {
		this.cencos = cencos;
	}

	public String getFutNombre() {
		return futNombre;
	}

	public void setFutNombre(String futNombre) {
		this.futNombre = futNombre;
	}

	public Float getVhcCapMaxTanq() {
		return vhcCapMaxTanq;
	}

	public void setVhcCapMaxTanq(Float vhcCapMaxTanq) {
		this.vhcCapMaxTanq = vhcCapMaxTanq;
	}

	public String getUltimoKilometrajeRegistrado() {
		return ultimoKilometrajeRegistrado;
	}

	public void setUltimoKilometrajeRegistrado(String ultimoKilometrajeRegistrado) {
		this.ultimoKilometrajeRegistrado = ultimoKilometrajeRegistrado;
	}

	public Long getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(Long tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public Long getVhaCodigo() {
		return vhaCodigo;
	}

	public void setVhaCodigo(Long vhaCodigo) {
		this.vhaCodigo = vhaCodigo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaTerminacion() {
		return fechaTerminacion;
	}

	public void setFechaTerminacion(Date fechaTerminacion) {
		this.fechaTerminacion = fechaTerminacion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public String getObservacionAsignacion() {
		return observacionAsignacion;
	}

	public void setObservacionAsignacion(String observacionAsignacion) {
		this.observacionAsignacion = observacionAsignacion;
	}

	public Long getKilometrajeEntrega() {
		return kilometrajeEntrega;
	}

	public void setKilometrajeEntrega(Long kilometrajeEntrega) {
		this.kilometrajeEntrega = kilometrajeEntrega;
	}

	public Long getKilometrajeDevolucion() {
		return kilometrajeDevolucion;
	}

	public void setKilometrajeDevolucion(Long kilometrajeDevolucion) {
		this.kilometrajeDevolucion = kilometrajeDevolucion;
	}

	public String getObservacionDevolucion() {
		return observacionDevolucion;
	}

	public void setObservacionDevolucion(String observacionDevolucion) {
		this.observacionDevolucion = observacionDevolucion;
	}

	public String getCencosAsignaciones() {
		return cencosAsignaciones;
	}

	public void setCencosAsignaciones(String cencosAsignaciones) {
		this.cencosAsignaciones = cencosAsignaciones;
	}

}
