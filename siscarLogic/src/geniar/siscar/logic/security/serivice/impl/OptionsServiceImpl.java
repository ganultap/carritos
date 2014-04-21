package geniar.siscar.logic.security.serivice.impl;

import geniar.siscar.logic.consultas.SearchSecurity;
import geniar.siscar.logic.security.serivice.OptionsService;
import geniar.siscar.model.Options;
import geniar.siscar.model.OptionsRolls;
import geniar.siscar.model.OptionsRollsId;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.OptionsDAO;
import geniar.siscar.persistence.OptionsRollsDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class OptionsServiceImpl implements OptionsService {

	public Options consultarOpcion(Long id) {
		return new OptionsDAO().findById(id);
	}

	public List<Options> getOpciones() {
		return new OptionsDAO().findAll();
	}

	public void crearOpcionRol(Long idOpcion, Long idRol) throws GWorkException {

		OptionsRolls optionsRol = SearchSecurity.consultarOpcionesDeRol(idRol, idOpcion);

		if (optionsRol != null)
			throw new GWorkException(Util.loadErrorMessageValue("OPCION.MODULO.CONST1") + " "
					+ optionsRol.getOptions().getOptNombre() + " " + Util.loadErrorMessageValue("OPCION.MODULO.CONST2"));

		agregarOpcionRol(idOpcion, idRol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.security.serivice.OptionsService#agregarOpcionRol(java.lang.Long,
	 *      java.lang.Long)
	 */
	public void agregarOpcionRol(Long idOpcion, Long idRol) throws GWorkException {
		try {

			Options options = new OptionsDAO().findById(idOpcion);
			Rolls rolls = new RollsDAO().findById(idRol);
			OptionsRollsId optionsRollsId = new OptionsRollsId();
			optionsRollsId.setOptCodigo(options.getOptCodigo());
			optionsRollsId.setRlsCodigo(rolls.getRlsCodigo());
			OptionsRolls optionsRolls = new OptionsRolls();
			optionsRolls.setId(optionsRollsId);
			if (options != null && rolls != null) {
				Util.validarSession();
				EntityManagerHelper.beginTransaction();
				new OptionsRollsDAO().save(optionsRolls);
				EntityManagerHelper.commit();
				//Util.limpiarSession();
			}
		} catch (Exception e) {
			if (EntityManagerHelper.getEntityManager().isOpen()) {
				EntityManagerHelper.rollback();
				//Util.limpiarSession();
			}
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de eliminar una opcion de un rol
	 * 
	 * @param idOpcion
	 * @throws GWorkException
	 */
	public void eliminarOpcionRol(Long idOpcion, Long idRol) throws GWorkException {
		try {
			OptionsRolls optionsRolls = SearchSecurity.consultarOpcionesDeRol(idRol, idOpcion);
			if (optionsRolls != null) {
				Util.validarSession();
				EntityManagerHelper.beginTransaction();
				new OptionsRollsDAO().delete(optionsRolls);
				EntityManagerHelper.commit();
				//Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}
}
