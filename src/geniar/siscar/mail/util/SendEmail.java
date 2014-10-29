package geniar.siscar.mail.util;

import java.util.Properties;
import java.util.Vector;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {

	String emailto;
	String emailCC;
	String emailfrom;
	String smtphost;
	String emailmultipart;
	String msgSubject;
	String msgText;

	public SendEmail(String emailto, String emailfrom, String smtphost,
			String emailmultipart, String msgSubject, String msgText) {
		this.emailto = emailto;
		this.emailCC = "";
		this.emailfrom = emailfrom;
		this.smtphost = smtphost;
		this.emailmultipart = emailmultipart;
		this.msgSubject = msgSubject;
		this.msgText = msgText;
	}

	public SendEmail(String emailto, String emailCC, String emailfrom,
			String smtphost, String emailmultipart, String msgSubject,
			String msgText) {
		this.emailto = emailto;
		this.emailCC = emailCC;
		this.emailfrom = emailfrom;
		this.smtphost = smtphost;
		this.emailmultipart = emailmultipart;
		this.msgSubject = msgSubject;
		this.msgText = msgText;
	}

	public String SendMessage() {
		boolean debug = false; // change to get more information
		String msgText2 = "multipart message";
		boolean sendmultipart = Boolean.valueOf(emailmultipart).booleanValue();
		
		try {
			// set the host
			Properties props = new Properties();
			props.put("mail.smtp.host", smtphost);
			// create some properties and get the default Session
			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(debug);
		
			// create a message
			Message msg = new MimeMessage(session);
			// set the from
			InternetAddress from = new InternetAddress(emailfrom);
			msg.setFrom(from);

			Vector<String> dirsTO = miscelanea.Split(emailto, ";");
			InternetAddress[] addressTO = new InternetAddress[dirsTO.size()];

			for (int i = 0; i < dirsTO.size(); i++) {
				addressTO[i] = new InternetAddress((String) dirsTO.elementAt(i));
			}
			msg.setRecipients(Message.RecipientType.TO, addressTO);

			if (emailCC != null && emailCC.length() > 0) {
				Vector<String> dirsCC = miscelanea.Split(emailCC, ";");
				InternetAddress[] addressCC = new InternetAddress[dirsCC.size()];

				for (int i = 0; i < dirsCC.size(); i++) {
					addressCC[i] = new InternetAddress((String) dirsCC
							.elementAt(i));
				}
				msg.setRecipients(Message.RecipientType.CC, addressCC);
			}
			// Para agregar los correos ocultos
			// msg.setRecipient(Message.RecipientType.BCC, );
			msg.setSubject(msgSubject);
			if (!sendmultipart) {
				// send a plain text message
				msg.setContent(msgText, "text/html");
			} else {
				// send a multipart message// create and fill the first message
				// part
				MimeBodyPart mbp1 = new MimeBodyPart();
				mbp1.setContent(msgText, "text/html");
				// create and fill the second message part
				MimeBodyPart mbp2 = new MimeBodyPart();
				mbp2.setContent(msgText2, "text/html");
				// create the Multipart and its parts to it
				Multipart mp = new MimeMultipart();
				mp.addBodyPart(mbp1);
				mp.addBodyPart(mbp2);
				// add the Multipart to the message
				msg.setContent(mp);
			}
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return "ERROR";
		}
		catch (Exception e) {
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}
}