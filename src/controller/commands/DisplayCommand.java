package controller.commands;


/**
 * display the level
 * @author itamar sheffer
 */
import java.util.HashMap;

import model.Model;
import view.LevelDisplayer;
import view.TextDisplayer;
import view.View;

public class DisplayCommand extends Command {
	protected String displayType;
	protected static HashMap<String, LevelDisplayer> factoryDisplayer;
	protected HashMap<String, LevelDisplayer> temp;
	protected Model modelRef;
	protected View viewRef;
	
	public DisplayCommand(Model m,View v) {
		
		this.modelRef=m;
		this.viewRef=v;
		if(factoryDisplayer==null)
		{
			factoryDisplayer=new HashMap<String, LevelDisplayer>();
		}
		
		this.addFormat("text", new TextDisplayer());
		this.addFormat("graphics2d",this.viewRef.getDisplayer());
	}

	
	protected void addFormat(String format, LevelDisplayer displayer2) {
		factoryDisplayer.put(format, displayer2);
	}

	
	public void execute() throws Exception {
		this.displayType=params.get(0);
		this.temp=factoryDisplayer;
		if(factoryDisplayer.get(displayType)!=null)
		{
			//call displayLeve for the type(text or graphics) and send level
			factoryDisplayer.get(displayType.toLowerCase()).displayLevel(this.modelRef.getLevel(), null);
		}
		else
		{
			throw new Exception("levelDisplay");
		}
		
	}

}
