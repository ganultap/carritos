/*
 * 
 */
package geniar.siscar.util;

import geniar.siscar.persistence.EntityManagerHelper;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class Util.
 *
 * @author - GeniarArq S.A
 * @version 1.0 Descripción : Clase encargada del manejo de metodos de utileria
 * de la aplicación
 */
public class Util {

	/** Carga los mensajes de error para los controladores de CU de la aplicación. */
	private static Log log = LogFactory.getLog(Util.class);

	/**
	 * Load parameters value.
	 *
	 * @param key the key
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public static String loadParametersValue(String key) throws GWorkException {
		String mesaje = LoaderResourceElements.getInstance()
				.getParametrosGenerales(key);

		if (mesaje == null){
			log.error(LoaderResourceElements.getInstance()
					.getErrorMessageValue("file.not.found"));
			
			throw new GWorkException(LoaderResourceElements.getInstance()
					.getErrorMessageValue("file.not.found"));
		}

		return mesaje;
	}

	/**
	 * Carga los mensajes de error para el programador de tareas.
	 *
	 * @param key the key
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public static String loadErrorMessageValue(String key)
			throws GWorkException {

		String mesaje = LoaderResourceElements.getInstance()
				.getErrorMessageValue(key);

		if (mesaje == null){
			
			log.error(LoaderResourceElements.getInstance()
					.getErrorMessageValue("file.not.found"));
			
			throw new GWorkException(LoaderResourceElements.getInstance()
					.getErrorMessageValue("file.not.found"));
		}

		return mesaje;
	}

	/**
	 * Carga los mensajes de la aplicación.
	 *
	 * @param key the key
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public static String loadMessageValue(String key) throws GWorkException {

		String mesaje = LoaderResourceElements.getInstance().getMsgProperties(
				key);

		if (mesaje == null)
			throw new GWorkException(LoaderResourceElements.getInstance()
					.getErrorMessageValue("file.not.found"));

		return mesaje;
	}

	/**
	 * Carga la expresion regular de numeros de acuerdo al archivo de
	 * propiedades.
	 *
	 * @param valor the valor
	 * @return the expresion
	 * @throws GWorkException the g work exception
	 */
	public static String getExpresion(String valor) throws GWorkException {

		String msg = LoaderResourceElements.getInstance().getValueExpresion(
				valor);

		if (msg == null)
			return "";
		return msg;
	}

	/**
	 * Valida que en una cadena solo vengan numeros para el bean de parametros.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarNumerosParametros(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.numero.parametros"));
	}

	/**
	 * Valida que en una cadena solo vengan numeros para el bean de parametros.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarMailEspecial(String valor)
			throws GWorkException {
		return validacionSintaxis(valor, getExpresion("expresion.mail"));
	}

	/**
	 * Valida que en una cadena solo vengan numeros para el bean de consulta a
	 * centrales.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarNumerosConsulta(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.numero.consulta"));
	}

	/**
	 * Validar numeros guion.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarNumerosGuion(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.soloNumeroGuion"));
	}

	/**
	 * Validar numeros decimales.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarNumerosDecimales(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.numeros.decimales"));
	}

	/**
	 * Valida que en una cadena solo vengan numeros para el bean de consulta a
	 * centrales.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarNumerosConsultaPuntos(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.numero"));
	}

	/**
	 * Valida que en una cadena solo vengan numeros con puntos y comas para el
	 * bean de consulta a centrales.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarNumerosConsultaPuntosComas(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.cargos"));
	}

	/**
	 * valida que en una cadena solo vengas letras del abcedario.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarCadenaCaracteres(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspecial"));
	}

	/**
	 * valida que en una cadena solo vengas letras del abcedario o numeros.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarCadenaCaracteresEspecialesNumLetrasGuion(
			String valor) throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.general"));
	}

	/**
	 * valida que en una cadena solo vengas letras del abcedario o numeros
	 * precedidas por letras del abcedario o numeros, divididas por un guión
	 * (Ej. 3454345-54)
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarCadenaCaracteresEspecialesNumLetrasGuionEspecial(
			String valor) throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.especial"));
	}

	/**
	 * valida que en una cadena solo vengas letras del abcedario o numeros sin
	 * espacios.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarPlaca(String valor) throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.placa"));
	}

	/**
	 * valida que en una cadena solo vengas letras del abcedario o numeros.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarCadenaCaracteresEspecialesFechas(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.fecha"));
	}

	/**
	 * Validar cadena caracteres especiales.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarCadenaCaracteresEspeciales(String valor)
			throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspecial"));
	}

	/**
	 * valida que en una cadena solo vengas letras del abcedario o numeros.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarCadenaCaracteresEspecialesPorcentaje(
			String valor) throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.porcentaje"));
	}

	/**
	 * valida que en una cadena solo tenga el simbolo %.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public static boolean validarPorcentaje(String valor) throws Exception {
		return validacionSintaxis(valor, getExpresion("expresion.porcentaje"));
	}

	/**
	 * valida que en una cadena solo tenga el simbolo %.
	 *
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarMail(String valor) throws GWorkException {
		return validacionSintaxis(valor,
				getExpresion("expresion.caracterEspeciales.mail"));
	}

	/**
	 * Metodo general que recibe un valor y una expresion regular.
	 *
	 * @param valor the valor
	 * @param expresion the expresion
	 * @return true, if successful
	 */
	public static boolean validacionSintaxis(String valor, String expresion) {
		boolean esValido = false;
		String cadenas[] = valor.split(" ");
		int cont = 0;
		for (int i = 0; i < cadenas.length; i++) {
			String temp = cadenas[i];
			esValido = validarExpresionesRegulares(temp, expresion);
			if (!esValido)
				cont++;
		}
		if (cont > 0)
			return false;

		return true;

	}

	/**
	 * Validar expresiones regulares.
	 *
	 * @param valor the valor
	 * @param expresion the expresion
	 * @return true, if successful
	 */
	public static boolean validarExpresionesRegulares(String valor,
			String expresion) {
		// comprueba que no contenga caracteres prohibidos
		Pattern p = Pattern.compile(expresion);
		Matcher m = p.matcher(valor);

		m = p.matcher(valor);
		StringBuffer sb = new StringBuffer();
		boolean resultado = m.find();
		boolean caracteresIlegales = false;

		while (resultado) {
			caracteresIlegales = true;
			m.appendReplacement(sb, "");
			resultado = m.find();
		}

		// Añade el ultimo segmento de la entrada a la cadena
		m.appendTail(sb);

		if (caracteresIlegales)
			return false;
		return true;
	}

	/**
	 * Funcion que a partir de una fecha retorna el dia, año y mes.
	 *
	 * @param date the date
	 * @return the string
	 */
	public String retornarDiasAño(Date date) {

		StringBuffer valor = new StringBuffer();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(new Date());

		int anho = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int diaMes = calendario.get(Calendar.DAY_OF_MONTH);

		valor.append(anho);
		valor.append(" ");
		valor.append(mes);
		valor.append(" ");
		valor.append(diaMes);

		return valor.toString();
	}

	/**
	 * Comparar numero dias fechas.
	 *
	 * @param fechaMayor the fecha mayor
	 * @param fechaMenor the fecha menor
	 * @return the long
	 */
	public static long compararNumeroDiasFechas(Date fechaMayor, Date fechaMenor) {
		long diferenciaMils = 0;
		long horas = 0;
		long dias = 0;
		long segundos = 0;
		try {
			if (fechaMayor != null && fechaMenor != null) {

				diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();
				// obtenemos los segundos
				segundos = diferenciaMils / 1000;
				// obtenemos las horas
				horas = segundos / 3600;
				// obtenemos los dias
				dias = horas / 24;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		if (dias < 0)
			dias = dias * -1L;

		return dias;
	}

	/**
	 * EL patron puede ser null.
	 *
	 * @param dateToFormat fecha a formatear
	 * @param pattern patron de formato. Si es null, el patron por defecto sera
	 * yyyyMMdd
	 * @return fechaFormateada segun patron
	 */
	public static String formatDate(Date dateToFormat, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		if (pattern != null)
			sdf.applyPattern(pattern);
		else
			sdf.applyPattern("yyyyMMdd");

		String formattingDate = sdf.format(dateToFormat);

		return formattingDate;
	}

	/**
	 * Traer fecha actual fecha.
	 *
	 * @return the date
	 * @throws Exception the exception
	 */
	public static Date traerFechaActualFecha() throws Exception {
		Date date = new Date();
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(date);

		calendario.set(Calendar.YEAR, 2008);
		calendario.set(Calendar.MONTH, 11);
		calendario.set(Calendar.DAY_OF_MONTH, 31);
		date = calendario.getTime();
		return date;
	}

	/**
	 * Traer fecha mas dias.
	 *
	 * @param dias the dias
	 * @param fecha the fecha
	 * @return the date
	 * @throws GWorkException the g work exception
	 */
	public static Date traerFechaMasDias(int dias, Date fecha)
			throws GWorkException {
		Date date = new Date();

		Calendar calendario = Calendar.getInstance();
		if (fecha != null)
			calendario.setTime(fecha);
		else
			calendario.setTime(date);

		int anho = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int diaMes = calendario.get(Calendar.DAY_OF_MONTH);

		calendario.set(Calendar.YEAR, anho);
		calendario.set(Calendar.MONTH, mes);
		calendario.set(Calendar.DAY_OF_MONTH, diaMes + dias);
		date = calendario.getTime();
		return date;
	}

	/**
	 * Convertir cadena.
	 *
	 * @param cadena the cadena
	 * @return the int
	 * @throws GWorkException the g work exception
	 */
	public static int convertirCadena(String cadena) throws GWorkException {
		int num = Integer.parseInt(cadena.replace("%", ""));
		return num;
	}

	/**
	 * Calcula una Fecha con un año de adelanto.
	 *
	 * @param fechaBase fecha a partir de la cual se realizara el calculo.
	 * @param manejarBisiestos indica si se deben tener en cuenta los años bisiestos o nó.
	 * @return Fecha con un año de adelanto.
	 * @Comment Los meses se manejan de 0-11 donde 0 es Enero y 11 Diciembre
	 * respectivamente.
	 */
	@SuppressWarnings( { "deprecation", "static-access" })
	public static Date obtenerFechaAnhoAdelante(Date fechaBase,
			boolean manejarBisiestos) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaBase);

		if (manejarBisiestos) {
			if (validarAnhoBisiesto(fechaBase.getYear())) {
				calendar.add(calendar.DATE, 366);
			} else {
				calendar.add(calendar.DATE, 365);
			}

			return calendar.getTime();
		}

		calendar.add(calendar.DATE, 365);
		return calendar.getTime();
	}

	/**
	 * Determina si el año ingresado es bisiesto o nó.
	 *
	 * @param anho Año a validar si es bisiesto.
	 * @return true si es bisiesto;
	 * @Comment Calculo tomado de
	 * http://lineadecodigo.com/2007/02/18/ano-bisiesto-en-java/
	 */
	public static boolean validarAnhoBisiesto(int anho) {

		if ((anho % 4 == 0) && ((anho % 100 != 0) || (anho % 400 == 0))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Validar email.
	 *
	 * @param email the email
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public static boolean validarEmail(String email) throws GWorkException {
		Pattern p = Pattern.compile(getExpresion("expresion.mail"));
		Matcher m = p.matcher(email);
		if (m.find()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Valida que el 'valor' ingresado se encuentre en el rango<br>
	 * minimo y maximo de caracteres especificado y genera la excepcion<br>
	 * con la llave de error que se solicite. Esta llave debe ir tal cual<br>
	 * aparece en el archivo de mensajes de error.<br>
	 * 
	 * @param valor
	 *            Valor a ser validado en l rango.
	 * @param maximo
	 *            Limite máximo de caracteres.
	 * @param minimo
	 *            Limite mínimo de caracteres.
	 * @param llaveError
	 *            Nombre del error en el archivo de mensajes de error.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public static void validarLimite(String valor, int maximo, int minimo,
			String llaveError) throws GWorkException {
		if (valor.trim().length() < minimo) {
			throw new GWorkException(Util.loadErrorMessageValue(llaveError));
		}
		if (valor.trim().length() > maximo) {
			throw new GWorkException(Util.loadErrorMessageValue(llaveError));
		}
	}

	/**
	 * Convertir decimales a numeros.
	 *
	 * @param valor the valor
	 * @return the string
	 */
	public static String convertirDecimalesANumeros(String valor) {
		String temp = valor.replace(".", ",");
		return temp;
	}
	
	/**
	 * Convertir cadena entero.
	 *
	 * @param valor the valor
	 * @return the string
	 */
	public static String convertirCadenaEntero(String valor) {
		String temp = valor.replace(".", "");
		return temp;
	}

	/**
	 * Da formato a un numero float.
	 * 
	 * @param valorNumerico
	 *            al que se va a dar formato.
	 * @return Número float.
	 */
	public static Float validarDecimales(Float valorNumerico) {

		DecimalFormat format = new DecimalFormat("0.00");
		String valorDecimal = format.format(valorNumerico);
		Float valor = new Float(valorDecimal);

		return valor;
	}

	/**
	 * Cambia las comas de una cadena por puntos.
	 * 
	 * @param param
	 *            al que se va a cambiar la coma por un punto.
	 * @return Un numero de tipo Float en el format ###.##
	 */
	public static Float convertirDecimal(String param) {
		String r = "";
		for (char c : param.toCharArray()) {
			if (c == ',') {
				c = '.';
			}
			r = r + c;
		}

		Float f = Float.parseFloat(r);

		return f.floatValue();
	}

	/**
	 * Convertir decimal double.
	 *
	 * @param param the param
	 * @return the big decimal
	 */
	public static BigDecimal convertirDecimalDouble(String param) {
		String r = "";
		for (char c : param.toCharArray()) {
			if (c == ',') {
				c = '.';
			}
			r = r + c;
		}

		BigDecimal d = new BigDecimal(r);

		return d;
	}

	/**
	 * Convertir cadena decimal.
	 *
	 * @param param the param
	 * @return the string
	 */
	public static String convertirCadenaDecimal(String param) {
		String r = "";
		for (char c : param.toCharArray()) {
			if (c == '.') {
				c = ',';
			}
			r = r + c;
		}

		return r;
	}

	/**
	 * Metodo encargado de validar si la session esta abierta.
	 */
	public static void validarSession() {
		if (EntityManagerHelper.getEntityManager().isOpen())
			EntityManagerHelper.getEntityManager().close();
	}

	/**
	 * Gets the url.
	 *
	 * @return the url
	 * @throws GWorkException the g work exception
	 */
	public static String getUrl() throws GWorkException {
		return Util.loadParametersValue("URL.SISCAR");
	}

	/**
	 * Redondear.
	 *
	 * @param numero the numero
	 * @param decimales the decimales
	 * @return the double
	 */
	public static Double redondear(double numero, int decimales) {
		return Math.round(numero * Math.pow(10, decimales))
				/ Math.pow(10, decimales);
	}

	/**
	 * Redondear float.
	 *
	 * @param numero the numero
	 * @param decimales the decimales
	 * @return the float
	 */
	public static Float redondearFloat(float numero, int decimales) {
		double valor = (double) numero;

		Float valorFlotante = (float) (Math.round(valor
				* Math.pow(10, decimales)) / Math.pow(10, decimales));
		return valorFlotante;
	}
	
	/**
	 * Round num.
	 *
	 * @param num the num
	 * @return the float
	 * @throws Exception the exception
	 */
	public static float roundNum(float num) throws Exception
	{
		float valor = 0;
		
		valor = num;
	
		valor = valor*100;
		valor = java.lang.Math.round(valor);
		valor = valor/100;
	
		return valor;

	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {

		// Date fecha = dateFormat.parse(date.toString());
		// System.out.println(fecha);

		/*
		 * Date date = new Date(); Date date2 = new Date(); String fecha =
		 * formatDate(date, "dd-MMM-yyyy"); String fecha2 = formatDate(date2,
		 * "dd-MMM-yyyy"); System.out.println(fecha);
		 * System.out.println(fecha.compareTo(fecha2));
		 */
		// Float val = new Float("45000000000.00");
		// System.out.println(val);
		// Double val2 = new Double(val.toString());
		// System.out.println(val2.longValue());
		// System.out.println(validarNumerosConsultaPuntos("10"));
		// System.out.println("vo1900ss".toUpperCase());
		// System.out.println(validarMail("22@gg.com"));
		// System.out.println(convertirNumeroPorcentaje("5%"));
		// traerFechaMasDias(10);
		// String fechaCadena="1/4/2008";
		// SimpleDateFormat dateFormat= new SimpleDateFormat();
		// Date fecha=dateFormat.parse(fechaCadena);
	}
	
	
	/**
	 * Convert a value to currency account.
	 *
	 * @param d the d
	 * @param i the i
	 * @return the string
	 */
	public static String doubleToStringLocale(double d, int i) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
		nf.setMinimumFractionDigits(i);
		nf.setMaximumFractionDigits(i);
		return nf.format(d);
	}

}
