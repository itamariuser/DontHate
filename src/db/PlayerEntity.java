package db;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * A class representing a single player.
 * For work with the ORM.
 */
@Entity(name = "PlayerEntities")
@SuppressWarnings("serial")
public class PlayerEntity implements Serializable {

	@Id
	@Column(name = "playerName")
	protected String playerName;

	@Column(name = "winCount")
	protected int winCount;

	public PlayerEntity() {
	}

	public PlayerEntity(String playerName, int winCount) {
		super();
		this.playerName = playerName;
		this.winCount = winCount;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getWinCount() {
		return winCount;
	}

	public void setWinCount(int winCount) {
		this.winCount = winCount;
	}

	@OneToMany
	@JoinColumn(name = "playerName")
	protected List<GameSession> bestSessions;
}
