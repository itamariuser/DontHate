package model.data;

import java.io.OutputStream;
import java.util.ArrayList;

import common.Level2D;
import common.Position2D;
import gameObjects.GameObject;
import gameObjects.Textures;

public class TextLevel2DSaver implements LevelSaver {

	Level2D level;
	private String checkMax(ArrayList<GameObject> z)
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
	
	public boolean SaveLevel(OutputStream out, Level2D level) throws Exception  {
		int tempX = 0;
		int tempY = 0;
		
		for (int i = 0; i < level.getName().length(); i++) {
			out.write(level.getName().charAt(i));
		}
		out.write('\n');
		out.write(level.getDifficulty()+48);
		out.write('\n');
		out.write(level.getLevelNum()+48);
		out.write('\n');
		for (Position2D pos : level.getPositionObjectLayout().keySet()) {
			if(pos.getY() > tempY)
				tempY = pos.getY();
			if(pos.getX() > tempX)
				tempX = pos.getX();	
		}

		
		for (int j = 0; j < tempY+1 ; j++) {
			for (int i = 0; i < tempX+1; i++) {
				Position2D temp = new Position2D(i ,j);
				for (Position2D pos : level.getPositionObjectLayout().keySet()) {
					if(pos.getX() == temp.getX() && pos.getY() == temp.getY())
					{
						out.write((new Textures().getDictionary().get(checkMax(level.getPositionObjectLayout().get(pos)))));
					}
				}
			}
			out.write('\n');
		}
		
		return true;
	}
}
