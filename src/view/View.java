package view;

import java.util.Observable;

import common.Level;
import common.Level2D;

/**
 * A class for the layer responsible for displaying game info and levels, and UI
 *
 */
public abstract class View  extends Observable{
	Level2D level;
	protected LevelDisplayer leveldisplayer;
	public abstract void displayMessage(String msg);
	public abstract void start();
	public LevelDisplayer getDisplayer() {
		return leveldisplayer;
	}
	public void setDisplayer(LevelDisplayer displayer) {
		this.leveldisplayer = displayer;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level2D level) {
		this.level = level;
	}
	public LevelDisplayer getLeveldisplayer() {
		return leveldisplayer;
	}
	public void setLeveldisplayer(LevelDisplayer leveldisplayer) {
		this.leveldisplayer = leveldisplayer;
	}
	
	/**
	 * Display a message when the player wins.
	 */
	public abstract void displayWinMessage();
	
	/**
	 * Called when the solution for this level is ready.
	 */
	public abstract void solutionReady();
}
