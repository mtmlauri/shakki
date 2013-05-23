package shakki;

import java.util.Scanner;

public class Lauta {
    public static Scanner input = new Scanner(System.in);
    
    private int[][] lauta = new int[8][8];
    int[][] testilauta = new int[8][8];
    String sarakkeet = "abcdefgh";
    String rivit = "12345678";
    String nappulat = "kdtlrs SRLTDK";
    private int pelaaja; // Valkean 1 ja mustan -1, niin vuoronvaihdot kertomalla -1:llä.
    // Koodin kirjoittaminen vuorojen avulla on hieman yksinkertaisempaa kuin esimerkiksi puolisiirto- ja siirtotilastojen kanssa.
    private int puolisiirto;
    
    private boolean[] linnoittautuminenValkea = new boolean[2]; //Ensimmäinen on lyhyt linnoittautuminen ja toinen pitkä
    private boolean[] linnoittautuminenMusta = new boolean[2];
    
    private int viimeisinKaksoisaskelX; // Sotilaan ohestalyöntiä varten tallennetaan millä sarakkeella
    private int viimeisimmanKaksoisaskeleenAika; // ja millä hetkellä viimeisin sotilaan kaksoisaskel on tapahtunut
 
    private int[][] kuninkaidenSijainti = new int[2][2]; // Helpompi tutkia shakkaus, kun pidetään kirjaa kuninkaiden sijainneista
    
    // onkoShakissa poistettiin ainakin väliaikaisesti: tutkiOnkoShakkaus-metodin sijaan käytetään onkoPelaajaShakissa-metodia
////    private boolean onkoShakissa; 
    
    // Näitä en nyt ainakaan heti käytä, mutta nämä olisivat varmaan avuksi tekoälylle siirtojen testauksessa
    //int[][] sotilaanSiirrot = {{0, 1}, {0, 2}, {1, 1}, {-1, 1}};
    int[][] ratsunSiirrot = {{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
    
    int[][] linjasuunnat = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
    int[][] viistosuunnat = { {1, 1}, {1, -1}, {-1, -1}, {-1, 1} };
    
    
    
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
            // Kuinkahan lauta olisi järkevintä asettaa? Asetan sen kuitenkin suoraan.
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
            pelaaja = 1;
            puolisiirto = 1;
            alustaLinnoittautuminen();
////            onkoShakissa = false;
            kuninkaidenSijainti = new int[][]{ {4, 0}, {4, 7} };
        }
        
//////////        public void alustaTyhjaLauta() {
//////////            for (int i = 0; i < 8; i++) {
//////////                for (int j = 0; j < 8; j++) {
//////////                    lauta[i][j] = 0; 
//////////                }
//////////            }
//////////        }
//////////        
//////////        public void alustaKuninkaat() {
//////////            lauta[4][0] = 6;
//////////            lauta[4][7] = -6;
//////////        }
//////////        
//////////        public void alustaDaamit() {
//////////            lauta[3][0] = 5;
//////////            lauta[3][7] = -5;
//////////        }
//////////        
//////////        public void alustaTornit() {
//////////            lauta[0][0] = 4;
//////////            lauta[7][0] = 4;
//////////            lauta[0][7] = -4;
//////////            lauta[7][7] = -4;
//////////        }
//////////        
//////////        public void alustaLahetit() {
//////////            lauta[2][0] = 3;
//////////            lauta[5][0] = 3;
//////////            lauta[2][7] = -3;
//////////            lauta[5][7] = -3;
//////////        }
//////////        
//////////        public void alustaRatsut() {
//////////            lauta[1][0] = 2;
//////////            lauta[6][0] = 2;
//////////            lauta[1][7] = -2;
//////////            lauta[6][7] = -2;
//////////        }
//////////        public void alustaSotilaat() {
//////////            for (int i = 0; i < 8; i++) {
//////////                lauta[i][1] = 1;
//////////                lauta[i][6] = -1;
//////////            }
//////////        }
        
        public void alustaNappula(int nappula, int x, int y) {
            lauta[x][y] = pelaaja * nappula;
        }
        
        public void korotaNappula(int x, int y, int pelaaja) {
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
            pelaaja = 1;
        }
        
        public void alustaLinnoittautuminen() {
            for (int i = 0; i < 2; i++) {
                linnoittautuminenValkea[i] = true;
                linnoittautuminenMusta[i] = true;
            }
        }
        
        public void onkoLautaKelvollinen() {
            // Metodi testaa, onko pelilaudan asema kelvollinen pelattavaksi.
            // Kummallakin pelaajalla on yksi kuningas
            // Sotilaat sijaitsevat riveillä 2-7
            // Siirtovuoroa vailla olevaa kuningasta ei shakata
            // Siirtovuorossa olevalla puolella on laillisia siirtoja
        }
        
        // Aseman tutkimista
        // Jos lataa/asettaa satunnaisen aseman, niin silloin pitää tutkia aseman luonne
        // Pelin aikana pärjää, kun tutkii, miten kukin siirto vaikuttaa asemaan
//        public int[] etsiViholliskuningas(int pelaaja, int[][] asema) {
//
//        }
        
        public boolean onkoRuutuLaudalla(int x, int y) {
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                return true;
            }
            else {
                return false;
            }
        }
        
    // Tarkistaa, ovatko annetut koordinaatit laudalla
        // ja jos ovat, niin jatketaan siirrä-metodiin
    public void kelpaavatkoSyotetytRuudut(String ruutu1, String ruutu2) {
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
                    && rivit.indexOf(ruutu1.substring(1, 2)) != -1
                    && rivit.indexOf(ruutu2.substring(1, 2)) != -1) {
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
        
    
    // Metodi tutkii, onko joku ruutu vihollisen uhkaama
    // Ruudussa mahdollisesti olevaan nappulaan ei kiinnitetä huomiota, mutta katsotaan uhkaako (tai suojeleeko) sitä vihollisnappula.
    // Etsitään jokaisesta hyökkäyssuunnasta ensimmäinen nappula, jos sellaisia on, 
        // ja katsotaan onko se vihollinen, joka kykenee hyökkäämään kyseisestä ruudusta
    // Tutkittavia suuntia ovat
        // a) vaakarivit ja pystylinjat
        // b) kaikki viistorivit
        // c) ratsuasemat
    public int monestikoRuutuUhattu(int ruutuX, int ruutuY, int omaPelaaja) {
        int uhkaajienLkm = 0;
        int i;
        int nappula;
        // Tutkitaan uhat vaaka- ja pystysuunnissa:
        for (int n = 0; n < 4; n++) {
            i = 1;
            while ( onkoRuutuLaudalla(ruutuX + i * linjasuunnat[n][0], ruutuY + i * linjasuunnat[n][1]) == true) {
                nappula = lauta[ruutuX + i * linjasuunnat[n][0]][ruutuY + i * linjasuunnat[n][1]];
                if (nappula == 0) {
                    i++;
                }
                else {
                    if (nappula == omaPelaaja * -4
                            || nappula == omaPelaaja * -5
                            || (nappula == omaPelaaja * -6 && i == 1)) {
                        uhkaajienLkm++;
                    }
                    break;
                }
            }
        }
        // Viistosuunnat:
        // Nyt pitää tutkia lähetti, daami, kuningas ja sotilas
        // Sotilaasta huomioidaan, että etuviistossa olevista vain mustan sotilaat hyökkäävät, kun vuoro on valkealla
            // ja takaviistossa olevista vain valkean sotilaat hyökkäävät, kun vuoro on mustalla
        for (int n = 0; n < 4; n++) {
            i = 1;
            while (onkoRuutuLaudalla(ruutuX + i * viistosuunnat[n][0], ruutuY + i * viistosuunnat[n][1]) == true) {
                nappula = lauta[ruutuX + i * viistosuunnat[n][0]][ruutuY + i * viistosuunnat[n][1]];
                if (nappula == 0) {
                    i++;
                }
                else {
                    if (nappula == omaPelaaja * -3
                            || nappula == omaPelaaja * -5
                            || (nappula == omaPelaaja * -6 && i == 1)
                            || (nappula * omaPelaaja == -1 && viistosuunnat[n][1] == omaPelaaja && i == 1) ) {
                        uhkaajienLkm++;
                    }
                    break;
                }
            }
        }
        // Ratsun siirrot
        for (int j = 0; j < 8; j++) {
            if (onkoRuutuLaudalla(ruutuX + ratsunSiirrot[j][0], ruutuY + ratsunSiirrot[j][1]) == true) {
                nappula = lauta[ruutuX + ratsunSiirrot[j][0]][ruutuY + ratsunSiirrot[j][1]];
                if (nappula == omaPelaaja * -2) {
                    uhkaajienLkm++;
                }
            }
        }
        return uhkaajienLkm;
    }    
    
    
    // Ongelmana on, miten tutkia shakkaukset, matit ja patit, ja missä vaiheessa siirtoa...
        // Nyt ohjelma tutkii kertoo, jos vihollinen joutui shakkiin siirron lopussa
        // Ennen siirron toimeenpanoa pitäisi kuitenkin suorittaa tarkistus, ettei oma kuningas jäänyt tai joutunut shakkiin.
            // Sitten joudun lisäämään jokaiseen siirraNappulametodiin tarkistukset...
    
    // Tutkii, onko pelaajan oma kuningas uhattuna
    // Käytämme metodia siirtojen testauksessa, että jääkö kuningas shakatuksi
        // TÄYTYY TARKISTAA: siirraKuningas-metodin toimivuus, sillä se ei nyt toimi suunnitellusti...
        public boolean onkoPelaajaShakissa(int pelaaja, int[][] asema) { 
            int x;
            int y;
            if (pelaaja == 1) {
                x = kuninkaidenSijainti[0][0];
                y = kuninkaidenSijainti[0][1];
            }
            else {
                x = kuninkaidenSijainti[1][0];
                y = kuninkaidenSijainti[1][1];
            }
            if (monestikoRuutuUhattu(x,y,pelaaja) > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        
        // Laillisten siirtojen tutkimus pitäisi suunnitella jotenkin, jotta mattien ja pattien tunnistus toimisi
        // Asema on shakki, ja jos vastapuolella ei ole laillisia siirtoja, niin asema on myös matti.
        
        public void onkoAsemaMatti() {
            
        }
        
        // Jos asema ei ole shakki, mutta vastapuolella ei ole laillisia siirtoja, tilanne on patti (joka on tasapeli).
        public void onkoAsemaPatti() {
            
        }
               
////////         Tutkitaan oman vuoron päättyessä
////////         Jos on shakkaus, niin onkoShakissa = true
////////         Oikeastaan riittää tutkia a) shakkaako siirtynyt nappula b) paljastiko siirtynyt nappula takaansa shakin.
////////             Mahdollisesti jopa kaksoisshakki eli a ja b, jolloin vastustajan ainoa mahdollisuus on liikuttaa kuningastaan pakoon.
////////         Tutkitaan lisäksi onko asema jopa matti
//////        public boolean tutkiOnkoShakkaus(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
//////            
//////        }
//////        
//////        public boolean onkoPaljastusshakki(int pelaaja, int[][] asema) {
//////            return true;
//////        }
        
        
        
        public void siirra(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            if (lauta[lahtoruutuX][lahtoruutuY] * pelaaja > 0) {
//                System.out.println("Valitsemasi nappula on " + nappulat.charAt(lauta[lahtoruutuX][lahtoruutuY] + 6)+".\n");
//                System.out.println("Seuraavaksi yritämme liikuttaa sitä.");
                if (lauta[lahtoruutuX][lahtoruutuY] == 1 || lauta[lahtoruutuX][lahtoruutuY] == -1) {
                    siirraSotilas(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                else if (lauta[lahtoruutuX][lahtoruutuY] == 2 || lauta[lahtoruutuX][lahtoruutuY] == -2) {
                    siirraRatsu(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                else if (lauta[lahtoruutuX][lahtoruutuY] == 3 || lauta[lahtoruutuX][lahtoruutuY] == -3) {
                    siirraLahetti(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                else if (lauta[lahtoruutuX][lahtoruutuY] == 4 || lauta[lahtoruutuX][lahtoruutuY] == -4) {
                    siirraTorni(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                else if (lauta[lahtoruutuX][lahtoruutuY] == 5 || lauta[lahtoruutuX][lahtoruutuY] == -5) {
                    siirraDaami(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
                else if (lauta[lahtoruutuX][lahtoruutuY] == 6 || lauta[lahtoruutuX][lahtoruutuY] == -6) {
                    siirraKuningas(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY);
                }
            }
            else if (lauta[lahtoruutuX][lahtoruutuY] * pelaaja == 0) {
                System.out.println("Et valinnut nappulaa.");
            }
            else {
                System.out.println("Valitsemasi nappula ei ole sinun. Siirto ei ole mahdollinen");
            }
            tulostaLauta();
        }
        
        // Jokaisen siirron päättyessä päivitettävät tilastot
        public void paivitaSiirtotilastot() {
            pelaaja = pelaaja*-1;
            puolisiirto++;
        }
        
        // Tarkistaa, onko väli tyhjä. Tänne tulevista arvoista on jo tarkastettu, että ne ovat nappulan liikeradalla.
        // Ensin tarkistetaan x- ja y-koordinaattien erotukset ja otetaan maksimi
        // Tarkistetaan sitten, että jokainen ruutu välillä on tyhjä
        public boolean valiOnTyhja(int lahtoX, int lahtoY, int kohdeX, int kohdeY) {
            int laskuri = 0;
            for (int i = 1; i < Math.max(Math.abs(kohdeX - lahtoX), Math.abs(kohdeY - lahtoY)); i++) {
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
        
        // Ovatkohan nämä siirrä-metodien ehdot hieman vaikeaselkoisia?
        // Täytyy varmaan tehdä jokunen kuvaava apumetodi...
        public void siirraSotilas(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Sotilaan marssiminen eteenpäin
            if (kohderuutuX == lahtoruutuX) {
                // Sotilaan yksöisaskel
                // Tarkistetaan, että lähtöruutu on järkevä sotilaan puoli huomioiden
                // Tarkistetaan, yritetäänko sotilasta liikuttaa yksi ruutu eteenpäin
                // Tarkistetaan, että kohderuutu on tyhjä.
                if (((lahtoruutuY >= 1 && pelaaja == 1) || (lahtoruutuY <= 6 && pelaaja == -1))
                        && kohderuutuY == lahtoruutuY + 1 * pelaaja 
                        && lauta[lahtoruutuX][lahtoruutuY + pelaaja] == 0) {
                    // Ennen siirtoa tarkistamme laillisuuden
                    testilauta = lauta;
                    testilauta[lahtoruutuX][lahtoruutuY] = 0;
                    testilauta[kohderuutuX][kohderuutuY] = 1 * pelaaja;
                    if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                        lauta[lahtoruutuX][lahtoruutuY] = 0;
                        lauta[kohderuutuX][kohderuutuY] = 1 * pelaaja;
                        // Yksöisaskel voi johtaa myöskin sotilaankorottumiseen,
                        if ((kohderuutuY == 7 && pelaaja == 1) || (kohderuutuY == 0 && pelaaja == -1)) {
                            korotaNappula(kohderuutuX, kohderuutuY, pelaaja);
                        }
                        paivitaSiirtotilastot();
                    }
                    else {
                        System.out.println("Sotilaan siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                    }
                }
                // Sotilaan kaksoisaskel
                // Jos kaksoisaskel päätyy toisen sotilaan vierelle, niin ohestalyönti mahdollistuu...
                else if (((lahtoruutuY == 1 && pelaaja == 1) || (lahtoruutuY == 6 && pelaaja == -1))
                        && kohderuutuY == lahtoruutuY + 2 * pelaaja 
                        && lauta[lahtoruutuX][lahtoruutuY + pelaaja] == 0 
                        && lauta[lahtoruutuX][lahtoruutuY + 2 * pelaaja] == 0) {
                    // Tarkistamme laillisuuden
                    testilauta = lauta;
                    testilauta[lahtoruutuX][lahtoruutuY] = 0;
                    testilauta[kohderuutuX][kohderuutuY] = 1 * pelaaja;
                    if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                        lauta[lahtoruutuX][lahtoruutuY] = 0;
                        lauta[kohderuutuX][kohderuutuY] = 1 * pelaaja;
                        viimeisinKaksoisaskelX = kohderuutuX;
                        viimeisimmanKaksoisaskeleenAika = puolisiirto;
                        paivitaSiirtotilastot();
                    }
                    else {
                        System.out.println("Sotilaan siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                    }
                }
                else {
                    System.out.println("Sotilaan siirto ei ole mahdollinen.");
                }
            }
            // Vastustajan nappulan tavallinen lyöminen
            else if ((kohderuutuX == lahtoruutuX + 1 || kohderuutuX == lahtoruutuX - 1)
                    && kohderuutuY == lahtoruutuY + 1*pelaaja
                    && lauta[kohderuutuX][kohderuutuY] * pelaaja < 0) {
                testilauta = lauta;
                testilauta[lahtoruutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][kohderuutuY] = 1 * pelaaja;
                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 1*pelaaja;
                    // Sotilaan korottuminen on myös mahdollinen
                    if ((kohderuutuY == 7 && pelaaja == 1) || (kohderuutuY == 0 && pelaaja == -1)) {
                            korotaNappula(kohderuutuX, kohderuutuY, pelaaja);
                    }
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Sotilaan siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                }
            }
            // Ohestalyönti
            // Samat ehdot kuin tavallisessa lyönnissä, paitsi
                // a) Kohderuutu onkin tyhjä
                // b) Viereen on juuri liikkunut sotilas kaksoisaskeleella
            else if ((kohderuutuX == lahtoruutuX + 1 || kohderuutuX == lahtoruutuX - 1)
                    && kohderuutuY == lahtoruutuY + 1*pelaaja
                    && lauta[kohderuutuX][kohderuutuY] == 0
                    && puolisiirto - viimeisimmanKaksoisaskeleenAika == 1
                    && kohderuutuX == viimeisinKaksoisaskelX) {
                testilauta = lauta;
                testilauta[lahtoruutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][kohderuutuY] = 1 * pelaaja;
                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 1*pelaaja;
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Sotilaan siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                }
            }
            else {
                System.out.println("Sotilaan siirto ei ole mahdollinen.");
            }
        }
        
        public void siirraRatsu(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Tarkistetaan että ruudut sijaitsevat ratsun liikeradalla
            // Tarkistetaan, että kohderuudussa ei ole omaa nappulaa.
            if (Math.abs((kohderuutuX - lahtoruutuX) * (kohderuutuY - lahtoruutuY)) == 2 
                    && !(lauta[kohderuutuX][kohderuutuY] * pelaaja > 0)) {
                testilauta = lauta;
                testilauta[lahtoruutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][kohderuutuY] = 2*pelaaja;
                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 2*pelaaja;
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Ratsun siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                }
            }
            else {
                System.out.println("Ratsun siirto ei ole mahdollinen.");
            }
        }
            
        public void siirraLahetti(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Ovathan ruudut lähetin liikeradalla
            // Kohderuudussa ei ole omaa nappulaa. Tämä estää myös paikallaan pysymisen.
            // Ruudut lähtö- ja kohderuudun välisellä linjalla ovat tyhjiä
            if (Math.abs((kohderuutuX - lahtoruutuX)) == Math.abs(kohderuutuY - lahtoruutuY) 
                    && !(lauta[kohderuutuX][kohderuutuY] * pelaaja > 0)
                    && valiOnTyhja(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY) == true) {
                testilauta = lauta;
                testilauta[lahtoruutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][kohderuutuY] = 3*pelaaja;
                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 3*pelaaja;
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Lähetin siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                }
            }
            else {
                System.out.println("Lähetin siirto ei ole mahdollinen.");
            }
        }
        
        public void siirraTorni(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Ovathan ruudut tornin liikeradalla
            // Kohderuudussa ei ole omaa nappulaa. Tämä estää myös paikallaan pysymisen.
            // Ruudut lähtö- ja kohderuudun välisellä linjalla ovat tyhjiä
            if ( (Math.abs((kohderuutuX - lahtoruutuX)) == 0 || Math.abs(kohderuutuY - lahtoruutuY) == 0)
                    && !(lauta[kohderuutuX][kohderuutuY] * pelaaja > 0)
                    && valiOnTyhja(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY) == true) {
                testilauta = lauta;
                testilauta[lahtoruutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][kohderuutuY] = 4*pelaaja;
                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 4*pelaaja;
                    if (lahtoruutuX == 7 && pelaaja == 1) {
                        linnoittautuminenValkea[0] = false;
                    }
                    else if (lahtoruutuX == 7 && pelaaja == -1) {
                        linnoittautuminenMusta[0] = false;
                    }
                    else if (lahtoruutuX == 0 && pelaaja == 1) {
                        linnoittautuminenValkea[1] = false;
                    }
                    else if (lahtoruutuX == 0 && pelaaja == -1) {
                        linnoittautuminenMusta[1] = false;
                    }
                    paivitaSiirtotilastot();
                 }
                 else {
                     System.out.println("Tornin siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                 }   
            }
            else {
                System.out.println("Tornin siirto ei ole mahdollinen.");
            }
        }
        
        public void siirraDaami(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Ovathan ruudut daamin liikeradalla
            // Kohderuudussa ei ole omaa nappulaa. Tämä estää myös paikallaan pysymisen.
            // Ruudut lähtö- ja kohderuudun välisellä linjalla ovat tyhjiä
            if ( ((Math.abs((kohderuutuX - lahtoruutuX)) == 0 || Math.abs(kohderuutuY - lahtoruutuY) == 0)
                        || Math.abs((kohderuutuX - lahtoruutuX)) == Math.abs(kohderuutuY - lahtoruutuY))
                    && !(lauta[kohderuutuX][kohderuutuY] * pelaaja > 0)
                    && valiOnTyhja(lahtoruutuX, lahtoruutuY, kohderuutuX, kohderuutuY) == true) {
                testilauta = lauta;
                testilauta[lahtoruutuX][lahtoruutuY] = 0;
                testilauta[kohderuutuX][kohderuutuY] = 5*pelaaja;
                if (onkoPelaajaShakissa(pelaaja, testilauta) == false) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 5*pelaaja;
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Daamin siirto ei ole mahdollinen, koska kuningas jää uhatuksi.");
                }
            }
            else {
                System.out.println("Daamin siirto ei ole mahdollinen.");
            }
        }
        
        public void siirraKuningas(int lahtoruutuX, int lahtoruutuY, int kohderuutuX, int kohderuutuY) {
            // Ovathan ruudut kuninkaan liikeradalla. x- ja y- koordinaattien erotuksien maksimin itseisarvo on silloin 1.
            // Kohderuudussa ei ole omaa nappulaa.
            if ( Math.max(Math.abs(kohderuutuX - lahtoruutuX), Math.abs(kohderuutuY - lahtoruutuY)) == 1
                    && !(lauta[kohderuutuX][kohderuutuY] * pelaaja > 0) ) {
                // Siirron testaus vaatii säätöä! Pitäisi ehkä muuttaa metodien saamia syötteitä...
//                testilauta = lauta;
//                testilauta[lahtoruutuX][lahtoruutuY] = 0;
//                testilauta[kohderuutuX][kohderuutuY] = 6*pelaaja;
                if (//onkoPelaajaShakissa(pelaaja, testilauta) == false
                        monestikoRuutuUhattu(kohderuutuX, kohderuutuY, pelaaja) == 0
                        // &&   // tarkista, ettei kuningas liiku pitkän kantaman hyökkäyksen suunnassa
                        ) {
                    lauta[lahtoruutuX][lahtoruutuY] = 0;
                    lauta[kohderuutuX][kohderuutuY] = 6*pelaaja;
                    // Päivitetään tiedot kuninkaan koordinaateista ja linnoittautumismahdollisuuksien menetyksestä
                    if (pelaaja == 1) {
                        kuninkaidenSijainti[0][0] = kohderuutuX;
                        kuninkaidenSijainti[0][1] = kohderuutuY;
                        linnoittautuminenValkea[0] = false;
                        linnoittautuminenValkea[1] = false;
                    }
                    else {
                        kuninkaidenSijainti[1][0] = kohderuutuX;
                        kuninkaidenSijainti[1][1] = kohderuutuY;
                        linnoittautuminenMusta[0] = false;
                        linnoittautuminenMusta[1] = false;
                    }
                    paivitaSiirtotilastot();
                }
                else {
                    System.out.println("Kuninkaan siirto ei ole mahdollinen, koska kuningas jäisi uhatuksi.");
                }
            }
            // Linnoittautuminen, edellytykset
                // Linnoittaumismahdollisuudet ovat tallella (K:n ja kyseinen T eivät ole vielä liikkuneet)
                // Väli kuninkaasta torniin on tyhjä
                // Kuninkaan lähtö, läpikulku ja kohde-ruutuja ei uhata
            // Koodataan linnoittautumismahdollisuudet yksitellen
            // Ensin valkean lyhyt linnoittautuminen
            else if (lahtoruutuX == 4 && lahtoruutuY == 0
                    && kohderuutuX == 6 && kohderuutuY == 0
                    && linnoittautuminenValkea[0] == true
                    && valiOnTyhja(4, 0, 7, 0) 
                    && monestikoRuutuUhattu(4, 0, 1) == 0
                    && monestikoRuutuUhattu(5, 0, 1) == 0
                    && monestikoRuutuUhattu(6, 0, 1) == 0) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 6*pelaaja;
                lauta[7][0] = 0;
                lauta[5][0] = 4;
                kuninkaidenSijainti[0][0] = kohderuutuX;
                kuninkaidenSijainti[0][1] = kohderuutuY;
                linnoittautuminenValkea[0] = false;
                linnoittautuminenValkea[1] = false;
                paivitaSiirtotilastot();
            }
            // Sitten mustan lyhyt linnoittautuminen
            else if (lahtoruutuX == 4 && lahtoruutuY == 7
                    && kohderuutuX == 6 && kohderuutuY == 7
                    && linnoittautuminenMusta[0] == true
                    && valiOnTyhja(4, 7, 7, 7) 
                    && monestikoRuutuUhattu(4, 7, -1) == 0
                    && monestikoRuutuUhattu(5, 7, -1) == 0
                    && monestikoRuutuUhattu(6, 7, -1) == 0) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 6*pelaaja;
                lauta[7][7] = 0;
                lauta[5][7] = -4;
                kuninkaidenSijainti[1][0] = kohderuutuX;
                kuninkaidenSijainti[1][1] = kohderuutuY;
                linnoittautuminenMusta[0] = false;
                linnoittautuminenMusta[1] = false;
                paivitaSiirtotilastot();
            }
            // Valkean pitkä linnoittautuminen
            else if (lahtoruutuX == 4 && lahtoruutuY == 0
                    && kohderuutuX == 2 && kohderuutuY == 0
                    && linnoittautuminenValkea[1] == true
                    && valiOnTyhja(4, 0, 0, 0) 
                    && monestikoRuutuUhattu(4, 0, 1) == 0
                    && monestikoRuutuUhattu(3, 0, 1) == 0
                    && monestikoRuutuUhattu(2, 0, 1) == 0) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 6*pelaaja;
                lauta[0][0] = 0;
                lauta[3][0] = 4;
                kuninkaidenSijainti[0][0] = kohderuutuX;
                kuninkaidenSijainti[0][1] = kohderuutuY;
                linnoittautuminenValkea[0] = false;
                linnoittautuminenValkea[1] = false;
                paivitaSiirtotilastot();
            }
            // Mustan pitkä linnoittautuminen
            else if (lahtoruutuX == 4 && lahtoruutuY == 7
                    && kohderuutuX == 2 && kohderuutuY == 7
                    && linnoittautuminenValkea[1] == true
                    && valiOnTyhja(4, 7, 0, 7) 
                    && monestikoRuutuUhattu(4, 7, -1) == 0
                    && monestikoRuutuUhattu(3, 7, -1) == 0
                    && monestikoRuutuUhattu(2, 7, -1) == 0) {
                lauta[lahtoruutuX][lahtoruutuY] = 0;
                lauta[kohderuutuX][kohderuutuY] = 6*pelaaja;
                lauta[0][7] = 0;
                lauta[3][7] = -4;
                kuninkaidenSijainti[1][0] = kohderuutuX;
                kuninkaidenSijainti[1][1] = kohderuutuY;
                linnoittautuminenMusta[0] = false;
                linnoittautuminenMusta[1] = false;
                paivitaSiirtotilastot();
            }
            else {
                System.out.println("Kuninkaan siirto ei ole mahdollinen.");
            }
        }

        // Tulostetaan pelilauta sopivasta katselusuunnasta.
        public void tulostaLauta() {
            // Valkean näkökulmasta ilmeisesti näin:
            for (int j = 7; j >= 0; j--) {
                for (int i = 0; i < 8; i++) {
                    System.out.print(nappulat.charAt(lauta[i][j] + 6));
                }
                System.out.println();
            }
            System.out.println("Siirto "+ Math.round((puolisiirto + 1)/ 2));
//            System.out.println("Puolisiirto on " + puolisiirto);
            System.out.println("Siirtovuorossa on " +  pelaaja);
            if (onkoPelaajaShakissa(pelaaja, lauta) == true) {
                System.out.println("Shakki!");
            }
        }

    public int[][] getLauta() {
        return lauta;
    }  
    
    public int getPelaaja() {
        return pelaaja;
    }
    
    public int getPuolisiirto() {
        return puolisiirto;
    }
    
    public boolean[] getLinnoittautuminenValkea() {
        return linnoittautuminenValkea;
    }
    
    public boolean[] getLinnoittautuminenMusta() {
        return linnoittautuminenMusta;
    }
    
    public int[][] getKuninkaidenSijainti() {
        return kuninkaidenSijainti;
    }
    
    public int getNappula(int x, int y) {
        return lauta[x][y];
    }
    
    public int getMonestikoRuutuUhattu(int x, int y, int pelaaja) {
        return monestikoRuutuUhattu(x, y, pelaaja);
    }
    
    public boolean getOnkoRuutuLaudalla(int x, int y) {
        return onkoRuutuLaudalla(x,y);
    }
    
    public boolean getValiOnTyhja(int lahtoX, int lahtoY, int kohdeX, int kohdeY) {
        return valiOnTyhja(lahtoX, lahtoY, kohdeX, kohdeY);
    }
}
