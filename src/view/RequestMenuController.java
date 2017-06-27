package view;

import java.util.LinkedList;
import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class RequestMenuController extends Observable{
	
	@FXML
	TextField ipText;
	
	@FXML
	TextField portText;
	
	public RequestMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendRequest()
	{
		LinkedList<String> params = new LinkedList<String>();
		params.add("Solve");
		params.add(ipText.getText());
		params.add(portText.getText());
		this.setChanged();
		this.notifyObservers(params);
	}
}
