package controller.commands;
import model.Model;
import view.View;


/**
 * command to Load a level in a desired format
 * @author itamar sheffer
 *
 */
public class LoadLevelCommand extends Command {
	
	Model modelRef;
	View viewRef;
	
	public LoadLevelCommand(Model modelRef,View viewRef) throws Exception {
		this.modelRef = modelRef;
		this.viewRef=viewRef;
	}
	
	public LoadLevelCommand() {}
	

	public Model getModelRef() {
		return modelRef;
	}

	public void setModelRef(Model modelRef,View viewRef) {
		this.modelRef = modelRef;
		this.viewRef=viewRef;
	}
	
	@Override
	public void execute() throws Exception{//load level in model reference and set view's level
		this.modelRef.loadLevel(this.params.get(0));
		this.viewRef.setLevel(this.modelRef.getLevel());
	}
}

