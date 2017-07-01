package controller.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

import commons.Level2D;
import commons.ServerCommand;
import commons.ServerPlan;

public class MyPlanRequester implements PlanRequester{
	@Override
	public ServerPlan request(Level2D level,String ip, int port)
	{
		/*ServerPlan objFromServer = null;
		Socket theServer = null;
		ObjectOutputStream output = null;
		ObjectInputStream input = null;
		try {
			theServer=new Socket(ip, port);
			System.out.println("Connected to the server");
			output = new ObjectOutputStream(theServer.getOutputStream());
			input = new ObjectInputStream(theServer.getInputStream());
			output.writeObject(level);
			output.flush();
			objFromServer = (ServerPlan) input.readObject();
			System.out.println(objFromServer);		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			if(output != null)
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}		
			if (input != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//TODO: make stub for a simple plan
		return objFromServer;*/
		ServerPlan plan=new ServerPlan();
		LinkedList<ServerCommand> commands=new LinkedList<>();
		commands.add(new ServerCommand("Move right"));
		commands.add(new ServerCommand("Move right"));
		plan.setCommands(commands);
		return plan;
	}
}
