package view;

import java.util.ArrayList;
import java.util.Date;

import db.GameSession;
import db.GameSessionKey;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class PlayerDetailsController {

	String playerName;
	Date playerDate;
	int steps;
	int time;
	@FXML
	TableView<GameSession> tableView;
	
	@FXML
	TextField searchField;
	
	
	GameSession sesh1;
	GameSession sesh2;
	GameSession sesh3;
	GameSession sesh4;
	
	public PlayerDetailsController(GameSession s) {
		this.playerDate=s.getDate();
		this.playerName=s.getKey().getPlayerName();
		this.steps=s.getSteps();
		this.time=s.getCompletionTime();
	}
	
	
	@SuppressWarnings("unchecked")
	public void init()
	{
		//DELETE
		Date dat1=new Date();
		dat1.setYear(2017);
		dat1.setMonth(6);
		dat1.setDate(15);
		dat1.setHours(14);
		dat1.setMinutes(56);
		sesh1=new GameSession(new GameSessionKey("Level 2", "Itamar"),50, 30,dat1);
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("AYLMAO");
		
		Date dat2=new Date();
		dat2.setYear(2017);
		dat2.setMonth(6);
		dat2.setDate(19);
		dat2.setHours(16);
		dat2.setMinutes(4);
		
		sesh2=new GameSession(new GameSessionKey("Level 2", "Matan"),60, 30,dat2);
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("MOSHE");
		
		Date dat3=new Date();
		dat3.setYear(2017);
		dat3.setMonth(7);
		dat3.setDate(21);
		dat3.setHours(22);
		dat3.setMinutes(23);
		
		sesh3=new GameSession(new GameSessionKey("Level 2", "Moshe"),40, 45,dat3);
		
		
		sesh4=new GameSession(new GameSessionKey("Level 2", this.playerName),this.time, this.steps,playerDate);
		
		//END
		
		TableColumn<GameSession,String> levelCol=new TableColumn<>("Level");
		levelCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameSession,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<GameSession, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getKey().getLevelName());
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
		levelCol.setPrefWidth(140);
		dateCol.setPrefWidth(180);
		stepCol.setPrefWidth(113);
		timeCol.setPrefWidth(123);
		ObservableList<TableColumn<GameSession, String>>  list=FXCollections.observableArrayList();
		list.addAll(levelCol,dateCol,stepCol,timeCol);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(list);
		
		
//		SokobanDBManager dbm=SokobanDBManager.getInstance();
//		ArrayList<GameSession> arr=dbm.getGameSessionsWithPlayerName(playerName);
		
		//********************* FILTER BY SEARCH FIELD****************
		ObservableList<GameSession> l=FXCollections.observableArrayList();
		
		//DELETE
		ObservableList<GameSession> z=FXCollections.observableArrayList();
		z.addAll(sesh1,sesh2,sesh3,sesh4);
		for (GameSession gameSession : z) {
			if(gameSession.getKey().getPlayerName().equals(playerName)){
				l.add(gameSession);
			}
		}
		//END
		
		//RESTORE
//		l.addAll(sesh1,sesh2,sesh3,sesh4);
		//END
		tableView.setItems(l);
		FilteredList<GameSession> data=new FilteredList<>(l);
		
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				data.setPredicate(sesh -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (sesh.getKey().getLevelName().toLowerCase().contains(lowerCaseFilter))
                    return true; // Filter matches first name.
                return false; // Does not match.
            });
        });
		 SortedList<GameSession> sortedData = new SortedList<>(data);
		 sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		 tableView.setItems(sortedData);
	}
}
