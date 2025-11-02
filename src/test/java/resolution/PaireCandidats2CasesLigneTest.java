package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PaireCandidats2CasesLigneTest {
    private PaireCandidats2CasesLigne methode;
    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        String fileName = "src/test/resources/grillesTest/PaireCandidats2CasesLigne.sud";
        grille.init(Paths.get(fileName).toAbsolutePath());
        methode = new PaireCandidats2CasesLigne(grille);
    }

    @Test
    void testTraiteCaseEnCours() {
        CaseEnCours.setCaseEnCours(1);
        assertFalse(methode.traiteCaseEnCours(false).isPresent());
        CaseEnCours.setCaseEnCours(64);
        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(64, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(64,3);

        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(64, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(64,8);

        CaseEnCours.setCaseEnCours(65);
        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(65, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(65,3);

        assertTrue(methode.traiteCaseEnCours(false).isPresent());
        assertEquals(65, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(65,8);

        assertFalse(methode.traiteCaseEnCours(false).isPresent());
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesLigne",methode.getSimpleName());
    }

}