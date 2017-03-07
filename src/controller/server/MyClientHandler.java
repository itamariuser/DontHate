package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;


/**
 * Recieve input from client and send to server
 * @author itamar sheffer
 *
 */
public class MyClientHandler extends Observable implements ClientHandler  {
	
	boolean stop = false;
	
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) {
		Scanner input = new Scanner(inFromClient);
		PrintStream output = new PrintStream(outToClient);
		String text = "";
		output.println("Connection completed");
		while(!stop)
		{
			LinkedList<String> params = new LinkedList<String>();
			try {
				text = input.nextLine();
				System.out.println(text);
				String[] arr = text.split(" ");
				if(arr[3].toLowerCase() == "exit")
				{
					stop = true;
					output.println("Exiting...");
				}
				else	
				{
					output.println("");
					int i = 0;
					for (String param : arr) {
						if(i > 2)
						{
							params.add(param);
						}
						i++;
					}
					setChanged();
					notifyObservers(params);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally
			{
				input.close();
				output.close();
			}

		}
	}
	public void stopHandler()
	{
		this.stop = true;
	}
}
