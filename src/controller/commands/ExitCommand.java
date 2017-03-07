package controller.commands;
/**
 * command for exiting the game
 * @author itamar sheffer
 *
 */
public class ExitCommand extends Command {

	@Override
	public void execute() throws Exception {
		System.out.println("Exiting now...");
		System.exit(0);
	}

}
