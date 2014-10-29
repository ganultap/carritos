package geniar.siscar.logic.security.serivice.impl;

import java.util.List;

import geniar.siscar.logic.security.serivice.RolService;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

/**
 * 
 * @author JAM, - Geniar Arq S.A
 * @version 1.0
 * @Descripción : Control de Caso de Uso para el manejo del CRUD de Roles
 * 
 */
public class RolServiceImpl implements RolService {

	/**
	 * Crea un rol
	 * 
	 * @param strNombre
	 * @param strMail
	 * @param strDescripcion
	 * @throws GWorkException
	 */
	public void crearRol(String strNombre, String strMail, String strDescripcion) throws GWorkException {
		String strTemp=strNombre.trim().toUpperCase();
		String strMailTemp=strMail.trim().toUpperCase();
		
		List<Rolls> listRols = new RollsDAO().findByRlsNombre(strTemp);

		if (listRols != null && listRols.size() > 0)
			throw new GWorkException(Util.loadErrorMessageValue("ROL.NOMBRE"));
		
		listRols = new RollsDAO().findByRlsMail(strMailTemp);
		
		if (listRols != null && listRols.size() > 0)
			throw new GWorkException(Util.loadErrorMessageValue("ROL.MAIL.EXISTE"));
		
		Rolls rolls = new Rolls();
		rolls.setRlsNombre(strTemp);
		rolls.setRlsDescripcion(strDescripcion);
		rolls.setRlsMail(strMailTemp);
		guardarRol(rolls);
	}

	/**
	 * Modifica un rol
	 * 
	 * @param idRol
	 * @param strNombre
	 * @param strMail
	 * @param strDescripcion
	 * @throws GWorkException
	 */
	public void modificarRol(Long idRol, String strNombre, String strMail, String strDescripcion) throws GWorkException {
		String strNombreTemp=strNombre.trim().toUpperCase();
		Rolls rolls = new RollsDAO().findById(idRol);
		List<Rolls> listRolls = null;
		if (rolls != null) {

			if (!rolls.getRlsNombre().equalsIgnoreCase(strNombreTemp))
				listRolls = new RollsDAO().findByRlsNombre(strNombreTemp);

			if (listRolls != null && listRolls.size() > 0)
				throw new GWorkException(Util.loadErrorMessageValue("ROL.NOMBRE"));

			rolls.setRlsDescripcion(strDescripcion);
			rolls.setRlsMail(strMail);
			rolls.setRlsNombre(strNombreTemp);
			modificarRol(rolls);

		}
	}

	/**
	 * Metodo encargado de ejecutar la modificacion de un rol
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void modificarRol(Rolls rolls) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();

			if (rolls != null) {
				new RollsDAO().update(rolls);
				EntityManagerHelper.commit();
				//Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de manejar la session y guardar el Rol
	 * 
	 * @param rol
	 * @throws GWorkException
	 */
	public void guardarRol(Rolls rol) throws GWorkException {
		try {
			//Util.validarSession();
			EntityManagerHelper.getEntityManager().getTransaction().begin();
			new RollsDAO().save(rol);
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			////Util.limpiarSession();
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			Util.validarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de ejecutar la eliminiacion o modificacion de un rol
	 * 
	 * @param id
	 * @throws GWorkException
	 */
	public void eliminarRol(Long id) throws GWorkException {
		try {
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			Rolls rolls = new RollsDAO().findById(id);

			if (rolls != null && rolls.getOptionsRollses().size() > 0) 
				throw new GWorkException(Util.loadErrorMessageValue("ROL.OPS.CONS"));

			if (rolls != null) {
				new RollsDAO().delete(rolls);
				EntityManagerHelper.commit();
				//Util.limpiarSession();
			}
		} catch (Exception e) {
			EntityManagerHelper.rollback();
			//Util.limpiarSession();
			throw new GWorkException(e.getMessage());
		}
	}

	/**
	 * Metodo encargado de consultar un rol por el id
	 * 
	 * @param param
	 * @return
	 */
	public Rolls consultarRolPorNombre(String nombre) throws GWorkException {
		List<Rolls> listRols = null;
		Rolls rolls = null;

		listRols = new RollsDAO().findByRlsNombre(nombre.trim().toUpperCase());

		if (listRols != null && listRols.size() > 0) {
			rolls = listRols.get(0);
		} else
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		
		return rolls;
	}
	
	/**
	 * Metodo encargado de consultar un rol por el id
	 * 
	 * @param param
	 * @return
	 */
	public Rolls consultarRolPorEmail(String email) throws GWorkException {
		List<Rolls> listRols = null;
		Rolls rolls = null;

		listRols = new RollsDAO().findByRlsMail(email.trim().toUpperCase());

		if (listRols != null && listRols.size() > 0) {
			rolls = listRols.get(0);
		} else
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		
		return rolls;
	}

	/**
	 * Metodo encargado de consultar un rol por el id
	 * 
	 * @param param
	 * @return
	 */
	public Rolls consultarRol(String param) {
		return new RollsDAO().findById(new Long(param));
	}

	/**
	 * Metodo consultar todos los roles
	 * 
	 * @throws GWorkException
	 */
	public List<Rolls> consultarRoles() {
		return new RollsDAO().findAll();
	}

}
