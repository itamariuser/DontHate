package model;

import commons.Level;
import commons.Level2D;


/**
 * The layer responsible for manipulating the level and game objects' data
 * @author itamar sheffer
 *
 */
public interface Model {
	public void move(String direction) throws Exception;
	public void setLevel(Level level);
	public Level2D getLevel();
	public void loadLevel(String fileName) throws Exception;
	public void saveLevel(String fileName) throws Exception;
}
