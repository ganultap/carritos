package geniar.siscar.persistence;

import geniar.hibernate.log.listener.LogHibernateInterceptor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.ejb.Ejb3Configuration;

/**
 * @author MyEclipse Persistence Tools
 */
public class EntityManagerHelper {

	private static final EntityManagerFactory emf;
	private static final ThreadLocal<EntityManager> threadLocal;
	private static final Logger logger;

	static {
		//para habilitar el log habilitar la siguiente linea de codigo
		emf = Persistence.createEntityManagerFactory("siscarLogicPU");
		//Ejb3Configuration cfg = new Ejb3Configuration();
		//cfg = cfg.configure("siscarLogicPU", null).setInterceptor(new LogHibernateInterceptor());
		//emf = cfg.buildEntityManagerFactory();

		threadLocal = new ThreadLocal<EntityManager>();
		logger = Logger.getLogger("siscarLogicPU");
		logger.setLevel(Level.ALL);
	}

	public static EntityManager getEntityManager() {
		EntityManager manager = threadLocal.get();
		if (manager == null || !manager.isOpen()) {
			manager = emf.createEntityManager();
			threadLocal.set(manager);
		}
		return manager;
	}

	public static void closeEntityManager() {
		EntityManager em = threadLocal.get();
		threadLocal.set(null);
		if (em != null)
			em.close();
	}

	public static void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public static void commit() {
		getEntityManager().getTransaction().commit();
	}

	public static void rollback() {
		getEntityManager().getTransaction().rollback();
	}

	public static Query createQuery(String query) {
		return getEntityManager().createQuery(query);
	}

	public static void log(String info, Level level, Throwable ex) {
		logger.log(level, info, ex);
	}

}
