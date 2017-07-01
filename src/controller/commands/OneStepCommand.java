package controller.commands;

import model.SokobanModel;
import view.Graphics2DDisplayer;
import view.MainWindowController;

public class OneStepCommand extends Command {
	private SokobanModel modelRef;
	private MainWindowController viewRef;
	public OneStepCommand(SokobanModel modelRef,MainWindowController viewRef) {
		super();
		this.modelRef = modelRef;
		this.viewRef=viewRef;
	}
	@Override
	public void execute() throws Exception {
		String description=modelRef.getCurrentSolution().getCommands().getLast().getDescription();
		modelRef.move(description.split(" ")[1]);//Get the arguments for the command, e.g: "Move right" -> "right"
		modelRef.getCurrentSolution().getCommands().removeLast();
	}
	
}
