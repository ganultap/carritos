package geniar.siscar.logic.parameters.services;

import geniar.siscar.model.LegateesTypes;
import gwork.exception.GWorkException;

import java.util.List;
/**
 * 
 * @author Jam
 *
 */
public interface LegateesTypesService {

	public List<LegateesTypes> listLegateesTypes() throws GWorkException;

	/**
	 * 
	 * @param idLegateeType identificador de un LegateeType
	 * @return un objeto de tipo LegateesTypes
	 */
	public LegateesTypes consultarLegateeTypesById(Long idLegateeType) throws GWorkException;
}
