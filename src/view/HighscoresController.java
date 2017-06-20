package view;

import java.io.IOException;
import java.util.Date;

import commons.GameSession;
import commons.GameSessionKey;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HighscoresController  {
	
	Scene scene;
	@FXML
	private TableView<GameSession> tableView;
	
	@FXML
	Button s;
	String levelName;
	@SuppressWarnings("unchecked")
	public void init() {
		//STUB FOR GAME SESSIONS FROM SQL
		GameSession sesh1=new GameSession(1, 1,new Date());
		sesh1.setKey(new GameSessionKey(levelName, "AYLMAO"));
		
		GameSession sesh2=new GameSession(3, 2,new Date());
		sesh2.setKey(new GameSessionKey(levelName, "MOSHE"));
		
		GameSession sesh3=new GameSession(2, 3,new Date());
		sesh3.setKey(new GameSessionKey(levelName, "MOSHE"));
		
		//END OF STUB
		
		TableColumn<GameSession,String> nameCol=new TableColumn<>("Name");
		nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameSession,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<GameSession, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getKey().getPlayerName());
			}
		});
		
		TableColumn<GameSession,String> dateCol=new TableColumn<>("Date");
		dateCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameSession,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<GameSession, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getDate().toString());
			}
		});
		
		TableColumn<GameSession,String> stepCol=new TableColumn<>("Steps");
		stepCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameSession,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<GameSession, String> arg0) {
				return new SimpleStringProperty(new Integer(arg0.getValue().getNumOfSteps()).toString());
			}
		});
		
		TableColumn<GameSession,String> timeCol=new TableColumn<>("Time (Seconds)");
		timeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameSession,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<GameSession, String> arg0) {
				return new SimpleStringProperty(new Integer(arg0.getValue().getCompletionTime()).toString());
			}
		});
		nameCol.setPrefWidth(140);
		dateCol.setPrefWidth(180);
		stepCol.setPrefWidth(113);
		timeCol.setPrefWidth(123);
		ObservableList<TableColumn<GameSession, String>>  list=FXCollections.observableArrayList();
		list.addAll(nameCol,dateCol,stepCol,timeCol);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(list);
		
		tableView.getItems().addAll(sesh1,sesh2,sesh3);
		
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        openPlayerWindow(newSelection);
		    };
		});
	}
	
	private void openPlayerWindow(GameSession sesh)
	{
		Platform.runLater(()->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerDetails.fxml"));
				loader.setController(new PlayerDetailsController( sesh.key.getPlayerName()));
				Parent root = (Parent) loader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root);      
		        stage.setTitle("Player Details");
		        stage.setScene(scene);
		        stage.setResizable(false);
		        stage.show();

	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		});
	}
	public HighscoresController(String levelName) {
		this.levelName=levelName;
	}

	public void sortSteps()
	{
		
	}
	
	public void sortTime()
	{
		
	}
}
