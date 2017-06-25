package view;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import db.GameSession;
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
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class WinMenuController implements Observer {

	Integer timePassed;
	Integer stepsTaken;
	String levelName;

	MediaPlayer backgroundMusicPlayer;
	// play this https://www.youtube.com/watch?v=wDajqW561KM
	@FXML
	Button button1;

	@FXML
	Button returnToMenu;

	@FXML
	TextField playerName;

	SokobanDBManager dbm;

	int levelDifficulty;

	public WinMenuController(Integer timePassed, Integer steps, String levelName, int levelDifficulty) {
		this.timePassed = timePassed;
		this.stepsTaken = steps;
		this.levelName = levelName;
		this.levelDifficulty = levelDifficulty;
		dbm = SokobanDBManager.getInstance();
	}

	public void registerHighscore() {
		List<LevelEntity> levels = dbm.getLevelsWithName(levelName);
		if (levels.isEmpty()) {
			dbm.add(new LevelEntity(levelName, levelDifficulty));
		}
		List<PlayerEntity> players = dbm.getPlayersWithName(playerName.getText());
		if (players.isEmpty()) {
			dbm.add(new PlayerEntity(playerName.getText(), 1));
		} else {
			PlayerEntity player = new PlayerEntity(players.get(0).getPlayerName(), players.get(0).getWinCount() + 1);
			dbm.updatePlayerWinCount(player.getWinCount() + 1, player.getWinCount(), playerName.getText());
		}

		dbm.add(new GameSession(timePassed, stepsTaken, new Date(), levelName, playerName.getText()));

		showHighscores();
	}

	/**
	 * open highscores window
	 */
	public void showHighscores() {
		Platform.runLater(() -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Highscores.fxml"));
				loader.setController(new HighscoresController(levelName));
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setTitle("Highscore Board");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage stage1 = (Stage) button1.getScene().getWindow();
				stage1.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	public void exitGame() {
		Platform.exit();
		System.exit(0);
	}

	public void requestSolution() {
		Platform.runLater(() -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("RequestMenu.fxml"));
				RequestMenuController cont = new RequestMenuController();
				cont.addObserver(WinMenuController.this);
				loader.setController(cont);
				Parent root = (Parent) loader.load();
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setTitle("Ask for solution");
				stage.setScene(scene);
				stage.setResizable(false);
				stage.show();
				Stage stage1 = (Stage) button1.getScene().getWindow();
				stage1.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		// receive port, ip from requestWindowController
		
	}

}
