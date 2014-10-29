/**
 * 
 */
package geniar.siscar.logic.policies.services.impl;

import java.util.List;

import geniar.siscar.logic.consultas.SearchMonthlyTransac;
import geniar.siscar.logic.policies.services.MonthTransacTypeService;
import geniar.siscar.model.MonthTransacType;
import geniar.siscar.persistence.EntityManagerHelper;
import gwork.exception.GWorkException;

/**
 * @author Geniar
 *
 */
public class MonthTransacTypeServiceImpl implements MonthTransacTypeService {

	/* (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * MonthTransacTypeService#consultarTodosTiposTransacMensual()
	 */
	public List<MonthTransacType> consultarTodosTiposTransacMensual()
			throws GWorkException {
		validarSession();
		return new SearchMonthlyTransac().consultarMonthTransacType();
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * MonthTransacTypeService#consultarMonthTransacType(java.lang.Long)
	 */
	public MonthTransacType consultarMonthTransacType(Long idTipoTransaccion)
			throws GWorkException {
		validarSession();
		return new SearchMonthlyTransac().consultarMonthTransacType(
				idTipoTransaccion);
	}
	
	public void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	// public void limpiarSession() {
	// EntityManagerHelper.getEntityManager().clear();
	// EntityManagerHelper.closeEntityManager();
	//	}
}
