package view;

import java.util.Date;

import commons.GameSession;
import commons.GameSessionKey;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class PlayerDetailsController {

	String playerName;
	@FXML
	TableView<GameSession> tableView;
	
	public PlayerDetailsController(String playerName) {
		this.playerName=playerName;
	}
	
	
	@SuppressWarnings("unchecked")
	public void init()
	{
		//TODO: SEARCH PLAYER BY NAME IN SOKOBANDB AND GET ALL GAME SESSIONS
		//STUB FOR GAME SESSIONS FROM SQL
		GameSession sesh1=new GameSession(1, 1,new Date());
		sesh1.setKey(new GameSessionKey("LEVEL 23", playerName));
		
		GameSession sesh2=new GameSession(3, 2,new Date());
		sesh2.setKey(new GameSessionKey("LEVEL 1", playerName));
		
		GameSession sesh3=new GameSession(2, 3,new Date());
		sesh3.setKey(new GameSessionKey("LEVEL 576", playerName));
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
		levelCol.setPrefWidth(140);
		dateCol.setPrefWidth(180);
		stepCol.setPrefWidth(113);
		timeCol.setPrefWidth(123);
		ObservableList<TableColumn<GameSession, String>>  list=FXCollections.observableArrayList();
		list.addAll(levelCol,dateCol,stepCol,timeCol);
		tableView.getColumns().clear();
		tableView.getColumns().addAll(list);
		
		tableView.getItems().addAll(sesh1,sesh2,sesh3);
		
		
		
	}
}
