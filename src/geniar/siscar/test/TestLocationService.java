package geniar.siscar.test;

import geniar.siscar.persistence.CitiesDAO;
import gwork.exception.GWorkException;

public class TestLocationService {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws GWorkException {

		// LocationService locationService = new LocationServiceImpl();

		System.out.println(new CitiesDAO().findById(1L).getCtsNombre());

	}

}
