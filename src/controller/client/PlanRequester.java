package controller.client;

import common.Level2D;
import commons.ServerPlan;

public interface PlanRequester {
	public ServerPlan request(Level2D level,String ip, int port);
}
