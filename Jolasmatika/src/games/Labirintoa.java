package games;

import javax.swing.ImageIcon;

/**
 * 
 */
public class Labirintoa extends Jolasa{

    private int[][] mapa;
    private ImageIcon fondoa;
    private ImageIcon bideaIrudia;
    private ImageIcon jokalariIrudia;

    /**
     * Constructor
     * @param mapa
     */
    public Labirintoa(int[][] mapa) {
        this.mapa = mapa;
    }

    /**
     * @return mapa
     */
    public int[][] getMapa() {
        return mapa;
    }

    /**
     * @param mapa
     */
    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }

    /**
     * @return fondoa
     */
    public ImageIcon getFondoa() {
        return fondoa;
    }

    /**
     * @param fondoa
     */
    public void setFondoa(ImageIcon fondoa) {
        this.fondoa = fondoa;
    }

    /**
     * @return bideaIrudia
     */
    public ImageIcon getBideaIrudia() {
        return bideaIrudia;
    }

    /**
     * @param bideaIrudia
     */
    public void setBideaIrudia(ImageIcon bideaIrudia) {
        this.bideaIrudia = bideaIrudia;
    }

    /**
     * @return jokalariIrudia
     */
    public ImageIcon getJokalariIrudia() {
        return jokalariIrudia;
    }

    /**
     * @param jokalariIrudia
     */
    public void setJokalariIrudia(ImageIcon jokalariIrudia) {
        this.jokalariIrudia = jokalariIrudia;
    }

}