package geniar.siscar.logic.fuels.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import geniar.siscar.logic.fuels.services.ScheduleFuelService;
import geniar.siscar.model.DailyMovementTank;
import geniar.siscar.model.FuelTanks;
import geniar.siscar.persistence.DailyMovementTankDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FuelTanksDAO;
import geniar.siscar.persistence.IDailyMovementTankDAO;
import geniar.siscar.persistence.IFuelTanksDAO;
import geniar.siscar.persistence.RevisionHourDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class ScheduleFuelServiceImpl implements ScheduleFuelService {

	public void modificarMoviminetoDiarioTanque(Long registroPlanillaTanque,
			Long tanque, Long horaRevision, String entrada, String Lectura,
			Date fecharRevision) throws GWorkException {

		DailyMovementTank dailyMovementTank = null;
		FuelTanks fuelTanks = null;
		IDailyMovementTankDAO dailyMovementTankDAO = new DailyMovementTankDAO();
		IFuelTanksDAO fuelTanksDAO = new FuelTanksDAO();
		fuelTanks = new FuelTanksDAO().findById(tanque);
		dailyMovementTank = dailyMovementTankDAO
				.findById(registroPlanillaTanque);

		if (dailyMovementTank == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.TANQUE.EXISTEN"));

		List<DailyMovementTank> registroDiario = new SearchParametersFuelServiceImp()
				.registroPlanillaTanque(tanque, horaRevision, fecharRevision);

		if (registroDiario != null && registroDiario.size() > 0) {
			DailyMovementTank dailyMovementTankConsulta = registroDiario.get(0);

			if (dailyMovementTankConsulta != null
					&& dailyMovementTankConsulta.getDamId().longValue() != registroPlanillaTanque
							.longValue())
				throw new GWorkException(Util
						.loadErrorMessageValue("REGISTRO.TANQUE.EXISTE"));
		}
		if (fuelTanks == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUENOEXISTE"));

		Float lectura = Util.convertirDecimal(Lectura);

		if (lectura.longValue() > fuelTanks.getFtaCapacidadMaxima().longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("LECTURA.MAX.CAPACIDAD"));

		if (validarFecha(fecharRevision) == false)
			throw new GWorkException(Util.loadErrorMessageValue("FECHA.LIMITE"));

		fuelTanks.setFtaGalonesActuales(lectura);
		dailyMovementTank.setFuelTanks(fuelTanks);
		dailyMovementTank.setRevisionHour(new RevisionHourDAO()
				.findById(horaRevision));
		dailyMovementTank.setDamLectura(lectura);
		dailyMovementTank.setDamEntrada(entrada);
		dailyMovementTank.setDamFecha(fecharRevision);

		EntityManagerHelper.beginTransaction();
		fuelTanksDAO.update(fuelTanks);
		dailyMovementTankDAO.update(dailyMovementTank);
		EntityManagerHelper.commit();

	}

	public void registrarMoviminetoDiarioTanque(Long tanque, Long horaRevision,
			String entrada, String Lectura, Date fecharRevision)
			throws GWorkException {

		DailyMovementTank dailyMovementTank = null;
		FuelTanks fuelTanks = null;
		IFuelTanksDAO fuelTanksDAO = new FuelTanksDAO();
		IDailyMovementTankDAO dailyMovementTankDAO = new DailyMovementTankDAO();
		fuelTanks = fuelTanksDAO.findById(tanque);

		List<DailyMovementTank> registroDiario = new SearchParametersFuelServiceImp()
				.registroPlanillaTanque(tanque, horaRevision, fecharRevision);

		if (registroDiario != null && registroDiario.size() > 0)
			dailyMovementTank = registroDiario.get(0);

		if (dailyMovementTank != null)
			throw new GWorkException(Util
					.loadErrorMessageValue("REGISTRO.TANQUE.EXISTE"));

		if (fuelTanks == null)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TANQUENOEXISTE"));

		Float lectura = Util.convertirDecimal(Lectura);

		if (lectura.longValue() > fuelTanks.getFtaCapacidadMaxima().longValue())
			throw new GWorkException(Util
					.loadErrorMessageValue("LECTURA.MAX.CAPACIDAD"));

//		if (validarFecha(fecharRevision) == false)
//			throw new GWorkException(Util.loadErrorMessageValue("FECHA.LIMITE"));

		fuelTanks.setFtaGalonesActuales(lectura);
		dailyMovementTank = new DailyMovementTank();
		dailyMovementTank.setFuelTanks(fuelTanks);
		dailyMovementTank.setRevisionHour(new RevisionHourDAO()
				.findById(horaRevision));
		dailyMovementTank.setDamLectura(lectura);
		dailyMovementTank.setDamEntrada(entrada);
		dailyMovementTank.setDamFecha(fecharRevision);

		EntityManagerHelper.beginTransaction();
		fuelTanksDAO.update(fuelTanks);
		dailyMovementTankDAO.save(dailyMovementTank);
		EntityManagerHelper.commit();
	}

	public String entradaTanques(Long idTanque, Date fechaCarga)
			throws GWorkException {
		String entradaTanque = new SearchParametersFuelServiceImp()
				.entradasTanque(idTanque, fechaCarga);

		return entradaTanque;
	}

	public List<DailyMovementTank> listaPlanillaTanque(Long idTanque,
			Date fechaIni, Date fechaFin) throws GWorkException {

		List<DailyMovementTank> listaPlanillaTanque = new SearchParametersFuelServiceImp()
				.listaPlanillaTanque(idTanque, fechaIni, fechaFin);

		if (listaPlanillaTanque == null || listaPlanillaTanque.isEmpty()
				|| listaPlanillaTanque.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaPlanillaTanque;
	}

	public boolean validarFecha(Date fechaRevision) {
		boolean fechaValida = true;
		Calendar fechaFormato = Calendar.getInstance();
		fechaFormato.setTime(fechaRevision);

		Integer anho = fechaFormato.get(Calendar.YEAR);
		Integer mes = fechaFormato.get(Calendar.MONTH);
		Integer dia = fechaFormato.get(Calendar.DAY_OF_MONTH);

		Calendar fechaActualFormato = Calendar.getInstance();
		fechaActualFormato.setTime(new Date());
		dia = fechaActualFormato.get(Calendar.DAY_OF_MONTH) - dia;

		if (anho.intValue() < fechaActualFormato.get(Calendar.YEAR))
			fechaValida = false;

		else if (mes.intValue() < fechaActualFormato.get(Calendar.MONTH))
			fechaValida = false;

		else if (anho.intValue() == fechaActualFormato.get(Calendar.YEAR)
				&& mes.intValue() == fechaActualFormato.get(Calendar.MONTH)
				&& dia > 2)
			fechaValida = false;

		return fechaValida;
	}
}
