package geniar.siscar.persistence;

import geniar.siscar.model.FlatFile;

import java.util.List;
import java.util.logging.Level;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * FlatFile entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see geniar.siscar.model.FlatFile
 * @author MyEclipse Persistence Tools
 */

public class FlatFileDAO implements IFlatFileDAO {
	// property constants
	public static final String FF_USUARIO = "ffUsuario";
	public static final String FF_CARNE = "ffCarne";
	public static final String FF_CONCEPTO = "ffConcepto";
	public static final String FF_UNIDADES = "ffUnidades";
	public static final String FF_VALOR = "ffValor";
	public static final String FF_FECHA = "ffFecha";
	public static final String FF_DOCUMENTO = "ffDocumento";
	public static final String FF_MONEDA = "ffMoneda";
	public static final String FF_DESCRIPCION = "ffDescripcion";
	public static final String FF_CENTRO_COSTO = "ffCentroCosto";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved FlatFile entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object) EntityManager#persist}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FlatFileDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlatFile entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FlatFile entity) {
		EntityManagerHelper.log("saving FlatFile instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent FlatFile entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the
	 * {@link javax.persistence.EntityManager#remove(Object) EntityManager#delete}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * FlatFileDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FlatFile entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FlatFile entity) {
		EntityManagerHelper.log("deleting FlatFile instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(FlatFile.class,
					entity.getFfCodigo());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved FlatFile entity and return it or a copy of it
	 * to the sender. A copy of the FlatFile entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = FlatFileDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FlatFile entity to update
	 * @returns FlatFile the persisted FlatFile entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FlatFile update(FlatFile entity) {
		EntityManagerHelper.log("updating FlatFile instance", Level.INFO, null);
		try {
			FlatFile result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public FlatFile findById(Long id) {
		EntityManagerHelper.log("finding FlatFile instance with id: " + id,
				Level.INFO, null);
		try {
			FlatFile instance = getEntityManager().find(FlatFile.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all FlatFile entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FlatFile property to query
	 * @param value
	 *            the property value to match
	 * @return List<FlatFile> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<FlatFile> findByProperty(String propertyName, final Object value) {
		EntityManagerHelper.log("finding FlatFile instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from FlatFile model where model."
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

	public List<FlatFile> findByFfUsuario(Object ffUsuario) {
		return findByProperty(FF_USUARIO, ffUsuario);
	}

	public List<FlatFile> findByFfCarne(Object ffCarne) {
		return findByProperty(FF_CARNE, ffCarne);
	}

	public List<FlatFile> findByFfConcepto(Object ffConcepto) {
		return findByProperty(FF_CONCEPTO, ffConcepto);
	}

	public List<FlatFile> findByFfUnidades(Object ffUnidades) {
		return findByProperty(FF_UNIDADES, ffUnidades);
	}

	public List<FlatFile> findByFfValor(Object ffValor) {
		return findByProperty(FF_VALOR, ffValor);
	}

	public List<FlatFile> findByFfFecha(Object ffFecha) {
		return findByProperty(FF_FECHA, ffFecha);
	}

	public List<FlatFile> findByFfDocumento(Object ffDocumento) {
		return findByProperty(FF_DOCUMENTO, ffDocumento);
	}

	public List<FlatFile> findByFfMoneda(Object ffMoneda) {
		return findByProperty(FF_MONEDA, ffMoneda);
	}

	public List<FlatFile> findByFfDescripcion(Object ffDescripcion) {
		return findByProperty(FF_DESCRIPCION, ffDescripcion);
	}

	public List<FlatFile> findByFfCentroCosto(Object ffCentroCosto) {
		return findByProperty(FF_CENTRO_COSTO, ffCentroCosto);
	}

	/**
	 * Find all FlatFile entities.
	 * 
	 * @return List<FlatFile> all FlatFile entities
	 */
	@SuppressWarnings("unchecked")
	public List<FlatFile> findAll() {
		EntityManagerHelper.log("finding all FlatFile instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from FlatFile model";
			Query query = getEntityManager().createQuery(queryString);
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}