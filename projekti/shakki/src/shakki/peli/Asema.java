
package shakki.peli;

/**
 * Asema-luokka sisältää kaikki pelilaudan asemaan liittyvät tiedot.
 * Pelilaudan aseman lisäksi sisältöön kuuluu kenen vuoro on ja mitkä erikoisiirtomahdollisuudet ovat
 */
public class Asema {
    /**
     * Pelilaudan asema
     */
    public Pelilauta pelilauta;
    
    /**
     * Totuusarvo ilmoittaa, onko valkean vuoro
     */
    public boolean valkeanVuoro;
    
    /**
     * Totuusarvot sisältävät pelaajien linnoittautumismahdollisuudet;
     */
    public boolean[] linnoittautuminen = new boolean[4];
    
    /**
     * Kertoo, milla sarakkeella sotilaan viimeisin kaksoisaskel on tapahtunut
     */
    public int viimeisinKaksoisaskelX; // Sotilaan ohestalyöntiä varten tallennetaan millä sarakkeella
    
    /**
     * Kertoo puolisiirtoina, milloin sotilas teki viimeksi kaksoisaskeleen
     */
    public int viimeisinKaksoisaskelAika; // ja millä hetkellä viimeisin sotilaan kaksoisaskel on tapahtunut
    
    /**
     * Aseman konstruktori
     */
    public Asema() {
        this.pelilauta = new Pelilauta();
    }
    
    /**
     * Alustaa aseman pelin alkutilanteeseen.
     */
    public void alustaAsema() {
        this.pelilauta.alustaLauta();
        this.valkeanVuoro = true;
        this.linnoittautuminen = new boolean[]{true, true, true, true};
        this.viimeisinKaksoisaskelX = -1;
        this.viimeisinKaksoisaskelAika = -1;
    }
}