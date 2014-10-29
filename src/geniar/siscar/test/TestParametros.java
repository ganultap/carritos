package geniar.siscar.test;

import geniar.siscar.logic.parameters.services.ParametrosService;
import geniar.siscar.logic.parametros.services.impl.ParametrosServiceImpl;



public class TestParametros {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ParametrosService parametrosService = new ParametrosServiceImpl();
		
		/*try {
			parametrosService.crearParametros("NUMERODIASAÑO","Prueba de un parametro", "220");
		} catch (Exception e) {
			e.printStackTrace();
		*/
	
		try {
			parametrosService.actualizarParametros(1L,"NUMERODIASAÑO","COBRO DE DIAS HABILES", "220");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*FindParameterService findParameterService= new FindParameterServiceImpl();
		
		try {
			System.out.println(findParameterService.findParameterByName("NUMERODIASAÑO"));
			
		} catch (GWorkException e) {
			e.printStackTrace();
		}*/
	}
}