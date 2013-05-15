package shakki;

import java.util.*;

public class Shakki {
    public static Scanner input = new Scanner(System.in);
    
    
    public static boolean ruutuKelvollinen(String ruutu) {
        if (ruutu.length() != 2) {
            return false;
        }
        else {
            if ("abcdefgh".indexOf(ruutu.substring(0, 1)) != -1
                    && Integer.parseInt(ruutu.substring(1, 2)) <= 8
                    && Integer.parseInt(ruutu.substring(1, 2)) >= 1) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Lauta pelilauta = new Lauta();
        pelilauta.alustaLauta();
        
        System.out.println("Tulostetaan lauta");
        pelilauta.tulostaLauta();

        while (true) {
            int lahtoruutuX;
            int lahtoruutuY;
            int kohderuutuX;
            int kohderuutuY;
            System.out.println("Anna siirto");
            while (true) {
                System.out.print("Anna ensin lähtöruutu: ");
                String ruutu = input.nextLine();
                if (ruutuKelvollinen(ruutu) == true) {
                    System.out.println("Lähtöruutu kelpaa.");
                    lahtoruutuX = "abcdefgh".indexOf(ruutu.substring(0, 1));
                    lahtoruutuY = Integer.parseInt(ruutu.substring(1, 2)) - 1;
                    break;
                }
                else {
                    System.out.println("Lähtöruutu ei kelpaa.");
                }
            }
            while (true) {
                System.out.print("Anna sitten kohderuutu: ");
                String ruutu = input.nextLine();
                if (ruutuKelvollinen(ruutu) == true) {
                    System.out.println("Kohderuutu kelpaa.");
                    kohderuutuX = "abcdefgh".indexOf(ruutu.substring(0, 1));
                    kohderuutuY = Integer.parseInt(ruutu.substring(1, 2)) - 1;
                    break;
                }
                else {
                    System.out.println("Kohderuutu ei kelpaa.");
                }
            }
                // Nyt on selvitetty lähtö- ja kohderuudut
                // Yritetään sitten siirtää nappulaa
            pelilauta.siirra(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                // Ja tulostetaan lopuksi pelilauta
            pelilauta.tulostaLauta();
            break; //POISTUMME SILMUKASTA. MYÖHEMMIN LISÄTÄÄN POISTUMISEHTO
        }
    }
}
