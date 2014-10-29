package geniar.siscar.util;

import gwork.exception.GWorkException;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class ManipulacionFechas extends Object {
	private static String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun",
			"Jul", "Ago", "Sep", "Oct", "Nov", "Dic" };

	private static String[] mesesCompletos = { "Enero", "Febrero", "Marzo",
			"Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
			"Octubre", "Noviembre", "Diciembre" };

	private static java.util.Date fechaActual = new java.util.Date();

	private static SimpleDateFormat formatoDia = new SimpleDateFormat("dd");

	private static SimpleDateFormat formatoMes = new SimpleDateFormat("MM");

	private static SimpleDateFormat formatoAgno = new SimpleDateFormat("yyyy");

	private static SimpleDateFormat formatoHora = new SimpleDateFormat("HH");

	private static SimpleDateFormat formatoMinuto = new SimpleDateFormat("mm");

	private static SimpleDateFormat formatoSegundo = new SimpleDateFormat("ss");

	private static int agno = Integer.parseInt(formatoAgno.format(fechaActual));

	private static int mes = Integer.parseInt(formatoMes.format(fechaActual));

	private static Vector<String> dias = new Vector<String>();

	private static Vector<String> agnos = new Vector<String>();

	private static String[] horas = new String[24];

	private static String[] minutosSegundos = new String[60];

	/**
	 * Constructor
	 */
	public ManipulacionFechas() {
	}

	/**
	 * Convierte a string la fecha correspondiente al parametro de entrada. La
	 * cadena devuelta esta en el formato dd/MM/yyyy HH:mm:ss. Si la fecha es
	 * null devuelve una cadena vacia.
	 * 
	 * @param fecha
	 *            java.sql.Date
	 * @return String correspondiente a la fecha
	 */
	public static String dateToString(java.sql.Date fecha) {
		if (fecha != null) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");

			return new String(formatoFecha.format(fecha));
		} else
			return "";
	}

	public static String dateToStringFormato(java.util.Date fecha) {
		if (fecha != null) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(
					"MM-dd-yyyy HH:mm:ss");

			return new String(formatoFecha.format(fecha));
		} else
			return "";
	}

	/**
	 * Convierte a string la fecha correspondiente al parametro de entrada. La
	 * cadena devuelta esta en el formato dd/MM/yyyy HH:mm:ss. Si la fecha es
	 * null devuelve una cadena vacia.
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return String correspondiente a la fecha
	 */
	public static String dateToString(java.util.Date fecha) {
		if (fecha != null) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");

			return new String(formatoFecha.format(fecha));
		} else {
			return "";
		}
	}

	/**
	 * Convierte a string la fecha correspondiente al parametro de entrada.
	 * Ignora las horas, minutos y segundos. La cadena devuelta esta en el
	 * formato dd/MM/yyyy. Si la fecha es null devuelve una cadena vacia.
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return String correspondiente a la fecha
	 */
	public static String dateToString(java.util.Date fecha,
			boolean despreciarHorasMinSeg) {
		SimpleDateFormat formatoFecha = null;
		if (fecha != null) {
			if (despreciarHorasMinSeg) {
				formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			} else {
				formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			}

			return new String(formatoFecha.format(fecha));
		} else {
			return "";
		}
	}

	/**
	 * Convierte a string la fecha correspondiente al parametro de entrada. La
	 * cadena devuelta esta en el formato dd-mmm-yyyy. Si la fecha es null
	 * devuelve una cadena vacia.
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return String correspondiente a la fecha
	 */
	public static String dateToStringConFormato(java.util.Date fecha) {
		if (fecha != null) {
			return getDia(fecha) + "-" + getMes(fecha) + "-" + getAgno(fecha);
		} else {
			return "";
		}
	}

	/**
	 * Convierte a java.util.Date una cadena de texto. La cadena texto en el
	 * parametro de entrada esta en formato dd/MM/yyyy HH:mm:ss. Si fechaString
	 * es null devuelve una fecha nula.
	 * 
	 * @param fechaString
	 *            java.lang.String en cadena de texto con la fecha en formato
	 *            dd/MM/yyyy HH:mm:ss.
	 * @return fecha java.util.Date correspondiente a fechaString
	 */
	public static java.util.Date stringToDate(String fechaString) {
		java.util.Date fecha;

		fecha = null;
		if (fechaString != null) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			fecha = formatoFecha.parse(fechaString, pos);

			if (fecha == null) {
				formatoFecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				pos = new ParsePosition(0);
				fecha = formatoFecha.parse(fechaString, pos);
			}// fin if

		}
		return fecha;
	}

	/**
	 * Convierte a java.util.Date una cadena de texto, que viene especificada en
	 * el formato . Si fechaString es null devuelve una fecha nula.
	 * 
	 * @param fechaString
	 *            java.lang.String cadena de texto con la fecha.
	 * @param formato
	 *            java.lang.String cadena de texto el formato de la fecha.
	 * @return fecha java.util.Date correspondiente a fechaString
	 */
	public static java.util.Date stringToDate(String fechaString, String formato) {
		java.util.Date fecha;

		fecha = null;
		if (fechaString != null) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
			ParsePosition pos = new ParsePosition(0);
			fecha = formatoFecha.parse(fechaString, pos);

		}
		return fecha;
	}

	/**
	 * Retorna una cadena de texto con la sentencia TO_CHAR de sql
	 * correspondiente al campo que entra como parametro
	 * 
	 * @param nombreCampo
	 *            java.lang.String con el nombre del campo de la tabla
	 * @return java.lang.String con el siguiente texto: TO_CHAR( nombreCampo,
	 *         'DD/MM/YYYY HH24:MI:SS')
	 */
	public static String getToChar(String nombreCampo) {
		return "TO_CHAR( " + nombreCampo + ", 'DD/MM/YYYY HH24:MI:SS')";

	}

	/**
	 * Retorna una cadena de texto con la sentencia TO_DATE de sql
	 * correspondiente al campo que entra como parametro
	 * 
	 * @param nombreCampo
	 *            java.lang.String con el nombre del campo de la tabla, o nombre
	 *            de la variable o simbolo.
	 * @return java.lang.String con el siguiente texto: TO_DATE( nombreCampo,
	 *         'DD/MM/YYYY HH24:MI:SS')
	 */
	public static String getToDate(String nombreCampo) {
		return "TO_DATE( " + nombreCampo + ", 'DD/MM/YYYY HH24:MI:SS')";
	}

	/**
	 * Retorna una cadena de texto con la sentencia TO_DATE de sql
	 * correspondiente al campo que entra como parametro, pero ignorando las
	 * horas minutos y segundos
	 * 
	 * @param nombreCampo
	 *            java.lang.String con el nombre del campo de la tabla, o nombre
	 *            de la variable o simbolo.
	 * @return java.lang.String con el siguiente texto: TO_DATE( nombreCampo,
	 *         'DD/MM/YYYY')
	 */
	public static String getToDate(String nombreCampo,
			boolean despreciarHorasMinSeg) {
		if (despreciarHorasMinSeg)
			return "TO_DATE( " + nombreCampo + ", 'DD/MM/YYYY')";
		else
			return "TO_DATE( " + nombreCampo + ", 'DD/MM/YYYY HH24:MI:SS')";
	}

	/**
	 * Retorna el dia correspondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getDia(java.util.Date fecha) {
		if (fecha != null) {
			return formatoDia.format(fecha);
		} else {
			return "";
		}
	}

	/**
	 * Retorna el numero del mes correspondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getMes(java.util.Date fecha) {
		if (fecha != null) {
			String mesString = formatoMes.format(fecha);
			String mes = mesString;
			return mes;
		} else {
			return "";
		}
	}

	/**
	 * Retorna el nombre del mes correspondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getNombreMes(java.util.Date fecha) {
		if (fecha != null) {
			String mesString = formatoMes.format(fecha);
			int mes = Integer.parseInt(mesString);
			boolean encontro = false;
			for (int i = 0; i < meses.length && !encontro; i++) {
				if (i == mes - 1) {
					encontro = true;
					mesString = meses[i];
				}
			}
			return mesString;
		} else {
			return "";
		}
	}

	/**
	 * Retorna el nombre del mes correspondiente al numero recibido como
	 * parámetro en parametro numero mes
	 * 
	 * @param nombreMes
	 *            java.lang.String
	 * @return String nombre del mes
	 */
	public static String getNombreMes(String numeroMes) {
		boolean encontro = false;
		String nombreMes = "";
		if (mesValido(numeroMes)) {
			int mes = Integer.parseInt(numeroMes);
			for (int i = 0; i < meses.length && !encontro; i++) {
				if (mes == (i + 1)) {
					encontro = true;
					nombreMes = meses[i];
				}
			}
		}
		return nombreMes;
	}

	/**
	 * Retorna un valor booleano que indica si el valor que entra por parametro
	 * corresponde a un numero de mes valido. Retorna true, si el numeroMes esta
	 * entre 1 y 12; false en otro caso
	 * 
	 * @param nombreMes
	 *            java.lang.String
	 * @return boolean
	 */
	public static boolean mesValido(String numeroMes) {
		boolean valido = true;
		if (numeroMes == null || numeroMes.equals("")) {
			valido = false;
		} else {
			try {
				Integer.parseInt(numeroMes);
			} catch (Exception e) {
				valido = false;
			}
			if (mes < 1 || mes > 12) {
				valido = false;
			}
		}

		return valido;
	}

	/**
	 * Retorna el numero del mes correspondiente al nombre del mes que esta en
	 * en parametro nombreMes
	 * 
	 * @param nombreMes
	 *            java.lang.String
	 * @return int numero del mes
	 */
	public static int getNumeroMes(String nombreMes) {
		boolean encontro = false;
		int numeroMes = -1;
		for (int i = 0; i < meses.length && !encontro; i++) {
			if (meses[i].equals(nombreMes)) {
				encontro = true;
				numeroMes = ++i;
			}
		}
		return numeroMes;
	}

	/**
	 * Retorna el agno correspondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getAgno(java.util.Date fecha) {
		if (fecha != null) {
			return formatoAgno.format(fecha);
		} else {
			return "";
		}
	}

	/**
	 * Retorna las horas correspondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getHora(java.util.Date fecha) {
		if (fecha != null) {
			return formatoHora.format(fecha);
		} else {
			return "";
		}
	}

	/**
	 * Retorna los minutoscorrespondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getMinutos(java.util.Date fecha) {
		if (fecha != null) {
			return formatoMinuto.format(fecha);
		} else {
			return "";
		}
	}

	/**
	 * Retorna los minutoscorrespondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getSegundos(java.util.Date fecha) {
		if (fecha != null) {
			return formatoSegundo.format(fecha);
		} else {
			return "";
		}
	}

	/**
	 * Retorna la fecha en formato dd/MM/yyyy con las horas minutos y segundos
	 * en cero, formada por los parametros
	 * 
	 * @param dia
	 *            java.lang.String el numero del dia (dd)
	 * @param mes
	 *            java.lang.String las tres primera letras del nombre del mes
	 *            (mmm)
	 * @param agno
	 *            java.lang.String el numero del año (yyyy)
	 * @return java.util.Date
	 */
	public static java.util.Date getFecha(String dia, String nombreMes,
			String agno) {
		int mes = getNumeroMes(nombreMes);

		String fecha = dia + "/" + mes + "/" + agno + " 00:00:00";

		return stringToDate(fecha);
	}

	/**
	 * Retorna la fecha en formato dd/MM/yyyy hh24:mm:ss, formada por los
	 * parametros
	 * 
	 * @param dia
	 *            java.lang.String el numero del dia (dd)
	 * @param mes
	 *            java.lang.String las tres primera letras del nombre del mes
	 *            (mmm)
	 * @param agno
	 *            java.lang.String el numero del año (yyyy)
	 * @param horas
	 *            java.lang.String las horas
	 * @param minutos
	 *            java.lang.String los minutos
	 * @param segundos
	 *            java.lang.String los segundos
	 * @return java.util.Date
	 */
	public static java.util.Date getFechaHora(String dia, String nombreMes,
			String agno, String horas, String minutos, String segundos) {
		int mes = getNumeroMes(nombreMes);

		String fecha = dia + "/" + mes + "/" + agno + " " + horas + ":"
				+ minutos + ":" + segundos;

		return stringToDate(fecha);
	}

	/**
	 * Inicializa los arreglos en donde se almacenas los años, los dias, las
	 * horas y minutos que se desplegaran en las listas. El arreglo de dias
	 * depende del año y el mes actual
	 */
	public static void InicializarFecha() {
		inicializarAgnos();
		inicializarDias(agno, mes);
		inicializarHoras();
		inicializarMinutosSegundos();
	}

	/**
	 * Determina el numero de dias para cada mes de un año. Este numero depende
	 * de si el año es bisiesto o no.
	 * 
	 * @param nuevoAgno
	 *            int
	 * @param nuevoMes
	 *            int
	 * @return int con el numero de dias por mes
	 */
	public static int maximoDiasMes(int nuevoAgno, int nuevoMes) {
		int numeroDias;
		boolean esBisiesto = (nuevoAgno % 4 == 0)
				&& ((nuevoAgno % 100 == 0) || (nuevoAgno % 400 == 0));

		switch (nuevoMes) {
		case 4:
		case 6:
		case 9:
		case 11:
			numeroDias = 30;
			break;
		case 2:
			numeroDias = ((esBisiesto) ? 29 : 28);
			break;
		default:
			numeroDias = 31;
			break;
		}
		return numeroDias;
	}

	/**
	 * Crea un vector en donde se almacenan los dias para el mes.
	 * 
	 * @param nuevoAgno
	 *            ano
	 * @param nuevoMes
	 *            mes
	 */
	private static void inicializarDias(int nuevoAgno, int nuevoMes) {
		String diaString = "";
		int diasMes = maximoDiasMes(nuevoAgno, nuevoMes);
		for (int i = 1; i <= diasMes; i++) {
			if (i < 10) {
				diaString = "0" + i;
			} else {
				diaString = "" + i;
			}
			dias.addElement(diaString);
		}
	}

	/**
	 * Adiciona los elementos al vector agnos, que contiene los años en orden
	 * desendente comprendidos en el rango del año actual mas 7 años y el año
	 * actual menos 7 años.
	 */
	@SuppressWarnings("unchecked")
	private static void inicializarAgnos() {
		agnos = new Vector(5, 5);
		int agnoFinal = agno + 7;
		int agnoInicial = agno - 7;

		for (int i = agnoFinal; i > agnoInicial; i--) {
			agnos.addElement("" + i);
		}

	}

	/**
	 * Metodo para obtener una lista HTML con los dias para el mes
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllDias() {
		return getAllDias(new java.util.Date());
	}

	/**
	 * Metodo para obtener una lista HTML con los dias para el mes
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllDias(java.util.Date fecha) {
		String lista = "";
		String value, valueActivo, descripcion;
		valueActivo = getDia(fecha);
		int maximoDias = dias.size();
		for (int i = 0; i < maximoDias; i++) {
			value = "" + dias.elementAt(i); // (i+1);
			descripcion = "" + dias.elementAt(i);
			lista += "<option value = \"" + value + "\"";
			if (valueActivo.equals(value)) {
				lista += " selected ";
			}
			lista += "> " + descripcion + "</option> ";
		}

		return lista;
	}

	/**
	 * Metodo para obtener una lista HTML con los meses para el año
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllMeses() {
		return getAllMeses(new java.util.Date());
	}

	/**
	 * Metodo para obtener una lista HTML con los meses para el año
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllMeses(java.util.Date fecha) {
		String lista = "";
		String descripcion;
		int valueActivo = getNumeroMes(fecha);
		int value;
		int maximoMeses = meses.length;

		for (int i = 0; i < maximoMeses; i++) {
			value = i + 1;
			descripcion = "" + meses[i];

			lista += "<option value = \"" + value + "\"";
			if (valueActivo == value) {
				lista += " selected ";
			}
			lista += "> " + descripcion + "</option> ";
		}

		return lista;
	}

	/**
	 * Metodo para obtener el número correspondiente a un mese del año
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static int getNumeroMes(java.util.Date fecha) {
		if (fecha != null) {
			String mesString = formatoMes.format(fecha);
			int mes = Integer.parseInt(mesString);

			return mes;
		} else {
			return -1;
		}
	}

	/**
	 * Metodo para obtener una lista HTML con los años
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllAgnos() {
		return getAllAgnos(new java.util.Date());
	}

	/**
	 * Metodo para obtener una lista HTML con los años
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllAgnos(java.util.Date fecha) {
		String lista = "";
		String value, valueActivo, descripcion;
		valueActivo = getAgno(fecha);
		int maximoAgnos = agnos.size();
		for (int i = 0; i < maximoAgnos; i++) {
			value = "" + agnos.elementAt(i);
			descripcion = "" + agnos.elementAt(i);

			lista += "<option value = \"" + value + "\"";
			if (valueActivo.equals(value)) {
				lista += " selected ";
			}
			lista += "> " + descripcion + "</option> ";
		}

		return lista;
	}

	/**
	 * Metodo para obtener una lista HTML con las horas
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllHoras() {
		return getAllHoras(new java.util.Date());

	}

	/**
	 * Metodo para obtener una lista HTML con las horas
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllHoras(java.util.Date fecha) {
		String lista = "";
		String value, valueActivo, descripcion;
		valueActivo = getHora(fecha);
		int maximoHoras = horas.length;
		for (int i = 0; i < maximoHoras; i++) {
			value = "" + horas[i];
			descripcion = "" + horas[i];

			lista += "<option value = \"" + value + "\"";
			if (valueActivo.equals(value)) {
				lista += " selected ";
			}
			lista += "> " + descripcion + "</option> ";
		}

		return lista;
	}

	/**
	 * Metodo para obtener una lista HTML con los Minutos
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllMinutos() {
		return getAllMinutos(new java.util.Date());

	}

	/**
	 * Metodo para obtener una lista HTML con los Minutos
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllMinutos(java.util.Date fecha) {
		String lista = "";
		String value, valueActivo, descripcion;
		valueActivo = getMinutos(fecha);
		int maximoMinutos = minutosSegundos.length;
		for (int i = 0; i < maximoMinutos; i++) {
			value = "" + minutosSegundos[i];
			descripcion = "" + minutosSegundos[i];

			lista += "<option value = \"" + value + "\"";
			if (valueActivo.equals(value)) {
				lista += " selected ";
			}
			lista += "> " + descripcion + "</option> ";
		}

		return lista;
	}

	/**
	 * Metodo para obtener una lista HTML con los segundos
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllSegundos() {
		return getAllSegundos(new java.util.Date());
	}

	/**
	 * Metodo para obtener una lista HTML con los segundos
	 * 
	 * @param -
	 *            sin parametros
	 * @return String
	 */
	public static String getAllSegundos(java.util.Date fecha) {
		String lista = "";
		String value, valueActivo, descripcion;
		valueActivo = getSegundos(fecha);
		int maximoSegundos = minutosSegundos.length;
		for (int i = 0; i < maximoSegundos; i++) {
			value = "" + minutosSegundos[i];
			descripcion = "" + minutosSegundos[i];
			lista += "<option value = \"" + value + "\"";
			if (valueActivo.equals(value)) {
				lista += " selected ";
			}
			lista += "> " + descripcion + "</option> ";
		}

		return lista;
	}

	/**
	 * Cada vez que se selecciona un año en el combo box de años, se iniciliza
	 * el vector de dias segun el año y mes seleccionados.
	 * 
	 * @param e
	 *            ActionEven que indica una accion sobre el combo box de años
	 */
	public static void CambioAgno(int Nuevoagno) {
		inicializarDias(Nuevoagno, mes);
	}

	/**
	 * Cada vez que se selecciona un mes en el combo box de meses, se iniciliza
	 * el vector de dias segun el año y mes seleccionados.
	 * 
	 * @param e
	 *            ActionEven que indica una accion sobre el combo box de meses
	 */
	public static void CambioMes(int nuevoMes) {
		inicializarDias(agno, nuevoMes);
	}

	/**
	 * Crea un arreglo en donde se almacenan las horas del dia
	 */
	private static void inicializarHoras() {
		String horaString = "";
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				horaString = "0" + i;
			} else {
				horaString = "" + i;
			}
			horas[i] = horaString;

		}
	}

	/**
	 * Crea un arreglo en donde se almacenan los minutos y segundos de la hora
	 */
	private static void inicializarMinutosSegundos() {
		String minSegString = "";
		for (int i = 0; i < 60; i++) {
			if (i < 10) {
				minSegString = "0" + i;
			} else {
				minSegString = "" + i;
			}
			minutosSegundos[i] = minSegString;
		}
	}

	/**
	 * 
	 */
	public static long calculaTiempoTranascurridoDias(
			java.util.Date fechaActual, java.util.Date fechaFinal) {
		long dias = ((calcularTotalSegundos(fechaActual, fechaFinal) / 60) / 60) / 24;
		return dias;
	}

	/**
	 * Calcula la diferencia de meses de dos fechas ingresadas
	 */
	public static long calculaTiempoTranascurridoMeses(
			java.util.Date fechaActual, java.util.Date fechaFinal) {
		int mesIni;
		int mesFin;
		Calendar calendarIni = Calendar.getInstance();
		Calendar calendarFin = Calendar.getInstance();
		calendarIni.setTime(fechaActual);
		calendarFin.setTime(fechaFinal);
		mesIni = calendarIni.get(Calendar.MONTH);
		mesFin = calendarFin.get(Calendar.MONTH) + 1;

		return mesFin - mesIni;
	}

	public static long calculaTiempoTranascurridoHoras(
			java.util.Date fechaActual, java.util.Date fechaFinal) {
		long totalHoras = (calcularTotalSegundos(fechaActual, fechaFinal) / 60) / 60;

		long tiempoTranscurridoHoras = totalHoras;

		return tiempoTranscurridoHoras;
	}

	public static long calculaTiempoTranascurridoMinutos(
			java.util.Date fechaActual, java.util.Date fechaFinal) {
		long minutos = calcularTotalSegundos(fechaActual, fechaFinal) / 60; // se
		// convierte
		// a
		// minutos
		long tiempoTranscurridoMinutos = minutos % 60;
		return tiempoTranscurridoMinutos;
	}

	public static long calculaTiempoTranascurridoSegundos(
			java.util.Date fechaActual, java.util.Date fechaFinal) {
		long totalTiempoSegundos = calcularTotalSegundos(fechaActual,
				fechaFinal);
		long tiempoTranscurridoSegundos = totalTiempoSegundos % 60;// se pasa a
		// segundos
		return tiempoTranscurridoSegundos;
	}

	public static long calcularTotalSegundos(java.util.Date fechaActual,
			java.util.Date fechaFinal) {

		java.util.Date d = fechaActual;
		java.util.Date h = fechaFinal;
		long dif = 0;

		long tiempoTranscurridoSegundos = 0;

		// se calcula diferencia en segundos
		if (validaRangoFechas(d, h)) {
			dif = d.getTime() - h.getTime();
		}

		tiempoTranscurridoSegundos = dif / 1000; // se pasa a segundos

		return tiempoTranscurridoSegundos;

	}

	public static boolean validaRangoFechas(java.util.Date fechaActual,
			java.util.Date fechaFinal) {
		boolean fechasOk = true;
		java.util.Date d = fechaActual;// fecha actual
		java.util.Date h = fechaFinal;// fecha a comparar

		long fechaInicial = d.getTime();
		long fechaFin = h.getTime();

		if ((fechaInicial < 0 || fechaFin < 0) || d.getTime() < h.getTime()) {
			fechasOk = false;
		} else {
			fechasOk = true;
		}// fin else

		return fechasOk;

	}

	/**
	 * Este método valida si una fecha es menor o igual a otra
	 */
	public static boolean comparaFechas(java.util.Date fechaInicio,
			java.util.Date fechaFinal) {
		boolean fechaMenor = true;
		java.util.Date fecha1 = fechaInicio;// fecha actual
		java.util.Date fecha2 = fechaFinal;// fecha a comparar

		long fechaIni = fecha1.getTime();
		long fechaFin = fecha2.getTime();

		if (fechaIni < fechaFin) {
			fechaMenor = true;
		} else {
			fechaMenor = false;
		}// fin else

		return fechaMenor;
	}

	/**
	 * Este método valida si la fechaInicio es menor o igual a la fechaFinal
	 * ingresadas por parámetro.
	 */
	public static boolean compararSiFechaMenorIgual(java.util.Date fechaInicio,
			java.util.Date fechaFinal) {
		boolean fechaMenor = true;
		java.util.Date fecha1 = fechaInicio;// fecha actual
		java.util.Date fecha2 = fechaFinal;// fecha a comparar

		long fechaIni = fecha1.getTime();
		long fechaFin = fecha2.getTime();

		if (fechaIni <= fechaFin) {
			fechaMenor = true;
		} else {
			fechaMenor = false;
		}// fin else

		return fechaMenor;
	}

	/**
	 * Este método valida si la fechaInicio es menor a la fechaFinal ingresadas
	 * por parámetro.
	 */
	public static boolean compararSiFechaMenor(java.util.Date fechaInicio,
			java.util.Date fechaFinal) {
		boolean fechaMenor = true;
		java.util.Date fecha1 = fechaInicio;// fecha actual
		java.util.Date fecha2 = fechaFinal;// fecha a comparar

		long fechaIni = fecha1.getTime();
		long fechaFin = fecha2.getTime();

		if (fechaIni < fechaFin) {
			fechaMenor = true;
		} else {
			fechaMenor = false;
		}// fin else

		return fechaMenor;
	}

	/**
	 * Este método valida si la fechaInicio es menor a la fechaFinal ingresadas
	 * por parámetro.
	 */
	public static boolean compararIgualdadFechasSinHoras(
			java.util.Date fechaInicio, java.util.Date fechaFinal) {
		boolean fechaMenor = true;
		String diaIncio = ManipulacionFechas.getDia(fechaInicio);
		String diaFin = ManipulacionFechas.getDia(fechaFinal);
		String mesIncio = ManipulacionFechas.getMes(fechaInicio);
		String mesFin = ManipulacionFechas.getMes(fechaFinal);
		String anoIncio = ManipulacionFechas.getAgno(fechaInicio);
		String anoFin = ManipulacionFechas.getAgno(fechaFinal);

		if (diaIncio.equals(diaFin) && mesIncio.equals(mesFin)
				&& anoIncio.equals(anoFin)) {
			fechaMenor = true;
		} else {
			fechaMenor = false;
		}// fin else

		return fechaMenor;
	}

	/**
	 * Este método valida si la fechaInicio es menor a la fechaFinal ingresadas
	 * por parámetro.
	 */
	@SuppressWarnings("deprecation")
	public static boolean compararFechasSinHoras(java.util.Date fechaInicio,
			java.util.Date fechaFinal) {
		boolean fechaMenor = true;

		String mesIncio = ManipulacionFechas.getMes(fechaInicio);
		String mesFin = ManipulacionFechas.getMes(fechaFinal);
		String anoIncio = ManipulacionFechas.getAgno(fechaInicio);
		String anoFin = ManipulacionFechas.getAgno(fechaFinal);

		int diaIntInicio = fechaInicio.getDay();
		int diaIntFin = fechaFinal.getDay();

		if (diaIntInicio < diaIntFin && mesIncio.equals(mesFin)
				&& anoIncio.equals(anoFin)) {
			fechaMenor = true;
		} else {
			fechaMenor = false;
		}// fin else

		return fechaMenor;
	}

	/**
	 * Convierte a string la fecha correspondiente al parametro fecha en el
	 * formato especificado por el parametro formato Si la fecha es null
	 * devuelve una cadena vacia.
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return String correspondiente a la fecha
	 */
	public static String dateToStringConFormato(java.util.Date fecha,
			String formato) {
		String fechaString = "";
		if (fecha != null) {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(formato);
			fechaString = new String(formatoFecha.format(fecha));
		}

		return fechaString;

	}

	/**
	 * Adiciona los días especificados a la fecha indicada.
	 * 
	 * @param fechaInicial
	 *            java.util.Date
	 * @param numeroDias
	 *            int
	 * @return java.util.Date
	 */
	public static java.util.Date adicionarDias(java.util.Date fechaInicial,
			int numeroDias) {
		java.util.Date fechaFinal = null;
		long fechaInicialMiliseg;
		long diasMiliseg;
		long fechaFinalMiliseg;
		if (fechaInicial != null) {
			fechaInicialMiliseg = fechaInicial.getTime();
			diasMiliseg = numeroDias * 86400000L; // 86.400.000 = 24horas *
			// 60min * 60seg *
			// 1000milseg
			fechaFinalMiliseg = fechaInicialMiliseg + diasMiliseg;
			fechaFinal = new java.util.Date(fechaFinalMiliseg);
		}
		return fechaFinal;
	}

	/**
	 * Resta el número de días especificado a la fecha proporcionada.
	 * 
	 * @param fechaInicial
	 *            java.util.Date
	 * @param numeroDias
	 *            int
	 * @return java.util.Date
	 */
	public static java.util.Date restarDias(java.util.Date fechaInicial,
			int numeroDias) {
		java.util.Date fechaFinal = null;
		long fechaInicialMiliseg;
		long diasMiliseg;
		long fechaFinalMiliseg;
		if (fechaInicial != null) {
			fechaInicialMiliseg = fechaInicial.getTime();
			diasMiliseg = numeroDias * 86400000L; // 86.400.000 = 24horas *
			// 60min * 60seg *
			// 1000milseg
			fechaFinalMiliseg = fechaInicialMiliseg - diasMiliseg;
			fechaFinal = new java.util.Date(fechaFinalMiliseg);
		}
		return fechaFinal;
	}

	/**
	 * Este método retorna la fecha actual del sistema
	 * 
	 * @return java.util.Date date.
	 */
	public static java.util.Date getFechaActual() {
		return fechaActual;
	}

	/**
	 * Retorna una cadena de texto con la sentencia TO_DATE de sql
	 * correspondiente al campo que entra como parametro
	 * 
	 * @param nombreCampo
	 *            java.lang.String con el nombre del campo de la tabla, o nombre
	 *            de la variable o simbolo.
	 * @return java.lang.String con el siguiente texto: TO_DATE( nombreCampo,
	 *         'DD/MM/YYYY HH24:MI:SS')
	 */
	public static String applyToDate(String nombreCampo) {
		return "TO_DATE( '" + nombreCampo + "', 'DD/MM/YYYY HH24:MI:SS')";
	}

	/**
	 * Retorna la edad a partir de la fecha de nacimiento
	 * 
	 * @param fechaNacimiento
	 * @return
	 */
	public static int calcularEdad(Date fechaNacimiento) {

		String anoNac = ManipulacionFechas.getAgno(fechaNacimiento);
		String mesNac = ManipulacionFechas.getMes(fechaNacimiento);
		String diaNac = ManipulacionFechas.getDia(fechaNacimiento);
		String fecNac = anoNac + mesNac + diaNac;
		int intFecNac = Integer.parseInt(fecNac);
		// System.out.println("fec nac: " + intFecNac);

		Date fecActual = new Date();
		String anoAct = ManipulacionFechas.getAgno(fecActual);
		String mesAct = ManipulacionFechas.getMes(fecActual);
		String diaAct = ManipulacionFechas.getDia(fecActual);
		String fecAct = anoAct + mesAct + diaAct;
		int intFecAct = Integer.parseInt(fecAct);
		// System.out.println("fec act: " + intFecAct);

		int difFecha = (intFecAct - intFecNac) / 10000;
		// System.out.println("anos : " + difFecha);

		return difFecha;
	}

	/**
	 * Retorna la edad a partir de la fecha de nacimiento
	 * 
	 * @param fechaNacimiento
	 * @return
	 */
	public static int calcularEdad(Date fechaNacimiento, Date fechaReferencia) {
		// Se convierte la fecha de nacimiento
		String anoNac = ManipulacionFechas.getAgno(fechaNacimiento);
		String mesNac = ManipulacionFechas.getMes(fechaNacimiento);
		String diaNac = ManipulacionFechas.getDia(fechaNacimiento);
		String fecNac = anoNac + mesNac + diaNac;
		int intFecNac = Integer.parseInt(fecNac);
		// Se convierte la fecha de referencia
		String anoAct = ManipulacionFechas.getAgno(fechaReferencia);
		String mesAct = ManipulacionFechas.getMes(fechaReferencia);
		String diaAct = ManipulacionFechas.getDia(fechaReferencia);
		String fecRef = anoAct + mesAct + diaAct;
		int intFecRef = Integer.parseInt(fecRef);
		// Se calcula la edad con base en las fechas ingresadas
		int difFecha = (intFecRef - intFecNac) / 10000;
		return difFecha;
	}

	/**
	 * Convierte una fecha de tipo yyyymmdd (eje. 20061202) a Date Este tipo de
	 * fchas vienen desde el As400 de la splaicaciones Taylor
	 * 
	 * @param fecha :
	 *            fecha tipo yyyymmdd
	 * @return Date
	 */
	public static Date intToDate(String fecha) {
		Date fechaDate = null;
		if (fecha != null) {
			String agno = fecha.substring(0, 4);
			String mes = fecha.substring(4, 6);
			String dia = fecha.substring(6, 8);
			StringBuffer fechaIngresoB = new StringBuffer();
			fechaIngresoB.append(dia + "/" + mes + "/" + agno + " 00:00:00");
			fechaDate = ManipulacionFechas.stringToDate(fechaIngresoB
					.toString());
		}
		return fechaDate;
	}

	/**
	 * Resta a la fecha inicial el numero de dias ingresado
	 * 
	 * @author Carlos Alberto Polo Vasquez -Geniar Architect S.A.
	 */
	public static Date restarNumeroDias(Date fechaInicial, int numeroDias) {
		Date fechaFinal = null;
		long fechaInicialMiliseg;
		long diasMiliseg;
		long fechaFinalMiliseg;
		if (fechaInicial != null) {
			fechaInicialMiliseg = fechaInicial.getTime();
			diasMiliseg = numeroDias * 86400000L; // 86.400.000 = 24horas *
			// 60min * 60seg *
			// 1000milseg

			fechaFinalMiliseg = fechaInicialMiliseg - diasMiliseg;
			fechaFinal = new java.util.Date(fechaFinalMiliseg);
		}
		return fechaFinal;
	}

	/**
	 * Convierte una fecha de tipo Date a tipo yyyymmdd (eje. 20061202)
	 * 
	 * @param fecha :
	 *            fecha tipo yyyymmdd
	 * @return Date
	 */
	public static int DateToInt(Date fecha) {
		int fechaDate = 0;
		if (fecha != null) {
			String agno = formatoAgno.format(fecha);
			String mes = formatoMes.format(fecha);
			String dia = formatoDia.format(fecha);
			fechaDate = Integer.parseInt(agno + mes + dia);
		}
		return fechaDate;
	}

	/**
	 * Valida que el año de la fecha ingresada este en el rango valido.
	 * 
	 * @param dateTemp
	 *            Fecha a la cual se le valida el año.
	 * @throws GWorkException
	 *             Manejador de Excepciones.
	 */
	public static void validarAnhoFecha(Date dateTemp, String errorMSG)
			throws GWorkException {
		String anho = ManipulacionFechas.getAgno(dateTemp);

		if (anho.equals("") || Integer.parseInt(anho) < 1969 || anho.equals("")
				|| Integer.parseInt(anho) > 2025) {
			if (errorMSG.trim().length() == 0) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.ANHOINCORRECTO"));
			} else
				throw new GWorkException(Util.loadErrorMessageValue(errorMSG));
		}
	}

	public static int retornarDiasAño(int nuevoAgno) {
		int numeroDias = 0;
		boolean esBisiesto = false;
		if ((nuevoAgno % 4 == 0) && (nuevoAgno % 100 != 0)
				|| (nuevoAgno % 400 == 0))
			esBisiesto = true;

		numeroDias = ((esBisiesto) ? 366 : 365);

		return numeroDias;
	}

	/**
	 * Retorna la fecha con el mes anterior al mes que se le envie por parametro
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.util.Date
	 */
	public static Date getMesAnterior(java.util.Date dtFechaActual) {
		Date mesAnterior = null;
		if (dtFechaActual != null) {
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(dtFechaActual);
			calendario.set(Calendar.MONTH, mes - 2);
			mesAnterior = calendario.getTime();

			return mesAnterior;
		}
		return mesAnterior;
	}

	/**
	 * Retorna el nombre completo del mes correspondiente a la fecha
	 * 
	 * @param fecha
	 *            java.util.Date
	 * @return java.lang.String
	 */
	public static String getNombreMesCompleto(java.util.Date fecha) {
		if (fecha != null) {
			String mesString = formatoMes.format(fecha);
			int mes = Integer.parseInt(mesString);
			boolean encontro = false;
			for (int i = 0; i < mesesCompletos.length && !encontro; i++) {
				if (i == mes - 1) {
					encontro = true;
					mesString = mesesCompletos[i];
				}
			}
			return mesString;
		} else {
			return "";
		}
	}

	public static Date getPrimerDiaAnho() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date fecha = null;
		ParametersUtil parametersUtil = new ParametersUtil();
		parametersUtil.fechasConstantes = ParametersUtil.UtilidadesFechasConstantes.PRIMER_DIA_MES;
		String fechaCadena = parametersUtil.fechasConstantes.getDia() + "/"
				+ parametersUtil.fechasConstantes.getMes() + "/"
				+ getAgno(new Date());
		try {
			fecha = dateFormat.parse(fechaCadena);
			return fecha;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
