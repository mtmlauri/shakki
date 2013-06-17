package shakki.peli;

import shakki.peli.nappulat.Kuningas;
import shakki.peli.nappulat.Kuningatar;
import shakki.peli.nappulat.Lahetti;
import shakki.peli.nappulat.Nappula;
import shakki.peli.nappulat.Ratsu;
import shakki.peli.nappulat.Sotilas;
import shakki.peli.nappulat.Torni;
import shakki.peli.nappulat.Tyhja;

/**
 * Pelilauta-luokka. Pelilauta koostuu Nappula-olioista. Tyhjätkin on luettu nappulaolioiksi.
 */
public class Pelilauta {
    /**
     * Pelilauta on 8x8 Nappula-ruudukko
     */
    public Nappula[][] lauta = new Nappula[8][8];
    
    public Pelilauta() {
        
    }
    
    /**
     * Alustaa pelilaudan alkuaseman
     */
    public void alustaLauta() {
        alustaTyhjat();
        alustaTornit();
        alustaRatsut();
        alustaLahetit();
        alustaDaamit();
        alustaKuninkaat();
        alustaSotilaat();
    }
    
    private void alustaTyhjat() {
        for (int i = 0; i < 8; i++) {
            for (int j = 2; j < 6; j++) {
                this.lauta[i][j] = new Tyhja(true);
            }
        }
    }
    
    private void alustaTornit() {
        this.lauta[0][0] = new Torni(true);
        this.lauta[7][0] = new Torni(true);
        this.lauta[0][7] = new Torni(false);
        this.lauta[7][7] = new Torni(false);
    }
    
    private void alustaRatsut() {
        this.lauta[1][0] = new Ratsu(true);
        this.lauta[6][0] = new Ratsu(true);
        this.lauta[1][7] = new Ratsu(false);
        this.lauta[6][7] = new Ratsu(false);
    }
    
    private void alustaLahetit() {
        this.lauta[2][0] = new Lahetti(true);
        this.lauta[5][0] = new Lahetti(true);
        this.lauta[2][7] = new Lahetti(false);
        this.lauta[5][7] = new Lahetti(false);
    }
    
    private void alustaDaamit() {
        this.lauta[3][0] = new Kuningatar(true);
        this.lauta[3][7] = new Kuningatar(false);
    }
    
    private void alustaKuninkaat() {
        this.lauta[4][0] = new Kuningas(true);
        this.lauta[4][7] = new Kuningas(false);
    }
    
    private void alustaSotilaat() {
        for (int i = 0; i < 8; i++) {
            this.lauta[i][1] = new Sotilas(true);
        }
        for (int i = 0; i < 8; i++) {
            this.lauta[i][6] = new Sotilas(false);
        }
    }
    
    /**
     * Palauttaa pelilaudan koordinaatissa olevan nappulan
     * @param x x-koordinaatti
     * @param y y-koordinaatti
     * @return Ruudussa oleva nappula
     */
    public Nappula haeNappula(int x, int y) {
        return this.lauta[x][y];
    }
    
    //alustaNappula
    
    //onkoLautaKelvollinen
}