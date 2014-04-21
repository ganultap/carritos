package geniar.siscar.view.billing;

import geniar.siscar.logic.billing.services.PeriodService;
import geniar.siscar.logic.billing.services.ProofService;
import geniar.siscar.model.ActualOthersApplications;
import geniar.siscar.model.Period;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import geniar.siscar.view.autenticate.LoginPage;
import gwork.exception.GWorkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

/**
 * @author alvaro.hincapie
 * 
 */
public class DisableProofPage {

	private Integer proofTypeSelected;
	private Integer periodSelected;
	private Date fechaIni;
	private Date fechaFin;
	private PeriodService periodService;
	private ProofService proofService;
	private List<Object[]> resultProof;
	private LoginPage loginPage;
	private List<ActualOthersApplications> proofDetail;

	/**
	 * Carga la fecha inicial y final de acuerdo al periodo seleccionado.
	 * 
	 * @param event
	 */
	public void listener_periodo(ValueChangeEvent event) {
		Integer idPeriodo = (Integer) event.getNewValue();
		Period period = null;
		if (idPeriodo != null) {
			try {
				period = periodService.consultarPeriodById(new Long(idPeriodo));

				if (period != null) {
					fechaIni = period.getPerFechaIni();
					fechaFin = period.getPerFechaFin();
				}

			} catch (GWorkException e) {
				FacesUtils.addErrorMessage(e.getMessage());
			}
		}
	}

	/**
	 * Consulta los comprobantes por tipo comprobante y periodo.
	 * 
	 * @param event
	 */
	public void listener_proof(ActionEvent event) {
		try {

			// Valida obligatoriedad de campo tipo comprobante
			// if (getProofTypeSelected() == null || getProofTypeSelected() ==
			// -1) {
			// throw new GWorkException(Util
			// .loadErrorMessageValue("TIPOCOMPROBANTE.NULO"));
			// }

			// Valida obligatoriedad de campo periodo
			if (getPeriodSelected() == null || getPeriodSelected() == -1) {
				throw new GWorkException(Util
						.loadErrorMessageValue("PERIODO.NULO"));
			}

			List<Object[]> result = proofService.consultaProofByTypeAndPeriod(
					new Long(getProofTypeSelected()), new Long(
							getPeriodSelected()));
			setResultProof(result);
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void action_limpiar(ActionEvent actionEvent) {
		limpiar();
	}

	public void limpiar() {
		setProofTypeSelected(-1);
		setPeriodSelected(-1);
		setResultProof(null);
		setFechaFin(null);
		setFechaIni(null);
		setProofDetail(null);
	}

	public void actionAnularComprobantes(ActionEvent event) {
		int contador = 0;
		try {
			if (resultProof == null || resultProof.size() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ANUL.COMPROBANTE.NULL"));

			List<Long> listHeaderProof = new ArrayList<Long>();
			for (Object[] var : resultProof) {
				contador++;
				
				String filaSeleccionada = var[6].toString();
				if(filaSeleccionada == "true"){									
					Boolean anulaComprobante = (Boolean) var[6];
	
					if (anulaComprobante) {
						listHeaderProof.add((Long) var[7]);
					}
				}
			}

			if (listHeaderProof == null || listHeaderProof.size() == 0)
				throw new GWorkException(Util
						.loadErrorMessageValue("ANUL.COMPROBANTE.SELECN"));

			// Si el comprobante es de tipo asignación
			getProofService().anularComprobanteVehiculosAsignados(
					listHeaderProof, getLoginPage().getLogin());

			limpiar();
			mostrarMensaje(Util.loadMessageValue("EXITO"), false);

		} catch (Exception e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void actionVerDetalleComprobante(ActionEvent event) {
		try {
			String strGroupId = FacesUtils.getRequestParameter("proofGroupId");

			List<ActualOthersApplications> result = getProofService()
					.consultarDetalleComprobante(strGroupId);
			setProofDetail(result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isVisibleDetalleComprobante() {
		boolean visible = false;

		if (getProofDetail() != null && !getProofDetail().isEmpty()) {
			visible = true;
		}

		return visible;
	}

	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Integer getProofTypeSelected() {
		return proofTypeSelected;
	}

	public void setProofTypeSelected(Integer proofTypeSelected) {
		this.proofTypeSelected = proofTypeSelected;
	}

	public Integer getPeriodSelected() {
		return periodSelected;
	}

	public void setPeriodSelected(Integer periodSelected) {
		this.periodSelected = periodSelected;
	}

	public PeriodService getPeriodService() {
		return periodService;
	}

	public void setPeriodService(PeriodService periodService) {
		this.periodService = periodService;
	}

	public ProofService getProofService() {
		return proofService;
	}

	public void setProofService(ProofService proofService) {
		this.proofService = proofService;
	}

	public List<Object[]> getResultProof() {
		return resultProof;
	}

	public void setResultProof(List<Object[]> resultProof) {
		this.resultProof = resultProof;
	}

	public LoginPage getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(LoginPage loginPage) {
		this.loginPage = loginPage;
	}

	public List<ActualOthersApplications> getProofDetail() {
		return proofDetail;
	}

	public void setProofDetail(List<ActualOthersApplications> proofDetail) {
		this.proofDetail = proofDetail;
	}

	/**
	 * Llama al ManagedBean encargado de desplegar un popUp.
	 */
	public void mostrarMensaje(String mensaje, boolean confirmar) {
		PopupMessagePage pmp = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (pmp != null) {
			pmp.showPopup(mensaje, confirmar);
		}
	}

}
