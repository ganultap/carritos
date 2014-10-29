package geniar.siscar.logic.vehicle.services.impl;

import java.util.List;

import geniar.siscar.logic.vehicle.services.ColorService;
import geniar.siscar.model.Colors;
import geniar.siscar.persistence.ColorsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class ColorServiceImpl implements ColorService {

	public void guardarColorCtrl(String nombre) throws GWorkException {
			String nombreTemp=nombre.trim().toUpperCase();
			buscarColorPorNombre(nombreTemp);
			guardarColor(nombreTemp);
	}

	public void guardarColor(String nombre) throws GWorkException {
		try {			
			Colors colors = new Colors();
			colors.setClNombre(nombre);
			validarSession();
			EntityManagerHelper.beginTransaction();
			new ColorsDAO().save(colors);
			EntityManagerHelper.commit();
			//Util.limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	public List<Colors> buscarColorPorNombre(String nombre) throws GWorkException {
		List<Colors> listColors = new ColorsDAO().findByClNombre(nombre);
		if (listColors != null && listColors.size() > 0)
			throw new GWorkException(Util.loadErrorMessageValue("COLOR.CONSULTA"));
		else
			return null;
	}
}
