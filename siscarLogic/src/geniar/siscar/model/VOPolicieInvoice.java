package geniar.siscar.model;

import java.util.Date;

public class VOPolicieInvoice {

	private Long pinId;
	private Policies policies;
	private String pinNumeroFactura;
	private Long pinCargado;
	private Date pinFechaFactura;
	private String pinConcepto;
	private String nombrePoliza;
	private String inconsistencia;
	
	public Long getPinId() {
		return pinId;
	}

	public void setPinId(Long pinId) {
		this.pinId = pinId;
	}

	public Policies getPolicies() {
		return policies;
	}

	public void setPolicies(Policies policies) {
		this.policies = policies;
	}

	public String getPinNumeroFactura() {
		return pinNumeroFactura;
	}

	public void setPinNumeroFactura(String pinNumeroFactura) {
		this.pinNumeroFactura = pinNumeroFactura;
	}

	public Long getPinCargado() {
		return pinCargado;
	}

	public void setPinCargado(Long pinCargado) {
		this.pinCargado = pinCargado;
	}

	public Date getPinFechaFactura() {
		return pinFechaFactura;
	}

	public void setPinFechaFactura(Date pinFechaFactura) {
		this.pinFechaFactura = pinFechaFactura;
	}

	public String getPinConcepto() {
		return pinConcepto;
	}

	public void setPinConcepto(String pinConcepto) {
		this.pinConcepto = pinConcepto;
	}

	public String getNombrePoliza() {
		return nombrePoliza;
	}

	public void setNombrePoliza(String nombrePoliza) {
		this.nombrePoliza = nombrePoliza;
	}

	public String getInconsistencia() {
		return inconsistencia;
	}

	public void setInconsistencia(String inconsistencia) {
		this.inconsistencia = inconsistencia;
	}

}
