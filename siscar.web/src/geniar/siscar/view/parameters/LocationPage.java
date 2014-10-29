package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.parameters.services.LocationService;
import geniar.siscar.model.Locations;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class LocationPage {

	private Long locationsTypes;
	private Long idCoutry;
	private Long idCity;
	private String lcnDescripcion;

	private HtmlSelectOneMenu cbxTipoUbicacion;
	private HtmlSelectOneMenu cbxUbicacion;
	private HtmlSelectOneMenu selectCountry;
	private HtmlSelectOneMenu selectCity;
	private HtmlInputText txtNombre;
	private HtmlInputTextarea txtDescripcion;
	private LocationService locationService;
	private boolean activarConfirmacion;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;

	public LocationPage() {

	}

	public Long getLocationsTypes() {
		return locationsTypes;
	}

	public void setLocationsTypes(Long locationsTypes) {
		this.locationsTypes = locationsTypes;
	}

	public HtmlSelectOneMenu getCbxTipoUbicacion() {
		return cbxTipoUbicacion;
	}

	public void setCbxTipoUbicacion(HtmlSelectOneMenu cbxTipoUbicacion) {
		this.cbxTipoUbicacion = cbxTipoUbicacion;
	}

	public HtmlSelectOneMenu getCbxUbicacion() {
		return cbxUbicacion;
	}

	public void setCbxUbicacion(HtmlSelectOneMenu cbxUbicacion) {
		this.cbxUbicacion = cbxUbicacion;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public LocationService getLocationService() {
		return locationService;
	}

	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public void action_crearUbicacion(ActionEvent event) {

		try {
			if (locationsTypes == null || locationsTypes.longValue() == -1L || locationsTypes.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.UBICACION"));

			if (idCoutry == null || idCoutry.longValue() == -1L || idCoutry.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("PAIS.SEL"));

			if (idCity == null || idCity.longValue() == -1L || idCity.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("CIUDAD.LOCATION"));

			if (lcnDescripcion == null || lcnDescripcion.trim().length() == 0) {
				throw new GWorkException(Util.loadErrorMessageValue("DESCRIPCION.VACIA"));
			}

			Util.validarLimite(lcnDescripcion, 50, 2, "ERROR.LIMDESCRIP");
			locationService.crearLugar(lcnDescripcion, idCity, locationsTypes);
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarLugar(ActionEvent event) {

		try {

			if (locationsTypes == null || locationsTypes.longValue() == -1L || locationsTypes.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.UBICACION"));

			if (idCoutry == null || idCoutry.longValue() == -1L || idCoutry.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("PAIS.SEL"));

			if (idCity == null || idCity.longValue() == -1L || idCity.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("CIUDAD.LOCATION"));

			if (lcnDescripcion == null || lcnDescripcion.trim().length() == 0) {
				throw new GWorkException(Util.loadErrorMessageValue("DESCRIPCION.VACIA"));
			}

			Util.validarLimite(lcnDescripcion, 50, 2, "ERROR.LIMDESCRIP");
			activarConfirmacion = true;
			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarLugar() {

		try {

			if (locationsTypes != null) {
				locationService.modificarLugar(locationsTypes, idCity, lcnDescripcion.toUpperCase());
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
				limpiar();
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_eliminarUbicaion(ActionEvent event) {

		try {

			if (locationsTypes == null || locationsTypes.longValue() == -1L || locationsTypes.longValue() == 0L)
				throw new GWorkException(Util.loadErrorMessageValue("TIPO.UBICACION"));

			activarConfirmacion = true;
			setOpcConfirmacion(ELIMINAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void elminarLugar() {

		try {

			if (locationsTypes != null) {

				locationService.eliminarLugar(idCity, locationsTypes);
				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_limpiar(ActionEvent event) {

		limpiar();

	}

	public void limpiar() {
		txtDescripcion.setValue("");
		cbxTipoUbicacion.setValue(new Long("-1"));

		SelectItemCountryPage page = (SelectItemCountryPage) FacesUtils.getManagedBean("selectItemCountryPage");

		if (selectCountry != null) {
			selectCountry.setValue(-1L);
			page.setListCountry(null);
		}
		if (selectCity != null) {
			selectCity.setValue(-1L);
			page.setListCity(null);
		}
	}

	public void listener_location(ValueChangeEvent event) {
		if (event.getNewValue() != null) {			
			Long idUbicacion = (Long) event.getNewValue();
			Long tipoUbicacion = null;
			Locations locations = null;
			Long idCiudad = null;
			if (cbxTipoUbicacion != null && !cbxTipoUbicacion.getValue().toString().equalsIgnoreCase("-1"))
				tipoUbicacion = new Long(cbxTipoUbicacion.getValue().toString());

			if (selectCity != null && !selectCity.getValue().toString().equalsIgnoreCase("-1"))
				idCiudad = new Long(selectCity.getValue().toString());

			try {
				if (idUbicacion != null && idUbicacion == -1L)
					limpiar();

				if (tipoUbicacion != null && idUbicacion != null && idUbicacion != -1L)
					locations = SearchVehicles.consultarDatosUbicacionPorIdCiudad(idCiudad, tipoUbicacion);
				if (locations != null)
					txtDescripcion.setValue(locations.getLcnDescripcion());
				else
					txtDescripcion.setValue(null);

			} catch (Exception e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	// TODO: public void validar Nombre(String nombre) throws GWorkException {
	// boolean esValido = true;

	// esValido = Util.validarCadenaCaracteres(nombre);

	// if (!esValido)
	// throw new
	// GWorkException(Util.loadErrorMessageValue("CARACTER.ESPECIAL"));

	// }

	public String getLcnDescripcion() {
		return lcnDescripcion;
	}

	public void setLcnDescripcion(String lcnDescripcion) {
		this.lcnDescripcion = lcnDescripcion;
	}

	public Long getIdCoutry() {
		return idCoutry;
	}

	public void setIdCoutry(Long idCoutry) {
		this.idCoutry = idCoutry;
	}

	public Long getIdCity() {
		return idCity;
	}

	public void setIdCity(Long idCity) {
		this.idCity = idCity;
	}

	public HtmlSelectOneMenu getSelectCountry() {
		return selectCountry;
	}

	public void setSelectCountry(HtmlSelectOneMenu selectCountry) {
		this.selectCountry = selectCountry;
	}

	public HtmlSelectOneMenu getSelectCity() {
		return selectCity;
	}

	public void setSelectCity(HtmlSelectOneMenu selectCity) {
		this.selectCity = selectCity;
	}

	public void validateMinLenght(FacesContext context, UIComponent validate, Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1 || inputText.length() > 50) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util.loadErrorMessageValue("DESCRIPCION.LENGTH"));
		}
	}

	public void setTxtDescripcion(HtmlInputTextarea txtDescripcion) {
		this.txtDescripcion = txtDescripcion;
	}

	public HtmlInputTextarea getTxtDescripcion() {
		return txtDescripcion;
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

}
