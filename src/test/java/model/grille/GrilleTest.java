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
    void testGetxCase() {
        assertEquals(0,grille.getxCase(1));
        assertEquals(1,grille.getxCase(2));
        assertEquals(7,grille.getxCase(8));
        assertEquals(8,grille.getxCase(9));
        assertEquals(0,grille.getxCase(10));
    }
    @Test
    void testGetyCase() {
        assertEquals(0,grille.getyCase(1));
        assertEquals(0,grille.getyCase(2));
        assertEquals(0,grille.getyCase(9));
        assertEquals(1,grille.getyCase(10));
        assertEquals(8,grille.getyCase(81));
    }
    @Test
    void testConstruitLibelleCandidats() {
        assertEquals("<html> 23<br>   <br>   </html>",grille.construitLibelleCandidats(3));
    }
}
