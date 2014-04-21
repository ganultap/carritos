package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.consultas.SearchVehicles;
import geniar.siscar.logic.vehicle.services.BrandService;
import geniar.siscar.model.Brands;
import geniar.siscar.model.Lines;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

/**
 * 
 * @author Rodrigo Lopez Geniar WEB
 * 
 */
public class SelectItemBrandPage {

	private SelectItem[] listBrands;
	private SelectItem[] listLines;
	private BrandService brandService;
	private List<Brands> listUtilBrands;
	private List<Lines> listUtilLines;

	public List<Brands> getListUtilBrands() {
		try {
			listUtilBrands = brandService.listaMarcas();
		} catch (GWorkException e) {
			e.printStackTrace();
		}

		return listUtilBrands;
	}

	public void setListUtilBrands(List<Brands> listUtilBrands) {
		this.listUtilBrands = listUtilBrands;
	}

	/**
	 * Metodo que llena el combo box de la jsp brand.jspx con las marcas de
	 * vehiculoss
	 * 
	 * @return
	 */
	public SelectItem[] getBrands() {
		try {
			listBrands = null;
			listUtilBrands = SearchParameters.getAllBrands();
			listBrands = new SelectItem[listUtilBrands.size() + 1];
			listBrands[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (Brands brand : listUtilBrands) {
				listBrands[i] = new SelectItem(brand.getBrnId(), brand.getBrnNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listBrands;
	}

	/**
	 * Metodo que permite realizar una lista enlazada entre combo de marcas y
	 * lineas
	 * 
	 * @param changeEvent
	 */
	public void changeBrand(ValueChangeEvent changeEvent) {
		
		if ( changeEvent.getNewValue() != null) {
			String idBrand = (String) changeEvent.getNewValue().toString();
			try {
				if (!idBrand.equalsIgnoreCase("-1")) {
					listLines = null;
					listUtilLines = SearchVehicles.consultarLineasDeMarca(new Long(idBrand));
					setListUtilLines(listUtilLines);
				} else {
					if (listUtilLines != null)
						listUtilLines.clear();
					
					setListUtilLines(listUtilLines);
				}
			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}	
		}
	}

	public void setListBrands(SelectItem[] listBrands) {
		this.listBrands = listBrands;
	}

	public SelectItem[] getListLines() {
		if (getListUtilLines() != null && getListUtilLines().size() > 0) {
			listLines = new SelectItem[getListUtilLines().size() + 1];
			int i = 1;
			listLines[0] = new SelectItem("-1", "--SELECCIONAR--");
			for (Lines lines : getListUtilLines()) {
				listLines[i] = new SelectItem(lines.getLnsId(), lines.getLnsNombre());
				i++;
			}
		}
		return listLines;
	}

	public void setListLines(SelectItem[] listLines) {
		this.listLines = listLines;
	}

	public BrandService getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public List<Lines> getListUtilLines() {
		return listUtilLines;
	}

	public void setListUtilLines(List<Lines> listUtilLines) {
		this.listUtilLines = listUtilLines;
	}
}
