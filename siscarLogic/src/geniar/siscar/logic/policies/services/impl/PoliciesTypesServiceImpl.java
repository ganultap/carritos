/**
 * 
 */
package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.consultas.SearchPoliciesTypes;
import geniar.siscar.logic.policies.services.PoliciesTypeService;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.persistence.PoliciesTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author geniar
 * 
 */
public class PoliciesTypesServiceImpl implements PoliciesTypeService {

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * PoliciesTypeService#consultarPoliciesTypesById(java.lang.Long)
	 */
	public PoliciesTypes consultarPoliciesTypesById(Long idPolicyType)
	throws GWorkException {
		PoliciesTypes policiesTypes = new PoliciesTypesDAO()
		.findById(idPolicyType);

		if (policiesTypes == null) {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

		return policiesTypes;
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * PoliciesTypeService#listPoliciesTypes()
	 */
	public List<PoliciesTypes> listPoliciesTypes() throws GWorkException {
		return new SearchPoliciesTypes().consultarPoliciesTypes();
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * PoliciesTypeService#listPoliciesTypesParam()
	 */
	public List<PoliciesTypes> listPoliciesTypesParam() throws GWorkException {
		return new SearchPoliciesTypes().consultarPoliciesTypesParam();
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * PoliciesTypeService#listPoliciesTypesTransac()
	 */
	public List<PoliciesTypes> listPoliciesTypesTransac() throws GWorkException {
		return new SearchPoliciesTypes().consultarPoliciesTypesTransac();
	}

	/*
	 * (non-Javadoc)
	 * @see geniar.siscar.logic.policies.services.
	 * PoliciesTypeService#consultarTipoPolizaPorNombre(java.lang.String)
	 */
	public PoliciesTypes consultarTipoPolizaPorNombre(String nombre)
			throws GWorkException {
		return new SearchPoliciesTypes().consultarPoliciesTypes(nombre);
	}
}
