package controller.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Level2D;
import commons.ServerPlan;

public class MyPlanRequester implements PlanRequester{
	@Override
	public ServerPlan request(Level2D level,String ip, int port)
	{
		ServerPlan objFromServer = null;
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
		return objFromServer;
	}
}
