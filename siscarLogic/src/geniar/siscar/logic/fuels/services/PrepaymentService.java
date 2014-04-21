package geniar.siscar.logic.fuels.services;

import java.util.Date;
import java.util.List;

import geniar.siscar.model.CostCentersFuel;
import geniar.siscar.model.Prepaid;
import gwork.exception.GWorkException;

/**
 * 
 * This class contains all services that allows to handle {@link Prepaid}s.
 * 
 * @project SISCAR
 * @module Combustibles
 * @author mauricio.cuenca
 * @company Geniar Intelligence
 * @since 20/08/2008
 */
public interface PrepaymentService {

	/**
	 * Guarda un nuevo prepago.
	 * 
	 * @param fechaIni
	 *            Fecha en la que se compró el prepago.
	 * @param lstCCF
	 *            listado de centros de costo asociados.
	 * @param prePlaca
	 *            placa del vehiculo del prepago.
	 * @param carneAsignatario
	 *            Asignatario registrado al prepago.
	 * @param preTotal
	 *            Total de prepago comprado.
	 * @throws GWorkException
	 *             Manejador de excepciones.
	 */
	public void comprarPrepago(Date fechaIni, List<CostCentersFuel> lstCCF,
			String prePlaca, String carneAsignatario, String login)
			throws GWorkException;

	/**
	 * Consulta el promedio de consumos de combustible de un vehiculos
	 * 
	 * @param placa
	 *            Placa del vehiculo.
	 * @return promedio de los consumos.
	 * @throws GWorkException
	 *             Manejador de Excepciones
	 */
	public float obtenerPromedio(String placa) throws GWorkException;

}
