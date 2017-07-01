package controller.commands;

import java.util.concurrent.LinkedBlockingQueue;

import commons.ServerCommand;
import model.Model;

public class RunSolutionCommand extends Command {
	private Model modelRef;

	public RunSolutionCommand(Model modelRef) {
		super();
		this.modelRef = modelRef;
	}

	@Override
	public void execute() throws Exception {
		LinkedBlockingQueue<String> directions = new LinkedBlockingQueue<>();
		for (ServerCommand command : modelRef.getSolution().getCommands()) {
			directions.add(command.getDescription().split(" ")[1]);
		}
		this.modelRef.runSolution(directions);
	}
}
