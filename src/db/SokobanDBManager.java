package db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SokobanDBManager{
	private static SokobanDBManager instance ;
	private static SessionFactory factory;

	public static SokobanDBManager getInstance() {
		if(instance==null)
		{
			instance=new SokobanDBManager();
		}
		
		try {
			Configuration configuration = new Configuration();
			configuration.configure();
			factory = configuration.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	
	private SokobanDBManager() {
	}
	
	
	public ArrayList<GameSession> getGameSessionsWithLevelName(String levelName)
	{
		Session session = null;
		ArrayList<GameSession> gameSessions=new ArrayList<GameSession>();
		try {
			session = factory.openSession();
			@SuppressWarnings("unchecked")
			Query<GameSession> query=session.createQuery("FROM GameSessions s WHERE s.key.levelName = :name");
			query.setParameter("name", levelName);
			List<GameSession> s=query.list();
			gameSessions.addAll(s);
			session.beginTransaction();
		}
		catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
		return gameSessions;
	}
	
	public ArrayList<GameSession> getGameSessionsWithPlayerName(String playerName)
	{
		Session session = null;
		ArrayList<GameSession> gameSessions=new ArrayList<GameSession>();
		try {
			session = factory.openSession();
			Query<GameSession> query=session.createQuery("FROM GameSessions s WHERE s.key.playerName = :name", GameSession.class);
			query.setParameter("name", playerName);
			gameSessions.addAll(query.list());
		}
		catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
		return gameSessions;
	}
	
	
	public List<PlayerEntity> getPlayersWithName(String playerName)
	{
		Session session = null;
		ArrayList<PlayerEntity> gameSessions=new ArrayList<PlayerEntity>();
		try {
			session = factory.openSession();
			Query<PlayerEntity> query=session.createQuery("FROM PlayerEntities p WHERE p.playerName = :name", PlayerEntity.class);
			query.setParameter("name", playerName);
			gameSessions.addAll(query.list());
		}
		catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
		return gameSessions;
	}
	
	public List<LevelEntity> getLevelsWithName(String levelName)
	{
		Session session = null;
		ArrayList<LevelEntity> levels=new ArrayList<LevelEntity>();
		try {
			session = factory.openSession();
			Query<LevelEntity> query=session.createQuery("FROM LevelEntities l WHERE l.levelName = :name", LevelEntity.class);
			query.setParameter("name", levelName);
			levels.addAll(query.list());
		}
		catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		}
		finally {
			if (session != null)
				session.close();
		}
		return levels;
	}
	
	
	public void add(LevelEntity level)  {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(level);
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
	
	public void updatePlayerWinCount(int newWinCount,int oldWinCount,String playerName)
	{
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String hqlUpdate = "update PlayerEntities set winCount = :newWinCount where winCount = :oldWinCount";
			Query<PlayerEntity> query=session.createQuery(hqlUpdate);
			query.setParameter("newWinCount", newWinCount);
			query.setParameter("oldWinCount", oldWinCount);
			query.executeUpdate();
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
	
	public void add(PlayerEntity player)  {
		Session session = null;
		Transaction tx = null;
		
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(player);
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
	
	public void add(GameSession sesh)  {
		Session session = null;
		Transaction tx = null;
		//THIS DOES NOT ADD ANY GAME SESSIONS Y U DO DIS
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(sesh);
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
