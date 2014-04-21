package geniar.siscar.mail.util;


public class ThreadSendMail implements Runnable {

	private MailUtil mailUtil = null;

	public ThreadSendMail(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}

	public void run() {
		new Mailer().sendmail(mailUtil.getHost(), mailUtil.getStarttls(), mailUtil.getPort(), mailUtil.getFromuser(), mailUtil.getPassword(), mailUtil.getAuthentication(), mailUtil.getToUser(), mailUtil.getSubject(), mailUtil.getBody());
		mailUtil = null;

	}

}
