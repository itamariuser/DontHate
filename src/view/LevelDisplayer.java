package view;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import common.Level2D;

/**
 * An interface responsible for displaying a level.
 */
public interface LevelDisplayer {
	/**
	 * Update the whole level, without regard to what's changed since the last rendering.
	 * This is a slow but safe way to render a level.
	 * @param level - The level to display.
	 * @param output - The output stream to display to
	 * @return true if display was successful, false otherwise.
	 * @throws FileNotFoundException If any of the game sprites were not found.
	 */
	boolean displayLevelClear(Level2D level, OutputStream output) throws FileNotFoundException;
	
	/**
	 * Update the level, rendering only the parts which were changed since the last rendering.
	 * This is a quick but unsafe way to display a level.
	 * @param level - The level to display.
	 * @param output - The output stream to display to
	 * @return true if display was successful, false otherwise.
	 * @throws FileNotFoundException If any of the game sprites were not found.
	 */
	boolean displayLevel(Level2D level, OutputStream output) throws FileNotFoundException;

	
}
