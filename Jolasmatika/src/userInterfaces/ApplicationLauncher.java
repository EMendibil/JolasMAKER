package userInterfaces;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.UIManager;

import businessLogic.Facade;
import businessLogic.FacadeImplementation;

public class ApplicationLauncher {

	public static void main(String[] args) {
		Locale.setDefault(new Locale("eus"));

		try {
			//TODO cambiar por si luego se añade online.
			Facade appFacadeInterface = new FacadeImplementation();
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			new StartGUI(appFacadeInterface);



		}catch (Exception e) {
			System.out.println("Error in ApplicationLauncher: "+e.toString());
			e.printStackTrace();
		}

	}

}
