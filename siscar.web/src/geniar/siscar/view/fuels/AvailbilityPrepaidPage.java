package geniar.siscar.view.fuels;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.fuels.services.SearchPrepaidService;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.Prepaid;
import geniar.siscar.model.PrepaidConsumption;
import geniar.siscar.model.Users;
import geniar.siscar.model.Vehicles;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.RowSelectorEvent;

public class AvailbilityPrepaidPage {

	private String placa;
	private String asignatario;
	private Date fechaIni;
	private Date fechaFin;
	private String centroCosto;
	private List<Prepaid> listaPrepagoDisponible;
	private List<PrepaidConsumption> listaDetallePrepago;
	private List<PrepaidConsumption> listaDetallePrepagoCostCenter;
	private boolean showDetailPrepaid = false;
	private boolean showDetailPrepaidCostCenter = false;
	private HtmlOutputText idCostCenters;
	private HtmlOutputText idUsuario;
	private HtmlInputText txtPlaca;
	private String nombreAsignatario;
	private String parametroCentroCostos;
	private HtmlOutputText txtCentroCosto;
	private HtmlOutputText txtPlacaPrepago;
	private HtmlCommandButton btnConsultar;
	private HtmlCommandButton btnConsultarCC;
	private HtmlCommandButton btnConsultarAsig;
	private SearchPrepaidService searchPrepaidService;
	private List<CostsCenters> listaCC;
	private List<Users> listUsers;
	private HtmlOutputText idCentroCosto;
	private Double disponible;
	private String disponibleCCTotal;
	private Double totalComprasCC;
	private Double totalConsumo;

	private String parametroBusquedad;
	private boolean showCentroCosto = false;
	private boolean showAsignatario = false;

	private ConsultsService consultsService;

	public ConsultsService getConsultsService() {
		return consultsService;
	}

	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	public SearchPrepaidService getSearchPrepaidService() {
		return searchPrepaidService;
	}

	public void setSearchPrepaidService(
			SearchPrepaidService searchPrepaidService) {
		this.searchPrepaidService = searchPrepaidService;
	}

	public HtmlOutputText getTxtCentroCosto() {
		return txtCentroCosto;
	}

	public void setTxtCentroCosto(HtmlOutputText txtCentroCosto) {
		this.txtCentroCosto = txtCentroCosto;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	public void action_filtrarConsumoPrepago(ActionEvent actionEvent) {

		try {
			Double totalComprasCCAcu = 0D;
			if (fechaFin == null
					&& fechaIni == null
					&& (placa == null || placa.trim().length() == 0)
					&& (asignatario == null || asignatario.trim().length() == 0)
					&& (centroCosto == null || centroCosto.trim().length() == 0))
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROBUSQUEDAD"));

			if (fechaFin == null || fechaIni == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			if (fechaFin != null && fechaIni != null
					&& fechaFin.compareTo(fechaIni) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			setListaPrepagoDisponible(null);

			if (placa != null && placa.trim().length() > 0) {

				validarDatos(placa);
				setListaPrepagoDisponible(null);
				setListaPrepagoDisponible(searchPrepaidService
						.consultarDisponibilidaPrepago(fechaIni, fechaFin,
								placa.toUpperCase()));
			}

			else if ((placa == null || placa.trim().length() == 0)
					&& (centroCosto != null || asignatario != null)) {

				setListaPrepagoDisponible(null);
				setListaPrepagoDisponible(searchPrepaidService
						.DisponibilidadPrepagoCCAsignatario(fechaIni, fechaFin,
								centroCosto, asignatario));
			}

			if (listaPrepagoDisponible != null
					&& listaPrepagoDisponible.size() > 0) {
				// disponibleCCTotal = searchPrepaidService
				// .disponibleCC(listaPrepagoDisponible.get(0)
				// .getCostCentersFuel().getCostsCenters()
				// .getCocNumero());
				// if (disponibleCCTotal != null
				// && disponibleCCTotal.trim().length() > 0)
				// disponibleCCTotal = Util.redondear(
				// new Double(disponibleCCTotal), 2).toString();

				for (Prepaid prepaid : listaPrepagoDisponible) {
					totalComprasCCAcu = totalComprasCCAcu
							+ prepaid.getPreTotal();
				}

				this.totalComprasCC = totalComprasCCAcu;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar() {
		this.fechaFin = null;
		this.fechaIni = null;
		this.placa = null;
		this.asignatario = null;
		setListaPrepagoDisponible(null);
		this.centroCosto = null;
		this.btnConsultar.setDisabled(false);
		this.btnConsultarAsig.setDisabled(false);
		this.btnConsultarCC.setDisabled(false);
		this.txtPlaca.setDisabled(false);
		this.nombreAsignatario = null;
		this.disponible = null;
		this.disponibleCCTotal = null;
		this.totalComprasCC = null;
		this.totalConsumo = null;
	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public String getAsignatario() {
		return asignatario;
	}

	public void setAsignatario(String asignatario) {
		this.asignatario = asignatario;
	}

	public void validarDatos(String placa) throws GWorkException {

		if (!Util.validarCadenaCaracteresEspecialesNumLetrasGuion(placa))
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CARACTER"));

		if (placa != null && placa.trim().length() != 0
				&& placa.trim().length() < 2)
			throw new GWorkException(Util
					.loadErrorMessageValue("PLACA.CONSULTA"));
	}

	public void action_detallarPrepago(ActionEvent actionEvent) {

		String placa = (String) txtPlacaPrepago.getValue();
		String costCenter = (String) txtCentroCosto.getValue();
		Double consumoAcu = 0D;
		String disponibleStr = null;

		try {

			if (placa != null) {
				for (Prepaid prepaid : listaPrepagoDisponible) {

					if (placa.equalsIgnoreCase(prepaid.getCostCentersFuel()
							.getVehiclesAssignation().getVehicles()
							.getVhcPlacaDiplomatica())
							&& costCenter.equalsIgnoreCase(prepaid
									.getCostCentersFuel().getCostsCenters()
									.getCocNumero())) {

						setListaDetallePrepago(searchPrepaidService
								.consumoPrepagoByVehiculo(prepaid
										.getCostCentersFuel().getCostsCenters()
										.getCocNumero(), placa, this.fechaIni,
										this.fechaFin));

						for (PrepaidConsumption prepaidConsumption : getListaDetallePrepago()) {
							consumoAcu = consumoAcu
									+ prepaidConsumption.getPrcValorConsumo();
						}
						totalConsumo = Util.redondear(consumoAcu, 2);
						disponibleStr = searchPrepaidService
								.disponibleCC(prepaid.getCostCentersFuel()
										.getCostsCenters().getCocNumero());

						if (disponibleStr != null)
							disponible = Util.redondear(new Double(
									disponibleStr), 2);
						showDetailPrepaid = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public boolean isShowDetailPrepaid() {
		return showDetailPrepaid;
	}

	public void setShowDetailPrepaid(boolean showDetailPrepaid) {
		this.showDetailPrepaid = showDetailPrepaid;
	}

	public void action_closeDetailPrepaid(ActionEvent actionEvent) {
		showDetailPrepaid = false;
	}

	public void listener_placa(ValueChangeEvent event) {

		String placa = (String) event.getNewValue();
		try {
			validarDatos(placa);
			if (placa != null && placa.trim().length() > 1) {
				Vehicles vehicles = null;
				vehicles = SearchVehicles
						.consultarVehiculosPorPlacaSinFiltros(placa
								.toUpperCase());

				if (vehicles != null) {
					btnConsultar.setDisabled(false);
					btnConsultarAsig.setDisabled(true);
					btnConsultarCC.setDisabled(true);
				} else if (vehicles == null) {
					btnConsultar.setDisabled(false);
					btnConsultarAsig.setDisabled(false);
					btnConsultarCC.setDisabled(false);
					throw new GWorkException(Util
							.loadErrorMessageValue("PLACA.EXISTEN"));
				}
			}

			if (placa == null || placa.trim().length() == 0) {

				btnConsultar.setDisabled(false);
				btnConsultarAsig.setDisabled(false);
				btnConsultarCC.setDisabled(false);
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public HtmlCommandButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(HtmlCommandButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}

	public HtmlCommandButton getBtnConsultarCC() {
		return btnConsultarCC;
	}

	public void setBtnConsultarCC(HtmlCommandButton btnConsultarCC) {
		this.btnConsultarCC = btnConsultarCC;
	}

	public HtmlCommandButton getBtnConsultarAsig() {
		return btnConsultarAsig;
	}

	public void setBtnConsultarAsig(HtmlCommandButton btnConsultarAsig) {
		this.btnConsultarAsig = btnConsultarAsig;
	}

	public boolean isShowCentroCosto() {
		return showCentroCosto;
	}

	public void setShowCentroCosto(boolean showCentroCosto) {
		this.showCentroCosto = showCentroCosto;
	}

	public boolean isShowAsignatario() {
		return showAsignatario;
	}

	public void setShowAsignatario(boolean showAsignatario) {
		this.showAsignatario = showAsignatario;
	}

	public void action_closeCentroCosto(ActionEvent actionEvent) {

		showCentroCosto = false;

	}

	public void action_closeAsignatario(ActionEvent actionEvent) {

		showAsignatario = false;
	}

	public void action_showAsignatario(ActionEvent actionEvent) {
		placa = null;
		showAsignatario = true;
	}

	public void action_showCentroCosto(ActionEvent actionEvent) {
		placa = null;
		showCentroCosto = true;
	}

	public String getParametroCentroCostos() {
		return parametroCentroCostos;
	}

	public void setParametroCentroCostos(String parametroCentroCostos) {
		this.parametroCentroCostos = parametroCentroCostos;
	}

	public List<CostsCenters> getListaCC() {
		return listaCC;
	}

	public void setListaCC(List<CostsCenters> listaCC) {
		this.listaCC = listaCC;
	}

	public void action_filtroBusquedaCentroCostos(ActionEvent actionEvent) {
		try {
			setListaCC(consultsService.centrosCosto(parametroCentroCostos
					.toUpperCase()));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void rowSelectorCostCenters(RowSelectorEvent event) {
		String idCostCenter = (String) idCostCenters.getValue();

		for (CostsCenters costsCenters : listaCC) {
			if (idCostCenter.equalsIgnoreCase(costsCenters.getCocNumero())) {
				setCentroCosto(costsCenters.getCocNumero());
				txtPlaca.setDisabled(true);
				showCentroCosto = false;
				parametroCentroCostos = null;
				nombreAsignatario = null;
				asignatario = null;
			}
		}
		setListaCC(null);

	}

	public HtmlOutputText getIdCostCenters() {
		return idCostCenters;
	}

	public void setIdCostCenters(HtmlOutputText idCostCenters) {
		this.idCostCenters = idCostCenters;
	}

	public HtmlInputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlInputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public String getParametroBusquedad() {
		return parametroBusquedad;
	}

	public void setParametroBusquedad(String parametroBusquedad) {
		this.parametroBusquedad = parametroBusquedad;
	}

	public List<Users> getListUsers() {
		return listUsers;
	}

	public void setListUsers(List<Users> listUsers) {
		this.listUsers = listUsers;
	}

	public void action_filtroBusquedaEmpleado(ActionEvent actionEvent) {
		try {
			setListUsers(consultsService.employeesCIAT(parametroBusquedad
					.toUpperCase(), parametroBusquedad.toUpperCase(),
					parametroBusquedad));
		} catch (GWorkException e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public HtmlOutputText getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(HtmlOutputText idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void rowSelectionEmployee(RowSelectorEvent rowSelectorEvent) {
		String idUsuario = (String) this.idUsuario.getValue();

		if (idUsuario != null) {
			for (Users users : listUsers) {

				if (idUsuario.equalsIgnoreCase(users.getUsrIdentificacion())) {
					setAsignatario(users.getUsrIdentificacion());
					setNombreAsignatario(users.getUsrNombre());
					txtPlaca.setDisabled(true);
					centroCosto = null;
					showAsignatario = false;
					setParametroBusquedad(null);
					setListUsers(null);
					break;
				}
			}
		}
	}

	public String getNombreAsignatario() {
		return nombreAsignatario;
	}

	public void setNombreAsignatario(String nombreAsignatario) {
		this.nombreAsignatario = nombreAsignatario;
	}

	public List<PrepaidConsumption> getListaDetallePrepago() {
		return listaDetallePrepago;
	}

	public void setListaDetallePrepago(
			List<PrepaidConsumption> listaDetallePrepago) {
		this.listaDetallePrepago = listaDetallePrepago;
	}

	public HtmlOutputText getIdCentroCosto() {
		return idCentroCosto;
	}

	public void setIdCentroCosto(HtmlOutputText idCentroCosto) {
		this.idCentroCosto = idCentroCosto;
	}

	public Double getDisponible() {
		return disponible;
	}

	public void setDisponible(Double disponible) {
		this.disponible = disponible;
	}

	public HtmlOutputText getTxtPlacaPrepago() {
		return txtPlacaPrepago;
	}

	public void setTxtPlacaPrepago(HtmlOutputText txtPlacaPrepago) {
		this.txtPlacaPrepago = txtPlacaPrepago;
	}

	public boolean isShowDetailPrepaidCostCenter() {
		return showDetailPrepaidCostCenter;
	}

	public void setShowDetailPrepaidCostCenter(
			boolean showDetailPrepaidCostCenter) {
		this.showDetailPrepaidCostCenter = showDetailPrepaidCostCenter;
	}

	public void action_showPrepaidCostCenter(ActionEvent actionEvent) {

		Long centroCosto = (Long) idCentroCosto.getValue();
		String disponibleCC = null;
		Double consumoAcu = 0D;

		try {

			if (centroCosto != null) {
				for (Prepaid prepaid : listaPrepagoDisponible) {

					if (centroCosto.longValue() == prepaid.getCostCentersFuel()
							.getCcfId().longValue()) {

						setListaDetallePrepagoCostCenter(searchPrepaidService
								.detalleConsumo(prepaid.getCostCentersFuel()
										.getCostsCenters().getCocCodigo()
										.longValue(), this.fechaIni,
										this.fechaFin));

						for (PrepaidConsumption prepaidConsumption : getListaDetallePrepagoCostCenter()) {
							consumoAcu = consumoAcu
									+ prepaidConsumption.getPrcValorConsumo();
						}
						totalConsumo = Util.redondear(consumoAcu, 2);
						disponibleCC = searchPrepaidService
								.disponibleCC(prepaid.getCostCentersFuel()
										.getCostsCenters().getCocNumero());

						if (disponibleCC != null)
							disponible = Util.redondear(
									new Double(disponibleCC), 2);

						showDetailPrepaidCostCenter = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_closePrepaidCostCenter(ActionEvent actionEvent) {

		showDetailPrepaidCostCenter = false;
	}

	public List<PrepaidConsumption> getListaDetallePrepagoCostCenter() {
		return listaDetallePrepagoCostCenter;
	}

	public void setListaDetallePrepagoCostCenter(
			List<PrepaidConsumption> listaDetallePrepagoCostCenter) {
		this.listaDetallePrepagoCostCenter = listaDetallePrepagoCostCenter;
	}

	public List<Prepaid> getListaPrepagoDisponible() {
		return listaPrepagoDisponible;
	}

	public void setListaPrepagoDisponible(List<Prepaid> listaPrepagoDisponible) {
		this.listaPrepagoDisponible = listaPrepagoDisponible;
	}

	public String getDisponibleCCTotal() {
		return disponibleCCTotal;
	}

	public void setDisponibleCCTotal(String disponibleCCTotal) {
		this.disponibleCCTotal = disponibleCCTotal;
	}

	public Double getTotalComprasCC() {
		return totalComprasCC;
	}

	public void setTotalComprasCC(Double totalComprasCC) {
		this.totalComprasCC = totalComprasCC;
	}

	public Double getTotalConsumo() {
		return totalConsumo;
	}

	public void setTotalConsumo(Double totalConsumo) {
		this.totalConsumo = totalConsumo;
	}

}
