package controller.commands;

import controller.client.MyPlanRequester;
import model.SokobanModel;
import view.View;

public class SolveCommand extends Command {
	
	private SokobanModel modelRef;
	private MyPlanRequester requester;
	private View viewRef;
	
	public SolveCommand(SokobanModel modelRef,View viewRef) {
		this.modelRef = modelRef;
		this.viewRef=viewRef;
		requester = new MyPlanRequester();
	}
	public SokobanModel getModelRef() {
		return modelRef;
	}
	public void setModelRef(SokobanModel modelRef) {
		this.modelRef = modelRef;
	}
	
	@Override
	public void execute()
	{
		this.modelRef.setSolution(SolveCommand.this.requester.request(SolveCommand.this.getModelRef().getLevel(), params.get(0), Integer.parseInt(params.get(1))));
		this.modelRef.setCurrentSolution(this.modelRef.getSolution());
		viewRef.solutionReady();
	}
}
