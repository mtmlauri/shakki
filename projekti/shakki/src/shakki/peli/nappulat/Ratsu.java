
package shakki.peli.nappulat;

import java.util.ArrayList;
import shakki.peli.Asema;
import shakki.peli.Peli;
import shakki.peli.siirrot.Ruutu;
import shakki.peli.siirrot.Siirto;

public class Ratsu extends NappulaYliluokka implements Nappula {

    public Ratsu(boolean onValkoinen) {
        super(onValkoinen);
        arvo = 3;
        lyhenne = 'R';
        if (onValkoinen) {
            tunnus = 2;
        }
        else {
            tunnus = -2;
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
        if ( Math.abs((kohde.haeX() - lahto.haeX()) * (kohde.haeY() - lahto.haeY())) == 2) {
            return true;
        }
        return false;
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
    
//    public void siirraRatsu(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
//            // Tarkistetaan että ruudut sijaitsevat ratsun liikeradalla
//            // Tarkistetaan, että kohderuudussa ei ole omaa nappulaa.
//            if (Math.abs((kohderuutuX - lahtoruutuX) * (kohderuutuY - lahtoruutuY)) == 2 
//                    && !(lauta[kohderuutuX][kohderuutuY] * pelaaja > 0)) {
//                testilauta = lauta;
//                testilauta[lahtoruutuX][lahtoruutuY] = 0;
//                testilauta[kohderuutuX][kohderuutuY] = 2*pelaaja;
//                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
//                    lauta[lahtoruutuX][lahtoruutuY] = 0;
//                    lauta[kohderuutuX][kohderuutuY] = 2*pelaaja;
//                    paivitaSiirtotilastot();
//                }
//                else {
//                    System.out.println("Ratsun siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
//                }
//            }
//            else {
//                System.out.println("Ratsun siirto ei ole mahdollinen.");
//            }
//        }
    

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