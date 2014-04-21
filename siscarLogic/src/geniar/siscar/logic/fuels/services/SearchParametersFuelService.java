package geniar.siscar.logic.fuels.services;

import geniar.siscar.model.RevisionHour;
import gwork.exception.GWorkException;

import java.util.List;

public interface SearchParametersFuelService {

	public List<RevisionHour> listaHoraRevision() throws GWorkException;
}
