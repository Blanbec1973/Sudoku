package resolution;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ResolutionAction action = methode.traiteCaseEnCours(context, false)
                .orElseThrow(()-> new AssertionError("Should be present"));
        assertEquals(8, action.getNumCaseAction());
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(8,3);

        ResolutionAction action2 = methode.traiteCaseEnCours(context, false)
                .orElseThrow(()-> new AssertionError("Should be present"));
        assertEquals(8, action2.getNumCaseAction());
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(8,8);

        CaseContext context2 = new CaseContext(17);
        ResolutionAction action3 = methode.traiteCaseEnCours(context2, false)
                .orElseThrow(()-> new AssertionError("Should be present"));
        assertEquals(17, action3.getNumCaseAction());
        assertEquals(3, methode.candidatAEliminer);
        grille.elimineCandidat(17,3);

        ResolutionAction action4 = methode.traiteCaseEnCours(context2, false)
                .orElseThrow(()-> new AssertionError("Should be present"));
        assertEquals(17, action4.getNumCaseAction());
        assertEquals(8, methode.candidatAEliminer);
        grille.elimineCandidat(17,8);
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesColonne",methode.getSimpleName());
    }


}