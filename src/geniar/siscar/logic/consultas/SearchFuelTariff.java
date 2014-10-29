package geniar.siscar.logic.consultas;

import geniar.siscar.model.FuelsTypes;
import geniar.siscar.model.TariffsTypes;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SearchFuelTariff {
	
	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public FuelsTypes consultarFuelType( Long idFuelType ) throws GWorkException {
		try {
			final String queryString ="select ft from FuelsTypes ft " +
					"where ft.futCodigo="+ idFuelType;
			
			Query query = getEntityManager().createQuery(queryString);

			List<FuelsTypes> list = query.getResultList();
			if (list.size()>0) {
				return (FuelsTypes) list.get(0);				
			}else 
				return null;			
		} catch (RuntimeException re) {
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
	
	@SuppressWarnings("unchecked")
	public TariffsTypes consultarTariffsTypes( Long idTariffType, String tipoTarifa ) throws GWorkException {
		try {
			final String queryString ="select tt from TariffsTypes tt " +
			"where tt.trtId="+ idTariffType ;
			
			Query query = getEntityManager().createQuery(queryString);
			
			List<TariffsTypes> list = query.getResultList();
			if (list.size()>0) {
				return (TariffsTypes) list.get(0);				
			}else 
				return null;			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new GWorkException(Util.loadErrorMessageValue("search.not.found"));
		}
	}
}
