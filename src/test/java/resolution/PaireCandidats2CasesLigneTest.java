package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaireCandidats2CasesLigneTest {
    private PaireCandidats2CasesLigne methode;
    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireCandidats2CasesLigne.sud");
        methode = new PaireCandidats2CasesLigne(grille);
    }

    @Test
    void testTraiteCaseEnCours() {
        CaseEnCours.setCaseEnCours(1);
        assertFalse(methode.traiteCaseEnCours(false));
        CaseEnCours.setCaseEnCours(64);
        assertTrue(methode.traiteCaseEnCours(false));
        assertEquals(64, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(64,3);

        assertTrue(methode.traiteCaseEnCours(false));
        assertEquals(64, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(64,8);

        CaseEnCours.setCaseEnCours(65);
        assertTrue(methode.traiteCaseEnCours(false));
        assertEquals(65, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(65,3);

        assertTrue(methode.traiteCaseEnCours(false));
        assertEquals(65, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(65,8);

        assertFalse(methode.traiteCaseEnCours(false));
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesLigne",methode.getSimpleName());
    }

}