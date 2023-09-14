package games;

import java.util.List;

/**
 * 
 */
public abstract class Jolasa {
	
    private String hasierakoPista = "this is a clue";
    private int blokeKopurua;
    private List<String> onartutakoBlokeak;
    		
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
    public List<String> getOnartutakoBlokeak() {
        return onartutakoBlokeak;
    }

    /**
     * @param blokeak
     */
    public void setOnartutakoBlokeak(List<String> blokeak) {
        this.onartutakoBlokeak = blokeak;
    }

}