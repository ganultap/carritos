package geniar.siscar.mail.util;

public class MailUtil {

	private String host;
	private String starttls;
	private String port;
	private String fromuser;
	private String password;
	private String authentication;
	private String toUser;
	private String subject;
	private String body;

	public MailUtil() {
		super();
	}

	public MailUtil(String host, String starttls, String port, String fromuser, String password, String authentication, String toUser, String subject, String body) {
		super();
		this.host = host;
		this.starttls = starttls;
		this.port = port;
		this.fromuser = fromuser;
		this.password = password;
		this.authentication = authentication;
		this.toUser = toUser;
		this.subject = subject;
		this.body = body;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getStarttls() {
		return starttls;
	}

	public void setStarttls(String starttls) {
		this.starttls = starttls;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFromuser() {
		return fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
