package geniar.siscar.view.accidents;

import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.paneltabset.PanelTab;

import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.logic.accidents.services.WitnessesService;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.Witnesses;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import gwork.exception.GWorkException;

public class WitnessesPage extends AccidentGeneralsPage {

	private Long wtnCodigo;
	private String wtnIdentificacion;
	private String wtnNombre;
	private String wtnDireccion;
	private String wtnTelefono;
	private Long idAccidente;
	private HtmlOutputText lblIdentificacionTestigo;
	private HtmlOutputText lblNombreTestigo;
	private HtmlOutputText lblTelefonoTestigo;
	private HtmlOutputText lblDireccionTestigo;
	private HtmlOutputText txtIdentificacion;

	private HtmlSelectOneMenu cbxCities;
	private WitnessesService witnessesService;
	private DataAccidentsService dataAccidentsService;

	private PanelTab panelTestigos;
	private List<Witnesses> listWitnesses;

	public List<Witnesses> getListWitnesses() {
		return listWitnesses;
	}

	public void setListWitnesses(List<Witnesses> listWitnesses) {
		this.listWitnesses = listWitnesses;
	}

	public PanelTab getPanelTestigos() {
		return panelTestigos;
	}

	public void setPanelTestigos(PanelTab panelTestigos) {
		this.panelTestigos = panelTestigos;
	}

	public Long getWtnCodigo() {
		return wtnCodigo;
	}

	public void setWtnCodigo(Long wtnCodigo) {
		this.wtnCodigo = wtnCodigo;
	}

	public String getWtnIdentificacion() {
		return wtnIdentificacion;
	}

	public void setWtnIdentificacion(String wtnIdentificacion) {
		this.wtnIdentificacion = wtnIdentificacion;
	}

	public String getWtnNombre() {
		return wtnNombre;
	}

	public void setWtnNombre(String wtnNombre) {
		this.wtnNombre = wtnNombre;
	}

	public String getWtnDireccion() {
		return wtnDireccion;
	}

	public void setWtnDireccion(String wtnDireccion) {
		this.wtnDireccion = wtnDireccion;
	}

	public String getWtnTelefono() {
		return wtnTelefono;
	}

	public void setWtnTelefono(String wtnTelefono) {
		this.wtnTelefono = wtnTelefono;
	}

	public void validarDatosNulos(String wtnIdentificacion, String wtnNombre,
			String wtnDireccion, String wtnTelefono, Long idAccidents)
			throws GWorkException {

		if (wtnIdentificacion == null || wtnIdentificacion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("IDENFICACION.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelTestigos.getLabel());

		if (wtnNombre == null || wtnNombre.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRETESTIGO.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelTestigos.getLabel());

		if (wtnDireccion == null || wtnDireccion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCIONTESTIGO.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelTestigos.getLabel());

		if (wtnTelefono == null || wtnTelefono.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONOTESTIGO.NULO")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ panelTestigos.getLabel());

	}

	public void validarCaracteres(String wtnIdentificacion, String wtnNombre,
			String wtnDireccion, String wtnTelefono) throws GWorkException {

		if (!Util.validarNumerosParametros(wtnIdentificacion))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL.CARACTER")
					+ " " + lblIdentificacionTestigo.getValue().toString());

		if (!Util.validarCadenaCaracteresEspeciales(wtnNombre))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL")
					+ " " + lblNombreTestigo.getValue().toString());

		if (!Util.validarNumerosParametros(wtnTelefono))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL.CARACTER")
					+ " " + lblTelefonoTestigo.getValue().toString());

	}

	public void action_crearTestigo(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			validarDatosNulos(wtnIdentificacion, wtnNombre, wtnDireccion,
					wtnTelefono, idAccidente);

			validarCaracteres(wtnIdentificacion, wtnNombre, wtnDireccion,
					wtnTelefono);

			validarTamanhoCaracteres(wtnIdentificacion,
					lblIdentificacionTestigo.getValue().toString(), 8,
					panelTestigos.getLabel());

			validarTamanhoCaracteres(wtnNombre, lblNombreTestigo.getValue()
					.toString(), 4, panelTestigos.getLabel());

			validarTamanhoCaracteres(wtnDireccion, lblDireccionTestigo
					.getValue().toString(), 4, panelTestigos.getLabel());

			validarTamanhoCaracteres(wtnTelefono, lblTelefonoTestigo.getValue()
					.toString(), 6, panelTestigos.getLabel());

			validarCantidadTestigos();
			validarEstadoAccidente(idAccidente);

			witnessesService.registrarTestigo(idAccidente, wtnIdentificacion,
					wtnNombre, wtnDireccion, wtnTelefono);
			listarTestigo();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public Long getIdAccidente() {
		return idAccidente;
	}

	public void setIdAccidente(Long idAccidente) {
		this.idAccidente = idAccidente;
	}

	public WitnessesService getWitnessesService() {
		return witnessesService;
	}

	public void setWitnessesService(WitnessesService witnessesService) {
		this.witnessesService = witnessesService;
	}

	public HtmlOutputText getLblIdentificacionTestigo() {
		return lblIdentificacionTestigo;
	}

	public void setLblIdentificacionTestigo(
			HtmlOutputText lblIdentificacionTestigo) {
		this.lblIdentificacionTestigo = lblIdentificacionTestigo;
	}

	public HtmlOutputText getLblNombreTestigo() {
		return lblNombreTestigo;
	}

	public void setLblNombreTestigo(HtmlOutputText lblNombreTestigo) {
		this.lblNombreTestigo = lblNombreTestigo;
	}

	public HtmlOutputText getLblTelefonoTestigo() {
		return lblTelefonoTestigo;
	}

	public void setLblTelefonoTestigo(HtmlOutputText lblTelefonoTestigo) {
		this.lblTelefonoTestigo = lblTelefonoTestigo;
	}

	public HtmlOutputText getLblDireccionTestigo() {
		return lblDireccionTestigo;
	}

	public void setLblDireccionTestigo(HtmlOutputText lblDireccionTestigo) {
		this.lblDireccionTestigo = lblDireccionTestigo;
	}

	public void limpiar() {

		wtnIdentificacion = null;
		wtnNombre = null;
		wtnDireccion = null;
		wtnTelefono = null;
		wtnCodigo = null;

	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public void listarTestigo() {

		try {

			setListWitnesses(null);
			setListWitnesses(witnessesService.listarTestigos(idAccidente));
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
	}

	public void action_cargarFormulario(ActionEvent actionEvent) {

		String identificaciontestigo = (String) txtIdentificacion.getValue();

		for (Witnesses witnesses : listWitnesses) {

			if (identificaciontestigo.equalsIgnoreCase(witnesses
					.getWtnIdentificacion())) {
				wtnCodigo = witnesses.getWtnCodigo().longValue();
				wtnNombre = witnesses.getWtnNombre();
				wtnIdentificacion = witnesses.getWtnIdentificacion();
				wtnDireccion = witnesses.getWtnDireccion();
				wtnTelefono = witnesses.getWtnTelefono();
				break;
			}
		}
	}

	public void action_modificarTestigo(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			if (wtnCodigo == null || wtnCodigo.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("CONSULTA.TESTIGO"));

			validarDatosNulos(wtnIdentificacion, wtnNombre, wtnDireccion,
					wtnTelefono, idAccidente);

			validarCaracteres(wtnIdentificacion, wtnNombre, wtnDireccion,
					wtnTelefono);

			validarTamanhoCaracteres(wtnIdentificacion,
					lblIdentificacionTestigo.getValue().toString(), 8,
					panelTestigos.getLabel());

			validarTamanhoCaracteres(wtnNombre, lblNombreTestigo.getValue()
					.toString(), 4, panelTestigos.getLabel());

			validarTamanhoCaracteres(wtnDireccion, lblDireccionTestigo
					.getValue().toString(), 4, panelTestigos.getLabel());

			validarTamanhoCaracteres(wtnTelefono, lblTelefonoTestigo.getValue()
					.toString(), 6, panelTestigos.getLabel());
			validarEstadoAccidente(idAccidente);
			witnessesService.modificarTestigo(wtnCodigo, idAccidente,
					wtnIdentificacion, wtnNombre, wtnDireccion, wtnTelefono);
			listarTestigo();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarTestigoForma(ActionEvent actionEvent) {

		try {
			if (idAccidente == null || idAccidente.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			if (wtnCodigo == null || wtnCodigo.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("CONSULTA.TESTIGO"));

			validarEstadoAccidente(idAccidente);
			witnessesService.eliminarTestigo(wtnCodigo);

			listarTestigo();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarTestigoTabla(ActionEvent actionEvent) {

		String identificaciontestigo = (String) txtIdentificacion.getValue();
		try {
			for (Witnesses witnesses : listWitnesses) {
				if (identificaciontestigo.equalsIgnoreCase(witnesses
						.getWtnIdentificacion())) {
					validarEstadoAccidente(idAccidente);
					witnessesService.eliminarTestigo(witnesses.getWtnCodigo()
							.longValue());
					mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"),
							false);
					listarTestigo();
					break;
				}
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public HtmlOutputText getTxtIdentificacion() {
		return txtIdentificacion;
	}

	public void setTxtIdentificacion(HtmlOutputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}

	public void validarCantidadTestigos() throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente);

		Long totalTestigos = accidents.getAccTotalTestigos();
		Long testigosRegistrados = null;

		if (listWitnesses == null || listWitnesses.size() == 0)
			testigosRegistrados = 0L;
		if (listWitnesses != null && listWitnesses.size() > 0)
			testigosRegistrados = new Long(listWitnesses.size());

		if (testigosRegistrados.longValue() >= totalTestigos.longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("TESTIGOS.EXCEDE"));
	}

	public DataAccidentsService getDataAccidentsService() {
		return dataAccidentsService;
	}

	public void setDataAccidentsService(
			DataAccidentsService dataAccidentsService) {
		this.dataAccidentsService = dataAccidentsService;
	}

	public void validarEstadoAccidente(Long idAccidente) throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente);

		String estadoAccidente = accidents.getAccidentsStates().getAcsNombre();

		if (estadoAccidente.equalsIgnoreCase(ViewOptionUtil.ACCIDENTE_CERRADO))
			throw new GWorkException(Util
					.loadErrorMessageValue("ACCIDENTE.CERRADO"));

	}

	public HtmlSelectOneMenu getCbxCities() {
		return cbxCities;
	}

	public void setCbxCities(HtmlSelectOneMenu cbxCities) {
		this.cbxCities = cbxCities;
	}
}
