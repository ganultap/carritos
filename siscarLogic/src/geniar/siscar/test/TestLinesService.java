package geniar.siscar.test;

import geniar.siscar.logic.vehicle.services.LinesService;
import geniar.siscar.logic.vehicle.services.impl.LinesServiceImpl;
import gwork.exception.GWorkException;

public class TestLinesService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LinesService linesService = new LinesServiceImpl();

		try {
			linesService.crearLineas("BMW", 1L);
		} catch (GWorkException e) {
			e.printStackTrace();
		}

		// try {
		// linesService.modificarLinea(21L, "MAZDA 6", 1L);
		// } catch (GWorkException e) {
		// e.printStackTrace();
		// }

	}

}
