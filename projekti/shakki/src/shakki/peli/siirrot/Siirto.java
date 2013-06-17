
package shakki.peli.siirrot;

public class Siirto {
    /**
     * Lähtöruutu
     */
    Ruutu lahto;
    /**
     * Kohderuutu
     */
    Ruutu kohde;
    
    public Siirto(Ruutu lahto, Ruutu kohde) {
        this.lahto = lahto;
        this.kohde = kohde;
    }
    
    @Override
    public String toString() {
        return lahto + " - " + kohde;
    }
}