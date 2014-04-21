package geniar.hibernate.log.data.dao;

import geniar.hibernate.log.data.model.ActionLog;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ActionLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see geniar.hibernate.log.data.model.ActionLog
 * @author MyEclipse Persistence Tools
 */

public class ActionLogDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ActionLogDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String TABLE_NAME = "tableName";
	public static final String PRIMARY_KEY = "primaryKey";
	public static final String CONCAT_VALUES = "concatValues";
	public static final String COMMAND = "command";
	public static final String TIME_STAMP = "timeStamp";

	public void save(ActionLog transientInstance) {
		log.debug("saving ActionLog instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ActionLog persistentInstance) {
		log.debug("deleting ActionLog instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ActionLog findById(java.lang.Long id) {
		log.debug("getting ActionLog instance with id: " + id);
		try {
			ActionLog instance = (ActionLog) getSession().get(
					"geniar.hibernate.log.model.ActionLog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ActionLog instance) {
		log.debug("finding ActionLog instance by example");
		try {
			List results = getSession().createCriteria(
					"geniar.hibernate.log.model.ActionLog").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ActionLog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ActionLog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByTableName(Object tableName) {
		return findByProperty(TABLE_NAME, tableName);
	}

	public List findByPrimaryKey(Object primaryKey) {
		return findByProperty(PRIMARY_KEY, primaryKey);
	}

	public List findByConcatValues(Object concatValues) {
		return findByProperty(CONCAT_VALUES, concatValues);
	}

	public List findByCommand(Object command) {
		return findByProperty(COMMAND, command);
	}

	public List findByTimeStamp(Object timeStamp) {
		return findByProperty(TIME_STAMP, timeStamp);
	}

	public List findAll() {
		log.debug("finding all ActionLog instances");
		try {
			String queryString = "from ActionLog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ActionLog merge(ActionLog detachedInstance) {
		log.debug("merging ActionLog instance");
		try {
			ActionLog result = (ActionLog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ActionLog instance) {
		log.debug("attaching dirty ActionLog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ActionLog instance) {
		log.debug("attaching clean ActionLog instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}