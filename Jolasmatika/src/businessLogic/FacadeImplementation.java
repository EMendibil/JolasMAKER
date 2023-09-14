package businessLogic;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import converter.Bihurtzailea;
import converter.BihurtzaileaHTML;
import exceptions.KeyNotFoundException;
import exceptions.WrongLayoutException;
import games.Jolasa;
import games.Labirintoa;

/**
 * An implementation of the business logic.
 */
public class FacadeImplementation implements Facade {

	private Jolasa game;
	
	
    /**Sets the layout of the labyrinth
     * @param mapa the matrix of the layout of the labyrinth
     * @throws WrongLayoutException if the start and finish are not connected
     */
    public void labirintoaSortu(int[][] mapa) throws WrongLayoutException {
    	if(labirintoOna(mapa)) this.game = new Labirintoa(mapa);
    	else throw new WrongLayoutException();
    }

    /**Checks if the labyrinth layout is correct
    * @param mapa the matrix of the layout of the labyrinth
    * @returns a boolean that is true if the layout is correct, and false otherwise
    */
    private boolean labirintoOna(int[][] mapa) {
    	int[] hasiera = new int[2];

    	ArrayList<int[]> tartekoak = new ArrayList<int[]>();
    	
    	int[] bukaera = new int[2];
    	for (int i = 0; i < 7; i++) {
    		for (int j = 0; j < 7; j++) {
    			if(mapa[i][j] == 1) {int[] lag = {i, j}; tartekoak.add(lag);}
    			if(mapa[i][j] == 2) {hasiera[0] = i; hasiera[1] = j;}
    			if(mapa[i][j] == 3) {bukaera[0] = i; bukaera[1] = j;}
    		}
    	}
    	return bideOna(hasiera, tartekoak, bukaera);
    }

    /**Helps checks if the labyrinth layout is correct
     * @param hasiera. the coordinates of the start of the labyrinth
     * @param tartekoak. the coordinates of the path of the labyrinth
     * @param bukaera. the coordinates of the end of the labyrinth
     * @returns a boolean that is true if the layout is correct, and false otherwise
     */
	private boolean bideOna(int[] hasiera, ArrayList<int[]> tartekoak, int[] bukaera) {
		boolean zuzena = false;
		if(ondoan(hasiera, bukaera)) zuzena = true;
		else {
			int i = 0;
			while(!zuzena && i < tartekoak.size()) {
				int[] tarteko = tartekoak.get(i);
				if(ondoan(hasiera, tarteko)) {tartekoak.remove(i); zuzena = bideOna(tarteko, tartekoak, bukaera);}
				i++;
			}
		}
		
		return zuzena;
	}

	/**Checks if two coordinates are next to each other
     * @param hasiera. the coordinates of the first coordinate
     * @param bukaera. the coordinates of the second coordinate
     * @returns a boolean that is true if the coordinates are next to each other, and false otherwise
     */
	private boolean ondoan(int[] hasiera, int[] bukaera) {
		boolean emaitza = false;
		if (hasiera[0] == bukaera[0] && hasiera[1]-1 <= bukaera[1] && bukaera[1] <= hasiera[1]+1) emaitza = true;
		if (hasiera[1] == bukaera[1] && hasiera[0]-1 <= bukaera[0] && bukaera[0] <= hasiera[0]+1) emaitza = true;
		return emaitza;
	}

	/**Sets which blocks can be used and the amount of times each can be used in the game.
     * @param blokeak. A list of the blocks the user has chosen
     * @param kopuruak. The maximum amount of times each block can be used.
     */
    public void blokeakTxertatu(List<String> blokeak, int kopurua) {
    	this.game.setOnartutakoBlokeak(blokeak);
    	this.game.setBlokeKopurua(kopurua);
    }

    /**
     * Converts the labyrinth and selected blocks into a playable game
     * @param jokoIzena. The name the user has chosen for the game
     * @param hasierakoPista. The starting hint for the game
     * @param jolasMota. The type of game (maze) that must be created
     * @throws KeyNotFoundException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public void bihurtu(String jokoIzena, String hasierakoPista, String jolasMota) throws KeyNotFoundException, InterruptedException, IOException {
     Bihurtzailea converter;
    	game.setHasierakoPista(hasierakoPista);
    	converter = new BihurtzaileaHTML();
		converter.bihurtu(game, jokoIzena, jolasMota);

    }

    /**Changes the designs of the maze and the player character
     * @param diseinuak. A list of the new designs. 0 is the background, 1 is the path and 2 is the character
     */
    public void diseinuakAldatu(ImageIcon[] diseinuak) {
    	((Labirintoa) this.game).setFondoa(diseinuak[0]);
    	((Labirintoa) this.game).setBideaIrudia(diseinuak[1]);
    	((Labirintoa) this.game).setJokalariIrudia(diseinuak[2]);
    }

}