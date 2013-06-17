
package shakki.peli.nappulat;

import java.util.ArrayList;
import shakki.peli.Asema;
import shakki.peli.Peli;
import shakki.peli.siirrot.Ruutu;
import shakki.peli.siirrot.Siirto;

public class Tyhja extends NappulaYliluokka implements Nappula {
    
    // Ongelmallista, jos lauta koostuu nappula-olioista, niin tyhjienkin pitäisi kai olla nappuloita
    // Ovatko tyhjät valkoisia, vai eivät...
    // En jaksaisi korvata lautaa ruutu-olioillakaan...
    public Tyhja(boolean onValkoinen) {
        super(onValkoinen);
        arvo = 0;
        lyhenne = ' ';
        //Tunnus on aina 0
        tunnus = 0;
    }
    
    @Override
    public int haeTunnus() {
        return tunnus;
    }
    
    @Override
    public boolean onkoValkoinen() {
        return onValkoinen;
    }
    
    @Override
    public boolean ovatkoRuudutLiikeradalla(Ruutu lahto, Ruutu kohde) {
        // Tyhjät eivät voi liikkua
        return false;
    }
    
    @Override
    public void siirra(Peli peli, Ruutu lahto, Ruutu kohde) {
        // Mikään siirto ei ole mahdollinen, eli pelilauta ei muutu
    }
    

    
    @Override
    public ArrayList<Siirto> haeLaillisetSiirrot(Asema asema, Nappula nappula) {
        ArrayList<Siirto> siirrot = new ArrayList<>();

        return siirrot;
    }
    
    @Override
    public String toString() {
        return "" + lyhenne;
    }
}