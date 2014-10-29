package geniar.siscar.test;

import geniar.siscar.logic.vehicle.services.BrandService;
import geniar.siscar.logic.vehicle.services.impl.BrandServiceImpl;
import gwork.exception.GWorkException;

public class TestServiceBrand {
	
	public static void main(String[] args) {
		
		BrandService brandService = new BrandServiceImpl();
		
		/*try {
			brandService.crearMarcas("JEEP");
		} catch (GWorkException e) {
			e.printStackTrace();
		}
		
		try {
			brandService.modificarMarca(22L, "MERCEDES");
		} catch (GWorkException e) {
			e.printStackTrace();
		}*/
		
		try {
			brandService.eliminarMarca(2L);
		} catch (GWorkException e) {
			e.printStackTrace();
		}
	}
}