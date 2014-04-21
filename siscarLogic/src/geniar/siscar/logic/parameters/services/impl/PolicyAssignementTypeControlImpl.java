package geniar.siscar.logic.parameters.services.impl;

import geniar.siscar.logic.consultas.SearchPoliciesTypes;
import geniar.siscar.logic.consultas.SearchPolicyAssignementTypeControl;
import geniar.siscar.logic.parameters.services.PolicyAssignementTypeControlService;
import geniar.siscar.logic.policies.services.impl.PoliciesTypesServiceImpl;
import geniar.siscar.model.ControlAssignationPolicies;
import geniar.siscar.model.LegateesTypes;
import geniar.siscar.model.LocationsTypes;
import geniar.siscar.model.PoliciesTypes;
import geniar.siscar.persistence.ControlAssignationPoliciesDAO;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.LegateesTypesDAO;
import geniar.siscar.persistence.LocationsTypesDAO;
import geniar.siscar.persistence.PoliciesTypesDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.List;

/**
 * @author Diego Humberto Bocanegra
 * 
 */
public class PolicyAssignementTypeControlImpl implements
		PolicyAssignementTypeControlService {

	public ControlAssignationPolicies consultarCtrlTipoAsignacion(
			Long idPolicyAssignementTypeControl) throws GWorkException {
		return null;
	}

	public void crearPolicyAssignementTypeControl(Long idTipoAsignatario,
			Long idTipoUbicacion, Long idTipoPoliza, boolean soatRequerido,
			boolean responsabCivil) throws GWorkException {

		if (new SearchPolicyAssignementTypeControl()
				.consultarLlaveAsignatarioUbicacion(idTipoAsignatario,
						idTipoUbicacion)) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERR.ASIGN.UBIC.EXISTE"));
		}

		// Se crean los objetos
		ControlAssignationPolicies assignationsPolicies = new ControlAssignationPolicies();
		ControlAssignationPolicies assignationsPolicies1 = new ControlAssignationPolicies();
		ControlAssignationPolicies assignationsPolicies2 = new ControlAssignationPolicies();

		LegateesTypes legateesTypes = new LegateesTypeServicieImpl()
				.consultarLegateeTypesById(idTipoAsignatario);
		LocationsTypes locationsTypes = new LocationTypesServiceImpl()
				.consultarLocationTypesById(idTipoUbicacion);
		PoliciesTypes policiesTypes = new PoliciesTypesServiceImpl()
				.consultarPoliciesTypesById(idTipoPoliza);

		if (legateesTypes == null || locationsTypes == null
				|| policiesTypes == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		}

		try {
			ControlAssignationPoliciesDAO policiesDAO = new ControlAssignationPoliciesDAO();

			assignationsPolicies.setLegateesTypes(legateesTypes);
			assignationsPolicies.setLocationsTypes(locationsTypes);
			assignationsPolicies.setPoliciesTypes(policiesTypes);
			policiesDAO.save(assignationsPolicies);

			if (soatRequerido) {
				policiesTypes = new SearchPoliciesTypes()
						.consultarPoliciesTypes(4L);
				assignationsPolicies1.setLegateesTypes(legateesTypes);
				assignationsPolicies1.setLocationsTypes(locationsTypes);
				assignationsPolicies1.setPoliciesTypes(policiesTypes);
				policiesDAO.save(assignationsPolicies1);

			}

			if (responsabCivil) {

				policiesTypes = new SearchPoliciesTypes()
						.consultarPoliciesTypes(3L);
				assignationsPolicies2.setLegateesTypes(legateesTypes);
				assignationsPolicies2.setLocationsTypes(locationsTypes);
				assignationsPolicies2.setPoliciesTypes(policiesTypes);
				policiesDAO.save(assignationsPolicies2);

				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();

			}
		} catch (RuntimeException e) {

			e.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR")
					+ " : " + e.getMessage());
		}
	}

	public void eliminarControlAssignationsPolicies(Long id)
			throws GWorkException {

	}

	public List<ControlAssignationPolicies> listaControlAssignationsPolicies(
			Long idTipoAsignatario, Long idTipoUbicacion) throws GWorkException {
		return new SearchPolicyAssignementTypeControl()
				.consultarTodosCAPPorFiltro(idTipoAsignatario, idTipoUbicacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.parameters.services.
	 *      PolicyAssignementTypeControlService#modificarPolicyAssignmentControl
	 *      (java.lang.Long, java.lang.Long, java.lang.Long, boolean, boolean)
	 */
	public void modificarPolicyAssignmentControl(Long idTipoAsignatario,
			Long idTipoUbicacion, Long idTipoPoliza, boolean soatRequerido,
			boolean responsabCivil) throws GWorkException {

		Util.validarSession();
		PoliciesTypes policiesTypes = new PoliciesTypesDAO()
				.findById(idTipoPoliza);
		if (policiesTypes == null) {
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.TIPOPOLIZANOF"));
		}

		try {
			if (idTipoPoliza.longValue() == 12L) {
				ControlAssignationPolicies capFull = consultarCAP(
						idTipoAsignatario, idTipoUbicacion, 11L);

				if (capFull != null) {
					new ControlAssignationPoliciesDAO().delete(capFull);
				}

				ControlAssignationPolicies cap = consultarCAP(
						idTipoAsignatario, idTipoUbicacion, 12L);

				if (cap == null) {
					cap = new ControlAssignationPolicies();
					cap.setLegateesTypes(new LegateesTypesDAO()
							.findById(idTipoAsignatario));
					cap.setLocationsTypes(new LocationsTypesDAO()
							.findById(idTipoUbicacion));
					cap.setPoliciesTypes(policiesTypes);
					new ControlAssignationPoliciesDAO().save(cap);
				}
			}
			if (idTipoPoliza.longValue() == 11L) {
				ControlAssignationPolicies capAmparos = consultarCAP(
						idTipoAsignatario, idTipoUbicacion, 12L);

				if (capAmparos != null) {
					new ControlAssignationPoliciesDAO().delete(capAmparos);
				}

				ControlAssignationPolicies cap = consultarCAP(
						idTipoAsignatario, idTipoUbicacion, 11L);

				if (cap == null) {
					cap = new ControlAssignationPolicies();
					cap.setLegateesTypes(new LegateesTypesDAO()
							.findById(idTipoAsignatario));
					cap.setLocationsTypes(new LocationsTypesDAO()
							.findById(idTipoUbicacion));
					cap.setPoliciesTypes(policiesTypes);
					new ControlAssignationPoliciesDAO().save(cap);
				}
			}

			if (soatRequerido) {
				policiesTypes = new SearchPoliciesTypes()
						.consultarPoliciesTypes(10L);

				ControlAssignationPolicies capSoat = consultarCAP(
						idTipoAsignatario, idTipoUbicacion, 10L);

				if (capSoat == null) {
					capSoat = new ControlAssignationPolicies();
					policiesTypes = new PoliciesTypesDAO().findById(10L);
					capSoat.setLegateesTypes(new LegateesTypesDAO()
							.findById(idTipoAsignatario));
					capSoat.setLocationsTypes(new LocationsTypesDAO()
							.findById(idTipoUbicacion));
					capSoat.setPoliciesTypes(policiesTypes);
					new ControlAssignationPoliciesDAO().save(capSoat);
				}
			} else {
				ControlAssignationPolicies capSoat = new SearchPolicyAssignementTypeControl()
						.consultarCAP(idTipoAsignatario, idTipoUbicacion, 10L);

				if (capSoat != null) {
					new ControlAssignationPoliciesDAO().delete(capSoat);
				}
			}

			if (responsabCivil) {
				policiesTypes = new SearchPoliciesTypes()
						.consultarPoliciesTypes(3L);

				ControlAssignationPolicies capResp = consultarCAP(
						idTipoAsignatario, idTipoUbicacion, 3L);

				if (capResp == null) {
					capResp = new ControlAssignationPolicies();
					capResp.setLegateesTypes(new LegateesTypesDAO()
							.findById(idTipoAsignatario));
					capResp.setLocationsTypes(new LocationsTypesDAO()
							.findById(idTipoUbicacion));
					capResp.setPoliciesTypes(policiesTypes);
					new ControlAssignationPoliciesDAO().save(capResp);
				}
			} else {
				ControlAssignationPolicies capResp = new SearchPolicyAssignementTypeControl()
						.consultarCAP(idTipoAsignatario, idTipoUbicacion, 3L);

				if (capResp != null) {
					new ControlAssignationPoliciesDAO().delete(capResp);
				}
			}

			EntityManagerHelper.getEntityManager().getTransaction().begin();
			EntityManagerHelper.getEntityManager().getTransaction().commit();
			// Util.limpiarSession();

		} catch (RuntimeException e) {
			// Util.limpiarSession();
			e.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		}
	}

	public ControlAssignationPolicies consultarCtrlTipoAsignacion(
			Long lgtCodigo, Long lctCodigo) throws GWorkException {
		return new SearchPolicyAssignementTypeControl().consultarCAP(lgtCodigo,
				lctCodigo);
	}

	public ControlAssignationPolicies consultarCAP(Long lgtCodigo,
			Long lctCodigo, Long pltCodigo) throws GWorkException {
		return new SearchPolicyAssignementTypeControl().consultarCAP(lgtCodigo,
				lctCodigo, pltCodigo);
	}
}
