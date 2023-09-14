package converter;



import java.io.IOException;

import exceptions.KeyNotFoundException;
import games.Jolasa;

/**
 * This is the abstract class extended by other converters.
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
     * @throws InterruptedException 
     */
    public abstract void bihurtu(Jolasa jolasa, String jokoIzena, String jolasMota) throws IOException, KeyNotFoundException, InterruptedException;

}