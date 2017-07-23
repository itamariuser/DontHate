package view;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import common.Level2D;
import common.Position2D;
import gameObjects.GameObject;
import gameObjects.Textures;

/**
 * Responsible for displaying a level by text
 * @author itamar sheffer
 *
 */
public class TextDisplayer implements LevelDisplayer {

	public String display;
	
	//return the object with the highest priority in a given position (arrayList)
	String checkMax(ArrayList<GameObject> z)
	{
		int maxPriority=-1;
		String c=" ";
		for (GameObject gameObject : z) {
			if(gameObject.getPriority()>=maxPriority)
			{
				maxPriority=gameObject.getPriority();
				c=gameObject.getClass().toString();
			}
		}
		return c;
	}
	
	public String getDisplay() {
		return display;
	}

	@Override
	public boolean displayLevel(Level2D level, OutputStream output) 
	{
		int tempX = 0;
		int tempY = 0;
		PrintWriter out = new PrintWriter(output);
		//find the longest row and column in the level
		for (Position2D pos : level.getPositionObjectLayout().keySet()) {
			if(pos.getY() > tempY)
				tempY = pos.getY();
			if(pos.getX() > tempX)
				tempX = pos.getX();	
		}
		System.out.println();
		
		//for each position (i,j) by order, print the game objects in it, according to checkMax (meaning by their priority)
		for (int j = 0; j < tempY+1 ; j++) {
			for (int i = 0; i < tempX+1; i++) {
				Position2D temp = new Position2D(i ,j);
				for (Position2D pos : level.getPositionObjectLayout().keySet()) {
					if(pos.getX() == temp.getX() && pos.getY() == temp.getY())
					{
						char c = new Textures().getDictionary().get(checkMax(level.getPositionObjectLayout().get(pos)));
						out.print(c);
					}
				}
			}
			out.println();
		}
		
		return true;
	}

	@Override
	public boolean displayLevelClear(Level2D level,OutputStream output) throws FileNotFoundException {
		return displayLevel(level, output);
	}
}