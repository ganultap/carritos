package geniar.siscar.view.vehicle;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.VehicleService;
import geniar.siscar.model.Cities;
import geniar.siscar.model.Locations;
import geniar.siscar.model.Models;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.model.Vehicles;
import geniar.siscar.persistence.ModelsDAO;
import geniar.siscar.persistence.SupplyingCatalogsDAO;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.parameters.SelectItemCountryPage;
import geniar.siscar.view.parameters.SelectItemLocationPage;
import geniar.siscar.view.parameters.SelectItemSupplyingCatalogsPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;

public class VehiclePage extends BaseBean {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String vhcPlacaDiplomatica;
	private String vhcPlacaActivoFijo;
	private String vhcNumeroTl;
	private Long idBrand;
	private Long idLine;
	private Long idCountry;
	private Long idLocation;
	private Long idLocationType;
	private Long idEstadoVehiculo;
	private Long idNumCatalogo;
	private Long idModelo;
	private String numCatalogo;
	private String vhcCatalogado;
	private String vhcNumeroMotor;
	private String vhcNumeroSerie;
	private Long idTipoVehiculo;
	private Long idCuidad;
	private String vhcAno;
	private Long idTipoCombustible;
	private String vhcColor;
	private String vhcCapacidad;
	private String vhcCapacidadTanque;
	private String vhcOdometro;
	private String vhcValorComercial;
	private String vhcAnoValCom;
	private Date vhcFechaProtocolo;
	private Date vhcFechaManifiesto;
	private String vhcNumeroManifiesto;
	private String vhcNumDeclImpor;
	private String vhcRemplazaA;
	private String vhcRemplazaPor;
	private String vhcNumeroLevante;
	private Date vhcFechaLevante;
	private String vhcDocumentTrans;
	private String vhcNumeroFactura;
	private String vhcOrderCompra;
	private String vhcProveedor;
	private String vhcAtInicial;
	private String vhcCiuAduan;
	private String vhcObservaciones;
	private String lineaCatalogo;
	private String modeloCatalogo;
	private String marcaCatalogo;
	private Float vhcCargosImportacion;
	private Long vhcIdTypeTapestrie;
	private Long vhcIdTypeTraction;
	private Long vhcIdTypeTransmissions;
	private String vhcClilindraje;
	private String vhcVidaUtil;
	private Float vhcValorCIF;
	private Float vhcValorFOB;
	private String vhcModelo;
	private String vhcAnofabricacion;
	private String vhcMesfabricacion;
	private Long vhcPais;
	private boolean locationVisible;
	private HtmlInputText txtVidaUtil;
	private HtmlInputText txtValorFob;
	private HtmlInputText txtValorCIF;
	private HtmlInputText txtProveedor;
	private HtmlInputText txtOrdenCompra;
	private HtmlInputText txtCargosImportacion;
	private HtmlInputText txtColor;
	private HtmlSelectOneMenu selectOneMenuUbicacion;
	private HtmlSelectOneMenu selectOneMenuTipoUbicacion;
	private HtmlSelectOneMenu selectCatalogos;
	private HtmlSelectOneMenu selectCity;
	private HtmlSelectOneMenu selectCountry;
	private HtmlSelectOneMenu selectModelo;
	private String countryName;

	// Service
	private VehicleService vehicleService;

	private ConsultsService consultService;
	//
	private HtmlCommandButton htmlButtonCrear;

	private boolean showEstate = false;
	private boolean showPopupCities = false;
	private boolean activarConfirmacion;

	/**
	 * 
	 * @param evento
	 */
	public void action_crear(ActionEvent event) {
		try {
			String idLinea = (String) FacesUtils.getSession().getAttribute("idLinea");
			String idUbicacion = null;
			if (idLinea == null && idNumCatalogo != null && !idNumCatalogo.equals(-1L))
				throw new GWorkException(Util.loadErrorMessageValue("CATALOGO.SEL"));

			if (selectOneMenuUbicacion != null)
				idUbicacion = selectOneMenuUbicacion.getValue().toString();

			validarCamposVehiculo(vhcNumeroTl, vhcPlacaDiplomatica, vhcPlacaActivoFijo, vhcClilindraje, vhcNumeroMotor,
					vhcNumeroSerie, idTipoVehiculo, vhcAno, vhcColor, idTipoCombustible, vhcNumeroManifiesto,
					vhcNumDeclImpor, vhcNumeroLevante, vhcDocumentTrans, vhcNumeroFactura, vhcOrderCompra,
					vhcProveedor, vhcAtInicial, vhcCiuAduan, vhcObservaciones, vhcRemplazaA, vhcCapacidad,
					vhcCapacidadTanque, vhcCatalogado, vhcAnoValCom, vhcValorComercial, idTipoVehiculo, vhcIdTypeTraction,
					vhcIdTypeTapestrie, vhcIdTypeTransmissions, idLocationType, vhcOdometro, vhcVidaUtil, vhcValorCIF,
					vhcValorFOB, vhcCargosImportacion, vhcAnofabricacion, vhcMesfabricacion, idModelo, idNumCatalogo,
					idLocation);

			vehicleService.crearVehiculo(vhcNumeroTl, vhcPlacaDiplomatica, vhcPlacaActivoFijo, new Long(idLinea),
					numCatalogo, vhcNumeroMotor, vhcNumeroSerie, idTipoVehiculo, new Long(vhcAno), vhcColor, new Long(
							idUbicacion), idTipoCombustible, vhcOdometro, new Long(vhcCapacidad), new Float(vhcCapacidadTanque), vhcValorComercial,
					vhcAnoValCom, vhcFechaProtocolo, vhcNumeroManifiesto, vhcFechaManifiesto, vhcNumDeclImpor,
					vhcRemplazaPor, vhcNumeroLevante, vhcFechaLevante, vhcDocumentTrans, vhcNumeroFactura,
					vhcOrderCompra, vhcProveedor, vhcAtInicial, vhcCiuAduan, vhcVidaUtil, vhcValorCIF, vhcValorFOB,
					vhcCargosImportacion, vhcObservaciones, vhcIdTypeTapestrie, vhcIdTypeTraction,
					vhcIdTypeTransmissions, vhcClilindraje, modeloCatalogo, vhcRemplazaA, idCuidad, new Long(
							vhcAnofabricacion), new Long(vhcMesfabricacion));

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void validarCamposVehiculo(String vhcNumeroTl, String vhcPlacaDiplomatica, String vhcPlacaActivoFijo,
			String vhcClilindraje, String vhcNumeroMotor, String vhcNumeroSerie, Long typesVehicles, String vhscAno,
			String vhcColor, Long typesFuels, String vhcNumeroManifiesto, String vhcNumDeclImpor,
			String vhcNumeroLevante, String vhcDocumentTrans, String vhcNumeroFactura, String vhcOrderCompra,
			String vhcProveedor, String vhcAtInicial, String vhcCiuAduan, String vhcObservaciones, String vhcRemplazaA,
			String vhcCapacidad, String vhcCapacidadTanque, String vhcCatalogado, String vhcAnoValCom, String vhcValorComercial,
			Long idTipoVehiculo, Long vhcIdTypeTraction, Long vhcIdTypeTapestrie, Long vhcIdTypeTransmissions,
			Long idLocationType, String vhcOdometro, String vhcVidaUtil, Float vhcValorCIF, Float vhcValorFOB,
			Float vhcCargosImportacion, String vhcAnofabricacion, String vhcMesfabricacion, Long idModelo,
			Long idNumCatalogo, Long idUbicacion) throws GWorkException {

		String combustible = null;
		String valorComercial = null;
		String valorFOB = null;
		String valorCIF = null;
		boolean esValido = true;
		int valorAnoValCom = 0;
		int valorAnoFab = 0;
		int valorAno = 0;

		if (idModelo != null && idModelo.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("MODELO"));

		if (idNumCatalogo != null && idNumCatalogo.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("CATALOGO.SEL"));

		if (vhcPlacaDiplomatica != null && vhcPlacaDiplomatica.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA"));

		if (vhcPlacaDiplomatica != null)
			esValido = Util.validarPlaca(vhcPlacaDiplomatica);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.INCORRECTO"));

		if (vhcPlacaDiplomatica != null && vhcPlacaDiplomatica.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

		if (vhcNumeroTl != null && vhcNumeroTl.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("TL.VACIO"));

		if (vhcNumeroTl != null)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroTl);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.TL.CARACTER"));

		if (vhcNumeroTl != null && vhcNumeroTl.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.TL.MINIMO"));

		if (vhcPlacaActivoFijo != null && vhcPlacaActivoFijo.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcPlacaActivoFijo);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("ACTIVO.FIJO.CARACTER"));

		if (vhcPlacaActivoFijo != null && vhcPlacaActivoFijo.trim().length() != 0
				&& vhcPlacaActivoFijo.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("ACTIVO.FIJO.MINIMO"));

		if (idTipoVehiculo != null && idTipoVehiculo.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("TIPO.VHC"));

		if (vhcIdTypeTraction != null && vhcIdTypeTraction.equals(-1l))
			throw new GWorkException(Util.loadErrorMessageValue("TRACCION"));

		if (vhcIdTypeTapestrie != null && vhcIdTypeTapestrie.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("TAPICERIA"));

		if (vhcIdTypeTransmissions != null && vhcIdTypeTransmissions.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("TRANSMICION"));

		if (idLocationType != null && idLocationType.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("TIPO.UBICACION"));

		if (idUbicacion == null || idUbicacion.equals(-1L))
			throw new GWorkException(Util.loadErrorMessageValue("UBICACION"));

		if (vhcOdometro != null && vhcOdometro.equalsIgnoreCase("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("ODOMETRO"));

		if (vhcClilindraje != null && vhcClilindraje.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CILINDRAJE"));

		if (vhcClilindraje != null && vhcClilindraje.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(vhcClilindraje);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("CILINDRAJE.CARACTER"));

		if (vhcClilindraje != null && vhcClilindraje.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("CILINDRAJE.MINIMO"));

		if (vhcNumeroMotor != null && vhcNumeroMotor.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NUMEROMOTOR"));

		if (vhcNumeroMotor != null) {
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroMotor);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("NUMERO.MOTOR.CARACTER"));

			if (vhcNumeroMotor != null && vhcNumeroMotor.trim().length() < 2)
				throw new GWorkException(Util.loadErrorMessageValue("NUMERO.MOTOR.MINIMO"));
		}

		if (vhcNumeroSerie != null && vhcNumeroSerie.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NUMEROSERIE"));

		if (vhcNumeroSerie != null)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroSerie);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.SERIE.CARACTER"));

		if (vhcNumeroSerie != null && vhcNumeroSerie.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.SERIE.MINIMO"));

		if (typesFuels != null)
			combustible = typesFuels.toString();

		if (combustible.equalsIgnoreCase("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("TIPOCOM.SEL"));

		if (vhcRemplazaA != null && vhcRemplazaA.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcRemplazaA);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.ANTERIOR.CARACTER"));

		if (vhcRemplazaA != null && vhcRemplazaA.trim().length() != 0 && vhcRemplazaA.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PLACA.ANTERIOR.MINIMO"));

		if (vhcAno != null && vhcAno.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(vhcAno);
		else
			throw new GWorkException(Util.loadErrorMessageValue("MODELO.SEL"));

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("AÑO.VHC.CARACTER"));

		if (vhcAno != null && vhcAno.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("AÑO.VHC.MINIMO"));

		if (vhcAno != null && vhcAno.trim().length() != 0)
			valorAno = Integer.parseInt(vhcAno);

		if (valorAno < 1970 || valorAno > 2025)
			throw new GWorkException(Util.loadErrorMessageValue("ANO.NOVALIDO"));

		if (vhcAnoValCom != null && vhcAnoValCom.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(vhcAnoValCom);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("AÑO.VALOR.COMERCIAL.CARACTER"));

		if (vhcAnoValCom != null && vhcAnoValCom.trim().length() != 0 && vhcAnoValCom.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("AÑO.VALOR.COMERCIAL.MINIMO"));

		if (vhcAnoValCom != null && vhcAnoValCom.trim().length() != 0)
			valorAnoValCom = Integer.parseInt(vhcAnoValCom);

		if (vhcAnoValCom != null && vhcAnoValCom.trim().length() != 0)
			if (valorAnoValCom < 1970 || valorAnoValCom > 2050)
				throw new GWorkException(Util.loadErrorMessageValue("ANO.VAL.NOVALIDO"));

		if (vhcValorComercial != null) {
			valorComercial = vhcValorComercial.toString();
			if (valorComercial != null && valorComercial.trim().length() != 0)
				esValido = Util.validarNumerosConsultaPuntos(valorComercial);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("VALOR.COMERCIAL.CARACTER"));

			if (valorComercial != null && valorComercial.trim().length() != 0 && valorComercial.trim().length() < 2
					&& !valorComercial.equalsIgnoreCase("0"))
				throw new GWorkException(Util.loadErrorMessageValue("VALOR.COMERCIAL.MINIMO"));
		}

		if (vhcMesfabricacion != null && vhcMesfabricacion.trim().length() != 0)
			esValido = Util.validarNumerosConsultaPuntos(vhcMesfabricacion);

		else
			throw new GWorkException(Util.loadErrorMessageValue("MES.FABRICACION"));

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("MES.FABRICACION.CARACTER"));

		if (new Long(vhcMesfabricacion) < 0 || new Long(vhcMesfabricacion) > 12)
			throw new GWorkException(Util.loadErrorMessageValue("MES.FAB.NOVALIDO"));

		if (vhcAnofabricacion != null && vhcAnofabricacion.trim().length() != 0)
			esValido = Util.validarNumerosConsultaPuntos(vhcAnofabricacion);
		else
			throw new GWorkException(Util.loadErrorMessageValue("ANO.FABRICACION"));

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("AÑO.FABRICACION.CARACTER"));

		if (vhcAnofabricacion != null && vhcAnofabricacion.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("AÑO.FABRICACION.MINIMO"));

		if (vhcAnofabricacion != null && vhcAnofabricacion.trim().length() != 0)
			valorAnoFab = Integer.parseInt(vhcAnofabricacion);

		if (valorAnoFab < 1970 || valorAnoFab > 2050)
			throw new GWorkException(Util.loadErrorMessageValue("ANO.FAB.NOVALIDO"));

		if (vhcNumeroManifiesto != null && vhcNumeroManifiesto.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroManifiesto);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.MANIFIESTO.CARACTER"));

		if (vhcNumeroManifiesto != null && vhcNumeroManifiesto.trim().length() != 0
				&& vhcNumeroManifiesto.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.MANIFIESTO.MINIMO"));

		if (vhcNumDeclImpor != null && vhcNumDeclImpor.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumDeclImpor);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.DECLARACION.CARACTER"));

		if (vhcNumDeclImpor != null && vhcNumDeclImpor.trim().length() != 0 && vhcNumDeclImpor.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.DECLARACION.MINIMO"));

		if (vhcNumeroMotor != null && vhcNumeroMotor.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NUMEROMOTOR"));

		if (vhcNumeroMotor != null && vhcNumeroMotor.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroMotor);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.MOTOR.CARACTER"));

		if (vhcNumeroMotor != null && vhcNumeroMotor.trim().length() != 0 && vhcNumeroMotor.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.MOTOR.MINIMO"));

		if (vhcCapacidad != null && vhcCapacidad.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(vhcCapacidad);
		else
			throw new GWorkException(Util.loadErrorMessageValue("CAPACIDAD"));

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("CAPACIDAD.VHC.CARACTER"));

		if (vhcCapacidadTanque != null && vhcCapacidadTanque.trim().length() != 0)
			esValido = Util.validarNumerosConsulta(vhcCapacidadTanque);
		else
			throw new GWorkException(Util.loadErrorMessageValue("CAPACIDADTANQUE"));

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("CAPACIDADTANQUE.VHC.CARACTER"));

		
		if (vhcColor != null && vhcColor.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("COLOR"));

		if (vhcColor != null && vhcColor.trim().length() != 0)
			esValido = Util.validarCadenaCaracteres(vhcColor);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("COLOR.VHC.CARACTER"));

		if (vhcColor != null && vhcColor.trim().length() != 0 && vhcColor.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("COLOR.VHC.MINIMO"));

		if (vhcNumeroLevante != null && vhcNumeroLevante.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroLevante);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.LEVANTE.CARACTER"));

		if (vhcNumeroLevante != null && vhcNumeroLevante.trim().length() != 0 && vhcNumeroLevante.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.LEVANTE.MINIMO"));

		if (vhcDocumentTrans != null && vhcDocumentTrans.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcDocumentTrans);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("DOC.TRANSPORTE.CARACTER"));

		if (vhcDocumentTrans != null && vhcDocumentTrans.trim().length() != 0 && vhcDocumentTrans.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("DOC.TRANSPORTE.MINIMO"));

		if (vhcNumeroFactura != null && vhcNumeroFactura.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroFactura);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.PROVEEDOR.CARACTER"));

		if (vhcNumeroFactura != null && vhcNumeroFactura.trim().length() != 0 && vhcNumeroFactura.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("NUMERO.FACTURA.MINIMO"));

		if (vhcOrderCompra != null && vhcOrderCompra.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcOrderCompra);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("ORDEN.COMPRA.CARACTER"));

		if (vhcOrderCompra != null && vhcOrderCompra.trim().length() != 0 && vhcOrderCompra.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("ORDEN.COMPRA.MINIMO"));

		if (vhcProveedor != null && vhcProveedor.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcProveedor);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("PROVEEDOR.CARACTER"));

		if (vhcProveedor != null && vhcProveedor.trim().length() != 0 && vhcProveedor.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("PROVEEDOR.MINIMO"));

		if (vhcAtInicial != null && vhcAtInicial.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcAtInicial);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("AT.INICIAL.CARACTER"));

		if (vhcAtInicial != null && vhcAtInicial.trim().length() != 0 && vhcAtInicial.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("AT.INICIAL.MINIMO"));

		if (vhcCiuAduan != null && vhcCiuAduan.trim().length() != 0)
			esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcCiuAduan);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("CIUDAD.ADUANA.CARACTER"));

		if (vhcVidaUtil != null)
			esValido = Util.validarNumerosConsultaPuntos(vhcVidaUtil);

		if (!esValido)
			throw new GWorkException(Util.loadErrorMessageValue("VIDA.UTIL.CARACTER"));

		if (vhcValorCIF != null) {

			valorCIF = vhcValorCIF.toString();

			esValido = Util.validarNumerosParametros(valorCIF);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("VALOR.CIF.CARACTER"));

			if (txtValorCIF != null && !txtValorCIF.isReadonly())
				if (valorCIF.trim().length() != 0 && valorCIF.trim().length() < 2)
					throw new GWorkException(Util.loadErrorMessageValue("VALOR.CIF.MINIMO"));
		}

		if (vhcValorFOB != null) {
			valorFOB = vhcValorFOB.toString();
			esValido = Util.validarNumerosParametros(valorFOB);

			if (!esValido)
				throw new GWorkException(Util.loadErrorMessageValue("VALOR.FOB.CARACTER"));

			if (txtValorFob != null && !txtValorFob.isReadonly())
				if (valorFOB.trim().length() != 0 && valorFOB.trim().length() < 2)
					throw new GWorkException(Util.loadErrorMessageValue("VALOR.FOB.MINIMO"));
		}

		if (vhcObservaciones != null && vhcObservaciones.trim().length() != 0)
			Util.validarLimite(vhcObservaciones, 200, 3, "DESCRIPCION.MINMAX");

	}

	public void seleccionarCamposCatalogo(ValueChangeEvent event) throws GWorkException {
		try {
			if (event.getNewValue() != null) {
				limpiarSessionCatalogo();
				SupplyingCatalogs catalogs = null;
				String numModelo = event.getNewValue().toString();
				if (numModelo != null && !numModelo.equalsIgnoreCase("-1")) {
					catalogs = SearchVehicles.consultarCatalogo(new Long(numModelo));
					lineaCatalogo = catalogs.getLines().getLnsNombre();
					setMarcaCatalogo(catalogs.getLines().getBrands().getBrnNombre());
					FacesUtils.getSession().setAttribute("idLinea", catalogs.getLines().getLnsId().toString());
					modeloCatalogo = catalogs.getModels().getMdlNombre();
					setModeloCatalogo(modeloCatalogo);
					setNumCatalogo(catalogs.getSupNumCatalogo());
				} else if (numCatalogo.equalsIgnoreCase("-1"))
					limpiarCamposCatalogo();
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarSessionCatalogo() {
		FacesUtils.getSession().removeAttribute("idLinea");
	}

	public void limpiarCamposCatalogo() {
		setLineaCatalogo(null);
		setModeloCatalogo(null);
		setIdNumCatalogo(null);
		selectCatalogos.setValue(new Long("-1"));
	}

	public void desactivarCatalogo() {
		if (selectCatalogos != null)
			selectCatalogos.setDisabled(true);
	}

	public void activarCatalogo() {
		if (selectCatalogos != null)
			selectCatalogos.setDisabled(false);
	}

	public void activarCamposValor() {

		if (txtValorCIF != null) {
			txtValorCIF.setReadonly(false);
			txtValorCIF.setValue(null);
		}
		if (txtValorFob != null) {
			txtValorFob.setReadonly(false);
			txtValorFob.setValue(null);
		}
		if (txtVidaUtil != null) {
			txtVidaUtil.setReadonly(false);
			txtVidaUtil.setValue(null);
		}
		if (txtProveedor != null) {
			txtProveedor.setReadonly(false);
			txtProveedor.setValue(null);
		}
		if (txtOrdenCompra != null) {
			txtOrdenCompra.setReadonly(false);
			txtOrdenCompra.setValue(null);
		}
		if (txtCargosImportacion != null) {
			txtCargosImportacion.setReadonly(false);
			txtCargosImportacion.setValue(null);
		}
	}

	public void sumarCargosEnCIF(ValueChangeEvent event) throws GWorkException {
		try {
			if (event.getNewValue() != null && txtValorFob != null && txtValorFob.getValue() != null) {
				String valorFOB = txtValorFob.getValue().toString();
				String valorCIF = event.getNewValue().toString();
				if (valorCIF != null && !valorCIF.trim().equalsIgnoreCase("0")) {
					boolean esValido = true;

					if (valorCIF != null) {
						if (valorCIF != null)
							esValido = Util.validarNumerosParametros(valorCIF);

						if (!esValido)
							throw new GWorkException(Util.loadErrorMessageValue("VALOR.CIF.CARACTER"));

						if (valorFOB != null && valorFOB.trim().length() != 0)
							esValido = Util.validarNumerosParametros(valorFOB);

						if (!esValido)
							throw new GWorkException(Util.loadErrorMessageValue("VALOR.FOB.CARACTER"));

						if (valorFOB != null && valorFOB.trim().length() == 0)
							valorFOB = "0";

						if (valorCIF != null && valorCIF.trim().length() == 0)
							valorCIF = "0";

						float suma = 0;
						suma = new Float(Util.convertirDecimal(valorCIF)) - new Float(Util.convertirDecimal(valorFOB));
						vhcCargosImportacion = suma;
						txtCargosImportacion.setValue(vhcCargosImportacion);
						txtCargosImportacion.setReadonly(true);
					}
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void sumarCargosEnFOB(ValueChangeEvent event) throws GWorkException {

		try {
			if (event.getNewValue() != null && txtValorCIF != null && txtValorCIF.getValue() != null) {
				String valorCIF = txtValorCIF.getValue().toString();
				String valorFOB = event.getNewValue().toString();
				boolean esValido = true;

				if (valorFOB != null && !valorFOB.trim().equalsIgnoreCase("0")) {
					if (valorCIF != null && valorCIF.trim().length() != 0)
						esValido = Util.validarNumerosParametros(valorCIF);

					if (!esValido)
						throw new GWorkException(Util.loadErrorMessageValue("VALOR.CIF.CARACTER"));

					if (valorFOB != null && valorFOB.trim().length() != 0)
						esValido = Util.validarNumerosParametros(valorFOB);

					if (!esValido)
						throw new GWorkException(Util.loadErrorMessageValue("VALOR.FOB.CARACTER"));

					if (valorCIF != null && valorCIF.trim().length() == 0)
						valorCIF = "0";

					if (valorFOB != null && valorFOB.trim().length() == 0)
						valorFOB = "0";

					float suma = 0;
					suma = new Float(Util.convertirDecimal(valorCIF)) - new Float(Util.convertirDecimal(valorFOB));
					vhcCargosImportacion = suma;
					txtCargosImportacion.setValue(vhcCargosImportacion);
					txtCargosImportacion.setReadonly(true);
				}
			}

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void cargarFuncionesCiat(ValueChangeEvent event) throws GWorkException {

		if (event.getNewValue() != null) {
			String valorCIF = null;
			String valorFOB = null;
			String valorActivoFijo = event.getNewValue().toString().trim();
			if (valorActivoFijo.trim().length() != 0) {

				consultService = new ConsultsServiceImpl();

				if (consultService.consultFuntions(valorActivoFijo, 1) != null)
					vhcValorCIF = new Float(consultService.consultFuntions(valorActivoFijo, 1).toString());

				if (consultService.consultFuntions(valorActivoFijo, 2) != null)
					vhcValorFOB = new Float(consultService.consultFuntions(valorActivoFijo, 2).toString());

				if (consultService.consultFuntions(valorActivoFijo, 3) != null)
					vhcCargosImportacion = new Float(consultService.consultFuntions(valorActivoFijo, 3).toString());

				if (consultService.consultFuntions(valorActivoFijo, 4) != null)
					vhcVidaUtil = consultService.consultFuntions(valorActivoFijo, 4).toString();

				vhcOrderCompra = consultService.consultBuyOrder(valorActivoFijo);
				vhcProveedor = consultService.consultSupplier(valorActivoFijo);

				if (vhcVidaUtil != null && vhcVidaUtil.trim().length() != 0) {
					txtVidaUtil.setValue(vhcVidaUtil);
					txtVidaUtil.setReadonly(true);
				} else
					txtVidaUtil.setReadonly(false);

				if (vhcValorCIF != null) {
					valorCIF = vhcValorCIF.toString();
					if (valorCIF != null && valorCIF.trim().length() != 0) {
						txtValorCIF.setValue(valorCIF);
						txtValorCIF.setReadonly(true);
					} else
						txtValorCIF.setReadonly(false);
				}

				if (vhcValorFOB != null) {
					valorFOB = vhcValorFOB.toString();
					if (valorFOB != null && valorFOB.trim().length() != 0) {
						txtValorFob.setValue(valorFOB);
						txtValorFob.setReadonly(true);
					} else
						txtValorFob.setReadonly(false);
				}

				if (vhcProveedor != null) {
					txtProveedor.setValue(vhcProveedor);
					txtProveedor.setReadonly(true);
				} else
					txtProveedor.setReadonly(false);

				if (vhcOrderCompra != null) {
					txtOrdenCompra.setValue(vhcOrderCompra);
					txtOrdenCompra.setReadonly(true);
				} else
					txtOrdenCompra.setReadonly(false);

				float suma = 0;
				float val1 = 0;
				float val2 = 0;
				if (valorCIF != null && valorCIF.trim().length() != 0 || valorCIF != null
						&& valorCIF.trim().length() != 0) {

					if (!txtValorCIF.isReadonly() && valorCIF != null && valorCIF.trim().length() != 0)
						val1 = new Float(Util.convertirDecimalesANumeros(valorCIF));
					else if (valorCIF != null && valorCIF.trim().length() != 0)
						val1 = new Float(valorCIF);

					if (!txtValorFob.isReadonly() && valorFOB != null && valorFOB.trim().length() != 0)
						val2 = new Float(Util.convertirDecimalesANumeros(valorFOB));
					else if (valorFOB != null && valorFOB.trim().length() != 0)
						val2 = new Float(valorFOB);

					suma = val1 - val2;

					vhcCargosImportacion = suma;
					txtCargosImportacion.setValue(vhcCargosImportacion);
					txtCargosImportacion.setReadonly(true);
				}
			} else
				activarCamposValor();
		}
	}

	public void mostrarConfirmacion(ActionEvent event) {
		try {
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}

	}

	/**
	 * 
	 * @param event
	 */
	public void action_modificar() {
		try {

			String placaAnterior = (String) FacesUtils.getSession().getAttribute("placaDiplomatica");
			String numeroTlAnterior = (String) FacesUtils.getSession().getAttribute("numeroTL");
			String placaActivoAnterior = (String) FacesUtils.getSession().getAttribute("placaActivoFijo");

			if (placaAnterior != null || numeroTlAnterior != null || placaActivoAnterior != null) {

				String idUbicacion = null;
				Long ubicacion = null;

				if (selectOneMenuUbicacion != null && selectOneMenuUbicacion.getValue() != null) {
					idUbicacion = selectOneMenuUbicacion.getValue().toString();
					if (idUbicacion != null && idUbicacion.trim().length() != 0) {
						ubicacion = new Long(idUbicacion);
					}
				}

				validarCamposVehiculo(vhcNumeroTl, vhcPlacaDiplomatica, vhcPlacaActivoFijo, vhcClilindraje,
						vhcNumeroMotor, vhcNumeroSerie, idTipoVehiculo, vhcAno, vhcColor, idTipoCombustible,
						vhcNumeroManifiesto, vhcNumDeclImpor, vhcNumeroLevante, vhcDocumentTrans, vhcNumeroFactura,
						vhcOrderCompra, vhcProveedor, vhcAtInicial, vhcCiuAduan, vhcObservaciones, vhcRemplazaA,
						vhcCapacidad, vhcCapacidadTanque, vhcCatalogado, vhcAnoValCom, vhcValorComercial, idTipoVehiculo,
						vhcIdTypeTraction, vhcIdTypeTapestrie, vhcIdTypeTransmissions, idLocationType, vhcOdometro,
						vhcVidaUtil, vhcValorCIF, vhcValorFOB, vhcCargosImportacion, vhcAnofabricacion,
						vhcMesfabricacion, idModelo, idNumCatalogo, idLocation);

				vehicleService.modificarVehiculo(placaAnterior, numeroTlAnterior, placaActivoAnterior, vhcNumeroTl,
						vhcPlacaDiplomatica, vhcPlacaActivoFijo, idEstadoVehiculo, lineaCatalogo, numCatalogo,
						vhcNumeroMotor, vhcNumeroSerie, idTipoVehiculo, new Long(vhcAno), vhcColor, ubicacion,
						idTipoCombustible, vhcOdometro, new Long(vhcCapacidad), new Float(vhcCapacidadTanque), vhcValorComercial, vhcAnoValCom,
						vhcFechaProtocolo, vhcNumeroManifiesto, vhcFechaManifiesto, vhcNumDeclImpor, vhcRemplazaPor,
						vhcNumeroLevante, vhcFechaLevante, vhcDocumentTrans, vhcNumeroFactura, vhcOrderCompra,
						vhcProveedor, vhcAtInicial, vhcCiuAduan, vhcVidaUtil, vhcValorCIF, vhcValorFOB,
						vhcCargosImportacion, vhcObservaciones, vhcIdTypeTapestrie, vhcIdTypeTraction,
						vhcIdTypeTransmissions, vhcClilindraje, modeloCatalogo, vhcRemplazaA, idCuidad, new Long(
								vhcAnofabricacion), new Long(vhcMesfabricacion));

				limpiar();
			} else
				throw new GWorkException(Util.loadErrorMessageValue("VEHICULO.NOMODIFICAR"));
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiarDatosSessionConsulta() {
		if (FacesUtils.getSession().getAttribute("placaDiplomatica") != null)
			FacesUtils.getSession().removeAttribute("placaDiplomatica");

		if (FacesUtils.getSession().getAttribute("placaActivoFijo") != null)
			FacesUtils.getSession().removeAttribute("placaActivoFijo");
		
		if (FacesUtils.getSession().getAttribute("numeroTL") != null) 
			FacesUtils.getSession().removeAttribute("numeroTL");

	}

	public void mostrarPopupCiudades(ActionEvent event) {
		setShowPopupCities(true);
		setIdCountry(-1L);
		setIdCuidad(-1L);
	}

	public void action_cancelar(ActionEvent event) {
		setShowPopupCities(false);
		setVhcCiuAduan(null);
	}

	public void aceptar_onclick(ActionEvent event) {
		setShowPopupCities(false);
	}

	/**
	 * Metodo para consultar el tipo de ubicacion ciudad y pais de un vehiculo
	 * 
	 * @param evento
	 */
	public void action_consultar(ActionEvent event) {
		consultar();
	}

	public void consultar() {
		try {
			boolean esValido = true;
			limpiarDatosSessionConsulta();
			Cities cities = null;
			SupplyingCatalogs catalogs = null;

			if (vhcPlacaDiplomatica != null && vhcPlacaDiplomatica.trim().length() == 0 && vhcNumeroTl != null
					&& vhcNumeroTl.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("TL.PLACA.VACIO"));

			if (vhcPlacaDiplomatica != null || vhcPlacaActivoFijo != null || vhcNumeroTl != null) {

				if (vhcPlacaDiplomatica != null)
					esValido = Util.validarPlaca(vhcPlacaDiplomatica);

				if (!esValido)
					throw new GWorkException(Util.loadErrorMessageValue("PLACA.INCORRECTO"));

				if (vhcPlacaDiplomatica != null && vhcPlacaDiplomatica.trim().length() != 0
						&& vhcPlacaDiplomatica.trim().length() < 2)
					throw new GWorkException(Util.loadErrorMessageValue("PLACA.MINIMO"));

				if (vhcNumeroTl != null)
					esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(vhcNumeroTl);

				if (!esValido)
					throw new GWorkException(Util.loadErrorMessageValue("NUMERO.TL.CARACTER"));

				if (vhcNumeroTl != null && vhcNumeroTl.trim().length() != 0 && vhcNumeroTl.trim().length() < 2)
					throw new GWorkException(Util.loadErrorMessageValue("NUMERO.TL.MINIMO"));

				Vehicles vehicles = vehicleService.consultarVehiculoPorIds(vhcPlacaDiplomatica.toUpperCase().trim(),
						vhcPlacaActivoFijo.toUpperCase().trim(), vhcNumeroTl.toUpperCase().trim());
				if (vehicles != null) {
					if (vehicles.getVhcPlacaDiplomatica() != null) {
						vhcPlacaDiplomatica = vehicles.getVhcPlacaDiplomatica();
						FacesUtils.getSession().setAttribute("placaDiplomatica", vhcPlacaDiplomatica);
					}

					if (vehicles.getVhcPlacaActivoFijo() != null) {
						vhcPlacaActivoFijo = vehicles.getVhcPlacaActivoFijo();
						FacesUtils.getSession().setAttribute("placaActivoFijo", vhcPlacaActivoFijo);
					}

					if (vehicles.getVhcModelo() != null && selectModelo != null) {
						List<Models> listModels = new ModelsDAO().findByMdlNombre(vehicles.getVhcModelo());
						Models models = null;
						if (listModels != null && listModels.size() > 0) {
							models = listModels.get(0);
							selectModelo.setValue(models.getMdlId());
							SelectItemSupplyingCatalogsPage catalogsPage = (SelectItemSupplyingCatalogsPage) FacesUtils
									.getManagedBean("selectItemSuppliyngPage");
							if (catalogsPage != null)
								catalogsPage.setSetSupplyingCatalogs(SearchVehicles
										.consultarCatalogoPorIdModelo(new Long(models.getMdlId())));

							List<SupplyingCatalogs> supplyingCatalogs = new SupplyingCatalogsDAO()
									.findBySupNumCatalogo(vehicles.getVhcNumReferCat());
							if (supplyingCatalogs != null && supplyingCatalogs.size() > 0) {
								catalogs = supplyingCatalogs.get(0);
								selectCatalogos.setValue(catalogs.getSupCodigo());
							}
						}
					}

					if (vehicles.getVhcNumeroTl() != null) {
						vhcNumeroTl = vehicles.getVhcNumeroTl();
						FacesUtils.getSession().setAttribute("numeroTL", vhcNumeroTl);
					}
					if (vehicles.getLines().getBrands() != null)
						setMarcaCatalogo(vehicles.getLines().getBrands().getBrnNombre());

					if (vehicles.getLines() != null) {
						setLineaCatalogo(vehicles.getLines().getLnsNombre());
						idLine = vehicles.getLines().getLnsId();
					}

					if (vehicles.getLocations() != null) {

						idLocationType = vehicles.getLocations().getLocationsTypes().getLctCodigo();
						selectOneMenuTipoUbicacion.setValue(idLocationType);
						setIdLocationType(idLocationType);

						List<Locations> setLocations = SearchVehicles.consultarUbicacionPorIdTipo(new Long(
								idLocationType));

						SelectItemLocationPage selectItemLocationPage = (SelectItemLocationPage) FacesUtils
								.getManagedBean("selectItemLocationPage");
						if (selectItemLocationPage != null)
							selectItemLocationPage.setSetLocations(setLocations);

						cities = vehicles.getLocations().getCities();
						if (cities != null && cities.getCountries() != null)
							setCountryName(cities.getCountries().getCntNombre());

						setIdLocation(cities.getCtsId());
						selectOneMenuUbicacion.setValue(cities.getCtsId());
						setSelectOneMenuUbicacion(selectOneMenuUbicacion);

						setIdLocationType(idLocationType);
						setSelectOneMenuTipoUbicacion(selectOneMenuTipoUbicacion);
					}

					if (vehicles.getVehiclesStates() != null)
						idEstadoVehiculo = vehicles.getVehiclesStates().getVhsCodigo();

					if (vehicles.getVhcNumeroMotor() != null)
						vhcNumeroMotor = vehicles.getVhcNumeroMotor();

					if (vehicles.getVhcNumeroSerie() != null)
						vhcNumeroSerie = vehicles.getVhcNumeroSerie();

					if (vehicles.getVehiclesTypes() != null)
						idTipoVehiculo = vehicles.getVehiclesTypes().getVhtCodigo();

					if (vehicles.getLocations().getCities() != null) {
						idCuidad = vehicles.getLocations().getCities().getCtsId();
						selectCity.setValue(idCuidad);

						List<Cities> setCities = new ArrayList<Cities>();
						Iterator<Cities> s = vehicles.getLocations().getCities().getCountries().getCitieses()
								.iterator();

						while (s.hasNext()) {
							setCities.add(s.next());
						}

						SelectItemCountryPage countryPage = (SelectItemCountryPage) FacesUtils
								.getManagedBean("selectItemCountryPage");
						if (countryPage != null)
							countryPage.setSetCities(setCities);

						vhcPais = vehicles.getLocations().getCities().getCountries().getCntId();
						selectCountry.setValue(vhcPais);
						countryName = vehicles.getLocations().getCities().getCountries().getCntNombre();
					}

					if (vehicles.getVhcAno() != null)
						vhcAno = vehicles.getVhcAno().toString();

					if (vehicles.getFuelsTypes() != null)
						idTipoCombustible = vehicles.getFuelsTypes().getFutCodigo();

					if (vehicles.getVhcColor() != null)
						vhcColor = vehicles.getVhcColor();

					if (vehicles.getVhcCapacidad() != null)
						vhcCapacidad = vehicles.getVhcCapacidad().toString();

					if (vehicles.getVhcCapMaxTanq() != null)
						vhcCapacidadTanque = vehicles.getVhcCapMaxTanq().toString();
					
					if (vehicles.getVhcOdometro() != null)
						vhcOdometro = vehicles.getVhcOdometro();

					if (vehicles.getVhcValorComercial() != null)
						vhcValorComercial = vehicles.getVhcValorComercial().toString();

					if (vehicles.getVhcAnoValCom() != null)
						vhcAnoValCom = vehicles.getVhcAnoValCom().toString();

					if (vehicles.getVhcFechaProtocolo() != null)
						vhcFechaProtocolo = vehicles.getVhcFechaProtocolo();

					if (vehicles.getVhcFechaManifiesto() != null)
						vhcFechaManifiesto = vehicles.getVhcFechaManifiesto();

					if (vehicles.getVhcNumeroManifiesto() != null)
						vhcNumeroManifiesto = vehicles.getVhcNumeroManifiesto();

					if (vehicles.getVhcNumDeclImpor() != null)
						vhcNumDeclImpor = vehicles.getVhcNumDeclImpor();

					if (vehicles.getVhcRemplazaA() != null)
						vhcRemplazaA = vehicles.getVhcRemplazaA();

					if (vehicles.getVhcNumeroLevante() != null)
						vhcNumeroLevante = vehicles.getVhcNumeroLevante();

					if (vehicles.getVhcFechaLevante() != null)
						vhcFechaLevante = vehicles.getVhcFechaLevante();

					if (vehicles.getVhcDocumentTrans() != null)
						vhcDocumentTrans = vehicles.getVhcDocumentTrans();

					if (vehicles.getVhcNumeroFactura() != null)
						vhcNumeroFactura = vehicles.getVhcNumeroFactura();

					if (vehicles.getVhcOrderCompra() != null)
						vhcOrderCompra = vehicles.getVhcOrderCompra();

					if (vehicles.getVhcProveedor() != null)
						vhcProveedor = vehicles.getVhcProveedor();

					if (vehicles.getVhcAtInicial() != null)
						vhcAtInicial = vehicles.getVhcAtInicial();

					if (vehicles.getVhcCiuAduan() != null)
						vhcCiuAduan = vehicles.getVhcCiuAduan();

					if (vehicles.getVhcObservaciones() != null)
						vhcObservaciones = vehicles.getVhcObservaciones();

					if (vehicles.getTapestriesTypes() != null)
						vhcIdTypeTapestrie = vehicles.getTapestriesTypes().getTptpcCodigo();

					if (vehicles.getTractionsTypes() != null)
						vhcIdTypeTraction = vehicles.getTractionsTypes().getTctCodigo();

					if (vehicles.getTransmissionsTypes() != null)
						vhcIdTypeTransmissions = vehicles.getTransmissionsTypes().getTntCodigo();

					if (vehicles.getVhcCilindraje() != null)
						vhcClilindraje = vehicles.getVhcCilindraje();

					if (vehicles.getVhcModelo() != null)
						modeloCatalogo = vehicles.getVhcModelo();

					if (vehicles.getVhcNumReferCat() != null)
						numCatalogo = vehicles.getVhcNumReferCat();

					if (vehicles.getVhcValorCif() != null)
						vhcValorCIF = new Float(vehicles.getVhcValorCif());

					if (vehicles.getVhcValorFob() != null)
						vhcValorFOB = new Float(vehicles.getVhcValorFob());

					if (vehicles.getVhcVidaUtil() != null)
						vhcVidaUtil = vehicles.getVhcVidaUtil().toString();

					if (vehicles.getVhcCargosImport() != null)
						vhcCargosImportacion = new Float(vehicles.getVhcCargosImport());

					if (vehicles.getVhcCiuAduan() != null)
						vhcCiuAduan = vehicles.getVhcCiuAduan();

					if (vehicles.getVhcObservaciones() != null)
						vhcObservaciones = vehicles.getVhcObservaciones();

					if (vehicles.getVhcMesfabricacion() != null)
						vhcMesfabricacion = vehicles.getVhcMesfabricacion().toString();

					if (vehicles.getVhcAnofabricacion() != null)
						vhcAnofabricacion = vehicles.getVhcAnofabricacion().toString();

					htmlButtonCrear.setDisabled(true);
					showEstate = true;
				} else
					throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar() {

		limpiarSessionCatalogo();

		activarCatalogo();

		activarCamposValor();

		if (selectOneMenuUbicacion != null && selectOneMenuUbicacion.getValue() != null)
			selectOneMenuUbicacion.setValue("-1");

		if (selectOneMenuTipoUbicacion != null && selectOneMenuTipoUbicacion.getValue() != null)
			selectOneMenuTipoUbicacion.setValue("-1");

		if (selectCity != null && selectCity.getValue() != null)
			selectCity.setValue("-1");

		if (selectCountry != null && selectCountry.getValue() != null)
			selectCountry.setValue("-1");

		if (selectModelo != null)
			selectModelo.setValue("-1");

		setVhcMesfabricacion(null);
		setVhcAnofabricacion(null);
		marcaCatalogo = null;
		vhcPlacaDiplomatica = "";
		vhcPlacaActivoFijo = "";
		vhcNumeroTl = "";
		idBrand = new Long("-1");
		idLine = new Long("-1");
		idLocation = new Long("-1");
		idEstadoVehiculo = null;
		vhcCatalogado = "";
		vhcNumeroMotor = "";
		vhcNumeroSerie = "";
		idTipoVehiculo = new Long("-1");
		idCuidad = new Long("-1");
		vhcAno = null;
		idTipoCombustible = new Long("-1");
		vhcColor = "";
		vhcCapacidad = null;
		vhcCapacidadTanque = null;
		vhcOdometro = "";
		vhcValorComercial = null;
		vhcAnoValCom = null;
		vhcFechaProtocolo = null;
		vhcFechaManifiesto = null;
		vhcNumeroManifiesto = "";
		vhcNumDeclImpor = "";
		vhcRemplazaA = "";
		vhcNumeroLevante = "";
		vhcFechaLevante = null;
		vhcDocumentTrans = "";
		vhcNumeroFactura = "";
		vhcOrderCompra = "";
		vhcProveedor = "";
		vhcAtInicial = "";
		vhcCiuAduan = "";
		vhcObservaciones = "";
		vhcIdTypeTapestrie = null;
		vhcIdTypeTraction = null;
		vhcIdTypeTransmissions = null;
		vhcClilindraje = null;
		vhcModelo = null;
		idCountry = null;
		idCuidad = null;
		numCatalogo = null;
		vhcVidaUtil = null;
		vhcValorFOB = null;
		vhcValorCIF = null;
		vhcCargosImportacion = null;
		htmlButtonCrear.setDisabled(false);
		showEstate = false;
		countryName = null;
		limpiarCamposCatalogo();
		limpiarDatosSessionConsulta();
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
	}

	public VehicleService getVehicleService() {
		return vehicleService;
	}

	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	public String getVhcPlacaDiplomatica() {
		return vhcPlacaDiplomatica;
	}

	public void setVhcPlacaDiplomatica(String vhcPlacaDiplomatica) {
		this.vhcPlacaDiplomatica = vhcPlacaDiplomatica;
	}

	public String getVhcPlacaActivoFijo() {
		return vhcPlacaActivoFijo;
	}

	public void setVhcPlacaActivoFijo(String vhcPlacaActivoFijo) {
		this.vhcPlacaActivoFijo = vhcPlacaActivoFijo;
	}

	public String getVhcNumeroTl() {
		return vhcNumeroTl;
	}

	public void setVhcNumeroTl(String vhcNumeroTl) {
		this.vhcNumeroTl = vhcNumeroTl;
	}

	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public Long getIdLine() {
		return idLine;
	}

	public void setIdLine(Long idLine) {
		this.idLine = idLine;
	}

	public Long getIdEstadoVehiculo() {
		return idEstadoVehiculo;
	}

	public void setIdEstadoVehiculo(Long idEstadoVehiculo) {
		this.idEstadoVehiculo = idEstadoVehiculo;
	}

	public String getVhcCatalogado() {
		return vhcCatalogado;
	}

	public void setVhcCatalogado(String vhcCatalogado) {
		this.vhcCatalogado = vhcCatalogado;
	}

	public String getVhcNumeroMotor() {
		return vhcNumeroMotor;
	}

	public void setVhcNumeroMotor(String vhcNumeroMotor) {
		this.vhcNumeroMotor = vhcNumeroMotor;
	}

	public String getVhcNumeroSerie() {
		return vhcNumeroSerie;
	}

	public void setVhcNumeroSerie(String vhcNumeroSerie) {
		this.vhcNumeroSerie = vhcNumeroSerie;
	}

	public Long getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(Long idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public Long getIdCuidad() {
		return idCuidad;
	}

	public void setIdCuidad(Long idCuidad) {
		this.idCuidad = idCuidad;
	}

	public String getVhcAno() {
		return vhcAno;
	}

	public void setVhcAno(String vhcAno) {
		this.vhcAno = vhcAno;
	}

	public Long getIdTipoCombustible() {
		return idTipoCombustible;
	}

	public void setIdTipoCombustible(Long idTipoCombustible) {
		this.idTipoCombustible = idTipoCombustible;
	}

	public String getVhcColor() {
		return vhcColor;
	}

	public void setVhcColor(String vhcColor) {
		this.vhcColor = vhcColor;
	}

	public String getVhcCapacidad() {
		return vhcCapacidad;
	}

	public void setVhcCapacidad(String vhcCapacidad) {
		this.vhcCapacidad = vhcCapacidad;
	}

	public String getVhcOdometro() {
		return vhcOdometro;
	}

	public void setVhcOdometro(String vhcOdometro) {
		this.vhcOdometro = vhcOdometro;
	}

	public String getVhcValorComercial() {
		return vhcValorComercial;
	}

	public void setVhcValorComercial(String vhcValorComercial) {
		this.vhcValorComercial = vhcValorComercial;
	}

	public String getVhcAnoValCom() {
		return vhcAnoValCom;
	}

	public void setVhcAnoValCom(String vhcAnoValCom) {
		this.vhcAnoValCom = vhcAnoValCom;
	}

	public Date getVhcFechaProtocolo() {
		return vhcFechaProtocolo;
	}

	public void setVhcFechaProtocolo(Date vhcFechaProtocolo) {
		this.vhcFechaProtocolo = vhcFechaProtocolo;
	}

	public Date getVhcFechaManifiesto() {
		return vhcFechaManifiesto;
	}

	public void setVhcFechaManifiesto(Date vhcFechaManifiesto) {
		this.vhcFechaManifiesto = vhcFechaManifiesto;
	}

	public String getVhcNumeroManifiesto() {
		return vhcNumeroManifiesto;
	}

	public void setVhcNumeroManifiesto(String vhcNumeroManifiesto) {
		this.vhcNumeroManifiesto = vhcNumeroManifiesto;
	}

	public String getVhcNumDeclImpor() {
		return vhcNumDeclImpor;
	}

	public void setVhcNumDeclImpor(String vhcNumDeclImpor) {
		this.vhcNumDeclImpor = vhcNumDeclImpor;
	}

	public String getVhcRemplazaA() {
		return vhcRemplazaA;
	}

	public void setVhcRemplazaA(String vhcRemplazaA) {
		this.vhcRemplazaA = vhcRemplazaA;
	}

	public String getVhcNumeroLevante() {
		return vhcNumeroLevante;
	}

	public void setVhcNumeroLevante(String vhcNumeroLevante) {
		this.vhcNumeroLevante = vhcNumeroLevante;
	}

	public Date getVhcFechaLevante() {
		return vhcFechaLevante;
	}

	public void setVhcFechaLevante(Date vhcFechaLevante) {
		this.vhcFechaLevante = vhcFechaLevante;
	}

	public String getVhcDocumentTrans() {
		return vhcDocumentTrans;
	}

	public void setVhcDocumentTrans(String vhcDocumentTrans) {
		this.vhcDocumentTrans = vhcDocumentTrans;
	}

	public String getVhcNumeroFactura() {
		return vhcNumeroFactura;
	}

	public void setVhcNumeroFactura(String vhcNumeroFactura) {
		this.vhcNumeroFactura = vhcNumeroFactura;
	}

	public String getVhcOrderCompra() {
		return vhcOrderCompra;
	}

	public void setVhcOrderCompra(String vhcOrderCompra) {
		this.vhcOrderCompra = vhcOrderCompra;
	}

	public String getVhcProveedor() {
		return vhcProveedor;
	}

	public void setVhcProveedor(String vhcProveedor) {
		this.vhcProveedor = vhcProveedor;
	}

	public String getVhcAtInicial() {
		return vhcAtInicial;
	}

	public void setVhcAtInicial(String vhcAtInicial) {
		this.vhcAtInicial = vhcAtInicial;
	}

	public String getVhcCiuAduan() {
		return vhcCiuAduan;
	}

	public void setVhcCiuAduan(String vhcCiuAduan) {
		this.vhcCiuAduan = vhcCiuAduan;
	}

	public String getVhcObservaciones() {
		return vhcObservaciones;
	}

	public void setVhcObservaciones(String vhcObservaciones) {
		this.vhcObservaciones = vhcObservaciones;
	}

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public Long getVhcIdTypeTapestrie() {
		return vhcIdTypeTapestrie;
	}

	public void setVhcIdTypeTapestrie(Long vhcIdTypeTapestrie) {
		this.vhcIdTypeTapestrie = vhcIdTypeTapestrie;
	}

	public Long getVhcIdTypeTraction() {
		return vhcIdTypeTraction;
	}

	public void setVhcIdTypeTraction(Long vhcIdTypeTraction) {
		this.vhcIdTypeTraction = vhcIdTypeTraction;
	}

	public Long getVhcIdTypeTransmissions() {
		return vhcIdTypeTransmissions;
	}

	public void setVhcIdTypeTransmissions(Long vhcIdTypeTransmissions) {
		this.vhcIdTypeTransmissions = vhcIdTypeTransmissions;
	}

	public String getVhcClilindraje() {
		return vhcClilindraje;
	}

	public void setVhcClilindraje(String vhcClilindraje) {
		this.vhcClilindraje = vhcClilindraje;
	}

	public String getVhcModelo() {
		return vhcModelo;
	}

	public void setVhcModelo(String vhcModelo) {
		this.vhcModelo = vhcModelo;
	}

	public Long getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Long idCountry) {
		this.idCountry = idCountry;
	}

	public HtmlCommandButton getHtmlButtonCrear() {
		return htmlButtonCrear;
	}

	public void setHtmlButtonCrear(HtmlCommandButton htmlButtonCrear) {
		this.htmlButtonCrear = htmlButtonCrear;
	}

	public boolean isShowEstate() {
		return showEstate;
	}

	public void setShowEstate(boolean showEstate) {
		this.showEstate = showEstate;
	}

	public HtmlSelectOneMenu getSelectOneMenuUbicacion() {
		return selectOneMenuUbicacion;
	}

	public void setSelectOneMenuUbicacion(HtmlSelectOneMenu selectOneMenuUbicacion) {
		this.selectOneMenuUbicacion = selectOneMenuUbicacion;
	}

	public HtmlSelectOneMenu getSelectOneMenuTipoUbicacion() {
		return selectOneMenuTipoUbicacion;
	}

	public void setSelectOneMenuTipoUbicacion(HtmlSelectOneMenu selectOneMenuTipoUbicacion) {
		this.selectOneMenuTipoUbicacion = selectOneMenuTipoUbicacion;
	}

	public String getLineaCatalogo() {
		return lineaCatalogo;
	}

	public void setLineaCatalogo(String lineaCatalogo) {
		this.lineaCatalogo = lineaCatalogo;
	}

	public String getModeloCatalogo() {
		return modeloCatalogo;
	}

	public void setModeloCatalogo(String modeloCatalogo) {
		this.modeloCatalogo = modeloCatalogo;
	}

	public String getMarcaCatalogo() {
		return marcaCatalogo;
	}

	public void setMarcaCatalogo(String marcaCatalogo) {
		this.marcaCatalogo = marcaCatalogo;
	}

	public HtmlSelectOneMenu getSelectCatalogos() {
		return selectCatalogos;
	}

	public void setSelectCatalogos(HtmlSelectOneMenu selectCatalogos) {
		this.selectCatalogos = selectCatalogos;
	}

	public Long getIdNumCatalogo() {
		return idNumCatalogo;
	}

	public void setIdNumCatalogo(Long idNumCatalogo) {
		this.idNumCatalogo = idNumCatalogo;
	}

	public HtmlSelectOneMenu getSelectCity() {
		return selectCity;
	}

	public void setSelectCity(HtmlSelectOneMenu selectCity) {
		this.selectCity = selectCity;
	}

	public HtmlSelectOneMenu getSelectCountry() {
		return selectCountry;
	}

	public void setSelectCountry(HtmlSelectOneMenu selectCountry) {
		this.selectCountry = selectCountry;
	}

	public Long getVhcPais() {
		return vhcPais;
	}

	public void setVhcPais(Long vhcPais) {
		this.vhcPais = vhcPais;
	}

	public Long getIdLocationType() {
		return idLocationType;
	}

	public void setIdLocationType(Long idLocationType) {
		this.idLocationType = idLocationType;
	}

	public String getNumCatalogo() {
		return numCatalogo;
	}

	public void setNumCatalogo(String numCatalogo) {
		this.numCatalogo = numCatalogo;
	}

	public String getVhcRemplazaPor() {
		return vhcRemplazaPor;
	}

	public void setVhcRemplazaPor(String vhcRemplazaPor) {
		this.vhcRemplazaPor = vhcRemplazaPor;
	}

	public HtmlSelectOneMenu getSelectModelo() {
		return selectModelo;
	}

	public void setSelectModelo(HtmlSelectOneMenu selectModelo) {
		this.selectModelo = selectModelo;
	}

	public Long getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}

	public boolean isShowPopupCities() {
		return showPopupCities;
	}

	public void setShowPopupCities(boolean showPopupCities) {
		this.showPopupCities = showPopupCities;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public ConsultsService getConsultService() {
		return consultService;
	}

	public void setConsultService(ConsultsService consultService) {
		this.consultService = consultService;
	}

	public String getVhcVidaUtil() {
		return vhcVidaUtil;
	}

	public void setVhcVidaUtil(String vhcVidaUtil) {
		this.vhcVidaUtil = vhcVidaUtil;
	}

	public HtmlInputText getTxtVidaUtil() {
		return txtVidaUtil;
	}

	public void setTxtVidaUtil(HtmlInputText txtVidaUtil) {
		this.txtVidaUtil = txtVidaUtil;
	}

	public HtmlInputText getTxtValorFob() {
		return txtValorFob;
	}

	public void setTxtValorFob(HtmlInputText txtValorFob) {
		this.txtValorFob = txtValorFob;
	}

	public HtmlInputText getTxtValorCIF() {
		return txtValorCIF;
	}

	public void setTxtValorCIF(HtmlInputText txtValorCIF) {
		this.txtValorCIF = txtValorCIF;
	}

	public Float getVhcValorCIF() {
		return vhcValorCIF;
	}

	public void setVhcValorCIF(Float vhcValorCIF) {
		this.vhcValorCIF = vhcValorCIF;
	}

	public Float getVhcValorFOB() {
		return vhcValorFOB;
	}

	public void setVhcValorFOB(Float vhcValorFOB) {
		this.vhcValorFOB = vhcValorFOB;
	}

	public Float getVhcCargosImportacion() {
		return vhcCargosImportacion;
	}

	public void setVhcCargosImportacion(Float vhcCargosImportacion) {
		this.vhcCargosImportacion = vhcCargosImportacion;
	}

	public HtmlInputText getTxtProveedor() {
		return txtProveedor;
	}

	public void setTxtProveedor(HtmlInputText txtProveedor) {
		this.txtProveedor = txtProveedor;
	}

	public HtmlInputText getTxtOrdenCompra() {
		return txtOrdenCompra;
	}

	public void setTxtOrdenCompra(HtmlInputText txtOrdenCompra) {
		this.txtOrdenCompra = txtOrdenCompra;
	}

	public HtmlInputText getTxtCargosImportacion() {
		return txtCargosImportacion;
	}

	public void setTxtCargosImportacion(HtmlInputText txtCargosImportacion) {
		this.txtCargosImportacion = txtCargosImportacion;
	}

	public String getVhcAnofabricacion() {
		return vhcAnofabricacion;
	}

	public void setVhcAnofabricacion(String vhcAnofabricacion) {
		this.vhcAnofabricacion = vhcAnofabricacion;
	}

	public String getVhcMesfabricacion() {
		return vhcMesfabricacion;
	}

	public void setVhcMesfabricacion(String vhcMesfabricacion) {
		this.vhcMesfabricacion = vhcMesfabricacion;
	}

	public HtmlInputText getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(HtmlInputText txtColor) {
		this.txtColor = txtColor;
	}

	public boolean isLocationVisible() {
		return locationVisible;
	}

	public void setLocationVisible(boolean locationVisible) {
		this.locationVisible = locationVisible;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public String getVhcCapacidadTanque() {
		return vhcCapacidadTanque;
	}

	public void setVhcCapacidadTanque(String vhcCapacidadTanque) {
		this.vhcCapacidadTanque = vhcCapacidadTanque;
	}
}
