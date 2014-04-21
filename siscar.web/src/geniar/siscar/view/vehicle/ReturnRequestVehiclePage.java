package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.ReturnRequestService;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.view.BaseBean;

import java.util.Date;

import javax.faces.event.ActionEvent;

public class ReturnRequestVehiclePage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idClaseSolicitud;
	private Date fechaIni;
	private Date fechaFin;
	private String descripcion;

	private ReturnRequestService returnRequestService;

	public void action_crearDevolucionSolicitud(ActionEvent event) {
		try {
			returnRequestService.devolucionSolicitudVehiculo(idClaseSolicitud, fechaIni, fechaFin, descripcion);
			resetValues();
			FacesUtils.addErrorMessage("Devolución Solicitud Vehículo hecha exitosamente");
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void resetValues() {
		setIdClaseSolicitud(null);
		setFechaFin(null);
		setFechaIni(null);
		setDescripcion(null);
	}

	public String getIdClaseSolicitud() {
		return idClaseSolicitud;
	}

	public void setIdClaseSolicitud(String idClaseSolicitud) {
		this.idClaseSolicitud = idClaseSolicitud;
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

	public ReturnRequestService getReturnRequestService() {
		return returnRequestService;
	}

	public void setReturnRequestService(ReturnRequestService returnRequestService) {
		this.returnRequestService = returnRequestService;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
