package commons;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class GameSession {
	int timePassed;
	int numOfSteps;
	
	@EmbeddedId
	GameSessionKey key;
	public GameSession(String playerName, int levelID) {
		super();
		this.key=new GameSessionKey(playerName,levelID);
	}
	public int getTimePassed() {
		return timePassed;
	}
	public void setTimePassed(int timePassed) {
		this.timePassed = timePassed;
	}
	public int getNumOfSteps() {
		return numOfSteps;
	}
	public void setNumOfSteps(int numOfSteps) {
		this.numOfSteps = numOfSteps;
	}
	public GameSessionKey getKey() {
		return key;
	}
	public void setKey(GameSessionKey key) {
		this.key = key;
	}
	
	
	
}
