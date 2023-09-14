package userinterfaces.commons;

import java.util.Locale;

import javax.swing.UIManager;

import businesslogic.Facade;
import businesslogic.FacadeImplementation;

/**
 * The launcher for the application.
 */
public class ApplicationLauncher {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Locale.setDefault(new Locale("eu"));

		try {
			Facade appFacadeInterface = new FacadeImplementation();
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			new StartGUI(appFacadeInterface);



		}catch (Exception e) {
			e.printStackTrace();
		}

	}

}
