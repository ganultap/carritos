package geniar.siscar.view.parameters;

import geniar.siscar.logic.consultas.SearchParameters;
import geniar.siscar.logic.security.serivice.RolService;
import geniar.siscar.model.Rolls;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * 
 * @author JAM
 * 
 */
public class SelectItemRollsPage {

	private SelectItem[] listRolls;
	private SelectItem[] listEstados;
	private RolService rolService;
	private List<Rolls> listUtilRolls;
	private List<String> listEstado;

	public List<Rolls> getListUtilRolls() throws GWorkException {
		try {
			listUtilRolls = rolService.consultarRoles();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listUtilRolls;
	}

	public void setListRolls(SelectItem[] listRolls) {
		this.listRolls = listRolls;
	}

	public void setListUtilRolls(List<Rolls> listUtilRolls) {
		this.listUtilRolls = listUtilRolls;
	}

	public SelectItem[] getListRolls() {
		try {
			listRolls = null;
			listUtilRolls = SearchParameters.getAllRolesOrder();
			listRolls = new SelectItem[listUtilRolls.size() + 1];
			listRolls[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (Rolls rolls : listUtilRolls) {
				listRolls[i] = new SelectItem(rolls.getRlsCodigo(), rolls.getRlsNombre());
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listRolls;
	}

	public RolService getRolService() {
		return rolService;
	}

	public void setRolService(RolService rolService) {
		this.rolService = rolService;
	}

	public List<String> getListEstado() {
		return listEstado;
	}

	public void setListEstado(List<String> listEstado) {
		this.listEstado = listEstado;
	}

	public SelectItem[] getListEstados() {
		try {
			listEstados = null;
			listEstado = SearchParameters.getAllEstados();
			listEstados = new SelectItem[listEstado.size() + 1];
			listEstados[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (String var : listEstado) {
				listEstados[i] = new SelectItem(i, var);
				i++;
			}
		} catch (Exception e) {
		}
		return listEstados;
	}

	public void setListEstados(SelectItem[] listEstados) {
		this.listEstados = listEstados;
	}
}
