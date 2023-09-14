package businessLogic;



import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import converter.Bihurtzailea;
import exceptions.KeyNotFoundException;
import exceptions.WrongLayoutException;
import games.Jolasa;

/**
 * The interface of the business logic.
 */
public interface Facade {


	
    /**Sets the layout of the labyrinth
     * @param mapa the matrix of the layout of the labyrinth
     * @throws WrongLayoutException if the start and finish are not connected
     */
    public void labirintoaSortu(int[][] mapa) throws WrongLayoutException;

    /**Sets which blocks can be used and the amount of times each can be used in the game.
     * @param blokeak. A list of the blocks the user has chosen
     * @param kopuruak. The maximum amount of times each block can be used.
     */
    public void blokeakTxertatu(ArrayList<String> blokeak, int kopurua);

    /**
     * Converts the labyrinth and selected blocks into a playable game
     * @throws KeyNotFoundException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public void bihurtu(String jokoIzena, String hasierakoPista, String jolasMota) throws KeyNotFoundException, InterruptedException, IOException;

    /**Changes the designs of the maze and the player character
     * @param diseinuak. A list of the new designs.
     */
    public void diseinuakAldatu(ImageIcon[] diseinuak);

}