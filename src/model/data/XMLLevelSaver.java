package model.data;

import java.beans.XMLEncoder;
import java.io.OutputStream;

import commons.Level2D;

public class XMLLevelSaver implements LevelSaver {

	@Override
	public boolean SaveLevel(OutputStream out, Level2D level) {
		XMLEncoder e = new XMLEncoder(out);
			e.writeObject(level);
			e.close();
		return true;
	}

}
