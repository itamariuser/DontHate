package commons;


import java.io.Serializable;


/*
 * A data type representing the level, its structure and additional info
 * @author itamar sheffer
 */
@SuppressWarnings("serial")
public class Level implements Serializable{
	protected String Name;
	protected int difficulty;
	protected int levelNum;
	protected int numberOfGoals;
	protected boolean winCondition;
	protected int score;
	protected int timePassed = 0;
	protected int numOfSteps;
	protected int numOfTries;
		public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getTimePassed() {
		return timePassed;
	}
	
	public boolean isWinCondition() {
		return winCondition;
	}
	public void setWinCondition(boolean winCondition) {
		this.winCondition = winCondition;
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
	public int getNumOfTries() {
		return numOfTries;
	}
	public void setNumOfTries(int numOfTries) {
		this.numOfTries = numOfTries;
	}
		public Level() {}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public int getDifficulty() {
			return difficulty;
		}
		public void setDifficulty(int difficulty) {
			this.difficulty = difficulty;
		}
		public int getLevelNum() {
			return levelNum;
		}
		public void setLevelNum(int levelNum) {
			this.levelNum = levelNum;
		}
		public int getNumberOfGoals() {
			return numberOfGoals;
		}
		public void setNumberOfGoals(int numberOfGoals) {
			this.numberOfGoals = numberOfGoals;
		}
		
}