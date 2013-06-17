
package shakki.peli;

    // Loogisesti ajateltuna pelaaja-luokka olisi voinut sisältää pelaajan nappulat
        // sekä pelaajan linnoittautumismahdollisuudet, LLyhyt ja LPitkä
        // Hylkäsin kuitenkin tämän vaihtoehdon.

/**
 * Sisältää tiedot pelaajasta. Oikeastaan vain nimen.
 */
public class Pelaaja {
    /**
     * Pelaajan nimi
     */
    private String pelaajanNimi;
    
    
    public Pelaaja(String nimi) {
        this.pelaajanNimi = nimi;
    }
    
    public String haeNimi() {
        return this.pelaajanNimi;
    }
    
    @Override
    public String toString() {
        return pelaajanNimi;
    }
}