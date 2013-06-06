package shakki;

import java.util.*;
import javax.swing.*;

import shakki.graafinenKayttoliittyma.graafinenKayttoliittyma;

public class Shakki {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        // KATSELMOIJALLE
        // Luokkia alkaa olla paljon, mutta tällä hetkellä toiminnassa ovat vain luokat
            // Shakki
            // Lauta
            // Käyttöliittymän luokat, 3 kpl
        // Muut luokat täyttyvät, kunhan alan siirrellä luokan lauta sisältöä niiden hoidettavaksi
        
        // Ohjelma toimii jotakuinkin pelattavasti. Lailliset siirrot onnistuvat yleensä.
        // Shakatessa kuitenkin toisella vaikuttaa olevan rajattomasti laittomia siirtoja, kunnes poistaa shakkauksen.
        
        // Graafinen käyttöliittymä toimii siinä missä tekstikäyttöliittymäkin ja on helppokäyttöisempi
            // Lähtöruudun korostamista voisin kokeilla vielä...
        // Nappulan korottamista ei kuitenkaan tueta vielä graafisen käyttöliittymän puolella
        
        graafinenKayttoliittyma graafinenkayttoliittyma = new graafinenKayttoliittyma();
        SwingUtilities.invokeLater(graafinenkayttoliittyma);
        
        
        
        
        
        // Tekstikäyttöliittymä
//        Lauta pelilauta = new Lauta();
//        pelilauta.alustaLauta();
//        
//        System.out.println("Pelaa shakkia noin 10 puolisiirron verran.");
//        System.out.println("Tulostetaan lauta");
//        pelilauta.tulostaLauta();
//        for (int i = 0; i < 10; i++) {
//            System.out.println();
//            System.out.println("Anna siirto");
//            System.out.print("Anna ensin lähtöruutu: ");
//            String ruutu1 = input.nextLine();
//            System.out.print("Anna sitten kohderuutu: ");
//            String ruutu2 = input.nextLine();
//            pelilauta.kelpaavatkoSyotetytRuudut(ruutu1, ruutu2);
//        }
        
        
        
        
        
           // TESTAUS, miten onkoRuutuUhattu edistyy
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