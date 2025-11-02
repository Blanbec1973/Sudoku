package model.grille;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GrilleTest {
    private Grille grille;
    @BeforeAll
    void beforeAll() {
        grille = new Grille();
        String fileName = "src/test/resources/grillesTest/initFacile.sud";
        grille.init(Paths.get(fileName).toAbsolutePath());
    }
    @Test
    void testIsCaseTrouvee() {
        assertFalse(grille.isCaseTrouvee(1));
        assertFalse(grille.isCaseTrouvee(3));
        CaseEnCours.setCaseEnCours(69);
        grille.setValeurCaseEnCours(7);
        assertTrue(grille.isCaseTrouvee(69));
    }
    @Test
    void testConstruitLibelleCandidats() {
        assertEquals("<html> 23<br>   <br>   </html>",grille.construitLibelleCandidats(3));
    }
}
