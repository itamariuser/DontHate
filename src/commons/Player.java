package commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name="Player")
@SuppressWarnings("serial")
public class Player implements Serializable {
	@Id
	@Column(name = "playerName")
	String playerName;
	
	@OneToMany
	@JoinColumn(name="playerName")
	private List<GameSession> sessionsPlayed = new ArrayList<GameSession>();

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
}
