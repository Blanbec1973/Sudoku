package model;

import model.grille.CandidatsCase;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {
    
    @Test
    void testCalculOuLogique2Candidats() {
        boolean[] tab1 = {true, true, false, false, false, false, false, false, false, false};
        boolean[] tab2 = {true, false, true, false, false, false, false, false, false, false};
        boolean[] res = {true, true, true, false, false, false, false, false, false, false};
        assertArrayEquals(res, Utils.calculOuLogique2Candidats(tab1, tab2));
    }
    @Test
    void testCalculEtLogique2Candidats() {
        boolean[] tab1 = {false, true, false, true, false, false, false, false, false, false};
        CandidatsCase c1 = new CandidatsCase(tab1);
        boolean[] tab2 = {false, false, true, true, false, false, false, false, false, false};
        CandidatsCase c2 = new CandidatsCase(tab2);
        boolean[] res = {false, false, false, true, false, false, false, false, false, false};
        CandidatsCase resC = new CandidatsCase(res);
        assertArrayEquals(resC.getCandidats(), Utils.calculEtLogique2Candidats(c1, c2).getCandidats());
    }
            
    @Test
    void testCalculNombreTriplettes() {
        assertEquals(3, Utils.calculNombreTriplettes(4));
        assertEquals(6, Utils.calculNombreTriplettes(5));
        assertEquals(10, Utils.calculNombreTriplettes(6));
        assertEquals(15, Utils.calculNombreTriplettes(7));
        assertEquals(21, Utils.calculNombreTriplettes(8));
        assertEquals(28, Utils.calculNombreTriplettes(9));
    }
    
    @Test
    void testCalculXsearch1a25() {
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
    }
    
    @Test
    void testCalculXsearch26a50() {
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
    }
    
    @Test
    void testCalculXsearch51a75() {
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
    }
    
    @Test
    void testCalculXsearch76a81() {
        assertEquals(3, Utils.calculXsearch(76));
        assertEquals(4, Utils.calculXsearch(77));
        assertEquals(5, Utils.calculXsearch(78));
        assertEquals(6, Utils.calculXsearch(79));
        assertEquals(7, Utils.calculXsearch(80));
        assertEquals(8, Utils.calculXsearch(81));
    }
    
    @Test
    void testCalculYsearch1a25() {
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
    }
    
    @Test
    void testCalculYsearch26a50() {
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
    }
    
    @Test
    void testCalculYsearch51a75() {
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
    }
    
    @Test
    void testCalculYsearch76a81() {
        assertEquals(8, Utils.calculYsearch(76));
        assertEquals(8, Utils.calculYsearch(77));
        assertEquals(8, Utils.calculYsearch(78));
        assertEquals(8, Utils.calculYsearch(79));
        assertEquals(8, Utils.calculYsearch(80));
        assertEquals(8, Utils.calculYsearch(81));
    }
    
    @Test
    void testCalculNumeroRegion1a25() {
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
    }
    
    @Test
    void testCalculNumeroRegion26a50() {
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
    }
    
    @Test
    void testCalculNumeroRegion51a75() {
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
    }
    
    @Test
    void testCalculNumeroRegion76a81() {
        assertEquals(8, Utils.calculNumeroRegion(76));
        assertEquals(8, Utils.calculNumeroRegion(77));
        assertEquals(8, Utils.calculNumeroRegion(78));
        assertEquals(9, Utils.calculNumeroRegion(79));
        assertEquals(9, Utils.calculNumeroRegion(80));
        assertEquals(9, Utils.calculNumeroRegion(81));
    }
    
    @Test
    void testCalculNumCase1a25() {
    	  assertEquals(1, Utils.calculNumCase(0,0));
    	  assertEquals(2, Utils.calculNumCase(1,0));
    	  assertEquals(3, Utils.calculNumCase(2,0));
    	  assertEquals(4, Utils.calculNumCase(3,0));
    	  assertEquals(5, Utils.calculNumCase(4,0));
    	  assertEquals(6, Utils.calculNumCase(5,0));
    	  assertEquals(7, Utils.calculNumCase(6,0));
    	  assertEquals(8, Utils.calculNumCase(7,0));
    	  assertEquals(9, Utils.calculNumCase(8,0));
    	  assertEquals(10, Utils.calculNumCase(0,1));
    	  assertEquals(11, Utils.calculNumCase(1,1));
    	  assertEquals(12, Utils.calculNumCase(2,1));
    	  assertEquals(13, Utils.calculNumCase(3,1));
    	  assertEquals(14, Utils.calculNumCase(4,1));
    	  assertEquals(15, Utils.calculNumCase(5,1));
    	  assertEquals(16, Utils.calculNumCase(6,1));
    	  assertEquals(17, Utils.calculNumCase(7,1));
    	  assertEquals(18, Utils.calculNumCase(8,1));
    	  assertEquals(19, Utils.calculNumCase(0,2));
    	  assertEquals(20, Utils.calculNumCase(1,2));
    	  assertEquals(21, Utils.calculNumCase(2,2));
    	  assertEquals(22, Utils.calculNumCase(3,2));
    	  assertEquals(23, Utils.calculNumCase(4,2));
    	  assertEquals(24, Utils.calculNumCase(5,2));
    	  assertEquals(25, Utils.calculNumCase(6,2));
    }
    
    @Test
    void testCalculNumCase26a50() {
    	  assertEquals(26, Utils.calculNumCase(7,2));
    	  assertEquals(27, Utils.calculNumCase(8,2));
    	  assertEquals(28, Utils.calculNumCase(0,3));
    	  assertEquals(29, Utils.calculNumCase(1,3));
    	  assertEquals(30, Utils.calculNumCase(2,3));
    	  assertEquals(31, Utils.calculNumCase(3,3));
    	  assertEquals(32, Utils.calculNumCase(4,3));
    	  assertEquals(33, Utils.calculNumCase(5,3));
    	  assertEquals(34, Utils.calculNumCase(6,3));
    	  assertEquals(35, Utils.calculNumCase(7,3));
    	  assertEquals(36, Utils.calculNumCase(8,3));
    	  assertEquals(37, Utils.calculNumCase(0,4));
    	  assertEquals(38, Utils.calculNumCase(1,4));
    	  assertEquals(39, Utils.calculNumCase(2,4));
    	  assertEquals(40, Utils.calculNumCase(3,4));
    	  assertEquals(41, Utils.calculNumCase(4,4));
    	  assertEquals(42, Utils.calculNumCase(5,4));
    	  assertEquals(43, Utils.calculNumCase(6,4));
    	  assertEquals(44, Utils.calculNumCase(7,4));
    	  assertEquals(45, Utils.calculNumCase(8,4));
    	  assertEquals(46, Utils.calculNumCase(0,5));
    	  assertEquals(47, Utils.calculNumCase(1,5));
    	  assertEquals(48, Utils.calculNumCase(2,5));
    	  assertEquals(49, Utils.calculNumCase(3,5));
    	  assertEquals(50, Utils.calculNumCase(4,5));
    }
    
    @Test
    void testCalculNumCase51a75() {
    	  assertEquals(51, Utils.calculNumCase(5,5));
    	  assertEquals(52, Utils.calculNumCase(6,5));
    	  assertEquals(53, Utils.calculNumCase(7,5));
    	  assertEquals(54, Utils.calculNumCase(8,5));
    	  assertEquals(55, Utils.calculNumCase(0,6));
    	  assertEquals(56, Utils.calculNumCase(1,6));
    	  assertEquals(57, Utils.calculNumCase(2,6));
    	  assertEquals(58, Utils.calculNumCase(3,6));
    	  assertEquals(59, Utils.calculNumCase(4,6));
    	  assertEquals(60, Utils.calculNumCase(5,6));
    	  assertEquals(61, Utils.calculNumCase(6,6));
    	  assertEquals(62, Utils.calculNumCase(7,6));
    	  assertEquals(63, Utils.calculNumCase(8,6));
    	  assertEquals(64, Utils.calculNumCase(0,7));
    	  assertEquals(65, Utils.calculNumCase(1,7));
    	  assertEquals(66, Utils.calculNumCase(2,7));
    	  assertEquals(67, Utils.calculNumCase(3,7));
    	  assertEquals(68, Utils.calculNumCase(4,7));
    	  assertEquals(69, Utils.calculNumCase(5,7));
    	  assertEquals(70, Utils.calculNumCase(6,7));
    	  assertEquals(71, Utils.calculNumCase(7,7));
    	  assertEquals(72, Utils.calculNumCase(8,7));
    	  assertEquals(73, Utils.calculNumCase(0,8));
    	  assertEquals(74, Utils.calculNumCase(1,8));
    	  assertEquals(75, Utils.calculNumCase(2,8));
    }
    
    @Test
    void testCalculNumCase76a81() {
    	  assertEquals(76, Utils.calculNumCase(3,8));
    	  assertEquals(77, Utils.calculNumCase(4,8));
    	  assertEquals(78, Utils.calculNumCase(5,8));
    	  assertEquals(79, Utils.calculNumCase(6,8));
    	  assertEquals(80, Utils.calculNumCase(7,8));
    	  assertEquals(81, Utils.calculNumCase(8,8));


    }
    
    @Test
    void testcalculNombreCandidats() {
    	CandidatsCase c1 = new CandidatsCase();
    	c1.setAllCandidatsToFalse();
    	assertEquals(0,Utils.calculNombreCandidats(c1.getCandidats()));
    	c1.setCandidat(2);
    	assertEquals(1,Utils.calculNombreCandidats(c1.getCandidats()));
    	c1.setCandidat(4);
    	c1.setCandidat(9);
    	assertEquals(3,Utils.calculNombreCandidats(c1.getCandidats()));
    }
    
    @Test
    void testtrouveCandidatNumero() {
    	CandidatsCase c1 = new CandidatsCase();
    	c1.setAllCandidatsToFalse();
    	assertEquals(0,Utils.trouveCandidatNumero(c1, 1));
    	c1.setCandidat(2);
    	c1.setCandidat(4);
    	c1.setCandidat(9);
    	assertEquals(2,Utils.trouveCandidatNumero(c1, 1));
    	assertEquals(4,Utils.trouveCandidatNumero(c1, 2));
    	assertEquals(9,Utils.trouveCandidatNumero(c1, 3));
    	assertEquals(0,Utils.trouveCandidatNumero(c1, 4));
    }

    @Test
    void testCalculAutresLignesOuColonnesDuneRegion() {
        assertEquals(1,Utils.calculAutresLignesOuColonnesDuneRegion(0,1));
        assertEquals(2,Utils.calculAutresLignesOuColonnesDuneRegion(0,2));

        assertEquals(0,Utils.calculAutresLignesOuColonnesDuneRegion(1,1));
        assertEquals(2,Utils.calculAutresLignesOuColonnesDuneRegion(1,2));

        assertEquals(0,Utils.calculAutresLignesOuColonnesDuneRegion(2,1));
        assertEquals(1,Utils.calculAutresLignesOuColonnesDuneRegion(2,2));

        assertEquals(4,Utils.calculAutresLignesOuColonnesDuneRegion(3,1));
        assertEquals(5,Utils.calculAutresLignesOuColonnesDuneRegion(3,2));

        assertEquals(3,Utils.calculAutresLignesOuColonnesDuneRegion(4,1));
        assertEquals(5,Utils.calculAutresLignesOuColonnesDuneRegion(4,2));

        assertEquals(3,Utils.calculAutresLignesOuColonnesDuneRegion(5,1));
        assertEquals(4,Utils.calculAutresLignesOuColonnesDuneRegion(5,2));

        assertEquals(7,Utils.calculAutresLignesOuColonnesDuneRegion(6,1));
        assertEquals(8,Utils.calculAutresLignesOuColonnesDuneRegion(6,2));

        assertEquals(6,Utils.calculAutresLignesOuColonnesDuneRegion(7,1));
        assertEquals(8,Utils.calculAutresLignesOuColonnesDuneRegion(7,2));

        assertEquals(6,Utils.calculAutresLignesOuColonnesDuneRegion(8,1));
        assertEquals(7,Utils.calculAutresLignesOuColonnesDuneRegion(8,2));
    }
}
