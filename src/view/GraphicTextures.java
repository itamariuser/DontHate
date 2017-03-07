package view;


import java.io.FileNotFoundException;
import java.util.HashMap;

import model.data.BlankSpace;
import model.data.Crate;
import model.data.GoalPoint;
import model.data.MainCharacter;
import model.data.Wall;

/**
 * Responsible for storing the images of how game objects will be displayed graphically
 * @author itamar sheffer
 *
 */

public class GraphicTextures {
	private HashMap<String,String> dictionary;
	
	
public GraphicTextures() throws FileNotFoundException {
	this.dictionary=new HashMap<String,String>();
	dictionary.put(new Wall().getClass().toString(), "./resources/Metalblock.png");
	dictionary.put(new BlankSpace().getClass().toString(),"./resources/Blankspace2.png");
	dictionary.put(new MainCharacter().getClass().toString(),"./resources/MainCharacter.bmp");
	dictionary.put(new Crate().getClass().toString(),"./resources/Crate.bmp");
	dictionary.put(new GoalPoint().getClass().toString(),"./resources/GoalPoint.png");
}


public HashMap<String, String> getDictionary() {
	return this.dictionary;
}


public void setDictionary(HashMap<String, String> dictionary) {
	this.dictionary = dictionary;
}
}
