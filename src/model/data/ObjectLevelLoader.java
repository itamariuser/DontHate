package model.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import common.Level;

public class ObjectLevelLoader implements LevelLoader {

	@Override
	public Level loadLevelFromStream(InputStream in) {
		Level newLevel=null;
		try{
			ObjectInputStream inp = new ObjectInputStream(in);
			newLevel = (Level)inp.readObject();
		}catch(IOException e)
		{
			e.printStackTrace();
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}

		return newLevel;
	}

}