package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.billing.services.impl.BillingAccountServiceImpl;
import geniar.siscar.util.CreateFile;

import java.io.IOException;

public class testArchivoPlano {
	public static void main(String[] args) {
		try {
			new BillingAccountServiceImpl().CobroCiatCasaCiat();
			CreateFile.createFile("C:/", "prueba", ".txt", "Ojala guarde");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
