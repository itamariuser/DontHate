package controller.commands;


import controller.client.MyPlanRequester;
import model.Model;
import view.View;

public class SolveCommand extends Command {
	
	private Model modelRef;
	private View viewRef;
	private MyPlanRequester requester;
	
	
	public SolveCommand(Model modelRef, View viewRef) {
		this.modelRef = modelRef;
		this.viewRef = viewRef;
		requester = new MyPlanRequester();
	}
	public Model getModelRef() {
		return modelRef;
	}
	public void setModelRef(Model modelRef) {
		this.modelRef = modelRef;
	}
	public View getViewRef() {
		return viewRef;
	}
	public void setViewRef(View viewRef) {
		this.viewRef = viewRef;
	}
	
	@Override
	public void execute()
	{
		this.modelRef.setSolution(SolveCommand.this.requester.request(SolveCommand.this.getModelRef().getLevel(), params.get(0), Integer.parseInt(params.get(1))));
		System.out.println(this.modelRef.getSolution());
	}
}
