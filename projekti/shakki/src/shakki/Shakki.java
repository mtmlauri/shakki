package shakki;

import shakki.tekstikayttoliittyma.Tekstikayttoliittyma;

public class Shakki {
        public static void main(String[] args) {
            
        // REFAKTOROITU VERSIO, ON KESKENERÄINEN
        Tekstikayttoliittyma kayttis = new Tekstikayttoliittyma();
        kayttis.pelaa();
        

        
        // AIKAISEMPI KOODI ENNEN REFAKTOROINTIA, OLI AINAKIN TOIMIVAA...
        // OHEINEN LAUTA-LUOKKA LIITTYI TÄHÄN KOODIIN
        // Tämä versio toimi pääsääntöisesti pelattavasti.
        // Keskeisin virhe tapahtui siirronlaillisuustarkistusosioissa,
        // laittomat siirrot huomattiin laittomiksi, mutta silti ne toteutuivat...
            // Luulen, että virhe oli siinä, että asetin testilaudan testilauta[][] = lauta[][] -tyyppisesti
            // Se taisi johtaa siihen, että viittaukset menivät ja muuttivat samaa oliota.
            // Testilaudaksi olisi pitänyt kopioida laudan asema ruutu kerrallaan tms. ...
        
        // Graafinen käyttöliittymä, toimi ihan näppärästi, siirtojen testaus on tällä helppoa
            // Yrität vain napsutella ruutuja lähtö-kohde-lähtö-kohde ja niin edelleen...
            // Nappulan korotusta ei tuettu vielä graafisessa käyttöliittymässä
//        graafinenKayttoliittyma graafinenkayttoliittyma = new graafinenKayttoliittyma();
//        SwingUtilities.invokeLater(graafinenkayttoliittyma);
          

//        
        
        
           // VANHOJA TESTEJÄ, miten Lauta-luokan onkoRuutuUhattu toimii
////        for (int j = 7; j >= 0; j--) {
////            for (int i = 0; i < 8; i++) {
////                    System.out.print(pelilauta.monestikoRuutuUhattu(i, j, 1));
////            }
////            System.out.println();
////        }
////        System.out.println();
////        for (int j = 7; j >= 0; j--) {
////            for (int i = 0; i < 8; i++) {
////                    System.out.print(pelilauta.monestikoRuutuUhattu(i, j, -1));
////            }
////            System.out.println();
////        }
        
    }
}