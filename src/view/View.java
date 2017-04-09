package view;

import java.util.Observable;

import commons.Level;

/**
 * Responsible for displaying game info and levels, and UI
 * @author itamar sheffer
 *
 */
public abstract class View  extends Observable{
	Level level;
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
	public void setLevel(Level level) {
		this.level = level;
	}
	public LevelDisplayer getLeveldisplayer() {
		return leveldisplayer;
	}
	public void setLeveldisplayer(LevelDisplayer leveldisplayer) {
		this.leveldisplayer = leveldisplayer;
	}
	public abstract void displayWinMessage();
	
	
}
