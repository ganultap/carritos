package ciat.siscar.mobile.services.model;

public class Surtidor {
	
	int surtidor;
	String nombreSurtidor;
	
	int TipoCombustible;
	
	String tarifa;
	
	public Surtidor() {
		
	}

	public Surtidor(
			int surtidor, 
			String nombreSurtidor, 
			int tipoCombustible,
			String tarifa) {
		this.surtidor = surtidor;
		this.nombreSurtidor = nombreSurtidor;
		this.TipoCombustible = tipoCombustible;
		this.tarifa = tarifa;
	}

	public int getSurtidor() {
		return surtidor;
	}

	public void setSurtidor(int surtidor) {
		this.surtidor = surtidor;
	}

	public String getNombreSurtidor() {
		return nombreSurtidor;
	}

	public void setNombreSurtidor(String nombreSurtidor) {
		this.nombreSurtidor = nombreSurtidor;
	}

	public int getTipoCombustible() {
		return TipoCombustible;
	}

	public void setTipoCombustible(int tipoCombustible) {
		TipoCombustible = tipoCombustible;
	}

	public String getTarifa() {
		return tarifa;
	}

	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}

}
