package resolution;

import model.grille.CaseContext;
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
        CaseContext context = new CaseContext(1);
        assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
        CaseContext context2 = new CaseContext(64);
        assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
        assertEquals(64, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(64,3);

        assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
        assertEquals(64, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(64,8);

        CaseContext context3 = new CaseContext(65);
        assertTrue(methode.traiteCaseEnCours(context3, false).isPresent());
        assertEquals(65, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(65,3);

        assertTrue(methode.traiteCaseEnCours(context3, false).isPresent());
        assertEquals(65, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(65,8);

        assertFalse(methode.traiteCaseEnCours(context, false).isPresent());
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesLigne",methode.getSimpleName());
    }

}