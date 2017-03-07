package controller.commands;

/**
 * command for displaying help about the game
 * @author itamar sheffer
 *
 */
public class HelpCommand extends Command {
	@Override
	public void execute() throws Exception{
		System.out.print("Load a level using 'Load <Level Name>' , e.g: load level1.txt\n");
		System.out.print("Display a level using 'Display <display method>', e.g: display text\n");
		System.out.print("Move: 'Move <up,down,left,right>'\n");
		System.out.print("Save a level using 'Save <destination name>', e.g: save myFile\n");
		System.out.print("Exit using Exit\n");
		
		return;
	}
	
}
