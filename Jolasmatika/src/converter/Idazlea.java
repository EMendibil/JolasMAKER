package converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;

import javax.swing.JFileChooser;

import exceptions.KeyNotFoundException;
import games.Jolasa;

public abstract class Idazlea {
	
    protected String blocklyPath;

	/**
     * Constructor
     */
    protected Idazlea(String blocklyPath) {
    	this.blocklyPath = blocklyPath;
    }
    
    /**
     * Modifies files that all games share in blockly-games-iruzkinak to create new games.
     * @param jolasa. The game that is going to be created
     * @throws KeyNotFoundException 
     * @throws IOException 
     */
    public void berridatzi(Jolasa jolasa) throws KeyNotFoundException, IOException{
    	
    	//Set the default language of the game as the same as the language this app is currently using
    	Path bidea = Paths.get(blocklyPath + "\\appengine\\common\\boot.js");
    	List<String> textua = Files.readAllLines(bidea, StandardCharsets.UTF_8);
    	
    	textua = aldatu(textua, "// Fourth choice: Custom.", "lang = '" + Locale.getDefault() + "'");
    	Files.write(bidea, textua, StandardCharsets.UTF_8);
    	
    }
    
    /**
     * Finds a String in the specified file and replaces the next line with the specified String.
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
}
