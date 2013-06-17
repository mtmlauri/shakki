
package shakki.peli;

import java.util.ArrayList;
import shakki.peli.siirrot.Siirtotilasto;

/**
 * Luokka sisältää shakkipeliin liittyvät tiedot
 */
public class Peli {
    /**
     * Valkoisilla pelaava
     */
    public Pelaaja valkoinen;
    /**
     * Mustilla pelaava
     */
    public Pelaaja musta;
    
    /**
     * Puolisiirtolaskuri
     */
    public int puolisiirto;
    /**
     * Siirtotilasto
     */
    public Siirtotilasto siirrot;
    
    /**
     * Asematiedot: laudan asema, vuoro, erikoissiirtomahdollisuudet
     */
    public Asema asema;
    /**
     * Lista pelilaudalla esiintyneistä asemista
     */
    public ArrayList<Asema> asemat; // Mielessäni aseman toistuminen 3 kertaa
    
    /**
     * Totuusarvo, onko peli kesken
     */
    public boolean peliJatkuu;
    
    // TARVITSISINKO MUITA MUUTTUJIA
    // ILMOITUS SHAKKI!
    // ENTÄPÄ KUINKA HOIDAN KOROTUSTILANTEEN
    
    /**
     * Viimeksi tapahtunut virhe
     */
    public String virhe; // Tänne koetetaan tallettaa viimeksi tapahtunut virhetieto,
                            // jotta graafinen käyttöliittymäkin osaisi kertoa viimeksi tapahtuneen virheen.
    

    /**
     * Pelin oletuskonstruktori
     */
    public Peli() {
        this.valkoinen = new Pelaaja("Valkoinen");
        this.musta = new Pelaaja("Musta");
        this.puolisiirto = 1;
        this.siirrot = new Siirtotilasto();
        this.asema = new Asema();
        this.asemat = new ArrayList<>();
        this.peliJatkuu = true;
        this.virhe = "";
    }
    
    /**
     * Pelin konstruktori
     * @param valkoinen Valkoisilla pelaavan nimi
     * @param musta Mustilla pelaavan nimi
     */
    public Peli(String valkoinen, String musta) {
        this.valkoinen = new Pelaaja(valkoinen);
        this.musta = new Pelaaja(musta);
        this.peliJatkuu = true;
    }
      
    
    /**
     * Alustaa pelin alkuasemaan
     * @param valkoinen Valkoisilla pelaavan nimi
     * @param musta Mustilla pelaavan nimi
     */
    public void uusiPeli(String valkoinen, String musta) {
        this.valkoinen = new Pelaaja(valkoinen);
        this.musta = new Pelaaja(musta);
        this.puolisiirto = 1;
        this.siirrot.alusta();
        this.asema.alustaAsema();
        this.asemat.clear();
        this.peliJatkuu = true;
        this.virhe = "";
    }
    
    
    
    @Override
    public String toString() {
        String palaute;
        return valkoinen + "\n" + musta + "\n" + siirrot;
    }
}