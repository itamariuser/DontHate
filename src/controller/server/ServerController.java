package controller.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.LinkedList;
import java.util.Observable;

import controller.commands.Command;
import controller.general.SokobanController;
import model.SokobanModel;
import view.View;


/**
 * Recieve commands from a remote client, and execute 
 * @author itamar sheffer
 *
 */
public class ServerController extends SokobanController {
	private int port; 
	private ClientHandler ch; 
	private volatile boolean stop;
	public ServerController(SokobanModel model, View view, int port,ClientHandler ch) throws Exception
	{
		super(model ,view);
		this.port=port;
		this.ch=ch;
		stop=false;
		this.start();
	}
	
	public void startServer(){
			new Thread(new Runnable() {
				public void run() {
					try{ runServer(); } catch (Exception e){ }
				}
			}).start();
		}
	
	
	public void stop(){
		stop=true;
	} 
	
	private void runServer() throws Exception
	{ 
		ServerSocket server=new ServerSocket(port); 
		server.setSoTimeout(1000); 
		while(!stop)
		{ 
			try
			{ 
				Socket aClient=server.accept();
				aClient.getOutputStream();
				new Thread(new Runnable() { 
					public void run() { 
						try { 
							System.out.println("Server is running");
							ch.handleClient(aClient.getInputStream(), aClient.getOutputStream()); 
							System.out.println("Client disconnected");
							aClient.getInputStream().close();
							aClient.getOutputStream().close(); 
							aClient.close(); 
							} catch (IOException e) {} 
						} 
					}).start(); 
			}catch(SocketTimeoutException e) {} 
		} server.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		LinkedList<String> params = (LinkedList<String>) arg;
		if(params.getFirst()==null)
		{
			return;
		}
		String commandKey = params.removeFirst().toLowerCase();
		
		System.out.println(commandKey);
		Command c = commands.get(commandKey);
		if(commandKey.equals("exit"))
		{
			ch.stopHandler();
			this.stop();
			return;
		}
		else if (c != null) {
			c.setParams(params);
			this.insertCommand(c);
			return;
		}
		this.viewRef.displayMessage("Command not found");		
	}
}
