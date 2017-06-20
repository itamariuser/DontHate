package commons;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GameSessions")
public class GameSession {

	@EmbeddedId
	public GameSessionKey key;

	@Column(name = "completionTime")
	int completionTime;

	@Column(name = "numOfSteps")
	int numOfSteps;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	Date date;
	
	public GameSession(int completionTime, int numOfSteps, Date date) {
		super();
		this.completionTime = completionTime;
		this.numOfSteps = numOfSteps;
		this.date = date;
	}

	public GameSessionKey getKey() {
		return key;
	}

	public void setKey(GameSessionKey key) {
		this.key = key;
	}

	public int getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(int completionTime) {
		this.completionTime = completionTime;
	}

	public int getNumOfSteps() {
		return numOfSteps;
	}

	public void setNumOfSteps(int numOfSteps) {
		this.numOfSteps = numOfSteps;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
