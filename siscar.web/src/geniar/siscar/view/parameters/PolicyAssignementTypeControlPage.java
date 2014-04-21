package geniar.siscar.view.parameters;

import geniar.siscar.logic.parameters.services.LegateesTypesService;
import geniar.siscar.logic.parameters.services.PolicyAssignementTypeControlService;
import geniar.siscar.logic.policies.services.PoliciesTypeService;
import geniar.siscar.model.ControlAssignationPolicies;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.util.FacesUtils;
import geniar.siscar.util.PopupMessagePage;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 * 
 * @author Bean PolicyAssignementTypeControl CAP = ControlAssignmentPolicy
 */
public class PolicyAssignementTypeControlPage {

	private Long lctCodigo;
	private Long lgtCodigo;
	private Long pltCodigo;

	private Integer capSoatRequerido;
	private Integer capResponsabCivil;

	private boolean bSoatRequerido;
	private boolean bRespCivil;

	private boolean showSoatRequerido;
	private boolean showRespCivil;

	private boolean disableCrear;

	private HtmlSelectOneMenu selectAsignatario;
	private HtmlSelectOneMenu selectLocation;
	private HtmlSelectOneMenu selectSeguro;

	private SelectItem[] listLocations;
	private SelectItem[] listLegateeTypes;
	private SelectItem[] listPolicyTypes;

	private Long idLocation;

	private PolicyAssignementTypeControlService policyAssignementTypeControlService;
	private PoliciesTypeService policiesTypeService;
	private LegateesTypesService legateesTypeService;
	private boolean activarConfirmacion;
	private Integer opcConfirmacion;
	private static Integer MODIFICAR = 1;

	public PolicyAssignementTypeControlPage() {
		showRespCivil = true;
		showSoatRequerido = true;
	}

	/**
	 * Almacena la información ingresada desde PolicyAssignementTypeControl.jspx
	 * 
	 * @param event
	 */
	public void action_crearCAP(ActionEvent event) {
		try {

			// Si cualquiera de los datos es nulo se genera una excepción.
			if (lgtCodigo == null || lgtCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOASIGNACION"));

			if (lctCodigo == null || lctCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPO.UBICACION"));

			if (pltCodigo == null || pltCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELTIPOSEGURO"));

			try {
				policyAssignementTypeControlService
						.crearPolicyAssignementTypeControl(lgtCodigo,
								lctCodigo, pltCodigo, bSoatRequerido,
								bRespCivil);
			} catch (GWorkException e) {
				throw new GWorkException(e.getMessage());
			}

			mostrarMensaje(Util.loadMessageValue("EXITO"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Consulta las combinacions de PolicyAssignementTypeControl
	 * 
	 * @param event
	 */
	public void action_consultarCAP(ActionEvent event) {
		try {
			// Si cualquiera de los datos es nulo se genera una excepción.
			if (lgtCodigo == null || lgtCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPOASIGNACION"));

			if (lctCodigo == null || lctCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("TIPO.UBICACION"));

			List<ControlAssignationPolicies> listCapObject = policyAssignementTypeControlService
					.listaControlAssignationsPolicies(lgtCodigo, lctCodigo);

			if (listCapObject == null || listCapObject.size() == 0) {
				limpiar();
				throw new GWorkException(Util
						.loadErrorMessageValue("search.not.found"));
			}

			ControlAssignationPolicies capTipoPoliza = null;
			ControlAssignationPolicies capSoat = null;
			ControlAssignationPolicies capResp = null;

			for (int i = 0; i < listCapObject.size(); i++) {
				ControlAssignationPolicies cap = listCapObject.get(i);
				if (cap != null) {

					PoliciesTypes policiesTypes = cap.getPoliciesTypes();

					if (policiesTypes != null) {
						if (policiesTypes.getPltCodigo().longValue() == 11L
								|| cap.getPoliciesTypes().getPltCodigo()
										.longValue() == 12L) {
							capTipoPoliza = cap;
						}
						if (policiesTypes.getPltCodigo().longValue() == 3L) {
							capResp = cap;
						}
						if (policiesTypes.getPltCodigo().longValue() == 10L) {
							capSoat = cap;
						}
					}
				}
			}

			if (capTipoPoliza != null) {
				selectSeguro.setValue(capTipoPoliza.getPoliciesTypes()
						.getPltCodigo());
			} else
				selectSeguro.setValue(-1L);

			if (capResp != null) {
				bRespCivil = true;
			} else
				bRespCivil = false;

			if (capSoat != null) {
				bSoatRequerido = true;
			} else
				bSoatRequerido = false;

			selectAsignatario.setDisabled(true);
			selectLocation.setDisabled(true);
			selectSeguro.setDisabled(false);
			disableCrear = true;

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	public void action_modificarCAP(ActionEvent event) {
		try {
			activarConfirmacion = true;
			validarDatos();

			if (pltCodigo == null || pltCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELTIPOSEGURO"));

			setOpcConfirmacion(MODIFICAR);
			mostrarMensaje(Util.loadMessageValue("MENSAJE.ALERTA"),
					activarConfirmacion);

		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());

		}
	}

	public void action_modificarCAP() {
		try {
			validarDatos();

			if (pltCodigo == null || pltCodigo == -1)
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.SELTIPOSEGURO"));

			policyAssignementTypeControlService
					.modificarPolicyAssignmentControl(lgtCodigo, lctCodigo,
							pltCodigo, bSoatRequerido, bRespCivil);

			mostrarMensaje(Util.loadMessageValue("EXITO.MODIFICAR"), false);
			limpiar();
		} catch (GWorkException e) {
			FacesUtils.addErrorMessage(e.getMessage());
		}
	}

	private void validarDatos() throws GWorkException {
		// Si requiere responsabilidad Civil
		if (bRespCivil) {
			capResponsabCivil = 1;
		} else
			capResponsabCivil = 0;

		// Si requiere SOAT
		if (bSoatRequerido) {
			capSoatRequerido = 1;
		} else
			capSoatRequerido = 0;

		// Si cualquiera de los datos es nulo se genera una excepción.
		if (lgtCodigo == null || lgtCodigo == -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPOASIGNACION"));

		if (lctCodigo == null || lctCodigo == -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("TIPO.UBICACION"));

		if (pltCodigo == null || pltCodigo == -1)
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.SELTIPOSEGURO"));
	}

	/**
	 * Vuelve a los valores iniciales PolicyAssignementTypeControl.jspx
	 * 
	 * @param event
	 */
	public void action_limpiarForma(ActionEvent event) {
		limpiar();
	}

	public void limpiar() {
		disableCrear = false;
		bRespCivil = false;
		bSoatRequerido = false;
		selectAsignatario.setValue(new Long("-1"));
		selectLocation.setValue(new Long("-1"));
		selectSeguro.setValue(new Long("-1"));
		selectAsignatario.setDisabled(false);
		selectLocation.setDisabled(false);
		selectSeguro.setDisabled(false);
	}

	public void mostrarMensaje(String mensaje, boolean buttonCancel) {
		PopupMessagePage message = (PopupMessagePage) FacesUtils
				.getManagedBean("popupMessagePage");
		if (message != null)
			message.showPopup(mensaje, buttonCancel);
	}

	public Long getLctCodigo() {
		return lctCodigo;
	}

	public void setLctCodigo(Long lctCodigo) {
		this.lctCodigo = lctCodigo;
	}

	public Long getLgtCodigo() {
		return lgtCodigo;
	}

	public void setLgtCodigo(Long lgtCodigo) {
		this.lgtCodigo = lgtCodigo;
	}

	public Long getPltCodigo() {
		return pltCodigo;
	}

	public void setPltCodigo(Long pltCodigo) {
		this.pltCodigo = pltCodigo;
	}

	public Integer getCapSoatRequerido() {
		return capSoatRequerido;
	}

	public void setCapSoatRequerido(Integer capSoatRequerido) {
		this.capSoatRequerido = capSoatRequerido;
	}

	public Integer getCapResponsabCivil() {
		return capResponsabCivil;
	}

	public void setCapResponsabCivil(Integer capResponsabCivil) {
		this.capResponsabCivil = capResponsabCivil;
	}

	public PolicyAssignementTypeControlService getPolicyAssignementTypeControlService() {
		return policyAssignementTypeControlService;
	}

	public void setPolicyAssignementTypeControlService(
			PolicyAssignementTypeControlService policyAssignementTypeControlService) {
		this.policyAssignementTypeControlService = policyAssignementTypeControlService;
	}

	public HtmlSelectOneMenu getSelectAsignatario() {
		return selectAsignatario;
	}

	public void setSelectAsignatario(HtmlSelectOneMenu selectAsignatario) {
		this.selectAsignatario = selectAsignatario;
	}

	public HtmlSelectOneMenu getSelectLocation() {
		return selectLocation;
	}

	public void setSelectLocation(HtmlSelectOneMenu selectLocation) {
		this.selectLocation = selectLocation;
	}

	public HtmlSelectOneMenu getSelectSeguro() {
		return selectSeguro;
	}

	public void setSelectSeguro(HtmlSelectOneMenu selectSeguro) {
		this.selectSeguro = selectSeguro;
	}

	public SelectItem[] getListLocations() {
		return listLocations;
	}

	public void setListLocations(SelectItem[] listLocations) {
		this.listLocations = listLocations;
	}

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public SelectItem[] getListLegateeTypes() {
		return listLegateeTypes;
	}

	public void setListLegateeTypes(SelectItem[] listLegateeTypes) {
		this.listLegateeTypes = listLegateeTypes;
	}

	public SelectItem[] getListPolicyTypes() {
		return listPolicyTypes;
	}

	public void setListPolicyTypes(SelectItem[] listPolicyTypes) {
		this.listPolicyTypes = listPolicyTypes;
	}

	public PoliciesTypeService getPoliciesTypeService() {
		return policiesTypeService;
	}

	public void setPoliciesTypeService(PoliciesTypeService policiesTypeService) {
		this.policiesTypeService = policiesTypeService;
	}

	public LegateesTypesService getLegateesTypeService() {
		return legateesTypeService;
	}

	public void setLegateesTypeService(LegateesTypesService legateesTypeService) {
		this.legateesTypeService = legateesTypeService;
	}

	public boolean isBSoatRequerido() {
		return bSoatRequerido;
	}

	public void setBSoatRequerido(boolean soatRequerido) {
		bSoatRequerido = soatRequerido;
	}

	public boolean isBRespCivil() {
		return bRespCivil;
	}

	public void setBRespCivil(boolean respCivil) {
		bRespCivil = respCivil;
	}

	public boolean isShowSoatRequerido() {
		return showSoatRequerido;
	}

	public void setShowSoatRequerido(boolean showSoatRequerido) {
		this.showSoatRequerido = showSoatRequerido;
	}

	public boolean isShowRespCivil() {
		return showRespCivil;
	}

	public void setShowRespCivil(boolean showRespCivil) {
		this.showRespCivil = showRespCivil;
	}

	public boolean isDisableCrear() {
		return disableCrear;
	}

	public void setDisableCrear(boolean disableCrear) {
		this.disableCrear = disableCrear;
	}

	public boolean isActivarConfirmacion() {
		return activarConfirmacion;
	}

	public void setActivarConfirmacion(boolean activarConfirmacion) {
		this.activarConfirmacion = activarConfirmacion;
	}

	public Integer getOpcConfirmacion() {
		return opcConfirmacion;
	}

	public void setOpcConfirmacion(Integer opcConfirmacion) {
		this.opcConfirmacion = opcConfirmacion;
	}
}
