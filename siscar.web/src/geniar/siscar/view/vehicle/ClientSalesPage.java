package geniar.siscar.view.vehicle;

import geniar.siscar.logic.vehicle.services.ClientSalesService;
import geniar.siscar.model.ClientsSalesVehicles;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.ClientsSalesVehiclesDAO;
import geniar.siscar.persistence.VehiclesDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlInputTextarea;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;

public class ClientSalesPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String placa;
	private String numeroIdentificacion;
	private String nombreComprador;
	private String direccion;
	private String telefono;
	private String email;
	private String valorVenta;
	private String atFinal;
	private Date fechaEntrega;
	private Date fechaLicitacion;
	private String numeroLicitacion;
	private String placaIntra;
	private String observaciones;
	boolean validar;
	private HtmlInputText txtNumeroLicitacion;
	private HtmlInputText txtNombreComprador;
	private HtmlInputText txtTelefono;
	private HtmlInputText txtEmail;
	private HtmlInputText txtValorVta;
	private HtmlInputText txtAtFinal;
	private HtmlInputText txtPlacaIntra;
	private HtmlInputText txtPlaca;
	private HtmlInputTextarea txtObservaciones;
	private HtmlInputText txtNumeroIdentificacion;
	private HtmlInputText txtDireccion;
	private SelectInputDate selectFechaLicitacion;
	private SelectInputDate selectEntrega;
	private boolean visibleCampos = true;

	private HtmlCommandButton botonModificar;
	private HtmlCommandButton botonCrear;

	// Service
	private ClientSalesService clientSalesService;

	/**
	 * @param evento
	 */
	public void action_crearCompradorVehiculo(ActionEvent event) {
		try {
			// if (!visibleCampos)
			// validarCamposAmparoBasico(atFinal, fechaEntrega, observaciones);

			if (visibleCampos)
				validarCampos(placa, numeroIdentificacion, nombreComprador,
						direccion, telefono, email, valorVenta, atFinal,
						fechaEntrega, fechaLicitacion, numeroLicitacion,
						placaIntra, observaciones, validar);

			clientSalesService.crearCompradorVehiculo(placa,
					numeroIdentificacion, nombreComprador, direccion, telefono,
					email, valorVenta, atFinal, fechaEntrega, fechaLicitacion,
					numeroLicitacion, placaIntra, observaciones);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			resetValues();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void consultarNumeroDocumento(ValueChangeEvent event)
			throws Exception {
		try {
			String value = null;
			boolean esValido = false;
			ClientsSalesVehicles salesVehicles = null;
			if (event.getNewValue() != null) {
				value = event.getNewValue().toString();

				if (value != null && value.trim().length() != 0) {

					if (value != null && value.trim().length() != 0)
						esValido = Util
								.validarCadenaCaracteresEspecialesNumLetrasGuion(value);

					if (!esValido)
						throw new GWorkException(Util
								.loadErrorMessageValue("CEDULA.CARACTER"));

					List<ClientsSalesVehicles> listSalesVehicles = new ClientsSalesVehiclesDAO()
							.findByCsvIdentificacacion(value);
					if (listSalesVehicles != null
							&& listSalesVehicles.size() > 0) {
						salesVehicles = listSalesVehicles.get(0);
						txtEmail.setValue(salesVehicles.getCsvMail());
						txtDireccion.setValue(salesVehicles.getCsvDireccion());
						txtNombreComprador.setValue(salesVehicles
								.getCsvNombre());
						txtTelefono.setValue(salesVehicles.getCsvTelefono());
					}
				}
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void consultarPlaca(ValueChangeEvent event) throws GWorkException {
		String value = null;
		Long razonRetiro = null;
		Vehicles vehicles = null;
		try {
			if (event.getNewValue() != null) {
				value = event.getNewValue().toString();

				if (value != null && value.trim().length() != 0) {
					List<Vehicles> listVehicles = new VehiclesDAO()
							.findByVhcPlacaDiplomatica(value.toUpperCase()
									.trim());

					if (listVehicles != null && listVehicles.size() > 0) {
						vehicles = listVehicles.get(0);

						if (vehicles.getVehiclesStates().getVhsCodigo().equals(
								new Long(Util.loadMessageValue("ESTADO.2"))))
							throw new GWorkException(
									Util
											.loadErrorMessageValue("VEHICULO.PROC.VENTA"));

						if (vehicles.getVehiclesStates().getVhsCodigo().equals(
								new Long(Util.loadMessageValue("ESTADO.1"))))
							throw new GWorkException(Util
									.loadErrorMessageValue("VEHICULO.ASIGNADO"));

						if (vehicles.getVehiclesStates().getVhsCodigo()
								.longValue() == 5L)
							activarVisible();

						if (vehicles.getRetirementsReasons() != null) {
							razonRetiro = vehicles.getRetirementsReasons()
									.getRetirementsTypes().getRetCodigo();
							if (razonRetiro.equals(new Long(Util
									.loadMessageValue("ESTADO.4")))) {
								activarVisible();
								txtNumeroLicitacion.setStyleClass(Util
										.loadMessageValue("SISCAR.NOTNULL"));
								txtObservaciones.setStyleClass(Util
										.loadMessageValue("SISCAR.NOTNULL"));
								txtPlacaIntra.setStyleClass(Util
										.loadMessageValue("SISCAR.NOTNULL"));
								setValidar(true);
							}
						}
					} else {
						activarVisible();
						throw new GWorkException(Util
								.loadErrorMessageValue("PLACA.NOEXISTE"));
					}
				} else {
					activarVisible();
					txtNumeroLicitacion.setStyleClass("");
					txtObservaciones.setStyleClass("");
					txtPlacaIntra.setStyleClass("");
					setValidar(false);
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void activarVisible() {
		setVisibleCampos(true);
	}

	public void desactivarVisible() {
		setVisibleCampos(false);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarCampos(String placa, String numeroIdentificacion,
			String nombreComprador, String direccion, String telefono,
			String email, String valorVenta, String atFinal, Date fechaEntrega,
			Date fechaLicitacion, String numeroLicitacion, String placaIntra,
			String observaciones, boolean validar) throws GWorkException {

		boolean esValido = true;

		if (numeroIdentificacion != null
				&& numeroIdentificacion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMERO_ID_CLIENTE"));

		if (numeroIdentificacion != null
				&& numeroIdentificacion.trim().length() != 0)
			esValido = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(numeroIdentificacion);
		// esValido = Util.validarNumerosConsulta(numeroIdentificacion);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CEDULA.CARACTER"));

		if (numeroIdentificacion.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("CEDULA.MINIMO"));

		if (placa != null && placa.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

		if (placa != null)
			esValido = Util.validarPlaca(placa);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CARACTER"));

		if (placa != null && placa.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

		if (nombreComprador != null && nombreComprador.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRE_CLIENTE"));

		if (nombreComprador != null && nombreComprador.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRE.MINIMO"));

		if (nombreComprador != null)
			esValido = Util.validarCadenaCaracteres(nombreComprador);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRE.CARACTER"));

		if (direccion != null && direccion.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCION_CLIENTE"));

		if (direccion != null)
			esValido = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(direccion);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCION.CARACTER"));

		if (direccion != null && direccion.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("DIRECCION.MINIMO"));

		if (atFinal != null && atFinal.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALOR_FINAL_CLIENTE"));

		if (atFinal != null)
			esValido = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(atFinal);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("AT.CARACTER"));

		if (atFinal != null && atFinal.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("AT.NOVALIDO"));

		if (email != null && email.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("EMAIL.VACIO"));

		if (!Util.validarEmail(email))
			throw new GWorkException(Util
					.loadErrorMessageValue("EMAIL.NOVALIDO"));

		if (email != null && email.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("EMAIL.MINIMO"));

		/*
		 * if (fechaEntrega == null) throw new
		 * GWorkException(Util.loadErrorMessageValue("FECHA_ENT_CLIENTE"));
		 */

		if (valorVenta != null && valorVenta.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALOR_VENTA_CLIENTE"));

		esValido = Util.validarNumerosConsultaPuntos(valorVenta);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALOR.VTA.CARACTER"));

		if (valorVenta != null && valorVenta.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALOR.VTA.NOVALIDO"));

		if (telefono != null && telefono.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONO_CLIENTE"));

		if (telefono != null && telefono.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(telefono);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONO.CARACTER"));

		if (telefono != null && telefono.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("TELEFONO.MINIMO"));

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.INTRA.CARACTER"));

		if (placaIntra != null && placaIntra.trim().length() != 0)
			esValido = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(placaIntra);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.ESPECIAL"));

		if (placaIntra != null && placaIntra.trim().length() != 0
				&& placaIntra.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.INTRA.NOVALIDO"));

		if (numeroLicitacion != null && numeroLicitacion.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(numeroLicitacion);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMERO.LIC.CARACTER"));

		if (numeroLicitacion != null && numeroLicitacion.trim().length() != 0
				&& numeroLicitacion.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("NUMERO.LIC.NOVALIDO"));

		if (observaciones != null && observaciones.trim().length() != 0
				&& observaciones.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACION.NOVALIDO"));

		if (observaciones != null && observaciones.trim().length() >= 100)
			throw new GWorkException(Util
					.loadErrorMessageValue("clientSales.OBSERVACIONES.MAXLENGTH"));
		
		if (validar) {
			/*
			 * if (fechaLicitacion != null && fechaEntrega != null) if
			 * (fechaEntrega.compareTo(fechaLicitacion) > 0) throw new
			 * GWorkException(Util.loadErrorMessageValue("FECHA.ENT.MAYOR"));
			 */

			validarCamposNoObligatorios(observaciones, numeroLicitacion,
					placaIntra, fechaLicitacion, fechaEntrega);
		}
	}

	public void validarCamposAmparoBasico(String AtFinal, Date FechaEntrega,
			String observaciones) throws GWorkException {

		boolean esValido = true;

		if (AtFinal != null && AtFinal.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("VALOR_FINAL_CLIENTE"));

		if (AtFinal != null)
			esValido = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(AtFinal);

		if (AtFinal != null && AtFinal.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("AT.NOVALIDO"));

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("AT.CARACTER"));

		/*
		 * if (fechaEntrega == null) throw new
		 * GWorkException(Util.loadErrorMessageValue("FECHAENTREGA.VACIO"));
		 */

		if (observaciones != null && observaciones.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACIONES_CLIENTE"));

		if (observaciones != null && observaciones.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACION.NOVALIDO"));
		
		if (observaciones != null && observaciones.trim().length() >= 100)
			throw new GWorkException(Util
					.loadErrorMessageValue("clientSales.OBSERVACIONES.MAXLENGTH"));		
	}

	public void validarCamposNoObligatorios(String observaciones,
			String numeroLicitacion, String placaIntra, Date fechaLicitacion,
			Date fechaEntrega) throws GWorkException {

		String valLicitacion = null;
		boolean esValido = true;

		if (fechaEntrega == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHAENTREGA.VACIO"));

		if (fechaLicitacion == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("FECHA_LIC_CLIENTE"));

		if (fechaLicitacion != null && fechaEntrega != null)
			if (fechaEntrega.compareTo(fechaLicitacion) > 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHA.ENT.MAYOR"));

		if (placaIntra != null && placaIntra.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA_INTRA_ClIENTE"));

		if (placaIntra != null)
			esValido = Util
					.validarCadenaCaracteresEspecialesNumLetrasGuion(placaIntra);

		if (placaIntra != null && placaIntra.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.INTRA.NOVALIDO"));

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("CARACTER.ESPECIAL"));

		if (numeroLicitacion != null) {
			valLicitacion = numeroLicitacion.toString();
			if (valLicitacion != null && valLicitacion.trim().length() != 0)
				esValido = Util.validarNumerosConsulta(valLicitacion);
			else
				throw new GWorkException(Util
						.loadErrorMessageValue("NUMERO_LICITACION"));

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("NUMERO.LIC.CARACTER"));

			if (valLicitacion != null && valLicitacion.trim().length() < 2) {
				throw new GWorkException(Util
						.loadErrorMessageValue("NUMERO.LIC.NOVALIDO"));
			}
		}

		if (observaciones != null && observaciones.trim().length() == 0)
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACIONES_CLIENTE"));

		if (observaciones != null && observaciones.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("OBSERVACION.NOVALIDO"));
	}

	public void action_consultar(ActionEvent event) {
		try {
			consultar_datos();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void consultar_datos() throws GWorkException {
		try {
			boolean esValido = true;

			if (placa != null && placa.trim().length() != 0)
				esValido = Util.validarPlaca(placa);

			if (!esValido)
				throw new GWorkException(Util
						.loadErrorMessageValue("CRITERIO.CARACTER"));

			ClientsSalesVehicles objCSvhc = clientSalesService
					.consultarVentasVhc(placa);

			if (objCSvhc != null) {
				if (objCSvhc.getCsvAtFinal() != null)
					setAtFinal(objCSvhc.getCsvAtFinal());
				if (objCSvhc.getCsvDireccion() != null)
					setDireccion(objCSvhc.getCsvDireccion());
				if (objCSvhc.getCsvFechaEntrega() != null)
					setFechaEntrega(objCSvhc.getCsvFechaEntrega());
				if (objCSvhc.getCsvFechaLicitacion() != null)
					setFechaLicitacion(objCSvhc.getCsvFechaLicitacion());
				if (objCSvhc.getCsvIdentificacacion() != null)
					setNumeroIdentificacion(objCSvhc.getCsvIdentificacacion());
				if (objCSvhc.getCsvMail() != null)
					setEmail(objCSvhc.getCsvMail());
				if (objCSvhc.getCsvNombre() != null)
					setNombreComprador(objCSvhc.getCsvNombre());
				if (objCSvhc.getCsvObservaciones() != null)
					setObservaciones(objCSvhc.getCsvObservaciones());
				if (objCSvhc.getCsvTelefono() != null)
					setTelefono(objCSvhc.getCsvTelefono());
				if (objCSvhc.getCsvNumeroLicitacion() != null)
					setNumeroLicitacion(objCSvhc.getCsvNumeroLicitacion()
							.toString());
				if (objCSvhc.getCsvValorVenta() != null)
					setValorVenta(objCSvhc.getCsvValorVenta().toString());
				if (objCSvhc.getCsvPlacaIntra() != null)
					this.setPlacaIntra(objCSvhc.getCsvPlacaIntra().toString());

				desactivarBtnModificar(false);
				desactivarBtnCrear(true);
			} else
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void desactivarBtnModificar(boolean activo) {
		if (botonModificar == null)
			botonModificar = new HtmlCommandButton();
		botonModificar.setDisabled(activo);
	}

	public void desactivarBtnCrear(boolean activo) {
		if (botonCrear == null)
			botonCrear = new HtmlCommandButton();
		botonCrear.setDisabled(activo);
	}

	public void action_modificar(ActionEvent event) {
		modificar_datos();
	}

	public void modificar_datos() {
		try {
			if (!visibleCampos)
				validarCamposAmparoBasico(atFinal, fechaEntrega, observaciones);

			if (visibleCampos)
				validarCampos(placa, numeroIdentificacion, nombreComprador,
						direccion, telefono, email, valorVenta, atFinal,
						fechaEntrega, fechaLicitacion, numeroLicitacion,
						placaIntra, observaciones, validar);

			clientSalesService.modificarVentasVhc(placa, numeroIdentificacion,
					nombreComprador, direccion, telefono, email, valorVenta,
					atFinal, fechaEntrega, fechaLicitacion, numeroLicitacion,
					placaIntra, observaciones);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			resetValues();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_limpiar(ActionEvent event) {
		resetValues();
	}

	public void resetValues() {
		if (txtPlaca != null)
			txtPlaca.setValue(null);

		setPlaca(null);
		setAtFinal(null);
		setDireccion(null);
		setEmail(null);
		setFechaEntrega(null);
		setFechaLicitacion(null);
		setNombreComprador(null);
		setNumeroIdentificacion(null);
		setNumeroLicitacion(null);
		setObservaciones(null);
		setPlaca(null);
		setNombreComprador(null);
		setPlacaIntra(null);
		setTelefono(null);
		setValorVenta(null);

		if (txtNumeroLicitacion != null)
			txtNumeroLicitacion.setStyleClass(null);

		if (txtObservaciones != null)
			txtObservaciones.setStyleClass(null);

		if (txtPlacaIntra != null)
			txtPlacaIntra.setStyleClass(null);

		desactivarBtnModificar(true);
		desactivarBtnCrear(false);
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNombreComprador() {
		return nombreComprador;
	}

	public void setNombreComprador(String nombreComprador) {
		this.nombreComprador = nombreComprador;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getValorVenta() {
		return valorVenta;
	}

	public void setValorVenta(String valorVenta) {
		this.valorVenta = valorVenta;
	}

	public String getAtFinal() {
		return atFinal;
	}

	public void setAtFinal(String atFinal) {
		this.atFinal = atFinal;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaLicitacion() {
		return fechaLicitacion;
	}

	public void setFechaLicitacion(Date fechaLicitacion) {
		this.fechaLicitacion = fechaLicitacion;
	}

	public String getNumeroLicitacion() {
		return numeroLicitacion;
	}

	public void setNumeroLicitacion(String numeroLicitacion) {
		this.numeroLicitacion = numeroLicitacion;
	}

	public String getPlacaIntra() {
		return placaIntra;
	}

	public void setPlacaIntra(String placaIntra) {
		this.placaIntra = placaIntra;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public ClientSalesService getClientSalesService() {
		return clientSalesService;
	}

	public void setClientSalesService(ClientSalesService clientSalesService) {
		this.clientSalesService = clientSalesService;
	}

	public HtmlInputText getTxtNumeroLicitacion() {
		return txtNumeroLicitacion;
	}

	public void setTxtNumeroLicitacion(HtmlInputText txtNumeroLicitacion) {
		this.txtNumeroLicitacion = txtNumeroLicitacion;
	}

	public HtmlInputText getTxtNombreComprador() {
		return txtNombreComprador;
	}

	public void setTxtNombreComprador(HtmlInputText txtNombreComprador) {
		this.txtNombreComprador = txtNombreComprador;
	}

	public HtmlInputText getTxtTelefono() {
		return txtTelefono;
	}

	public void setTxtTelefono(HtmlInputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}

	public HtmlInputText getTxtEmail() {
		return txtEmail;
	}

	public void setTxtEmail(HtmlInputText txtEmail) {
		this.txtEmail = txtEmail;
	}

	public HtmlInputText getTxtValorVta() {
		return txtValorVta;
	}

	public void setTxtValorVta(HtmlInputText txtValorVta) {
		this.txtValorVta = txtValorVta;
	}

	public HtmlInputText getTxtAtFinal() {
		return txtAtFinal;
	}

	public void setTxtAtFinal(HtmlInputText txtAtFinal) {
		this.txtAtFinal = txtAtFinal;
	}

	public HtmlInputText getTxtPlacaIntra() {
		return txtPlacaIntra;
	}

	public void setTxtPlacaIntra(HtmlInputText txtPlacaIntra) {
		this.txtPlacaIntra = txtPlacaIntra;
	}

	public HtmlInputTextarea getTxtObservaciones() {
		return txtObservaciones;
	}

	public void setTxtObservaciones(HtmlInputTextarea txtObservaciones) {
		this.txtObservaciones = txtObservaciones;
	}

	public HtmlInputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlInputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public HtmlInputText getTxtNumeroIdentificacion() {
		return txtNumeroIdentificacion;
	}

	public void setTxtNumeroIdentificacion(HtmlInputText txtNumeroIdentificacion) {
		this.txtNumeroIdentificacion = txtNumeroIdentificacion;
	}

	public boolean isValidar() {
		return validar;
	}

	public void setValidar(boolean validar) {
		this.validar = validar;
	}

	public HtmlInputText getTxtDireccion() {
		return txtDireccion;
	}

	public void setTxtDireccion(HtmlInputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}

	public SelectInputDate getSelectFechaLicitacion() {
		return selectFechaLicitacion;
	}

	public void setSelectFechaLicitacion(SelectInputDate selectFechaLicitacion) {
		this.selectFechaLicitacion = selectFechaLicitacion;
	}

	public SelectInputDate getSelectEntrega() {
		return selectEntrega;
	}

	public void setSelectEntrega(SelectInputDate selectEntrega) {
		this.selectEntrega = selectEntrega;
	}

	public boolean isVisibleCampos() {
		return visibleCampos;
	}

	public void setVisibleCampos(boolean visibleCampos) {
		this.visibleCampos = visibleCampos;
	}

	public HtmlCommandButton getBotonModificar() {
		return botonModificar;
	}

	public void setBotonModificar(HtmlCommandButton botonModificar) {
		this.botonModificar = botonModificar;
	}

	public HtmlCommandButton getBotonCrear() {
		return botonCrear;
	}

	public void setBotonCrear(HtmlCommandButton botonCrear) {
		this.botonCrear = botonCrear;
	}

}
