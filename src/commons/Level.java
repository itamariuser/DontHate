package commons;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.data.GameObject;

/*
 * A data type representing the level, its structure and additional info
 * @author itamar sheffer
 */
@SuppressWarnings("serial")
public class Level implements Serializable {

	@Column(name = "levelID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int levelID;
	@Column(name = "Name")
	protected String Name;
	@Column(name = "difficulty")
	protected int difficulty;
	@Column(name = "levelNum")
	protected int levelNum;
	@Column(name = "numberOfGoals")
	protected int numberOfGoals;
	@Column(name = "winCondition")
	protected boolean winCondition;
	@Column(name = "score")
	protected int score;
	@Column(name = "timePassed")
	protected int timePassed = 0;
	@Column(name = "numOfSteps")
	protected int numOfSteps;
	@Column(name = "numOfTries")
	protected int numOfTries;

	public Level(int levelID, String name, int difficulty, int levelNum, int numberOfGoals, boolean winCondition,
			int score, int timePassed, int numOfSteps, int numOfTries) {
		super();
		this.levelID = levelID;
		Name = name;
		this.difficulty = difficulty;
		this.levelNum = levelNum;
		this.numberOfGoals = numberOfGoals;
		this.winCondition = winCondition;
		this.score = score;
		this.timePassed = timePassed;
		this.numOfSteps = numOfSteps;
		this.numOfTries = numOfTries;
	}

	protected ArrayList<GameObject> objReferences;

//	@Override
//	public String toString() {
//		return "Level [levelID=" + levelID + ", first_name=" + first_name + ", last_name=" + last_name + ", salary="
//				+ salary + "]";
//	}

	public int getLevelID() {
		return levelID;
	}

	public void setLevelID(int levelID) {
		this.levelID = levelID;
	}

	public ArrayList<GameObject> getObjReferences() {
		return objReferences;
	}

	public void setObjReferences(ArrayList<GameObject> objReferences) {
		this.objReferences = objReferences;
	}

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

	public Level() {
	}

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