package geniar.siscar.view.vehicle;

import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.SupplyingService;
import geniar.siscar.logic.vehicle.services.impl.SupplyingServiceImpl;
import geniar.siscar.model.SupplyingCatalogs;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import geniar.siscar.view.parameters.SelectItemSupplyingCatalogsPage;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

public class VehicleSupplyingPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idMarca;
	private Long idLinea;
	private String idModeloCatalogo;
	private String numCatalogo;
	private String modeloVehiculo;
	private HtmlInputText txtModelo;
	private HtmlSelectOneMenu selectLineas;
	private HtmlSelectOneMenu selectMarcas;
	private HtmlSelectOneMenu selectModelo;
	private HtmlSelectOneMenu selectCatalogos;
	private List<SupplyingCatalogs> listSupplyingCatalogs;
	private boolean mostrarTabla;

	// Service
	private SupplyingService supplyingService;
	
	
	/**
	 * @param evento
	 */
	public void action_crear(ActionEvent event) {
		try {
			String marca = null;
			String linea = null;
			String modelo = null;
			boolean isValid = true;

			if (idMarca != null)
				marca = idMarca.toString();

			if (idLinea != null)
				linea = idLinea.toString();

			if (idModeloCatalogo != null && idModeloCatalogo.equalsIgnoreCase("-1"))
				if (modeloVehiculo != null && modeloVehiculo.trim().length() == 0)
					throw new GWorkException(Util.loadErrorMessageValue("MODELO"));

			if (modeloVehiculo != null && modeloVehiculo.trim().length() != 0)
				isValid = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(modeloVehiculo);

			if (!isValid)
				throw new GWorkException(Util.loadErrorMessageValue("MODELO.CARACTER"));

			if (modeloVehiculo != null && modeloVehiculo.trim().length() != 0 && modeloVehiculo.trim().length() < 2)
				throw new GWorkException(Util.loadErrorMessageValue("MODELO.MINIMO"));

			if (numCatalogo != null && numCatalogo.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("NUM.CATALGO"));

			if (numCatalogo != null && numCatalogo.trim().length() != 0)
				isValid = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(numCatalogo);

			if (!isValid)
				throw new GWorkException(Util.loadErrorMessageValue("NUM.CATALOGO.CARACTER"));

			if (numCatalogo != null && numCatalogo.trim().length() < 2)
				throw new GWorkException(Util.loadErrorMessageValue("NUM.CATALOGO.MINIMO"));

			if (marca != null && marca.equalsIgnoreCase("-1"))
				throw new GWorkException(Util.loadErrorMessageValue("MARCA.SEL"));

			if (linea == null || linea.equalsIgnoreCase("-1") || linea.equalsIgnoreCase("0"))
				throw new GWorkException(Util.loadErrorMessageValue("LINEA.SEL"));

			if (idModeloCatalogo != null && idModeloCatalogo.equalsIgnoreCase("-1")) {
				if (modeloVehiculo != null && modeloVehiculo.trim().length() != 0)
					modelo = modeloVehiculo;
				isValid = false;
			} else
				modelo = idModeloCatalogo;

			supplyingService.crearCatalogoAprovisionamientoVehiculo(modelo, numCatalogo, idMarca, idLinea, isValid);

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
			actualizarListaCatalogos();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void actualizarListaCatalogos() throws GWorkException {
		SelectItemSupplyingCatalogsPage catalogsPage = (SelectItemSupplyingCatalogsPage) FacesUtils
				.getManagedBean("selectItemSuppliyngPage");
		SupplyingService supplyingService = new SupplyingServiceImpl();
		List<SupplyingCatalogs> listUtilSupplyingCatalogs = null;
		if (catalogsPage != null) {
			listUtilSupplyingCatalogs = supplyingService.getListCatalogs();
			catalogsPage.setListUtilSupplyingCatalogs(listUtilSupplyingCatalogs);
		}
	}

	public void mostrarTabla(String valor) throws GWorkException {
		try {
			if (selectModelo != null && selectModelo.getValue().toString().equalsIgnoreCase("-1"))
				throw new GWorkException(Util.loadErrorMessageValue("CATALOGO.SEL"));

			listSupplyingCatalogs = SearchVehicles.consultarCatalogoPorIdModelo(new Long(valor));
			if (listSupplyingCatalogs != null && listSupplyingCatalogs.size() > 0) {
				for (SupplyingCatalogs supplyingCatalogs : listSupplyingCatalogs) {
					supplyingCatalogs.getLines().getBrands().getBrnNombre();
					supplyingCatalogs.getLines().getLnsNombre();
				}

				setListSupplyingCatalogs(listSupplyingCatalogs);
				setMostrarTabla(true);
			} else
				throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_eliminarCatalogo(ActionEvent event) throws GWorkException {
		try {
			eliminarCatalogoSession();
			String idCatalogo = (String) FacesUtils.getRequestParameter("idCatalogo");
			if (selectModelo != null && selectModelo.getValue().toString().equalsIgnoreCase("-1"))
				throw new GWorkException(Util.loadErrorMessageValue("CATALOGO.SEL"));

			if (idCatalogo != null && idCatalogo.trim().length() != 0)
				FacesUtils.getSession().setAttribute("idCatalogo", idCatalogo);

			if (idModeloCatalogo != null && idModeloCatalogo.trim().length() != 0)
				FacesUtils.getSession().setAttribute("idModelo", idModeloCatalogo);

			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"), true);

			actualizarListaCatalogos();

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void isSelected(ValueChangeEvent event) throws GWorkException {
		
		if (event.getNewValue() != null) {
			try {
				String valor = event.getNewValue().toString();

				if (valor != null && !valor.equalsIgnoreCase("-1"))
					if (txtModelo != null && txtModelo.getValue().toString().trim().length() != 0)
						txtModelo.setValue(null);

				if (txtModelo != null && !valor.equalsIgnoreCase("-1")) {
					setIdModeloCatalogo(null);
					txtModelo.setDisabled(true);
				} else if (txtModelo != null) {
					txtModelo.setDisabled(false);
					setIdModeloCatalogo(null);
				}
				if (valor != null && !valor.equalsIgnoreCase("-1"))
					mostrarTabla(valor);
				else
					setListSupplyingCatalogs(null);
			} catch (Exception e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
	}

	public void limpiar() {
		try {
			if (selectCatalogos != null)
				selectCatalogos.setValue("-1");

			if (selectLineas != null)
				selectLineas.setValue("-1");

			if (selectMarcas != null)
				selectMarcas.setValue("-1");

			if (selectModelo != null)
				selectModelo.setValue("-1");

			if (txtModelo != null) {
				txtModelo.setDisabled(false);
				txtModelo.setValue(null);
			}
			setNumCatalogo(null);
			setModeloVehiculo(null);
			limpiarTabla();

		} catch (Exception e) {
			mostrarMensaje(e.getMessage(), false);
		}
	}

	public void limpiarTabla() {
		setMostrarTabla(false);
		setListSupplyingCatalogs(null);
	}

	public void limpiarCampos(ActionEvent event) {
		limpiar();
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void eliminarCatalogoSession() {
		FacesUtils.getSession().removeAttribute("catalogo");
	}

	public Long getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}

	public Long getIdLinea() {
		return idLinea;
	}

	public void setIdLinea(Long idLinea) {
		this.idLinea = idLinea;
	}

	public String getModeloVehiculo() {
		return modeloVehiculo;
	}

	public void setModeloVehiculo(String modeloVehiculo) {
		this.modeloVehiculo = modeloVehiculo;
	}

	public SupplyingService getSupplyingService() {
		return supplyingService;
	}

	public void setSupplyingService(SupplyingService supplyingService) {
		this.supplyingService = supplyingService;
	}

	public String getNumCatalogo() {
		return numCatalogo;
	}

	public void setNumCatalogo(String numCatalogo) {
		this.numCatalogo = numCatalogo;
	}

	public HtmlSelectOneMenu getSelectLineas() {
		return selectLineas;
	}

	public void setSelectLineas(HtmlSelectOneMenu selectLineas) {
		this.selectLineas = selectLineas;
	}

	public HtmlSelectOneMenu getSelectMarcas() {
		return selectMarcas;
	}

	public void setSelectMarcas(HtmlSelectOneMenu selectMarcas) {
		this.selectMarcas = selectMarcas;
	}

	public HtmlSelectOneMenu getSelectModelo() {
		return selectModelo;
	}

	public void setSelectModelo(HtmlSelectOneMenu selectModelo) {
		this.selectModelo = selectModelo;
	}

	public HtmlSelectOneMenu getSelectCatalogos() {
		return selectCatalogos;
	}

	public void setSelectCatalogos(HtmlSelectOneMenu selectCatalogos) {
		this.selectCatalogos = selectCatalogos;
	}

	public HtmlInputText getTxtModelo() {
		return txtModelo;
	}

	public void setTxtModelo(HtmlInputText txtModelo) {
		this.txtModelo = txtModelo;
	}

	public String getIdModeloCatalogo() {
		return idModeloCatalogo;
	}

	public void setIdModeloCatalogo(String idModeloCatalogo) {
		this.idModeloCatalogo = idModeloCatalogo;
	}

	public List<SupplyingCatalogs> getListSupplyingCatalogs() {
		return listSupplyingCatalogs;
	}

	public void setListSupplyingCatalogs(List<SupplyingCatalogs> listSupplyingCatalogs) {
		this.listSupplyingCatalogs = listSupplyingCatalogs;
	}

	public boolean isMostrarTabla() {
		return mostrarTabla;
	}

	public void setMostrarTabla(boolean mostrarTabla) {
		this.mostrarTabla = mostrarTabla;
	}
}
