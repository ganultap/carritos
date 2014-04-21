/*
 * Created on 12/01/2009
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ciat.siscar.mobile;

import java.util.Date;
import java.util.regex.*;

import ciat.siscar.mobile.constants.CargoA;
import ciat.siscar.mobile.constants.TipoAsignacion;
import ciat.siscar.mobile.constants.TipoVehiculo;
import ciat.siscar.mobile.constants.UsoDisponible;
import ciat.siscar.mobile.services.SiscarException;

/**
 * @author Jaime Chavarriaga
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SiscarData {

	static String login;
	static String usuario;

	static Date fecha;

	static String carnetSolicitante;
	static String nombreSolicitante;

	static String cantidadGalones;
	static int cargoA;
	static String carnetCargoA;
	static String nombreCargoA;
	static String centroCosto;
	static String centroCostoCombustible;

	static String[] centroCostoCombustiblePrepago;

	static String valorPrepago;
	static String kmActual;
	static String numeroRecibo;
	static String observaciones;
	static String placa;
	static int tipoVehiculo;
	static int tipoTarifa;
	static int usoDisponible;
	static String tarifa;
	static int idSurtidor;
	static int tipoCobro;

	static String carnetAsignatario;
	static String nombreAsignatario;
	static String tipoAsignacion;
	static int idTipoAsignacion;
	static String capacidadTanque;
	static int tipoCombustible;
	static Long ultimoKilometraje;
	static String valorTanqueada;
	static String valorTotal;
	static String disponiblePrepago;
	static String disponibleGalones;

	/**
	 * @return
	 */
	public static String getCantidadGalones() {
		return cantidadGalones;
	}

	/**
	 * @return
	 */
	public static String getCapacidadTanque() {
		return capacidadTanque;
	}

	/**
	 * @return
	 */
	public static int getCargoA() {
		return cargoA;
	}

	/**
	 * @return
	 */
	public static String getCarnetAsignatario() {
		return carnetAsignatario;
	}

	/**
	 * @return
	 */
	public static String getCarnetSolicitante() {
		return carnetSolicitante;
	}

	/**
	 * @return
	 */
	public static String getCentroCosto() {
		return centroCosto;
	}

	/**
	 * @return
	 */
	public static String getKmActual() {
		return kmActual;
	}

	/**
	 * @return
	 */
	public static String getLogin() {
		return login;
	}

	/**
	 * @return
	 */
	public static String getNombreAsignatario() {
		return nombreAsignatario;
	}

	/**
	 * @return
	 */
	public static String getNumeroRecibo() {
		return numeroRecibo;
	}

	/**
	 * @return
	 */
	public static String getObservaciones() {
		return observaciones;
	}

	/**
	 * @return
	 */
	public static String getPlaca() {
		return placa;
	}

	/**
	 * @return
	 */
	public static int getTipoCombustible() {
		return tipoCombustible;
	}

	/**
	 * @return
	 */
	public static int getTipoVehiculo() {
		return tipoVehiculo;
	}

	/**
	 * @return
	 */
	public static Long getUltimoKilometraje() {
		return ultimoKilometraje;
	}

	/**
	 * @return
	 */
	public static int getUsoDisponible() {
		return usoDisponible;
	}

	/**
	 * @return
	 */
	public static String getUsuario() {
		return usuario;
	}

	/**
	 * @return
	 */
	public static String getValorTanqueada() {
		return valorTanqueada;
	}

	/**
	 * @param string
	 */
	public static void setCantidadGalones(String string) {
		cantidadGalones = string;
	}

	/**
	 * @param string
	 */
	public static void setCapacidadTanque(String string) {
		capacidadTanque = string;
	}

	/**
	 * @param i
	 */
	public static void setCargoA(int i) {
		cargoA = i;
	}

	/**
	 * @param string
	 */
	public static void setCarnetSolicitante(String string) {
		carnetSolicitante = string;
	}

	/**
	 * @param string
	 */
	public static void setCentroCosto(String string) {
		centroCosto = string;
	}

	/**
	 * @param string
	 */
	public static void setKmActual(String string) {
		kmActual = string;
	}

	/**
	 * @param string
	 */
	public static void setLogin(String string) {
		login = string;
	}

	/**
	 * @param string
	 */
	public static void setNombreAsignatario(String string) {
		nombreAsignatario = string;
	}

	/**
	 * @param string
	 */
	public static void setNumeroRecibo(String string) {
		numeroRecibo = string;
	}

	/**
	 * @param string
	 */
	public static void setObservaciones(String string) {
		observaciones = string;
	}

	/**
	 * @param string
	 */
	public static void setPlaca(String string) {
		placa = string;
	}

	/**
	 * @param i
	 */
	public static void setTipoCombustible(int i) {
		tipoCombustible = i;
	}

	/**
	 * @param i
	 */
	public static void setTipoVehiculo(int i) {
		tipoVehiculo = i;
	}

	/**
	 * @param string
	 */
	public static void setUltimoKilometraje(Long string) {
		ultimoKilometraje = string;
	}

	/**
	 * @param i
	 */
	public static void setUsoDisponible(int i) {
		usoDisponible = i;
	}

	/**
	 * @param string
	 */
	public static void setUsuario(String string) {
		usuario = string;
	}

	/**
	 * @param string
	 */
	public static void setValorTanqueada(String string) {
		valorTanqueada = string;
	}

	/**
	 * @return
	 */
	public static Date getFecha() {
		return fecha;
	}

	/**
	 * @param date
	 */
	public static void setFecha(Date date) {
		fecha = date;
	}

	/**
	 * @return
	 */
	public static String getNombreSolicitante() {
		return nombreSolicitante;
	}

	/**
	 * @param string
	 */
	public static void setNombreSolicitante(String string) {
		nombreSolicitante = string;
	}

	/**
	 * @return
	 */
	public static String getTipoAsignacion() {
		return tipoAsignacion;
	}

	/**
	 * @param string
	 */
	public static void setTipoAsignacion(String string) {
		tipoAsignacion = string;
	}

	// --

	public static void dumpModel() {

		System.out.println("cargo A : " + SiscarData.getCargoA());
		System.out.println(
			"carnet Solicitante : " + SiscarData.getCarnetSolicitante());
		System.out.println("centro costo : " + SiscarData.getCentroCosto());
		System.out.println(
			"nombre solicitante : " + SiscarData.getNombreSolicitante());
		System.out.println("tipo vehiculo : " + SiscarData.getTipoVehiculo());
		System.out.println("numero recibo : " + SiscarData.getNumeroRecibo());
		System.out.println("Id surtidor : " + SiscarData.getIdSurtidor());

		System.out.println("placa :" + SiscarData.getPlaca());
		System.out.println(
			"carnet asignatario" + SiscarData.getCarnetAsignatario());
		System.out.println(
			"nombre asignatario : " + SiscarData.getNombreAsignatario());
		System.out.println(
			"tipo asignacion : " + SiscarData.getTipoAsignacion());
		System.out.println("ultimo km : " + SiscarData.getUltimoKilometraje());
		System.out.println("km actual : " + SiscarData.getKmActual());

		System.out.println(
			"capacidad tanque : " + SiscarData.getCapacidadTanque());
		System.out.println("galones : " + SiscarData.getCantidadGalones());
		System.out.println(
			"tipo combustible : " + SiscarData.getTipoCombustible());
		System.out.println("uso disponible : " + SiscarData.getUsoDisponible());
		System.out.println(
			"valor tanqueada : " + SiscarData.getValorTanqueada());
		System.out.println("valor total : " + SiscarData.getValorTotal());

		System.out.println("observaciones : " + SiscarData.getObservaciones());

	}

	public static void save() throws Exception, SiscarException {
		Long numeroRecibo = null;
		String centroCosto = null;
		Long kmActual = null;
		Long ultimoKilometraje = null;
		Float capacidadTanque = null;
		Float valorTanqueada = null;
		Float disponiblePre = null;
		String mensaje = "";

		if (getTipoVehiculo() == -1)
			throw new SiscarException("No se ha seleccionado el tipo de vehiculo");

		if (SiscarData.getTipoVehiculo() == TipoVehiculo.OTROS 
				&& (SiscarData.getNumeroRecibo() == null || SiscarData.getNumeroRecibo().trim().length() == 0)) {
			throw new SiscarException("No se ha ingresado el número del recibo");
		}
		
		if (getCargoA() == CargoA.CARNET
				&& SiscarData.getTipoVehiculo() == TipoVehiculo.MEMO
				&& (placa.trim() == "" || placa.trim().length() == 0))
				throw new SiscarException("Debe ingresar una placa para MEMO");
		
		if (getCargoA() == CargoA.CARNET
				&& SiscarData.getTipoVehiculo() == TipoVehiculo.MEMO
				&& getTipoCombustible() == 0)
				throw new SiscarException("Debe seleccionar el tipo de combustible para MEMO");
		
		if (getCargoA() == -1
			&& SiscarData.getTipoVehiculo() != TipoVehiculo.OTROS)
			throw new SiscarException("No se ha seleccionado a quien se piensa cargar el consumo");

		if (getTipoCombustible() == -1)
			throw new SiscarException("No se ha seleccionado tipo de combustible");

		if (SiscarData.getValorTotal() == null
			|| SiscarData.getValorTotal() == "") {
			throw new SiscarException("Aún no ha calculado el valor de la compra");
		}

		if (SiscarData.getCargoA() != CargoA.CARNET) {
			if (SiscarData.disponiblePrepago != null
				&& SiscarData.disponiblePrepago != "") {
				valorTanqueada = new Float(SiscarData.getValorTotal());
				if(SiscarData.getUsoDisponible() == UsoDisponible.PREPAGO){									
					disponiblePre = new Float(SiscarData.disponiblePrepago);
	
					if (valorTanqueada.floatValue() > disponiblePre.floatValue())
						throw new SiscarException("El valor de la compra es mayor al disponible prepago");
				}
			}else{
				if (SiscarData.getCargoA() == CargoA.CENTRO_COSTOS && SiscarData.disponiblePrepago == "" && SiscarData.getUsoDisponible() == UsoDisponible.PREPAGO) {
					throw new SiscarException("El valor disponible de prepago es cero");
				}
			}
			
			
			
		}
	
		/*
		 * Se valida para asegurarnos que en caso de que sea terceros y parque
		 * cientifico el usu disponible sea PRESUPUESTO
		 */
		if (SiscarData.getCargoA() == CargoA.TERCEROS
			&& SiscarData.getTipoVehiculo() == TipoVehiculo.PARQUECIENTIFICO) {
			SiscarData.setUsoDisponible(UsoDisponible.PRESUPUESTO); //
		}

		// if (getIdSurtidor() == -1)
		// throw new SiscarException("No se ha seleccionado surtidor");

		if (SiscarData.getTipoVehiculo() == TipoVehiculo.VEHICULO_CIAT
			&& getPlaca() != null
			&& getPlaca().trim().length() == 0)
			throw new SiscarException("No se ha ingresado la placa");

		try {

			if (SiscarData.getNumeroRecibo() != null
				&& SiscarData.getNumeroRecibo().trim().length() > 0)
				numeroRecibo = new Long(SiscarData.getNumeroRecibo());

			if (SiscarData.getCentroCosto() != null
				&& SiscarData.getCentroCosto().trim().length() > 0)
				centroCosto = SiscarData.getCentroCosto();
			else if (
				(SiscarData.getCentroCosto() == null
					|| SiscarData.getCentroCosto().trim().length() == 0)
					&& SiscarData.getCentroCostoCombustible() != null
					&& SiscarData.getCentroCostoCombustible().trim().length()
						> 0) {
				centroCosto = SiscarData.getCentroCostoCombustible();
			}
			// nos aseguramos de que llegue el cargo a cuando tengo un centro de
			// costo y el tipo de asignacion no es convenio
			if (centroCosto != null
				&& SiscarData.getTipoAsignacion() != TipoAsignacion.CONVENIO) {
				//cuando se selecciono carnet no se le debe asignar el cargo a de un centro de costos
				if(SiscarData.getCargoA() != CargoA.CARNET){
					SiscarData.setCargoA(1);
				}
			}

			if (SiscarData.getCargoA() == 0) {
				throw new SiscarException("El cargo A no se ha seleccionado");
			}
			
			if ((SiscarData.getTipoCobro() == TipoVehiculo.VEHICULO_CIAT || SiscarData.getTipoVehiculo() == TipoVehiculo.VEHICULO_CIAT) && SiscarData.getPlaca() != null) {
				if (SiscarData.getKmActual() != null && SiscarData.getKmActual().trim().length() == 0 ) {
					throw new SiscarException("Debe ingresar el registro del kilometraje actual del vehículo");
				}
			}
			
			boolean cadenaRespuesta = isNumeric(SiscarData.getKmActual());
			
			if (cadenaRespuesta == false) {
				throw new SiscarException("No se pueden ingresar datos de tipo caracter o caracteres especiales en el campo Kilometraje actual");
			}
			
			if (SiscarData.getKmActual() != null
				&& SiscarData.getKmActual().trim().length() > 0) {
				kmActual = new Long(SiscarData.getKmActual());
												
				if (SiscarData.getUltimoKilometraje() != null
					&& SiscarData.getUltimoKilometraje().longValue() > 0) {
					
					ultimoKilometraje = SiscarData.getUltimoKilometraje();				
					if (kmActual.longValue() < ultimoKilometraje.longValue()) {
						throw new SiscarException("El kilometráje actual es menor al último kilomentraje");
					}
				}

			} else{
				kmActual = new Long(0);
				
				if (SiscarData.getUltimoKilometraje() != null
						&& SiscarData.getUltimoKilometraje().longValue() > 0) {
						
					ultimoKilometraje = SiscarData.getUltimoKilometraje();				
					if (kmActual.longValue() < ultimoKilometraje.longValue()) {
						throw new SiscarException("El kilometráje actual es menor al último kilomentraje");
					}
				}
			}
					
			if (SiscarData.getCapacidadTanque() != null
				&& SiscarData.getCapacidadTanque().trim().length() > 0)
				capacidadTanque = new Float(SiscarData.getCapacidadTanque());
			else
				capacidadTanque = new Float(0);

			// Long idFuelTypeRequest, String placa, String login,
			// String nombreSolicitante, String carneAsignatario, String
			// carneSolicitante, Float numeroGalones,
			// Float total, String observaciones, Long idPump, Long
			// kilometrajeActual, Long numeroReciboPago,
			// String NumeroCentroCosto, Long usoDisponible, Long fuelType,
			// Float capacidadTanque,Long cargoA

			SiscarService.getService().guardarServicioRegistro(
				new Long(SiscarData.getTipoVehiculo()),
				SiscarData.getPlaca(),
				SiscarData.getLogin(),
				SiscarData.getNombreSolicitante(),
				SiscarData.getCarnetAsignatario(),
				SiscarData.getCarnetSolicitante(),
				new Float(SiscarData.getCantidadGalones()),
				new Float(SiscarData.getValorTotal()),
				SiscarData.getObservaciones(),
				new Long(SiscarData.getIdSurtidor()),
				kmActual,
				numeroRecibo,
				centroCosto,
				new Long(SiscarData.getUsoDisponible()),
				new Long(SiscarData.getTipoCombustible()),
				capacidadTanque,
				new Long(SiscarData.getCargoA()));

			limpiarDatos();

		} catch (Exception e) {
			throw new SiscarException(e.getMessage());
		}

	}

	public static void limpiarDatos() {
		SiscarData.setIdSurtidor(-1);

		SiscarData.setCantidadGalones("");
		SiscarData.setCapacidadTanque("");
		SiscarData.setCargoA(-1);
		SiscarData.setCarnetAsignatario("");
		SiscarData.setCarnetCargoA("");
		SiscarData.setCarnetSolicitante("");
		SiscarData.setCentroCosto("");
		SiscarData.setCentroCostoCombustible("");
		SiscarData.setCentroCostoCombustiblePrepago(null);
		SiscarData.setDisponibleGalones("");
		SiscarData.setDisponiblePrepago("");
		SiscarData.setKmActual("");
		SiscarData.setNumeroRecibo("");
		SiscarData.setObservaciones("");
		SiscarData.setPlaca("");
		SiscarData.setTarifa("");
		SiscarData.setTipoAsignacion("");
		SiscarData.setTipoCobro(-1);
		SiscarData.setTipoCombustible(-1);
		SiscarData.setTipoVehiculo(-1);
		SiscarData.setValorPrepago("");
		SiscarData.setValorTanqueada("");
		SiscarData.setValorTotal("");
		SiscarData.setUltimoKilometraje(null);
	}
	
	public static void limpiarDatosConsultaPlaca() {
		SiscarData.setIdSurtidor(-1);

		SiscarData.setCantidadGalones("");
		SiscarData.setCapacidadTanque("");
		SiscarData.setCargoA(-1);
		SiscarData.setCarnetAsignatario("");
		SiscarData.setCarnetCargoA("");
		//SiscarData.setCarnetSolicitante("");
		SiscarData.setCentroCosto("");
		SiscarData.setCentroCostoCombustible("");
		SiscarData.setCentroCostoCombustiblePrepago(null);
		SiscarData.setDisponibleGalones("");
		SiscarData.setDisponiblePrepago("");
		SiscarData.setKmActual("");
		SiscarData.setNumeroRecibo("");
		SiscarData.setObservaciones("");
		SiscarData.setPlaca("");
		SiscarData.setTarifa("");
		SiscarData.setTipoAsignacion("");
		//SiscarData.setTipoCobro(-1);
		SiscarData.setTipoCombustible(-1);
		//SiscarData.setTipoVehiculo(-1);
		SiscarData.setValorPrepago("");
		SiscarData.setValorTanqueada("");
		SiscarData.setValorTotal("");
		SiscarData.setUltimoKilometraje(null);
	}

	/**
	 * @return
	 */
	public static String getCarnetCargoA() {
		return carnetCargoA;
	}

	/**
	 * @return
	 */
	public static String getNombreCargoA() {
		return nombreCargoA;
	}

	/**
	 * @param string
	 */
	public static void setCarnetAsignatario(String string) {
		carnetAsignatario = string;
	}

	/**
	 * @param string
	 */
	public static void setCarnetCargoA(String string) {
		carnetCargoA = string;
	}

	/**
	 * @param string
	 */
	public static void setNombreCargoA(String string) {
		nombreCargoA = string;
	}

	public static int getTipoTarifa() {
		return tipoTarifa;
	}

	public static void setTipoTarifa(int tipoTarifa) {
		SiscarData.tipoTarifa = tipoTarifa;
	}

	public static String getTarifa() {
		return tarifa;
	}

	public static void setTarifa(String tarifa) {
		SiscarData.tarifa = tarifa;
	}

	public static String getValorTotal() {
		return valorTotal;
	}

	public static void setValorTotal(String valorTotal) {
		SiscarData.valorTotal = valorTotal;
	}

	public static String getDisponiblePrepago() {
		return disponiblePrepago;
	}

	public static void setDisponiblePrepago(String disponiblePrepago) {
		SiscarData.disponiblePrepago = disponiblePrepago;
	}

	public static String getDisponibleGalones() {
		return disponibleGalones;
	}

	public static void setDisponibleGalones(String disponibleGalones) {
		SiscarData.disponibleGalones = disponibleGalones;
	}

	public static int getIdSurtidor() {
		return idSurtidor;
	}

	public static void setIdSurtidor(int idSurtidor) {
		SiscarData.idSurtidor = idSurtidor;
	}

	public static int getTipoCobro() {
		return tipoCobro;
	}

	public static void setTipoCobro(int tipoCobro) {
		SiscarData.tipoCobro = tipoCobro;
	}

	public static String getCentroCostoCombustible() {
		return centroCostoCombustible;
	}

	public static void setCentroCostoCombustible(String centroCostoCombustible) {
		SiscarData.centroCostoCombustible = centroCostoCombustible;
	}

	public static String getValorPrepago() {
		return valorPrepago;
	}

	public static void setValorPrepago(String valorPrepago) {
		SiscarData.valorPrepago = valorPrepago;
	}

	public static String[] getCentroCostoCombustiblePrepago() {
		return centroCostoCombustiblePrepago;
	}

	public static void setCentroCostoCombustiblePrepago(String[] centroCostoCombustiblePrepago) {
		SiscarData.centroCostoCombustiblePrepago =
			centroCostoCombustiblePrepago;
	}

	public static int getIdTipoAsignacion() {
		return idTipoAsignacion;
	}

	public static void setIdTipoAsignacion(int idTipoAsignacion) {
		SiscarData.idTipoAsignacion = idTipoAsignacion;
	}
	
	/**
	 * @author alvaro.hincapie
	 * 
	 * Permite validar si un campo tiene letras
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean validacionCaracteres1(String cadena) {
	    // comprueba que no contenga caracteres prohibidos		
		Pattern p = Pattern.compile("[^A-Za-z\\<\\>\\!\\?\\@_\\-~#]+");

	    Matcher m = p.matcher(cadena);
	      
	      boolean resultado = m.find();
	      boolean caracteresIlegales = false;
	      StringBuffer sb = new StringBuffer();

	      while(resultado) {	         
	         m.appendReplacement(sb, "");
	         resultado = m.find();
	      }

	      // Añade el ultimo segmento de la entrada a la cadena
	      m.appendTail(sb);

	      cadena = sb.toString();

	      System.out.println(cadena);
	      
	      if (cadena.trim().length() > 0) {
	    	  caracteresIlegales = true;
	         //System.out.println("La cadena contiene caracteres ilegales");
	      }else{
	    	  caracteresIlegales = false;
	      }
	      return caracteresIlegales;
	}

	public static boolean isNumeric(String cadena) {
		
		boolean isValid = true;

		// Loop through length of string and test for any alpha numeric
		// characters
		for (int i = 0; i < cadena.toCharArray().length; i++) {

			// Alphanumeric must be between "0"-"9", "A"-"Z", or "a"-"z"
			//System.out.println(cadena.charAt(i));
			
				if (!(cadena.charAt(i) == '0' || cadena.charAt(i) == '1'
						|| cadena.charAt(i) == '2' || cadena.charAt(i) == '3'
						|| cadena.charAt(i) == '4' || cadena.charAt(i) == '5'
						|| cadena.charAt(i) == '6' || cadena.charAt(i) == '7'
						|| cadena.charAt(i) == '8' || cadena.charAt(i) == '9'
						|| cadena.charAt(i) == '.')) {
					isValid = false;
					break;
				}
		} // END for
	   
	   	return isValid;

	}
	
	/**
	 * @author alvaro.hincapie
	 * 
	 * Permite validar si un campo es numerico y que pueda tener un guión
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean isNumericCarne(String cadena) {
		
		boolean isValid = true;

		// Loop through length of string and test for any alpha numeric
		// characters
		for (int i = 0; i < cadena.toCharArray().length; i++) {

			// Alphanumeric must be between "0"-"9", "A"-"Z", or "a"-"z"
			//System.out.println(cadena.charAt(i));
			
				if (!(cadena.charAt(i) == '0' || cadena.charAt(i) == '1'
						|| cadena.charAt(i) == '2' || cadena.charAt(i) == '3'
						|| cadena.charAt(i) == '4' || cadena.charAt(i) == '5'
						|| cadena.charAt(i) == '6' || cadena.charAt(i) == '7'
						|| cadena.charAt(i) == '8' || cadena.charAt(i) == '9'
						|| cadena.charAt(i) == '-')) {
					isValid = false;
					break;
				}
		} // END for
	   
		return isValid;

	}
	
	/**
	 * @author alvaro.hincapie
	 * 
	 * Permite validar si un campo es numerico y que pueda tener un punto para los decimales
	 * 
	 * @param cadena
	 * @return
	 */
	public static boolean isNumericGalones(String cadena) {
		
		boolean isValid = true;

		// Loop through length of string and test for any alpha numeric
		// characters
		for (int i = 0; i < cadena.toCharArray().length; i++) {

			//System.out.println(cadena.charAt(i));
			
				if (!(cadena.charAt(i) == '0' || cadena.charAt(i) == '1'
						|| cadena.charAt(i) == '2' || cadena.charAt(i) == '3'
						|| cadena.charAt(i) == '4' || cadena.charAt(i) == '5'
						|| cadena.charAt(i) == '6' || cadena.charAt(i) == '7'
						|| cadena.charAt(i) == '8' || cadena.charAt(i) == '9'
						|| cadena.charAt(i) == '.')) {
					isValid = false;
					break;
				}
		} // END for
	   
		return isValid;

	}

}
