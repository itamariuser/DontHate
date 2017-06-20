package commons;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class GameSessionKey implements Serializable {
	@Column(name = "levelName")
	String levelName;
	
	@Column(name = "playerName")
	String playerName;
	
	
	@Override 
	public int hashCode() {
		return 31 * (Integer.parseInt(levelName)+Integer.parseInt(playerName)) ;
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
		if(obj instanceof GameSessionKey)
		{
			return (this.levelName.equals(((GameSessionKey) obj).levelName) && this.playerName.equals(((GameSessionKey) obj).getPlayerName()));
		}
		return false;
	}

	
	
}