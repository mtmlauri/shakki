package shakki.graafinenKayttoliittyma;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class nappulanKuuntelija implements ActionListener{
    graafinenKayttoliittyma graafinenkayttoliittyma;
    
    public nappulanKuuntelija(graafinenKayttoliittyma graafinenkayttoliittyma) {
        this.graafinenkayttoliittyma = graafinenkayttoliittyma;
    }
    
    // Nappulanpainalluksista otetaan siirtojen koordinaatit
    @Override
    public void actionPerformed(ActionEvent tapahtuma) {
        JButton nappula = ((JButton)tapahtuma.getSource());
        int valinta = graafinenkayttoliittyma.kaikkiNappulat.indexOf((JButton)tapahtuma.getSource());
        // Eka painallus, saavutaan, kun lähtöpaikka on "nollattu"
        if (graafinenkayttoliittyma.lahtoX == -1) {
            graafinenkayttoliittyma.lahtoX = valinta % 8;
            graafinenkayttoliittyma.lahtoY = 7 - (valinta - valinta % 8) / 8;
        }
        // Parilliset kierrokset, saavutaan, kun kohderuutu on nollattu
        else if (graafinenkayttoliittyma.kohdeX == -1) {
            graafinenkayttoliittyma.kohdeX = valinta % 8;
            graafinenkayttoliittyma.kohdeY = 7 - (valinta - valinta % 8) / 8;
            graafinenkayttoliittyma.siirra();
        }
        //Parittomat valinnat, lähtö- ja kohdemuistipaikat ovat jo täynnä, joten lähtö on uusi lähtö ja kohde nollataan
        else {
            graafinenkayttoliittyma.lahtoX = valinta % 8;
            graafinenkayttoliittyma.lahtoY = 7 - (valinta - valinta % 8) / 8;
            graafinenkayttoliittyma.kohdeX = -1;
        }
    }
    
}
