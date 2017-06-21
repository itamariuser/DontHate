package view;

import java.util.ArrayList;
import java.util.Date;

import db.GameSession;
import db.GameSessionKey;
import db.SokobanDBManager;
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
	@FXML
	TableView<GameSession> tableView;
	
	@FXML
	TextField searchField;
	
	public PlayerDetailsController(String playerName) {
		this.playerName=playerName;
	}
	
	
	@SuppressWarnings("unchecked")
	public void init()
	{
		//TODO: SEARCH PLAYER BY NAME IN SOKOBANDB AND GET ALL GAME SESSIONS
		//STUB FOR GAME SESSIONS FROM SQL
	/*	GameSession sesh1=new GameSession(1, 1,new Date());
		sesh1.setKey(new GameSessionKey("LEVEL 23", playerName));
		
		GameSession sesh2=new GameSession(3, 2,new Date());
		sesh2.setKey(new GameSessionKey("LEVEL 1", playerName));
		
		GameSession sesh3=new GameSession(2, 3,new Date());
		sesh3.setKey(new GameSessionKey("LEVEL 576", playerName));*/
		//END OF STUB
		
		
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
		
		
		SokobanDBManager dbm=SokobanDBManager.getInstance();
		ArrayList<GameSession> arr=dbm.getGameSessionsWithPlayerName(playerName);
		
		//********************* FILTER BY SEARCH FIELD****************
		ObservableList<GameSession> l=FXCollections.observableArrayList();
		l.addAll(arr);
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
