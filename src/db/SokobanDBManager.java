package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import commons.GameSession;
import commons.GameSessionKey;
import commons.Level;
import commons.Player;

public class SokobanDBManager {
	private static SokobanDBManager instance = new SokobanDBManager();

	public static SokobanDBManager getInstance() {
		return instance;
	}
	private SessionFactory factory;
	
	private SokobanDBManager() {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			factory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void addLevel(Level level) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(level);
			tx.commit();			
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}		
	}
	
	public void addPlayer(Player player) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(player);
			tx.commit();			
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}		
	}
	
	public void registerStudentToCourse(String playerName, int levelID) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			GameSession sess = new GameSession(playerName, levelID);
			session.save(sess);
			tx.commit();			
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
	}
	
	public void updateSession(String playerName, int levelID, int time, int steps) {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			
			GameSessionKey key = new GameSessionKey(playerName, levelID);
			GameSession sess = session.get(GameSession.class, key);
			
			sess.setNumOfSteps(steps);
			sess.setTimePassed(time);
			session.update(sess);
			tx.commit();			
		}
		catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
	}
	
//	public void deleteGameSession()
//	{
//		Serializable id = new Long(levelID);
//		Object persistentInstance = session.load(Category.class, id);
//		if (persistentInstance != null) {
//		    session.delete(persistentInstance);
//		}
//	}
}
