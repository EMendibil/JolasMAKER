package converter;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import exceptions.KeyNotFoundException;
import games.Jolasa;

/**
 * This class is used to create executable games in .html format
 */
public class BihurtzaileaHTML extends Bihurtzailea{


    /**
     * Constructor
     */
    public BihurtzaileaHTML() {
        super();
    }

    /**
     * Converts game into playable HTML file.
     * @param jolasa. The class containing the information about the game.
     * @param jokoIzena. The name the user has chosen for the new game.
     * @param jolasMota. The type of game that will be created ("maze", for example).
     * @throws IOException 
     * @throws KeyNotFoundException 
     * @throws InterruptedException 
     */
    public void bihurtu(Jolasa jolasa, String jokoIzena, String jolasMota) throws IOException, KeyNotFoundException, InterruptedException {
    	
    	String documentsIdazle = System.getProperty("user.home") + "/Documents";
    	String documents = documentsIdazle;
    	
    	String desktop = System.getProperty("user.home") + "/Desktop";
    	
    	String starter;
    	
    	
    	idazlea = new IdazleaMaze(documentsIdazle + "\\blockly-games-iruzkinak");
    	idazlea.berridatzi(jolasa, jolasMota);
    	
    	ProcessBuilder eraikitzailea;
    	
    	if (System.getProperty("os.name").contains("Windows")) {
    		eraikitzailea= new ProcessBuilder("c:\\cygwin64\\Cygwin.bat"); //Opens Cygwin
    		
    		starter = "/cygdrive/c"; //To use Cygwin instead of the command line
    		documents = documentsIdazle.replace('\\', '/').substring(2); //CygWin only accepts regular slashes and substring is used to delete the "C:", so it can be replaced by "c"
        	desktop = desktop.replace('\\', '/').substring(2); //CygWin only accepts regular slashes
    	}
    	else {
    		eraikitzailea = new ProcessBuilder("/bin/bash"); //Uses the command line
    		
    		starter = "";
    	}
    	
		final Process prozesua = eraikitzailea.start();
		
		BufferedWriter aginduIdazlea = new BufferedWriter(new OutputStreamWriter(prozesua.getOutputStream()));
		
		agindu(aginduIdazlea, "export PATH=$PATH:\"C:/Program Files/Java/jre-11.0.19/bin/\"\n");	
		
		agindu(aginduIdazlea, "rm -rf " + starter + desktop + "/" + jokoIzena + "\n");	
		
		agindu(aginduIdazlea, "cd " + starter + documents + "/blockly-games-iruzkinak\n");
		agindu(aginduIdazlea, "make common\n");
		agindu(aginduIdazlea, "make " + jolasMota + "\n");
		
		agindu(aginduIdazlea, "mkdir -p " + starter + desktop + "/" + jokoIzena + "\n");

		agindu(aginduIdazlea, "cp appengine/" + jolasMota + ".html " + starter + desktop + "/" + jokoIzena + "\n");
		agindu(aginduIdazlea, "cp -r appengine/common " + starter + desktop + "/" + jokoIzena + "/common\n");
		agindu(aginduIdazlea, "cp -r appengine/src " + starter + desktop + "/" + jokoIzena + "/src\n");
		agindu(aginduIdazlea, "cp -r appengine/third-party " + starter + desktop + "/" + jokoIzena + "/third-party\n");
		agindu(aginduIdazlea, "cp -r appengine/" + jolasMota + " " + starter + desktop + "/" + jokoIzena + "/" + jolasMota + "\n");
		
		agindu(aginduIdazlea, "exit\n");
		prozesua.waitFor();
		
    }
    
    private void agindu(BufferedWriter aginduIdazlea, String agindua) throws IOException {
    	aginduIdazlea.write(agindua);
    	aginduIdazlea.flush();
    }

}