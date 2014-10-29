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
 * 
 * @author jam
 * 
 */
public class EnviarMail {

	private static final Log log = LogFactory.getLog(EnviarMail.class);

	/**
	 * main de prueba
	 * 
	 * @param args
	 *            Se ignoran.
	 */
	public static void main(String[] args) {
		new EnviarMail().sendmail("smtp.gmail.com", true, 587, "pruebas.siscar@gmail.com", "pruebassiscar", true,
				"julian.marin@geniar.net", "Mensaje de prueba", "ESTO ES UNA PRUEBA DE UN MAIL");
	}

	public void sendmail(String server, boolean starttlsenable, int port, String fromUser, String password,
			boolean authentication, String toUser, String subject, String textmessage) {
		try {
			log.info("Enviando mail");
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", server);
			props.setProperty("mail.smtp.starttls.enable", "" + starttlsenable);
			props.setProperty("mail.smtp.port", "" + port);
			props.setProperty("mail.smtp.user", fromUser);
			props.setProperty("mail.smtp.auth", "" + authentication);

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
			log.error("sendmail Error: ", e);
		}
	}
}