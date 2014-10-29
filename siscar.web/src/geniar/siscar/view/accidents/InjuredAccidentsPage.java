package geniar.siscar.view.accidents;

import java.util.List;

import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.InjuredPersons;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import gwork.exception.GWorkException;
import javax.faces.event.ActionEvent;
import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.paneltabset.PanelTab;

public class InjuredAccidentsPage extends AccidentGeneralsPage {

	private String pivId;
	private String pivNombre;
	private String pivDireccion;
	private String pnvTelefono;
	private Long pivEdad;
	private String pivSitioatencion;
	private String placa;
	private Long idLesionadoVehiculo;
	private List<InjuredPersons> listInjuredPersons;
	private Long idAccidente;

	private DataAccidentsService dataAccidentsService;

	private HtmlOutputText lblIdentificacion;
	private HtmlOutputText lblNombreLesionado;
	private HtmlOutputText lblDireccionLesionado;
	private HtmlOutputText lblTelefonoLesionado;
	private HtmlOutputText lblEdadLesionado;
	private HtmlOutputText lblSitioAtencion;
	private HtmlOutputText txtPlaca;
	private HtmlOutputText txtIdentificacion;

	private HtmlSelectOneMenu cbxCities;

	private HtmlCommandButton btnCrear;
	private HtmlCommandButton btnModificar;

	private PanelTab panelLesionadosVehiculos;

	public PanelTab getPanelLesionadosVehiculos() {
		return panelLesionadosVehiculos;
	}

	public void setPanelLesionadosVehiculos(PanelTab panelLesionadosVehiculos) {
		this.panelLesionadosVehiculos = panelLesionadosVehiculos;
	}

	public String getPivId() {
		return pivId;
	}

	public void setPivId(String pivId) {
		this.pivId = pivId;
	}

	public String getPivNombre() {
		return pivNombre;
	}

	public void setPivNombre(String pivNombre) {
		this.pivNombre = pivNombre;
	}

	public String getPivDireccion() {
		return pivDireccion;
	}

	public void setPivDireccion(String pivDireccion) {
		this.pivDireccion = pivDireccion;
	}

	public String getPnvTelefono() {
		return pnvTelefono;
	}

	public void setPnvTelefono(String pnvTelefono) {
		this.pnvTelefono = pnvTelefono;
	}

	public Long getPivEdad() {
		return pivEdad;
	}

	public void setPivEdad(Long pivEdad) {
		this.pivEdad = pivEdad;
	}

	public String getPivSitioatencion() {
		return pivSitioatencion;
	}

	public void setPivSitioatencion(String pivSitioatencion) {
		this.pivSitioatencion = pivSitioatencion;
	}

	public HtmlOutputText getLblIdentificacion() {
		return lblIdentificacion;
	}

	public void setLblIdentificacion(HtmlOutputText lblIdentificacion) {
		this.lblIdentificacion = lblIdentificacion;
	}

	public HtmlOutputText getLblNombreLesionado() {
		return lblNombreLesionado;
	}

	public void setLblNombreLesionado(HtmlOutputText lblNombreLesionado) {
		this.lblNombreLesionado = lblNombreLesionado;
	}

	public HtmlOutputText getLblDireccionLesionado() {
		return lblDireccionLesionado;
	}

	public void setLblDireccionLesionado(HtmlOutputText lblDireccionLesionado) {
		this.lblDireccionLesionado = lblDireccionLesionado;
	}

	public HtmlOutputText getLblTelefonoLesionado() {
		return lblTelefonoLesionado;
	}

	public void setLblTelefonoLesionado(HtmlOutputText lblTelefonoLesionado) {
		this.lblTelefonoLesionado = lblTelefonoLesionado;
	}

	public HtmlOutputText getLblEdadLesionado() {
		return lblEdadLesionado;
	}

	public void setLblEdadLesionado(HtmlOutputText lblEdadLesionado) {
		this.lblEdadLesionado = lblEdadLesionado;
	}

	public HtmlOutputText getLblSitioAtencion() {
		return lblSitioAtencion;
	}

	public void setLblSitioAtencion(HtmlOutputText lblSitioAtencion) {
		this.lblSitioAtencion = lblSitioAtencion;
	}

	public void limpiarFormulario() {

		placa = null;
		pivId = null;
		pivNombre = null;
		pivDireccion = null;
		pnvTelefono = null;
		pivEdad = null;
		pivSitioatencion = null;
		idLesionadoVehiculo = null;

	}

	public void limpiarPantalla() {

		idLesionadoVehiculo = null;
		placa = null;
		pivId = null;
		pivNombre = null;
		pivDireccion = null;
		pnvTelefono = null;
		pivEdad = null;
		pivSitioatencion = null;
		setListInjuredPersons(null);

	}

	public void action_limpiar(ActionEvent actionEvent) {
		limpiarPantalla();
	}

	public void validarDatosNulos(String pivId, String pivNombre,
			String pivDireccion, String pnvTelefono, Long pivEdad,
			String pivSitioatencion, String placa) throws GWorkException {

		if (pivId == null || pivId.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("IDENFICACION.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ panelLesionadosVehiculos.getLabel());

		if (pivNombre == null || pivNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRELESIONADO.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelLesionadosVehiculos.getLabel());
		if (pivDireccion == null || pivDireccion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCIONLESIONADO.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelLesionadosVehiculos.getLabel());

		if (pnvTelefono == null || pnvTelefono.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONOLESIONADO.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelLesionadosVehiculos.getLabel());

		if (pivEdad.longValue() > 150L)
			throw new GWorkException(Util
					.loadErrorMessageValue("EDAD.LESIONADO"));

		if (pivSitioatencion == null || pivSitioatencion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("SITIOATENCION.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelLesionadosVehiculos.getLabel());

		if (placa == null || placa.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA") + " "
					+ Util.loadErrorMessageValue("PANEL") + " "
					+ panelLesionadosVehiculos.getLabel());

	}

	public void action_crearLesionado(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			validarDatosNulos(pivId, pivNombre, pivDireccion, pnvTelefono,
					pivEdad, pivSitioatencion, placa);

			validarCaracteres(pivId, pivNombre, pivDireccion, pnvTelefono,
					pivEdad, pivSitioatencion, placa);

			validarTamanhoCaracteres(pivId, lblIdentificacion.getValue()
					.toString(), 8, panelLesionadosVehiculos.getLabel());
			validarTamanhoCaracteres(pivNombre, lblNombreLesionado.getValue()
					.toString(), 4, panelLesionadosVehiculos.getLabel());
			validarTamanhoCaracteres(pivDireccion, lblDireccionLesionado
					.getValue().toString(), 4, panelLesionadosVehiculos
					.getLabel());
			validarTamanhoCaracteres(pnvTelefono, lblTelefonoLesionado
					.getValue().toString(), 6, panelLesionadosVehiculos
					.getLabel());
			validarTamanhoCaracteres(pivSitioatencion, lblSitioAtencion
					.getValue().toString(), 4, panelLesionadosVehiculos
					.getLabel());

			validarCantidadLesionados();
			validarEstadoAccidente(idAccidente);
			dataAccidentsService
					.registroLesionadosVehiculos(pivId, pivNombre,
							pivDireccion, pnvTelefono, pivEdad,
							pivSitioatencion, placa);
			listarLesionados();
			limpiarFormulario();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_modificarLesionado(ActionEvent actionEvent) {

		try {

			if (idAccidente == null || idAccidente.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			if (idLesionadoVehiculo == null
					|| idLesionadoVehiculo.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("LESIONADO.CONSULTA"));

			validarDatosNulos(pivId, pivNombre, pivDireccion, pnvTelefono,
					pivEdad, pivSitioatencion, placa);

			validarCaracteres(pivId, pivNombre, pivDireccion, pnvTelefono,
					pivEdad, pivSitioatencion, placa);

			validarTamanhoCaracteres(pivId, lblIdentificacion.getValue()
					.toString(), 8, panelLesionadosVehiculos.getLabel());
			validarTamanhoCaracteres(pivNombre, lblNombreLesionado.getValue()
					.toString(), 4, panelLesionadosVehiculos.getLabel());
			validarTamanhoCaracteres(pivDireccion, lblDireccionLesionado
					.getValue().toString(), 8, panelLesionadosVehiculos
					.getLabel());
			validarTamanhoCaracteres(pnvTelefono, lblTelefonoLesionado
					.getValue().toString(), 6, panelLesionadosVehiculos
					.getLabel());
			validarTamanhoCaracteres(pivSitioatencion, lblSitioAtencion
					.getValue().toString(), 4, panelLesionadosVehiculos
					.getLabel());
			validarEstadoAccidente(idAccidente);
			dataAccidentsService.modificarLesionadosVehiculos(
					idLesionadoVehiculo, pivId, pivNombre, pivDireccion,
					pnvTelefono, pivEdad, pivSitioatencion, placa);

			listarLesionados();
			limpiarFormulario();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void validarCaracteres(String pivId, String pivNombre,
			String pivDireccion, String pnvTelefono, Long pivEdad,
			String pivSitioatencion, String placa) throws GWorkException {

		if (!Util.validarNumerosParametros(pivId))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL.CARACTER")
					+ " " + lblIdentificacion.getValue().toString());

		if (!Util.validarCadenaCaracteresEspeciales(pivNombre))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL")
					+ " " + lblNombreLesionado.getValue().toString());

		if (!Util.validarNumerosParametros(pnvTelefono))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL.CARACTER")
					+ " " + lblTelefonoLesionado.getValue().toString());

		if (!Util.validarNumerosParametros(pivEdad.toString()))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ " " + lblEdadLesionado.getValue().toString());

		if (!Util.validarCadenaCaracteresEspeciales(pivSitioatencion))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL")
					+ " " + lblSitioAtencion.getValue().toString());

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(placa))
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CARACTER"));
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa.toUpperCase();
	}

	public DataAccidentsService getDataAccidentsService() {
		return dataAccidentsService;
	}

	public void setDataAccidentsService(
			DataAccidentsService dataAccidentsService) {
		this.dataAccidentsService = dataAccidentsService;
	}

	public void action_registrarPlaca(ActionEvent actionEvent) {
		placa = txtPlaca.getValue().toString();
		listarLesionados();
	}

	public HtmlOutputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlOutputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public List<InjuredPersons> getListInjuredPersons() {
		return listInjuredPersons;
	}

	public void setListInjuredPersons(List<InjuredPersons> listInjuredPersons) {
		this.listInjuredPersons = listInjuredPersons;
	}

	public void listarLesionados() {

		listInjuredPersons = null;

		try {
			setListInjuredPersons(dataAccidentsService
					.lesionadosVehiculos(placa));
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlCommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(HtmlCommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public void action_cargarFormulario(ActionEvent actionEvent) {

		String identificacion = txtIdentificacion.getValue().toString();
		if (identificacion != null && identificacion.trim().length() > 0) {

			try {
				InjuredPersons injuredPersons = new InjuredPersons();
				injuredPersons = dataAccidentsService
						.consultarLesionado(identificacion);

				idLesionadoVehiculo = injuredPersons.getPivIdentificacion();
				placa = injuredPersons.getInvolvedVehicles().getHnvPlaca();
				pivId = injuredPersons.getPivId();
				pivNombre = injuredPersons.getPivNombre();
				pnvTelefono = injuredPersons.getPnvTelefono();
				pivDireccion = injuredPersons.getPivDireccion();
				pivEdad = injuredPersons.getPivEdad();
				pivSitioatencion = injuredPersons.getPivSitioatencion();

			} catch (GWorkException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}

		}

	}

	public HtmlOutputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(HtmlOutputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public Long getIdLesionadoVehiculo() {
		return idLesionadoVehiculo;
	}

	public void setIdLesionadoVehiculo(Long idLesionadoVehiculo) {
		this.idLesionadoVehiculo = idLesionadoVehiculo;
	}

	public HtmlSelectOneMenu getCbxCities() {
		return cbxCities;
	}

	public void setCbxCities(HtmlSelectOneMenu cbxCities) {
		this.cbxCities = cbxCities;
	}

	public void action_eliminarLesionado(ActionEvent actionEvent) {

		try {

			if (idAccidente == null || idAccidente.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			if (idLesionadoVehiculo == null
					|| idLesionadoVehiculo.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("LESIONADO.CONSULTA"));

			if (pivId == null || pivId.trim().length() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("IDENFICACION.NULO")
						+ Util.loadErrorMessageValue("PANEL")
						+ panelLesionadosVehiculos.getLabel());

			if (!Util.validarNumerosParametros(pivId))
				throw new GWorkException(Util
						.loadErrorMessageValue("CARACTERES.NUMERICOS")
						+ lblIdentificacion.getValue().toString());

			validarTamanhoCaracteres(pivId, lblIdentificacion.getValue()
					.toString(), 8, panelLesionadosVehiculos.getLabel());
			validarEstadoAccidente(idAccidente);
			dataAccidentsService.eliminarLesionado(pivId);
			listarLesionados();
			limpiarFormulario();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarLesionadoLista(ActionEvent actionEvent) {

		String identificacion = txtIdentificacion.getValue().toString();
		if (identificacion != null && identificacion.trim().length() > 0) {

			try {
				validarEstadoAccidente(idAccidente);
				dataAccidentsService.eliminarLesionado(identificacion);

				listarLesionados();
				limpiarFormulario();
				mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			} catch (GWorkException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}

		}
	}

	public Long getIdAccidente() {
		return idAccidente;
	}

	public void setIdAccidente(Long idAccidente) {
		this.idAccidente = idAccidente;
	}

	public void validarCantidadLesionados() throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente.longValue());

		List<InjuredPersons> cantidadLesionados = dataAccidentsService
				.cantidadLesionados(idAccidente);

		Long totalHeridos = new Long(accidents.getAccTotalHeridos());
		Long heridosRegistrados = null;

		if (cantidadLesionados == null || cantidadLesionados.size() == 0)
			heridosRegistrados = 0L;
		if (cantidadLesionados != null && cantidadLesionados.size() > 0)
			heridosRegistrados = new Long(cantidadLesionados.size());

		if (heridosRegistrados.longValue() >= totalHeridos.longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("LESIONADOS.EXCEDE"));
	}

	public void validarEstadoAccidente(Long idAccidente) throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente);

		String estadoAccidente = accidents.getAccidentsStates().getAcsNombre();

		if (estadoAccidente.equalsIgnoreCase(ViewOptionUtil.ACCIDENTE_CERRADO))
			throw new GWorkException(Util
					.loadErrorMessageValue("ACCIDENTE.CERRADO"));

	}
}
