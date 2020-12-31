package modele;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SauveurDeGrilleTest {
	private static Grille grille;
	private static final String nomFichierSource="C:\\Users\\heynerr\\Documents\\W-Workspace\\Sudoku\\init67-40.sud";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille =new Grille();
        InitialiseurDeGrille i  = new InitialiseurDeGrille(grille);
        i.init(nomFichierSource);
	}

	@Test
	void testSauveurDeGrille() throws IOException {
		String nomFichierCible = "c:\\Temp\\testSaveSudoku.sud";
		new SauveurDeGrille(grille, nomFichierCible);		
		File fichierSource = new File(nomFichierSource);
		File fichierCible = new File(nomFichierCible);
		assertEquals(97, fichierCible.length());
		assertTrue("The files differ!", FileUtils.contentEquals(fichierSource, fichierCible));
		fichierCible.delete();
	}

}
