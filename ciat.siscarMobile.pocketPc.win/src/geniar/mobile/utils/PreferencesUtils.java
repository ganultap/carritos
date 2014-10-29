/*
 * Created on 12/01/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package geniar.mobile.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

// import com.ibm.oti.connection.file.FileInputStream;
// import com.ibm.oti.connection.file.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import ciat.siscar.mobile.vo.VOCostCenters;
import ciat.siscar.mobile.vo.VOFuelTypeRequest;
import ciat.siscar.mobile.vo.VOPumps;

/**
 * @author Jaime Chavarriaga
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PreferencesUtils {

	private static String propertiesFile = "geniar.properties";
	private static Properties properties = null;

	public static Properties getProperties() throws Exception {
		if (properties == null) {
			properties = new Properties();
			try {
				InputStream is = new FileInputStream(propertiesFile);
				properties.load(is);
			} catch (Exception e) {
				throw new Exception("No se pudo abrir archivo de configuración");
			}
		}
		return properties;
	}

	public static void saveProperties() throws Exception {
		try {
			OutputStream os = new FileOutputStream(propertiesFile, false);
			properties.save(os, "actualizando");
		} catch (Exception e) {
			throw new Exception("no se pudo grabar archivo de configuración");
		}
	}

	public final static String[] split(String str, char separatorChar) {
			if (str == null) {
				return null;
			}
			int len = str.length();
			if (len == 0) {
				return null;
			}
			Vector list = new Vector();
			int i = 0;
			int start = 0;
			boolean match = false;
			while (i < len) {
				if (str.charAt(i) == separatorChar) {
					if (match) {
						list.addElement(str.substring(start, i).trim());
						match = false;
					}
					start = ++i;
					continue;
				}
				match = true;
				i++;
			}
			if (match) {
				list.addElement(str.substring(start, i).trim());
			}
			String[] arr = new String[list.size()];
			list.copyInto(arr);
			return arr;
		}

	public List ordenarListas(String cadena) {
		
		List lista = new ArrayList();
		VOCostCenters centers = null;
		VOFuelTypeRequest typeRequest = null;
		VOPumps pumps = null;		
		String temp = null;
		String arreglo[] = new String[1];
		String lastStr = null;
		String firstStr = null;
		int first = 3;
		int last = 0;
		int sum = 0;
		String clase = null;

		for (int i = 0, j = 0; i < cadena.length(); i++, j++) {
			char c = cadena.charAt(0);
			char l = cadena.charAt(1);
			clase = String.valueOf(c) + String.valueOf(l);

			if (clase.equalsIgnoreCase("cc")) {
				centers = new VOCostCenters();
				lastStr = "'" + sum + "'";
				last = cadena.indexOf(lastStr);
				temp = cadena.substring(first, last).trim();
				arreglo = split(temp,' ');
				firstStr ="'" + i + "'";
				if (cadena.indexOf(firstStr)+3 < cadena.length()){
					first = cadena.indexOf(firstStr)+3;								    				   
				    sum = j + 1;
				    centers.setCocCodigo(new Long(arreglo[0]));
				    centers.setCocNumero(arreglo[1]);			
				    lista.add(centers);				    
				}
				else
					return lista;
			}

			if (clase.equalsIgnoreCase("pm")) {
				pumps = new VOPumps();
				lastStr = "'" + sum + "'";
				last = cadena.indexOf(lastStr);
				temp = cadena.substring(first, last).trim();
				arreglo = split(temp,' ');
				firstStr ="'" + i + "'";
				if (cadena.indexOf(firstStr)+3 < cadena.length()){
					first = cadena.indexOf(firstStr)+3;								    				   
				    sum = j + 1;
				    pumps.setPumCodigo(new Long(arreglo[0]));
				    pumps.setPumNombre(arreglo[1]);			
				    lista.add(pumps);				    
				}
				else
					return lista;
			}

			if (clase.equalsIgnoreCase("tr")) {
				typeRequest = new VOFuelTypeRequest();
				lastStr = "'" + sum + "'";
				last = cadena.indexOf(lastStr);
				temp = cadena.substring(first, last).trim();
				arreglo = split(temp,' ');
				firstStr ="'" + i + "'";
				if (cadena.indexOf(firstStr)+3 < cadena.length()){
					first = cadena.indexOf(firstStr)+3;								    				   
				    sum = j + 1;
				    typeRequest.setFtrCodigo(new Long(arreglo[0]));
				    typeRequest.setFtrNombre(arreglo[1]);			
				    lista.add(typeRequest);				    
				}
				else
					return lista;
			}
		}
		return lista;
	}
	
	public String redondeoNumeros(String val,int cantidadDecimales){
		int sum=0;
		if (val != null && val.trim().length() !=0) {
			int num=val.lastIndexOf(".");
			sum=num+cantidadDecimales;
			if (sum <= val.length()) {
				val = val.substring(0, num+cantidadDecimales);
			}			
			return val;
		}
		return val;
	}
}
