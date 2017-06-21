package view;

import java.io.IOException;
import java.util.Date;

import db.GameSession;
import db.GameSessionKey;
import db.LevelEntity;
import db.PlayerEntity;
import db.SokobanDBManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class WinMenuController{

	Integer timePassed;
	Integer stepsTaken;
	String levelName;
	
	
	MediaPlayer backgroundMusicPlayer;
	//play this https://www.youtube.com/watch?v=wDajqW561KM
	@FXML
	Button button1;
	
	@FXML
	Button returnToMenu;
	
	@FXML
	TextField playerName;
	
	SokobanDBManager dbm;
	
	public WinMenuController(Integer timePassed, Integer steps,String levelName) {
		this.timePassed=timePassed;
		this.stepsTaken=steps;
		this.levelName=levelName;
		dbm= SokobanDBManager.getInstance();
	}
	
	
	
	
	
	public void registerHighscore(){
		//register in highscores
//		GameSessionKey key=new GameSessionKey(this.levelName, playerName.getText());
//		GameSession session=new GameSession(timePassed, stepsTaken, new Date());
//		session.setKey(key);
//		SokobanDBManager.getInstance().addGameSession(session);
//		showHighscores();
		
		//TODO: ADD GAME SESSION IN SQL (USING SokobanDBmanager)
		
		dbm.add(new GameSession(timePassed, stepsTaken, new Date(),levelName,playerName.getText()));
	}
	
	public void showHighscores(){
		//open highscores window
		dbm.add(new LevelEntity("EYY",1));
		dbm.add(new PlayerEntity("player 1",1));
		

		Platform.runLater(()->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Highscores.fxml"));
				loader.setController(new HighscoresController( levelName));
				Parent root = (Parent) loader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root);      
		        stage.setTitle("Highscore Board");
		        stage.setScene(scene);
		        stage.setResizable(false);
		        stage.show();
	            Stage stage1 = (Stage) button1.getScene().getWindow();
	            stage1.close();

	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		});
		

	}
	
	public void exitGame(){
		Platform.exit();
		System.exit(0);
	}
	
	
}
