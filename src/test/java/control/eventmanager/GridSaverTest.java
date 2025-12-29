package control.eventmanager;

import model.grille.Grille;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

class GridSaverTest {

    @Test
	void testSaveGrille() throws IOException {
        Grille grille =new Grille();
        String NOM_FICHIER_SOURCE = "src/test/resources/grillesTest/init67-40.sud";
        grille.init(Paths.get(NOM_FICHIER_SOURCE).toAbsolutePath());
        String nomFichierCible = System.getProperty("user.dir")+"/testSaveSudoku.sud";

        GridSaverService saverService = new GridSaverService();
        saverService.saveGrid(grille, nomFichierCible);
		File fichierSource = new File(NOM_FICHIER_SOURCE);
		File fichierCible = new File(nomFichierCible);
		Assertions.assertTrue(FileUtils.contentEquals(fichierSource, fichierCible), "The files differ!");
		if (fichierCible.exists()&& !fichierCible.delete()) {
			System.err.println("Failed to delete the file: " + fichierCible.getAbsolutePath());
		}
	}

}
