package geniar.siscar.consults.impl;

import geniar.siscar.conf.EntityManagerHelper;
import geniar.siscar.consults.ConsultsService;
import geniar.siscar.logic.billing.services.impl.SearchParametersBilling;
import geniar.siscar.logic.consultas.DisponibilidadPresupuestalAlquiler;
import geniar.siscar.model.AccountingParameters;
import geniar.siscar.model.ConsultUsers;
import geniar.siscar.model.CostsCenters;
import geniar.siscar.model.CostsCentersVehicles;
import geniar.siscar.model.Driver;
import geniar.siscar.model.InvoicesHeaderPoliciesVO;
import geniar.siscar.model.Users;
import geniar.siscar.model.VOEmployee;
import geniar.siscar.model.VOUser;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Class ConsultsServiceImpl.
 *
 * @author Geniar
 */

public class ConsultsServiceImpl implements ConsultsService {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(ConsultsServiceImpl.class);
	
	/** The valida presupuesto. */
	private boolean validaPresupuesto = false;

	/**
	 * Valida que la cuenta exista.
	 * No se esta utilizando
	 *
	 * @param carneAuxiliar the carne auxiliar
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String validarCarneAuxiliar(String carneAuxiliar)
			throws GWorkException {
		String result = null;
		Query query = null;
		try {
			result = "select ciatapps.f_valida_carnet_auxiliar_of"
					+ Util.loadMessageValue("DBLINK") + "(?) from dual";
			query = EntityManagerHelper.getEntityManager().createNativeQuery(
					result);
			query.setParameter(1, carneAuxiliar);

			result = (String) query.getSingleResult();

			if (result != null)
				return result;

			return result;
		} catch (Exception e) {
			log.error("validarCarneAuxiliar", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION"), e);
		}
	}

	/**
	 * Consulta que la cuenta exista.
	 * No se está utilizando
	 *
	 * @param codigoCuenta the codigo cuenta
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultAccount(String codigoCuenta) throws GWorkException {
		String result = null;
		Query query = null;
		try { 
			result = "select FINANZAS_INTERFAZ.V_ABW_ACTIVE_ACCOUNT (?) from dual";
			query = EntityManagerHelper.getEntityManager().createNativeQuery(
					result);
			query.setParameter(1, codigoCuenta);

			result = (String) query.getSingleResult();

		} catch (Exception e) {
			log.error("consultAccount", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION"), e);
		}

		return result;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#consultCompany(java.lang.String)
	 */
	public String consultCompany(String codigoCompañia) throws GWorkException {
		String result = null;
		Query query = null;
		try {
			
			result = "select FINANZAS_INTERFAZ.V_ABW_ACTIVE_ENTITY (?) from dual";
			query = EntityManagerHelper.getEntityManager().createNativeQuery(result);
			query.setParameter(1, codigoCompañia);

			result = (String) query.getSingleResult();

		} catch (Exception e) {
			log.error("consultCompany -> " + e.getMessage(), e);
			log.info("consultCompany -> " + e.getMessage(), e);
			
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION"), e);
		}

		return result;
	}

	/**
	 * Consulta que el centro de costo exista y este activo.
	 *
	 * @param costCenter the cost center
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean validateCostCenter(String costCenter) throws GWorkException {

		if(consultCostCenter(costCenter) != null){
			return false;
		}
		return true;
	}

	/**
	 * Consulta que el centro de costo exista y este activo.
	 *
	 * @param costCenter the cost center
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public String consultCostCenter(String costCenter) throws GWorkException {
		String result = null;

		List<Object> arreglo = null;
		
		try {
			String sbQuery = "select ATTR_VALUE from FINANZAS_INTERFAZ.V_ABW_ACTIVE_AEC " +
			"where ATTR_VALUE = '" + costCenter + "' AND STATUS = 'N'";
											 
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			arreglo = query.getResultList();

			if (arreglo == null || arreglo.isEmpty()) {
				result = "El AEC " + costCenter + " no existe ó no está activo, por favor verificar";
			}
		} catch (Exception e) {
			log.error("centrosCosto", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		} finally{
			EntityManagerHelper.getEntityManager().close();
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#consultCostCenterPeriodo(java.lang.String, java.util.Date, java.util.Date)
	 */
	public String consultCostCenterPeriodo(String costCenter, Date fechaIni,
			Date fechaFin) throws GWorkException {
				
		String result = consultCostCenter(costCenter);
		if(result == null){
			result = "";
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#consultEmployee(java.lang.String)
	 */
	public VOEmployee consultEmployee(String carne) throws GWorkException {
		String result = null;
		VOEmployee user = null;
		Object[] resultado = null;
		Query query = null;
		try {

			result = "select * from V_EMPLEADO_SRH_ACT_DRIVERS"
					+ " where emp_codigo = :carne";
			query = geniar.siscar.persistence.EntityManagerHelper
					.getEntityManager().createNativeQuery(result);
			query.setParameter("carne", carne);

			resultado = (Object[]) query.getSingleResult();

			if (resultado != null) {
				user = new VOEmployee();
				user.setEmpApellido(resultado[0].toString());
				user.setEmpNombre(resultado[1].toString());
			}

		} catch (Exception e) {
			log.error("consultEmployee", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#consultarAuxiliarCiat(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String consultarAuxiliarCiat(String codigo) throws GWorkException {

		List<Object[]> arreglo = null;
		VOUser users = null;
		try {

			String sbQuery = "SELECT V.flex_value, v.description nombre_auxiliar , v.ENABLED_FLAG, v.START_DATE_ACTIVE, v.END_DATE_ACTIVE "
					+ " FROM apps.FND_FLEX_VALUE_SETS"
					+ Util.loadMessageValue("DBLINK")
					+ " VS,apps.FND_FLEX_VALUES_VL"
					+ Util.loadMessageValue("DBLINK")
					+ " V"
					+ " WHERE VS.FLEX_VALUE_SET_NAME = 'CIAT_GL_AUXILIAR'"
					+ " AND VS.FLEX_VALUE_SET_ID = V.FLEX_VALUE_SET_ID"
					+ " AND v.flex_value <> '00000000' "
					+ " AND V.flex_value = '"
					+ codigo
					+ "'"
					+ " AND v.end_date_active is  null "
					+ " ORDER BY v.description ";
			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {

			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage());

		}

		if (arreglo != null && !arreglo.isEmpty() && arreglo.size() > 0) {

			for (Object[] listObj : arreglo) {
				users = new VOUser();
				if (listObj[0] != null)
					users.setUsrIdentificacion(listObj[0].toString());
				if (listObj[1] != null)
					users.setUsrNombre(listObj[1].toString());
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

		EntityManagerHelper.closeEntityManager();
		if (users != null)
			return users.getUsrNombre();

		return "";

	}

	/**
	 * Consulta la lista de carnes auxiliares del CIAT.
	 *
	 * @param nombreAuxiliar the nombre auxiliar
	 * @param codigo the codigo
	 * @return the list
	 * @throws GWorkException the g work exception
	 */

	@SuppressWarnings("unchecked")
	public List<Users> auxiliaresCIAT(String nombreAuxiliar, String codigo)
			throws GWorkException {

		List<Users> listaUsersCIAT = null;
		List<Object[]> arreglo = null;

		try {

			String sbQuery = "select ATTR_VALUE, DESCRIPTION " +
					" from FINANZAS_INTERFAZ.V_ABW_ACTIVE_CUSTOMER " +
					" where description like '%" + nombreAuxiliar + "%' " +
					" OR ATTR_VALUE LIKE '% " + codigo + "%' " +
					" AND STATUS = 'N' " +
					" ORDER BY DESCRIPTION";
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {
			log.error("auxiliaresCIAT", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);

		}

		if (arreglo != null && !arreglo.isEmpty() && arreglo.size() > 0) {
			listaUsersCIAT = new ArrayList<Users>();
			Users users = null;

			for (Object[] listObj : arreglo) {
				users = new Users();
				for (Object object : listObj) {

					if (listObj[0] != null)
						users.setUsrIdentificacion(listObj[0].toString());
					if (listObj[1] != null)
						users.setUsrNombre(listObj[1].toString());
				}
				listaUsersCIAT.add(users);
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

		EntityManagerHelper.closeEntityManager();
		return listaUsersCIAT;
	}

	/**
	 * Consultar ordenes de trabajo.
	 *
	 * @param paramNombre the param nombre
	 * @param paramApellido the param apellido
	 * @param carne the carne
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<ConsultUsers> usuariosCiat(String paramNombre,
			String paramApellido, String carne) throws GWorkException {

		List<Object[]> listaOrdenes = null;
		List<ConsultUsers> listaUsersCIAT = null;
		StringBuffer buffer = new StringBuffer();
		try {
			buffer.append("select emp_codigo, emp_nombre,emp_nombre_completo, emp_apellido1, EMP_APELLIDO2,emp_email,EMP_TELEFONO,EMP_DIRECCION ");
			buffer.append(" from FINANZAS_INTERFAZ.V_EMPLEADO_SRH where emp_estado <> 'R' AND ");
			buffer.append(" (emp_nombre_completo like '%" + paramNombre + "%'");
			buffer.append(" OR emp_apellido1 LIKE '%" + paramApellido
					+ "%' OR emp_codigo LIKE '%" + carne + "%')");
			buffer.append("ORDER BY emp_nombre ASC");

			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(buffer.toString());
			listaOrdenes = query.getResultList();

		} catch (Exception e) {
			log.error("usuariosCiat", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (listaOrdenes == null || listaOrdenes.isEmpty()
				|| listaOrdenes.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		if (listaOrdenes != null && !listaOrdenes.isEmpty()
				&& listaOrdenes.size() > 0) {
			listaUsersCIAT = new ArrayList<ConsultUsers>();
			ConsultUsers users = null;

			for (Object[] listObj : listaOrdenes) {
				users = new ConsultUsers();

				if (listObj[0] != null)
					users.setUsrIdentificacion(listObj[0].toString());

				if (listObj[1] != null)
					users.setUsrNombre(listObj[1].toString());

				if (listObj[2] != null)
					users.setUsr_Segundo_Nombre(listObj[2].toString());

				if (listObj[3] != null) {
					if (listObj[3] != null && listObj[4] != null)
						users.setUsrApellido(listObj[3].toString() + " "
								+ listObj[4].toString());
					else
						users.setUsrApellido(listObj[3].toString());
				}

				if (listObj[5] != null)
					users.setUsrEmail(listObj[5].toString());

				if (listObj[6] != null)
					users.setUsrTelefono(listObj[6].toString());

				if (listObj[7] != null)
					users.setUsrDireccion(listObj[7].toString());

				listaUsersCIAT.add(users);
			}
		}
		return listaUsersCIAT;
	}

	/**
	 * Consultar ordenes de trabajo.
	 *
	 * @param nombreOrden the nombre orden
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> ordenesTrabajo(String placa)
			throws GWorkException {

		List<Object[]> listaOrdenes = null;

		try {

			String sbQuery = "SELECT CODE, DESCRIPTION, VEHICLE_PLATE " +
					"FROM MAINTENANCE01.T_OCA_PROJECT " +
					"WHERE VEHICLE_PLATE ='" + placa.trim() + "'";
			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(sbQuery);
			listaOrdenes = query.getResultList();

		} catch (Exception e) {
			log.error("ordenesTrabajo", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (listaOrdenes == null || listaOrdenes.isEmpty()
				|| listaOrdenes.size() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));

		return listaOrdenes;
	}

	/**
	 * Consulta la lista de empleados activos en el CIAT*.
	 *
	 * @param paramNombre the param nombre
	 * @param paramApellido the param apellido
	 * @param carne the carne
	 * @return the list
	 * @throws GWorkException the g work exception
	 */

	@SuppressWarnings("unchecked")
	public List<Users> employeesCIAT(String paramNombre, String paramApellido,
			String carne) throws GWorkException {

		List<Users> listaUsersCIAT = null;
		List<Object[]> arreglo = null;

		try {

			String sbQuery = "select emp_codigo, emp_nombre_completo,emp_apellido1, emp_email " 
					+ "from FINANZAS_INTERFAZ.V_EMPLEADO_SRH"
					+ " where emp_estado <> 'R' AND "
					+ "(emp_nombre_completo like '%"
					+ paramNombre
					+ "%' OR emp_apellido1 LIKE '%"
					+ paramApellido
					+ "%' OR emp_codigo LIKE '%"
					+ carne
					+ "%') ORDER BY emp_nombre_completo ASC";
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {
			log.error("employeesCIAT", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (arreglo != null && !arreglo.isEmpty() && arreglo.size() > 0) {
			listaUsersCIAT = new ArrayList<Users>();
			Users users = null;

			for (Object[] listObj : arreglo) {
				users = new Users();
				for (Object object : listObj) {

					if (listObj[0] != null)
						users.setUsrIdentificacion(listObj[0].toString());
					if (listObj[1] != null)
						users.setUsrNombre(listObj[1].toString());
					if (listObj[2] != null)
						users.setUsrApellido(listObj[2].toString());

					if (listObj[3] != null)
						users.setUsrEmail(listObj[3].toString());
				}
				listaUsersCIAT.add(users);
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

		EntityManagerHelper.closeEntityManager();
		return listaUsersCIAT;
	}

	/**
	 * Consulta para traer empleados del CIAT y relacionarlos como conductores
	 * en el modelo.
	 *
	 * @param paramNombre the param nombre
	 * @param paramCedula the param cedula
	 * @param carne the carne
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public List<Driver> driversCIAT(String paramNombre, String paramCedula,
			String carne) throws GWorkException {

		List<Driver> listaDriversCIAT = null;
		List<Object[]> arreglo = null;

		try {

			String sbQuery = "select emp_codigo,emp_nombre_completo,emp_cedula,emp_cargo " 
					+ "from FINANZAS_INTERFAZ.V_EMPLEADO_SRH"
					+ " where emp_estado <> 'R' AND "
					+ "(emp_nombre_completo like '%"
					+ paramNombre
					+ "%' OR emp_cedula LIKE '%"
					+ paramCedula
					+ "%' OR emp_codigo LIKE '%"
					+ carne
					+ "%') ORDER BY emp_cedula ASC";
			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {
			log.error("driversCIAT", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (arreglo != null && !arreglo.isEmpty() && arreglo.size() > 0) {
			listaDriversCIAT = new ArrayList<Driver>();
			Driver driver = null;

			for (Object[] listObj : arreglo) {
				driver = new Driver();
				for (Object object : listObj) {

					if (listObj[0] != null)
						driver.setDrvNumeroCarne(listObj[0].toString());
					if (listObj[1] != null)
						driver.setDrvNombre(listObj[1].toString());

					if (listObj[2] != null)
						driver.setDrvCedula(listObj[2].toString());

					if (listObj[3] != null)
						driver.setDrvCargo(listObj[3].toString());
				}
				listaDriversCIAT.add(driver);
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

		EntityManagerHelper.closeEntityManager();
		return listaDriversCIAT;
	}

	/**
	 * *************************************************************************
	 * Consulta el email del empleado por carnet.
	 *
	 * @param carnet the carnet
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultEmpleoyeeEmail(String carnet) throws GWorkException {
		String result = null;

		Query query = null;

		try {

			String sbQuery = "select RHINTERFAZ.F_RETURN_EMAIL_CARNET (?) from dual";
			query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);

			query.setParameter(1, carnet);
			result = (String) query.getSingleResult();
		} catch (Exception e) {
			log.error("consultEmpleoyeeEmail", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION"), e);
		}

		if (result == null || result.trim().length() == 0) {
			return "";
		}
		return result;

	}

	/**
	 * Consulta el nombre del empleado por carnet.
	 *
	 * @param carnet the carnet
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultEmpleoyeeName(String carnet) throws GWorkException {
		String result = null;
		Query query = null;

		try {
			String sbQuery = "select FINANZAS_INTERFAZ.F_ABW_RETURN_NOMBRE_AUXILIAR (?) from dual";
			query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			query.setParameter(1, carnet);
			result = (String) query.getSingleResult();
		} catch (Exception e) {
			log.error("consultEmpleoyeeName", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION"), e);
		}

		if (result == null || result.trim().length() == 0)
			throw new GWorkException(Util.loadErrorMessageValue("NOMBREEXISTEN"));

		return result;

	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#validateEmpleooyee(java.lang.String)
	 */
	public boolean validateEmpleooyee(String carnet) throws GWorkException {
		return false;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#centrosCosto(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CostsCenters> centrosCosto(String parametroCentroCosto)
			throws GWorkException {

		List<CostsCenters> listaCentroCosto = null;
		List<Object> arreglo = null;

		try {

			String sbQuery = "select ATTR_VALUE from FINANZAS_INTERFAZ.V_ABW_ACTIVE_AEC " +
					"where ATTR_VALUE like '%" + parametroCentroCosto + "%' AND STATUS = 'N'";
													 
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {
			log.error("centrosCosto", e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (arreglo != null && !arreglo.isEmpty()) {
			listaCentroCosto = new ArrayList<CostsCenters>();
			CostsCenters costsCenters = null;

			for (Object object : arreglo) {
				costsCenters = new CostsCenters();
				costsCenters.setCocNumero(object.toString());
				listaCentroCosto.add(costsCenters);
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
		return listaCentroCosto;
	}

	/**
	 * Centros costo.
	 *
	 * @return the list
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("unchecked")
	public static List<CostsCenters> centrosCosto() throws GWorkException {

		List<CostsCenters> listaCentroCosto = null;
		List<Object> arreglo = null;

		try {

			String sbQuery = "select ATTR_VALUE " +
					"from FINANZAS_INTERFAZ.V_ABW_ACTIVE_AEC " +
					"where STATUS = 'N'";
											 
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {
			log.error("centrosCosto", e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (arreglo != null && !arreglo.isEmpty()) {
			listaCentroCosto = new ArrayList<CostsCenters>();
			CostsCenters costsCenters = null;

			for (Object object : arreglo) {
				costsCenters = new CostsCenters();
				costsCenters.setCocNumero(object.toString());
				listaCentroCosto.add(costsCenters);
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}
		return listaCentroCosto;
	}

	/**
	 * Función que permita traer el número de la orden de compra a través de la
	 * placa del vehículo.
	 *
	 * Este dato no está contemplados en ABW por ahora. Estamos a la espera de confirmación.
	 *
	 * @param buyOrder the buy order
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultBuyOrder(String buyOrder) throws GWorkException {
		/*
		try {
			String sbQuery = "select ciatapps.f_fa_numero_po"
					+ Util.loadMessageValue("DBLINK") + "(?) from dual";
			Query query = null;
			query = EntityManagerHelper.getEntityManager().createNativeQuery(
					sbQuery);
			query.setParameter(1, buyOrder);
			String result = (String) query.getSingleResult();
			if (result != null && result.trim().length() != 0)
				return result;

		} catch (Exception e) {
			log.error("consultBuyOrder -> " + e.getMessage(), e);
		}
		*/
		return null;
	}

	/**
	 * Función que permita traer el proveedor a través de la placa del vehículo.
	 *
	 * @param supplier the supplier
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultSupplier(String supplier) throws GWorkException {
		/*
		try {
			String sbQuery = "select ciatapps.f_fa_proveedor_po"
					+ Util.loadMessageValue("DBLINK") + "(?) from dual";
					
			Query query = null;
			query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			query.setParameter(1, supplier);
			String result = (String) query.getSingleResult();
			if (result != null && result.trim().length() != 0)
				return result;

		} catch (Exception e) {
			log.error("consultSupplier -> " + e.getMessage(), e);
		}
		*/
		return null;
	}

	/**
	 * Función que permita traer el email del repsonsable del centro de costos.
	 *
	 * @param cencos the cencos
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String consultEmailResponsableCENCOS(String cencos)
			throws GWorkException {
		try {
			String sbQuery = "select FINANZAS_INTERFAZ.F_ABW_RETURN_EMAIL_RESP_CENCOS (?) from dual";
			Query query = null;
			query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			query.setParameter(1, cencos);
			String result = (String) query.getSingleResult();
			if (result != null && result.trim().length() != 0)
				return result;

		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Función que permita traer el proveedor a través de la placa del vehículo.
	 *
	 * @param placActivoFijo the plac activo fijo
	 * @param numero the numero
	 * @return the big decimal
	 * @throws GWorkException the g work exception
	 */
	public BigDecimal consultFuntions(String placActivoFijo, int numero)
			throws GWorkException {
		String sbQuery = null;
		String sbQueryCIF = "select FINANZAS_INTERFAZ.F_ABW_VALOR_ORIG_ACTIVO (?) from dual";
		String sbQueryFOB = "select ciatapps.f_fa_valor_FOB"
				+ Util.loadMessageValue("DBLINK") + "(?) from dual";
		String sbQueryFLETES = "select ciatapps.f_fa_valor_FLETES"
				+ Util.loadMessageValue("DBLINK") + "(?) from dual";
		String sbQueryUtil = "select FINANZAS_INTERFAZ.F_ABW_FA_VIDA_UTIL (?) from dual";

		BigDecimal result = new BigDecimal(0);

		try {
			if (numero == 1)
				sbQuery = sbQueryCIF;
			else if (numero == 2)
				sbQuery = sbQueryFOB;
			else if (numero == 3)
				sbQuery = sbQueryFLETES;
			if (numero == 4 || numero == 1){
				sbQuery = sbQueryUtil;

				Query query = null;
				query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
				query.setParameter(1, placActivoFijo);
				result = (BigDecimal) query.getSingleResult();
			}
			if (result != null && !result.equals(new BigDecimal(0)))
				return result;

			if (result != null && result.equals(new BigDecimal(0)))
				return null;

		} catch (Exception e) {
			log.error("consultFuntions -> " + e.getMessage(), e);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.consults.ConsultsService#
	 *      counsultarTipoMonedaAsignatario(java.lang.String)
	 */
	public Long counsultarTipoMonedaAsignatario(String carne)
			throws GWorkException {

		try {
			String sbQuery = "SELECT FINANZAS_INTERFAZ.F_RETURN_MONEDA_SRH (:carne)MONEDA FROM DUAL";
			Query query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			query.setParameter("carne", carne);
			String result = "";
			try {
				if (query.getResultList() != null
						&& query.getResultList().size() > 0
						&& query.getResultList().get(0) != null)
					result = query.getResultList().get(0).toString();

			} catch (RuntimeException e) {
				log.error("counsultarTipoMonedaAsignatario -> " + e.getMessage(), e);
				throw new GWorkException(Util
						.loadErrorMessageValue("ERRORCONEXION"), e);
			}
			if (result != null && result.trim().length() != 0)
				return new BigDecimal(result).longValue();

		} catch (Exception e) {
			log.error("counsultarTipoMonedaAsignatario -> " + e.getMessage(), e);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.consults.ConsultsService
	 *      #consultarTasaConversion(java.util.Date)
	 */
	public Float consultarTasaConversion(Date fechaActual)
			throws GWorkException {
		try {
			String sbQuery = "select TRM from FINANZAS_INTERFAZ.T_ABW_TRM";

			Query query = null;
			query = EntityManagerHelper.getEntityManager().createNativeQuery(sbQuery);
			String result = "";
			try {
				if (query.getResultList().size() > 0)
					result = query.getSingleResult().toString();
			} catch (Exception e) {
				throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
						+ "\n" + e.getCause().getCause().getMessage());
			}

			if (result != null && result.trim().length() != 0)
				return Float.parseFloat(result);
			else{
				throw new GWorkException(Util.loadErrorMessageValue("ERROR.TASACONVERSION_NULL") + fechaActual);
			}
		} catch (RuntimeException e) {
			log.error("consultarTasaConversion -> " + e.getMessage(), e);
		}

		return null;
	}

	/**
	 * Metodo que consulta la disponibilidad presupuestal de un centro de costo.
	 *
	 * @param anho the anho
	 * @param codigoCentroCosto the codigo centro costo
	 * @param cuenta the cuenta
	 * @param auxiliar the auxiliar
	 * @param valor the valor
	 * @return true, if successful
	 * @throws GWorkException the g work exception
	 */
	public boolean disponibilidaCombustibleCC(Integer anho,
			String codigoCentroCosto, String cuenta, String auxiliar, Long valor)
			throws GWorkException {

		String result = null;
		String queryString = "select CIATAPPS.F_VALIDACION_PPTAL_ORACLE"
				+ Util.loadMessageValue("DBLINK")
				+ "(:anho,:codigoCentroCosto,:cuenta,:auxiliar,:valor) campo FROM DUAL";
		Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);
		query.setParameter("anho", anho);
		query.setParameter("codigoCentroCosto", codigoCentroCosto);
		query.setParameter("cuenta", cuenta); // 51410300 para prepago, el que habia antes 51110501
		query.setParameter("auxiliar", null);
		query.setParameter("valor", valor);

		try {
			result = (String) query.getSingleResult();
		} catch (RuntimeException e) {
			log.error("disponibilidaCombustibleCC -> " + e.getMessage(), e);
			throw new GWorkException(Util.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}

		if (result == null || !result.equalsIgnoreCase("Y"))
			validaPresupuesto = false;

		if (result != null && result.equalsIgnoreCase("Y"))
			validaPresupuesto = true;

		return validaPresupuesto;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#consultarDisponibiladaPresupuestal(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Double)
	 */
	public boolean consultarDisponibiladaPresupuestal(Integer anho,
			String codigoCentroCosto, String cuenta, String auxiliar,
			Double valor) throws GWorkException {

		String queryString = "select FINANZAS_INTERFAZ.F_ABW_AVAILABLE_BDG( '" + codigoCentroCosto + "'," + valor + ") campo FROM DUAL";
		Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);

		String result = "";

		try {
			result = (String) query.getSingleResult();

		} catch (RuntimeException e) {
			log.error("consultarDisponibiladaPresupuestal -> " + e.getMessage(), e);
			throw new GWorkException(Util.loadErrorMessageValue("DISPONIBILDADPRESUPUESTAL"), e);
		}
		if (result != null)
			if (!result.equalsIgnoreCase("NO_DATA_FOUND") && !result.equalsIgnoreCase("N"))
				validaPresupuesto = true;
		validaPresupuesto = true;
		return validaPresupuesto;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#consultarDisponibilidadAlquilerCC(java.util.List, java.lang.Long, java.lang.Long)
	 */
	public void consultarDisponibilidadAlquilerCC(
			List<CostsCentersVehicles> listCostsCenters, Long idTipoVehiculo,
			Long claseSoliicitud, Long legateesTypes) throws GWorkException {

		if ((listCostsCenters != null && listCostsCenters.size() > 0 && !listCostsCenters.isEmpty()) && 
				(idTipoVehiculo != null && idTipoVehiculo.longValue() != -1L && idTipoVehiculo != 0L)) {

			for (CostsCentersVehicles centersRequests : listCostsCenters) {

				Double valorCC = DisponibilidadPresupuestalAlquiler.consultarDisponibilidadAlquiler(idTipoVehiculo);
				Double porcentaje = new Double(centersRequests.getCcrPorcentaje()) / new Double(100);
				valorCC = valorCC * porcentaje;
				AccountingParameters parameters = null;
				String cuenta = null;

				if (claseSoliicitud.longValue() == ParametersUtil.CLASE_ALQUILER.longValue())
					parameters = SearchParametersBilling.consultarParemeter(
							ParametersUtil.DEBITO,
							ParametersUtil.CARGO_NO_APLICA,
							ParametersUtil.TRASACCTION_TYPE_RENT,
							legateesTypes,
							ParametersUtil.SEDE);

				else if (claseSoliicitud.longValue() == ParametersUtil.CLASE_ALQUILER_TERCEROS.longValue())
					parameters = SearchParametersBilling.consultarParemeter(
							ParametersUtil.DEBITO,
							ParametersUtil.CARGO_TERCEROS,
							ParametersUtil.TRASACCTION_TYPE_RENT,
							legateesTypes,
							ParametersUtil.SEDE);

				if (parameters == null)
					throw new GWorkException("No existe el parametro contable para alquileres");
				else
					cuenta = parameters.getAccount().getAccNumeroCuenta();

				Calendar calendario = Calendar.getInstance();
				calendario.setTime(new Date()); // fecha es el Date de antes.
				int anho = calendario.get(Calendar.YEAR);

				boolean valido = validarPresupuesto(anho, 
						centersRequests.getCostsCenters().getCocNumero(), 
						cuenta, null,valorCC);

				if (!valido)
					throw new GWorkException(Util.loadErrorMessageValue("DISPONIBILDADPRESUPUESTAL")
							+ centersRequests.getCostsCenters().getCocNumero());
			}
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			

			Float val = Float.valueOf("99999.96");
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("#.00",simbolos);
			Float valor = Float.valueOf(df.format(val));
			System.out.println(valor);
			System.out.println("Fin");			
			
			//new ConsultsServiceImpl().validarPresupuesto(2008, "CS31","51110728", null, 1000D);

		} catch (Exception e) {
			log.error("main -> " + e.getMessage(), e);
		}
	}
	
	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#validarPresupuesto(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Double)
	 */
	public boolean validarPresupuesto(Integer anho, String codigoCentroCosto,
			String cuenta, String auxiliar, Double valor) throws GWorkException {

		String queryString = "select FINANZAS_INTERFAZ.F_ABW_AVAILABLE_BDG( '" + codigoCentroCosto + "'," + valor + ") campo FROM DUAL";
		Query query = EntityManagerHelper.getEntityManager().createNativeQuery(queryString);

		String result = "";
		
		try {
			result = (String) query.getSingleResult();

		} catch (RuntimeException e) {
			log.error("validarPresupuesto -> " + e.getMessage(), e);
			throw new GWorkException(Util.loadErrorMessageValue("DISPONIBILDADPRESUPUESTAL"), e);
		}
		if (result != null)
			if (!result.equalsIgnoreCase("NO_DATA_FOUND") && !result.equalsIgnoreCase("N"))
				validaPresupuesto = true;
		validaPresupuesto = true;
		return validaPresupuesto;		
	}

	/**
	 * Insercion contable.
	 *
	 * @param PSob the p sob
	 * @param PAccdate the p accdate
	 * @param PCurr the p curr
	 * @param PUser the p user
	 * @param PCategory the p category
	 * @param PSource the p source
	 * @param PConvDate the p conv date
	 * @param PConvType the p conv type
	 * @param PConvRate the p conv rate
	 * @param PCompany the p company
	 * @param PAccount the p account
	 * @param PCcenter the p ccenter
	 * @param PAuxiliary the p auxiliary
	 * @param PEntDr the p ent dr
	 * @param PEntCr the p ent cr
	 * @param PBname the p bname
	 * @param PDescription the p description
	 * @param PNit the p nit
	 * @param PAttribute2 the p attribute2
	 * @param PAttribute5 the p attribute5
	 * @param PAttribute6 the p attribute6
	 * @param PAttribute7 the p attribute7
	 * @param PAttribute8 the p attribute8
	 * @param PAttribute9 the p attribute9
	 * @param PAttribute10 the p attribute10
	 * @param pGroupId the group id
	 * @throws GWorkException the g work exception
	 */
	public static void insercionContable(Long PSob, Date PAccdate,
			String PCurr, String PUser, String PCategory, String PSource,
			Date PConvDate, String PConvType, Long PConvRate, String PCompany,
			String PAccount, String PCcenter, String PAuxiliary, Float PEntDr,
			Float PEntCr, String PBname, String PDescription, String PNit,
			String PAttribute2, String PAttribute5, String PAttribute6,
			String PAttribute7, String PAttribute8, String PAttribute9,
			String PAttribute10, String pGroupId) throws GWorkException {

		Connection connection = null;
		
		try {
			connection = getConnection("jdbc/siscarFinanciero");

			final String queryString = "call CIATAPPS.P_ACTUAL_OTHERS_APPLICATIONS"
					+ Util.loadMessageValue("DBLINK.FINANCIERO")
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			CallableStatement statement = connection.prepareCall(queryString);
			java.sql.Date date = new java.sql.Date(PAccdate.getTime());

			if (PEntCr == null)
				PEntCr = 0F;

			if (PEntDr == null)
				PEntDr = 0F;

			statement.setLong(1, PSob);
			statement.setDate(2, date);
			statement.setString(3, PCurr.trim());
			statement.setString(4, PUser.toUpperCase().trim());
			statement.setString(5, PCategory);
			statement.setString(6, PSource);
			statement.setDate(7, null);
			statement.setString(8, "");
			statement.setInt(9, 1);
			statement.setString(10, PCompany);
			statement.setString(11, PAccount);
			statement.setString(12, PCcenter.trim());
			statement.setString(13, PAuxiliary);
			statement.setFloat(14, PEntDr);
			statement.setFloat(15, PEntCr);
			statement.setString(16, PBname);
			statement.setString(17, PDescription);
			statement.setString(18, PNit);
			statement.setString(19, PAttribute2);
			statement.setString(20, PAttribute5);
			statement.setString(21, PAttribute6);
			statement.setString(22, PAttribute7);
			statement.setString(23, PAttribute8);
			statement.setString(24, PAttribute9);
			statement.setString(25, PAttribute10);
			statement.setLong(26, new Long(pGroupId));
			statement.registerOutParameter(27, Types.VARCHAR);
			statement.execute();

			connection.commit();
			System.out.println(statement.getString(27) + "-" + PBname + "-"
					+ PDescription);

		} catch (Exception e) {
			log.error("insercionContable -> " + e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}finally{
			try{
				if (connection!=null && !connection.isClosed()){
					connection.close();
				}
			}catch(Exception ex2){
				log.error("Error: " + ex2.getMessage(), ex2);
				throw new GWorkException(ex2.getMessage(), ex2);
			}
		}

	}

	/**
	 * Insercion contable sin autocommit.
	 *
	 * @param connection the connection
	 * @param PSob the p sob
	 * @param PAccdate the p accdate
	 * @param PCurr the p curr
	 * @param PUser the p user
	 * @param PCategory the p category
	 * @param PSource the p source
	 * @param PConvDate the p conv date
	 * @param PConvType the p conv type
	 * @param PConvRate the p conv rate
	 * @param PCompany the p company
	 * @param PAccount the p account
	 * @param PCcenter the p ccenter
	 * @param PAuxiliary the p auxiliary
	 * @param PEntDr the p ent dr
	 * @param PEntCr the p ent cr
	 * @param PBname the p bname
	 * @param PDescription the p description
	 * @param PNit the p nit
	 * @param PAttribute2 the p attribute2
	 * @param PAttribute5 the p attribute5
	 * @param PAttribute6 the p attribute6
	 * @param PAttribute7 the p attribute7
	 * @param PAttribute8 the p attribute8
	 * @param PAttribute9 the p attribute9
	 * @param PAttribute10 the p attribute10
	 * @param pGroupId the group id
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	@SuppressWarnings("finally")
	public static Connection insercionContableSinAutocommit(Connection connection, Long PSob,
			Date PAccdate, String PCurr, String PUser, String PCategory,
			String PSource, Date PConvDate, String PConvType, Long PConvRate,
			String PCompany, String PAccount, String PCcenter,
			String PAuxiliary, Float PEntDr, Float PEntCr, String PBname,
			String PDescription, String PNit, String PAttribute2,
			String PAttribute5, String PAttribute6, String PAttribute7,
			String PAttribute8, String PAttribute9, String PAttribute10,
			String pGroupId, String idMaster, Long idDetail) throws GWorkException {

		String error = "";
		
		try {
			
			final String queryString = "call FINANZAS_INTERFAZ.P_ABW_INSERTGL_OTHERAPP"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			CallableStatement statement = connection.prepareCall(queryString);		
			java.sql.Date date = new java.sql.Date(PAccdate.getTime());

			/*if (PEntCr == null)
				PEntCr = 0F;

			if (PEntDr == null)
				PEntDr = 0F;*/

			statement.setLong(1, PSob);
			statement.setDate(2, date);
			statement.setString(3, PCurr.trim());
			statement.setString(4, PUser.toUpperCase().trim());
			statement.setString(5, PCategory);
			statement.setString(6, PSource);
			statement.setDate(7, null);
			statement.setString(8, "");
			statement.setInt(9, 1);
			statement.setString(10, PCompany);
			statement.setString(11, PAccount);
			statement.setString(12, PCcenter.trim());
			statement.setString(13, null); //bline P_BLINE
			String auxiliar = "00000000";
			
			if(PAuxiliary.equals("00000000"))
				PAuxiliary = null;
			
			statement.setString(14, PAuxiliary);//PAuxiliary
			statement.setString(15, null); //tipo de control P_CTYPE 
			statement.setString(16, null); // futuro P_FUTURE

			/*NumberFormat numberFormatter;
			String amountOut;
			
			Locale currentLocale = new Locale("en","US");
			numberFormatter = NumberFormat.getNumberInstance(currentLocale);
			*/
			DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
			simbolos.setDecimalSeparator('.');
			DecimalFormat df = new DecimalFormat("#.00",simbolos);

			if (PEntDr == null)
				statement.setString(17, null);
			else{
				//Double valor = Double.valueOf(df.format(PEntDr.doubleValue()));
				Float valor = Float.valueOf(df.format(PEntDr));
				//Double valor = Util.convertirDecimalConDouble(PEntDr.toString());
				
				if(PEntDr < 0) valor = valor * -1;
				//amountOut = numberFormatter.format(valor);
				statement.setFloat(17, valor);
			}
			
			if (PEntCr == null)
				statement.setString(18, null);
			else{
				//Double valor = Double.valueOf(df.format(PEntCr.doubleValue()));
				//Double valor = Util.convertirDecimalConDouble(PEntCr.toString());
				Float valor = Float.valueOf(df.format(PEntCr));

				if(PEntCr < 0) valor = valor * -1;
				statement.setFloat(18, valor);
			}
			
			/*statement.setFloat(17, PEntDr);
			statement.setFloat(18, PEntCr);*/
			statement.setString(19, PBname);
			statement.setString(20, PDescription);
			statement.setString(21, PNit);
			statement.setString(22, PAttribute2);
			statement.setString(23, PAttribute5);
			statement.setString(24, PAttribute6);
			statement.setString(25, PAttribute7);
			statement.setString(26, PAttribute8);
			statement.setString(27, PAttribute9);
			statement.setString(28, PAttribute10);
			statement.setLong(29, new Long(pGroupId));
			statement.setObject (30,null ); //P_ENCUMB_ID  number
			statement.setString(31, null);//P_BALANCE_TYPE
			statement.setString(32, null);//P_REFERENCE6
			statement.setString(33, null);//P_REFERENCE23
			statement.setString(34, null);//P_REFERENCE24
			statement.setString(35, null);//P_REFERENCE25
			statement.setString(36, idMaster);//P_ID_MASTER se debe llamar a la funcion FINANZAS_INTERFAZ.F_ABW_GET_MASTER_ID('VEHICLES')
			statement.setString(37, idDetail.toString());//P_ID_DETAIL
			statement.setString(38, "VH");//P_TYPE
			statement.registerOutParameter(39, Types.VARCHAR);
			connection.setAutoCommit(false);
			statement.execute();

			error = statement.getString(39) + "-" + PBname + "-";
			
			if (statement.getString(39) == null){
				return connection;
			}else{
				connection = null;				
				throw new Exception(error);
			}
		} catch (Exception e) {			
			log.error("Error grabando en la interfaz contable.... Descripcion: "
					+ PDescription + " Error: " + error, e);			
			throw new GWorkException(e.getMessage(), e);
		}finally{
			return connection;
		}
	}
	
	@SuppressWarnings("finally")
	public static Connection insertTMaster(Connection connection, String idMaster, 
			String status, int numRows) throws GWorkException {

		String error = "";
		
		try {
			
			final String queryString = "CALL finanzas_interfaz.P_ABW_INSERT_T_MASTER (?, ?, ?, ?)";

			CallableStatement statement = connection.prepareCall(queryString);		
			//Parametros de entrada

            statement.setString(1, idMaster);
            statement.setString(2, status);
            statement.setInt(3, numRows);

            statement.registerOutParameter(4,Types.VARCHAR);                         
			connection.setAutoCommit(false);
			statement.execute();

			error = statement.getString(4);
			 
			if (statement.getString(4) == null){
				return connection;
			}else{
				connection = null;				
				throw new Exception(error);
			}
		} catch (Exception e) {			
			log.error("Error grabando en insertTMaster: Error: " + error, e);			
			throw new GWorkException(e.getMessage(), e);
		}finally{
			return connection;
		}
	}

	
	
	public String getIdMaster() throws GWorkException {      
		String result = "";
		try {
			
			String sbQuery = "select FINANZAS_INTERFAZ.F_ABW_GET_MASTER_ID('VH') MASTERID from dual";
			Query query = null;
			query = EntityManagerHelper.getEntityManager().createNativeQuery(
					sbQuery);
			
			try {
				if (query.getResultList().size() > 0)
					result = query.getSingleResult().toString();
			} catch (Exception e) {
				throw new GWorkException(Util
						.loadErrorMessageValue("ERRORCONEXION")
						+ "\n" + e.getCause().getCause().getMessage());
			}
			
		} catch (RuntimeException e) {
			log.error("ERROR: getIdMaster -> " + e.getMessage(), e);
		}
		return result;
    }
	
	
	
	
	/**
	 * Consulta los vehiculos de activos fijos.
	 *
	 * @param strPlacaActivoFijo the str placa activo fijo
	 * @return the string
	 * @throws GWorkException the g work exception
	 */
	public String carnetVehiculoActivoFijo(String strPlacaActivoFijo)
			throws GWorkException {
		String result = null;
		/*
		Query query = null;
		try {
			final String queryString = "SELECT F_FA_carnet_asig"
					+ Util.loadMessageValue("DBLINK")
					+ "(:p_placa_num)carnet_asig from dual";
			query = EntityManagerHelper.getEntityManager().createNativeQuery(
					queryString);
			query.setParameter("p_placa_num", strPlacaActivoFijo);

			result = (String) query.getSingleResult();

		} catch (Exception e) {
			log.error("carnetVehiculoActivoFijo -> " + e.getMessage(), e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);
		}
		*/
		return result;
	}

	/**
	 * Consulta la lista de carnes auxiliares del CIAT.
	 *
	 * @param Parametro the parametro
	 * @return the list
	 * @throws GWorkException the g work exception
	 */

	@SuppressWarnings("unchecked")
	public List<Users> nitProveedor(String Parametro) throws GWorkException {

		List<Object[]> arreglo = null;
		Users users = null;
		List<Users> listaUsersCIAT = null;

		try {
			String sbQuery = "SELECT SEGMENT1 NIT_PROVEEDOR, VENDOR_NAME "
					+ "FROM apps.PO_VENDORS" + Util.loadMessageValue("DBLINK")
					+ " WHERE VENDOR_NAME LIKE '%" + Parametro + "%'"
					+ " ORDER BY VENDOR_NAME ";
			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(sbQuery);
			arreglo = query.getResultList();

		} catch (Exception e) {
			log.error("nitProveedor -> " + e.getMessage(), e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);

		}

		if (arreglo != null && !arreglo.isEmpty() && arreglo.size() > 0) {
			listaUsersCIAT = new ArrayList<Users>();
			for (Object[] listObj : arreglo) {
				users = new Users();
				for (Object object : listObj) {
					if (listObj[0] != null)
						users.setUsrIdentificacion(listObj[0].toString());
					if (listObj[1] != null)
						users.setUsrNombre(listObj[1].toString());
				}
				listaUsersCIAT.add(users);
			}
		} else {
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
		}

		EntityManagerHelper.closeEntityManager();
		return listaUsersCIAT;
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#CodigoProveedorByNit(java.lang.String)
	 */
	public String CodigoProveedorByNit(String Parametro) throws GWorkException {

		String CodigoProveedor = "";

		try {

			String sbQuery = "SELECT CC.SEGMENT5 "
					+ "FROM APPS.PO_VENDORS"
					+ Util.loadMessageValue("DBLINK")
					+ " V"
					+ ", APPS.PO_VENDOR_SITES_ALL"
					+ Util.loadMessageValue("DBLINK")
					+ " S"
					+ ", APPS.GL_CODE_COMBINATIONS"
					+ Util.loadMessageValue("DBLINK")
					+ " CC"
					+ " WHERE   V.VENDOR_ID = S.VENDOR_ID"
					+ " AND S.ACCTS_PAY_CODE_COMBINATION_ID = CC.CODE_COMBINATION_ID "
					+ " AND V.SEGMENT1 = '" + Parametro + "'"
					+ " AND ROWNUM = 1";
			Query query = EntityManagerHelper.getEntityManager()
					.createNativeQuery(sbQuery);
			CodigoProveedor = (String) query.getSingleResult();

			EntityManagerHelper.closeEntityManager();
		} catch (Exception e) {
			log.error("CodigoProveedorByNit -> " + e.getMessage(), e);
			throw new GWorkException(Util
					.loadErrorMessageValue("ERRORCONEXION")
					+ "\n" + e.getCause().getCause().getMessage(), e);

		}

		if (CodigoProveedor != null)
			return CodigoProveedor;
		else
			throw new GWorkException(Util.loadErrorMessageValue("CONSULTA"));
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#insercionCabeceraFactura(java.sql.Connection, java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.Long, java.util.Date, java.lang.String, java.lang.String, java.util.Date, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Long)
	 */
	public List<InvoicesHeaderPoliciesVO> insercionCabeceraFactura(Connection connection,
			String NumeroFactura, String TipoFactura, Date FechaFactura,
			String Nit, Long TotalFactura, Date FechaRecibidoFactura,
			String MonedaFactura, String TipoConversion, Date FechaCoversion,
			Long TasaConversion, String Descripcion, String Login,
			String OrigenVehiculo, Long inh_Codigo) throws GWorkException {

		List<InvoicesHeaderPoliciesVO> listInvoicesHeaderPolicies = new ArrayList<InvoicesHeaderPoliciesVO>();
		InvoicesHeaderPoliciesVO invoicesHeaderPoliciesVO = new InvoicesHeaderPoliciesVO();
		try {
			java.sql.Date dtFechaFactura = new java.sql.Date(FechaFactura
					.getTime());
			java.sql.Date dtFechaRecibidoFactura = new java.sql.Date(
					FechaRecibidoFactura.getTime());
			java.sql.Date dtFechaCoversion = new java.sql.Date(FechaCoversion
					.getTime());

			if(connection == null){
				connection = getConnection("jdbc/siscarFinanciero");
			}

			final String queryString = "call Ciatapps.P_AP_INSERT_FACTURA_HEADER"
					+ Util.loadMessageValue("DBLINK.FINANCIERO")
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//final String queryString = "";
			CallableStatement statement = connection.prepareCall(queryString);

			statement.setString(1, NumeroFactura.trim());
			statement.setString(2, TipoFactura.trim());
			statement.setDate(3, dtFechaFactura);
			statement.setString(4, Nit.trim());
			statement.setLong(5, TotalFactura);
			statement.setDate(6, dtFechaRecibidoFactura);
			statement.setString(7, MonedaFactura.trim());
			statement.setString(8, TipoConversion.trim());
			statement.setDate(9, dtFechaCoversion);
			statement.setLong(10, 0L);
			statement.setString(11, Descripcion);
			statement.setString(12, Login);
			statement.setString(13, OrigenVehiculo);
			statement.setString(14, MonedaFactura);
			// error
			statement.registerOutParameter(15, Types.VARCHAR);
			// mensaje de grabado
			statement.registerOutParameter(16, Types.VARCHAR);
			// secuencia de la factura
			statement.registerOutParameter(17, Types.NUMERIC);
			// origen Id
			statement.registerOutParameter(18, Types.NUMERIC);
			// Location
			statement.registerOutParameter(19, Types.NUMERIC);
			// Codigo usuario
			statement.registerOutParameter(20, Types.NUMERIC);
			// codigo de la cabecera del la factura
			statement.setLong(21, inh_Codigo);

			connection.setAutoCommit(false);
			statement.execute();
			
			if (statement.getString(15) != null)
				System.out.println("Valor parámetro 15: " + statement.getString(15));
			if (statement.getString(16) != null)
				System.out.println("Valor parámetro 16: " + statement.getString(16));
			if (statement.getString(17) != null)
				System.out.println("Valor parámetro 17: " + statement.getString(17));
			if (statement.getString(18) != null)
				System.out.println("Valor parámetro 18: " + statement.getString(18));
			if (statement.getString(19) != null)
				System.out.println("Valor parámetro 19: " + statement.getString(19));
			
			invoicesHeaderPoliciesVO.setStrError(statement.getString(15));
			invoicesHeaderPoliciesVO.setStrGraba(statement.getString(16));
			invoicesHeaderPoliciesVO.setLgSecuenciaFactura(statement
					.getLong(17));
			invoicesHeaderPoliciesVO.setLgOrigenId(statement.getLong(18));
			invoicesHeaderPoliciesVO.setLgUbicacion(statement.getLong(19));
			invoicesHeaderPoliciesVO.setLgCodigoUsuario(statement.getLong(20));

			listInvoicesHeaderPolicies.add(invoicesHeaderPoliciesVO);

			return listInvoicesHeaderPolicies;
		} catch (Exception e) {
			log.error("insercionCabeceraFactura -> " + e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see geniar.siscar.consults.ConsultsService#insercionDetalleFactura(java.sql.Connection, java.lang.Long, java.lang.Long, java.lang.String, java.util.Date, java.lang.Long, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long, java.lang.String, java.lang.Long)
	 */
	public Connection insercionDetalleFactura(Connection connection, Long PInvoiceId, Long PLineNum,
			String PInvoiceNum, Date PInvoiceDate, Long PInvoiceAmount,
			String PDescription, Long PUserId, String PPlacaVeh,
			String PCompany, String PAccount, String PCCenter,
			String PAuxiliary, Long POrgId, Long PLocation, String Nit,
			Long codigoInvoiceDetail) throws GWorkException {

		try {
			java.sql.Date dtFechaFactura = new java.sql.Date(PInvoiceDate
					.getTime());

			if(connection == null){
				connection = getConnection("jdbc/siscarFinanciero");
			}

			final String queryString = "call Ciatapps.P_AP_INSERT_FACTURA_DETAIL"
					+ Util.loadMessageValue("DBLINK.FINANCIERO")
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			CallableStatement statement = connection.prepareCall(queryString);

			statement.setLong(1, PInvoiceId);
			statement.setLong(2, PLineNum);
			statement.setString(3, PInvoiceNum.trim());
			statement.setDate(4, dtFechaFactura);
			statement.setLong(5, PInvoiceAmount);
			statement.setString(6, PDescription.trim());
			statement.setLong(7, PUserId);
			statement.setString(8, PPlacaVeh.trim());
			statement.setString(9, PCompany.trim());
			statement.setString(10, PAccount.trim());
			statement.setString(11, PCCenter.trim());
			statement.setString(12, PAuxiliary.trim());
			statement.setLong(13, POrgId);
			statement.setLong(14, PLocation);
			statement.setString(15, Nit.trim());
			statement.setLong(16, codigoInvoiceDetail);
			statement.registerOutParameter(17, Types.VARCHAR);

			connection.setAutoCommit(false);
			statement.execute();
			//connection.commit();

			if (statement.getString(17) == null)
				return connection;
			else{
				System.out.println("Posible error, valor parámetro 17: " + statement.getString(17));
			}

		} catch (Exception e) {
			log.error("insercionDetalleFactura -> " + e.getMessage(), e);
			throw new GWorkException(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * Gets the connection.
	 *
	 * @param entityManagerImpl the entity manager impl
	 * @return the connection
	 * @throws GWorkException the g work exception
	 */
	public static Connection getConnection(String jndi)
			throws GWorkException {
		
		Connection conn = null;
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			Object objRes = envContext.lookup(jndi);
			
			log.info("SAI. Recurso de conexión establecido: " + objRes!=null?objRes.getClass().getName():"nulo");
			
			//oracle.jdbc.pool.OracleDataSource ds = (oracle.jdbc.pool.OracleDataSource) objRes;
			DataSource ds = (DataSource)objRes;

			if (envContext == null) throw new Exception("Error: No Context");
			if (ds == null) throw new Exception("Error: No DataSource");
			if (ds != null) conn = ds.getConnection();
			
			return conn;
		} catch (Exception e) {
			throw new GWorkException("SAI. La conexión se encuentra cerrada", e);
		}
		
//		EntityManagerImpl manager = entityManagerImpl;
//		Connection connection = manager.getSession().connection();
//
//		try {
//			if (connection.isClosed()) {
//				System.out.println("La conexión se encuentra cerrada.");
//				connection = closeCreateConnection();
//			}
//
//			PreparedStatement stm = connection
//					.prepareStatement("Select 1 from dual");
//			try {
//				stm.execute();
//			} catch (SQLException sqlE) {
//				log.error("getConnection -> " + sqlE.getMessage(), sqlE);
//				connection = closeCreateConnection();
//			} finally {
//				stm.close();
//			}
//		} catch (Exception e) {
//			throw new GWorkException("La conexión se encuentra cerrada", e);
//		}
//		return connection;
	}
}