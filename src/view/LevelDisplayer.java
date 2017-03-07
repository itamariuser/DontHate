package view;

import java.io.FileNotFoundException;
import java.io.OutputStream;

import commons.Level2D;
/**
 * Responsible for displaying a level
 * @author itamar sheffer
 *
 */
public interface LevelDisplayer {
	boolean displayLevel(Level2D level, OutputStream output) throws FileNotFoundException;

	boolean displayLevelClear(Level2D level,OutputStream output) throws FileNotFoundException;
}
