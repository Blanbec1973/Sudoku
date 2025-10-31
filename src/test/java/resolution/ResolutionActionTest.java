package resolution;

import model.grille.CandidatsCase;
import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResolutionActionTest {

    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        grille.init(System.getProperty("user.dir") + "/src/test/resources/grillesTest/initFacile.sud");
    }

    @Test
    void testApplyTo_setValeurCase() {
        // Choisir une case à trouver
        int numCase = grille.getCasesAtrouver().get(0);
        CaseEnCours.setCaseEnCours(numCase);

        ResolutionAction action = new ResolutionAction(numCase, 5, null, null);
        action.applyTo(grille);

        assertTrue(grille.isCaseTrouvee(numCase));
        assertEquals(5, grille.getValeurCase(numCase));
    }

    @Test
    void testApplyTo_elimineCandidat() {
        int numCase = grille.getCasesAtrouver().get(0);
        CaseEnCours.setCaseEnCours(numCase);

        assertTrue(grille.isCandidat(numCase, 3)); // Vérifier que le candidat est présent

        ResolutionAction action = new ResolutionAction(numCase, null, 3, null);
        action.applyTo(grille);

        assertFalse(grille.isCandidat(numCase, 3)); // Vérifier qu’il a été éliminé
    }

    @Test
    void testApplyTo_rienAFaire() {
        int numCase = grille.getCasesAtrouver().get(0);
        CaseEnCours.setCaseEnCours(numCase);

        CandidatsCase avant = grille.getCandidats(numCase);
        ResolutionAction action = new ResolutionAction(numCase, null, null, null);
        action.applyTo(grille);

        CandidatsCase apres = grille.getCandidats(numCase);
        assertEquals(avant.toString(), apres.toString()); // Aucun changement
    }
}