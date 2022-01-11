package es.deusto.ingenieria.sd.strava.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.strava.server.data.domain.Activity;

//This class implements Singleton and DAO patterns
public class ActivityDAO extends DataAccessObjectBase implements IDataAccessObject<Activity> {

	private static ActivityDAO instance;	
	
	private ActivityDAO() { }
	
	public static ActivityDAO getInstance() {
		if (instance == null) {
			instance = new ActivityDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Activity object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Activity object) {
		super.deleteObject(object);
	}

	@Override
	public List<Activity> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();

		List<Activity> activities = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Activity> extent = pm.getExtent(Activity.class, true);

			for (Activity Activity : extent) {
				activities.add(Activity);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Activities: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return activities;		
	}

	@Override
	public Activity find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();
		
		Activity result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Activity.class.getName() + " WHERE title == '" + param + "'");
			query.setUnique(true);
			result = (Activity) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Activity: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}