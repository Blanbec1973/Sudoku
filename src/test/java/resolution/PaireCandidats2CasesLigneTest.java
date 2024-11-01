package resolution;

import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaireCandidats2CasesLigneTest {
    private PaireCandidats2CasesLigne methode;

    @BeforeEach
    void setUp() {
        Grille grille = new Grille();
        methode = new PaireCandidats2CasesLigne(null, grille);
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesLigne",methode.getSimpleName());
    }

}