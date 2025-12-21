package model.grille;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidatUtilsTest {
    @Test
    void testcalculNombreCandidats() {
        CandidatsCase c1 = new CandidatsCase();
        c1.setAllCandidatsToFalse();
        assertEquals(0, CandidatUtils.calculNombreCandidats(c1.getCandidats()));
        c1.setCandidat(2);
        assertEquals(1,CandidatUtils.calculNombreCandidats(c1.getCandidats()));
        c1.setCandidat(4);
        c1.setCandidat(9);
        assertEquals(3,CandidatUtils.calculNombreCandidats(c1.getCandidats()));
    }

    @Test
    void testtrouveCandidatNumero() {
        CandidatsCase c1 = new CandidatsCase();
        c1.setAllCandidatsToFalse();
        assertEquals(0,CandidatUtils.trouveCandidatNumero(c1, 1));
        c1.setCandidat(2);
        c1.setCandidat(4);
        c1.setCandidat(9);
        assertEquals(2, CandidatUtils.trouveCandidatNumero(c1, 1));
        assertEquals(4,CandidatUtils.trouveCandidatNumero(c1, 2));
        assertEquals(9,CandidatUtils.trouveCandidatNumero(c1, 3));
        assertEquals(0,CandidatUtils.trouveCandidatNumero(c1, 4));
    }
    @Test
    void testCalculOuLogique2Candidats() {
        boolean[] tab1 = {true, true, false, false, false, false, false, false, false, false};
        boolean[] tab2 = {true, false, true, false, false, false, false, false, false, false};
        boolean[] res = {true, true, true, false, false, false, false, false, false, false};
        assertArrayEquals(res, CandidatUtils.calculOuLogique2Candidats(tab1, tab2));
    }
    @Test
    void testCalculEtLogique2Candidats() {
        boolean[] tab1 = {false, true, false, true, false, false, false, false, false, false};
        CandidatsCase c1 = new CandidatsCase(tab1);
        boolean[] tab2 = {false, false, true, true, false, false, false, false, false, false};
        CandidatsCase c2 = new CandidatsCase(tab2);
        boolean[] res = {false, false, false, true, false, false, false, false, false, false};
        CandidatsCase resC = new CandidatsCase(res);
        assertArrayEquals(resC.getCandidats(), CandidatUtils.calculEtLogique2Candidats(c1, c2).getCandidats());
    }
}