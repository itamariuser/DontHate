package commons;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class GameSessionKey implements Serializable {
	String playerName;
	int levelID;
	public GameSessionKey(String playerName, int levelID) {
		super();
		this.playerName = playerName;
		this.levelID = levelID;
	}
	public GameSessionKey()
	{
		
	}

	@Override 
	public int hashCode() {
		int sum = 0;
		int evenSum = 0;
		for (char c : playerName.replaceAll("\\D", "").toCharArray()) {
		     int digit = c - '0';
		     sum += digit;
		     if (digit % 2 == 0) {
		         evenSum += digit;
		     }
		 };
		return 31 * levelID + sum;
	}
	
	public String getPlayerName() {
		return playerName;
	}



	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}



	public int getLevelID() {
		return levelID;
	}



	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}



	@Override
	public boolean equals(Object obj) {
		GameSessionKey key = (GameSessionKey)obj;
		
		return this.levelID == key.levelID && this.playerName.equals(playerName);
	}
}