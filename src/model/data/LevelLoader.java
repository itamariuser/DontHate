package model.data;

import java.io.InputStream;

import common.Level;

public interface LevelLoader {
	/**
	 * Loads a level from an input stream.
	 * @param in - The InputStream.
	 * @return The loaded level.
	 * @throws Exception
	 */
	public Level loadLevelFromStream(InputStream in) throws Exception;
}
