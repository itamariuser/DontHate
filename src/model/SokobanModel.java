package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;

import commons.Level;
import commons.Level2D;
import model.data.LevelLoader;
import model.data.LevelSaver;
import model.data.MainCharacter;
import model.data.ObjectLevelLoader;
import model.data.ObjectLevelSaver;
import model.data.TextLevel2DLoader;
import model.data.TextLevel2DSaver;
import model.data.XMLLevelLoader;
import model.data.XMLLevelSaver;
import model.policy.MySokobanPolicy;


/**
 * My implementation of a model, using files to save levels and a 2D level perspective
 * @author itamar sheffer
 *
 */
public class SokobanModel extends Observable implements Model  {
	//for level manipulation
	Level2D level;
	MySokobanPolicy policy;
	String direction;
	MainCharacter ch;
	ArrayList<String> directionsAvailable;
	
	//for file manipulation
	String filePath;
	static HashMap<String, LevelLoader> levelLoaderFactory;
	static HashMap<String,LevelSaver> levelSaverFactory;
	String fileType;
	
	public void addLoader(String endsWith,LevelLoader l)
	{
		if(levelLoaderFactory==null)
		{
			levelLoaderFactory=new HashMap<String, LevelLoader>();
		}
		levelLoaderFactory.put(endsWith, l);
	}
	
	
	public SokobanModel() {
		this.directionsAvailable=new ArrayList<String>();
		this.directionsAvailable.add("up");
		this.directionsAvailable.add("down");
		this.directionsAvailable.add("left");
		this.directionsAvailable.add("right");
		
		addLoader("obj",new ObjectLevelLoader());
		addLoader("txt",new TextLevel2DLoader());
		addLoader("xml",new XMLLevelLoader());
		
		addSaver("obj", new ObjectLevelSaver());
		addSaver("xml", new XMLLevelSaver());
		addSaver("txt", new TextLevel2DSaver());
	}
	
	public void addSaver(String endsWith,LevelSaver l)
	{
		if(levelSaverFactory==null)
		{
			levelSaverFactory=new HashMap<String, LevelSaver>();
		}
		levelSaverFactory.put(endsWith, l);
	}
	
	public SokobanModel(Level2D level) {
		this();
		this.level=level;
	}
	
	
	@Override
	public void move(String direction) throws Exception {
		this.policy = new MySokobanPolicy(this.level);
		boolean doestDirectionExist=false;
		//get direction from factory
		for (String string : this.directionsAvailable) {
			if(string.toLowerCase().equals(direction.toLowerCase()))
			{
				doestDirectionExist=true;
			}
		}
		if(doestDirectionExist==false)
		{
			throw new Exception("InvalidDirection");
		}
		
		//Send the move command to the policy
		if(!this.policy.moveMainCharacter(this.level.getMainCharacter(), direction))
		{
			System.out.println("Can't move to this direction");
		}
	}
	
	@Override
	public Level2D getLevel() {
		return level;
	}
	
	@Override
	public void setLevel(Level level) {
		this.level = (Level2D) level;
		LinkedList<String> params = new LinkedList<String>();
		params.add("DoNothing");
		this.setChanged();
		this.notifyObservers(params);
	}
	

	@Override
	public void loadLevel(String filePath) throws Exception {
		this.filePath=filePath;
		String tempName=new File(this.filePath).getName();
		
		//take the file type and load the level in a fitting levelLoader
		String[] tokens = tempName.split("\\.(?=[^\\.]+$)");
		this.fileType=tokens[tokens.length-1];
		FileInputStream fileIn=null;
		fileIn = new FileInputStream(this.filePath);
		if(levelLoaderFactory.get(this.fileType)!=null)
		{
			this.setLevel(levelLoaderFactory.get(this.fileType).loadLevelFromStream(fileIn));
			fileIn.close();
		}
		else
		{
			fileIn.close();
			throw new Exception("fileNotFound");//file format not supported, prompt to add level loader
		}
		LinkedList<String> params = new LinkedList<String>();
		params.add("updateView");
		this.setChanged();
		this.notifyObservers(params);
	}


	@Override
	public void saveLevel(String filePath) throws Exception {
		this.filePath=filePath;//
		String[] tokens = this.filePath.split("\\.(?=[^\\.]+$)");
		this.fileType=tokens[tokens.length-1];
		
		//take the file type and save the level in a fitting levelSaver
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(this.filePath);
			LevelSaver saver=levelSaverFactory.get(this.fileType);
			if(!saver.SaveLevel(fileOut, level))
			{
				throw new Exception("File couldn't be saved.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		LinkedList<String> params = new LinkedList<String>();
		params.add("DoNothing");
		this.setChanged();
		this.notifyObservers(params);
		
	}
	

}
