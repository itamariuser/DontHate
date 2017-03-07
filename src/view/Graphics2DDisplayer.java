package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import commons.Level2D;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.data.GameObject;
import model.data.MainCharacter;
import model.data.Position2D;
/**
 * Responsible for displaying level graphically
 * @author itamar sheffer
 *
 */
public class Graphics2DDisplayer extends Canvas implements LevelDisplayer  {
	private HashMap<String ,String> fileNameFactory;
	private StringProperty wallFilePath;
	static boolean cleared=false;
	String toClient;
	
	
	public static boolean isCleared() {
		return cleared;
	}

	public static void setCleared(boolean cleared) {
		Graphics2DDisplayer.cleared = cleared;
	}

	public String getwallFilePath() {
		return wallFilePath.get();
	}

	public void setWallFileName(String wallFileName) {
		this.wallFilePath.set(wallFileName);
	}
	
	

	public void addTexture(String textureObject,String filePath)
	{
		fileNameFactory.put(textureObject, filePath);
	}
	
	public Graphics2DDisplayer() {
		this.wallFilePath=new SimpleStringProperty();
		if(fileNameFactory==null)
		{
			fileNameFactory=new HashMap<String ,String>();
		}
	}
	
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
	
	@Override
	public boolean displayLevel(Level2D level, OutputStream output) throws FileNotFoundException {
		
		GraphicsContext gc=getGraphicsContext2D();
		
		int tempX = 0;
		int tempY = 0;
		
		for (Position2D pos : level.getPositionObjectLayout().keySet()) {
			if(pos.getY() > tempY)
				tempY = pos.getY();
			if(pos.getX() > tempX)
				tempX = pos.getX();	
		}
		double W=getWidth();
		double H=getHeight();
		double w=W/(tempX+1);
		double h=H/(tempY+1);
		
		for (int j = 0; j < tempY+1 ; j++) {
			for (int i = 0; i < tempX+1; i++) {
				Position2D temp = new Position2D(i ,j);
				for (Position2D pos : level.getPositionObjectLayout().keySet()) {
					if(pos.getX() == temp.getX() && pos.getY() == temp.getY())
					{
						if (checkMax(level.getPositionObjectLayout().get(pos)).equals(MainCharacter.class.getName().toString())) {
						}
						else {
							String filePath = new GraphicTextures().getDictionary().get(checkMax(level.getPositionObjectLayout().get(pos)));
							File file = new File(filePath);
							gc.drawImage(new Image(new FileInputStream(file)), i*w, j * h,w,h);
						}
						
					}
				}
			}
		}
		return true;
	}
	@Override
	public boolean displayLevelClear(Level2D level,OutputStream output) throws FileNotFoundException {
		GraphicsContext gc=getGraphicsContext2D();
		
		int tempX = 0;
		int tempY = 0;
		
		for (Position2D pos : level.getPositionObjectLayout().keySet()) {
			if(pos.getY() > tempY)
				tempY = pos.getY();
			if(pos.getX() > tempX)
				tempX = pos.getX();	
		}
		double W=getWidth();
		double H=getHeight();
		double w=W/(tempX+1);
		double h=H/(tempY+1);
		gc.drawImage(new Image(new FileInputStream("./resources/Blankspace2.png")), 0, 0,getWidth(),getHeight());
		for (int j = 0; j < tempY+1 ; j++) {
			for (int i = 0; i < tempX+1; i++) {
				Position2D temp = new Position2D(i ,j);
				for (Position2D pos : level.getPositionObjectLayout().keySet()) {
					if(pos.getX() == temp.getX() && pos.getY() == temp.getY())
					{
						String filePath = new GraphicTextures().getDictionary().get(checkMax(level.getPositionObjectLayout().get(pos)));
						File file = new File(filePath);
						gc.drawImage(new Image(new FileInputStream(file)), i*w, j * h,w,h);
					}
				}
			}
		}
		return true;
	}

}
