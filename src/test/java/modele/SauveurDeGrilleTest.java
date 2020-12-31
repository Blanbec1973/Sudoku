package modele;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SauveurDeGrilleTest {
	private static Grille grille;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille =new Grille();
        InitialiseurDeGrille i  = new InitialiseurDeGrille(grille);
        i.init("C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init67-40.sud");
	}

	@Test
	void testSauveurDeGrille() {
		fail("Not yet implemented");
	}

}
