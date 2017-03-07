package model.data;

import java.io.OutputStream;

import commons.Level2D;

public interface LevelSaver {
	public boolean SaveLevel(OutputStream out,Level2D level) throws Exception;
}
