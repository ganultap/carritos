package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.parameters.services.NoveltyTypesService;
import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

public class SelectItemNoveltyTypesPage {

	private SelectItem[] itemsTipoNovedad;
	private SelectItem[] listNovelty;
	private List<NoveltyTypes> listUtilNovelty;
	private NoveltyTypesService noveltyTypesService;

	public SelectItem[] getItemsTipoNovedad() {

		List<NoveltyTypes> lstTipoNovedad = null;
		try {
			lstTipoNovedad = noveltyTypesService.consultarTodosTiposNovedad();

			itemsTipoNovedad = new SelectItem[lstTipoNovedad.size() + 1];

			itemsTipoNovedad[0] = new SelectItem(-1, "--SELECCIONAR--");

			int i = 1;
			for (NoveltyTypes noveltyTypes : lstTipoNovedad) {
				itemsTipoNovedad[i] = new SelectItem(noveltyTypes.getNtId(),
						noveltyTypes.getNtNombre());
				i++;
			}

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}

		return itemsTipoNovedad;
	}

	/**
	 * Metodo que llena el combo box de la jsp brand.jspx con las marcas de
	 * vehiculoss
	 * 
	 * @return
	 */
	public SelectItem[] getNovelty() {
		try {
			listNovelty = null;
			listUtilNovelty = SearchParameters.getAllNovelty();
			listNovelty = new SelectItem[listUtilNovelty.size() + 2];
			listNovelty[0] = new SelectItem("-1", "--SELECCIONAR--");
			listNovelty[1] = new SelectItem(-2L, Util
					.loadMessageValue("COBRO_AUTOSURE"));
			int i = 2;
			for (NoveltyTypes novelty : listUtilNovelty) {
				listNovelty[i] = new SelectItem(novelty.getNtId(), novelty
						.getNtNombre());
				i++;
			}

		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listNovelty;
	}

	public void setItemsTipoNovedad(SelectItem[] itemsTipoNovedad) {
		this.itemsTipoNovedad = itemsTipoNovedad;
	}

	public NoveltyTypesService getNoveltyTypesService() {
		return noveltyTypesService;
	}

	public void setNoveltyTypesService(NoveltyTypesService noveltyTypesService) {
		this.noveltyTypesService = noveltyTypesService;
	}
}
