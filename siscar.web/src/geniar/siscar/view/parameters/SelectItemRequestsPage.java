package geniar.siscar.view.parameters;

import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.vehicle.services.RequestService;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.RequestsClasses;
import geniar.siscar.model.RequestsStates;
import geniar.siscar.model.RequestsTypes;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class SelectItemRequestsPage.
 */
public class SelectItemRequestsPage {

	/** The request service. */
	private RequestService requestService;
	
	/** The list requests classes. */
	private SelectItem[] listRequestsClasses;
	
	/** The list requests types. */
	private SelectItem[] listRequestsTypes;
	
	/** The list requests states. */
	private SelectItem[] listRequestsStates;
	
	/** The list legatees types. */
	private SelectItem[] listLegateesTypes;
	
	/** The list util requests classes. */
	private List<RequestsClasses> listUtilRequestsClasses;
	
	/** The list util requests types. */
	private List<RequestsTypes> listUtilRequestsTypes;
	
	/** The list util requests states. */
	private List<RequestsStates> listUtilRequestsStates;
	
	/** The list util legatees types. */
	private List<LegateesTypes> listUtilLegateesTypes;
	
	/** The consults service. */
	private ConsultsService consultsService;
	
	/** The list cost centers. */
	private SelectItem[] listCostCenters;
	
	/** The list state request. */
	private SelectItem[] listStateRequest;
	
	/** The log. */
	private Log log = LogFactory.getLog(SelectItem.class);
	
	/**
	 * Gets the list legatees types.
	 *
	 * @return the list legatees types
	 */
	public SelectItem[] getListLegateesTypes() {
		try {
			listUtilLegateesTypes = requestService.listLegateesTypes();
			listLegateesTypes = new SelectItem[listUtilLegateesTypes.size() + 1];
			listLegateesTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (LegateesTypes legateesTypes : listUtilLegateesTypes) {
				listLegateesTypes[i] = new SelectItem(legateesTypes
						.getLgtCodigo(), legateesTypes.getLgtNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listLegateesTypes;
	}

	/**
	 * Gets the list requests classes.
	 *
	 * @return the list requests classes
	 */
	public SelectItem[] getListRequestsClasses() {
		try {
			listUtilRequestsClasses = requestService.listRequestsClasses();
			listRequestsClasses = new SelectItem[listUtilRequestsClasses.size() + 1];
			listRequestsClasses[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (RequestsClasses requestsClasses : listUtilRequestsClasses) {
				listRequestsClasses[i] = new SelectItem(requestsClasses
						.getRqcCodigo(), requestsClasses.getRqcNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listRequestsClasses;
	}

	/**
	 * Sets the list requests classes.
	 *
	 * @param listRequestsClasses the new list requests classes
	 */
	public void setListRequestsClasses(SelectItem[] listRequestsClasses) {
		this.listRequestsClasses = listRequestsClasses;
	}

	/**
	 * Gets the request service.
	 *
	 * @return the request service
	 */
	public RequestService getRequestService() {
		return requestService;
	}

	/**
	 * Sets the request service.
	 *
	 * @param requestService the new request service
	 */
	public void setRequestService(RequestService requestService) {
		this.requestService = requestService;
	}

	/**
	 * Gets the list requests types.
	 *
	 * @return the list requests types
	 */
	public SelectItem[] getListRequestsTypes() {
		try {
			listUtilRequestsTypes = requestService.listRequestsTypes();
			listRequestsTypes = new SelectItem[listUtilRequestsTypes.size() + 1];
			listRequestsTypes[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (RequestsTypes requestsTypes : listUtilRequestsTypes) {
				listRequestsTypes[i] = new SelectItem(requestsTypes
						.getRqyCodigo(), requestsTypes.getRqyNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listRequestsTypes;
	}

	/**
	 * Sets the list requests types.
	 *
	 * @param listRequestsTypes the new list requests types
	 */
	public void setListRequestsTypes(SelectItem[] listRequestsTypes) {
		this.listRequestsTypes = listRequestsTypes;
	}

	/**
	 * Gets the list requests states.
	 *
	 * @return the list requests states
	 */
	public SelectItem[] getListRequestsStates() {
		try {
			listUtilRequestsStates = requestService.listRequestsStates();
			listRequestsStates = new SelectItem[listUtilRequestsStates.size() + 1];
			listRequestsStates[0] = new SelectItem("-1", "--SELECCIONAR--");
			int i = 1;
			for (RequestsStates requestsStates : listUtilRequestsStates) {
				listRequestsStates[i] = new SelectItem(requestsStates
						.getRqtCodigo(), requestsStates.getRqtNombre());
				i++;
			}
		} catch (GWorkException e) {
			System.out.println(e.getMessage());
		}
		return listRequestsStates;
	}

	/**
	 * Sets the list requests states.
	 *
	 * @param listRequestsStates the new list requests states
	 */
	public void setListRequestsStates(SelectItem[] listRequestsStates) {
		this.listRequestsStates = listRequestsStates;
	}

	/**
	 * Sets the list legatees types.
	 *
	 * @param listLegateesTypes the new list legatees types
	 */
	public void setListLegateesTypes(SelectItem[] listLegateesTypes) {
		this.listLegateesTypes = listLegateesTypes;
	}

	/**
	 * Gets the consults service.
	 *
	 * @return the consults service
	 */
	public ConsultsService getConsultsService() {
		return consultsService;
	}

	/**
	 * Sets the consults service.
	 *
	 * @param consultsService the new consults service
	 */
	public void setConsultsService(ConsultsService consultsService) {
		this.consultsService = consultsService;
	}

	/**
	 * Gets the list cost centers.
	 *
	 * @return the list cost centers
	 */
	public SelectItem[] getListCostCenters() {

		return listCostCenters;
	}

	/**
	 * Sets the list cost centers.
	 *
	 * @param listCostCenters the new list cost centers
	 */
	public void setListCostCenters(SelectItem[] listCostCenters) {
		this.listCostCenters = listCostCenters;
	}

	/**
	 * Gets the list state request.
	 *
	 * @return the list state request
	 */
	public SelectItem[] getListStateRequest() {

		if (listStateRequest == null) {

			try {

				listStateRequest = new SelectItem[3];
				listStateRequest[0] = new SelectItem(-1L, Util
						.loadMessageValue("SELECCIONAR"));
				listStateRequest[1] = new SelectItem(1, Util
						.loadMessageValue("RQS.PENDIENTE"));
				listStateRequest[2] = new SelectItem(2, Util
						.loadMessageValue("RQS.CUMPLIDA"));

			} catch (GWorkException e) {
				log.error(e.getMessage());
			}

		}

		return listStateRequest;
	}

	/**
	 * Sets the list state request.
	 *
	 * @param listStateRequest the new list state request
	 */
	public void setListStateRequest(SelectItem[] listStateRequest) {
		this.listStateRequest = listStateRequest;
	}
}
