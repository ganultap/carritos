package geniar.hibernate.log.data.model;

import java.sql.Timestamp;

/**
 * AbstractActionLog entity provides the base persistence definition of the
 * ActionLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public abstract class AbstractActionLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long actionLogId;
	private String userId;
	private String tableName;
	private String primaryKey;
	private String concatValues;
	private String command;
	private Timestamp timeStamp;

	// Constructors

	/** default constructor */
	public AbstractActionLog() {
	}

	/** minimal constructor */
	public AbstractActionLog(Long actionLogId) {
		this.actionLogId = actionLogId;
	}

	/** full constructor */
	public AbstractActionLog(Long actionLogId, String userId, String tableName,
			String primaryKey, String concatValues, String command,
			Timestamp timeStamp) {
		this.actionLogId = actionLogId;
		this.userId = userId;
		this.tableName = tableName;
		this.primaryKey = primaryKey;
		this.concatValues = concatValues;
		this.command = command;
		this.timeStamp = timeStamp;
	}

	// Property accessors

	public Long getActionLogId() {
		return this.actionLogId;
	}

	public void setActionLogId(Long actionLogId) {
		this.actionLogId = actionLogId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPrimaryKey() {
		return this.primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getConcatValues() {
		return this.concatValues;
	}

	public void setConcatValues(String concatValues) {
		this.concatValues = concatValues;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Timestamp getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

}