package controller.commands;

import java.util.ArrayList;

import gameObjects.MainCharacter;
import model.Model;
import view.View;

/**
 * command to move a game object in the 2D version
 * @author itamar sheffer
 *
 */
public class MoveCommand2D extends Command {
	
	String direction;
	MainCharacter ch;
	ArrayList<String> directionsAvailable;
	Model modelRef;
	View viewRef;
	
	public MoveCommand2D() {
		
	}
	
	public MoveCommand2D(Model modelRef,View viewRef) {
		this.modelRef=modelRef;
		this.viewRef=viewRef;
	}
	
	public ArrayList<String> getDirectionsAvailable() {
		return directionsAvailable;
	}

	public void setDirectionsAvailable(ArrayList<String> directionsAvailable) {
		this.directionsAvailable = directionsAvailable;
	}
	
	@Override
	public void execute() throws Exception {	
		this.modelRef.move(this.params.get(0));
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
