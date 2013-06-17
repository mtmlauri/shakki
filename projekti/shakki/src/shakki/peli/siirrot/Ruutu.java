
package shakki.peli.siirrot;


public class Ruutu {
    /**
     * Ruudun x-koordinaatti
     */
    private int x;
    /**
     * Ruudun y-koordinaatti
     */
    private int y;
    //private Nappula nappula; //Olisiko kannattanut ottaa nappulat mukaan tänne
    
    /**
     * Ruudun konstruktori
     * @param x Ruudun x-koordinaatti
     * @param y Ruudun y-koordinaatti
     */
    public Ruutu(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Hakee ruudun x-koordinaatin
     * @return Ruudun x-koordinaatti
     */
    public int haeX() {
        return this.x;
    }
    
    /**
     * Hakee ruudun y-koordinaatin
     * @return Ruudun y-koordinaatti
     */
    public int haeY() {
        return this.y;
    }
    
    /**
     * Asettaa tämän ruudun koordinaatit
     * @param x Asetettava x-koordinaatti
     * @param y Asetettava y-koordinaatti
     */
    public void asetaKoordinaatit(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    //onkoRuutuLaudalla
    
    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }
}