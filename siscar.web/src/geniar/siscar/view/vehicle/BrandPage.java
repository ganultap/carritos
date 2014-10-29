package geniar.siscar.view.vehicle;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

import geniar.siscar.logic.vehicle.services.BrandService;
import geniar.siscar.model.Brands;

import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.NavigationResults;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;

import geniar.siscar.view.BaseBean;

import gwork.exception.GWorkException;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * 
 * @author Rodrigo Lopez Ramos Geniar WEB
 * 
 */
public class BrandPage extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BrandService brandService;

	private Long idBrand;

	private HtmlInputText txtNombre;
	private Brands brands = null;
	private String nombre;
	private HtmlSelectOneMenu cbxBrand;
	private static Integer ELIMINAR = 2;
	private static Integer MODIFICAR = 1;
	private Integer opcConfirmacion;
	private boolean activarConfirmacion;

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public HtmlSelectOneMenu getCbxBrand() {
		return cbxBrand;
	}

	public void setCbxBrand(HtmlSelectOneMenu cbxBrand) {
		this.cbxBrand = cbxBrand;
	}

	public HtmlInputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(HtmlInputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public void listener_brand(ValueChangeEvent event) {
		Long sbId = (Long) event.getNewValue();
		brands = new Brands();
		try {

			if (sbId != null && sbId != -1L) {
				brands = brandService.consultarMarcaPorId(sbId);
				txtNombre.setValue(brands.getBrnNombre());
			} else if (sbId.longValue() == -1L && nombre != null) {
				limpiar();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void action_crear(ActionEvent event) {
		try {
			String nombre = (String) txtNombre.getValue();

			if (nombre == null || nombre.trim().length() == 0)
				throw new GWorkException(Util.loadErrorMessageValue("MARCA"));

			validarNombre(nombre);
			brandService.crearMarcas(nombre.toUpperCase());
			// TODO hacer mensajes de usuarios
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificar(ActionEvent event) {
		try {

			validateData(idBrand, nombre);

			validarNombre(nombre);
			setOpcConfirmacion(MODIFICAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void modificarBrand() {

		try {

			if (idBrand != null) {
				brandService.modificarMarca(brands.getBrnId(), nombre
						.toUpperCase());
				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			}
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	public void action_eliminarBrand(ActionEvent event) {
		try {

			validateData(idBrand, nombre);
			validarNombre(nombre);
			setOpcConfirmacion(ELIMINAR);
			activarConfirmacion = true;
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void eliminarBrand() {

		try {
			if (idBrand != null) {
				brandService.eliminarMarca(idBrand);
				limpiar();
				mostrarMensaje(Util.loadMessageValue("EXITO.ELIMINAR"), false);
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

	}

	/**
	 * @return
	 */
	public String action_cancelar() {
		return NavigationResults.HOME;
	}

	/**
	 * 
	 * @param idBrand
	 * @param nombre
	 * @return
	 */

	private void validateData(Long idBrand, String nombre)
			throws GWorkException {

		if (idBrand == null || idBrand.longValue() == 0
				|| idBrand.longValue() == -1L)
			throw new GWorkException(Util.loadErrorMessageValue("MARCA.SEL"));

	}

	public BrandService getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public void action_limpiar() {
		txtNombre.setValue("");
	}

	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public Brands getBrands() {
		return brands;
	}

	public void setBrands(Brands brands) {
		this.brands = brands;
	}

	public void action_limpiar(ActionEvent event) {
		limpiar();
//		try {
//			brandService.pruebasContables();
//		} catch (GWorkException e) {
//			e.printStackTrace();
//		}
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public void limpiar() {
		txtNombre.setValue("");
		idBrand = -1L;
	}

	public void validarNombre(String nombre) throws GWorkException {
		boolean esValido = true;

		esValido = Util.validarCadenaCaracteresEspecialesNumLetrasGuion(nombre);

		if (!esValido)
			throw new GWorkException(Util
					.loadErrorMessageValue("NOMBRES.CARACTERES"));

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}

	public void validateMinLenght(FacesContext context, UIComponent validate,
			Object value) throws GWorkException {
		String inputText = (String) value;

		if (inputText.length() <= 1) {
			((UIInput) validate).setValid(false);
			FacesUtils.addErrorMessage(Util
					.loadErrorMessageValue("NOMBRE.MINTAMAÑO"));
		}
	}

}
