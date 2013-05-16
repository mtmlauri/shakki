package shakki;

import java.util.*;

public class Shakki {
    public static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        Lauta pelilauta = new Lauta();
        pelilauta.alustaLauta();
        
        System.out.println("Pelaa shakkia noin 10 puolisiirron verran.");
        System.out.println("Tulostetaan lauta");
        pelilauta.tulostaLauta();
        for (int i = 0; i < 10; i++) {
            System.out.println("Anna siirto");
            System.out.print("Anna ensin lähtöruutu: ");
            String ruutu1 = input.nextLine();
            System.out.print("Anna sitten kohderuutu: ");
            String ruutu2 = input.nextLine();
            pelilauta.ruudutLaudalla(ruutu1, ruutu2);
        }
    }
}