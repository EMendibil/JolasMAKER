package converter;



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
    public Bihurtzailea(Jolasa jolasa) 
    {
    	this.jolasa = jolasa;
    }

    /**
     * Converts game into playable file.
     */
    public abstract void bihurtu();

}