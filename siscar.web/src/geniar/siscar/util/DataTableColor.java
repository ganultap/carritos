package geniar.siscar.util;

import geniar.siscar.logic.vehicle.services.ColorService;
import geniar.siscar.model.Colors;
import geniar.siscar.persistence.ColorsDAO;
import geniar.siscar.view.vehicle.VehiclePage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlOutputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

public class DataTableColor {

	private int rows;
	private boolean showAvailability;
	private HtmlOutputText txtIdColor;
	private HtmlInputText txtColor;
	private HtmlSelectOneMenu SelectColor;
	private String color;
	private Long idColor;
	private ColorService colorService;

	private List<geniar.siscar.util.Colors> listColors;

	public List<geniar.siscar.util.Colors> getListColors() {
		return listColors;
	}

	public void setListColors(List<geniar.siscar.util.Colors> listColors) {
		this.listColors = listColors;
	}

	public void action_aceptar(ActionEvent event) {
		try {
			Colors colors = null;
			VehiclePage vehiclePage = (VehiclePage) FacesUtils.getManagedBean("vehiclePage");
			if (vehiclePage != null) {
				if (idColor != null && !idColor.equals(-1L)) {
					colors = new ColorsDAO().findById(idColor);
					if (colors != null)
						vehiclePage.setVhcColor(colors.getClNombre());
					setShowAvailability(false);
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void llenarTablaColores(ActionEvent event) throws GWorkException {
		setShowAvailability(true);
		listColors = new ArrayList<geniar.siscar.util.Colors>();

		geniar.siscar.util.Colors colorsPage = new geniar.siscar.util.Colors();

		colorsPage.setRojo(Util.loadParametersValue("1"));

		colorsPage.setAzul(Util.loadParametersValue("2"));

		colorsPage.setBlanco(Util.loadParametersValue("3"));

		colorsPage.setGrisClaro(Util.loadParametersValue("4"));

		colorsPage.setGris(Util.loadParametersValue("5"));

		colorsPage.setGrisOscuro(Util.loadParametersValue("6"));

		colorsPage.setAmarillo(Util.loadParametersValue("7"));

		colorsPage.setNegro(Util.loadParametersValue("8"));

		colorsPage.setAzulCeleste(Util.loadParametersValue("9"));

		colorsPage.setAzulOscuro(Util.loadParametersValue("10"));

		colorsPage.setAzulClaro(Util.loadParametersValue("11"));

		colorsPage.setMarron(Util.loadParametersValue("12"));

		colorsPage.setBeige(Util.loadParametersValue("13"));

		listColors.add(colorsPage);
		setListColors(listColors);
		rows = listColors.size();
	}

	public void seleccionarColor(ValueChangeEvent event) throws GWorkException {
		try {
			if (event.getNewValue() != null) {
				String valor = event.getNewValue().toString();

				if (valor != null && !valor.equalsIgnoreCase("-1"))
					if (txtColor != null && txtColor.getValue().toString().trim().length() != 0)
						txtColor.setValue(null);

				if (txtColor != null && !valor.equalsIgnoreCase("-1")) {
					setColor(null);
					txtColor.setDisabled(true);
				} else if (txtColor != null) {
					txtColor.setDisabled(false);
					setColor(null);
				}
			}
		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void cargarCatalogoModelo(ValueChangeEvent event) {
		if (event.getNewValue() != null) {
			String valor = event.getNewValue().toString();
			if (valor != null) {
				geniar.siscar.model.Colors models = new ColorsDAO().findById(new Long(valor));
				if (models != null) {
					setColor(models.getClNombre());
				}
			}
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void elegirColor(ActionEvent event) {
		String param = (String) FacesUtils.getRequestParameter("color");
		VehiclePage vehiclePage = (VehiclePage) FacesUtils.getManagedBean("vehiclePage");
		if (param != null && param.trim().length() != 0 && vehiclePage != null) {
			vehiclePage.setVhcColor(param);
			if (vehiclePage.getTxtColor() != null)
				vehiclePage.getTxtColor().setReadonly(true);
		}
		showAvailability = false;
	}

	public void action_crear(ActionEvent event) {
		try {
			String colorTemp = null;
			boolean isValid = true;

			if (idColor != null && idColor.equals(-1L))
				if (color != null && color.trim().length() == 0)
					throw new GWorkException(Util.loadErrorMessageValue("COLOR"));

			if (color != null && color.trim().length() != 0)
				isValid = Util.validarCadenaCaracteres(color);

			if (!isValid)
				throw new GWorkException(Util.loadErrorMessageValue("COLOR.CARACTER"));

			if (color != null && color.trim().length() != 0 && color.trim().length() < 2)
				throw new GWorkException(Util.loadErrorMessageValue("COLOR.MINIMO"));

			if (idColor != null && idColor.equals(-1L)) {
				if (color != null && color.trim().length() != 0)
					colorTemp = color;
				isValid = false;
			} else
				colorTemp = idColor.toString();
			if (colorTemp != null && txtColor.getValue().toString().trim().length() != 0)
				colorService.guardarColorCtrl(colorTemp);

			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void limpiar() {
		if (SelectColor != null)
			SelectColor.setValue("-1");

		if (color != null)
			setColor(null);
	}

	/**
	 * 
	 * @param event
	 */
	public void action_closeAvailability(ActionEvent event) {
		if (listColors != null && !listColors.isEmpty())
			listColors.clear();

		showAvailability = false;
	}

	public boolean isShowAvailability() {
		return showAvailability;
	}

	public void setShowAvailability(boolean showAvailability) {
		this.showAvailability = showAvailability;
	}

	public HtmlOutputText getTxtIdColor() {
		return txtIdColor;
	}

	public void setTxtIdColor(HtmlOutputText txtIdColor) {
		this.txtIdColor = txtIdColor;
	}

	public HtmlInputText getTxtColor() {
		return txtColor;
	}

	public void setTxtColor(HtmlInputText txtColor) {
		this.txtColor = txtColor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public HtmlSelectOneMenu getSelectColor() {
		return SelectColor;
	}

	public void setSelectColor(HtmlSelectOneMenu selectColor) {
		SelectColor = selectColor;
	}

	public ColorService getColorService() {
		return colorService;
	}

	public void setColorService(ColorService colorService) {
		this.colorService = colorService;
	}

	public Long getIdColor() {
		return idColor;
	}

	public void setIdColor(Long idColor) {
		this.idColor = idColor;
	}
}
