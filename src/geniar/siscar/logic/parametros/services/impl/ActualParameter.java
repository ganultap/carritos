package geniar.siscar.logic.parametros.services.impl;

import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

public class ActualParameter {
	
	public static final String IDPARAMETRO = "parParametros.idparametro";
	public static final String NOMBRE = "parParametros.nombre";

	/**Consulta un parametro con el ultimo valor, el cual tiene el valor final null*/
	@SuppressWarnings("unchecked")
	public List<ParValoresparametros> findByProperty(String propertyName,
			final Object value) throws GWorkException{
		EntityManagerHelper.log(
				"finding ParValoresparametros instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ParValoresparametros model where model."
					+ propertyName
					+ "= :propertyValue and model.valorfinal is null";
			Query query = EntityManagerHelper.getEntityManager().createQuery(
					queryString);
			query.setParameter("propertyValue", value);
			if(query.getResultList().isEmpty())throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	/**Consulta el parametro vigente por id*/
	public ParValoresparametros consultarUltimoParametro(Long idParametro) {

		try {
			return findByProperty(IDPARAMETRO, idParametro).get(0);
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public ParValoresparametros consultarUltimoParametroByName(String nombreParametro) {

		try {
			return findByProperty(NOMBRE, nombreParametro).get(0);
		} catch (GWorkException e) {
			e.printStackTrace();
		}
		return null;
	}
}
