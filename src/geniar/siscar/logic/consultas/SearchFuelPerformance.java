package geniar.siscar.logic.consultas;

import geniar.siscar.model.FuelPerformance;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchFuelPerformance {
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	/**
	 * Consulta un registro especifico con los parametros indicados.
	 * @param lgtCodigo identificador del tipo de asignatario.
	 * @param lctCodigo identificador del tipo de ubicación.
	 * @param pltCodigo identificador del tipo de seguro. 
	 * @return objeto ControlAssignationPolicies.
	 * @throws GWorkException
	 */
	@SuppressWarnings("unchecked")
	public FuelPerformance consultarFuelPerformance(
			Long idLine, Long idTractionsTypes, Long idFuelsTypes) throws GWorkException {
		try {
			final String queryString ="select fp from FuelPerformance fp " +
					"where fp.lines.lnsId="+ idLine +
					 " AND fp.fuelsTypes.futCodigo="+idFuelsTypes+ "" +
					 " AND fp.tractionsTypes.tctCodigo="+idTractionsTypes;
			Query query = getEntityManager().createQuery(queryString);

			List<FuelPerformance> list = query.getResultList();
			if (list.size()>0) {
				return (FuelPerformance) list.get(0);				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.CAPNOENCONTRAD"));
		}
	}
	
	/**
	 * Determina si la llave Linea-TipoTraccion-TipoCombustible existe. 
	 * @param idLine Identificador de la Linea.
	 * @param idTractionsTypes Identificador del Tipo de traccion.
	 * @param idFuelsTypes Identificador de Tipo de Combustibles.
	 * @return boolean.
	 * @throws GWorkException Manejador de excepciones.
	 */
	public boolean consultarLlaveLineaTipoTraccionTipoCombustible(
			Long idLine, Long idTractionsTypes, Long idFuelsTypes) throws GWorkException {
		try {
			final String queryString ="select model from FuelPerformance model " +
					"where model.lines.lnsId="+ idLine +
					 " AND model.fuelsTypes.futCodigo="+idFuelsTypes+ "" +
					 " AND model.tractionsTypes.tctCodigo="+idTractionsTypes;
			Query query = getEntityManager().createQuery(queryString);
			
			if (query.getResultList().size() == 1) {
				return true;				
			}else 
				return false;
			
			
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw new GWorkException(Util.loadErrorMessageValue("ERROR.GUARDAR"));
		}
	}
}
