package view;

import java.util.LinkedList;
import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A class responsible for controlling the solution request window.
 * 
 * @author itama
 */
public class RequestMenuController extends Observable {

	@FXML
	TextField ipText;

	@FXML
	TextField portText;

	public RequestMenuController() {
	}

	public void sendRequest() {
		LinkedList<String> params = new LinkedList<String>();
		params.add("Solve");
		params.add(ipText.getText());
		params.add(portText.getText());
		this.setChanged();
		this.notifyObservers(params);
		Stage stage1 = (Stage) ipText.getScene().getWindow();
		stage1.close();
	}
}
