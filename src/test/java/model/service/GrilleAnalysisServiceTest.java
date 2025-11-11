package model.service;

import model.grille.CaseContext;
import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class GrilleAnalysisServiceTest {
    private Grille grille;
    private GrilleAnalysisService grilleAnalysisService;


    @BeforeEach
    void setUp() {
        grille = new Grille();
        String fileName = "src/test/resources/grillesTest/initFacile.sud";
        grille.init(Paths.get(fileName).toAbsolutePath());
        grilleAnalysisService = new GrilleAnalysisService(grille);
    }

    @Test
    void testCheckPresenceCandidatLigne() {
        CaseContext context = new CaseContext(2,4);
        boolean present = grilleAnalysisService.checkPresenceCandidatLigne(context, 7);
        assertTrue(present);
    }

    @Test
    void testCheckPresenceCandidatColonne() {
        CaseContext context = new CaseContext(2,4);
        boolean present = grilleAnalysisService.checkPresenceCandidatColonne(context, 8);
        assertTrue(present);
    }

    @Test
    void testCheckPresenceCandidatRegion() {
        CaseContext context = new CaseContext(2,4);
        boolean present = grilleAnalysisService.checkPresenceCandidatRegion(context, 7);
        assertTrue(present);
    }

    @Test
    void testCalculNombreCaseATrouverDansLigne() {
        int count = grilleAnalysisService.calculNombreCaseATrouverDansLigne(4);
        assertTrue(count > 0);
    }

    @Test
    void testCalculNombreCaseATrouverDansColonne() {
        int count = grilleAnalysisService.calculNombreCaseATrouverDansColonne(2);
        assertTrue(count > 0);
    }
}