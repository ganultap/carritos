package geniar.siscar.logic.billing.services.impl;

import geniar.siscar.consults.impl.ConsultsServiceImpl;
import geniar.siscar.logic.billing.services.PersonalMileageService;
import geniar.siscar.logic.consultas.SearchNoveltyTypes;
import geniar.siscar.logic.consultas.SearchPlainFileParameter;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.model.FlatFile;
import geniar.siscar.model.NoveltyTypes;
import geniar.siscar.model.Period;
import geniar.siscar.model.PlainFileParameter;
import geniar.siscar.model.Rolls;
import geniar.siscar.model.VehiclesAssignation;
import geniar.siscar.model.VhaFf;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.FlatFileDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.persistence.VhaFfDAO;
import geniar.siscar.util.ManipulacionFechas;
import geniar.siscar.util.ParametersUtil;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PersonalMileageImpl implements PersonalMileageService {
	private static final Log log = LogFactory.getLog(SendEmail.class);

	public void GuardarFlatFileKmPersonal(
			VehiclesAssignation vehiclesAssignation, String Recorrido,
			String Reportado, String Personal, Float tariff, String login,
			Date fechaIni, Date fechaFin, String kmInicial, String kmFinal,
			String kmPolitica) throws GWorkException {
		try {
			Util.validarSession();
			String ffCarne = null;
			Long ffConcepto = null;
			Long ffUnidades = null;
			String ffValor = null;
			String ffFecha = null;
			String ffDocumento = null;
			String ffDescripcion = null;
			String ffCentroCosto = null;
			Period period = null;
			Long TipoNomina = 0L;
			String Carnet;
			Long ffEstado = 0L;

			PlainFileParameter plainFileParameter = new PlainFileParameter();
			ConsultsServiceImpl consultsServiceImpl = new ConsultsServiceImpl();
			String formato = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
			FlatFile flatFile = new FlatFile();
			/*
			 * se consulta a la tabla de vehiculos asignacion por placa
			 * diplomatica para obtener la tarifa de ciat casa ciat
			 */

			if (vehiclesAssignation != null
					&& vehiclesAssignation.getRequests() != null) {
				vehiclesAssignation = EntityManagerHelper.getEntityManager()
						.merge(vehiclesAssignation);

				Carnet = vehiclesAssignation.getVhaNumeroCarne();
				ffUnidades = 1L;

				TipoNomina = consultsServiceImpl
						.counsultarTipoMonedaAsignatario(Carnet);

				/*
				 * se consultan los parametros del archivo plano para ciat casa
				 * ciat
				 */
				plainFileParameter = new SearchPlainFileParameter()
						.consultar_PFP_PorId(TipoNomina,
								ParametersUtil.TRASACCTION_CIAT_KMPERSONAL);

				ffCarne = Carnet;
				if (plainFileParameter != null)
					ffConcepto = plainFileParameter.getPfpConceptonomina();
				else
					ffConcepto = 0L;

				ffValor = tariff.toString();

				ffFecha = simpleDateFormat.format(new Date()).toString();
				ffDocumento = vehiclesAssignation.getVehicles()
						.getVhcPlacaDiplomatica();

				ffDescripcion = "Km Rec: " + Recorrido + ", Km Rep: "
						+ Reportado + ", Km Per: " + Personal;

				System.out.println("Tamanho descripcion: "
						+ ffDescripcion.length());

				String anhoCobro = ManipulacionFechas.getAgno(new Date());
				NoveltyTypes noveltyTypes = new SearchNoveltyTypes()
						.consultarTipoNovedadPorId(new Long(5));
				period = new BillingAccountAutoInsuranceServiceImpl()
						.consultarPeriodByAno(anhoCobro);
				flatFile.setFfCarne(ffCarne);
				flatFile.setFfConcepto(new Long(ffConcepto));
				flatFile.setFfUnidades(ffUnidades);
				flatFile.setFfValor(ffValor);
				flatFile.setFfFecha(Long.valueOf(ffFecha));
				flatFile.setFfDocumento(ffDocumento);
				flatFile.setFfMoneda(TipoNomina);
				flatFile.setFfDescripcion(ffDescripcion);
				flatFile.setFfCentroCosto(ffCentroCosto);
				flatFile.setFfUsuario(login);
				flatFile.setNoveltyTypes(noveltyTypes);
				flatFile.setPeriod(period);
				flatFile.setFfEstado(ffEstado);
				// flatFile.setTariff(tariff);

				FlatFileDAO FlatFileDao = new FlatFileDAO();

				FlatFileDao.save(flatFile);
				// EntityManagerHelper.getEntityManager().getTransaction().begin();
				// EntityManagerHelper.getEntityManager().getTransaction()
				// .commit();

				/* Creación de los datos de la tabla Vha_ff */
				String vhf_observacion = "Cobro Km Personal";

				/* Creación del Bean Vha_ff */
				VhaFf vha_Ff = new VhaFf();
				vha_Ff.setVhfObservacion(vhf_observacion);
				vha_Ff.setFlatFile(flatFile);
				vha_Ff.setVehiclesAssignation(vehiclesAssignation);

				// Envio de notificacion reportando el km personal
				try {

					String subject = Util
							.loadParametersValue("SUBJECT_KM_PERSONAL")
							+ " " + ffDocumento;

					notificarKilometrajePersonal(
							vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrEmail(),
							vehiclesAssignation.getVehicles()
									.getVhcPlacaDiplomatica(),
							vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrNombre(),
							vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrIdentificacion(),
							vehiclesAssignation.getRequests()
									.getLegateesTypes().getLgtNombre(),
							fechaIni, fechaFin, kmInicial, kmFinal, Recorrido,
							kmPolitica, Reportado, Personal, ffValor, subject);
				} catch (Exception e) {
					throw new GWorkException(e.getMessage());
				}

				/* Guardar el objeto en la BD */
				try {
					new VhaFfDAO().save(vha_Ff);
				} catch (Exception e) {
					e.printStackTrace();
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.GUARDAR"));
				}
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				// Util.limpiarSession();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR")
					+ ": " + e.getMessage());
		}
	}

	public void modificarFlatFileKmPersonal(
			VehiclesAssignation vehiclesAssignation, String Recorrido,
			String Reportado, String Personal, Float tariff, String login,
			Long idFlatFile, Date fechaIni, Date fechaFin, String kmInicial,
			String kmFinal, String kmPolitica) throws GWorkException {
		try {
			Util.validarSession();
			String ffCarne = null;
			Long ffConcepto = null;
			Long ffUnidades = null;
			String ffValor = null;
			String ffFecha = null;
			String ffDocumento = null;
			String ffDescripcion = null;
			String ffCentroCosto = null;
			Period period = null;
			Long TipoNomina = 0L;
			String Carnet;
			Long ffEstado = 0L;

			PlainFileParameter plainFileParameter = new PlainFileParameter();
			ConsultsServiceImpl consultsServiceImpl = new ConsultsServiceImpl();
			String formato = "yyyyMMdd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
			FlatFile flatFile = new FlatFileDAO().findById(idFlatFile);
			/*
			 * se consulta a la tabla de vehiculos asignacion por placa
			 * diplomatica para obtener la tarifa de ciat casa ciat
			 */

			if (vehiclesAssignation != null
					&& vehiclesAssignation.getRequests() != null) {

				Carnet = vehiclesAssignation.getVhaNumeroCarne();
				ffUnidades = 1L;

				TipoNomina = consultsServiceImpl
						.counsultarTipoMonedaAsignatario(Carnet);

				/*
				 * se consultan los parametros del archivo plano para ciat casa
				 * ciat
				 */
				plainFileParameter = new SearchPlainFileParameter()
						.consultar_PFP_PorId(TipoNomina,
								ParametersUtil.TRASACCTION_CIAT_KMPERSONAL);

				ffCarne = Carnet;
				if (plainFileParameter != null)
					ffConcepto = plainFileParameter.getPfpConceptonomina();
				else
					ffConcepto = 0L;

				ffValor = tariff.toString();

				ffFecha = simpleDateFormat.format(new Date()).toString();
				ffDocumento = vehiclesAssignation.getVehicles()
						.getVhcPlacaDiplomatica();

				ffDescripcion = "Km Rec: " + Recorrido + ", Km Rep: "
						+ Reportado + ", Km Per: " + Personal;

				System.out.println("Tamanho descripcion: "
						+ ffDescripcion.length());

				String anhoCobro = ManipulacionFechas.getAgno(new Date());
				NoveltyTypes noveltyTypes = new SearchNoveltyTypes()
						.consultarTipoNovedadPorId(new Long(5));
				period = new BillingAccountAutoInsuranceServiceImpl()
						.consultarPeriodByAno(anhoCobro);
				flatFile.setFfCarne(ffCarne);
				flatFile.setFfConcepto(new Long(ffConcepto));
				flatFile.setFfUnidades(ffUnidades);
				flatFile.setFfValor(ffValor);
				flatFile.setFfFecha(Long.valueOf(ffFecha));
				flatFile.setFfDocumento(ffDocumento);
				flatFile.setFfMoneda(TipoNomina);
				flatFile.setFfDescripcion(ffDescripcion);
				flatFile.setFfCentroCosto(ffCentroCosto);
				flatFile.setFfUsuario(login);
				flatFile.setNoveltyTypes(noveltyTypes);
				flatFile.setPeriod(period);
				flatFile.setFfEstado(ffEstado);
				// flatFile.setTariff(tariff);

				FlatFileDAO flatFileDao = new FlatFileDAO();

				// Envio de notificacion reportando el km personal
				try {
					String subject = Util
							.loadParametersValue("SUBJECT_KM_PERSONAL_MOD")
							+ " " + ffDocumento;
					notificarKilometrajePersonal(
							vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrEmail(),
							vehiclesAssignation.getVehicles()
									.getVhcPlacaDiplomatica(),
							vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrNombre(),
							vehiclesAssignation.getRequests()
									.getUsersByRqsUser().getUsrIdentificacion(),
							vehiclesAssignation.getRequests()
									.getLegateesTypes().getLgtNombre(),
							fechaIni, fechaFin, kmInicial, kmFinal, Recorrido,
							kmPolitica, Reportado, Personal, ffValor, subject);
				} catch (Exception e) {
					throw new GWorkException(e.getMessage());
				}

				/* Guardar el objeto en la BD */
				try {
					flatFileDao.save(flatFile);
				} catch (Exception e) {
					e.printStackTrace();
					throw new GWorkException(Util
							.loadErrorMessageValue("ERROR.GUARDAR"));
				}
				EntityManagerHelper.getEntityManager().getTransaction().begin();
				EntityManagerHelper.getEntityManager().getTransaction()
						.commit();
				// Util.limpiarSession();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new GWorkException(Util
					.loadErrorMessageValue("ERROR.GUARDAR"));
		}
	}

	public FlatFile getKmPersonalByYear(String placa, Date fechaInicial) {

		String queryString = "select ff from FlatFile ff "
				+ "where ff.ffDocumento = :placa "
				+ "and ff.noveltyTypes.ntId = :noveltyType "
				+ "and substr(ff.ffFecha, 1, 4) = :año";

		Query query = EntityManagerHelper.getEntityManager().createQuery(
				queryString);
		query.setParameter("placa", placa.toUpperCase());
		query.setParameter("noveltyType", ParametersUtil.NOVEDAD_KM_PERSONAL);
		query.setParameter("año", ManipulacionFechas.getAgno(fechaInicial));

		if (query.getResultList() != null && query.getResultList().size() > 0
				&& query.getResultList().get(0) != null)
			return (FlatFile) query.getResultList().get(0);

		else
			return null;

	}

	public void notificarKilometrajePersonal(String email, String placa,
			String nombreAsignatario, String carneAsignatario,
			String tipoAsignacion, Date fechaIni, Date fechaFin,
			String kmInicial, String kmFinal, String kmRecorrido,
			String kmPolitica, String kmReportado, String kmPersonal,
			String kmCobro, String subject) throws GWorkException {

		try {

			Rolls rolls = new RollsDAO().findById(new Long(Util
					.loadParametersValue("MAIL.ADMINISTRADOR")));

			// String emailTest = "C.MORALES@cgiar.org";
			String server = Util.loadParametersValue("MAIL.HOST");
			String fromUser = email;
			String toUser = rolls.getRlsMail();

			SimpleDateFormat fechaFormato = new SimpleDateFormat("dd-MMM-yyyy");

			StringBuffer detalleServicio = new StringBuffer();
			detalleServicio = detalleServicio.append(
					Util.loadParametersValue("SALUDO_MAIL_KM_PERSONAL"))
					.append("<BR>").append("<BR>").append(
							Util.loadParametersValue("VEHICULO_PLACA")).append(
							placa).append("<br>").append(
							Util.loadParametersValue("ASIGNATARIO")).append(
							nombreAsignatario).append("<br>").append(
							Util.loadParametersValue("CARNE_ASIG_PERSONAL"))
					.append(carneAsignatario).append("<br>").append(
							Util.loadParametersValue("ASIGNACION_PERSONAL"))
					.append(tipoAsignacion).append("<br>").append(
							Util.loadParametersValue("FECHA_INI")).append(
							fechaFormato.format(fechaIni)).append("<br>")
					.append(Util.loadParametersValue("FECHA_INI")).append(
							fechaFormato.format(fechaFin)).append("<br>")
					.append(Util.loadParametersValue("KM_INICIAL")).append(
							kmInicial).append("<BR>").append(
							Util.loadParametersValue("KM_FINAL")).append(
							kmFinal).append("<BR>").append(
							Util.loadParametersValue("KM_RECORRIDO")).append(
							kmRecorrido).append("<BR>").append(
							Util.loadParametersValue("KM_POLITICA")).append(
							kmPolitica).append("<BR>").append(
							Util.loadParametersValue("KM_REPORTADO")).append(
							kmReportado).append("<BR>").append(
							Util.loadParametersValue("KM_PERSONAL")).append(
							kmPersonal).append("<br>").append(
							Util.loadParametersValue("KM_COBRO_PERSONAL"))
					.append("$ ").append(kmCobro);

			SendEmail mail = new SendEmail(toUser, fromUser, server, "false",
					subject, detalleServicio.toString());

			String enviado = mail.SendMessage();
			if (enviado.equals("ERROR")) {
				log.info("No se pudo enviar el mensaje");
				throw new GWorkException(Util
						.loadErrorMessageValue("NOTIFICACION.ERROR"));
			} else
				log.info("Mensaje enviado exitosamente");

		} catch (Exception e) {
			throw new GWorkException(e.getMessage());
		}
	}

}
