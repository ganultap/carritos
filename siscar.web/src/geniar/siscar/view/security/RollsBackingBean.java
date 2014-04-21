package geniar.siscar.view.security;

import java.util.ArrayList;
import java.util.List;

import com.icesoft.faces.component.ext.HtmlCommandButton;

import geniar.siscar.logic.security.serivice.RolService;
import geniar.siscar.logic.security.serivice.impl.RolServiceImpl;
import geniar.siscar.model.Rolls;
import geniar.siscar.util.Util;
import geniar.siscar.view.BaseBean;
import gwork.exception.GWorkException;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : BackingBean para el manejo de los eventos del page de roles
 * 
 */
public class RollsBackingBean extends BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RolService rolService;
	private HtmlCommandButton selectAllBtn;
	private HtmlCommandButton btnCrear;

	public RolService getRolService() {
		return rolService;
	}

	public void setRolService(RolService rolService) {
		this.rolService = rolService;
	}

	/**
	 * Metodo encargado de guardar un Rol
	 * 
	 * @param rlsNombre
	 * @param rlsDescripcion
	 * @param rlsMail
	 * @throws GWorkException
	 */
	public void guardarRol(String rlsNombre, String rlsDescripcion, String rlsMail) throws GWorkException {
		validarCampos(rlsNombre, rlsDescripcion, rlsMail);
		rolService = new RolServiceImpl();
		rolService.crearRol(rlsNombre.trim().toUpperCase(), rlsMail.trim().toUpperCase(), rlsDescripcion);
	}
	
	/**
	 * Metodo encargado de consultar un rol por el id
	 * @param param
	 * @return
	 */
	public Rolls consultarRol(String param){
		rolService = new RolServiceImpl();
		return rolService.consultarRol(param);
	}
	
	/**
	 * Metodo encargado de consultar un rol por el id
	 * @param param
	 * @return
	 */
	public Rolls consultarRolPorNombre(String nombre)throws GWorkException{
		rolService = new RolServiceImpl();
		return rolService.consultarRolPorNombre(nombre);
	}
	
	
	/**
	 * Metodo encargado de modificar un Rol
	 * 
	 * @param rlsNombre
	 * @param rlsDescripcion
	 * @param rlsMail
	 * @throws GWorkException
	 */
	public void modificarRol(Long rlsCodigo,String rlsNombre, String rlsDescripcion, String rlsMail) throws GWorkException {
		validarCampos(rlsNombre.trim().toUpperCase(), rlsDescripcion, rlsMail.trim().toUpperCase());
		rolService = new RolServiceImpl();
		rolService.modificarRol(new Long(rlsCodigo),rlsNombre, rlsMail, rlsDescripcion);
	}

	/**
	 * Metodo encargado de validar los campos del formulario
	 * @param rlsNombre
	 * @param rlsDescripcion
	 * @param rlsMail
	 * @throws GWorkException
	 */
	public void validarCampos(String rlsNombre, String rlsDescripcion, String rlsMail) throws GWorkException {

		boolean isValid = true;

		if (rlsNombre != null && rlsNombre.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ROL.VACIO"));

		if (rlsNombre != null && rlsNombre.trim().length() != 0)
			isValid = Util.validarCadenaCaracteres(rlsNombre);

		if (!isValid)
			throw new GWorkException(Util.loadErrorMessageValue("ROL.CARACTER"));

		if (rlsNombre != null && rlsNombre.trim().length() != 0 && rlsNombre.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("ROL.MINIMO"));

		if (rlsMail != null && rlsMail.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("ROL.EMAIL.VACIO"));

		if (!Util.validarEmail(rlsMail))
			throw new GWorkException(Util.loadErrorMessageValue("EMAIL.NOVALIDO"));

		if (rlsMail != null && rlsMail.trim().length() < 2)
			throw new GWorkException(Util.loadErrorMessageValue("EMAIL.MINIMO"));

		if (rlsDescripcion != null && rlsDescripcion.trim().length() != 0)

			if (rlsDescripcion != null && rlsDescripcion.trim().length() != 0)
				Util.validarLimite(rlsDescripcion, 200, 3, "DESCRIPCION.MINMAX");
	}

	/**
	 * Metodo encargado de actualizar la lista de roles
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public List<VORollsPage> actualizarLista(boolean check) {
		List<Rolls> listRols = null;
		List<VORollsPage> listVo = new ArrayList<VORollsPage>();
		rolService = new RolServiceImpl();
		listRols = rolService.consultarRoles();
		for (Rolls rolls : listRols) {
			VORollsPage rollsPage = new VORollsPage();
			rollsPage.setCheckState(check);
			rollsPage.setIdRol(rolls.getRlsCodigo().toString());
			rollsPage.setDescripcion(rolls.getRlsDescripcion());
			rollsPage.setNombre(rolls.getRlsNombre());
			rollsPage.setEmail(rolls.getRlsMail());
			listVo.add(rollsPage);
		}
		return listVo;
	}

	/**
	 * Metodo encargado de eliminar los componentes seleccionados
	 * 
	 * @param list
	 * @param check
	 * @return
	 */
	public void eliminarRoles(List<VORollsPage> list) throws GWorkException {
		rolService = new RolServiceImpl();
		if (list != null && list.size() > 0) {
			for (VORollsPage rollsVo : list) {
				if (rollsVo.getCheckState())
					rolService.eliminarRol(new Long(rollsVo.getIdRol()));
			}
		}
	}

	/**
	 * Metodo encargado de validar si alguno de los checks de la lista estan
	 * seleccionados
	 * 
	 * @throws GWorkException
	 */
	public boolean validarChecks(List<VORollsPage> list) throws GWorkException {
		rolService = new RolServiceImpl();
		if (list != null && list.size() > 0) {
			for (VORollsPage rollsVo : list) {
				if (rollsVo.getCheckState())
					return true;
			}
		}
		return false;
	}
	
	public void activarBtnCrear(boolean bandera){
		btnCrear.setDisabled(bandera);
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
}
