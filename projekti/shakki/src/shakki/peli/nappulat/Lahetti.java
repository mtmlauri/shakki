
package shakki.peli.nappulat;

import java.util.ArrayList;
import shakki.peli.Asema;
import shakki.peli.Peli;
import shakki.peli.siirrot.Ruutu;
import shakki.peli.siirrot.Siirto;

public class Lahetti extends NappulaYliluokka implements Nappula {
    public Lahetti(boolean onValkoinen) {
        super(onValkoinen);
        arvo = 3;
        lyhenne = 'L';
        if (onValkoinen) {
            tunnus = 3;
        }
        else {
            tunnus = -3;
        }
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
        return ovatkoRuudutLahetinLiikeradalla(lahto, kohde);
    }
    
    @Override
    public void siirra(Peli peli, Ruutu lahto, Ruutu kohde) {
        if (this.onValkoinen = peli.asema.valkeanVuoro) {
            if (this.ovatkoRuudutLiikeradalla(lahto, kohde)) {
                if (this.onValkoinen != peli.asema.pelilauta.haeNappula(kohde.haeX(), kohde.haeY()).onkoValkoinen()) {
                    System.out.println("Toistaiseksi OK.");
                }
                else {
                    System.out.println("Kohderuudussa on oma nappulasi");
                }
            }
            else {
                    System.out.println("Valitsemasi nappula ei liiku näin.");
            }
        }
        else {
            System.out.println("Et valinnut oikeanväristä nappulaa.");
        }
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
