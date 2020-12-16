package resolution;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.CaseEnCours;
import modele.Grille;
import modele.InitialiseurDeGrille;

class CandidatUniqueDansColonneTest {
	private static Grille grille;
	private static CandidatUniqueDansColonne methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\src\\test\\resources\\grillesTest\\CandidatUniqueDansColonne.sud");
		i.calculTousLesCandidats();
		methode = new CandidatUniqueDansColonne(null, grille);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTraiteCaseEnCours() {
		CaseEnCours.setCaseEnCours(53);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(9, methode.getSolution());
	}

}
