package ciat.siscar.mobile.services.model;

public class ValidacionVehiculo {

	protected String tipoAsignacion;
    protected String carneAsignado;
    protected String nombreAsignado;

    protected int 	tipoCombustible;
    protected String capacidadTanque;
    protected String ultimoKilometraje;
    protected String valorTanqueada;

    public ValidacionVehiculo() {
    	
    }
    
    public ValidacionVehiculo(
    		String tipoAsignacion,
    		String carneAsignado, 
    		String nombreAsignado,
			String capacidadTanque, 
			int tipoCombustible,
			String ultimoKilometraje, 
			String valorTanqueada) {
    	
    	this.tipoAsignacion = tipoAsignacion;
		this.carneAsignado = carneAsignado;
		this.nombreAsignado = nombreAsignado;
		this.capacidadTanque = capacidadTanque;
		this.tipoCombustible = tipoCombustible;
		this.ultimoKilometraje = ultimoKilometraje;
		this.valorTanqueada = valorTanqueada;
	}

	public String getCarneAsignado() {
		return carneAsignado;
	}

	public void setCarneAsignado(String carneAsignado) {
		this.carneAsignado = carneAsignado;
	}

	public String getNombreAsignado() {
		return nombreAsignado;
	}

	public void setNombreAsignado(String nombreAsignado) {
		this.nombreAsignado = nombreAsignado;
	}

	public String getCapacidadTanque() {
		return capacidadTanque;
	}

	public void setCapacidadTanque(String capacidadTanque) {
		this.capacidadTanque = capacidadTanque;
	}

	public int getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(int tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public String getUltimoKilometraje() {
		return ultimoKilometraje;
	}

	public void setUltimoKilometraje(String ultimoKilometraje) {
		this.ultimoKilometraje = ultimoKilometraje;
	}

	public String getValorTanqueada() {
		return valorTanqueada;
	}

	public void setValorTanqueada(String valorTanqueada) {
		this.valorTanqueada = valorTanqueada;
	}

	public String getTipoAsignacion() {
		return tipoAsignacion;
	}

	public void setTipoAsignacion(String tipoAsignacion) {
		this.tipoAsignacion = tipoAsignacion;
	}
	
	
}
