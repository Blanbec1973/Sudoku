package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class PaireCandidats2CasesColonneTest {
    private PaireCandidats2CasesColonne methode;
    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        String fileName = "src/test/resources/grillesTest/PaireCandidats2CasesColonne.sud";
        grille.init(Paths.get(fileName).toAbsolutePath());
        methode = new PaireCandidats2CasesColonne(grille);
    }

    @Test
    void testTraiteCaseEnCours() {
        CaseContext context = new CaseContext(8);
        assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
        assertEquals(8, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(8,3);

        assertTrue(methode.traiteCaseEnCours(context, false).isPresent());
        assertEquals(8, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(8,8);

        CaseContext context2 = new CaseContext(17);
        assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
        assertEquals(17, methode.numCaseAction);
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(17,3);

        assertTrue(methode.traiteCaseEnCours(context2, false).isPresent());
        assertEquals(17, methode.numCaseAction);
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(17,8);
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesColonne",methode.getSimpleName());
    }


}