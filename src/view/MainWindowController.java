package view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * An implementation of View, which uses JavaFX Windows to receive user input and display levels graphically.
 * @author itamar sheffer
 */
public class MainWindowController extends View implements Observer{
	String fileSourcePath="./levels";
	String fileLocation;
	File lastFileChosen;
	int playSpeed;
	boolean winCondition=false;
	Timeline secondPassed=null;
	
	Media backgroundMusic = new Media(new File("./resources/audio/backgroundMusic1.mp3").toURI().toString());

	boolean playerCanMove;
	
	Integer timePassed;
	Integer steps;
	MediaPlayer backgroundMusicPlayer;
	Boolean isPaused;
	
	@FXML
	Label stepsLabel;
	@FXML
	TextField upText;
	@FXML
	TextField downText;
	@FXML
	TextField rightText;
	@FXML
	TextField leftText;
	@FXML
	TextField restartText;
	@FXML
	TextField pauseText;
	@FXML
	Graphics2DDisplayer displayer;
	@FXML
	Label timePassedLabel;
	@FXML
	KeyCodeStorage keyCodes;
	
	@FXML
	Button oneStepButton;
	@FXML
	Button fullSolutionButton;
	
	public void init() {
		this.leveldisplayer=displayer;
		this.isPaused=false;
		
	}
	
	public void pauseLevel()
	{
		if(!this.isPaused)
		{
			this.isPaused=true;
			this.timePassed=0;
			this.backgroundMusicPlayer.pause();
		}
		
	}
	
	public void edit()
	{
		this.upText.setEditable(true);
		this.downText.setEditable(true);
		this.rightText.setEditable(true);
		this.leftText.setEditable(true);
		this.restartText.setEditable(true);
		this.pauseText.setEditable(true);
	}
	
	public void finishEdit()
	{
		this.keyCodes.setUpKey(this.upText.getText());
		this.keyCodes.setDownKey(this.downText.getText());
		this.keyCodes.setRightKey(this.rightText.getText());
		this.keyCodes.setLeftKey(this.leftText.getText());
		this.keyCodes.setRestartLevelKey(this.restartText.getText());
	}
	
	public void openFile() throws FileNotFoundException
	{	
		FileChooser fc=new FileChooser();
		fc.setTitle("Choose file load location");
		fc.setInitialDirectory(new File(this.fileSourcePath));
		File chosen =fc.showOpenDialog(null);
		this.fileLocation=chosen.getPath();
		this.timePassed=0;
		if(chosen != null)
		{
			this.lastFileChosen=chosen;
			LinkedList<String> params = new LinkedList<String>();
			params.add("Load");
			params.add(chosen.getPath());
			this.setChanged();
			this.notifyObservers(params);

			secondPassed = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
				
				if(this.winCondition)
				{
					this.backgroundMusicPlayer.stop();
					this.displayWinMessage();
					Platform.runLater(()->{
						try {
							secondPassed.stop();
							FXMLLoader loader = new FXMLLoader(getClass().getResource("WinMenu.fxml"));
							
				        	loader.setController(new WinMenuController(this.timePassed,this.steps, level.getName(),level.getDifficulty()));
				        	BorderPane root1 = (BorderPane)loader.load();
				            Stage stage = new Stage();
				            stage.setTitle("Congratulations!");
				            stage.setScene(new Scene(root1));  
				            stage.show();
				            Stage stage1 = (Stage) stepsLabel.getScene().getWindow();
				            stage1.close();
				        }
				        catch (IOException e) {
				            e.printStackTrace();
				        }
					});
				}
				if(this.isPaused==false)
				{
					this.timePassed++;
					this.timePassedLabel.textProperty().set(" "+timePassed);
					
				}
		    }));
			secondPassed.setCycleCount(Animation.INDEFINITE);
			secondPassed.play();

		}
		
	}
	
	public void saveFile() throws FileNotFoundException
	{
		if(this.fileLocation!=null)
		{
			LinkedList<String> params = new LinkedList<String>();
			params.add("Save");
			params.add(this.fileLocation);
			this.setChanged();
			this.notifyObservers(params);
		}
		else{
			saveFileAs();
		}
	}
	
	public void saveFileAs() throws FileNotFoundException
	{
		
		FileChooser fc=new FileChooser();
		fc.setTitle("Choose file save location");
		fc.setInitialDirectory(new File(this.fileSourcePath));
		File chosen =fc.showOpenDialog(null);
		this.fileLocation=chosen.getPath();
		if(chosen!=null)
		{
			this.lastFileChosen=chosen;
			LinkedList<String> params = new LinkedList<String>();
			params.add("Save");
			params.add(this.fileLocation);
			this.setChanged();
			this.notifyObservers(params);
		}
	}

	public MainWindowController(boolean playerControl) {
		this.playerCanMove=playerControl;
	}
	
	public void startLevel() throws FileNotFoundException
	{
		if(this.backgroundMusicPlayer==null)
		{
			this.backgroundMusicPlayer= new MediaPlayer(backgroundMusic);
		}
		this.backgroundMusicPlayer.stop();
		this.backgroundMusicPlayer.play();
		this.leveldisplayer=displayer;
		LinkedList<String> params = new LinkedList<String>();
		params.add("displayclear");
		params.add("graphics2d");
		this.setChanged();
		this.notifyObservers(params);
	}
	
	public void openControlsMenu()
	{
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getClassLoader().getResource("./view/ControlsMenu.fxml"));
			Stage st = new Stage();
			st.setTitle("Controls");
			st.setScene(new Scene(root, 450, 450));
			st.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void restart() throws FileNotFoundException
	{
		if(this.lastFileChosen != null)
		{
			this.timePassed=0;
			LinkedList<String> params = new LinkedList<String>();
			params.add("Load");
			params.add(this.lastFileChosen.getPath());
			this.setChanged();
			this.notifyObservers(params);
			startLevel();
		}
	}

	
	private void handleMovementInput(KeyEvent event)
	{
		String key=event.getCode().getName();
		
		if(isPaused==false)
		{
			LinkedList<String> params = new LinkedList<String>();
    		params.add("Move");
			if(key.toLowerCase().equals(this.keyCodes.getUpKey().toLowerCase()))
			{
				params.add("UP");
	    		setChanged();
	    		notifyObservers(params);
			}
			else if(key.toLowerCase().equals(this.keyCodes.getDownKey().toLowerCase()))
			{
				params.add("DOWN");
	    		setChanged();
	    		notifyObservers(params);
			}
			else if(key.toLowerCase().equals(this.keyCodes.getRightKey().toLowerCase()))
			{
				params.add("RIGHT");
	    		setChanged();
	    		notifyObservers(params);
			}
			else if(key.toLowerCase().equals(this.keyCodes.getLeftKey().toLowerCase()))
			{
					params.add("LEFT");
		    		setChanged();
		    		notifyObservers(params);
			}
			
    		params = new LinkedList<String>();
    		params.add("Display");
    		params.add("graphics2d");
    		setChanged();
    		notifyObservers(params);
    		stepsLabel.textProperty().set(""+level.getNumOfSteps());
		}
	}
	
	private void handleMiscInput(KeyEvent event) throws FileNotFoundException
	{
		String key=event.getCode().getName().toLowerCase();
		if(key.equals(this.restartText.getText().toLowerCase()))
		{
			restart();
		}
	}
	

	@Override
	public void start() {
		this.timePassed=0;
		this.steps=0;
		this.keyCodes.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				try{
					handleMovementInput(event);
					handleMiscInput(event);
				}catch(FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
			}
		});
	}

	@Override
	public void displayMessage(String msg) {
	}

	@Override
	public void displayWinMessage() {
		this.isPaused=true;
		this.winCondition=true;
    }
	
	public void requestSolution()
	{
		Platform.runLater(()->{
			try {
				secondPassed.stop();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestMenu.fxml"));
				RequestMenuController cont=new RequestMenuController();
				cont.addObserver(this);
	        	loader.setController(cont);
	        	AnchorPane root1 = (AnchorPane)loader.load();
	            Stage stage = new Stage();
	            stage.setTitle("Enter server details");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	            
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof RequestMenuController && arg instanceof LinkedList){
			LinkedList<String> args = (LinkedList<String>) arg;
			LinkedList<Object> params = new LinkedList<Object>();
			params.add("Solve");
			params.add(args.get(1));
			params.add(args.get(2));
			this.setChanged();
			this.notifyObservers(params);
		}
	}
	
	private void prepForSolution()
	{
		try {
			this.restart();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.secondPassed.play();
	}
	
	private boolean isFirstStep;
	public void showFullSolution()
	{
		//TODO: Maybe just send alot of commands one after the other
		prepForSolution();
		LinkedList<Object> params = new LinkedList<Object>();
		params.add("runFullSolution");
		this.setChanged();
		this.notifyObservers(params);
	}
	
	@Override
	public void solutionReady() {
		fullSolutionButton.setVisible(true);
		oneStepButton.setVisible(true);
		isFirstStep=true;
	}
	
	
	public void showOneStep()
	{
		if(isFirstStep) 
		{
			prepForSolution();
			isFirstStep=false;
		}
		LinkedList<Object> params = new LinkedList<Object>();
		params.add("runOneStep");
		this.setChanged();
		this.notifyObservers(params);
		params = new LinkedList<Object>();
		params.add("display");
		params.add("graphics2d");
		this.setChanged();
		this.notifyObservers(params);
	}

	
}