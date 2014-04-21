package geniar.siscar.model;

import java.util.Date;

public class VOprepaidFuelProof {
	
	private String prepago;
	private String carne;
	private String asignatario;
	private String placaDiplomatica;
	private String cocCencos;
	private String validarCencos;
	private Date fechaInicial;
	private Date fechaFinal;
	private Double valorCencos;
	private Double valorDistribucion;
	private Double valorDevolucionCencos;
	
	/*Variables nuevas*/
	private Double kmAnual;
	private Double galonesAno;
	private String tipoCombustible;
	private String observacion;
	private Double consumoPromedio;
	private Long idtipoCargo;

	public Long getIdtipoCargo() {
		return idtipoCargo;
	}

	public void setIdtipoCargo(Long idtipoCargo) {
		this.idtipoCargo = idtipoCargo;
	}

	public Double getKmAnual() {
		return kmAnual;
	}

	public void setKmAnual(Double kmAnual) {
		this.kmAnual = kmAnual;
	}

	public Double getGalonesAno() {
		return galonesAno;
	}

	public void setGalonesAno(Double galonesAno) {
		this.galonesAno = galonesAno;
	}

	public String getTipoCombustible() {
		return tipoCombustible;
	}

	public void setTipoCombustible(String tipoCombustible) {
		this.tipoCombustible = tipoCombustible;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Double getConsumoPromedio() {
		return consumoPromedio;
	}

	public void setConsumoPromedio(Double consumoPromedio) {
		this.consumoPromedio = consumoPromedio;
	}

	public String getCarne() {
		return carne;
	}
	
	public void setCarne(String carne) {
		this.carne = carne;
	}
	
	public String getAsignatario() {
		return asignatario;
	}
	
	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}
	
	public String getPlacaDiplomatica() {
		return placaDiplomatica;
	}
	
	public void setPlacaDiplomatica(String placaDiplomatica) {
		this.placaDiplomatica = placaDiplomatica;
	}
	
	public String getValidarCencos() {
		return validarCencos;
	}
	
	public void setValidarCencos(String validarCencos) {
		this.validarCencos = validarCencos;
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
	
	public Double getValorCencos() {
		return valorCencos;
	}

	public void setValorCencos(Double valorCencos) {
		this.valorCencos = valorCencos;
	}

	public Double getValorDevolucionCencos() {
		return valorDevolucionCencos;
	}
	
	public void setValorDevolucionCencos(Double valorDevolucionCencos) {
		this.valorDevolucionCencos = valorDevolucionCencos;
	}

	public String getPrepago() {
		return prepago;
	}

	public void setPrepago(String prepago) {
		this.prepago = prepago;
	}

	public String getCocCencos() {
		return cocCencos;
	}

	public void setCocCencos(String cocCencos) {
		this.cocCencos = cocCencos;
	}

	public Double getValorDistribucion() {
		return valorDistribucion;
	}

	public void setValorDistribucion(Double valorDistribucion) {
		this.valorDistribucion = valorDistribucion;
	}

}