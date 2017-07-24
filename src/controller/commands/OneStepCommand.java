package controller.commands;

import model.SokobanModel;
import view.MainWindowController;

public class OneStepCommand extends Command {
	private SokobanModel modelRef;
	@SuppressWarnings("unused")
	private MainWindowController viewRef;
	public OneStepCommand(SokobanModel modelRef,MainWindowController viewRef) {
		super();
		this.modelRef = modelRef;
		this.viewRef=viewRef;
	}
	@Override
	public void execute() throws Exception {
		String direction=modelRef.getCurrentSolution().getCommands().getFirst().getDescription();
//		modelRef.move(description.split(" ")[1]);//Get the arguments for the command, e.g: "Move right" -> "right"
		modelRef.getCurrentSolution().getCommands().removeFirst();
		modelRef.move(direction);
	}
	
}
