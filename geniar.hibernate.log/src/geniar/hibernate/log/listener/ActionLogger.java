package geniar.hibernate.log.listener;

public interface ActionLogger {

	void logThis (String command, Object entity, String[] properties, Long userId) ;

}