package resolution;

import model.grille.CaseEnCours;
import model.grille.Grille;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaireCandidats2CasesLigneTest {
    private PaireCandidats2CasesLigne methode;
    private Grille grille;

    @BeforeEach
    void setUp() {
        grille = new Grille();
        methode = new PaireCandidats2CasesLigne(null,grille);
    }

    @Test
    void getSimpleName() {
        assertEquals("PaireCandidats2CasesLigne",methode.getSimpleName());
    }

}