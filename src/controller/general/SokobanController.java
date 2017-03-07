package controller.general;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;

import controller.commands.Command;
import controller.commands.DisplayClearCommand;
import controller.commands.DisplayCommand;
import controller.commands.DoNothingCommand;
import controller.commands.HelpCommand;
import controller.commands.LoadLevelCommand;
import controller.commands.MoveCommand2D;
import controller.commands.SaveCommand;
import model.Model;
import view.View;

/**
 * My version of a controller, using command line to receive input from user
 * @author itamar sheffer
 *
 */
public class SokobanController extends Controller {
	protected Map<String, Command> commands;
	
	public SokobanController() {
		
	}
	
	public SokobanController(Model model, View view) throws Exception {
		this.modelRef = model;
		this.viewRef = view;
		initCommands();
		this.start();
	}

	protected void initCommands() throws Exception {
		commands = new HashMap<String, Command>();
		commands.put("move", new MoveCommand2D(this.modelRef,this.viewRef));//
		commands.put("load", new LoadLevelCommand(this.modelRef,this.viewRef));
		commands.put("display", new DisplayCommand(this.modelRef, this.viewRef));
		commands.put("displayclear", new DisplayClearCommand(this.modelRef, this.viewRef));
		commands.put("save", new SaveCommand(this.modelRef));
		commands.put("donothing", new DoNothingCommand());
		commands.put("help", new HelpCommand());
	}

	
	@Override
	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		LinkedList<String> params = (LinkedList<String>) arg;
		if(params.getFirst()==null)
		{
			return;
		}
		String commandKey = params.removeFirst().toLowerCase();
		System.out.println(commandKey);
		Command c = commands.get(commandKey);
		if(commandKey.equals("exit"))
		{
			this.stop();
			return;
		}
		else if (c != null) {
			
			c.setParams(params);
			this.insertCommand(c);
			return;
		}
		this.viewRef.displayMessage("Command not found");		
	}
	
	
}
