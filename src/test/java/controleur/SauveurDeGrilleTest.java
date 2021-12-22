package controleur;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modele.Grille;
import modele.InitialiseurDeGrille;

class SauveurDeGrilleTest {
	private static Grille grille;
	private static final String nomFichierSource=System.getProperty("user.dir")+"/init67-40.sud";
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		grille =new Grille();
        InitialiseurDeGrille i  = new InitialiseurDeGrille(grille);
        i.init(nomFichierSource);
	}

	@Test
	void testSaveGrille() throws IOException {
		String nomFichierCible = System.getProperty("user.dir")+"/testSaveSudoku.sud";
		SauveurDeGrille.saveGrille(grille, nomFichierCible);		
		File fichierSource = new File(nomFichierSource);
		File fichierCible = new File(nomFichierCible);
		assertEquals(97, fichierCible.length());
		assertTrue("The files differ!", FileUtils.contentEquals(fichierSource, fichierCible));
		fichierCible.delete();
	}

}
