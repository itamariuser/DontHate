package model;

import java.util.Queue;

import common.Level;
import common.Level2D;
import commons.ServerPlan;


/**
 * The layer responsible for manipulating the level and its game objects' data.
 * @author itamar sheffer
 *
 */
public interface Model {
	/**
	 * Move the mainCharacter in a direction.
	 * @param direction - The direction to move in.
	 * @throws Exception
	 */
	public void move(String direction) throws Exception;
	
	/**
	 * Set the level inside the model to a new level.
	 * @param level - The new level.
	 */
	public void setLevel(Level level);
	
	/**
	 * @return This model's current level.
	 */
	public Level2D getLevel();
	
	/**
	 * Get the stored plan for this level.
	 * @return The solution.
	 */
	public ServerPlan getSolution();
	
	/**
	 * Set the stored plan for this level to a new plan.
	 * @param plan - The new plan.
	 */
	public void setSolution(ServerPlan plan);
	
	/**
	 * Load a level from a file with the given name.
	 * @param fileName - The name of the file to load from.
	 * @throws Exception
	 */
	public void loadLevel(String fileName) throws Exception;
	
	/**
	 * Save a level to a file with the given name.
	 * @param fileName - The name of the file to save to.
	 * @throws Exception
	 */
	public void saveLevel(String fileName) throws Exception;
	
	/**
	 * Execute a list of commands on the current level.
	 * @param moveQueue - The list of commands to execute. 
	 */
	public void runSolution(Queue<String> moveQueue);
	
	public void stop();
}
