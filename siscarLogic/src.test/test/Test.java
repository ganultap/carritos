package test;

import geniar.siscar.model.ParValoresparametros;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import javax.persistence.Query;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
	try {
		final String queryString = 
			"select valores from ParValoresparametros valores " +
			"inner join valores.parParametros parametro " +
			"where parametro.nombre = :nombre"
			;
		Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
		query.setParameter("nombre", Util.loadParametersValue("dias.terminacion.asignacion"));
		ParValoresparametros par =  (ParValoresparametros) query.getSingleResult();
		
		System.out.println(par);
		
	} catch (GWorkException e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	}

	}

}
