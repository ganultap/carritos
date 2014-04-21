package geniar.siscar.view.fuels;

import geniar.siscar.logic.fuels.services.AverageFuelService;
import geniar.siscar.model.ServiceRegistry;
import geniar.siscar.model.VORegistryService;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.selectinputdate.SelectInputDate;

public class ServiceRegisterPage {

	protected boolean isExpanded;
	private List<Object[]> listaRegistroCombustible;
	private AverageFuelService averageFuelService;
	private List<Object[]> listaPromedioTipoVHC;
	private List<VORegistryService> listServiceRegistry;

	private boolean showHistory = false;
	private String placa;

	private HtmlOutputText txtLinea;
	private HtmlOutputText txtPlaca;
	private HtmlOutputText txtCombustible;
	private HtmlOutputText txtTraccion;

	private SelectInputDate txtFechaIni;
	private SelectInputDate txtFechaFin;

	private Date filtroFechaIni;
	private Date filtroFechaFin;

	private Double totalGalones;
	private Double totalConsumo;

	public AverageFuelService getAverageFuelService() {
		return averageFuelService;
	}

	public void setAverageFuelService(AverageFuelService averageFuelService) {
		this.averageFuelService = averageFuelService;
	}

	public List<Object[]> getListaRegistroCombustible() {
		return listaRegistroCombustible;
	}

	public void setListaRegistroCombustible(
			List<Object[]> listaRegistroCombustible) {
		this.listaRegistroCombustible = listaRegistroCombustible;
	}

	public boolean isExpanded() {
		return isExpanded;
	}

	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}

	// public void toggleSubGroupAction(ActionEvent event) {
	// // toggle expanded state
	// isExpanded = !isExpanded;
	//
	// // add sub elements to list
	// if (isExpanded) {
	// expandNodeAction();
	// }
	// // remove items from list
	// else {
	// contractNodeAction();
	// }
	// }
	//
	// private void expandNodeAction() {
	// if (childFilesRecords != null && childFilesRecords.size() > 0) {
	// // get index of current node
	// int index = parentInventoryList.indexOf(this);
	//
	// // add all items in childFilesRecords to the parent list
	// parentInventoryList.addAll(index + 1, childFilesRecords);
	// }
	//
	// }

	public void action_filtraRegistroServicio(ActionEvent actionEvent) {

		try {

			if (filtroFechaIni == null || filtroFechaFin == null)
				throw new GWorkException(Util
						.loadErrorMessageValue("FECHAS.CONSULTA.NULO"));

			if (filtroFechaFin.compareTo(filtroFechaIni) < 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("FCH_INI_FCH_FIN"));

			setListaPromedioTipoVHC(null);
			setListaPromedioTipoVHC(averageFuelService.promedioPorTipoVehiculo(
					filtroFechaIni, filtroFechaFin));

			txtFechaFin.setDisabled(true);
			txtFechaIni.setDisabled(true);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public Date getFiltroFechaIni() {
		return filtroFechaIni;
	}

	public void limpiar() {

		filtroFechaFin = null;
		filtroFechaIni = null;
		setListaPromedioTipoVHC(null);
		setListaRegistroCombustible(null);
		setListServiceRegistry(null);
		txtFechaFin.setDisabled(false);
		txtFechaIni.setDisabled(false);
	}

	public void action_limpiar(ActionEvent actionEvent) {

		limpiar();
	}

	public void setFiltroFechaIni(Date filtroFechaIni) {
		this.filtroFechaIni = filtroFechaIni;
	}

	public Date getFiltroFechaFin() {
		return filtroFechaFin;
	}

	public void setFiltroFechaFin(Date filtroFechaFin) {
		this.filtroFechaFin = filtroFechaFin;
	}

	public List<Object[]> getListaPromedioTipoVHC() {

		return listaPromedioTipoVHC;
	}

	public void setListaPromedioTipoVHC(List<Object[]> listaPromedioTipoVHC) {
		this.listaPromedioTipoVHC = listaPromedioTipoVHC;
	}

	public void action_detallarMarcaCombustible(ActionEvent actionEvent) {
		try {
			String linea = (String) txtLinea.getValue();

			for (Object[] promedioLinea : listaPromedioTipoVHC) {
				if (promedioLinea[1].toString().equalsIgnoreCase(linea)) {

					setListaRegistroCombustible(averageFuelService
							.detalleConsumoVHC(filtroFechaIni, filtroFechaFin,
									linea,
									txtCombustible.getValue().toString(),
									txtTraccion.getValue().toString()));
					break;

				}
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public HtmlOutputText getTxtLinea() {
		return txtLinea;
	}

	public void setTxtLinea(HtmlOutputText txtLinea) {
		this.txtLinea = txtLinea;
	}

	public boolean isShowHistory() {
		return showHistory;
	}

	public void setShowHistory(boolean showHistory) {
		this.showHistory = showHistory;
	}

	public void action_closeShowHistory(ActionEvent actionEvent) {

		showHistory = false;
	}

	public void action_showShowHistory(ActionEvent actionEvent) {

		String placa = (String) txtPlaca.getValue();
		String totalGalonesConsulta = null;
		Double totalConsumoConsulta = null;
		List<ServiceRegistry> historialCombustibleVHC = null;
		List<VORegistryService> listaVOService = new ArrayList<VORegistryService>();
		try {
			for (Object[] registroCombustible : listaRegistroCombustible) {
				if (placa.equalsIgnoreCase(registroCombustible[4].toString())) {

					historialCombustibleVHC = averageFuelService
							.historialConsumoVehiculo(filtroFechaIni,
									filtroFechaFin, placa);

					for (ServiceRegistry serviceRegistry : historialCombustibleVHC) {
						VORegistryService ObjregistryService = new VORegistryService();
						Float promedioCombustible = null;

						ObjregistryService.setVehicles(serviceRegistry
								.getVehicles());
						if (serviceRegistry.getSerNumRebPag() != null)

							ObjregistryService.setNroRecibo(serviceRegistry
									.getSerNumRebPag());
						ObjregistryService.setSercodigo(serviceRegistry
								.getSerCodigo());

						ObjregistryService.setFechaRecibo(serviceRegistry
								.getSerFecha());
						ObjregistryService.setGalones(serviceRegistry
								.getSerNumeroGalones());
						ObjregistryService
								.setSerKilometrajeActual(serviceRegistry
										.getSerKilometrajeActual());
						ObjregistryService
								.setSerKilometrajeAnterior(serviceRegistry
										.getSerKilometrajeAnterior());

						promedioCombustible = (new Float(serviceRegistry
								.getSerKilometrajeActual()) - new Float(
								serviceRegistry.getSerKilometrajeAnterior()))
								/ serviceRegistry.getSerNumeroGalones();

						promedioCombustible = Util.redondear(
								promedioCombustible, 2).floatValue();

						ObjregistryService.setKmPromedio(promedioCombustible);

						listaVOService.add(ObjregistryService);

					}

					showHistory = true;
					this.placa = placa;
					totalGalonesConsulta = averageFuelService.totalGalones(
							filtroFechaIni, filtroFechaFin, placa);

					if (totalGalonesConsulta != null
							&& totalGalonesConsulta.trim().length() > 0)
						this.totalGalones = Util.redondear(new Double(
								//modificado el 24 de abril 2012  totalGalonesConsulta), 3);
								totalGalonesConsulta), 2);

					totalConsumoConsulta = averageFuelService
							.totalKMCombustible(filtroFechaIni, filtroFechaFin,
									placa);

					if (totalConsumoConsulta != null)
						this.totalConsumo = Util.redondear(new Double(
								totalConsumoConsulta), 2);
					break;
				}
			}
			setListServiceRegistry(null);
			setListServiceRegistry(listaVOService);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public HtmlOutputText getTxtPlaca() {
		return txtPlaca;
	}

	public void setTxtPlaca(HtmlOutputText txtPlaca) {
		this.txtPlaca = txtPlaca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public HtmlOutputText getTxtCombustible() {
		return txtCombustible;
	}

	public void setTxtCombustible(HtmlOutputText txtCombustible) {
		this.txtCombustible = txtCombustible;
	}

	public HtmlOutputText getTxtTraccion() {
		return txtTraccion;
	}

	public void setTxtTraccion(HtmlOutputText txtTraccion) {
		this.txtTraccion = txtTraccion;
	}

	public Double getTotalGalones() {
		return totalGalones;
	}

	public void setTotalGalones(Double totalGalones) {
		this.totalGalones = totalGalones;
	}

	public SelectInputDate getTxtFechaIni() {
		return txtFechaIni;
	}

	public void setTxtFechaIni(SelectInputDate txtFechaIni) {
		this.txtFechaIni = txtFechaIni;
	}

	public SelectInputDate getTxtFechaFin() {
		return txtFechaFin;
	}

	public void setTxtFechaFin(SelectInputDate txtFechaFin) {
		this.txtFechaFin = txtFechaFin;
	}

	public Double getTotalConsumo() {
		return totalConsumo;
	}

	public void setTotalConsumo(Double totalConsumo) {
		this.totalConsumo = totalConsumo;
	}

	public List<VORegistryService> getListServiceRegistry() {
		return listServiceRegistry;
	}

	public void setListServiceRegistry(
			List<VORegistryService> listServiceRegistry) {
		this.listServiceRegistry = listServiceRegistry;
	}
}
