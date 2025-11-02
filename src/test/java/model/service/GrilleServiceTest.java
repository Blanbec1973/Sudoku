package model.service;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        CaseEnCours.setCaseEnCours(39); // Ligne 4
        boolean present = grilleService.checkPresenceCandidatLigne(7, 2, 4);
        assertTrue(present);
    }

    @Test
    void testCheckPresenceCandidatColonne() {
        CaseEnCours.setCaseEnCours(39); // Colonne 2
        boolean present = grilleService.checkPresenceCandidatColonne(8, 2, 4);
        assertTrue(present);
    }

    @Test
    void testCheckPresenceCandidatRegion() {
        CaseEnCours.setCaseEnCours(39); // Région 5
        boolean present = grilleService.checkPresenceCandidatRegion(7, 2, 4);
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
        CaseEnCours.setCaseEnCours(39);
        grille.setValeurCaseEnCours(7);
        int x = CaseEnCours.getX();
        int y = CaseEnCours.getY();
        grilleService.elimineCandidatsCaseTrouvee(x, y, 7);

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
        grilleService.calculTousLesCandidats();
        int numCase = grille.getCasesAtrouver().get(0);
        assertTrue(grille.getCandidats(numCase).getNombreCandidats() > 0);
    }
}