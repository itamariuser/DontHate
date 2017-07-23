package controller.commands;

import common.Level2D;
import model.Model;
/**
 * command to save a level in a desired format
 * @author itamar sheffer
 *
 */
public class SaveCommand extends Command {
	String levelName;
	Level2D level;
	Model modelRef;
	
	public SaveCommand(Model m) {
		this.modelRef=m;
	}
	
	public SaveCommand(String levelName,Level2D level) {
		this.levelName=levelName;
		this.level=level;
	}
	@Override
	public void execute() throws Exception {
		this.modelRef.saveLevel(this.params.get(0));
	}

}
