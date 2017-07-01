package controller.general;

import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import controller.commands.Command;
import model.Model;
import view.View;

/**
 * the link between view and model, responsible for receiving info from both and deciding what to do next
 * @author itamar sheffer
 *
 */
public abstract class Controller implements Observer{
	protected Model modelRef;
	protected View viewRef;
	
	
	
	public final View getViewRef() {
		return viewRef;
	}

	public final Model getModelRef() {
		return modelRef;
	}

	private BlockingQueue<Command> queue;
	private boolean stop = false;
	
	public Controller() {
		queue = new ArrayBlockingQueue<Command>(10);
	}
	
	public void insertCommand(Command cmd) {
		try {
			queue.put(cmd);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	public void start() {
		Thread thread = new Thread(new Runnable() {
		@Override
		public void run() {
			while (!stop) {
				try {
					Command cmd = queue.poll(1, TimeUnit.SECONDS);
					if (cmd != null)
					{
						cmd.execute();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		});
		thread.start();
	}
	
	public void stop() {
		this.stop = true;
	}
}
