package shakki.graafinenKayttoliittyma;

import java.awt.event.*;
import javax.swing.*;

public class valikonKuuntelija implements ActionListener {
    graafinenKayttoliittyma graafinenkayttoliittyma;
    
    public valikonKuuntelija(graafinenKayttoliittyma graafinenkayttoliittyma) {
        this.graafinenkayttoliittyma = graafinenkayttoliittyma;
    }
    
    @Override
    public void actionPerformed(ActionEvent tapahtuma) {
        String valinta = ((JMenuItem)tapahtuma.getSource()).getText();
        switch (valinta) {
            case "Uusi peli":
                graafinenkayttoliittyma.uusi();
                break;
            case "Avaa":
                graafinenkayttoliittyma.avaa();
                break;
            case "Tallenna":
                graafinenkayttoliittyma.tallenna();
                break;
            case "Sulje":
                graafinenkayttoliittyma.sulje();
                break;
            case "Kumoa":
                graafinenkayttoliittyma.kumoa();
                break;
            case "Aseta asema":
                graafinenkayttoliittyma.asetaAsema();
                break;
            case "Vaihda katselusuunta":
                graafinenkayttoliittyma.asetaKatselusuunta();
                break;
                        
        }
    }
}
