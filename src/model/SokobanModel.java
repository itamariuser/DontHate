package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import commons.Level;
import commons.Level2D;
import commons.ServerPlan;
import javafx.animation.Timeline;
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
	private LinkedList<String> params = new LinkedList<String>();
	private Level2D level;
	private MySokobanPolicy policy;
	private String direction;
	private MainCharacter ch;
	private ServerPlan solution;
	private ServerPlan currentSolution;
	private ArrayList<String> directionsAvailable;
	
	public void resetCurrentSolution()
	{
		currentSolution=solution;
	}
	
	
	
	public ServerPlan getCurrentSolution() {
		return currentSolution;
	}



	public void setCurrentSolution(ServerPlan currentSolution) {
		this.currentSolution = currentSolution;
	}



	public LinkedList<String> getParams() {
		return params;
	}


	public void setParams(LinkedList<String> params) {
		this.params = params;
	}


	public MySokobanPolicy getPolicy() {
		return policy;
	}


	public void setPolicy(MySokobanPolicy policy) {
		this.policy = policy;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}


	public MainCharacter getCh() {
		return ch;
	}


	public void setCh(MainCharacter ch) {
		this.ch = ch;
	}


	public ServerPlan getSolution() {
		return solution;
	}


	public void setSolution(ServerPlan solution) {
		this.solution = solution;
	}


	public ArrayList<String> getDirectionsAvailable() {
		return directionsAvailable;
	}


	public void setDirectionsAvailable(ArrayList<String> directionsAvailable) {
		this.directionsAvailable = directionsAvailable;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getFileType() {
		return fileType;
	}


	public void setFileType(String fileType) {
		this.fileType = fileType;
	}


	public void setLevel(Level2D level) {
		this.level = level;
	}


	//for file manipulation
	private String filePath;
	private static HashMap<String, LevelLoader> levelLoaderFactory;
	private static HashMap<String,LevelSaver> levelSaverFactory;
	private String fileType;
	
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
		
		this.solution=new ServerPlan(new LinkedList<>());
		this.currentSolution=new ServerPlan(new LinkedList<>());
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
			throw new Exception("InvalidDirection: "+direction);
		}
		
		//Send the move command to the policy
		if(!this.policy.moveMainCharacter(this.level.getMainCharacter(), direction))
		{
			System.out.println("Can't move to this direction");
		}
		if(this.level.isWinCondition())
		{
			params.clear();
			params.add("win");
			params.add("win");
			setChanged();
			notifyObservers(params);
		}
	}
	
	@Override
	public void runSolution(Queue<String> moveQueue) {
//		LinkedList<String> q= new LinkedList<>();
//		q.addAll(moveQueue);
//		Timer t=new Timer();
//		t.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				String direction=q.removeFirst().split(" ")[1];
//				try {
//					move(direction);
//					System.out.println(direction);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				params=new LinkedList<>();
//				params.add("display");
//				params.add("graphics2d");
//				setChanged();
//				notifyObservers(params);
//			}
//		}, 500);
		
		
		
	}
	
	
	@Override
	public Level2D getLevel() {
		return level;
	}
	
	@Override
	public void setLevel(Level level) {
		this.level = (Level2D) level;
		params.clear();
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
		params.clear();
		params.add("doNothing");
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
		params.clear();
		params.add("DoNothing");
		this.setChanged();
		this.notifyObservers(params);
		
	}



	

}
