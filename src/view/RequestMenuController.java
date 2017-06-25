package view;

import java.util.LinkedList;
import java.util.Observable;

import commons.Level2D;
import commons.ServerPlan;
import controller.client.PlanRequester;

public class RequestMenuController extends Observable{
	
	public RequestMenuController() {
		// TODO Auto-generated constructor stub
	}
	
	public void sendRequest()
	{
		LinkedList<String> params = new LinkedList<String>();
		params.add("Solve");
		params.add("10.10.247.156");
		params.add("2828");
		this.setChanged();
		this.notifyObservers(params);
	}
}
