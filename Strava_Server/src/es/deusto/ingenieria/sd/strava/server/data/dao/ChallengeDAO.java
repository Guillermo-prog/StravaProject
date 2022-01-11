package es.deusto.ingenieria.sd.strava.server.data.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.ingenieria.sd.strava.server.data.domain.Challenge;

//This class implements Singleton and DAO patterns
public class ChallengeDAO extends DataAccessObjectBase implements IDataAccessObject<Challenge> {

	private static ChallengeDAO instance;	
	
	private ChallengeDAO() { }
	
	public static ChallengeDAO getInstance() {
		if (instance == null) {
			instance = new ChallengeDAO();
		}		
		
		return instance;
	}
	
	@Override
	public void save(Challenge object) {
		super.saveObject(object);
	}

	@Override
	public void delete(Challenge object) {
		super.deleteObject(object);
	}

	@Override
	public List<Challenge> getAll() {				
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();

		List<Challenge> challenges = new ArrayList<>();
		
		try {
			tx.begin();
			
			Extent<Challenge> extent = pm.getExtent(Challenge.class, true);

			for (Challenge Challenge : extent) {
				challenges.add(Challenge);
			}

			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error retrieving all the Challenges: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return challenges;		
	}

	@Override
	public Challenge find(String param) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.setDetachAllOnCommit(true);
		Transaction tx = pm.currentTransaction();
		
		Challenge result = null; 

		try {
			tx.begin();
						
			Query<?> query = pm.newQuery("SELECT FROM " + Challenge.class.getName() + " WHERE title == '" + param + "'");
			query.setUnique(true);
			result = (Challenge) query.execute();
			
			tx.commit();
		} catch (Exception ex) {
			System.out.println("  $ Error querying a Challenge: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return result;
	}
}