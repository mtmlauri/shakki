package shakki.pelaaja;

import java.util.*;
import shakki.pelaaja.nappula.Nappula;

public class Pelaaja {
    private String pelaajanNimi;
    private ArrayList<Nappula> nappulat = new ArrayList<Nappula>();
    
    public Pelaaja(String nimi) {
        this.pelaajanNimi = nimi;
    }
    
    public void lisaaNappula(Nappula nappula) {
        nappulat.add(nappula);
    }
    
    public void poistaNappula(Nappula nappula) {
        nappulat.remove(nappula);
    }

}
