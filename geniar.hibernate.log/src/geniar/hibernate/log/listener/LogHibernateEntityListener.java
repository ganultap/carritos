package geniar.hibernate.log.listener;

import org.hibernate.EntityMode;
import org.hibernate.event.PostDeleteEvent;
import org.hibernate.event.PostDeleteEventListener;
import org.hibernate.event.PostInsertEvent;
import org.hibernate.event.PostInsertEventListener;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class LogHibernateEntityListener implements PostUpdateEventListener, 
		PostInsertEventListener, PostDeleteEventListener {

	private static final long serialVersionUID = 1L;

	public LogHibernateEntityListener() {
		
	}
	
	public void onPostUpdate(PostUpdateEvent evento) {
		try {
			System.out.println("Persister" + evento.getPersister());
			EntityPersister persister = evento.getPersister();
			Object entity = evento.getEntity();
			
			String[] propertyNames = persister.getPropertyNames();
			for (int i=0; i < propertyNames.length; i++) {
				System.out.println(propertyNames[i]);
				System.out.println(persister.getPropertyValue(entity, 
						propertyNames[i], 
						EntityMode.POJO));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onPostInsert(PostInsertEvent evento) {
		try {
			System.out.println("Persister" + evento.getPersister());
			EntityPersister persister = evento.getPersister();
			Object entity = evento.getEntity();
			
			String[] propertyNames = persister.getPropertyNames();
			for (int i=0; i < propertyNames.length; i++) {
				System.out.println(propertyNames[i]);
				System.out.println(persister.getPropertyValue(entity, 
						propertyNames[i], 
						EntityMode.POJO));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void onPostDelete(PostDeleteEvent evento) {
		try {
			System.out.println("Persister" + evento.getPersister());
			EntityPersister persister = evento.getPersister();
			Object entity = evento.getEntity();
			
			String[] propertyNames = persister.getPropertyNames();
			for (int i=0; i < propertyNames.length; i++) {
				System.out.println(propertyNames[i]);
				System.out.println(persister.getPropertyValue(entity, 
						propertyNames[i], 
						EntityMode.POJO));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
