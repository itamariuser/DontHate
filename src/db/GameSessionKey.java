package db;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key for GameSession.
 * For work with the ORM.
 */
@SuppressWarnings("serial")
@Embeddable()
public class GameSessionKey implements Serializable {
	@Column(name = "levelName")
	String levelName;

	@Column(name = "playerName")
	String playerName;

	@Override
	public int hashCode() {
		return 31 * (Integer.parseInt(levelName) + Integer.parseInt(playerName));
	}

	public GameSessionKey() {
		this.levelName="level 1";
		this.playerName="default";
	}
	

	public GameSessionKey(String levelName, String playerName) {
		super();
		this.levelName = levelName;
		this.playerName = playerName;
	}

	
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameSessionKey) {
			return (this.levelName.equals(((GameSessionKey) obj).levelName)
					&& this.playerName.equals(((GameSessionKey) obj).getPlayerName()));
		}
		return false;
	}

	// @Column(name = "player")
	// private String playerName;
	// @Column(name = "lvl")
	// private String levelName;
	//
	//
	//
	//
	// public GameSessionKey(String playerName, String levelName) {
	// super();
	// this.playerName = playerName;
	// this.levelName = levelName;
	// }
	//
	//
	// public String getPlayerName() {
	// return playerName;
	// }
	//
	//
	// public void setPlayerName(String playerName) {
	// this.playerName = playerName;
	// }
	//
	//
	// public String getLevelName() {
	// return levelName;
	// }
	//
	//
	// public void setLevelName(String levelName) {
	// this.levelName = levelName;
	// }
	//
	//
	// @Override
	// public int hashCode()
	// {
	// return playerName.hashCode()*31 + levelName.hashCode();
	// }
	//
	// @Override
	// public boolean equals(Object obj)
	// {
	// if(obj == this)
	// return true;
	// if(obj instanceof GameSessionKey){
	// GameSessionKey a = (GameSessionKey)obj;
	// return this.playerName.equals(a.playerName) &&
	// this.levelName.equals(a.levelName);
	// }
	// return false;
	// }

}