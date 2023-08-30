package converter;



import java.io.IOException;

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
     */
    public abstract void bihurtu(Jolasa jolasa, String jokoIzena, String jolasMota) throws IOException;

}