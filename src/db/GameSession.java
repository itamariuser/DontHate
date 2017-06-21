package db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity(name="GameSessions")
@Table(name = "GameSessions")
public class GameSession implements Serializable{

	
	@EmbeddedId
	public GameSessionKey key;
//	@Id
//	public String playerName;
//	
//	@Id
//	public String levelName;

	@Column(name = "time")
	int completionTime;

	@Column(name = "steps")
	int steps;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	Date date;
	
	public GameSession(int completionTime, int numOfSteps, Date date,String levelName,String playerName) {
		super();
		key=new GameSessionKey(levelName, playerName);
		this.completionTime = completionTime;
		this.steps = numOfSteps;
		this.date = date;
	}
	
	public GameSession() {
		this.key=new GameSessionKey();
		this.completionTime=1;
		this.steps=1;
		this.date=new Date();
	}

//public GameSession(String playerName, String levelName, int completionTime, int steps, Date date) {
//		super();
//		this.playerName = playerName;
//		this.levelName = levelName;
//		this.completionTime = completionTime;
//		this.steps = steps;
//		this.date = date;
//	}

	
	public int getCompletionTime() {
		return completionTime;
	}

//	public String getPlayerName() {
//		return playerName;
//	}
//
//	public void setPlayerName(String playerName) {
//		this.playerName = playerName;
//	}
//
//	public String getLevelName() {
//		return levelName;
//	}
//
//	public void setLevelName(String levelName) {
//		this.levelName = levelName;
//	}

	
	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public GameSession(GameSessionKey key, int completionTime, int steps, Date date) {
	super();
	this.key = key;
	this.completionTime = completionTime;
	this.steps = steps;
	this.date = date;
}



	public GameSessionKey getKey() {
		return key;
	}



	public void setKey(GameSessionKey key) {
		this.key = key;
	}



	public int getSteps() {
		return steps;
	}

	public void setSteps(int numOfSteps) {
		this.steps = numOfSteps;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
