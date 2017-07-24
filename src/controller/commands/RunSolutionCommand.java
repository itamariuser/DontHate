package controller.commands;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import commons.ServerCommand;
import controller.general.Controller;
import model.Model;
import model.SokobanModel;
import view.MainWindowController;
import view.View;

public class RunSolutionCommand extends Command {
	private Model modelRef;
	private View viewRef;
	private Controller contRef;

	
	

	public RunSolutionCommand(Model modelRef, View viewRef, Controller contRef) {
		super();
		this.modelRef = modelRef;
		this.viewRef = viewRef;
		this.contRef = contRef;
	}




	@Override
	public void execute() throws Exception {
//		LinkedList<ServerCommand> list=modelRef.getSolution().getCommands();
//		Timer t=new Timer();
//		t.schedule(new TimerTask() {
//			@Override
//			public void run() {
//					ServerCommand command=list.removeFirst();
//					MoveCommand2D moveCommand=new MoveCommand2D(modelRef, viewRef);
//					moveCommand.setDirection(command.getDescription().split(" ")[1]);
//					LinkedList<String> l=new LinkedList<>();
//					l.add(moveCommand.getDirection());
//					moveCommand.setParams(l);
//					contRef.insertCommand(moveCommand);
//			}
//		}, 500);
		
		LinkedList<String> commands=new LinkedList<>();
		for (ServerCommand c : modelRef.getSolution().getCommands()) {
			commands.add(c.getDescription());
		}
		modelRef.runSolution(commands);
		
		Timer t=new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				OneStepCommand comm=new OneStepCommand((SokobanModel) modelRef, (MainWindowController)viewRef);
				contRef.insertCommand(comm);
				DisplayCommand dispComm=new DisplayCommand(modelRef, viewRef);
				LinkedList<String> par=new LinkedList<>();
				par.add("graphics2d");
				dispComm.setParams(par);
				contRef.insertCommand(dispComm);
			}
		}, 500);
	}
}