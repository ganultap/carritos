package geniar.siscar.logic.vehicle.services;

import java.util.List;

import geniar.siscar.model.Colors;
import gwork.exception.GWorkException;

public interface ColorService {

	public void guardarColorCtrl(String nombre) throws GWorkException;

	public List<Colors> buscarColorPorNombre(String nombre) throws GWorkException;

}
