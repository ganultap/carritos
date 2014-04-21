
package ciat.siscar.mobile.services.model;

public class ValidacionSolicitante {

    protected String nombre;
    protected String tarifa;
    
    public ValidacionSolicitante() {
    	
    }
    
    public ValidacionSolicitante(String nombre, String tarifa) {
		this.nombre = nombre;
		this.tarifa = tarifa;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
    
    

}
