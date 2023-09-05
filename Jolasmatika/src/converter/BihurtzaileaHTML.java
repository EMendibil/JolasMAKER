package converter;



import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import exceptions.KeyNotFoundException;
import games.Blokea;
import games.Jolasa;
import games.Labirintoa;

/**
 * 
 */
public class BihurtzaileaHTML extends Bihurtzailea{


    /**
     * Constructor
     * @param jolasa
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
    	int[][] map = ((Labirintoa) jolasa).getMapa();
    	
    	
    	System.out.println("Mapa: ");
    	for (int j = 0; j < 7; j++) {
			for (int i = 0; i < 7; i++) {
				System.out.print(map[i][j] + ", ");
			}
			System.out.println();
    	}
    	System.out.println("Onartutako blokeak: ");
    	ArrayList<Blokea> blokeak = jolasa.getOnartutakoBlokeak();
    	for (Blokea bloke : blokeak) System.out.println(bloke.getMota());
    	
    	System.out.print("Bloke kopuru maximoa: ");
    	if(jolasa.getBlokeKopurua() == 0) System.out.print("Infinito");
    	else System.out.print(jolasa.getBlokeKopurua());
    	
    	
    	String documentsIdazle = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
    	String documents = documentsIdazle.replace('\\', '/').substring(2); //CygWin only accepts regular slashes and substring is used to delete the "C:", so it can be replaced by "c"
    	
    	String desktop = System.getProperty("user.home").substring(2) + "/Desktop";
    	desktop = desktop.replace('\\', '/'); //CygWin only accepts regular slashes
    	
    	System.out.println(desktop);
    	
    	Idazlea idatzi = new IdazleaMaze(documentsIdazle + "\\blockly-games-iruzkinak");
    	idatzi.berridatzi(jolasa);
    	
    	ProcessBuilder eraikitzailea = new ProcessBuilder("c:\\cygwin64\\Cygwin.bat");
		final Process prozesua = eraikitzailea.start();
		
		BufferedWriter idazlea = new BufferedWriter(new OutputStreamWriter(prozesua.getOutputStream()));
		
		agindu(idazlea, "export PATH=$PATH:\"C:/Program Files/Java/jre-11.0.19/bin/\"\n");	
		
		agindu(idazlea, "rm -rf /cygdrive/c/Users/PC/Desktop/" + jokoIzena + "\n");	
		
		agindu(idazlea, "cd /cygdrive/c" + documents + "/blockly-games-iruzkinak\n");
		agindu(idazlea, "make common\n");
		agindu(idazlea, "make " + jolasMota + "\n");
		
		agindu(idazlea, "mkdir -p /cygdrive/c" + desktop + "/" + jokoIzena + "\n");

		agindu(idazlea, "cp appengine/" + jolasMota + ".html /cygdrive/c" + desktop + "/" + jokoIzena + "\n");
		agindu(idazlea, "cp -r appengine/common /cygdrive/c" + desktop + "/" + jokoIzena + "/common\n");
		agindu(idazlea, "cp -r appengine/src /cygdrive/c" + desktop + "/" + jokoIzena + "/src\n");
		agindu(idazlea, "cp -r appengine/third-party /cygdrive/c" + desktop + "/" + jokoIzena + "/third-party\n");
		agindu(idazlea, "cp -r appengine/" + jolasMota + " /cygdrive/c" + desktop + "/" + jokoIzena + "/" + jolasMota + "\n");
		
		agindu(idazlea, "exit\n");

    }
    
    private void agindu(BufferedWriter idazlea, String agindua) throws IOException {
    	idazlea.write(agindua);
    	idazlea.flush();
    }

}