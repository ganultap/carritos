/**
 * 
 */
package geniar.siscar.logic.policies.services.impl;

/**
 * @author Geniar Intelligence
 * @version 1.0, 20/11/2008 Managed Bean de Novedades de polizas
 * 
 * Esta clase contiene las llaves de los tipos de inconsistencia.
 */
public class TiposInconsis {

	/** El vehiculo del archivo no aparece en la BD */
	private static Long VHC_NO_EXISTE_BD = 1L;

	/** El vehiculo aparece mas de una vez en la factura. */
	private static Long VHC_2_VECESPOL = 2L;

	/** Vehiculo inactivo. */
	private static Long VHC_INACTIVO = 3L;

	/** Vehiculo con dos tipos de polizas excluyentes. */
	private static Long VHC_DOBLE_POL = 4L;

	/** Asignación personal con poliza basica. */
	private static Long ASIG_PER_AMP_BASIC = 5L;

	/** Vehiculo fuera de SEDE con poliza basica. */
	private static Long VHC_SEDE_POL_BASIC = 6L;

	/** Vehiculo sin soat no ubicado en exteriores. */
	private static Long VHC_NOSOAT_EXTERIOR = 7L;

	/** Vehiculo no tiene el mismo numero de serial que el archivo */
	private static Long VHC_NUMERO_SERIAL_DIFERENTE = 8L;

	/** Vehiculo no tiene el mismo numero de MOTOR que el archivo */
	private static Long VHC_NUMERO_MOTOR_DIFERENTE = 9L;

	/** Vehiculo no tiene la misma MARCA que el archivo */
	private static Long VHC_MARCA_DIFERENTE = 10L;
	
	/** Vehiculo no tiene el mismo AÑO que el archivo */
	private static Long VHC_AÑO_DIFERENTE = 11L;

	/** Vehiculo diferente a la poliza que debe ir */
	private static Long VHC_POLIZA_DIFERENTE = 12L;
	/** Vehiculo diferente a la poliza que debe ir */
	private static Long VHC_ASIGNACION_VENCIDA = 13L;
	
	/**
	 * @return the vHC_NO_EXISTE_BD
	 */
	public static Long getVHC_NO_EXISTE_BD() {
		return VHC_NO_EXISTE_BD;
	}

	/**
	 * @param vhc_no_existe_bd
	 *            the vHC_NO_EXISTE_BD to set
	 */
	public static void setVHC_NO_EXISTE_BD(Long vhc_no_existe_bd) {
		VHC_NO_EXISTE_BD = vhc_no_existe_bd;
	}

	/**
	 * @return the vHC_2_VECESPOL
	 */
	public static Long getVHC_2_VECESPOL() {
		return VHC_2_VECESPOL;
	}

	/**
	 * @param vhc_2_vecespol
	 *            the vHC_2_VECESPOL to set
	 */
	public static void setVHC_2_VECESPOL(Long vhc_2_vecespol) {
		VHC_2_VECESPOL = vhc_2_vecespol;
	}

	/**
	 * @return the vHC_INACTIVO
	 */
	public static Long getVHC_INACTIVO() {
		return VHC_INACTIVO;
	}

	/**
	 * @param vhc_inactivo
	 *            the vHC_INACTIVO to set
	 */
	public static void setVHC_INACTIVO(Long vhc_inactivo) {
		VHC_INACTIVO = vhc_inactivo;
	}

	/**
	 * @return the vHC_DOBLE_POL
	 */
	public static Long getVHC_DOBLE_POL() {
		return VHC_DOBLE_POL;
	}

	/**
	 * @param vhc_doble_pol
	 *            the vHC_DOBLE_POL to set
	 */
	public static void setVHC_DOBLE_POL(Long vhc_doble_pol) {
		VHC_DOBLE_POL = vhc_doble_pol;
	}

	/**
	 * @return the aSIG_PER_AMP_BASIC
	 */
	public static Long getASIG_PER_AMP_BASIC() {
		return ASIG_PER_AMP_BASIC;
	}

	/**
	 * @param asig_per_amp_basic
	 *            the aSIG_PER_AMP_BASIC to set
	 */
	public static void setASIG_PER_AMP_BASIC(Long asig_per_amp_basic) {
		ASIG_PER_AMP_BASIC = asig_per_amp_basic;
	}

	/**
	 * @return the vHC_SEDE_POL_BASIC
	 */
	public static Long getVHC_SEDE_POL_BASIC() {
		return VHC_SEDE_POL_BASIC;
	}

	/**
	 * @param vhc_sede_pol_basic
	 *            the vHC_SEDE_POL_BASIC to set
	 */
	public static void setVHC_SEDE_POL_BASIC(Long vhc_sede_pol_basic) {
		VHC_SEDE_POL_BASIC = vhc_sede_pol_basic;
	}

	/**
	 * @return the vHC_NOSOAT_EXTERIOR
	 */
	public static Long getVHC_NOSOAT_EXTERIOR() {
		return VHC_NOSOAT_EXTERIOR;
	}

	/**
	 * @param vhc_nosoat_exterior
	 *            the vHC_NOSOAT_EXTERIOR to set
	 */
	public static void setVHC_NOSOAT_EXTERIOR(Long vhc_nosoat_exterior) {
		VHC_NOSOAT_EXTERIOR = vhc_nosoat_exterior;
	}

	public static Long getVHC_NUMERO_SERIAL_DIFERENTE() {
		return VHC_NUMERO_SERIAL_DIFERENTE;
	}

	public static void setVHC_NUMERO_SERIAL_DIFERENTE(
			Long vhc_numero_serial_diferente) {
		VHC_NUMERO_SERIAL_DIFERENTE = vhc_numero_serial_diferente;
	}

	public static Long getVHC_NUMERO_MOTOR_DIFERENTE() {
		return VHC_NUMERO_MOTOR_DIFERENTE;
	}

	public static void setVHC_NUMERO_MOTOR_DIFERENTE(
			Long vhc_numero_motor_diferente) {
		VHC_NUMERO_MOTOR_DIFERENTE = vhc_numero_motor_diferente;
	}

	public static Long getVHC_MARCA_DIFERENTE() {
		return VHC_MARCA_DIFERENTE;
	}

	public static void setVHC_MARCA_DIFERENTE(Long vhc_marca_diferente) {
		VHC_MARCA_DIFERENTE = vhc_marca_diferente;
	}

	public static Long getVHC_AÑO_DIFERENTE() {
		return VHC_AÑO_DIFERENTE;
	}

	public static void setVHC_AÑO_DIFERENTE(Long vhc_año_diferente) {
		VHC_AÑO_DIFERENTE = vhc_año_diferente;
	}

	public static Long getVHC_POLIZA_DIFERENTE() {
		return VHC_POLIZA_DIFERENTE;
	}

	public static void setVHC_POLIZA_DIFERENTE(Long vhc_poliza_diferente) {
		VHC_POLIZA_DIFERENTE = vhc_poliza_diferente;
	}

	public static Long getVHC_ASIGNACION_VENCIDA() {
		return VHC_ASIGNACION_VENCIDA;
	}

	public static void setVHC_ASIGNACION_VENCIDA(Long vhc_asignacion_vencida) {
		VHC_ASIGNACION_VENCIDA = vhc_asignacion_vencida;
	}
}
