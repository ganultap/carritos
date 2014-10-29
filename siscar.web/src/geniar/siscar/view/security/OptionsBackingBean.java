package geniar.siscar.view.security;

import geniar.siscar.logic.security.serivice.OptionsService;
import geniar.siscar.logic.security.serivice.impl.OptionsServiceImpl;
import geniar.siscar.model.Options;
import geniar.siscar.model.OptionsRolls;
import geniar.siscar.model.Rolls;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;

import com.icesoft.faces.component.ext.HtmlCommandButton;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : BackingBean para el manejo de los eventos del page de Opciones
 * 
 */
public class OptionsBackingBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OptionsService optionsService;
	private HtmlCommandButton btnCrear;
	private HtmlCommandButton selectAllBtn;
	private HtmlSelectOneMenu selectRolls;

	/**
	 * agrega las opciones al rol
	 * 
	 * @param list
	 */
	public void agregarOpcionRol(List<VOOptionsPage> list) throws GWorkException {
		int i=0;
		String idRol = selectRolls.getValue().toString();
		if (idRol.equalsIgnoreCase("-1"))
			throw new GWorkException(Util.loadErrorMessageValue("MODULO.SEL"));

		optionsService = new OptionsServiceImpl();
		for (VOOptionsPage optionsPage : list) {
			if (optionsPage.isCheckStateBD()) {
				optionsService.crearOpcionRol(optionsPage.getOptCodigo(), new Long(idRol));
				i++;
			}
		}
		if (i==0) 
			throw new GWorkException(Util.loadErrorMessageValue("OPCIONES.SEL"));
		
	}

	/**
	 * elimina las opciones al rol
	 * 
	 * @param list
	 */
	public void eliminarOpcionRol(List<VOOptionRollsPage> list) throws GWorkException {
		int i=-1;
		if (list != null && list.size() > 0) {
			String idRols = selectRolls.getValue().toString();
			if (idRols.equalsIgnoreCase("-1"))
				throw new GWorkException(Util.loadErrorMessageValue("MODULO.SEL"));

			optionsService = new OptionsServiceImpl();
			for (VOOptionRollsPage optionsPage : list) {
				if (optionsPage.isCheckState()) {
					optionsService.eliminarOpcionRol(optionsPage.getIdOpcion(), new Long(idRols));
					i++;
				}				
			}
			if (i < 0) 
				throw new GWorkException(Util.loadErrorMessageValue("OPCIONES.DEL"));
		}
	}

	/**
	 * Metodo encargado de actualizar la lista de opciones
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public List<VOOptionsPage> actualizarLista(boolean check) {
		List<Options> listOptions = null;
		List<VOOptionsPage> listVo = new ArrayList<VOOptionsPage>();
		optionsService = new OptionsServiceImpl();
		listOptions = optionsService.getOpciones();
		for (Options options : listOptions) {
			VOOptionsPage optionsPage = new VOOptionsPage();
			optionsPage.setOptCodigo(options.getOptCodigo());
			optionsPage.setOptNombre(options.getOptNombre());
			optionsPage.setCheckStateBD(check);
			listVo.add(optionsPage);
		}
		return listVo;
	}

	/**
	 * Metodo encargado de actualizar la lista de opciones
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public List<VOOptionsPage> actualizarLista() {
		List<Options> listOptions = null;
		List<VOOptionsPage> listVo = new ArrayList<VOOptionsPage>();
		optionsService = new OptionsServiceImpl();
		listOptions = optionsService.getOpciones();
		for (Options options : listOptions) {
			VOOptionsPage optionsPage = new VOOptionsPage();
			optionsPage.setOptCodigo(options.getOptCodigo());
			optionsPage.setOptNombre(options.getOptNombre());
			listVo.add(optionsPage);
		}
		return listVo;
	}

	/**
	 * Metodo encargado de actualizar la lista de opciones
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public List<VOOptionRollsPage> actualizarListaOpciones(List<OptionsRolls> list) {
		if (list != null && list.size() > 0) {
			List<VOOptionRollsPage> listVo = new ArrayList<VOOptionRollsPage>();
			optionsService = new OptionsServiceImpl();
			Rolls rolls = null;
			for (OptionsRolls optionsRolls : list) {
				VOOptionRollsPage optionsPage = new VOOptionRollsPage();
				optionsPage.setIdOpcion(optionsRolls.getId().getOptCodigo());
				optionsPage.setIdRol(optionsRolls.getId().getRlsCodigo());
				optionsPage.setNombreOpcion(optionsRolls.getOptions().getOptNombre());
				optionsPage.setNombreRol(optionsRolls.getRolls().getRlsNombre());
				listVo.add(optionsPage);
			}
			return listVo;
		}
		return null;
	}

	public HtmlCommandButton getSelectAllBtn() {
		return selectAllBtn;
	}

	public void setSelectAllBtn(HtmlCommandButton selectAllBtn) {
		this.selectAllBtn = selectAllBtn;
	}

	public HtmlCommandButton getBtnCrear() {
		return btnCrear;
	}

	public void setBtnCrear(HtmlCommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}

	public HtmlSelectOneMenu getSelectRolls() {
		return selectRolls;
	}

	public void setSelectRolls(HtmlSelectOneMenu selectRolls) {
		this.selectRolls = selectRolls;
	}
}
