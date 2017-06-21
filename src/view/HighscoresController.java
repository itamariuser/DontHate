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
	
	@FXML
	TextField searchField;
	@SuppressWarnings("unchecked")
	public void init() {
		//STUB FOR GAME SESSIONS FROM SQL
		
		//get all sessions where sesh.levelName=this.levelName
		/*Select sesh
		 *From GameSessions
		 *Where sesh.levelName = "+this.levelName;
		 * 
		 */
		
		
		
		//STUB
		
		GameSession sesh1=new GameSession(new GameSessionKey(levelName, "AYLMAO"),1, 1,new Date());
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("AYLMAO");
		
		GameSession sesh2=new GameSession(new GameSessionKey(levelName, "MOSHE"),3, 2,new Date());
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("MOSHE");
		
		GameSession sesh3=new GameSession(new GameSessionKey(levelName, "MOSHE"),2, 3,new Date());
//		sesh1.setLevelName(levelName);
//		sesh1.setPlayerName("DODA");
		//END OF STUB
		
		
		//Configure columns
		TableColumn<GameSession,String> nameCol=new TableColumn<>("Name");
		nameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GameSession,String>, ObservableValue<String>>() {
			
			@Override
			public ObservableValue<String> call(CellDataFeatures<GameSession, String> arg0) {
				return new SimpleStringProperty(arg0.getValue().getKey().getPlayerName());
//				return new SimpleStringProperty(arg0.getValue().getPlayerName());
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
		SokobanDBManager dbm=SokobanDBManager.getInstance();
		ArrayList<GameSession> arr=dbm.getGameSessionsWithLevelName(levelName);//TODO
		
		//Detect click
		tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		        openPlayerWindow(newSelection);
		    };
		});
		
		
		//********************* FILTER BY SEARCH FIELD****************
		ObservableList<GameSession> l=FXCollections.observableArrayList();
		//l.addAll(sesh1,sesh2,sesh3);
		l.addAll(arr);
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
//                if (sesh.getPlayerName().toLowerCase().contains(lowerCaseFilter))
                    return true; // Filter matches first name.
                return false; // Does not match.
            });
        });
		 SortedList<GameSession> sortedData = new SortedList<>(data);
		 sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		 tableView.setItems(sortedData);
	}
	
	private void openPlayerWindow(GameSession sesh)
	{
		Platform.runLater(()->{
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerDetails.fxml"));
				loader.setController(new PlayerDetailsController( sesh.key.getPlayerName()));
//				loader.setController(new PlayerDetailsController( sesh.getPlayerName()));
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
	public void filterList()
	{
		
	}
	
	public void sortTime()
	{
		
	}
}
