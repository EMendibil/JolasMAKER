package converter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import exceptions.KeyNotFoundException;
import games.Jolasa;
import games.Labirintoa;

/**
 * The writer used to modify the files of Maze games only.
 */
public class IdazleaMaze extends Idazlea{

    /**
     * Constructor
     */
    public IdazleaMaze(String blocklyPath) {
      super(blocklyPath);
    }
    
    /**
     * Modifies files that the game "maze" uses in blockly-games-iruzkinak to create new games.
     * @param jolasa. The game that is going to be created
     * @throws KeyNotFoundException 
     * @throws IOException 
     */
    @Override
    public void berridatzi(Jolasa jolasa, String jolasMota) throws KeyNotFoundException, IOException {
    	super.berridatzi(jolasa, jolasMota);
    	
    	//Set the custom starting hint in the created name
    	Path bidea = Paths.get(blocklyPath + "\\messages.json");
    	List<String> textua = Files.readAllLines(bidea, StandardCharsets.UTF_8);    	
    	String pista = "    \"msg\": \"" + jolasa.getHasierakoPista() + "\"";
    	
    	textua = aldatu(textua, "    \"desc\": \"callout - Custom hint for Maze game.\",",  pista);
    	Files.write(bidea, textua, StandardCharsets.UTF_8);
    	
    	//Set the layout of the created maze and maximum block amount.
    	bidea = Paths.get(blocklyPath + "\\appengine\\maze\\src\\main.js");
    	textua = Files.readAllLines(bidea, StandardCharsets.UTF_8);
    	int[][] map = ((Labirintoa) jolasa).getMapa();
    	
    	String mapLayout = "[";
    	for (int j = 0; j < 7; j++) {
    		mapLayout = mapLayout + "[";
			for (int i = 0; i < 7; i++) {
				mapLayout = mapLayout + map[i][j] + ", ";
			}
			mapLayout = mapLayout.substring(0, mapLayout.length()-2) + "], ";
    	}
    	mapLayout = mapLayout.substring(0, mapLayout.length()-2) + "]";
    	
    	int blokeKop = jolasa.getBlokeKopurua();
    	if(blokeKop >= 0) textua = aldatu(textua, "//Maximum Blocks", "[" + blokeKop + "]"); //Overwrite maximum block amount
    	else textua = aldatu(textua, "//Maximum Blocks", "[Infinity]"); //Overwrite maximum block amount by infinity
    	textua = aldatu(textua, "// Level 1.", mapLayout); //Overwrite maze layout
    	Files.write(bidea, textua, StandardCharsets.UTF_8);
    	

    }
    

	/**
	 * Method to give blocks the correct format to insert in file.
	 * @param bloke. Identifier of a Blockly block
	 * @return the block with the correct format to insert in "html.js"
	 */
    @Override
	protected String blokeFormatu(String bloke) {
		if (bloke.equals("maze_turn")) {
			String hasi = "<block type=\"maze_turn\"><field name=\"DIR\">";
			String bukatu = "</field></block>";
			return hasi + "turnLeft" + bukatu + hasi + "turnRight" + bukatu;
		}
		else return super.blokeFormatu(bloke);
	}
    
}
