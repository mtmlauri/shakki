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
        assertEquals(1, lauta.getVuoro());
    }
    
    @Test
    public void alustaLautaAsettaaPuolisiirtolaskurinYhdeksi() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        assertEquals(1, lauta.getPuolisiirto());
    }
    
    @Test
    public void alustaLautaAsettaaLinnoittaumismahdollisuudet() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        boolean[] linnoittautuminen = {true, true, true, true};
        for (int i = 0; i < 4; i++) {
            assertTrue(linnoittautuminen[i] == lauta.getLinnoittautuminen()[i]);
        }
    }
    
    @Test
    public void alustaLautaEiAsetaValkeaaShakkiin() {
        Lauta lauta = new Lauta();
        lauta.alustaLauta();
        assertTrue(lauta.getOnkoShakissa() == false);
    }
    
    @Test
    public void alustaTyhjaLautaAsettaaTyhjanLaudan() {
        Lauta lauta = new Lauta();
        lauta.alustaTyhjaLauta();
        int[][] tyhjaLauta = new int[][]{ {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0}, 
                {0, 0, 0, 0, 0, 0, 0, 0} };
        assertArrayEquals(tyhjaLauta, lauta.getLauta());
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
    
}
