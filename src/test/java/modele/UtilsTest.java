package modele;

import modele.Utils;
import java.util.Arrays;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author heynerr
 */
public class UtilsTest extends TestCase {
    
    public UtilsTest() {
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

    /**
     * Test of getCase method, of class Grille.
     */
    @Test
    @SuppressWarnings("empty-statement")
    public void testCalculOuLogique2Candidats() {
        boolean tab1 [] = {true, true, false, false, false, false, false, false, false, false};
        boolean tab2 [] = {true, false, true, false, false, false, false, false, false, false};
        boolean res  [] = {true, true, true, false, false, false, false, false, false, false};
        //System.out.println("expected : "+Arrays.toString(res));
        //System.out.println("Calcul   : "+Arrays.toString(Utils.calculOuLogique2Candidats(tab1, tab2)));
        //assertEquals(Arrays.toString(res),Arrays.toString(Utils.calculOuLogique2Candidats(tab1, tab2)));
        assertTrue(Arrays.equals(res,Utils.calculOuLogique2Candidats(tab1, tab2)));
    }
            
    @Test
    public void testCalculNombreTriplettes() {
        System.out.println("calculNombreTriplettes");
        assertEquals(3, Utils.calculNombreTriplettes(4));
        assertEquals(6, Utils.calculNombreTriplettes(5));
        assertEquals(10, Utils.calculNombreTriplettes(6));
        assertEquals(15, Utils.calculNombreTriplettes(7));
        assertEquals(21, Utils.calculNombreTriplettes(8));
        assertEquals(28, Utils.calculNombreTriplettes(9));
    }
    @Test
    public void testCalculXsearch() {
        System.out.println("calculNumeroXsearch");
        assertEquals(0, Utils.calculXsearch(1));
        assertEquals(1, Utils.calculXsearch(2));
        assertEquals(2, Utils.calculXsearch(3));
        assertEquals(3, Utils.calculXsearch(4));
        assertEquals(4, Utils.calculXsearch(5));
        assertEquals(5, Utils.calculXsearch(6));
        assertEquals(6, Utils.calculXsearch(7));
        assertEquals(7, Utils.calculXsearch(8));
        assertEquals(8, Utils.calculXsearch(9));
        assertEquals(0, Utils.calculXsearch(10));
        assertEquals(1, Utils.calculXsearch(11));
        assertEquals(2, Utils.calculXsearch(12));
        assertEquals(3, Utils.calculXsearch(13));
        assertEquals(4, Utils.calculXsearch(14));
        assertEquals(5, Utils.calculXsearch(15));
        assertEquals(6, Utils.calculXsearch(16));
        assertEquals(7, Utils.calculXsearch(17));
        assertEquals(8, Utils.calculXsearch(18));
        assertEquals(0, Utils.calculXsearch(19));
        assertEquals(1, Utils.calculXsearch(20));
        assertEquals(2, Utils.calculXsearch(21));
        assertEquals(3, Utils.calculXsearch(22));
        assertEquals(4, Utils.calculXsearch(23));
        assertEquals(5, Utils.calculXsearch(24));
        assertEquals(6, Utils.calculXsearch(25));
        assertEquals(7, Utils.calculXsearch(26));
        assertEquals(8, Utils.calculXsearch(27));
        assertEquals(0, Utils.calculXsearch(28));
        assertEquals(1, Utils.calculXsearch(29));
        assertEquals(2, Utils.calculXsearch(30));
        assertEquals(3, Utils.calculXsearch(31));
        assertEquals(4, Utils.calculXsearch(32));
        assertEquals(5, Utils.calculXsearch(33));
        assertEquals(6, Utils.calculXsearch(34));
        assertEquals(7, Utils.calculXsearch(35));
        assertEquals(8, Utils.calculXsearch(36));
        assertEquals(0, Utils.calculXsearch(37));
        assertEquals(1, Utils.calculXsearch(38));
        assertEquals(2, Utils.calculXsearch(39));
        assertEquals(3, Utils.calculXsearch(40));
        assertEquals(4, Utils.calculXsearch(41));
        assertEquals(5, Utils.calculXsearch(42));
        assertEquals(6, Utils.calculXsearch(43));
        assertEquals(7, Utils.calculXsearch(44));
        assertEquals(8, Utils.calculXsearch(45));
        assertEquals(0, Utils.calculXsearch(46));
        assertEquals(1, Utils.calculXsearch(47));
        assertEquals(2, Utils.calculXsearch(48));
        assertEquals(3, Utils.calculXsearch(49));
        assertEquals(4, Utils.calculXsearch(50));
        assertEquals(5, Utils.calculXsearch(51));
        assertEquals(6, Utils.calculXsearch(52));
        assertEquals(7, Utils.calculXsearch(53));
        assertEquals(8, Utils.calculXsearch(54));
        assertEquals(0, Utils.calculXsearch(55));
        assertEquals(1, Utils.calculXsearch(56));
        assertEquals(2, Utils.calculXsearch(57));
        assertEquals(3, Utils.calculXsearch(58));
        assertEquals(4, Utils.calculXsearch(59));
        assertEquals(5, Utils.calculXsearch(60));
        assertEquals(6, Utils.calculXsearch(61));
        assertEquals(7, Utils.calculXsearch(62));
        assertEquals(8, Utils.calculXsearch(63));
        assertEquals(0, Utils.calculXsearch(64));
        assertEquals(1, Utils.calculXsearch(65));
        assertEquals(2, Utils.calculXsearch(66));
        assertEquals(3, Utils.calculXsearch(67));
        assertEquals(4, Utils.calculXsearch(68));
        assertEquals(5, Utils.calculXsearch(69));
        assertEquals(6, Utils.calculXsearch(70));
        assertEquals(7, Utils.calculXsearch(71));
        assertEquals(8, Utils.calculXsearch(72));
        assertEquals(0, Utils.calculXsearch(73));
        assertEquals(1, Utils.calculXsearch(74));
        assertEquals(2, Utils.calculXsearch(75));
        assertEquals(3, Utils.calculXsearch(76));
        assertEquals(4, Utils.calculXsearch(77));
        assertEquals(5, Utils.calculXsearch(78));
        assertEquals(6, Utils.calculXsearch(79));
        assertEquals(7, Utils.calculXsearch(80));
        assertEquals(8, Utils.calculXsearch(81));

    }
    
    @Test
    public void testCalculYsearch() {
        System.out.println("calculYsearch");
        assertEquals(0, Utils.calculYsearch(1));
        assertEquals(0, Utils.calculYsearch(2));
        assertEquals(0, Utils.calculYsearch(3));
        assertEquals(0, Utils.calculYsearch(4));
        assertEquals(0, Utils.calculYsearch(5));
        assertEquals(0, Utils.calculYsearch(6));
        assertEquals(0, Utils.calculYsearch(7));
        assertEquals(0, Utils.calculYsearch(8));
        assertEquals(0, Utils.calculYsearch(9));
        assertEquals(1, Utils.calculYsearch(10));
        assertEquals(1, Utils.calculYsearch(11));
        assertEquals(1, Utils.calculYsearch(12));
        assertEquals(1, Utils.calculYsearch(13));
        assertEquals(1, Utils.calculYsearch(14));
        assertEquals(1, Utils.calculYsearch(15));
        assertEquals(1, Utils.calculYsearch(16));
        assertEquals(1, Utils.calculYsearch(17));
        assertEquals(1, Utils.calculYsearch(18));
        assertEquals(2, Utils.calculYsearch(19));
        assertEquals(2, Utils.calculYsearch(20));
        assertEquals(2, Utils.calculYsearch(21));
        assertEquals(2, Utils.calculYsearch(22));
        assertEquals(2, Utils.calculYsearch(23));
        assertEquals(2, Utils.calculYsearch(24));
        assertEquals(2, Utils.calculYsearch(25));
        assertEquals(2, Utils.calculYsearch(26));
        assertEquals(2, Utils.calculYsearch(27));
        assertEquals(3, Utils.calculYsearch(28));
        assertEquals(3, Utils.calculYsearch(29));
        assertEquals(3, Utils.calculYsearch(30));
        assertEquals(3, Utils.calculYsearch(31));
        assertEquals(3, Utils.calculYsearch(32));
        assertEquals(3, Utils.calculYsearch(33));
        assertEquals(3, Utils.calculYsearch(34));
        assertEquals(3, Utils.calculYsearch(35));
        assertEquals(3, Utils.calculYsearch(36));
        assertEquals(4, Utils.calculYsearch(37));
        assertEquals(4, Utils.calculYsearch(38));
        assertEquals(4, Utils.calculYsearch(39));
        assertEquals(4, Utils.calculYsearch(40));
        assertEquals(4, Utils.calculYsearch(41));
        assertEquals(4, Utils.calculYsearch(42));
        assertEquals(4, Utils.calculYsearch(43));
        assertEquals(4, Utils.calculYsearch(44));
        assertEquals(4, Utils.calculYsearch(45));
        assertEquals(5, Utils.calculYsearch(46));
        assertEquals(5, Utils.calculYsearch(47));
        assertEquals(5, Utils.calculYsearch(48));
        assertEquals(5, Utils.calculYsearch(49));
        assertEquals(5, Utils.calculYsearch(50));
        assertEquals(5, Utils.calculYsearch(51));
        assertEquals(5, Utils.calculYsearch(52));
        assertEquals(5, Utils.calculYsearch(53));
        assertEquals(5, Utils.calculYsearch(54));
        assertEquals(6, Utils.calculYsearch(55));
        assertEquals(6, Utils.calculYsearch(56));
        assertEquals(6, Utils.calculYsearch(57));
        assertEquals(6, Utils.calculYsearch(58));
        assertEquals(6, Utils.calculYsearch(59));
        assertEquals(6, Utils.calculYsearch(60));
        assertEquals(6, Utils.calculYsearch(61));
        assertEquals(6, Utils.calculYsearch(62));
        assertEquals(6, Utils.calculYsearch(63));
        assertEquals(7, Utils.calculYsearch(64));
        assertEquals(7, Utils.calculYsearch(65));
        assertEquals(7, Utils.calculYsearch(66));
        assertEquals(7, Utils.calculYsearch(67));
        assertEquals(7, Utils.calculYsearch(68));
        assertEquals(7, Utils.calculYsearch(69));
        assertEquals(7, Utils.calculYsearch(70));
        assertEquals(7, Utils.calculYsearch(71));
        assertEquals(7, Utils.calculYsearch(72));
        assertEquals(8, Utils.calculYsearch(73));
        assertEquals(8, Utils.calculYsearch(74));
        assertEquals(8, Utils.calculYsearch(75));
        assertEquals(8, Utils.calculYsearch(76));
        assertEquals(8, Utils.calculYsearch(77));
        assertEquals(8, Utils.calculYsearch(78));
        assertEquals(8, Utils.calculYsearch(79));
        assertEquals(8, Utils.calculYsearch(80));
        assertEquals(8, Utils.calculYsearch(81));

    }
    @Test
    public void testCalculNumeroRegion() {
        System.out.println("calculNumeroRegion");
        assertEquals(1, Utils.calculNumeroRegion(1));
        assertEquals(1, Utils.calculNumeroRegion(2));
        assertEquals(1, Utils.calculNumeroRegion(3));
        assertEquals(2, Utils.calculNumeroRegion(4));
        assertEquals(2, Utils.calculNumeroRegion(5));
        assertEquals(2, Utils.calculNumeroRegion(6));
        assertEquals(3, Utils.calculNumeroRegion(7));
        assertEquals(3, Utils.calculNumeroRegion(8));
        assertEquals(3, Utils.calculNumeroRegion(9));
        assertEquals(1, Utils.calculNumeroRegion(10));
        assertEquals(1, Utils.calculNumeroRegion(11));
        assertEquals(1, Utils.calculNumeroRegion(12));
        assertEquals(2, Utils.calculNumeroRegion(13));
        assertEquals(2, Utils.calculNumeroRegion(14));
        assertEquals(2, Utils.calculNumeroRegion(15));
        assertEquals(3, Utils.calculNumeroRegion(16));
        assertEquals(3, Utils.calculNumeroRegion(17));
        assertEquals(3, Utils.calculNumeroRegion(18));
        assertEquals(1, Utils.calculNumeroRegion(19));
        assertEquals(1, Utils.calculNumeroRegion(20));
        assertEquals(1, Utils.calculNumeroRegion(21));
        assertEquals(2, Utils.calculNumeroRegion(22));
        assertEquals(2, Utils.calculNumeroRegion(23));
        assertEquals(2, Utils.calculNumeroRegion(24));
        assertEquals(3, Utils.calculNumeroRegion(25));
        assertEquals(3, Utils.calculNumeroRegion(26));
        assertEquals(3, Utils.calculNumeroRegion(27));
        assertEquals(4, Utils.calculNumeroRegion(28));
        assertEquals(4, Utils.calculNumeroRegion(29));
        assertEquals(4, Utils.calculNumeroRegion(30));
        assertEquals(5, Utils.calculNumeroRegion(31));
        assertEquals(5, Utils.calculNumeroRegion(32));
        assertEquals(5, Utils.calculNumeroRegion(33));
        assertEquals(6, Utils.calculNumeroRegion(34));
        assertEquals(6, Utils.calculNumeroRegion(35));
        assertEquals(6, Utils.calculNumeroRegion(36));
        assertEquals(4, Utils.calculNumeroRegion(37));
        assertEquals(4, Utils.calculNumeroRegion(38));
        assertEquals(4, Utils.calculNumeroRegion(39));
        assertEquals(5, Utils.calculNumeroRegion(40));
        assertEquals(5, Utils.calculNumeroRegion(41));
        assertEquals(5, Utils.calculNumeroRegion(42));
        assertEquals(6, Utils.calculNumeroRegion(43));
        assertEquals(6, Utils.calculNumeroRegion(44));
        assertEquals(6, Utils.calculNumeroRegion(45));
        assertEquals(4, Utils.calculNumeroRegion(46));
        assertEquals(4, Utils.calculNumeroRegion(47));
        assertEquals(4, Utils.calculNumeroRegion(48));
        assertEquals(5, Utils.calculNumeroRegion(49));
        assertEquals(5, Utils.calculNumeroRegion(50));
        assertEquals(5, Utils.calculNumeroRegion(51));
        assertEquals(6, Utils.calculNumeroRegion(52));
        assertEquals(6, Utils.calculNumeroRegion(53));
        assertEquals(6, Utils.calculNumeroRegion(54));
        assertEquals(7, Utils.calculNumeroRegion(55));
        assertEquals(7, Utils.calculNumeroRegion(56));
        assertEquals(7, Utils.calculNumeroRegion(57));
        assertEquals(8, Utils.calculNumeroRegion(58));
        assertEquals(8, Utils.calculNumeroRegion(59));
        assertEquals(8, Utils.calculNumeroRegion(60));
        assertEquals(9, Utils.calculNumeroRegion(61));
        assertEquals(9, Utils.calculNumeroRegion(62));
        assertEquals(9, Utils.calculNumeroRegion(63));
        assertEquals(7, Utils.calculNumeroRegion(64));
        assertEquals(7, Utils.calculNumeroRegion(65));
        assertEquals(7, Utils.calculNumeroRegion(66));
        assertEquals(8, Utils.calculNumeroRegion(67));
        assertEquals(8, Utils.calculNumeroRegion(68));
        assertEquals(8, Utils.calculNumeroRegion(69));
        assertEquals(9, Utils.calculNumeroRegion(70));
        assertEquals(9, Utils.calculNumeroRegion(71));
        assertEquals(9, Utils.calculNumeroRegion(72));
        assertEquals(7, Utils.calculNumeroRegion(73));
        assertEquals(7, Utils.calculNumeroRegion(74));
        assertEquals(7, Utils.calculNumeroRegion(75));
        assertEquals(8, Utils.calculNumeroRegion(76));
        assertEquals(8, Utils.calculNumeroRegion(77));
        assertEquals(8, Utils.calculNumeroRegion(78));
        assertEquals(9, Utils.calculNumeroRegion(79));
        assertEquals(9, Utils.calculNumeroRegion(80));
        assertEquals(9, Utils.calculNumeroRegion(81));

    }
}
