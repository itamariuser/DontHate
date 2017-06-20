package view;
	
import controller.general.SokobanController;
import controller.server.MyClientHandler;
import controller.server.ServerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SokobanModel;

/**
 * Responsible for initializing the MVC pattern and starting the game
 * @author itamar sheffer
 *
 */
public class Main extends Application {
	static String arg[];
	static boolean isServer;
	
	@Override
	public void start(Stage primaryStage) {//start the JavaFX window
		
		
		try {
			primaryStage.setTitle("Sokoban");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane)loader.load();
			MainWindowController mainView = loader.getController();
			mainView.init();
			Scene scene = new Scene(root,1600,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			init(mainView);
			if(isServer)
			{
				if(arg[0].equals("-server")) { initServer(mainView); } else { ; }
			}
			primaryStage.show();		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initServer(MainWindowController view) throws Exception//link MVC and start the server
	{
		SokobanModel model = new SokobanModel();
		MyClientHandler ch = new MyClientHandler();
		ServerController controller = new ServerController(model, view, Integer.parseInt(arg[1]), ch);
		model.addObserver(controller);
		view.addObserver(controller);
		ch.addObserver(controller);
		controller.startServer();
		view.start();
	}
	
	private void init(MainWindowController view) throws Exception {//link MVC
		SokobanModel model = new SokobanModel();
		SokobanController controller = new SokobanController(model, view);
		
		model.addObserver(controller);
		view.addObserver(controller);
		view.start();
	}
	
	public static void main(String[] args) {//receive user input before starting
		if(args.length > 0)
		{
			isServer = true;
			arg = args;
		}
		launch(args);
	}
}
