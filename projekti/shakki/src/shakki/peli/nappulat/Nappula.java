package shakki.peli.nappulat;

import java.util.ArrayList;
import shakki.peli.Asema;
import shakki.peli.Peli;
import shakki.peli.siirrot.Ruutu;
import shakki.peli.siirrot.Siirto;

    // HMMHH, MITÄHÄN NAPPULOILLE PITÄISI SYÖTTÄÄ JA MITEN ONNISTUU PELIASEMAN MUUTTAMINEN

public interface Nappula {
    public int haeTunnus();
    public boolean onkoValkoinen();
    public void siirra(Peli peli, Ruutu lahto, Ruutu kohde);
    public ArrayList<Siirto> haeLaillisetSiirrot(Asema asema, Nappula nappula);
    public boolean ovatkoRuudutLiikeradalla(Ruutu lahto, Ruutu kohde);
}