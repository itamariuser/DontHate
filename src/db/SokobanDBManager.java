package db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import commons.GameSession;
import commons.LevelEntity;
import commons.PlayerEntity;

public class SokobanDBManager{
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
	
	
	
	public void addLevelEntity(LevelEntity level)  {
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
	
	public void addPlayerEntity(PlayerEntity player) {
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
	
	public void addGameSession(GameSession sesh) {
		Session session = null;
		Transaction tx = null;
		
		//TODO: if (thisSession.steps+thisSession.time< [check players current session from database]
		//TODO: then add the BestGameSession
		
//		@SuppressWarnings("unchecked") USE THIS WHEN GENERATING HIGHSCORE BOARD
//		Query<GameSession> query = session.createQuery( "SELECT Sesh FROM BestGameSessions");
//		List<GameSession> results = query.list();
//		if(!results.isEmpty())
//		{
//			
//		
//			for (GameSession bestGameSession : results) {
//				if(sesh.getKey().equals(bestGameSession.getKey()))
//				{
//					if(sesh.getCompletionTime()+sesh.getNumOfSteps()<bestGameSession.getCompletionTime()+bestGameSession.getNumOfSteps()){
//						tx = session.beginTransaction();
//						session.save(sesh);
//						tx.commit();	
//					}
//				}
//			}
//		}
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(sesh);
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
