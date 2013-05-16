package shakki;

import java.util.Scanner;

public class Lauta {
    public static Scanner input = new Scanner(System.in);
    
    private int[][] lauta = new int[8][8];
    String sarakkeet = "abcdefgh";
    String nappulat = "kdtlrs SRLTDK";
    private int vuoro; // Valkean vuoro 1 ja mustan vuoro -1, niin vuoronvaihto kertomalla -1:llä.
    // Koodin kirjoittaminen vuorojen avulla on hieman yksinkertaisempaa kuin esimerkiksi puolisiirto- ja siirtotilastojen kanssa.
    private int puolisiirto;
    
    private boolean[] linnoittautuminen = new boolean[4]; // Linnoittautumismahdollisuudet V-pitkä, V-lyhyt, M-pitkä, M-lyhyt
    private boolean onkoShakissa;
    
    // Näitä en nyt ainakaan heti käytä, mutta nämä olisivat varmaan avuksi tekoälylle siirtojen testauksessa
    //int[][] sotilaanSiirrot = {{0, 1}, {0, 2}, {1, 1}, {-1, 1}};
    //int[][] ratsunSiirrot = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, 2}, {-2, -1}, {-2, 1}, {-1, 2}};
    
        public Lauta () {
            
        }
        
        //Pelilaudan alustus
        //Pelilauta koostuu 8x8 int-taulukosta
        //Taulukkoon talletettavat numerot kertovat kulloisenkin asukin
        //Valkealle positiiviset arvot, mustalle negatiiviset
        //tyhjät ruudut: 0
        //sotilaat: 1
        //ratsut: 2
        //lähetit: 3
        //tornit: 4
        //daamit: 5
        //kuninkaat: 6
        //Alustetaan taulukko siten, että esimerkiksi valkea kuningas on paikassa 4-0 eli 5. sarake ja 1. rivi
        //Laudan tulostusvaiheessa pitää myöhemmin käännellä katselusuunta oikeaksi. Nyt valkean näkökulmasta pitäisi katsella lautaa vasemmalta.
        public void alustaLauta() {
            // Kuinkahan lauta olisi järkevintä asettaa?
////            alustaTyhjaLauta();
////            alustaKuninkaat();
////            alustaDaamit();
////            alustaTornit();
////            alustaLahetit();
////            alustaRatsut();
////            alustaSotilaat();
            lauta = new int[][]{ {4, 1, 0, 0, 0, 0, -1, -4}, 
                {2, 1, 0, 0, 0, 0, -1, -2}, 
                {3, 1, 0, 0, 0, 0, -1, -3}, 
                {5, 1, 0, 0, 0, 0, -1, -5}, 
                {6, 1, 0, 0, 0, 0, -1, -6}, 
                {3, 1, 0, 0, 0, 0, -1, -3}, 
                {2, 1, 0, 0, 0, 0, -1, -2}, 
                {4, 1, 0, 0, 0, 0, -1, -4} };
            vuoro = 1;
            puolisiirto = 1;
            alustaLinnoittautuminen();
            onkoShakissa = false;
        }
        
        public void alustaTyhjaLauta() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    lauta[i][j] = 0; 
                }
            }
        }
        
        public void alustaKuninkaat() {
            lauta[4][0] = 6;
            lauta[4][7] = -6;
        }
        
        public void alustaDaamit() {
            lauta[3][0] = 5;
            lauta[3][7] = -5;
        }
        
        public void alustaTornit() {
            lauta[0][0] = 4;
            lauta[7][0] = 4;
            lauta[0][7] = -4;
            lauta[7][7] = -4;
        }
        
        public void alustaLahetit() {
            lauta[2][0] = 3;
            lauta[5][0] = 3;
            lauta[2][7] = -3;
            lauta[5][7] = -3;
        }
        
        public void alustaRatsut() {
            lauta[1][0] = 2;
            lauta[6][0] = 2;
            lauta[1][7] = -2;
            lauta[6][7] = -2;
        }
        public void alustaSotilaat() {
            for (int i = 0; i < 8; i++) {
                lauta[i][1] = 1;
                lauta[i][6] = -1;
            }
        }
        
        public void alustaNappula(int nappula, int x, int y) {
            lauta[x][y] = vuoro * nappula;
        }
        
        public void korotaNappula(int x, int y, int vuoro) {
            while (true) {
                System.out.println("Valitse, miksi nappulaksi korotat sotilaan?");
                System.out.println("Vaihtoehdot ovat D, T, L ja R.");
                String valinta = input.nextLine();
                valinta = valinta.toLowerCase();
                if (valinta.equals("d")) {
                    alustaNappula(5, x, y);
                }
                else if (valinta.equals("t")) {
                    alustaNappula(4, x, y);
                }
                else if (valinta.equals("l")) {
                    alustaNappula(3, x, y);
                }
                else if (valinta.equals("r")) {
                    alustaNappula(2, x, y);
                }
                else {
                    System.out.println("Valintasi ei kelpaa. Anna valinta uudelleen");
                    continue;
                }
                break;
            }
        }
        
        public void alustaVuoro() {
            vuoro = 1;
        }
        
        public void alustaLinnoittautuminen() {
            for (int i = 0; i < 4; i++) {
                linnoittautuminen[i] = true;
            }
        }
        
        
    public void ruudutLaudalla(String ruutu1, String ruutu2) {
        ruutu1 = ruutu1.toLowerCase();
        ruutu2 = ruutu2.toLowerCase();
        int lahtoruutuX;
        int lahtoruutuY;
        int kohderuutuX;
        int kohderuutuY;
        //Tarkistetaan syötteet ovat oikeanpituisia
        if (ruutu1.length() == 2 && ruutu2.length() == 2) {
            // Tarkistetaan, että syötteet ovat oikeassa muodossa
            if (sarakkeet.indexOf(ruutu1.substring(0, 1)) != -1
                    && sarakkeet.indexOf(ruutu2.substring(0, 1)) != -1
                    && Integer.parseInt(ruutu1.substring(1, 2)) <= 8
                    && Integer.parseInt(ruutu1.substring(1, 2)) >= 1
                    && Integer.parseInt(ruutu2.substring(1, 2)) <= 8
                    && Integer.parseInt(ruutu2.substring(1, 2)) >= 1) {
                lahtoruutuX = sarakkeet.indexOf(ruutu1.substring(0, 1));
                lahtoruutuY = Integer.parseInt(ruutu1.substring(1, 2)) - 1;
                kohderuutuX = sarakkeet.indexOf(ruutu2.substring(0, 1));
                kohderuutuY = Integer.parseInt(ruutu2.substring(1, 2)) - 1;
                siirra(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
            }
            else {
                System.out.println("Antamasi ruudut eivät kelvanneet.");
                System.out.println("Tarkista, että syöte on muotoa XN, missä x on kirjain välillä A-H ja N on numero välillä 1-8.");
            }
        }
        else {
            System.out.println("Antamasi ruudut eivät kelvanneet. Syötteen pituus väärä.");
        }
    }
        
        
        
        // Aseman tutkimista
        // Jos lataa/asettaa satunnaisen aseman, niin silloin pitää tutkia aseman luonne
        // Pelin aikana pärjää, kun tutkii, miten kukin siirto vaikuttaa asemaan
        public void onkoAsemaShakki() { 
            // Etsitään vastapuolen kuningas
            // Tutkitaan jokainen kahdeksasta vaaka- ja viistorivistä ja etsitään ensimmäinen nappula
                // Jos nappula on vihollisen ja kulkee kyseistä linjaa pitkin, niin K on shakissa.
            // Tutkitaan lisäksi ettei vihollisratsua ole shakkaamassa.
        }
        
        // Asema on shakki, ja jos vastapuolella ei ole laillisia siirtoja, niin asema on myös matti.
        public void onkoAsemaMatti() {
            
        }
        
        // Jos asema ei ole shakki, mutta vastapuolella ei ole laillisia siirtoja, tilanne on patti (joka on tasapeli).
        public void onkoAsemaPatti() {
            
        }
               
        // Tutkitaan oman vuoron päättyessä
        // Jos on shakkaus, niin onkoShakissa = true
        // Oikeastaan riittää tutkia a) shakkaako siirtynyt nappula b) paljastiko siirtynyt nappula takaansa shakin.
            // Mahdollisesti jopa kaksoisshakki eli a ja b, jolloin vastustajan ainoa mahdollisuus on liikuttaa kuningastaan pakoon.
        // Tutkitaan lisäksi onko asema jopa matti
        public void tutkiOnkoShakkaus() {
            
        }
        
        // Jossain kohtaa siirrä-metodeita pitäisi tutkia myös siirron laillisuus
        // Kun on todettu, että kyseinen nappulan siirto on normaalisti mahdollinen,
        // niin pitää vielä tarkistaa, että siirron jalkeen kuningas ei ole shakissa
            // jos kuningas on ollut shakissa, niin ei ole enää
            // jos kuningas ei ollut shakissa, niin siirto ei paljasta hyökkäystä
        public void onkoSiirtoMyosLaillinen() {
            
        }
        
        
        public void siirra(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            if (lauta[lahtoruutuX][lahtoruutuY] * vuoro > 0) {
                System.out.println("Valitsemasi nappula on " + nappulat.charAt(lauta[lahtoruutuX][lahtoruutuY] + 6)+".\n");
                System.out.println("Seuraavaksi yritämme liikuttaa sitä.");
                if (lauta[lahtoruutuX][lahtoruutuY] == 1 || lauta[lahtoruutuX][lahtoruutuY] == -1) {
                    siirraSotilas(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                if (lauta[lahtoruutuX][lahtoruutuY] == 2 || lauta[lahtoruutuX][lahtoruutuY] == -2) {
                    siirraRatsu(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                if (lauta[lahtoruutuX][lahtoruutuY] == 3 || lauta[lahtoruutuX][lahtoruutuY] == -3) {
                    siirraLahetti(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                if (lauta[lahtoruutuX][lahtoruutuY] == 4 || lauta[lahtoruutuX][lahtoruutuY] == -4) {
                    siirraTorni(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                if (lauta[lahtoruutuX][lahtoruutuY] == 5 || lauta[lahtoruutuX][lahtoruutuY] == -5) {
                    siirraDaami(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                if (lauta[lahtoruutuX][lahtoruutuY] == 6 || lauta[lahtoruutuX][lahtoruutuY] == -6) {
                    siirraKuningas(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
            }
            else if (lauta[lahtoruutuX][lahtoruutuY] * vuoro == 0) {
                System.out.println("Et valinnut nappulaa.");
            }
            else {
                System.out.println("Valitsemasi nappula ei ole sinun. Siirto ei ole mahdollinen");
            }
            tulostaLauta();
        }
        
        // Jokaisen siirron päättyessä päivitettävät tilastot
        public void paivitaSiirtotilastot() {
            vuoro = vuoro*-1;
            puolisiirto++;
            // shakkaustilaston voisi lisätä 0, 1 tai 2 kertaa shakissa
            // linnoitusmahdollisuudet päivitetään. Tai oikeastaan ne päivittyvät, kun kuningas linnoittuu tai torni liikkuu ekaa kertaa.
            // ohestalyöntimahdollisuus. Oikeastaan se päivittyy aina, kun sotilas liikkuu kaksoisaskeleen toisen sotilaan vierelle
        }
        
        // Ovatkohan nämä siirrä-metodien ehdot hieman vaikeaselkoisia, olisikohan pitänyt tehdä kuvaavampia metodeja tarkistukseen?
        public void siirraSotilas(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Sotilaan marssiminen eteenpäin
            if (kohderuutuX == lahtoruutuX) {
                // Sotilaan yksöisaskel
                // Tarkistetaan, että lähtöruutu on järkevä sotilaan puoli huomioiden
                // Tarkistetaan, yritetäänko sotilasta liikuttaa yksi ruutu eteenpäin
                // Tarkistetaan, että kohderuutu on tyhjä
                if (((lahtoruutuY >= 1 && vuoro == 1) || (lahtoruutuY <= 6 && vuoro == -1))
                        && kohderuutuY == lahtoruutuY + 1 * vuoro 
                        && lauta[lahtoruutuX][lahtoruutuY + vuoro] == 0) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 1 * vuoro;
                    // Yksöisaskel voi johtaa myöskin sotilaankorottumiseen,
                    if ((kohderuutuY == 7 && vuoro == 1) || (kohderuutuY == 0 && vuoro == -1)) {
                        korotaNappula(kohderuutuX, kohderuutuY, vuoro);
                    }
                    paivitaSiirtotilastot();
                }
                // Sotilaan kaksoisaskel
                // Jos kaksoisaskel päätyy toisen sotilaan vierelle, niin ohestalyönti mahdollistuu...
                else if (((lahtoruutuY == 1 && vuoro == 1) || (lahtoruutuY == 6 && vuoro == -1))
                        && kohderuutuY == lahtoruutuY + 2 * vuoro 
                        && lauta[lahtoruutuX][lahtoruutuY + vuoro] == 0 
                        && lauta[lahtoruutuX][lahtoruutuY + 2 * vuoro] == 0) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 1 * vuoro;
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Sotilaan siirto ei ole mahdollinen.");
                }
            }
            // Vastustajan nappulan tavallinen lyöminen
            else if ((kohderuutuX == lahtoruutuX + 1 || kohderuutuX == lahtoruutuX - 1)
                    && kohderuutuY == lahtoruutuY + 1*vuoro
                    && lauta[kohderuutuX][kohderuutuY] * vuoro < 0) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 1*vuoro;
                // Sotilaan korottuminen on myös mahdollinen
                if ((kohderuutuY == 7 && vuoro == 1) || (kohderuutuY == 0 && vuoro == -1)) {
                        korotaNappula(kohderuutuX, kohderuutuY, vuoro);
                }
                paivitaSiirtotilastot();
            }
            // Ohestalyönti on vielä koodaamatta
            else {
                System.out.println("Sotilaan siirto ei ole mahdollinen.");
            }
        }
        
        public void siirraRatsu(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Tarkistetaan että ruudut sijaitsevat ratsun liikeradalla
            // Tarkistetaan, että kohderuudussa ei ole omaa nappulaa
            if (Math.abs((kohderuutuX - lahtoruutuX) * (kohderuutuY - lahtoruutuY)) == 2 
                    && !(lauta[kohderuutuX][kohderuutuY] * vuoro > 0)) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 2*vuoro;
                paivitaSiirtotilastot();
            }
            else {
                System.out.println("Ratsun siirto ei ole mahdollinen.");
            }
        }
        
        public boolean valiOnTyhja(int lahtoX, int lahtoY, int kohdeX, int kohdeY) {
            int laskuri = 0;
            for (int i = 1; i < Math.abs((kohdeX - lahtoX)); i++) {
                    if (lauta[lahtoX + i * (int)Math.signum(kohdeX - lahtoX)][lahtoY + i * (int)Math.signum(kohdeY - lahtoY)] != 0) {
                        laskuri++;
                    }
            }
            if (laskuri == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        
        public void siirraLahetti(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Ovathan ruudut lähetin liikeradalla
            // Kohderuudussa ei ole omaa nappulaa
            // Ruudut lähtö- ja kohderuudun välisellä linjalla ovat tyhjiä
            if (Math.abs((kohderuutuX - lahtoruutuX)) == Math.abs(kohderuutuY - lahtoruutuY) 
                    && !(lauta[kohderuutuX][kohderuutuY] * vuoro > 0)
                    && valiOnTyhja(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY) == true) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 3*vuoro;
                paivitaSiirtotilastot();
            }
            else {
                System.out.println("Lähetin siirto ei ole mahdollinen.");
            }
        }
        
        public void siirraTorni(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            
        }
        
        public void siirraDaami(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            
        }
        
        public void siirraKuningas(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            
        }

        // Lauta pitäisi kääntää valkean katselukulmaan
        public void tulostaLauta() {
            //Haluamme nähdä pelilaudan valkean näkökulmasta, joten i laskee seitsemästä nollaan
            //Mustan näkökulmasta i nousisi nollasta seitsemään ja j taitaisi laskea.
            for (int j = 7; j >= 0; j--) {
                for (int i = 0; i < 8; i++) {
                    System.out.print(nappulat.charAt(lauta[i][j] + 6));
                }
                System.out.println();
            }
////            for (int i = 0; i < 8; i++) {
////                for (int j = 0; j < 8; j++) {
////                    System.out.print(nappulat.charAt(lauta[i][j] + 6));
////                }
////                System.out.println();
////            }
            System.out.println("Vuoro on " +  vuoro);
            System.out.println("Puolisiirto on " + puolisiirto);
        }
        

        
        public void onkoLautaKelvollinen() {
            // Metodi testaa, onko pelilaudan asema kelvollinen pelattavaksi.
            // Kummallakin pelaajalla on yksi kuningas
            // Sotilaat sijaitsevat riveillä 2-7
            // Siirtovuoroa vailla olevaa kuningasta ei shakata
            // Siirtovuorossa olevalla puolella on laillisia siirtoja
        }

    public int[][] getLauta() {
        return lauta;
    }  
    
    public int getVuoro() {
        return vuoro;
    }
    
    public int getPuolisiirto() {
        return puolisiirto;
    }
    
    public boolean[] getLinnoittautuminen() {
        return linnoittautuminen;
    }
    
    public boolean getOnkoShakissa() {
        return onkoShakissa;
    }
    
    
}
