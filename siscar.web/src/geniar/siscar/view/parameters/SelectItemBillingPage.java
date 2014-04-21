package geniar.siscar.view.parameters;

import geniar.siscar.logic.billing.services.ParametersBillingService;
import geniar.siscar.model.Period;
import geniar.siscar.model.ProofType;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SelectItemBillingPage {

	private ParametersBillingService parametersBillingService;

	private SelectItem listaTipoComprobante[];
	private SelectItem listaTipoComprobanteAnulacionComprobante[];
	private SelectItem listaPeriodo[];
	private SelectItem periodDisableProof[];
	private SelectItem listaPeriodoCombustible[];
	private SelectItem listaPeriodoAsignacion[];
	private static final Log log = LogFactory
			.getLog(SelectItemBillingPage.class);

	private geniar.siscar.logic.billing.services.PeriodService periodService;

	public SelectItem[] getListaPeriodo() {

		if (listaPeriodo == null) {
			try {
				List<Period> listPeriod = parametersBillingService
						.listPeriodOrder();
				listaPeriodo = new SelectItem[listPeriod.size() + 1];
				listaPeriodo[0] = new SelectItem(new Long("-1"), Util
						.loadMessageValue("SELECCIONAR"));
				int i = 1;

				for (Period period : listPeriod) {
					listaPeriodo[i] = new SelectItem(period.getPerId(), period
							.getPerNombre());
					i++;
				}
			} catch (GWorkException e) {
				log.error(e);

			}
		}
		return listaPeriodo;
	}

	public void setListaPeriodo(SelectItem[] listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public ParametersBillingService getParametersBillingService() {

		return parametersBillingService;
	}

	public void setParametersBillingService(
			ParametersBillingService parametersBillingService) {
		this.parametersBillingService = parametersBillingService;
	}

	public SelectItem[] getListaTipoComprobante() {

		if (listaTipoComprobante == null) {

			try {
				List<ProofType> listProofType = parametersBillingService
						.listProofTypeOrder();
				listaTipoComprobante = new SelectItem[listProofType.size() + 1];
				listaTipoComprobante[0] = new SelectItem(new Long("-1"), Util
						.loadMessageValue("SELECCIONAR"));
				int i = 1;

				for (ProofType proofType : listProofType) {
					listaTipoComprobante[i] = new SelectItem(proofType
							.getPrtCodigo(), proofType.getPrtNombre());
					i++;
				}
			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}
		}
		return listaTipoComprobante;
	}

	public void setListaTipoComprobante(SelectItem[] listaTipoComprobante) {
		this.listaTipoComprobante = listaTipoComprobante;
	}

	public SelectItem[] getListaTipoComprobanteAnulacionComprobante() {

		if (listaTipoComprobante == null) {

			try {
				List<ProofType> listProofType = parametersBillingService
						.listProofTypeOrder();
				listaTipoComprobanteAnulacionComprobante = new SelectItem[3];
				listaTipoComprobanteAnulacionComprobante[0] = new SelectItem(
						new Long("-1"), Util.loadMessageValue("SELECCIONAR"));
				int i = 1;

				for (ProofType proofType : listProofType) {
					if (proofType.getPrtCodigo() == 1
							|| proofType.getPrtCodigo() == 3) {
						listaTipoComprobanteAnulacionComprobante[i] = new SelectItem(
								proofType.getPrtCodigo(), proofType
										.getPrtNombre());
						i++;
					}
				}
			} catch (GWorkException e) {
				System.out.println(e.getMessage());
			}
		}
		return listaTipoComprobanteAnulacionComprobante;
	}

	public void setListaTipoComprobanteAnulacionComprobante(
			SelectItem[] listaTipoComprobanteAnulacionComprobante) {
		this.listaTipoComprobanteAnulacionComprobante = listaTipoComprobanteAnulacionComprobante;
	}

	public SelectItem[] getListaPeriodoCombustible() {

		if (listaPeriodoCombustible == null) {
			try {
				List<Period> listPeriod = parametersBillingService
						.listPeriodFuel();
				listaPeriodoCombustible = new SelectItem[listPeriod.size() + 1];
				listaPeriodoCombustible[0] = new SelectItem(new Long("-1"),
						Util.loadMessageValue("SELECCIONAR"));
				int i = 1;

				for (Period period : listPeriod) {
					listaPeriodoCombustible[i] = new SelectItem(period
							.getPerId(), period.getPerNombre());
					i++;
				}
			} catch (GWorkException e) {
				log.error(e);

			}
		}
		return listaPeriodoCombustible;
	}

	public void setListaPeriodoCombustible(SelectItem[] listaPeriodoCombustible) {
		this.listaPeriodoCombustible = listaPeriodoCombustible;
	}

	public SelectItem[] getListaPeriodoAsignacion() {
		try {
			List<Period> listPeriod = parametersBillingService
					.listPeriodAssignation();
			listaPeriodoAsignacion = new SelectItem[listPeriod.size() + 1];
			listaPeriodoAsignacion[0] = new SelectItem(new Long("-1"), Util
					.loadMessageValue("SELECCIONAR"));
			int i = 1;

			for (Period period : listPeriod) {
				listaPeriodoAsignacion[i] = new SelectItem(period.getPerId(),
						period.getPerNombre());
				i++;
			}
		} catch (GWorkException e) {
			log.error(e);
		}

		return listaPeriodoAsignacion;
	}

	public void setListaPeriodoAsignacion(SelectItem[] listaPeriodoAsignacion) {
		this.listaPeriodoAsignacion = listaPeriodoAsignacion;
	}

	public void listener_periodDiseableProof(ValueChangeEvent event) {
		try {
			Integer idProofType = (Integer) event.getNewValue();
			Long idNovetltieType = null;
			if (idProofType.longValue() == -1L)
				setPeriodDisableProof(null);
			else {
				if (idProofType.longValue() == ParametersUtil.PROOF_TYPE_ASIGNACION)
					idNovetltieType = ParametersUtil.NOVEDAD_ASIG;
				else if (idProofType.longValue() == ParametersUtil.PROOF_TYPE_COMBUSTIBLE)
					idNovetltieType = ParametersUtil.NOVEDAD_COMB;

				List<Period> listPeriod = periodService
						.periodsByeNoveltie(idNovetltieType);

				periodDisableProof = new SelectItem[listPeriod.size() + 1];
				periodDisableProof[0] = new SelectItem(-1L, Util
						.loadMessageValue("SELECCIONAR"));

				int i = 1;

				for (Period period : listPeriod) {
					periodDisableProof[i] = new SelectItem(period.getPerId(),
							period.getPerNombre());
					i++;
				}
				setPeriodDisableProof(periodDisableProof);
			}
		} catch (GWorkException e) {
			log.error(e.getMessage());
		}

	}

	public geniar.siscar.logic.billing.services.PeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(
			geniar.siscar.logic.billing.services.PeriodService periodService) {
		this.periodService = periodService;
	}

	public SelectItem[] getPeriodDisableProof() {
		return periodDisableProof;
	}

	public void setPeriodDisableProof(SelectItem[] periodDisableProof) {
		this.periodDisableProof = periodDisableProof;
	}
}
