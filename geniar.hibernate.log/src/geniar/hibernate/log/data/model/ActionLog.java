package geniar.hibernate.log.data.model;

import java.sql.Timestamp;

/**
 * ActionLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */
public class ActionLog extends AbstractActionLog implements
		java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Constructors

	/** default constructor */
	public ActionLog() {
	}

	/** minimal constructor */
	public ActionLog(Long actionLogId) {
		super(actionLogId);
	}

	/** full constructor */
	public ActionLog(Long actionLogId, String userId, String tableName,
			String primaryKey, String concatValues, String command,
			Timestamp timeStamp) {
		super(actionLogId, userId, tableName, primaryKey, concatValues,
				command, timeStamp);
	}

}
