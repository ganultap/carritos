package geniar.siscar.mail.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author Julian Andres Marin - GeniarArq S.A
 * @version 1.0 Descripción : Clase encargada del envio de correos electrónicos
 */
public class Mailer {

	private static final Log log = LogFactory.getLog(Mailer.class);

	/**
	 * Envia un correo electrónico
	 * 
	 * @param server
	 * @param starttlsenable
	 * @param port
	 * @param fromUser
	 * @param password
	 * @param authentication
	 * @param toUser
	 * @param subject
	 * @param textmessage
	 */
	public void sendmail(String server, String starttlsenable, String port, String fromUser, String password,
			String authentication, String toUser, String subject, String textmessage) {
		try {
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", server);
			props.setProperty("mail.smtp.starttls.enable", starttlsenable);
			props.setProperty("mail.smtp.port", port);
			props.setProperty("mail.smtp.user", fromUser);
			props.setProperty("mail.smtp.auth", authentication);

			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);

			// Construimos el mensaje
			MimeMessage message = new MimeMessage(session);
			// quien envia el correo
			if (fromUser != null)
				message.setFrom(new InternetAddress(fromUser));
			// a quien va dirigido
			if (toUser != null)
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
			message.setSubject(subject);
			message.setText(textmessage);

			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect(server, fromUser, password);
			t.sendMessage(message, message.getAllRecipients());

			// Cierre.
			t.close();

			log.info("Mensaje enviado exitosamente");

		} catch (Exception e) {
			e.printStackTrace();
			log.error("sendmail error: ", e);
		}
	}
}