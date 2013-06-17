
package shakki.tekstikayttoliittyma;

import java.util.Scanner;
import shakki.peli.Peli;
import shakki.peli.Pelilauta;
import shakki.peli.siirrot.Ruutu;

/**
 * Ohjelman tekstikäyttöliittymä, sisältää peli-olion ja kyselee siirtojen koordinaatteja ja muuta tarpeellista.
 */
public class Tekstikayttoliittyma {
    private static Scanner input = new Scanner(System.in);
    
    /**
     * Sisältää shakkipelin
     */
    Peli peli;
    
    /**
     * Ruudun x-koordinaatti
     */
    int ruutuX;
    /**
     * Ruudun y-koordinaatti
     */
    int ruutuY;
    /**
     * Lähtöruutu
     */
    Ruutu lahto;
    /**
     * Kohderuutu
     */
    Ruutu kohde;
    
    /**
     * Nappuloiden lyhenteet tulostettavalla laudalla
     */
    String nappulat = "kdtlrs SRLTDK";
    /**
     * Shakkilaudan sarakkeet
     */
    String sarakkeet = "abcdefgh";
    /**
     * Shakkilaudan rivit
     */
    String rivit = "12345678";
    
/**
 * Konstruktori alustaa peliä varten tarpeelliset oliot.
 */
    public Tekstikayttoliittyma() {
        lahto = new Ruutu(-1, -1);
        kohde = new Ruutu(-1, -1);
        peli = new Peli();
    }
    
    /**
     * Tekstikäyttöliittymän runko
     */
    public void pelaa() {
        peli.uusiPeli("Valkoinen", "Musta");
        while (peli.peliJatkuu) {
            kysySiirto();
//            // TÄHÄN PITÄISI KEKSIÄ, MITEN TUTKITAAN SIIRRON MAHDOLLISUUS
//            // JA MITEN MUUTETAAN PELILAUDAN ASEMAA, PITÄISIKÖHÄN SIIRTOMETODIEN PALAUTTAA JOTAIN...
            peli.asema.pelilauta.haeNappula(lahto.haeX(), lahto.haeY()).siirra(peli, lahto, kohde);
            tulostaLautaValkealle(peli.asema.pelilauta);
        }
    }
    
    /**
     * Kysyy lähtö- ja kohderuutuja, kunnes ne on annettu oikeassa muodossa
     */
    public void kysySiirto() {
        System.out.println();
            System.out.println("Anna siirto");
            String ruutu1;
            String ruutu2;
            do {
                System.out.print("Anna ensin lähtöruutu: ");
                ruutu1 = input.nextLine();
            } while (!syoteKelvollinen(ruutu1));
            lahto.asetaKoordinaatit(ruutuX, ruutuY);
            do {
                System.out.print("Anna sitten kohderuutu: ");
                ruutu2 = input.nextLine();
            } while (!syoteKelvollinen(ruutu2));
            kohde.asetaKoordinaatit(ruutuX, ruutuY);
    }
    
    /**
     * Tarkistaa, että syöte on oikeaa muotoa, esimerkiksi A1 tai H8.
     * Myönteisessä tapauksessa tallettaa koordinaatit tekstikäyttöliittymän muuttujiin ruutuX ja ruutuY
     * @param ruutu Syötteenä tullut merkkijono
     * @return Palauttaa totuusarvon, voitiinko ruutu muuttaa oikeaan muotoon
     */
    public boolean syoteKelvollinen(String ruutu) {
        ruutu = ruutu.toLowerCase();
        //Tarkistetaan syötteet ovat oikeanpituisia
        if (ruutu.length() == 2) {
            // Tarkistetaan, että syötteet ovat oikeassa muodossa
            if (sarakkeet.indexOf(ruutu.substring(0, 1)) != -1
                    && rivit.indexOf(ruutu.substring(1, 2)) != -1) {
                System.out.println("Syöte kelpasi.");
                this.ruutuX = sarakkeet.indexOf(ruutu.substring(0, 1));
                this.ruutuY = Integer.parseInt(ruutu.substring(1, 2)) - 1;
                return true;
            }
            else {
                System.out.println("Ruutu ei kelvannut.");
                System.out.println("Tarkista, että syöte on muotoa XN, missä x on kirjain välillä A-H ja N on numero välillä 1-8.");
            }
        }
        else {
            System.out.println("Antamasi ruutu ei kelvannut. Syötteen pituus väärä.");
        }
        return false;
    }
    
    /**
     * Tulostaa pelilaudan valkean näkökulmasta
     * @param pelilauta Syötteenä tullut pelilauta
     */
    public void tulostaLautaValkealle(Pelilauta pelilauta) {
        for (int j = 7; j >= 0; j--) {
           for (int i = 0; i < 8; i++) {
               System.out.print(
                       nappulat.charAt(pelilauta.haeNappula(i, j).haeTunnus() + 6));
           }
            System.out.println();
       }
    }
}