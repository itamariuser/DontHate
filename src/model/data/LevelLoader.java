package model.data;

import java.io.InputStream;

import commons.Level;

public interface LevelLoader {
	public Level loadLevelFromStream(InputStream in) throws Exception;
}
