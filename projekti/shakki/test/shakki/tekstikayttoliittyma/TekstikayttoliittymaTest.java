/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package shakki.tekstikayttoliittyma;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mtmlauri
 */
public class TekstikayttoliittymaTest {
    
    public TekstikayttoliittymaTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void syoteKelvollinenToimii() {
        Tekstikayttoliittyma kayttis = new Tekstikayttoliittyma();
        assertTrue(false == kayttis.syoteKelvollinen("af3"));
        assertTrue(false == kayttis.syoteKelvollinen("F9"));
        assertTrue(false == kayttis.syoteKelvollinen("-1"));
        assertTrue(false == kayttis.syoteKelvollinen("a0"));
        assertTrue(true == kayttis.syoteKelvollinen("A1"));
        assertTrue(true == kayttis.syoteKelvollinen("h8"));
        assertTrue(true == kayttis.syoteKelvollinen("f5"));
    }
}
