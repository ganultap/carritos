package geniar.siscar.persistence;

import geniar.siscar.model.ActualOthersApplications;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * ActualOthersApplications entities. Transaction control of the save(),
 * update() and delete() operations must be handled externally by senders of
 * these methods or must be manually added to each of these methods for data to
 * be persisted to the JPA datastore.
 * 
 * @see modelo.ActualOthersApplications
 * @author MyEclipse Persistence Tools
 */

public class ActualOthersApplicationsDAO implements
		IActualOthersApplicationsDAO {
	// property constants
	public static final String AOA_STATE = "aoaState";
	public static final String _PSOB = "PSob";
	public static final String _PCURR = "PCurr";
	public static final String _PUSER = "PUser";
	public static final String _PCATEGORY = "PCategory";
	public static final String _PSOURCE = "PSource";
	public static final String _PCONV_TYPE = "PConvType";
	public static final String _PCONV_RATE = "PConvRate";
	public static final String _PCOMPANY = "PCompany";
	public static final String _PACCOUNT = "PAccount";
	public static final String _PCCENTER = "PCcenter";
	public static final String _PAUXILIARY = "PAuxiliary";
	public static final String _PENT_DR = "PEntDr";
	public static final String _PENT_CR = "PEntCr";
	public static final String _PBNAME = "PBname";
	public static final String _PDESCRIPTION = "PDescription";
	public static final String _PNIT = "PNit";
	public static final String _PATTRIBUTE2 = "PAttribute2";
	public static final String _PATTRIBUTE5 = "PAttribute5";
	public static final String _PATTRIBUTE6 = "PAttribute6";
	public static final String _PATTRIBUTE7 = "PAttribute7";
	public static final String _PATTRIBUTE8 = "PAttribute8";
	public static final String _PATTRIBUTE9 = "PAttribute9";
	public static final String _PATTRIBUTE10 = "PAttribute10";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved ActualOthersApplications
	 * entity. All subsequent persist actions of this entity should use the
	 * #update() method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ActualOthersApplicationsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActualOthersApplications entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(ActualOthersApplications entity) {
		EntityManagerHelper.log("saving ActualOthersApplications instance",
				Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent ActualOthersApplications entity. This operation must
	 * be performed within the a database transaction context for the entity's
	 * data to be permanently deleted from the persistence store, i.e.,
	 * database. This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ActualOthersApplicationsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            ActualOthersApplications entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(ActualOthersApplications entity) {
		EntityManagerHelper.log("deleting ActualOthersApplications instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(
					ActualOthersApplications.class, entity.getAoaCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved ActualOthersApplications entity and return it
	 * or a copy of it to the sender. A copy of the ActualOthersApplications
	 * entity parameter is returned when the JPA persistence mechanism has not
	 * previously been tracking the updated entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently saved to the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ActualOthersApplicationsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            ActualOthersApplications entity to update
	 * @returns ActualOthersApplications the persisted ActualOthersApplications
	 *          entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public ActualOthersApplications update(ActualOthersApplications entity) {
		EntityManagerHelper.log("updating ActualOthersApplications instance",
				Level.INFO, null);
		try {
			ActualOthersApplications result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public ActualOthersApplications findById(Long id) {
		EntityManagerHelper.log(
				"finding ActualOthersApplications instance with id: " + id,
				Level.INFO, null);
		try {
			ActualOthersApplications instance = getEntityManager().find(
					ActualOthersApplications.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all ActualOthersApplications entities with a specific property
	 * value.
	 * 
	 * @param propertyName
	 *            the name of the ActualOthersApplications property to query
	 * @param value
	 *            the property value to match
	 * @return List<ActualOthersApplications> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<ActualOthersApplications> findByProperty(String propertyName,
			final Object value) {
		EntityManagerHelper.log(
				"finding ActualOthersApplications instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from ActualOthersApplications model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}
	
	/**/
	
	public List<ActualOthersApplications> findByCriteria(Long whereCondition) {
//	       log.debug("finding Cliente " + whereCondition);}
		EntityManagerHelper.log(
				"finding ActualOthersApplications instance with criteria: " + whereCondition,
				Level.INFO, null);

	       try {
//	           String where = ((whereCondition == null) ||
//	               (whereCondition.length() == 0)) ? "" : ("where " +
//	               whereCondition);
//	           final String queryString = "select model from ActualOthersApplications model " +
//	               where;
	           final String queryString ="from ActualOthersApplications where HEP_ID="+whereCondition+" order by AOA_CODIGO";
	           
	           Query query = getEntityManager().createQuery(queryString);
//	           Query query = getSession().createQuery(queryString);
	           List<ActualOthersApplications> entitiesList = query.getResultList();
//	           List<ActualOthersApplications> entitiesList = query.list();

	           return entitiesList;
	       } catch (RuntimeException re) {
//	           log.error("find By Criteria in Cliente failed", re);
	    	   EntityManagerHelper.log("find by criteria name failed",
						Level.SEVERE, re);
	           throw re;
	       }
	   }
	/**/

	public List<ActualOthersApplications> findByAoaState(Object aoaState) {
		return findByProperty(AOA_STATE, aoaState);
	}

	public List<ActualOthersApplications> findByPSob(Object PSob) {
		return findByProperty(_PSOB, PSob);
	}

	public List<ActualOthersApplications> findByPCurr(Object PCurr) {
		return findByProperty(_PCURR, PCurr);
	}

	public List<ActualOthersApplications> findByPUser(Object PUser) {
		return findByProperty(_PUSER, PUser);
	}

	public List<ActualOthersApplications> findByPCategory(Object PCategory) {
		return findByProperty(_PCATEGORY, PCategory);
	}

	public List<ActualOthersApplications> findByPSource(Object PSource) {
		return findByProperty(_PSOURCE, PSource);
	}

	public List<ActualOthersApplications> findByPConvType(Object PConvType) {
		return findByProperty(_PCONV_TYPE, PConvType);
	}

	public List<ActualOthersApplications> findByPConvRate(Object PConvRate) {
		return findByProperty(_PCONV_RATE, PConvRate);
	}

	public List<ActualOthersApplications> findByPCompany(Object PCompany) {
		return findByProperty(_PCOMPANY, PCompany);
	}

	public List<ActualOthersApplications> findByPAccount(Object PAccount) {
		return findByProperty(_PACCOUNT, PAccount);
	}

	public List<ActualOthersApplications> findByPCcenter(Object PCcenter) {
		return findByProperty(_PCCENTER, PCcenter);
	}

	public List<ActualOthersApplications> findByPAuxiliary(Object PAuxiliary) {
		return findByProperty(_PAUXILIARY, PAuxiliary);
	}

	public List<ActualOthersApplications> findByPEntDr(Object PEntDr) {
		return findByProperty(_PENT_DR, PEntDr);
	}

	public List<ActualOthersApplications> findByPEntCr(Object PEntCr) {
		return findByProperty(_PENT_CR, PEntCr);
	}

	public List<ActualOthersApplications> findByPBname(Object PBname) {
		return findByProperty(_PBNAME, PBname);
	}

	public List<ActualOthersApplications> findByPDescription(Object PDescription) {
		return findByProperty(_PDESCRIPTION, PDescription);
	}

	public List<ActualOthersApplications> findByPNit(Object PNit) {
		return findByProperty(_PNIT, PNit);
	}

	public List<ActualOthersApplications> findByPAttribute2(Object PAttribute2) {
		return findByProperty(_PATTRIBUTE2, PAttribute2);
	}

	public List<ActualOthersApplications> findByPAttribute5(Object PAttribute5) {
		return findByProperty(_PATTRIBUTE5, PAttribute5);
	}

	public List<ActualOthersApplications> findByPAttribute6(Object PAttribute6) {
		return findByProperty(_PATTRIBUTE6, PAttribute6);
	}

	public List<ActualOthersApplications> findByPAttribute7(Object PAttribute7) {
		return findByProperty(_PATTRIBUTE7, PAttribute7);
	}

	public List<ActualOthersApplications> findByPAttribute8(Object PAttribute8) {
		return findByProperty(_PATTRIBUTE8, PAttribute8);
	}

	public List<ActualOthersApplications> findByPAttribute9(Object PAttribute9) {
		return findByProperty(_PATTRIBUTE9, PAttribute9);
	}

	public List<ActualOthersApplications> findByPAttribute10(Object PAttribute10) {
		return findByProperty(_PATTRIBUTE10, PAttribute10);
	}

	/**
	 * Find all ActualOthersApplications entities.
	 * 
	 * @return List<ActualOthersApplications> all ActualOthersApplications
	 *         entities
	 */
	@SuppressWarnings("unchecked")
	public List<ActualOthersApplications> findAll() {
		EntityManagerHelper.log(
				"finding all ActualOthersApplications instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from ActualOthersApplications model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}