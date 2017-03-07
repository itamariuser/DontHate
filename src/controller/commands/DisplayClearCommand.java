package controller.commands;

import java.util.HashMap;

import model.Model;
import view.LevelDisplayer;
import view.TextDisplayer;
import view.View;

/**
 * display a level clearly so nothing overlaps and it looks kinda weird
 * (in graphics it draw white spaces before displaying the level)
 * @author itamar sheffer
 */
public class DisplayClearCommand extends Command{
	String displayType;
	static HashMap<String, LevelDisplayer> factoryDisplayer;
	Model modelRef;
	View viewRef;
	
	public DisplayClearCommand(Model m,View v) {
		
		this.modelRef=m;
		this.viewRef=v;
		if(factoryDisplayer==null)
		{
			factoryDisplayer=new HashMap<String, LevelDisplayer>();
		}
		
		this.addFormat("text", new TextDisplayer());
		this.addFormat("graphics2d",this.viewRef.getDisplayer());
	}

	
	private void addFormat(String format, LevelDisplayer displayer2) {
		factoryDisplayer.put(format, displayer2);
	}


	public void execute() throws Exception {
		this.addFormat("text", new TextDisplayer());
		this.addFormat("graphics2d",this.viewRef.getDisplayer());
		this.displayType=params.get(0);
		if(factoryDisplayer.get(displayType)!=null)
		{
			//call displayLevelClear for the type(text or graphics) and send level
			factoryDisplayer.get(displayType.toLowerCase()).displayLevelClear((this.modelRef.getLevel()),null);
		}
		else
		{
			throw new Exception("levelDisplay");
		}
		
	}
}
