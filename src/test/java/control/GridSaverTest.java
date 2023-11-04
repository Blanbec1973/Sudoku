package control;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.grille.Grille;

class GridSaverTest {
	private static Grille grille;
	private static final String nomFichierSource=System.getProperty("user.dir")+"/src/test/resources/grillesTest/init67-40.sud";
	
	@BeforeAll
	static void setUpBeforeClass() {
		grille =new Grille();
        grille.init(nomFichierSource);
	}

	@Test
	void testSaveGrille() throws IOException {
		String nomFichierCible = System.getProperty("user.dir")+"/testSaveSudoku.sud";
		GridSaver.saveGrid(grille, nomFichierCible);
		File fichierSource = new File(nomFichierSource);
		File fichierCible = new File(nomFichierCible);
		//assertEquals(97, fichierCible.length());
		Assertions.assertTrue(FileUtils.contentEquals(fichierSource, fichierCible), "The files differ!");
		fichierCible.delete();
	}

}
