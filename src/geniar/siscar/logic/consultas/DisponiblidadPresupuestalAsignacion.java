package geniar.siscar.logic.consultas;

import java.util.ArrayList;
import java.util.List;
import geniar.siscar.model.Tariffs;
import geniar.siscar.model.Vehicles;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.persistence.AccountingParametersDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

public class DisponiblidadPresupuestalAsignacion {

	public static final Long OF = 1L;
	public static final Long OFS = 2L;
	public static final Long PROGRAMA = 3L;
	public static final Long PROYECTO = 4L;
	public static final Long CONVENIO = 5L;
	public static final Long PERSONAL = 6L;

	public static List<Double> valorTarifaAsignacion(
			VehiclesAssignation assignation) throws GWorkException {

		Double valorTarifaAsignacion = null;
		Double valorDepreciacion = null;
		Double valorAutoseguro = null;
		Double valorMantenimiento = null;

		Vehicles vehicles = assignation.getVehicles();

		ConsultTariff consultTariff = new ConsultTariff();

		if (vehicles != null
				&& (consultTariff == null || consultTariff
						.consultarTarifaAsignacionDepreciacion(
								vehicles.getLines().getLnsId().longValue(),
								vehicles.getFuelsTypes().getFutCodigo().longValue(),
								vehicles.getTractionsTypes().getTctCodigo().longValue()).size() == 0)
				|| consultTariff
						.consultarTarifaAsignacionDepreciacion(
								vehicles.getLines().getLnsId().longValue(),
								vehicles.getFuelsTypes().getFutCodigo()
										.longValue(),
								vehicles.getTractionsTypes().getTctCodigo()
										.longValue()).isEmpty())
			throw new GWorkException(Util
					.loadErrorMessageValue("TARIFAASIGNACION")
					+ " "
					+ Util.loadErrorMessageValue("MARCA.TARIF.ERROR")
					+ vehicles.getLines().getBrands().getBrnNombre()
					+ " "
					+ Util.loadErrorMessageValue("LINEA.TARIF.ERROR")
					+ vehicles.getLines().getLnsNombre()
					+ " "
					+ Util.loadErrorMessageValue("COMBUS.TARIF.ERROR")
					+ vehicles.getFuelsTypes().getFutNombre()
					+ " "
					+ Util.loadErrorMessageValue("TRACCION.TARIF.ERROR")
					+ vehicles.getTractionsTypes().getTctNombre());

		/** Valor tarifa asignacion depreciacion */
		Tariffs tariffsDepreciacion = new ConsultTariff()
				.consultarTarifaAsignacionDepreciacion(
						vehicles.getLines().getLnsId().longValue(),
						vehicles.getFuelsTypes().getFutCodigo().longValue(),
						vehicles.getTractionsTypes().getTctCodigo().longValue())
				.get(0);
		valorDepreciacion = tariffsDepreciacion.getTrfValor().doubleValue();

		/** Valor tarifa asignacion autoseguro */
		Tariffs tariffsAutoseguro = new ConsultTariff()
				.consultarTarifaAsignacionAutoseguro(
						vehicles.getLines().getLnsId().longValue(),
						vehicles.getFuelsTypes().getFutCodigo().longValue(),
						vehicles.getTractionsTypes().getTctCodigo().longValue())
				.get(0);
		valorAutoseguro = tariffsAutoseguro.getTrfValor().doubleValue();

		/** Valor tarifa asignacion mantenimiento */
		Tariffs tariffsMantenimiento = new ConsultTariff()
				.consultarTarifaAsignacionMantenimiento(
						vehicles.getLines().getLnsId().longValue(),
						vehicles.getFuelsTypes().getFutCodigo().longValue(),
						vehicles.getTractionsTypes().getTctCodigo().longValue())
				.get(0);
		valorMantenimiento = tariffsMantenimiento.getTrfValor().doubleValue();

		valorAutoseguro = Util.redondear(valorAutoseguro, 2);
		valorDepreciacion = Util.redondear(valorDepreciacion, 2);
		valorMantenimiento = Util.redondear(valorMantenimiento, 2);

		valorTarifaAsignacion = valorAutoseguro + valorDepreciacion
				+ valorMantenimiento;
		valorTarifaAsignacion = Util.redondear(valorTarifaAsignacion, 2);

		List<Double> listaTarfiaAsignacion = new ArrayList<Double>();
		listaTarfiaAsignacion.add(0, valorAutoseguro);
		listaTarfiaAsignacion.add(1, valorDepreciacion);
		listaTarfiaAsignacion.add(2, valorMantenimiento);
		listaTarfiaAsignacion.add(3, valorTarifaAsignacion);

		System.out.println("Valor autoseguro: " + valorAutoseguro);
		System.out.println("Valor depreciacion: " + valorDepreciacion);
		System.out.println("Valor valor mantenimiento: " + valorMantenimiento);
		System.out.println("Valor asignacion: " + valorTarifaAsignacion);

		return listaTarfiaAsignacion;

	}

	public String consultarParametroAsignacion(Long tipoAsignacion) {

		String cuenta = null;

		if (tipoAsignacion == OF)
			cuenta = new AccountingParametersDAO().findById(1L).getAccount()
					.getAccNumeroCuenta();

		if (tipoAsignacion == OFS)
			cuenta = new AccountingParametersDAO().findById(2L).getAccount()
					.getAccNumeroCuenta();

		if (tipoAsignacion == PROYECTO)
			cuenta = new AccountingParametersDAO().findById(3L).getAccount()
					.getAccNumeroCuenta();

		return cuenta;

	}

}
