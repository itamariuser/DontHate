package commons;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LevelEntities")
public class LevelEntity {
	@Id
	@Column(name = "levelName")
	protected String levelName;
	
	@OneToMany
	@JoinColumn(name= "levelName")
	protected List<GameSession> sessions;

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public List<GameSession> getSessions() {
		return sessions;
	}

	public void setBSessions(List<GameSession> bestSessions) {
		this.sessions = bestSessions;
	}  
	
	
}
