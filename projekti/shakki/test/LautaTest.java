import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import shakki.Lauta;


public class LautaTest {
    
    public LautaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void alustaLautaAsettaaNappulat() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[][] alkuasema = new int[][]{ {4, 1, 0, 0, 0, 0, -1, -4}, 
                {2, 1, 0, 0, 0, 0, -1, -2}, 
                {3, 1, 0, 0, 0, 0, -1, -3}, 
                {5, 1, 0, 0, 0, 0, -1, -5}, 
                {6, 1, 0, 0, 0, 0, -1, -6}, 
                {3, 1, 0, 0, 0, 0, -1, -3}, 
                {2, 1, 0, 0, 0, 0, -1, -2}, 
                {4, 1, 0, 0, 0, 0, -1, -4} };
        assertArrayEquals(alkuasema, lauta.getLauta());
    }
    
    @Test
    public void alustaLautaAsettaaValkealleVuoron() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        assertEquals(1, lauta.getPelaaja());
    }
    
    @Test
    public void alustaLautaAsettaaPuolisiirtolaskurinYhdeksi() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        assertEquals(1, lauta.getPuolisiirto());
    }
    
    @Test
    public void alustaLautaAsettaaValkeanLinnoittaumismahdollisuudet() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        boolean[] linnoittautuminenValkea = {true, true};
        for (int i = 0; i < 2; i++) {
            assertTrue(linnoittautuminenValkea[i] == lauta.getLinnoittautuminenValkea()[i]);
        }
    }
    
    @Test
    public void alustaLautaAsettaaMustanLinnoittaumismahdollisuudet() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        boolean[] linnoittautuminenMusta = {true, true};
        for (int i = 0; i < 2; i++) {
            assertTrue(linnoittautuminenMusta[i] == lauta.getLinnoittautuminenMusta()[i]);
        }
    }
    
    @Test
    public void alustaLautaAsettaaKuninkaidenSijainnit() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        int[][] kuninkaat = new int[][]{ {4, 0}, {4, 7} };
        assertArrayEquals(kuninkaat, lauta.getKuninkaidenSijainti());
    }
    
////    @Test
////    public void alustaTyhjaLautaAsettaaTyhjanLaudan() {
////        Lauta lauta = new Lauta();
////        lauta.alustaTyhjaLauta();
////        int[][] tyhjaLauta = new int[][]{ {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0}, 
////                {0, 0, 0, 0, 0, 0, 0, 0} };
////        assertArrayEquals(tyhjaLauta, lauta.getLauta());
////    }
    
    @Test
    public void alustaNappulaAlustaaNappulanKohteeseen() {
        Lauta lauta = new Lauta();
        lauta.alustaNappula(2, 4, 5);
        assertEquals(2, lauta.getNappula(4,5));
    }
    
    @Test
    public void onkoRuutuLaudallaToimii() {
        Lauta lauta = new Lauta();
        assertTrue(false == lauta.getOnkoRuutuLaudalla(-1,4));
        assertTrue(false == lauta.getOnkoRuutuLaudalla(0,-3));
        assertTrue(false == lauta.getOnkoRuutuLaudalla(-2,8));
        assertTrue(true == lauta.getOnkoRuutuLaudalla(0,0));
        assertTrue(true == lauta.getOnkoRuutuLaudalla(1,4));
        assertTrue(true == lauta.getOnkoRuutuLaudalla(7,7));
        assertTrue(false == lauta.getOnkoRuutuLaudalla(8,4));
    }
    
    @Test
    public void monestikoRuutuUhattuAntaaAlkuasemassaOikeitaArvoja() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        assertEquals(0, lauta.getMonestikoRuutuUhattu(1,1,1));
        assertEquals(0, lauta.getMonestikoRuutuUhattu(3,4,1));
        assertEquals(0, lauta.getMonestikoRuutuUhattu(7,7,1));
        assertEquals(1, lauta.getMonestikoRuutuUhattu(6,7,1));
        assertEquals(2, lauta.getMonestikoRuutuUhattu(0,5,1));
        assertEquals(4, lauta.getMonestikoRuutuUhattu(4,6,1));
        assertEquals(0, lauta.getMonestikoRuutuUhattu(6,7,-1));
        assertEquals(2, lauta.getMonestikoRuutuUhattu(0,2,-1));
        assertEquals(4, lauta.getMonestikoRuutuUhattu(4,1,-1));
    }
    
    @Test
    public void paivitaSiirtotilastotPaivittaaPuolisiirrot() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        lauta.paivitaSiirtotilastot();
        assertEquals(2, lauta.getPuolisiirto());
        lauta.paivitaSiirtotilastot();
        assertEquals(3, lauta.getPuolisiirto());
    }
    
    @Test
    public void paivitaSiirtotilastotPaivittaaPelaajan() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        lauta.paivitaSiirtotilastot();
        assertEquals(-1, lauta.getPelaaja());
        lauta.paivitaSiirtotilastot();
        assertEquals(1, lauta.getPelaaja());
    }
    
    @Test
    public void valiOnTyhjaAntaaOikeitaArvojaAlkuasemassa() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        assertTrue(false == lauta.getValiOnTyhja(0,0,6,6));
        assertTrue(true == lauta.getValiOnTyhja(1,1,6,6));
        assertTrue(false == lauta.getValiOnTyhja(1,1,7,7));
        assertTrue(false == lauta.getValiOnTyhja(0,6,3,6));
        assertTrue(true == lauta.getValiOnTyhja(5,2,2,2));
        assertTrue(false == lauta.getValiOnTyhja(1,6,7,0));
        assertTrue(true == lauta.getValiOnTyhja(1,6,6,1));
    }
    
}
