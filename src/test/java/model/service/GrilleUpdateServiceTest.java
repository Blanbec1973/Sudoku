package model.service;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolution.ResolutionAction;
import utils.Utils;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GrilleUpdateServiceTest {
    private Grille grille;
    private GrilleUpdateService grilleUpdateService;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        String fileName = "src/test/resources/grillesTest/initFacile.sud";
        grille.init(Paths.get(fileName).toAbsolutePath());
        GrilleAnalysisService grilleAnalysisService = new GrilleAnalysisService(grille);
        grilleUpdateService = new GrilleUpdateService(grille, grilleAnalysisService);
    }

    @Test
    void testElimineCandidatsCaseTrouvee() {
        ResolutionAction action = new ResolutionAction(39,7, null,
                                                        null, new CaseContext(39), null);
        grille.setValeurCaseEnCours(action);
        int x = Utils.calculXsearch(action.getNumCaseAction());
        int y = Utils.calculYsearch(action.getNumCaseAction());

        // Vérifie que le candidat 7 est éliminé dans la ligne, colonne et région
        for (int i = 0; i < 9; i++) {
            assertFalse(grille.isCandidat(i, y, 7));
            assertFalse(grille.isCandidat(x, i, 7));
        }

        CaseContext context = new CaseContext(action.getNumCaseAction());

        for (int abs = context.getxRegion(); abs < context.getxRegion() + 3; abs++) {
            for (int ord = context.getyRegion(); ord < context.getyRegion() + 3; ord++) {
                assertFalse(grille.isCandidat(abs, ord, 7));
            }
        }
    }

    @Test
    void testCalculTousLesCandidats() {
        grilleUpdateService.calculTousLesCandidats();
        int numCase = grille.getCasesAtrouver().get(0);
        assertTrue(grille.getCandidats(numCase).getNombreCandidats() > 0);
    }

    @Test
    void testCalculCandidatsInitiaux() {
        grilleUpdateService.calculCandidatsInitiaux(new CaseContext(2,0));
        assertFalse(grille.isCandidat(2,0,1));
        assertTrue(grille.isCandidat(2,0,2));
        assertTrue(grille.isCandidat(2,0,3));
        assertFalse(grille.isCandidat(2,0,4));
        assertFalse(grille.isCandidat(2,0,5));
        assertFalse(grille.isCandidat(2,0,6));
        assertFalse(grille.isCandidat(2,0,7));
        assertFalse(grille.isCandidat(2,0,8));
        assertFalse(grille.isCandidat(2,0,9));
    }
}