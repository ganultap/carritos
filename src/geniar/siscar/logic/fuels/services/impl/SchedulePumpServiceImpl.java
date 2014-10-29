package geniar.siscar.logic.fuels.services.impl;

import java.util.Date;
import java.util.List;

import geniar.siscar.logic.fuels.services.SchedulePumpService;
import geniar.siscar.model.DialyMovementPumps;
import geniar.siscar.persistence.DialyMovementPumpsDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IDialyMovementPumpsDAO;
import geniar.siscar.persistence.PumpsDAO;
import geniar.siscar.persistence.RevisionHourDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class SchedulePumpServiceImpl implements SchedulePumpService {

	public void modificarPlanillaSurtidor(Long idPlanillaSurtidor,
			Long surtidor, Long horaRevision, Date fechaRevision, String lectura)
			throws GWorkException {

		DialyMovementPumps dialyMovementPumps = null;
		IDialyMovementPumpsDAO dialyMovementPumpsDAO = new DialyMovementPumpsDAO();

		dialyMovementPumps = dialyMovementPumpsDAO.findById(idPlanillaSurtidor);

		if (dialyMovementPumps == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.TANQUE.EXISTEN"));

		List<DialyMovementPumps> registroDiario = new SearchParametersFuelServiceImp()
				.registroPlanillaSurtidor(surtidor, horaRevision, fechaRevision);

		if (registroDiario != null && registroDiario.size() > 0) {
			DialyMovementPumps dialyMovementPumpsConsulta = registroDiario
					.get(0);

			if (dialyMovementPumpsConsulta != null
					&& dialyMovementPumpsConsulta.getDmpCodigo().longValue() != idPlanillaSurtidor
							.longValue())
				throw new GWorkException(Util
						.loadErrorMessageValue("REGISTRO.TANQUE.EXISTE"));
		}

		dialyMovementPumps.setDmpFecha(fechaRevision);
		dialyMovementPumps.setDmpLectura(Util.convertirDecimalDouble(lectura));
		dialyMovementPumps.setPumps(new PumpsDAO().findById(surtidor));
		dialyMovementPumps.setRevisionHour(new RevisionHourDAO()
				.findById(horaRevision));

		EntityManagerHelper.beginTransaction();
		dialyMovementPumpsDAO.update(dialyMovementPumps);
		EntityManagerHelper.commit();

	}

	public void registrarPlanillaSurtidor(Long surtidor, Long horaRevision,
			Date fechaRevision, String lectura) throws GWorkException {

		DialyMovementPumps dialyMovementPumps = null;
		IDialyMovementPumpsDAO dialyMovementPumpsDAO = new DialyMovementPumpsDAO();

		List<DialyMovementPumps> registroDiario = new SearchParametersFuelServiceImp()
				.registroPlanillaSurtidor(surtidor, horaRevision, fechaRevision);

		if (registroDiario != null && registroDiario.size() > 0)
			dialyMovementPumps = registroDiario.get(0);

		if (dialyMovementPumps != null)
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.TANQUE.EXISTE"));

		dialyMovementPumps = new DialyMovementPumps();

		dialyMovementPumps.setDmpFecha(fechaRevision);
		dialyMovementPumps.setDmpLectura(Util.convertirDecimalDouble(lectura));
		dialyMovementPumps.setPumps(new PumpsDAO().findById(surtidor));
		dialyMovementPumps.setRevisionHour(new RevisionHourDAO()
				.findById(horaRevision));

		EntityManagerHelper.beginTransaction();
		dialyMovementPumpsDAO.save(dialyMovementPumps);
		EntityManagerHelper.commit();

	}

	public List<DialyMovementPumps> listaPlanillaSurtidor(Long idSurtidor,
			Date fechaIni, Date fechaFin) throws GWorkException {
		List<DialyMovementPumps> listaPlanilla = new SearchParametersFuelServiceImp()
				.listaPlanillaSurtidor(idSurtidor, fechaIni, fechaFin);

		if (listaPlanilla == null || listaPlanilla.size() == 0
				|| listaPlanilla.isEmpty())
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		return listaPlanilla;
	}

}
