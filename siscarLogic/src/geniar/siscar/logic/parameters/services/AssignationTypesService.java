package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.AssignationsTypes;
import gwork.exception.GWorkException;

import java.util.List;

public interface AssignationTypesService {

	 public List<AssignationsTypes> listAssignationTypes() throws GWorkException; 
}
