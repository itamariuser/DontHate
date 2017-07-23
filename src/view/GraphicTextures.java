package view;

import java.io.FileNotFoundException;
import java.util.HashMap;

import gameObjects.BlankSpace;
import gameObjects.Crate;
import gameObjects.GoalPoint;
import gameObjects.MainCharacter;
import gameObjects.Wall;

/**
 * A singleton class responsible for storing the images of how game objects will be
 * displayed in a 2D graphic environment.
 */

public class GraphicTextures {
	private HashMap<String, String> dictionary;

	private static GraphicTextures instance;
	
	public static GraphicTextures getInstace() throws FileNotFoundException
	{
		if(instance==null)
		{
			instance=new GraphicTextures();
		}
		return instance;
	}
	/**
	 * Responsible for initializing the file paths of all game objects. Add more
	 * paths here if any new types of game objects were added.
	 * 
	 * @throws FileNotFoundException
	 *             - If a file was not found.
	 */
	private GraphicTextures() throws FileNotFoundException {
		this.dictionary = new HashMap<String, String>();
		dictionary.put(new Wall().getClass().toString(), "./resources/Metalblock.png");
		dictionary.put(new BlankSpace().getClass().toString(), "./resources/Blankspace2.png");
		dictionary.put(new MainCharacter().getClass().toString(), "./resources/MainCharacter.bmp");
		dictionary.put(new Crate().getClass().toString(), "./resources/Crate.bmp");
		dictionary.put(new GoalPoint().getClass().toString(), "./resources/GoalPoint.png");
	}

	public HashMap<String, String> getDictionary() {
		return this.dictionary;
	}

	public void setDictionary(HashMap<String, String> dictionary) {
		this.dictionary = dictionary;
	}
}
