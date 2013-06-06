package shakki.pelaaja.nappula;

import shakki.pelaaja.nappula.kuningas.Kuningas;
import shakki.pelaaja.nappula.kuningatar.Kuningatar;
import shakki.pelaaja.nappula.lahetti.Lahetti;
import shakki.pelaaja.nappula.ratsu.Ratsu;
import shakki.pelaaja.nappula.sotilas.Sotilas;
import shakki.pelaaja.nappula.torni.Torni;

public class Nappula {
    // Tänne voisi tulla nappuloiden perusliikkeisiin liittyviä metodeja
    private Kuningas kuningas;
    private Kuningatar kuningatar;
    private Torni torni;
    private Lahetti lahetti;
    private Ratsu ratsu;
    private Sotilas sotilas;
    
    private int x;
    private int y;
    
    
    public Nappula(Kuningas kuningas, int x, int y) {
        this.kuningas = kuningas;
        this.x = x;
        this.y = y;
    }
    
    public Nappula(Kuningatar kuningatar, int x, int y) {
        this.kuningatar = kuningatar;
    }
    
    public Nappula(Torni torni, int x, int y) {
        this.torni = torni;
    }
    
    public Nappula(Lahetti lahetti, int x, int y) {
        this.lahetti = lahetti;
    }
    
    public Nappula(Ratsu ratsu, int x, int y) {
        this.ratsu = ratsu;
    }
    public Nappula(Sotilas sotilas, int x, int y) {
        this.sotilas = sotilas;
    }
    
}
