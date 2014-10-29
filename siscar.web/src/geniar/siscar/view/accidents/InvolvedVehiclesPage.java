package geniar.siscar.view.accidents;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.accidents.services.DataAccidentsService;
import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.model.Accidents;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Countries;
import geniar.siscar.model.Driver;
import geniar.siscar.model.InvolvedVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import geniar.siscar.util.ViewOptionUtil;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.paneltabset.PanelTab;

public class InvolvedVehiclesPage extends AccidentGeneralsPage {

	private String hnvTipoVehiculo;
	private String hnvPlaca;
	private String hnvMarca;
	private String hnvConductor;
	private String hnvModelo;
	private String hnvIdentifConduc;
	private String hnvTelefConduc;
	private String hnvIdentifProp;
	private String hnvPropietario;
	private String hnvDireccionconductor;
	private Long idAccidente;
	private Long idVehiculo;
	private Long idPais;
	private Long idCiudad;

	private SelectItem listaCiudades[];
	private SelectItem[] listCountry;

	private HtmlOutputText lblTipoVehiculo;
	private HtmlOutputText lblPlaca;
	private HtmlOutputText lblMarca;
	private HtmlOutputText lblModelo;
	private HtmlOutputText lblNomConductor;
	private HtmlOutputText lblCedulaConductor;
	private HtmlOutputText lblDireccion;
	private HtmlOutputText lblTelefono;
	private HtmlOutputText lblCedulaPropier;
	private HtmlOutputText lblNomPropier;
	private HtmlOutputText lblPais;
	private HtmlOutputText lblCiudad;
	private HtmlSelectOneMenu cbxTipoVehiculo;

	private HtmlCommandButton btnFindTercero;

	private HtmlInputText txtCedulaConduc;
	private HtmlInputText txtNombreConduc;
	private HtmlInputText txtDireccionConduc;
	private HtmlInputText txtMarca;
	private HtmlInputText txtModelo;
	private HtmlInputText txtPlacaVHC;

	private DataAccidentsService dataAccidentsService;

	private boolean showIconFind = false;

	private List<InvolvedVehicles> listInvolvedVehicles = new ArrayList<InvolvedVehicles>();
	private PanelTab panelvehiculosInvolucrados;

	private HtmlOutputText txtPlaca;

	private ConsultsService consultsService = (ConsultsService) FacesUtils
			.getManagedBean("consultsService");

	private boolean activarConfirmacion;
	private Integer opcConfirmacion;

	private static int ELIMINAR_VEHICULO_INVOLUCRADO_TABLA = 1;
	private static int ELIMINAR_VEHICULO_INVOLUCRADO_FORM = 2;

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public HtmlOutputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlOutputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public List<InvolvedVehicles> getListInvolvedVehicles() {
		return listInvolvedVehicles;
	}

	public void setListInvolvedVehicles(
			List<InvolvedVehicles> listInvolvedVehicles) {
		this.listInvolvedVehicles = listInvolvedVehicles;
	}

	public String getHnvTipoVehiculo() {
		return hnvTipoVehiculo;
	}

	public void setHnvTipoVehiculo(String hnvTipoVehiculo) {
		this.hnvTipoVehiculo = hnvTipoVehiculo;
	}

	public String getHnvPlaca() {
		return hnvPlaca;
	}

	public void setHnvPlaca(String hnvPlaca) {
		this.hnvPlaca = hnvPlaca;
	}

	public String getHnvMarca() {
		return hnvMarca;
	}

	public void setHnvMarca(String hnvMarca) {
		this.hnvMarca = hnvMarca;
	}

	public String getHnvConductor() {
		return hnvConductor;
	}

	public void setHnvConductor(String hnvConductor) {
		this.hnvConductor = hnvConductor;
	}

	public String getHnvIdentifConduc() {
		return hnvIdentifConduc;
	}

	public void setHnvIdentifConduc(String hnvIdentifConduc) {
		this.hnvIdentifConduc = hnvIdentifConduc;
	}

	public String getHnvTelefConduc() {
		return hnvTelefConduc;
	}

	public void setHnvTelefConduc(String hnvTelefConduc) {
		this.hnvTelefConduc = hnvTelefConduc;
	}

	public String getHnvIdentifProp() {
		return hnvIdentifProp;
	}

	public void setHnvIdentifProp(String hnvIdentifProp) {
		this.hnvIdentifProp = hnvIdentifProp;
	}

	public String getHnvPropietario() {
		return hnvPropietario;
	}

	public void setHnvPropietario(String hnvPropietario) {
		this.hnvPropietario = hnvPropietario;
	}

	public String getHnvDireccionconductor() {
		return hnvDireccionconductor;
	}

	public void setHnvDireccionconductor(String hnvDireccionconductor) {
		this.hnvDireccionconductor = hnvDireccionconductor;
	}

	public String getHnvModelo() {
		return hnvModelo;
	}

	public void setHnvModelo(String hnvModelo) {
		this.hnvModelo = hnvModelo;
	}

	public void action_ingresarVehiculoInvolucrado(ActionEvent actionEvent) {
		try {

			if (idAccidente == null || idAccidente.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			validarCamposNulos(hnvTipoVehiculo, hnvPlaca, hnvMarca, hnvModelo,
					hnvIdentifConduc, hnvConductor, idPais, idCiudad);

			/** Validacion del tamanho de los valores ingresados */
			validarTamanhoCaracteres(hnvPlaca, lblPlaca.getValue().toString(),
					4, panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvMarca, lblMarca.getValue().toString(),
					3, panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvModelo,
					lblModelo.getValue().toString(), 4,
					panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvConductor, lblNomConductor.getValue()
					.toString(), 4, panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvIdentifConduc, lblCedulaConductor
					.getValue().toString(), 8, panelvehiculosInvolucrados
					.getLabel());
			if (hnvPropietario != null && hnvPropietario.trim().length() != 0)
				validarTamanhoCaracteres(hnvPropietario, lblNomPropier
						.getValue().toString(), 4, panelvehiculosInvolucrados
						.getLabel());
			if (hnvIdentifProp != null && hnvIdentifProp.trim().length() != 0)
				validarTamanhoCaracteres(hnvIdentifProp, lblCedulaPropier
						.getValue().toString(), 4, panelvehiculosInvolucrados
						.getLabel());
			if (hnvTelefConduc != null && hnvTelefConduc.trim().length() != 0)
				validarTamanhoCaracteres(hnvTelefConduc, lblTelefono.getValue()
						.toString(), 4, panelvehiculosInvolucrados.getLabel());
			if (hnvDireccionconductor != null
					&& hnvDireccionconductor.trim().length() != 0)
				validarTamanhoCaracteres(hnvDireccionconductor, lblDireccion
						.getValue().toString(), 8, panelvehiculosInvolucrados
						.getLabel());

			validarCaracteresEspeciales(hnvPlaca, hnvMarca, hnvModelo,
					hnvIdentifConduc, hnvConductor, hnvTelefConduc,
					hnvIdentifProp, hnvPropietario);

			validarCantidadVehiculosInvolucrados();
			validarEstadoAccidente(idAccidente);
			// validarVehiculoAccidente(hnvPlaca);

			dataAccidentsService.registrarVehiculosInvolucrados(
					hnvTipoVehiculo, hnvPlaca.toUpperCase(), hnvMarca
							.toUpperCase(), hnvModelo, hnvIdentifConduc,
					hnvConductor.toUpperCase(), hnvDireccionconductor,
					hnvPropietario, hnvTelefConduc, hnvIdentifProp,
					idAccidente, idCiudad);

			listarVehiculos();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void validarVehiculosIngresados(String hnvPlaca)
			throws GWorkException {

		if (listInvolvedVehicles != null) {
			for (InvolvedVehicles involvedVehicles : listInvolvedVehicles) {

				if (involvedVehicles.getHnvPlaca().equalsIgnoreCase(hnvPlaca))
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.EXISTE"));
			}
		}
	}

	public void validarCaracteresEspeciales(String hnvPlaca, String hnvMarca,
			String hnvModelo, String hnvIdentifConduc, String hnvConductor,
			String hnvTelefConduc, String hnvIdentifProp, String hnvPropietario)
			throws GWorkException {

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(hnvPlaca))
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CARACTER"));

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(hnvMarca))
			throw new GWorkException(Util
					.loadErrorMessageValue("MARCA.VHC.INVOLUCRADO")
					+ " " + lblMarca.getValue());

		if (!Util.validarNumerosConsulta(hnvModelo))
			throw new GWorkException(Util
					.loadErrorMessageValue("AÑO.VHC.CARACTER")
					+ " " + lblModelo.getValue());

		if (new Integer(hnvModelo).intValue() < 1930
				|| new Integer(hnvModelo).intValue() > 2025)
			throw new GWorkException(Util.loadErrorMessageValue("MODELO.RANGO"));

		if (!Util.validarNumerosParametros(hnvIdentifConduc))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ " " + lblCedulaConductor.getValue());

		if (!Util.validarCadenaCaracteresEspeciales(hnvConductor))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL")
					+ " " + lblNomConductor.getValue());

		if (hnvTelefConduc != null
				&& !Util.validarNumerosParametros(hnvTelefConduc))
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONOCARACTERES")
					+ " " + lblTelefono.getValue());

		if (hnvIdentifProp != null
				&& !Util.validarNumerosParametros(hnvIdentifProp))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERES.NUMERICOS")
					+ " " + lblCedulaPropier.getValue());

		if (hnvPropietario != null
				&& !Util.validarCadenaCaracteresEspeciales(hnvPropietario))
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTERESPECIAL")
					+ " " + lblNomPropier.getValue());

	}

	public void validarCamposNulos(String hnvTipoVehiculo, String placa,
			String hnvMarca, String hnvModelo, String hnvIdentifConduc,
			String hnvConductor, Long idPais, Long idCiudad)
			throws GWorkException {

		if (hnvTipoVehiculo == null
				|| hnvTipoVehiculo.equalsIgnoreCase(Util
						.loadMessageValue("SELECCIONAR")))
			throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC")
					+ " " + Util.loadErrorMessageValue("PANEL") + " "
					+ getPanelvehiculosInvolucrados().getLabel());

		if (placa == null || placa.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA") + " "
					+ Util.loadErrorMessageValue("PANEL") + " "
					+ getPanelvehiculosInvolucrados().getLabel());

		if (hnvMarca == null || hnvMarca.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA") + " "
					+ Util.loadErrorMessageValue("PANEL") + " "
					+ getPanelvehiculosInvolucrados().getLabel());

		if (hnvModelo == null || hnvModelo.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("MODELO.ANHO")
					+ " " + Util.loadErrorMessageValue("PANEL") + " "
					+ getPanelvehiculosInvolucrados().getLabel());

		if (hnvConductor == null || hnvConductor.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRECONDUCTORNULL")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ getPanelvehiculosInvolucrados().getLabel());

		if (hnvIdentifConduc == null || hnvIdentifConduc.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("CEDULACONDUCTORNULL")
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ getPanelvehiculosInvolucrados().getLabel());
		if (idPais == null || idPais.longValue() == 0L
				|| idPais.longValue() == -1L)
			throw new GWorkException(Util.loadErrorMessageValue("PAIS.SEL")
					+ Util.loadErrorMessageValue("PANEL") + " "
					+ getPanelvehiculosInvolucrados().getLabel());

		if (idCiudad == null || idCiudad.longValue() == 0L
				|| idCiudad.longValue() == -1L)
			throw new GWorkException(Util
					.loadErrorMessageValue("CIUDAD.LOCATION")
					+ " "
					+ Util.loadErrorMessageValue("PANEL")
					+ " "
					+ getPanelvehiculosInvolucrados().getLabel());

	}

	public void limpiar() {

		hnvTipoVehiculo = null;
		hnvPlaca = null;
		hnvMarca = null;
		hnvConductor = null;
		hnvModelo = null;
		hnvIdentifConduc = null;
		hnvTelefConduc = null;
		hnvIdentifProp = null;
		hnvPropietario = null;
		hnvDireccionconductor = null;
		idVehiculo = null;
		idPais = new Long("-1");
		idCiudad = new Long("-1");

	}

	public void action_eliminarVehiculoInvolucradoTabla(ActionEvent event)
			throws GWorkException {
		try {
			System.out.println(1 + hnvPlaca);
			activarConfirmacion = true;
			setOpcConfirmacion(ELIMINAR_VEHICULO_INVOLUCRADO_TABLA);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminarVehiculoInvolucradoTabla() {

		String placa = hnvPlaca;
		try {
			for (InvolvedVehicles involvedVehicles : listInvolvedVehicles) {

				if (placa.equalsIgnoreCase(involvedVehicles.getHnvPlaca())) {
					validarEstadoAccidente(idAccidente);

					/*
					 * dataAccidentsService.eliminarVehiculoInvolucrado(involvedVehicles
					 * .getHnvCodigo().longValue());
					 */

					dataAccidentsService
							.deleteVehiculoInvolucrado(involvedVehicles
									.getHnvCodigo().longValue());

					mostrarMensaje(Util
							.loadErrorMessageValue("VEHICULOINVOL.ELIMINAR"),
							false);
					listarVehiculos();
					break;
				}
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarVehiculoInvolucradoForm(ActionEvent actionEvent) {
		try {
			activarConfirmacion = true;
			setOpcConfirmacion(ELIMINAR_VEHICULO_INVOLUCRADO_FORM);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void eliminarVehiculoInvolucradoForm() {

		try {

			if (idAccidente == null || idAccidente.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			else if (idVehiculo == null || idVehiculo.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("VEHICULOINVOLUCRADO.CONSULTA"));

			validarEstadoAccidente(idAccidente);

			dataAccidentsService.eliminarVehiculoInvolucrado(idVehiculo);
			listarVehiculos();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public PanelTab getPanelvehiculosInvolucrados() {
		return panelvehiculosInvolucrados;
	}

	public void setPanelvehiculosInvolucrados(
			PanelTab panelvehiculosInvolucrados) {
		this.panelvehiculosInvolucrados = panelvehiculosInvolucrados;
	}

	public void action_cargarVehiculoInvolucrado(ActionEvent actionEvent) {

		String placa = (String) txtPlaca.getValue();

		try {

			for (InvolvedVehicles involvedVehicles : listInvolvedVehicles) {

				if (placa.equalsIgnoreCase(involvedVehicles.getHnvPlaca())) {

					idVehiculo = involvedVehicles.getHnvCodigo();
					hnvTipoVehiculo = involvedVehicles.getHnvTipoVehiculo();
					hnvPlaca = involvedVehicles.getHnvPlaca();
					hnvMarca = involvedVehicles.getHnvMarca();
					hnvModelo = involvedVehicles.getHnvModelo();
					hnvConductor = involvedVehicles.getHnvConductor();
					hnvIdentifConduc = involvedVehicles.getHnvIdentifConduc();
					hnvDireccionconductor = involvedVehicles
							.getHnvDireccionconductor();
					hnvTelefConduc = involvedVehicles.getHnvTelefConduc();
					hnvPropietario = involvedVehicles.getHnvPropietario();
					hnvIdentifProp = involvedVehicles.getHnvIdentifProp();
					if (involvedVehicles.getCities() != null)
						idPais = involvedVehicles.getCities().getCountries()
								.getCntId().longValue();
					List<Cities> ciudades = SearchVehicles
							.ConsultarUbicacionCiudad(idPais);

					listaCiudades = new SelectItem[ciudades.size() + 1];
					int i = 1;
					listaCiudades[0] = new SelectItem("-1", "--SELECCIONAR--");
					for (Cities cities : ciudades) {
						listaCiudades[i] = new SelectItem(cities.getCtsId(),
								cities.getCtsNombre());
						i++;
					}
					setListaCiudades(listaCiudades);

					if (involvedVehicles.getCities() != null)
						idCiudad = involvedVehicles.getCities().getCtsId()
								.longValue();

					break;
				}

			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void listener_busquedaEmpleado(ValueChangeEvent event)
			throws GWorkException {
		try {
			String cedulaConductor = null;
			if (event.getNewValue() != null) {
				cedulaConductor = String.valueOf(event.getNewValue());

				if (cedulaConductor != null
						&& cedulaConductor.trim().length() > 0) {
					List<Driver> drivers = consultsService.driversCIAT(
							cedulaConductor.toUpperCase(), cedulaConductor,
							cedulaConductor);

					if (drivers != null && drivers.size() > 0) {
						Driver objDriver = drivers.get(0);
						txtNombreConduc.setValue(objDriver.getDrvNombre());
						txtDireccionConduc
								.setValue(objDriver.getDrvDireccion());
						setHnvTelefConduc(objDriver.getDrvTelefono());
					}
				}
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public HtmlOutputText getLblTipoVehiculo() {
		return lblTipoVehiculo;
	}

	public void setLblTipoVehiculo(HtmlOutputText lblTipoVehiculo) {
		this.lblTipoVehiculo = lblTipoVehiculo;
	}

	public HtmlOutputText getLblPlaca() {
		return lblPlaca;
	}

	public void setLblPlaca(HtmlOutputText lblPlaca) {
		this.lblPlaca = lblPlaca;
	}

	public HtmlOutputText getLblMarca() {
		return lblMarca;
	}

	public void setLblMarca(HtmlOutputText lblMarca) {
		this.lblMarca = lblMarca;
	}

	public HtmlOutputText getLblModelo() {
		return lblModelo;
	}

	public void setLblModelo(HtmlOutputText lblModelo) {
		this.lblModelo = lblModelo;
	}

	public HtmlOutputText getLblNomConductor() {
		return lblNomConductor;
	}

	public void setLblNomConductor(HtmlOutputText lblNomConductor) {
		this.lblNomConductor = lblNomConductor;
	}

	public HtmlOutputText getLblCedulaConductor() {
		return lblCedulaConductor;
	}

	public void setLblCedulaConductor(HtmlOutputText lblCedulaConductor) {
		this.lblCedulaConductor = lblCedulaConductor;
	}

	public HtmlOutputText getLblDireccion() {
		return lblDireccion;
	}

	public void setLblDireccion(HtmlOutputText lblDireccion) {
		this.lblDireccion = lblDireccion;
	}

	public HtmlOutputText getLblTelefono() {
		return lblTelefono;
	}

	public void setLblTelefono(HtmlOutputText lblTelefono) {
		this.lblTelefono = lblTelefono;
	}

	public HtmlOutputText getLblCedulaPropier() {
		return lblCedulaPropier;
	}

	public void setLblCedulaPropier(HtmlOutputText lblCedulaPropier) {
		this.lblCedulaPropier = lblCedulaPropier;
	}

	public HtmlOutputText getLblNomPropier() {
		return lblNomPropier;
	}

	public void setLblNomPropier(HtmlOutputText lblNomPropier) {
		this.lblNomPropier = lblNomPropier;
	}

	public boolean isShowIconFind() {
		return showIconFind;
	}

	public void setShowIconFind(boolean showIconFind) {
		this.showIconFind = showIconFind;
	}

	public HtmlSelectOneMenu getCbxTipoVehiculo() {
		return cbxTipoVehiculo;
	}

	public void setCbxTipoVehiculo(HtmlSelectOneMenu cbxTipoVehiculo) {
		this.cbxTipoVehiculo = cbxTipoVehiculo;
	}

	public void listener_placaVehiculo(ValueChangeEvent changeEvent) {

		String placa = (String) changeEvent.getNewValue();
		Vehicles vehicles = null;
		if (placa != null && placa.trim().length() > 0) {

			try {

				vehicles = SearchVehicles
						.consultarVehiculosPorPlacaSinFiltros(placa
								.toUpperCase());

				if (vehicles != null) {
					hnvTipoVehiculo = Util.loadMessageValue("CIAT");
					cbxTipoVehiculo.setValue(Util.loadMessageValue("CIAT"));
					if (!Util
							.validarCadenaCaracteresEspecialesNumLetrasGuion(hnvPlaca))
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.CARACTER"));
					if (vehicles.getVhcPlacaDiplomatica() != null)
						txtPlacaVHC.setValue(vehicles.getVhcPlacaDiplomatica());
					if (vehicles.getVhcAno() != null)
						txtModelo.setValue(vehicles.getVhcAno().toString());
					if (vehicles.getLines() != null)
						txtMarca.setValue(vehicles.getLines().getBrands()
								.getBrnNombre()
								+ "-" + vehicles.getLines().getLnsNombre());
				} else {
					txtMarca.setValue("");
					txtModelo.setValue("");
				}

			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}
		}

	}

	public void listener_tipoVehiculo(ValueChangeEvent changeEvent) {

		if (changeEvent.getNewValue() != null) {

			txtPlacaVHC.setValue("");
			txtMarca.setValue("");
			txtModelo.setValue("");
		}

	}

	public HtmlInputText getTxtCedulaConduc() {
		return txtCedulaConduc;
	}

	public void setTxtCedulaConduc(HtmlInputText txtCedulaConduc) {
		this.txtCedulaConduc = txtCedulaConduc;
	}

	public HtmlInputText getTxtNombreConduc() {
		return txtNombreConduc;
	}

	public void setTxtNombreConduc(HtmlInputText txtNombreConduc) {
		this.txtNombreConduc = txtNombreConduc;
	}

	public HtmlInputText getTxtDireccionConduc() {
		return txtDireccionConduc;
	}

	public void setTxtDireccionConduc(HtmlInputText txtDireccionConduc) {
		this.txtDireccionConduc = txtDireccionConduc;
	}

	public HtmlCommandButton getBtnFindTercero() {
		return btnFindTercero;
	}

	public void setBtnFindTercero(HtmlCommandButton btnFindTercero) {
		this.btnFindTercero = btnFindTercero;
	}

	public DataAccidentsService getDataAccidentsService() {
		return dataAccidentsService;
	}

	public void setDataAccidentsService(
			DataAccidentsService dataAccidentsService) {
		this.dataAccidentsService = dataAccidentsService;
	}

	public Long getIdAccidente() {
		return idAccidente;
	}

	public void setIdAccidente(Long idAccidente) {
		this.idAccidente = idAccidente;
	}

	public void listarVehiculos() {

		try {
			listInvolvedVehicles = null;
			setListInvolvedVehicles(dataAccidentsService
					.listarVehiculosInvolucrados(idAccidente));
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarVehiculoInvolucrado(ActionEvent actionEvent) {

		try {

			if (idAccidente == null || idAccidente.longValue() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ACCIDENTE.NULO"));

			else if (idVehiculo == null || idVehiculo.longValue() == 0L)
				throw new GWorkException(Util
						.loadErrorMessageValue("VEHICULOINVOLUCRADO.CONSULTA"));
			validarCamposNulos(hnvTipoVehiculo, hnvPlaca, hnvMarca, hnvModelo,
					hnvIdentifConduc, hnvConductor, idPais, idCiudad);

			/** Validacion del tamanho de los valores ingresados */
			validarTamanhoCaracteres(hnvPlaca, lblPlaca.getValue().toString(),
					4, panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvMarca, lblMarca.getValue().toString(),
					3, panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvModelo,
					lblModelo.getValue().toString(), 4,
					panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvConductor, lblNomConductor.getValue()
					.toString(), 4, panelvehiculosInvolucrados.getLabel());
			validarTamanhoCaracteres(hnvIdentifConduc, lblCedulaConductor
					.getValue().toString(), 4, panelvehiculosInvolucrados
					.getLabel());
			if (hnvPropietario != null && hnvPropietario.trim().length() != 0)
				validarTamanhoCaracteres(hnvPropietario, lblNomPropier
						.getValue().toString(), 4, panelvehiculosInvolucrados
						.getLabel());
			if (hnvIdentifProp != null && hnvIdentifProp.trim().length() != 0)
				validarTamanhoCaracteres(hnvIdentifProp, lblCedulaPropier
						.getValue().toString(), 4, panelvehiculosInvolucrados
						.getLabel());

			validarEstadoAccidente(idAccidente);

			validarCaracteresEspeciales(hnvPlaca, hnvMarca, hnvModelo,
					hnvIdentifConduc, hnvConductor, hnvTelefConduc,
					hnvIdentifProp, hnvPropietario);

			// validarVehiculosIngresados(hnvPlaca);

			dataAccidentsService.modificarVehiculosInvolucrados(idVehiculo,
					hnvTipoVehiculo, hnvPlaca, hnvMarca, hnvModelo,
					hnvIdentifConduc, hnvConductor, hnvDireccionconductor,
					hnvPropietario, hnvTelefConduc, hnvIdentifProp,
					idAccidente, idCiudad);

			listarVehiculos();
			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public Long getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Long idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public void validarCantidadVehiculosInvolucrados() throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente);

		Long totalVehiculos = new Long(accidents.getAccVehiculosInvolucrados()
				.longValue());
		Long vehiculosRegistrados = null;

		if (listInvolvedVehicles == null || listInvolvedVehicles.size() == 0)
			vehiculosRegistrados = 0L;
		if (listInvolvedVehicles != null && listInvolvedVehicles.size() > 0)
			vehiculosRegistrados = new Long(listInvolvedVehicles.size());

		if (vehiculosRegistrados.longValue() >= totalVehiculos.longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("VEHICULOS.EXCEDE"));
	}

	public void validarEstadoAccidente(Long idAccidente) throws GWorkException {

		Accidents accidents = dataAccidentsService
				.consultarAccidente(idAccidente);

		String estadoAccidente = accidents.getAccidentsStates().getAcsNombre();

		if (estadoAccidente.equalsIgnoreCase(ViewOptionUtil.ACCIDENTE_CERRADO))
			throw new GWorkException(Util
					.loadErrorMessageValue("ACCIDENTE.CERRADO"));

	}

	public Long getIdPais() {
		return idPais;
	}

	public void setIdPais(Long idPais) {
		this.idPais = idPais;
	}

	public Long getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(Long idCiudad) {
		this.idCiudad = idCiudad;
	}

	public HtmlOutputText getLblPais() {
		return lblPais;
	}

	public void setLblPais(HtmlOutputText lblPais) {
		this.lblPais = lblPais;
	}

	public HtmlOutputText getLblCiudad() {
		return lblCiudad;
	}

	public void setLblCiudad(HtmlOutputText lblCiudad) {
		this.lblCiudad = lblCiudad;
	}

	public SelectItem[] getListaCiudades() {
		return listaCiudades;
	}

	public void setListaCiudades(SelectItem[] listaCiudades) {
		this.listaCiudades = listaCiudades;
	}

	public SelectItem[] getListCountry() {

		try {
			List<Countries> listUtilCountry = SearchParameters
					.getAllCountries();
			listCountry = new SelectItem[listUtilCountry.size() + 1];
			int i = 1;
			listCountry[0] = new SelectItem("-1", Util
					.loadMessageValue("SELECCIONAR"));
			for (Countries countries : listUtilCountry) {
				listCountry[i] = new SelectItem(countries.getCntId(), countries
						.getCntNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listCountry;
	}

	public void setListCountry(SelectItem[] listCountry) {
		this.listCountry = listCountry;
	}

	public void listener_listarCiudades(ValueChangeEvent event)
			throws GWorkException {
		Long sbCode = null;
		if (event.getNewValue() != null) {
			sbCode = (Long) event.getNewValue();
			List<Cities> ciudades = SearchVehicles
					.ConsultarUbicacionCiudad(sbCode);

			listaCiudades = new SelectItem[ciudades.size() + 1];
			int i = 1;
			listaCiudades[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Cities cities : ciudades) {
				listaCiudades[i] = new SelectItem(cities.getCtsId(), cities
						.getCtsNombre());
				i++;
			}
			setListaCiudades(listaCiudades);
		}
	}

	public HtmlInputText getTxtMarca() {
		return txtMarca;
	}

	public void setTxtMarca(HtmlInputText txtMarca) {
		this.txtMarca = txtMarca;
	}

	public HtmlInputText getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(HtmlInputText txtModelo) {
		this.txtModelo = txtModelo;
	}

	public HtmlInputText getTxtPlacaVHC() {
		return txtPlacaVHC;
	}

	public void setTxtPlacaVHC(HtmlInputText txtPlacaVHC) {
		this.txtPlacaVHC = txtPlacaVHC;
	}
}
