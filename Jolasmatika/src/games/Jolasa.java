package games;

import java.util.ArrayList;

/**
 * 
 */
public abstract class Jolasa {
	
    private String hasierakoPista = "this is a clue";
    private int blokeKopurua;
    private ArrayList<Blokea> onartutakoBlokeak;
    		
    /**
     * Constructor
     */
    protected Jolasa() {
    }

    /**
     * @return hasierakoPista
     */
    public String getHasierakoPista() {
        return hasierakoPista;
    }

    /**
     * @param hasierakoPista
     */
    public void setHasierakoPista(String hasierakoPista) {
        this.hasierakoPista = hasierakoPista;
    }

    /**
     * @return blokeKopurua
     */
    public int getBlokeKopurua() {
        return blokeKopurua;
    }

    /**
     * @param blokeKopuruak
     */
    public void setBlokeKopurua(int blokeKopurua) {
        this.blokeKopurua = blokeKopurua;
    }

    /**
     * @return onartutakoBlokeak
     */
    public ArrayList<Blokea> getOnartutakoBlokeak() {
        return onartutakoBlokeak;
    }

    /**
     * @param onartutakoBlokeak
     */
    public void setOnartutakoBlokeak(ArrayList<Blokea> onartutakoBlokeak) {
        this.onartutakoBlokeak = onartutakoBlokeak;
    }

}