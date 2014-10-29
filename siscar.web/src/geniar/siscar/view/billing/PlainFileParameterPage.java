package geniar.siscar.view.billing;

import java.util.List;

import geniar.siscar.logic.billing.services.PlainFileParameterService;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlDataTable;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class PlainFileParameterPage {

	private PlainFileParameterService plainFileParameterService;

	private HtmlSelectOneMenu selectTipoMonedas;
	private HtmlSelectOneMenu selectTipoNovedad;
	private HtmlInputText txtConcepto;
	private HtmlInputText txtDescripcion;
	private HtmlDataTable tblPFP;

	private String descripcion;
	private Long conceptoNomina;
	private Long idTipoMoneda;
	private Long idTipoNovedad;
	private List<PlainFileParameter> listaPFPs;

	private boolean disableCrear;

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR=1;
	private static Integer ELIMINAR=2;

	public void action_crearPFP(ActionEvent actionEvent) {
		try {
			validarDatos();
			plainFileParameterService.crearPlainFileParameter(
					idTipoMoneda, idTipoNovedad, conceptoNomina, descripcion);

			mostrarMensaje(Util.loadMessageValue("EXITO"),false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void validarDatos() throws GWorkException {

		boolean isValid = true;
		
		if (idTipoNovedad == -1) {
			throw new GWorkException(
					"Debe seleccionar el campo tipo de novedad");
		}

		if (idTipoMoneda == -1) {
			throw new GWorkException("Debe seleccionar el campo tipo de moneda");
		}
		
		if (conceptoNomina == null || 
				conceptoNomina.toString().trim().length() == 0 ) {
			throw new GWorkException(
					Util.loadErrorMessageValue("Debe ingresar el campo concepto de nomina"));
		}			
		
		if (conceptoNomina != null && conceptoNomina.toString().length() != 0)			
			isValid = Util.validarNumerosConsulta(conceptoNomina.toString());
		
		if (!isValid)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.CARACTERCONCEPTONOMINATIPPONOVEDAD"));
		
		if (descripcion == null || descripcion.toString().length() == 0 ) {
			throw new GWorkException("Debe ingresar el campo descripción");
		}
		
		if (descripcion != null && descripcion.trim().length() != 0)
			isValid = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(descripcion);
		
		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.CARACTERDESCRIPCIONTIPPONOVEDAD"));
		
		if (descripcion != null && descripcion.trim().length() != 0)
			Util.validarLimite(descripcion, 200, 3, "ERROR.LIMDESCRIPCIONTIPONOVEDAD");

		if (descripcion != null && descripcion.toString().trim().length() > 200) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.LIMDESCRIPCIONTIPONOVEDAD"));
		}
	}

	public void action_modificarPFP(ActionEvent actionEvent) {
		try {
			
			validarDatos();
			
			setActivarConfirmacion(true);
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue(
					"MENSAJE.ALERTA"), activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	
	
	public void modificarPFP() {
		try {
			plainFileParameterService.modificarPlainFileParameter(
					idTipoMoneda, idTipoNovedad,
					conceptoNomina, descripcion);

			mostrarMensaje(Util.loadMessageValue(
					"EXITO.MODIFICAR"),false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_consultarPFP(ActionEvent actionEvent) {
		try {

			if (idTipoMoneda == null || idTipoNovedad == null) {
				throw new GWorkException(
						Util.loadErrorMessageValue("DATOSVACIOS"));
			}

			if (idTipoMoneda == -1) {
				throw new GWorkException(
						"Debe seleccionar un tipo de moneda");
			}

			if (idTipoNovedad == -1) {
				throw new GWorkException(
						"Debe seleccionar el tipo de novedad");
			}

			PlainFileParameter plainFileParameter = plainFileParameterService.
			consultarPlainFileParameter(idTipoMoneda, idTipoNovedad);

			if (plainFileParameter == null) {
				throw new GWorkException("La consulta no arrojo resultados");
			}

			txtConcepto.setValue(plainFileParameter.getPfpConceptonomina());
			txtDescripcion.setValue(plainFileParameter.getPfpDescripcion());
			listaPFPs.clear();
			listaPFPs.add(plainFileParameter);
			disableCrear = true;
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarPFP(ActionEvent event) {
		try {			
			validarDatos();
			
			setActivarConfirmacion(true);
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue(
					"MENSAJE.ALERTA"), activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}
	
	public void action_dibujar(ActionEvent event) {
		
	}
	
	public void action_limpiarForma(ActionEvent actionEvent) {
		limpiar();
	}

	private void limpiar() { 
		disableCrear = false;
		selectTipoMonedas.setValue(-1);
		selectTipoNovedad.setValue(-1);
		txtConcepto.setValue("");
		txtDescripcion.setValue("");
	}

	public void mostrarMensaje(String mensaje,boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.
		getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public HtmlSelectOneMenu getSelectTipoMonedas() {
		return selectTipoMonedas;
	}

	public void setSelectTipoMonedas(HtmlSelectOneMenu selectTipoMonedas) {
		this.selectTipoMonedas = selectTipoMonedas;
	}

	public HtmlSelectOneMenu getSelectTipoNovedad() {
		return selectTipoNovedad;
	}

	public void setSelectTipoNovedad(HtmlSelectOneMenu selectTipoNovedad) {
		this.selectTipoNovedad = selectTipoNovedad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getConceptoNomina() {
		return conceptoNomina;
	}

	public void setConceptoNomina(Long conceptoNomina) {
		this.conceptoNomina = conceptoNomina;
	}

	public Long getIdTipoMoneda() {
		return idTipoMoneda;
	}

	public void setIdTipoMoneda(Long idTipoMoneda) {
		this.idTipoMoneda = idTipoMoneda;
	}

	public Long getIdTipoNovedad() {
		return idTipoNovedad;
	}

	public void setIdTipoNovedad(Long idTipoNovedad) {
		this.idTipoNovedad = idTipoNovedad;
	}

	public HtmlInputText getTxtConcepto() {
		return txtConcepto;
	}

	public void setTxtConcepto(HtmlInputText txtConcepto) {
		this.txtConcepto = txtConcepto;
	}

	public HtmlInputText getTxtDescripcion() {
		return txtDescripcion;
	}

	public void setTxtDescripcion(HtmlInputText txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public boolean isDisableCrear() {
		return disableCrear;
	}

	public void setDisableCrear(boolean disableCrear) {
		this.disableCrear = disableCrear;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}


	public PlainFileParameterService getPlainFileParameterService() {
		return plainFileParameterService;
	}

	public void setPlainFileParameterService(
			PlainFileParameterService plainFileParameterService) {
		this.plainFileParameterService = plainFileParameterService;
	}

	public HtmlDataTable getTblPFP() {
		return tblPFP;
	}

	public void setTblPFP(HtmlDataTable tblPFP) {
		this.tblPFP = tblPFP;
	}

	public List<PlainFileParameter> getListaPFPs() {
		return listaPFPs;
	}

	public void setListaPFPs(List<PlainFileParameter> listaPFPs) {
		this.listaPFPs = listaPFPs;
	}
}
