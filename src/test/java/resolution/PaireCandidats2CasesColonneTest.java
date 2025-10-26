package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaireCandidats2CasesColonneTest {
    private PaireCandidats2CasesColonne methode;
    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        grille.init(System.getProperty("user.dir")+"/src/test/resources/grillesTest/PaireCandidats2CasesColonne.sud");
        methode = new PaireCandidats2CasesColonne(grille);
    }

    @Test
    void testTraiteCaseEnCours() {
        CaseEnCours.setCaseEnCours(1);
        assertFalse(methode.traiteCaseEnCours(false).isPresent());
        CaseEnCours.setCaseEnCours(8);
        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(8, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(8,3);

        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(8, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(8,8);

        CaseEnCours.setCaseEnCours(17);
        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(17, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(17,3);

        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(17, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(17,8);

        assertFalse(methode.traiteCaseEnCours(false).isPresent());
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesColonne",methode.getSimpleName());
    }


}