package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import controller.general.SokobanController;
import model.SokobanModel;
import view.CLI;

public class Run {
	public static void main(String[] args) {
		try {
			//Create reader which will read from system.in(user input)
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			//Create writer which will write to system.out(to screen)
			PrintWriter writer = new PrintWriter(System.out);
			
			//define model, controller and view(CLI)
			CLI mainCLI = new CLI(reader, writer, "Exit");
			SokobanModel mainModel=new SokobanModel();
			SokobanController mainController=null;
			
			//link controller to the view and model
			mainController = new SokobanController(mainModel,mainCLI);
			
			//link controller to view and model
			mainModel.addObserver(mainController);
			mainCLI.addObserver(mainController);

			mainCLI.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
