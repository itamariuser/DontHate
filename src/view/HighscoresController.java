package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import db.GameSession;
import db.GameSessionKey;
import db.SokobanDBManager;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HighscoresController  {
	
	Scene scene;
	@FXML
	private TableView<GameSession> tableView;
	
	@FXML
	Button s;
	
	String levelName;
	int stepsTaken;
	int timePassed;
	
	@FXML
	TextField searchField;
	
	GameSession sesh1;
	GameSession sesh2;
	GameSession sesh3;
	GameSession sesh4;
	
	@SuppressWarnings({ "unchecked", "unused" })
	public void init() {
		//STUB FOR GAME SESSIONS FROM SQL
		Date dat1=new Date();
		dat1.setYear(2017);
		dat1.setMonth(6);
		dat1.setDate(15);
		dat1.setHours(14);
		dat1.setMinutes(56);
		dat1.setSeconds(33);
		sesh1=new GameSession(new GameSessionKey(levelName, "Itamar"),50, 30,dat1);
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("AYLMAO");
		
		Date dat2=new Date();
		dat2.setYear(2017);
		dat2.setMonth(6);
		dat2.setDate(19);
		dat2.setHours(16);
		dat2.setMinutes(4);
		dat2.setSeconds(15);
		
		sesh2=new GameSession(new GameSessionKey(levelName, "Matan"),60, 30,dat2);
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("MOSHE");
		
		Date dat3=new Date();
		dat3.setYear(2017);
		dat3.setMonth(7);
		dat3.setDate(21);
		dat3.setHours(22);
		dat3.setMinutes(23);
		dat3.setSeconds(43);
		sesh3=new GameSession(new GameSessionKey(levelName, "Moshe"),40, 45,dat3);
		
		
		sesh4=new GameSession(new GameSessionKey(levelName, "Itamar"),timePassed, stepsTaken,new Date());
		
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("DODA");
		//END OF STUB
		
		
		//Configure columns
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
				return new SimpleStringProperty(new Integer(arg0.getValue().getSteps()).toString());
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
		
		//Add data
//		SokobanDBManager dbm=SokobanDBManager.getInstance();
//		ArrayList<GameSession> arr=dbm.getGameSessionsWithLevelName(levelName);//TODO
		
		//Detect click
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        openPlayerWindow(newSelection);
		    };
		});
		
		
		//********************* FILTER BY SEARCH FIELD****************
		ObservableList<GameSession> l=FXCollections.observableArrayList();
		l.addAll(sesh1,sesh2,sesh3,sesh4);
//		l.addAll(arr);
		tableView.setItems(l);
		FilteredList<GameSession> data=new FilteredList<>(l);
		
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        openPlayerWindow(newSelection);
		    };
		});
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				data.setPredicate(sesh -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (sesh.getKey().getPlayerName().toLowerCase().contains(lowerCaseFilter))
                    return true; // Filter matches first name.
                return false; // Does not match.
            });
        });
		 SortedList<GameSession> sortedData = new SortedList<>(data);
		 sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		 tableView.setItems(sortedData);
	}
	
	/**
	 * Display a window for the current game session and the player.
	 * @param sesh - The game session.
	 */
	private void openPlayerWindow(GameSession sesh)
	{
		Platform.runLater(()->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerDetails.fxml"));
				loader.setController(new PlayerDetailsController(sesh));
				Parent root = (Parent) loader.load();
		        Stage stage = new Stage();
		        Scene scene = new Scene(root);      
		        stage.setTitle("Player Details: "+ sesh.key.getPlayerName());
		        stage.setScene(scene);
		        stage.setResizable(false);
		        stage.show();

	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
		});
	}

	public HighscoresController(String levelName, int stepsTaken, int timePassed) {
		super();
		this.levelName = levelName;
		this.stepsTaken = stepsTaken;
		this.timePassed = timePassed;
	}
	
}
