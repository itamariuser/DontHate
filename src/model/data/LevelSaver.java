package model.data;

import java.io.OutputStream;

import common.Level2D;

public interface LevelSaver {
	/**
	 * Save a level to an output stream.
	 * @param out - The OutputStream.
	 * @param level - The level
	 * @return true if save was successful, false otherwise.
	 * @throws Exception
	 */
	public boolean SaveLevel(OutputStream out,Level2D level) throws Exception;
}
