package geniar.siscar.test;

import geniar.siscar.logic.fuels.services.ServiceRegistryService;
import geniar.siscar.logic.fuels.services.impl.ServiceRegistryImp;
import geniar.siscar.util.ParametersUtil;
import gwork.exception.GWorkException;

public class TestServiceRegistry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ServiceRegistryService registryService = new ServiceRegistryImp();

		try {
			registryService.serviceRegistryFuel(5L, null, "GVALENCIA",
					"JEFFER", "02123", "1050524", 10F, 50F,
					"PRUEBA NOTIFICATION", 1L, 14000L, null, null,
					ParametersUtil.USO_PREPAGO, 2L, null,
					ParametersUtil.CARGO_CARNE);
		} catch (GWorkException e) {
			e.printStackTrace();
		}

	}
}
