package geniar.hibernate.log.util;


public class LogUserInfo {

	private static final ThreadLocal<String> currentUser = new ThreadLocal<String>();
	
	public static void setCurrentUser(String currentUserName) {
		currentUser.set(currentUserName);
	}
	
	public static String getCurrentUser() {
		return currentUser.get() != null ? currentUser.get() : "[Sin nombre]";
	}
	
}
