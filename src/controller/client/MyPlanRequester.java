package controller.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import commons.Level2D;
import commons.ServerPlan;

public class MyPlanRequester implements PlanRequester{
	@Override
	public ServerPlan request(Level2D level,String ip, int port)
	{
		ServerPlan objFromServer = null;
		Socket theServer = null;
		try {
			theServer=new Socket(ip, port);
			ObjectInputStream input = new ObjectInputStream(theServer.getInputStream());
			ObjectOutputStream output = new ObjectOutputStream(theServer.getOutputStream());
			output.writeObject(level);
			objFromServer = (ServerPlan) input.readObject();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
		} finally {
			try {
				theServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return objFromServer;
	}
}
