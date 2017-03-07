package view;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

/**
 * For storing user defined keys
 * @author itamar sheffer
 *
 */
public class KeyCodeStorage extends BorderPane {
	@FXML
	private StringProperty upKey;
	private StringProperty downKey;
	private StringProperty rightKey;
	private StringProperty leftKey;
	private StringProperty specialMoveKey;
	private StringProperty restartLevelKey;
	
	public String getRestartLevelKey() {
		return restartLevelKey.get();
	}
	public void setRestartLevelKey(String restartLevelKey) {
		this.restartLevelKey.set(restartLevelKey);
	}
	public String getUpKey() {
		return upKey.get();
	}
	public void setUpKey(String upKey) {
		this.upKey.set(upKey);
	}
	public String getDownKey() {
		return downKey.get();
	}
	public void setDownKey(String downKey) {
		this.downKey.set(downKey);
	}
	public String getRightKey() {
		return rightKey.get();
	}
	public void setRightKey(String rightKey) {
		this.rightKey.set(rightKey);
	}
	public String getLeftKey() {
		return leftKey.get();
	}
	public void setLeftKey(String leftKey) {
		this.leftKey.set(leftKey);
	}
	public String getSpecialMoveKey() {
		return specialMoveKey.get();
	}
	public void setSpecialMoveKey(String specialMoveKey) {
		this.specialMoveKey.set(specialMoveKey);
	}

	public KeyCodeStorage() {
		this.downKey=new SimpleStringProperty();
		this.upKey=new SimpleStringProperty();
		this.rightKey=new SimpleStringProperty();
		this.leftKey=new SimpleStringProperty();
		this.specialMoveKey=new SimpleStringProperty();
		this.restartLevelKey=new SimpleStringProperty();
	}
	
	
	
}
