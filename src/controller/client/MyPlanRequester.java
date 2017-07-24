package controller.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Level2D;
import commons.ServerPlan;
import controller.server.ServerPlanAdapter;
import solver.SokobanSolver;

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
//RESTORE
//			objFromServer = (ServerPlan) input.readObject();
			//return objFromServer;
		} catch (Exception e) {
		}

//		RESTORE
//		finally {
//			if(output != null)
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}		
//			if (input != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		//DELETE
		SokobanSolver solver = new SokobanSolver();
		
		ServerPlan s=ServerPlanAdapter.convert(solver.solveLevel(level), level.getName());
		return s;
	}
}
