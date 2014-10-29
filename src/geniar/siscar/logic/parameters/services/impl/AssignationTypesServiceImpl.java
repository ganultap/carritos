package geniar.siscar.logic.parameters.services.impl;

import java.util.List;

import geniar.siscar.logic.parameters.services.AssignationTypesService;
import geniar.siscar.model.AssignationsTypes;
import geniar.siscar.persistence.AssignationsTypesDAO;
import geniar.siscar.persistence.IAssignationsTypesDAO;
import gwork.exception.GWorkException;

public class AssignationTypesServiceImpl implements AssignationTypesService {

	public List<AssignationsTypes> listAssignationTypes() throws GWorkException{
		IAssignationsTypesDAO assignationsTypesDAO  = new AssignationsTypesDAO();
		List<AssignationsTypes> listAssignationTypes = assignationsTypesDAO.findAll();
		if(listAssignationTypes==null)
			//TODO Hacer Excepction
			throw new GWorkException("");
		return listAssignationTypes;
	}

}
