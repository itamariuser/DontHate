package commons;

import java.io.Serializable;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@SuppressWarnings("serial")
public class ServerPlan implements Serializable {
	private LinkedList<ServerCommand> commands;
	private String levelName;
	public ServerPlan() {
		commands = new LinkedList<>();
	}

	

	public ServerPlan(LinkedList<ServerCommand> commands) {
		super();
		this.commands = commands;
	}

	public LinkedList<ServerCommand> getCommands() {
		return commands;
	}

	public void setCommands(LinkedList<ServerCommand> commands) {
		this.commands = commands;
	}
	
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	@Override
	public boolean equals(Object obj) {
		boolean eq = true;
		if (obj instanceof ServerPlan) {
			for (ServerCommand c : ((ServerPlan) obj).commands) {
				if (this.commands.contains(c))
					eq = false;
			}
		}
		return eq;
	}

	

}
