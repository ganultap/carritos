package geniar.siscar.view.security;

import geniar.siscar.logic.consultas.SearchSecurity;
import geniar.siscar.logic.security.serivice.MenuBarService;
import geniar.siscar.logic.security.serivice.impl.MenuBarServiceImpl;
import geniar.siscar.logic.security.serivice.impl.UserServiceImpl;
import geniar.siscar.model.Modules;
import geniar.siscar.model.OptionsRolls;
import geniar.siscar.model.Users;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.List;
import com.icesoft.faces.component.menubar.MenuItem;

public class MenuBarList {

	private List<MenuItem> menuModel;
	private MenuBarService menuBarService;

	public List<MenuItem> getMenuModel() {
		return menuModel;
	}

	public List<MenuItem> cargarListaOpciones(String strLogin)
			throws GWorkException {

		menuBarService = new MenuBarServiceImpl();
		List<Modules> listModulo = menuBarService
				.consultarOpcionesUsuario(strLogin.trim().toUpperCase());
		menuModel = new ArrayList<MenuItem>();
		Users user = null;
		user = new UserServiceImpl().consultarUsuarioPorLogin(strLogin);
		MenuItem topLevelMenu = null;

		if (listModulo != null && listModulo.size() > 0) {
			for (Modules modules : listModulo) {
				if (modules.getOptionses() != null) {
					topLevelMenu = new MenuItem();
					topLevelMenu.setValue(modules.getMdlNombre());

					List<OptionsRolls> listaOpcionesRoll = SearchSecurity
							.consultarOpcionesDeRolModulo(user.getRolls()
									.getRlsCodigo(), modules.getMdlCodigo());
					for (OptionsRolls options : listaOpcionesRoll) {
						MenuItem topLevelOption = new MenuItem();
						if (options.getOptions().getOptNombre().toString()
								.equals("Reportes"))
							topLevelOption
									.setOnclick("window.open('"
											+ options.getOptions().getOptLink()
													.toString()
											+ "','popup');window.location.reload(false);");
						else
							topLevelOption.setLink(options.getOptions()
									.getOptLink());

						topLevelOption.setValue(options.getOptions()
								.getOptNombre());
						topLevelMenu.getChildren().add(topLevelOption);
					}
				}
				menuModel.add(topLevelMenu);
			}
			setMenuModel(menuModel);
			return menuModel;
		}
		return menuModel;
	}

	// public MenuBarList() throws GWorkException {
	// LoginPage loginPage = (LoginPage) FacesUtils.getManagedBean("loginPage");
	// if (loginPage != null && loginPage.getLogin() != null &&
	// loginPage.getLogin().trim().length() != 0)
	// cargarListaOpciones(loginPage.getLogin());
	// }

	public MenuBarService getMenuBarService() {
		return menuBarService;
	}

	public void setMenuBarService(MenuBarService menuBarService) {
		this.menuBarService = menuBarService;
	}

	public void setMenuModel(List<MenuItem> menuModel) {
		this.menuModel = menuModel;
	}
}
