package controller.server;

import java.io.InputStream;
import java.io.OutputStream;
/**
 * Responsible for handling client input and sending back output
 * @author itamar sheffer
 *
 */
public interface ClientHandler {
	public void handleClient(InputStream inFromClient, OutputStream outToClient);
	
	public void stopHandler();
}
