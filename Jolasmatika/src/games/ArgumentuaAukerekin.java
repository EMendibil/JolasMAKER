package games;

/**
 * 
 */
public class ArgumentuaAukerekin extends Argumentua {
	
	private Aukera[] aukerak;

    /**
     * Constructor
     * @param mota 
     * @param izena 
     * @param aukerak
     */
    public ArgumentuaAukerekin(String mota, String izena, Aukera[] aukerak) {
        super(mota, izena);
        this.aukerak = aukerak;
    }

    /**
     * @return
     */
    public Aukera[] getAukerak() {
        return aukerak;
    }

}