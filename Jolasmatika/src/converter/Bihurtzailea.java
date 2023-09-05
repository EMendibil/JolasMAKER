package converter;



import java.io.IOException;

import exceptions.KeyNotFoundException;
import games.Jolasa;

/**
 * 
 */
public abstract class Bihurtzailea {

	private Jolasa jolasa;
	
    /**
     * Constructor
     * @param jolasa
     */
    public Bihurtzailea() 
    {
    	this.jolasa = jolasa;
    }

    /**
     * Converts game into playable file.
     * @throws IOException 
     * @throws KeyNotFoundException 
     */
    public abstract void bihurtu(Jolasa jolasa, String jokoIzena, String jolasMota) throws IOException, KeyNotFoundException;

}