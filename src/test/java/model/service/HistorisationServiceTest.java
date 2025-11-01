package model.service;

import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistorisationServiceTest {

    private HistorisationService historisationService;
    private Grille grille;

    @BeforeEach
    void setUp() {
        historisationService = new HistorisationService();
        grille = new Grille();
        grille.init(System.getProperty("user.dir") + "/src/test/resources/grillesTest/initFacile.sud");
    }


    @Test
    void testHistoriseGrille() {
        historisationService.reloadGrille(grille); // on part d'une seule grille
        assertFalse(historisationService.canReloadLastHistoricization(), "Il ne devrait y avoir qu'une seule grille");

        grille.setValeurCaseEnCours(5); // modification
        historisationService.historiseGrille(grille); // on ajoute une version

        assertTrue(historisationService.canReloadLastHistoricization(), "Il devrait y avoir au moins deux grilles historisées");
    }


    @Test
    void testReloadGrille() {
        historisationService.reloadGrille(grille);
        assertFalse(historisationService.canReloadLastHistoricization()); // une seule grille
    }

    @Test
    void testSupprimeDerniereGrille() {
        historisationService.historiseGrille(grille);
        grille.setValeurCaseEnCours(5); // modification
        historisationService.historiseGrille(grille);
        assertTrue(historisationService.canReloadLastHistoricization());

        historisationService.supprimeDerniereGrille(grille);
        assertTrue(grille.isCaseATrouver(Grille.calculNumCase(0, 5))); // vérifie que la case est revenue à l'état précédent
    }

    @Test
    void testCanReloadLastHistoricization() {
        historisationService.historiseGrille(grille);
        assertFalse(historisationService.canReloadLastHistoricization());

        grille.setValeurCaseEnCours(5);
        historisationService.historiseGrille(grille);
        assertTrue(historisationService.canReloadLastHistoricization());
    }
}