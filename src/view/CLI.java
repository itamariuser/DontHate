package view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;


/**
 * Implementation of View, which uses a command line to receive user input and display levels
 * @author itamar sheffer
 *
 */
public class CLI extends View {
	
	private BufferedReader reader;
	private PrintWriter writer;
	private String exitString;
	
	public CLI(BufferedReader reader, PrintWriter writer, String exitString) {
		this.reader = reader;
		this.writer = writer;
		this.exitString = exitString;
	}
	
	@Override
	public void displayMessage(String msg) {
		System.out.println(msg);
		
	}
	@Override
	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String commandLine = "";
				writer.print("Welcome to Sokoban demo, by Itamar Sheffer and Matan Bachar.\n");
				writer.print("Load a level using 'Load <Level Name>' , e.g: load level1.txt\n");
				writer.print("Display a level using 'Display <display method>', e.g: display text\n");
				writer.print("Move: 'Move <up,down,left,right>'\n");
				writer.print("Save a level using 'Save <destination name>', e.g: save myFile\n");
				writer.print("Exit using Exit\n");
				writer.print("Type 'Help' to display help\n" );
				writer.print("Enter command:\n");
				do {
					writer.flush();
					try {
						commandLine = reader.readLine();
						//split command to its arguments
						String[] arr = commandLine.split(" ");
						LinkedList<String> params = new LinkedList<String>();
						
						//create a list with the arguments
						for (String param : arr) {
							params.add(param);
						}
						
						//alert the observers (in this case, the controller)
						setChanged();
						notifyObservers(params);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				} while (!commandLine.equals(exitString));				
			}
		}).start();	
		
	}

	@Override
	public void displayWinMessage() {
		System.out.println("CONGRATULATIONS!\n You beat this level!");
	}


	@Override
	public void solutionReady() {
		
	}
}
