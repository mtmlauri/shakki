
package shakki.peli.siirrot;

import java.util.ArrayList;

public class Siirtotilasto {
    
    /**
     * Siirtotilasto
     */
    private ArrayList<Siirto> siirrot;
    
    public Siirtotilasto() {
        this.siirrot = new ArrayList<>();
    }
    
    public void alusta() {
        this.siirrot.clear();
    }
    
    @Override
    public String toString() {
        return "Siirrot" + siirrot;
    }
}