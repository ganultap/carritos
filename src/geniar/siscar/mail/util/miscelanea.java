package geniar.siscar.mail.util;

import java.util.Vector;
public class miscelanea {

	// Divide una cadena por el separador indicado y devuelve un arreglo de
	// cadenas
	public static Vector<String> Split(String cadena, String separator) {
		int i;
		Vector<String> vector = new Vector<String>();
		if (cadena == null) {
			cadena = "";
		}
		i = cadena.indexOf(separator, 0);
		if (i == -1) {
			vector.add(cadena);
			return vector;
		}
		vector = Split(cadena.substring(i + separator.length()), separator);
		if (i != 0)
			vector.add(0, cadena.substring(0, i));
		return vector;
	}
}
