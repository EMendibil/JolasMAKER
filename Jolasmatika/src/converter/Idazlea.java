package converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JFileChooser;

import exceptions.KeyNotFoundException;
import games.Jolasa;

/**
 * The writer used to modify the files of games.
 */
public abstract class Idazlea {
	
    protected String blocklyPath;

	/**
     * Constructor
     * @param blocklyPath. The path to the folder "blockly-games-iruzkinak".
     */
    protected Idazlea(String blocklyPath) {
    	this.blocklyPath = blocklyPath;
    }
    
    /**
     * Modifies files that all games share in blockly-games-iruzkinak to create new games.
     * @param jolasa 
     * @param jolasa. The game that is going to be created
     * @throws KeyNotFoundException 
     * @throws IOException 
     */
    public void berridatzi(Jolasa jolasa, String jolasMota) throws KeyNotFoundException, IOException{
    	
    	//Set the default language of the game as the same as the language this app is currently using
    	Path bidea = Paths.get(blocklyPath + "\\appengine\\common\\boot.js");
    	List<String> textua = Files.readAllLines(bidea, StandardCharsets.UTF_8);
    	
    	textua = aldatu(textua, "// Fourth choice: Custom.", "lang = '" + Locale.getDefault() + "'");
    	Files.write(bidea, textua, StandardCharsets.UTF_8);
    	
    	//Set the allowed blocks of the created game
    	bidea = Paths.get(blocklyPath + "\\appengine\\" + jolasMota + "\\src\\html.js");
    	textua = Files.readAllLines(bidea, StandardCharsets.UTF_8); 
    	
    	textua = aldatuBlokeak(textua, jolasa.getOnartutakoBlokeak());
    	Files.write(bidea, textua, StandardCharsets.UTF_8);
    }
    
    /**
     * Finds a String in the specified file and replaces the next line with the specified String.
     * @param fitxategiTextu. The old text of the file.
     * @param topatu. The String that must be found.
     * @param txertatu. The String that must be inserted in the next line.
     * @return The modified text of the file
     * @throws KeyNotFoundException if the String is not found in the file.
     */
    protected List<String> aldatu(List<String> fitxategiTextu, String topatu, String txertatu) throws KeyNotFoundException{
    	int kokapena = fitxategiTextu.indexOf(topatu);
    	if(kokapena != -1) {
    		fitxategiTextu.set(kokapena+1, txertatu);
        	return fitxategiTextu;
    	}
    	else throw new KeyNotFoundException();
    }
    
    /**
     * Substitutes the old available blocks with new ones.
     * @param fitxategiTextu. The old text of the file.
     * @param txertatu. The ArrayList<String> that contains the new available blocks.
     * @return The modified text of the file
     * @throws KeyNotFoundException if the String is not found in the file.
     */
    protected List<String> aldatuBlokeak(List<String> fitxategiTextu, ArrayList<String> txertatu) throws KeyNotFoundException{
    	int kokapena = fitxategiTextu.indexOf("//Accepted Blocks") + 1;
    	if(kokapena != -1) {
    		for(String bloke : txertatu) {
    			kokapena++;
    			fitxategiTextu.set(kokapena, blokeFormatu(bloke));
    		}
    		
    		int amaituKokapena = fitxategiTextu.indexOf("//Accepted Blocks End") - 1;
    		
    		for(int i = kokapena+1; i<amaituKokapena; i++) {
    			fitxategiTextu.set(i, "");
    		}
        	return fitxategiTextu;
    	}
    	else throw new KeyNotFoundException();
    }

	/**
	 * Method to give blocks the correct format to insert in file.
	 * @param bloke. Identifier of a Blockly block
	 * @return the block with the correct format to insert in "html.js"
	 */
	protected String blokeFormatu(String bloke) {
		return "<block type=\"" + bloke + "\"></block>";
	}
}
