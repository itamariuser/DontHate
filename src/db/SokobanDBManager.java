package db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * A class for interaction with the Database.
 * For work with the ORM.
 */
public class SokobanDBManager {
	private static SokobanDBManager instance;
	private static SessionFactory factory;

	public static SokobanDBManager getInstance() {
		if (instance == null) {
			instance = new SokobanDBManager();
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

	/**
	 * Finds all game sessions with players playing a level with a given name.
	 * @param levelName - The level's name.
	 * @return An ArrayList containing all fitting game sessions.
	 */
	public ArrayList<GameSession> getGameSessionsWithLevelName(String levelName) {
		Session session = null;
		ArrayList<GameSession> gameSessions = new ArrayList<GameSession>();
		try {
			session = factory.openSession();
			@SuppressWarnings("unchecked")
			Query<GameSession> query = session.createQuery("FROM GameSessions s WHERE s.key.levelName = :name");
			query.setParameter("name", levelName);
			List<GameSession> s = query.list();
			gameSessions.addAll(s);
			session.beginTransaction();
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return gameSessions;
	}

	/**
	 * Finds all game sessions played by a player with a given name.
	 * @param playerName - The player's name.
	 * @return An ArrayList containing all fitting game sessions.
	 */
	public ArrayList<GameSession> getGameSessionsWithPlayerName(String playerName) {
		Session session = null;
		ArrayList<GameSession> gameSessions = new ArrayList<GameSession>();
		try {
			session = factory.openSession();
			Query<GameSession> query = session.createQuery("FROM GameSessions s WHERE s.key.playerName = :name",
					GameSession.class);
			query.setParameter("name", playerName);
			gameSessions.addAll(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return gameSessions;
	}

	/**
	 * Finds all players with a given name.
	 * @param playerName - The name to be searched.
	 * @return A List containing all player entities fitting the search.
	 */
	public List<PlayerEntity> getPlayersWithName(String playerName) {
		Session session = null;
		ArrayList<PlayerEntity> gameSessions = new ArrayList<PlayerEntity>();
		try {
			session = factory.openSession();
			Query<PlayerEntity> query = session.createQuery("FROM PlayerEntities p WHERE p.playerName = :name",
					PlayerEntity.class);
			query.setParameter("name", playerName);
			gameSessions.addAll(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return gameSessions;
	}
	
	/**
	 * Finds all levels with a given name.
	 * @param levelName - The name to be searched.
	 * @return A List containing all level entities fitting the search.
	 */
	public List<LevelEntity> getLevelsWithName(String levelName) {
		Session session = null;
		ArrayList<LevelEntity> levels = new ArrayList<LevelEntity>();
		try {
			session = factory.openSession();
			Query<LevelEntity> query = session.createQuery("FROM LevelEntities l WHERE l.levelName = :name",
					LevelEntity.class);
			query.setParameter("name", levelName);
			levels.addAll(query.list());
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
		return levels;
	}
	
	/**
	 * Updates a player's win count.
	 * @param playerEntity - The entity to be updated.
	 * @param newWinCount - The new win count.
	 */
	public void updatePlayerWinCount(PlayerEntity playerEntity, int newWinCount) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String hqlUpdate = "update PlayerEntities set winCount = :newWinCount where playerName = :name";
			Query<PlayerEntity> query = session.createQuery(hqlUpdate);
			query.setParameter("name", playerEntity.getPlayerName());
			query.setParameter("newWinCount", newWinCount);
			
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * Inserts a level entity to the DB if none exist,
	 * updates otherwise.
	 * @param levelEntity - The entity to be added
	 */
	public void add(LevelEntity levelEntity) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(levelEntity);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * Inserts a player entity to the DB if none exist,
	 * updates otherwise.
	 * @param player - The entity to be added
	 */
	public void add(PlayerEntity player) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(player);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * Inserts a game session to the DB if none exist,
	 * updates otherwise.
	 * @param s - The session to be added.
	 */
	public void add(GameSession s) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(s);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
			System.out.println(ex.getMessage());
		} finally {
			if (session != null)
				session.close();
		}
	}
}
