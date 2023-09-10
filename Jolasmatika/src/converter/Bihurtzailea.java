package converter;



import java.io.IOException;

import exceptions.KeyNotFoundException;
import games.Jolasa;

/**
 * 
 */
public abstract class Bihurtzailea {
	protected Idazlea idazlea;
	
    /**
     * Constructor
     */
    protected Bihurtzailea() 
    {
    }

    /**
     * Converts game into playable file.
     * @throws IOException 
     * @throws KeyNotFoundException 
     */
    public abstract void bihurtu(Jolasa jolasa, String jokoIzena, String jolasMota) throws IOException, KeyNotFoundException;

}