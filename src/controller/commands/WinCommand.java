package controller.commands;

import view.View;

public class WinCommand extends Command {
	View viewRef;

	public WinCommand(View viewRef) {
		this.viewRef = viewRef;
	}

	@Override
	public void execute() throws Exception {
		this.viewRef.displayWinMessage();
	}
	
	
	
}
