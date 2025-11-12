package model.service;

import model.grille.CaseContext;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import resolution.ResolutionAction;
import utils.Utils;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GrilleServiceTest {

    private Grille grille;
    private GrilleService grilleService;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        String fileName = "src/test/resources/grillesTest/initFacile.sud";
        grille.init(Paths.get(fileName).toAbsolutePath());
        grilleService = new GrilleService(grille);
    }

    @Test
    void testCheckPresenceCandidatLigne() {
        CaseContext context = new CaseContext(2,4);
        boolean present = grilleService.checkPresenceCandidatLigne(context, 7);
        assertTrue(present);
    }

    @Test
    void testCheckPresenceCandidatColonne() {
        CaseContext context = new CaseContext(2,4);
        boolean present = grilleService.checkPresenceCandidatColonne(context, 8);
        assertTrue(present);
    }

    @Test
    void testCheckPresenceCandidatRegion() {
        CaseContext context = new CaseContext(39);
        boolean present = grilleService.checkPresenceCandidatRegion(context,7);
        assertTrue(present);
    }

    @Test
    void testCalculNombreCaseATrouverDansLigne() {
        int count = grilleService.calculNombreCaseATrouverDansLigne(4);
        assertTrue(count > 0);
    }

    @Test
    void testCalculNombreCaseATrouverDansColonne() {
        int count = grilleService.calculNombreCaseATrouverDansColonne(2);
        assertTrue(count > 0);
    }

    @Test
    void testElimineCandidatsCaseTrouvee() {
        CaseContext context = new CaseContext(6);
        ResolutionAction action = new ResolutionAction(6,6, null,
                                         null, context, null);
        grille.setValeurCaseEnCours(action);
        grilleService.elimineCandidatsCaseTrouvee(action);
        int x = Utils.calculXsearch(6);
        int y = Utils.calculYsearch(6);

        // Vérifie que le candidat 6 est éliminé dans la ligne, colonne et région
        for (int i = 0; i < 9; i++) {
            assertFalse(grille.isCandidat(i, y, 6));
            assertFalse(grille.isCandidat(x, i, 6));
        }


        for (int abs = context.getxRegion(); abs < context.getxRegion() + 3; abs++) {
            for (int ord = context.getyRegion(); ord < context.getyRegion() + 3; ord++) {
                assertFalse(grille.isCandidat(abs, ord, 6));
            }
        }
    }

    @Test
    void testCalculTousLesCandidats() {
        grilleService.calculTousLesCandidats();
        int numCase = grille.getCasesAtrouver().get(0);
        assertTrue(grille.getCandidats(numCase).getNombreCandidats() > 0);
    }
}