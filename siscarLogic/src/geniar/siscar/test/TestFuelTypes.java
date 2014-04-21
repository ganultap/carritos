package geniar.siscar.test;


public class TestFuelTypes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Float valorTotalFactura = 222771852F;
		
		Long valorTotalFactura1 = 222771852L;
		
		Long valorTotalFactura2 = valorTotalFactura.longValue();// (long)valorTotalFactura;
		
		//Long conversion =   new Long(valorTotalFactura); 
		String ValorFactura = String.valueOf(valorTotalFactura.toString());	
		System.out.println(valorTotalFactura);
		System.out.println(valorTotalFactura1);
		System.out.println(valorTotalFactura2);
		System.out.println(valorTotalFactura);
		System.out.println(ValorFactura);		

		/*TypeFuelsService fuelsService = new TypeFuelsServiceImpl();
		try {
			fuelsService.eliminarTipoCombustible("GASOLINA");
		} catch (GWorkException e) {
			e.printStackTrace();
		}*/
		
	}
}
