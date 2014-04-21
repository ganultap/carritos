/**
 * 
 */
package geniar.siscar.logic.policies.services.impl;

import geniar.siscar.logic.policies.services.InconsistenciesService;
import geniar.siscar.mail.util.MailUtil;
import geniar.siscar.mail.util.SendEmail;
import geniar.siscar.mail.util.ThreadSendMail;
import geniar.siscar.model.Inconsistencies;
import geniar.siscar.model.PoliciesInvoice;
import geniar.siscar.model.Rolls;
import geniar.siscar.persistence.EntityManagerHelper;
import geniar.siscar.persistence.IInconsistenciesDAO;
import geniar.siscar.persistence.InconsistenciesDAO;
import geniar.siscar.persistence.RollsDAO;
import geniar.siscar.util.Util;
import gwork.exception.GWorkException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Geniar
 * 
 */
public class InconsistenciesServiceImpl implements InconsistenciesService {

	private static final Log log = LogFactory.getLog(SendEmail.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.
	 *      InconsistenciesService#crearInconsistencia( java.lang.Long,
	 *      java.lang.String, java.util.Date, java.lang.String, java.lang.Long,
	 *      java.lang.Long)
	 */
	public void crearInconsistencia(Long estado, String observacion,
			Date fechaCargue, String usrLogin, Long idTipoInconsistencia,
			Long idPoliza) throws GWorkException {
		new InconsistenciesServiceImpl().crearInconsistencia(estado,
				observacion, fechaCargue, usrLogin, idTipoInconsistencia,
				idPoliza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see geniar.siscar.logic.policies.services.
	 *      InconsistenciesService#crearInconsistencias(java.util.List)
	 */
	public void crearInconsistencias(List<Inconsistencies> lst)
			throws GWorkException {
		if (lst != null && !lst.isEmpty()) {

			IInconsistenciesDAO inconsistenciesDAO = new InconsistenciesDAO();

			try {

				for (Inconsistencies i : lst) {

					Inconsistencies inc = new Inconsistencies();

					inc.setIncEstado(i.getIncEstado());
					inc.setIncFechaCargue(i.getIncFechaCargue());
					inc.setIncObservacion(i.getIncObservacion());
					inc.setInconsistenciesTypes(i.getInconsistenciesTypes());
					inc.setIncValorAsegurado(i.getIncValorAsegurado());
					inc.setIncValorIva(i.getIncValorIva());
					inc.setIncValorPrima(i.getIncValorPrima());
					inc.setIncValorTotal(i.getIncValorTotal());
					inc.setLegateesTypes(i.getLegateesTypes());
					inc.setMonthTransacType(i.getMonthTransacType());
					inc.setPoliciesInvoice(i.getPoliciesInvoice());
					inc.setPvsCarnetAsignatario(i.getPvsCarnetAsignatario());
					inc.setUsrLogin(i.getUsrLogin());

					inc.setVhcPlaca(i.getVhcPlaca());

					Util.validarSession();
					EntityManagerHelper.beginTransaction();
					inconsistenciesDAO.save(inc);
					EntityManagerHelper.commit();
					// Util.limpiarSession();
				}
				enviarMail(lst.size(), lst.get(0).getPoliciesInvoice()
						.getPinNumeroFactura()
						+ "");
			} catch (RuntimeException e) {
				// Util.limpiarSession();
				throw new GWorkException(Util
						.loadErrorMessageValue("ERROR.GUARDTRASLPOLI"));
			}

		}
	}

	public void enviarMail(int cantidad, String numFactura)
			throws GWorkException {

		Rolls rolls = new RollsDAO().findById(new Long(Util
				.loadParametersValue("MAIL.ADMINISTRADOR")));

		// String server =
		// "smtp.gmail.com";//Util.loadParametersValue("MAIL.HOST");
		// String fromUser = "diego.bocanegra@geniar.net";//rolls.getRlsMail();
		String toUser = "jisela.torres@geniar.net";// rolls.getRlsMail();
		String subject = "[SISCAR] "
				+ Util.loadParametersValue("MSJ.INCONS.FACTURA");
		String body1 = Util.loadParametersValue("CUERPO.MSJ.INCONS.FAC1") + " ";
		String body2 = Util.loadParametersValue("CUERPO.MSJ.INCONS.FAC2");
		String body3 = Util.loadParametersValue("CUERPO.MSJ.INCONS.FAC3");

		MailUtil mailUtil = null;
		String server = "smtp.gmail.com";
		String starttlsenable = Util
				.loadParametersValue("MAIL.STARTTLS.ENABLE");
		String port = "587";
		String fromUser = "diego8087@gmail.com";
		String password = "87082103";
		String authentication = Util.loadParametersValue("MAIL.AUTH");

		// SendEmail mail = new SendEmail(toUser, fromUser, server, "false",
		// subject, body1 + cantidad + body2 + body3 + numFactura);

		mailUtil = new MailUtil(server, starttlsenable, port, fromUser,
				password, authentication, toUser, subject, body1 + cantidad
						+ " " + body2 + body3 + numFactura);

		if (mailUtil != null) {
			Thread thread = new Thread(new ThreadSendMail(mailUtil));
			thread.start();
		}

		// if (mail.SendMessage().equals("SUCCESS"))
		// log.info("Mensaje enviado exitosamente");
		// else {
		// log.info("Eror Enviando el mensaje");
		// throw new GWorkException(Util
		// .loadErrorMessageValue("NOTIFICACION.ERROR"));
		// }
	}

	public void modificarInconsistencia(Long estado, String observacion,
			Date fechaCargue, String usrLogin, Long idTipoInconsistencia,
			Long idPoliza) throws GWorkException {

	}

	public void modificarInconsistenciasFactura(PoliciesInvoice invoice)
			throws GWorkException {

		Iterator<Inconsistencies> iter = invoice.getInconsistencieses()
				.iterator();
		while (iter.hasNext()) {
			Inconsistencies inc = (Inconsistencies) iter.next();
			inc.setPoliciesInvoice(invoice);
			Util.validarSession();
			EntityManagerHelper.beginTransaction();
			new InconsistenciesDAO().update(inc);
			EntityManagerHelper.commit();
			// Util.limpiarSession();
		}
	}

	public void eliminarInconsistencia(Inconsistencies inc)
			throws GWorkException {
		Util.validarSession();
		EntityManagerHelper.beginTransaction();
		new InconsistenciesDAO().delete(inc);
		EntityManagerHelper.commit();
		// Util.limpiarSession();
	}

	public void eliminarInconsistencia(Long idInc) throws GWorkException {
		Util.validarSession();

		Inconsistencies inc = new InconsistenciesDAO().findById(idInc);

		EntityManagerHelper.beginTransaction();
		new InconsistenciesDAO().delete(inc);
		EntityManagerHelper.commit();
		// Util.limpiarSession();
	}

}
