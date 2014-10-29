package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.PeriodService;
import geniar.siscar.model.Period;
import geniar.siscar.model.VOAssignationProof;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

/**
 * The Class ProofVehicleAssignationPage.
 */
public class ProofVehicleAssignationPage {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ProofVehicleAssignationPage.class);
	
	/** The periodo. */
	private Long periodo;
	
	/** The autorizacion comprobante. */
	private boolean autorizacionComprobante = false;
	
	/** The fecha ini. */
	private Date fechaIni;
	
	/** The fecha fin. */
	private Date fechaFin;
	
	/** The period service. */
	private PeriodService periodService;
	
	/** The list vo assignation proof. */
	private List<VOAssignationProof> listVOAssignationProof;
	
	/** The fuel proof initial service. */
	private geniar.siscar.logic.billing.services.FuelProofInitialService fuelProofInitialService;
	
	/** The assignation proof service. */
	private geniar.siscar.logic.billing.services.AssignationProofService assignationProofService;
	
	/** The cbx periodo. */
	private HtmlSelectOneMenu cbxPeriodo;
	
	/** The activar confirmacion. */
	private boolean activarConfirmacion;
	
	/** The login page. */
	private LoginPage loginPage = (LoginPage) FacesUtils
			.getManagedBean("loginPage");
	
	/** The tbl proof vehicle. */
	private HtmlDataTable tblProofVehicle;

	/**
	 * Gets the tbl proof vehicle.
	 *
	 * @return the tbl proof vehicle
	 */
	public HtmlDataTable getTblProofVehicle() {
		return tblProofVehicle;
	}

	/**
	 * Sets the tbl proof vehicle.
	 *
	 * @param tblProofVehicle the new tbl proof vehicle
	 */
	public void setTblProofVehicle(HtmlDataTable tblProofVehicle) {
		this.tblProofVehicle = tblProofVehicle;
	}

	/**
	 * Gets the cbx periodo.
	 *
	 * @return the cbx periodo
	 */
	public HtmlSelectOneMenu getCbxPeriodo() {
		return cbxPeriodo;
	}

	/**
	 * Sets the cbx periodo.
	 *
	 * @param cbxPeriodo the new cbx periodo
	 */
	public void setCbxPeriodo(HtmlSelectOneMenu cbxPeriodo) {
		this.cbxPeriodo = cbxPeriodo;
	}

	/**
	 * Gets the period service.
	 *
	 * @return the period service
	 */
	public PeriodService getPeriodService() {
		return periodService;
	}

	/**
	 * Sets the period service.
	 *
	 * @param periodService the new period service
	 */
	public void setPeriodService(PeriodService periodService) {
		this.periodService = periodService;
	}

	/**
	 * Gets the periodo.
	 *
	 * @return the periodo
	 */
	public Long getPeriodo() {
		return periodo;
	}

	/**
	 * Sets the periodo.
	 *
	 * @param periodo the new periodo
	 */
	public void setPeriodo(Long periodo) {
		this.periodo = periodo;
	}

	/**
	 * Checks if is autorizacion comprobante.
	 *
	 * @return true, if is autorizacion comprobante
	 */
	public boolean isAutorizacionComprobante() {
		return autorizacionComprobante;
	}

	/**
	 * Sets the autorizacion comprobante.
	 *
	 * @param autorizacionComprobante the new autorizacion comprobante
	 */
	public void setAutorizacionComprobante(boolean autorizacionComprobante) {
		this.autorizacionComprobante = autorizacionComprobante;
	}

	/**
	 * Gets the fecha ini.
	 *
	 * @return the fecha ini
	 */
	public Date getFechaIni() {
		return fechaIni;
	}

	/**
	 * Sets the fecha ini.
	 *
	 * @param fechaIni the new fecha ini
	 */
	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * Limpiar.
	 */
	public void limpiar() {
		periodo = new Long("-1");
		autorizacionComprobante = false;
		fechaFin = null;
		fechaIni = null;
		setListVOAssignationProof(null);
		cbxPeriodo.setDisabled(false);
	}

	/**
	 * Action_limpiar.
	 *
	 * @param actionEvent the action event
	 */
	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

	/**
	 * Listener_periodo.
	 *
	 * @param event the event
	 */
	public void listener_periodo(ValueChangeEvent event) {
		Long idPeriodo = (Long) event.getNewValue();
		Period period = null;
		if (idPeriodo != null) {
			try {
				period = periodService.consultarPeriodById(idPeriodo);

				if (period != null) {
					fechaIni = period.getPerFechaIni();
					fechaFin = period.getPerFechaFin();
				}

			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Action_consulta asignacion vhc.
	 *
	 * @param actionEvent the action event
	 */
	public void action_consultaAsignacionVHC(ActionEvent actionEvent) {

		try {

			if (periodo == null || periodo.longValue() == -1L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELEPERIODO"));

			setListVOAssignationProof(null);
			setListVOAssignationProof(assignationProofService
					.listComprobanteAsignacion(periodo));

			if (this.listVOAssignationProof == null
					|| this.listVOAssignationProof.size() == 0)
				throw new GWorkException(
						"No existe cobro de asignación para el período contable");

			if (listVOAssignationProof != null
					&& listVOAssignationProof.size() > 0)
				cbxPeriodo.setDisabled(true);
			else
				cbxPeriodo.setDisabled(false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Gets the list vo assignation proof.
	 *
	 * @return the list vo assignation proof
	 */
	public List<VOAssignationProof> getListVOAssignationProof() {
		return listVOAssignationProof;
	}

	/**
	 * Sets the list vo assignation proof.
	 *
	 * @param listVOAssignationProof the new list vo assignation proof
	 */
	public void setListVOAssignationProof(
			List<VOAssignationProof> listVOAssignationProof) {
		this.listVOAssignationProof = listVOAssignationProof;
	}

	/**
	 * Action_generar comprobante.
	 *
	 * @param event the event
	 */
	public void action_generarComprobante(ActionEvent event) {

		try {
			validarDatos(periodo, this.listVOAssignationProof);

			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Genear comprobante asignacion.
	 */
	public void genearComprobanteAsignacion() {

		try {
			List<VOAssignationProof> listVOAssignationProofSel = new ArrayList<VOAssignationProof>();
			int i = 0;
			for (VOAssignationProof assignationProof : listVOAssignationProof) {

				log.info(i + " " + "Placa :"
						+ assignationProof.getPlaca() + "Estado: "
						+ assignationProof.isSeleccion() + " Centro Costo: "
						+ assignationProof.getCentroCosto());
				i++;
				if (isChecked(assignationProof.getPlaca()))
					listVOAssignationProofSel.add(assignationProof);
			}

			log.info(listVOAssignationProofSel.size());
			assignationProofService.generarComprobanteAsignacion(periodo,
					listVOAssignationProofSel, loginPage.getLogin());
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Gets the fuel proof initial service.
	 *
	 * @return the fuel proof initial service
	 */
	public geniar.siscar.logic.billing.services.FuelProofInitialService getFuelProofInitialService() {
		return fuelProofInitialService;
	}

	/**
	 * Sets the fuel proof initial service.
	 *
	 * @param fuelProofInitialService the new fuel proof initial service
	 */
	public void setFuelProofInitialService(
			geniar.siscar.logic.billing.services.FuelProofInitialService fuelProofInitialService) {
		this.fuelProofInitialService = fuelProofInitialService;
	}

	/**
	 * Gets the assignation proof service.
	 *
	 * @return the assignation proof service
	 */
	public geniar.siscar.logic.billing.services.AssignationProofService getAssignationProofService() {
		return assignationProofService;
	}

	/**
	 * Sets the assignation proof service.
	 *
	 * @param assignationProofService the new assignation proof service
	 */
	public void setAssignationProofService(
			geniar.siscar.logic.billing.services.AssignationProofService assignationProofService) {
		this.assignationProofService = assignationProofService;
	}

	/**
	 * Validar datos.
	 *
	 * @param idPeriodo the id periodo
	 * @param listAssignations the list assignations
	 * @throws GWorkException the g work exception
	 */
	public void validarDatos(Long idPeriodo,
			List<VOAssignationProof> listAssignations) throws GWorkException {

		if (idPeriodo == null || idPeriodo.longValue() == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELEPERIODO"));

		if (listAssignations == null || listAssignations.size() == 0
				|| listAssignations.isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("LISTA.PREPAGO.NULO"));
	}

	/**
	 * Checks if is activar confirmacion.
	 *
	 * @return true, if is activar confirmacion
	 */
	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	/**
	 * Sets the activar confirmacion.
	 *
	 * @param activarConfirmacion the new activar confirmacion
	 */
	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	/**
	 * Mostrar mensaje.
	 *
	 * @param mensaje the mensaje
	 * @param buttonCancel the button cancel
	 */
	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	/**
	 * Checks if is checked.
	 *
	 * @param placa the placa
	 * @return true, if is checked
	 */
	public boolean isChecked(String placa) {
		for (VOAssignationProof assignationProof : listVOAssignationProof) {
			if (placa.equalsIgnoreCase(assignationProof.getPlaca())
					&& assignationProof.isSeleccion()) {
				return true;
			}
		}
		return false;
	}
}