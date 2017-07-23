package db;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * A class representing a level. For work with the ORM.
 */
@Entity(name = "LevelEntities")
public class LevelEntity {
	@Id
	@Column(name = "levelName")
	protected String levelName;

	@Column(name = "difficulty")
	protected int difficulty;

	@OneToMany
	@JoinColumn(name = "levelName")
	protected List<GameSession> sessions;

	public String getLevelName() {
		return levelName;
	}

	public LevelEntity() {
		// TODO Auto-generated constructor stub
	}

	public LevelEntity(String levelName, int difficulty) {
		super();
		this.levelName = levelName;
		this.difficulty = difficulty;
	}

	public List<GameSession> getSessions() {
		return sessions;
	}

	public void setBSessions(List<GameSession> bestSessions) {
		this.sessions = bestSessions;
	}

}
