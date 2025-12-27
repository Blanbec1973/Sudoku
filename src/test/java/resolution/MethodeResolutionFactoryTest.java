package resolution;

import model.grille.Grille;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MethodeResolutionFactoryTest {

    @Test
    void testCreateAllReturnsExpectedMethods() {
        Grille grille = new Grille();
        List<MethodeResolution> methodes = MethodeResolutionFactory.createAll(grille);

        assertNotNull(methodes);
        assertFalse(methodes.isEmpty());
        assertTrue(methodes.stream().anyMatch(CandidatUniqueDansCase.class::isInstance));
        assertTrue(methodes.stream().anyMatch(PaireConjugueeDansZone.class::isInstance));
        assertTrue(methodes.stream().anyMatch(AbsenceCandidatEnLigneDansLesAutresRegions.class::isInstance));
        assertTrue(methodes.stream().anyMatch(TripletteCandidatsEnZone.class::isInstance));

        // Vérifie le nombre attendu (actuellement 16)
        assertEquals(16, methodes.size());
    }
}