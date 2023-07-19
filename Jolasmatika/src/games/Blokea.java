package games;

/**
 * 
 */
public class Blokea {


    private String mota;
    private String textua;
    private boolean aurreanBlokea;
    private boolean atzeanBlokea;
    private String kolorea;
    private String pista;

    /**
     * Constructor
     * @param mota 
     * @param textua 
     * @param aurreanBlokea 
     * @param atzeanBlokea 
     * @param kolorea 
     * @param pista
     */
    public Blokea(String mota, String textua, boolean aurreanBlokea, boolean atzeanBlokea, String kolorea, String pista) {
        this.mota = mota;
        this.textua = textua;
        this.aurreanBlokea = aurreanBlokea;
        this.atzeanBlokea = atzeanBlokea;
        this.kolorea = kolorea;
        this.pista = pista;
        
    }

    /**
     * @return mota
     */
    public String getMota() {
        return mota;
    }

    /**
     * @return textua
     */
    public String getTextua() {
        return textua;
    }

    /**
     * @return aurreanBlokea
     */
    public boolean getAurreanBlokea() {
        return aurreanBlokea;
    }

    /**
     * @return atzeanBlokea
     */
    public boolean getAtzeanBlokea() {
        return atzeanBlokea;
    }

    /**
     * @return kolorea
     */
    public String getKolorea() {
        return kolorea;
    }

    /**
     * @return pista
     */
    public String getPista() {
        return pista;
    }

}