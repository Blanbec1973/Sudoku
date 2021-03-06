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

class CandidatUniqueDansLigneTest {
	private static Grille grille;
	private static CandidatUniqueDansLigne methode;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille = new Grille();
		InitialiseurDeGrille i = new InitialiseurDeGrille(grille);
		i.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\src\\test\\resources\\grillesTest\\CandidatUniqueDansLigne.sud");
		i.calculTousLesCandidats();
		methode = new CandidatUniqueDansLigne(null, grille);
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
		CaseEnCours.setCaseEnCours(2);
		assertFalse(methode.traiteCaseEnCours(false));
		
		CaseEnCours.setCaseEnCours(39);
		assertTrue(methode.traiteCaseEnCours(false));
		assertEquals(6, methode.getSolution());
	}


}
