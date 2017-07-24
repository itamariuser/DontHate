package controller.commands;
import model.SokobanModel;
import view.View;


/**
 * command to Load a level in a desired format
 * @author itamar sheffer
 *
 */
public class LoadLevelCommand extends Command {
	
	SokobanModel modelRef;
	View viewRef;
	
	public LoadLevelCommand(SokobanModel modelRef,View viewRef) throws Exception {
		this.modelRef = modelRef;
		this.viewRef=viewRef;
	}
	
	public LoadLevelCommand() {}
	

	public SokobanModel getModelRef() {
		return modelRef;
	}

	public void setModelRef(SokobanModel modelRef,View viewRef) {
		this.modelRef = modelRef;
		this.viewRef=viewRef;
	}
	
	@Override
	public void execute() throws Exception{//load level in model reference and set view's level
		this.modelRef.loadLevel(this.params.getFirst());
		this.modelRef.resetCurrentSolution();
		this.viewRef.setLevel(this.modelRef.getLevel());
		this.modelRef.stop();
	}
}

