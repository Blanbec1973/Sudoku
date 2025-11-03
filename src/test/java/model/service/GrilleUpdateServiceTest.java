package model.service;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

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
        CaseEnCours.setCaseEnCours(39);
        grille.setValeurCaseEnCours(7);
        int x = CaseEnCours.getX();
        int y = CaseEnCours.getY();
        grilleUpdateService.elimineCandidatsCaseTrouvee(x, y, 7);

        // Vérifie que le candidat 7 est éliminé dans la ligne, colonne et région
        for (int i = 0; i < 9; i++) {
            assertFalse(grille.isCandidat(i, y, 7));
            assertFalse(grille.isCandidat(x, i, 7));
        }

        for (int abs = CaseEnCours.getxRegion(); abs < CaseEnCours.getxRegion() + 3; abs++) {
            for (int ord = CaseEnCours.getyRegion(); ord < CaseEnCours.getyRegion() + 3; ord++) {
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
        CaseEnCours.setCaseEnCours(3);
        grilleUpdateService.calculCandidatsInitiaux(2,0);
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