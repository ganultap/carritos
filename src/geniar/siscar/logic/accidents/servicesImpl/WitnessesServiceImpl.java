package geniar.siscar.logic.accidents.servicesImpl;

import geniar.siscar.logic.accidents.services.WitnessesService;
import geniar.siscar.model.Witnesses;
import geniar.siscar.persistence.AccidentsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IWitnessesDAO;
import geniar.siscar.persistence.WitnessesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

public class WitnessesServiceImpl implements WitnessesService {

    public void registrarTestigo(Long idAccidents, String wtnIdentificacion, String wtnNombre, String wtnDireccion,
	    String wtnTelefono) throws GWorkException {

	Witnesses witnesses = new Witnesses();
	IWitnessesDAO witnessesDAO = new WitnessesDAO();
	List<Witnesses> listWitnesses = witnessesDAO.findByWtnIdentificacion(wtnIdentificacion);

	for (int i = 0; i < listWitnesses.size(); i++ ) {
	    if ((listWitnesses != null && listWitnesses.size() > 0 || !listWitnesses.isEmpty())
		    && (listWitnesses.get(i).getAccidents().getAccCodigo().longValue() == idAccidents.longValue()))
		throw new GWorkException(Util.loadErrorMessageValue("TESTIGO.EXISTE"));

	}

	/* verificar que el testigo no sea el mismo lesionado */
	String queryString = "select model from InjuredPersons model where model.pivId=:wtnIdentificacion";

	javax.persistence.Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
	query.setParameter("wtnIdentificacion", wtnIdentificacion);

	if (query.getResultList().size() > 0) {
	    throw new GWorkException(Util.loadErrorMessageValue("TESTIGO.EXISTE"));

	} else {
	    if (idAccidents != null)
		witnesses.setAccidents(new AccidentsDAO().findById(idAccidents));
	    witnesses.setWtnIdentificacion(wtnIdentificacion);
	    witnesses.setWtnNombre(wtnNombre);
	    witnesses.setWtnDireccion(wtnDireccion);
	    witnesses.setWtnTelefono(wtnTelefono);

	    EntityManagerHelper.beginTransaction();
	    witnessesDAO.save(witnesses);
	    EntityManagerHelper.commit();

	}

    }

    public void modificarTestigo(Long idTestigo, Long idAccidents, String wtnIdentificacion, String wtnNombre,
	    String wtnDireccion, String wtnTelefono) throws GWorkException {

	Witnesses witnesses = new Witnesses();
	IWitnessesDAO witnessesDAO = new WitnessesDAO();
	witnesses = witnessesDAO.findById(idTestigo);
	List<Witnesses> listWitnesses = witnessesDAO.findByWtnIdentificacion(wtnIdentificacion);

	if (witnesses == null)
	    throw new GWorkException(Util.loadErrorMessageValue("TESTIGO.EXISTEN"));

	for (int i = 0; i < listWitnesses.size(); i++ ) {
	    if ((listWitnesses != null && listWitnesses.size() > 0 || !listWitnesses.isEmpty())
		    && (listWitnesses.get(i).getAccidents().getAccCodigo().longValue() == idAccidents.longValue())
		    && (listWitnesses.get(i).getWtnCodigo().longValue() != idTestigo.longValue()))
		throw new GWorkException(Util.loadErrorMessageValue("TESTIGO.EXISTE"));

	}

	/* verificar que el testigo no sea el mismo lesionado */
	String queryString = "select model from InjuredPersons model where model.pivId=:wtnIdentificacion";

	javax.persistence.Query query = EntityManagerHelper.getEntityManager().createQuery(queryString);
	query.setParameter("wtnIdentificacion", wtnIdentificacion);

	if (query.getResultList().size() > 0) {
	    throw new GWorkException(Util.loadErrorMessageValue("TESTIGO.EXISTE"));

	} else {
	    if (idAccidents != null)
		witnesses.setAccidents(new AccidentsDAO().findById(idAccidents));
	    witnesses.setWtnIdentificacion(wtnIdentificacion);
	    witnesses.setWtnNombre(wtnNombre);
	    witnesses.setWtnDireccion(wtnDireccion);
	    witnesses.setWtnTelefono(wtnTelefono);

	    EntityManagerHelper.beginTransaction();
	    witnessesDAO.save(witnesses);
	    EntityManagerHelper.commit();

	}

    }

    public void eliminarTestigo(Long idTestigo) throws GWorkException {

	Witnesses witnesses = new Witnesses();
	IWitnessesDAO witnessesDAO = new WitnessesDAO();
	witnesses = witnessesDAO.findById(idTestigo);

	if (witnesses == null)
	    throw new GWorkException(Util.loadErrorMessageValue("TESTIGO.EXISTEN"));

	EntityManagerHelper.beginTransaction();
	witnessesDAO.delete(witnesses);
	EntityManagerHelper.commit();

    }

    public List<Witnesses> listarTestigos(Long idAccidente) throws GWorkException {

	List<Witnesses> listWitnesses = new SearchParametersAccidents().listarTestigos(idAccidente);

	if (listWitnesses == null || listWitnesses.size() == 0 || listWitnesses.isEmpty())
	    throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

	return listWitnesses;
    }
}
