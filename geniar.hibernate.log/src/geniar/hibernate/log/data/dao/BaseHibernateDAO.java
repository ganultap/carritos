package geniar.hibernate.log.data.dao;

import geniar.hibernate.log.data.hibernate.LogSessionFactory;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		return LogSessionFactory.getSession();
	}
	
}