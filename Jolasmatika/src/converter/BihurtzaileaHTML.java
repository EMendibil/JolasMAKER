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
     * @throws IOException 
     * @throws KeyNotFoundException 
     */
    public void bihurtu(Jolasa jolasa, String jokoIzena, String jolasMota) throws IOException, KeyNotFoundException {

    	System.out.println("Onartutako blokeak: ");
    	ArrayList<String> blokeak = jolasa.getOnartutakoBlokeak();
    	for (String bloke : blokeak) System.out.println(bloke);
    	
    	System.out.print("Bloke kopuru maximoa: ");
    	if(jolasa.getBlokeKopurua() == 0) System.out.print("Infinito");
    	else System.out.print(jolasa.getBlokeKopurua());
    	
    	
    	String documentsIdazle = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    	String documents = documentsIdazle.replace('\\', '/').substring(2); //CygWin only accepts regular slashes and substring is used to delete the "C:", so it can be replaced by "c"
    	
    	String desktop = System.getProperty("user.home").substring(2) + "/Desktop";
    	desktop = desktop.replace('\\', '/'); //CygWin only accepts regular slashes
    	
    	System.out.println(desktop);
    	
    	idazlea = new IdazleaMaze(documentsIdazle + "\\blockly-games-iruzkinak");
    	idazlea.berridatzi(jolasa);
    	
    	ProcessBuilder eraikitzailea = new ProcessBuilder("c:\\cygwin64\\Cygwin.bat");
		final Process prozesua = eraikitzailea.start();
		
		BufferedWriter aginduIdazlea = new BufferedWriter(new OutputStreamWriter(prozesua.getOutputStream()));
		
		agindu(aginduIdazlea, "export PATH=$PATH:\"C:/Program Files/Java/jre-11.0.19/bin/\"\n");	
		
		agindu(aginduIdazlea, "rm -rf /cygdrive/c/Users/PC/Desktop/" + jokoIzena + "\n");	
		
		agindu(aginduIdazlea, "cd /cygdrive/c" + documents + "/blockly-games-iruzkinak\n");
		agindu(aginduIdazlea, "make common\n");
		agindu(aginduIdazlea, "make " + jolasMota + "\n");
		
		agindu(aginduIdazlea, "mkdir -p /cygdrive/c" + desktop + "/" + jokoIzena + "\n");

		agindu(aginduIdazlea, "cp appengine/" + jolasMota + ".html /cygdrive/c" + desktop + "/" + jokoIzena + "\n");
		agindu(aginduIdazlea, "cp -r appengine/common /cygdrive/c" + desktop + "/" + jokoIzena + "/common\n");
		agindu(aginduIdazlea, "cp -r appengine/src /cygdrive/c" + desktop + "/" + jokoIzena + "/src\n");
		agindu(aginduIdazlea, "cp -r appengine/third-party /cygdrive/c" + desktop + "/" + jokoIzena + "/third-party\n");
		agindu(aginduIdazlea, "cp -r appengine/" + jolasMota + " /cygdrive/c" + desktop + "/" + jokoIzena + "/" + jolasMota + "\n");
		
		agindu(aginduIdazlea, "exit\n");

    }
    
    private void agindu(BufferedWriter aginduIdazlea, String agindua) throws IOException {
    	aginduIdazlea.write(agindua);
    	aginduIdazlea.flush();
    }

}