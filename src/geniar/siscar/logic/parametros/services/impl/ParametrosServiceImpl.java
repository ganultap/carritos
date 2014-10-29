package geniar.siscar.logic.parametros.services.impl;

import geniar.siscar.logic.parameters.services.ParametrosService;
import geniar.siscar.model.ParParametros;
import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IParParametrosDAO;
import geniar.siscar.persistence.IParValoresparametrosDAO;
import geniar.siscar.persistence.ParParametrosDAO;
import geniar.siscar.persistence.ParValoresparametrosDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author - GeniarArq S.A
 * @version 1.0 Descripción : Control de caso de uso para el manejo de
 *          Parametros
 * 
 */
public class ParametrosServiceImpl implements ParametrosService {

	public static final String IDPARAMETRO = "parParametros.idparametro";

	/**
	 * Crea un Parametro
	 * 
	 * @param strNombre
	 * @param strValor
	 * @param fechaIni
	 * @param fechaFin
	 */
	public void crearParametros(String strNombre, String descripcion,
			String strValorIni) throws GWorkException {

		ParParametrosDAO parametrosDAO = new ParParametrosDAO();
		ParParametros parametros = new ParParametros();
		List<ParParametros> listParametros = parametrosDAO
				.findByNombre(strNombre);

		// if (listParametros.isEmpty() != true)
		// throw new
		// GWorkException(Util.loadErrorMessageValue("name.equals.error"));

		EntityManagerHelper.beginTransaction();
		parametros.setNombre(strNombre);
		parametros.setDescripcion(descripcion);
		parametrosDAO.save(parametros);

		crearValoresParametros(parametros, strValorIni);
	}

	/**
	 * Actualiza un valor de un parametro
	 * 
	 * @param IdParametro
	 * @param parametros
	 * @param strValor
	 * @param fechaIni
	 * @param fechaFin
	 */

	public void actualizarValoresParametros(Long IdParametro,
			ParParametros parametros, String strValorIni, String strValorFin,
			Date fechaIni, Date fechaFin) throws GWorkException {

		IParValoresparametrosDAO valoresparametrosDAO = new ParValoresparametrosDAO();
		ParValoresparametros valoresparametros = valoresparametrosDAO
				.findById(IdParametro);

		try {

			if (valoresparametros != null) {
				valoresparametros.setParParametros(parametros);
				valoresparametros.setValorinicial(new Float(strValorIni));
				if (strValorFin != null)
					valoresparametros.setValorfinal(new Float(strValorFin));
				EntityManagerHelper.beginTransaction();
				valoresparametros.setFechainicio(fechaIni);
				valoresparametros.setFechafin(fechaFin);
				valoresparametrosDAO.update(valoresparametros);
				EntityManagerHelper.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * crea un valor para un parametro
	 * 
	 * @param parametros
	 * @param strValor
	 * @param fechaIni
	 * @param fechaFin
	 */
	public void crearValoresParametros(ParParametros parametros,
			String strValorIni) throws GWorkException {

		ParValoresparametrosDAO valoresparametrosDAO = new ParValoresparametrosDAO();
		ParValoresparametros valoresparametros = new ParValoresparametros();
		valoresparametros.setParParametros(parametros);

		valoresparametros.setFechainicio(new Date());
		valoresparametros.setValorinicial(new Float(strValorIni));
		valoresparametrosDAO.save(valoresparametros);
		EntityManagerHelper.commit();
		// Util.limpiarSession();

	}

	/**
	 * Modifica Parametro
	 * 
	 * @param idParametro
	 * @param strNombre
	 * @param strValor
	 * @param fechaIni
	 * @param fechaFin
	 */
	public void actualizarParametros(Long idParametro, String strNombre,
			String descripcion, String strValorFin) throws GWorkException {

		ParParametros parametros = new ParParametros();
		IParParametrosDAO parametrosDAO = new ParParametrosDAO();
		parametros = parametrosDAO.findById(idParametro);

		ParValoresparametros parValoresparametros = consultarUltimoParametro(idParametro);
		IParValoresparametrosDAO parValoresparametrosDAO = new ParValoresparametrosDAO();

		if (strNombre.equalsIgnoreCase(parametros.getNombre())) {
			/** Actulizo el nombre y la descripcion del parametro */
			parametros.setDescripcion(descripcion);
		} else {

			if (!parametrosDAO.findByNombre(strNombre).isEmpty())
				throw new GWorkException(Util
						.loadErrorMessageValue("PARAMETROEXISTE"));

			parametros.setNombre(strNombre.toUpperCase());
			parametros.setDescripcion(descripcion);

		}

		EntityManagerHelper.beginTransaction();
		parametrosDAO.update(parametros);

		parValoresparametros.setValorfinal(parValoresparametros
				.getValorinicial());
		parValoresparametros.setFechafin(new Date());
		parValoresparametrosDAO.update(parValoresparametros);

		crearValoresParametros(parValoresparametros.getParParametros(),
				strValorFin);

	}

	/**
	 * Busca un Parametro
	 * 
	 * @param id
	 * @return
	 */
	public ParParametros buscarParametroPorId(Long id) {
		return new ParParametrosDAO().findById(id);
	}

	public ParParametros buscarParParametrosPorNombre(String nombre) {
		try {
			IParParametrosDAO parametrosDAO = new ParParametrosDAO();
			List<ParParametros> listParParametros = parametrosDAO
					.findByNombre(nombre);
			return listParParametros.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Busca un Valor de un Parametro por el id
	 * 
	 * @param id
	 * @return
	 */
	public ParValoresparametros buscarValorParametroPorId(Long id) {
		return new ParValoresparametrosDAO().findById(id);
	}

	/**
	 * @return
	 */
	public List<ParParametros> getParParametros() throws GWorkException {

		EntityManagerHelper.getEntityManager().clear();
		List<ParParametros> listparametros = new ParParametrosDAO().findAll();

		if (listparametros == null || listparametros.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return new ParParametrosDAO().findAll();
	}

	public ParValoresparametros buscarValorParametroPorNombre(Long id) {
		return null;
	}

	public ParValoresparametros consultarUltimoParametro(Long idParametro)
			throws GWorkException {
		ActualParameter actualParameter = new ActualParameter();
		if (actualParameter.consultarUltimoParametro(idParametro) == null)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return actualParameter.consultarUltimoParametro(idParametro);
	}
}