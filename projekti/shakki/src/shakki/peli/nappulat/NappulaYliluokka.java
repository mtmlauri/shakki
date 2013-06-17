package shakki.peli.nappulat;

import shakki.peli.siirrot.Ruutu;

public class NappulaYliluokka {
    
    protected int arvo;
    protected int tunnus;
    protected char lyhenne;
    
    
    public boolean onValkoinen;
    
    /**
     * Ratsun mahdolliset siirrot
     */
    protected int[][] ratsunSiirrot = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    
    /**
     * Vaaka- ja pystysuorien liikkeiden suunnat
     */
    protected int[][] linjasuunnat = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    
    /**
     * Viistosuuntaisten liikkeiden suunnat
     */
    protected int[][] viistosuunnat = { {1, 1}, {1, -1}, {-1, -1}, {-1, 1} };
    
    //int[][] sotilaanSiirrot = {{0, 1}, {0, 2}, {1, 1}, {-1, 1}}
    
    
    public NappulaYliluokka(boolean onValkoinen) {
        this.onValkoinen = onValkoinen;
    }
    
    public boolean ovatkoRuudutLahetinLiikeradalla(Ruutu lahto, Ruutu kohde) {
        if (Math.abs(kohde.haeX() - lahto.haeX()) == Math.abs(kohde.haeY() - lahto.haeY())) {
            return true;
        }
        return false;
    }
    
    public boolean ovatkoRuudutTorninLiikeradalla(Ruutu lahto, Ruutu kohde) {
        if (lahto.haeX() == kohde.haeX() || lahto.haeY() == kohde.haeY()) {
            return true;
        }
        return false;
    }
    
    
}